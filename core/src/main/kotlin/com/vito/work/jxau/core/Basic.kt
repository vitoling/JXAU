package com.vito.work.jxau.core

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.http.NameValuePair
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.cookie.Cookie
import org.apache.http.impl.client.BasicCookieStore
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import java.net.SocketTimeoutException

/**
 * Created by lingzhiyuan.
 * Date : 16/6/14.
 * Time : 10:39.
 * Description:
 *
 */

const val DEFAULT_REQUEST_PARAMS_CHARSET = "utf-8"
const val DEFAULT_SOCKET_TIMEOUT = 3000

const val DomainUrl = "http://jwgl.jxau.edu.cn"
// Path for Login
const val LoginPath = "/user/checklogin"
const val GuidPath = "/User/CheckGuid/"

val CookiesStorageByUsername = mutableMapOf<String, List<Cookie>>()

fun fetchCookies(username: String, password: String): List<Cookie> {
    var cookies = CookiesStorageByUsername["username"] ?: listOf()
    cookies = checkCookieOrLogin(username, password, cookies)
    return cookies
}

private fun checkCookieOrLogin(username: String, password: String, cookies: List<Cookie>): List<Cookie> {
    if (! checkGuid(cookies)) {
        val url = loginUrlBuilder(LoginPath, username, password)
        val cookieStore = BasicCookieStore()
        HttpClients.custom().setDefaultCookieStore(cookieStore).build().execute(HttpPost(url))
        if (cookieStore.cookies.size == 0) {
            throw Exception("Login Failed")
        }
        CookiesStorageByUsername.put(username, cookies)
        return cookieStore.cookies
    }
    return cookies
}

private fun checkGuid(cookies: List<Cookie>): Boolean {
    val guid = cookies.lastOrNull { it.value.contains("ticket=") }?.name
    val url = "$DomainUrl$GuidPath"
    val result = invokeStandardAPI<Any>(url, listOf(BasicNameValuePair("guid", guid)), cookies)
    return if (result == null) return false else true
}

private fun loginUrlBuilder(path: String, username: String, password: String) = "$DomainUrl$path?UserName=$username&Password=$password"

inline fun <reified T : Any> invokeStandardAPI(url: String, params: List<NameValuePair>?, cookies: List<Cookie>): List<T>? {
    val result: String?
    try {
        result = fetchStandardData(url, cookies, params)
    }
    catch(ex: SocketTimeoutException) {
        ex.printStackTrace()
        return null
    }
    // Deserialize JSON data
    val mapper = jacksonObjectMapper()
    val domainData = mapper.readValue(result, DomainData::class.java)
    if (! domainData.Result) return null
    val innerData = domainData.Data
    val innerDataList = mutableListOf<T>()
    innerData?.forEach { innerDataList.add(mapper.readValue(mapper.writeValueAsString(it), T::class.java)) }
    return innerDataList
}

private val fetchStandardData = fun(baseUrl: String, cookies: List<Cookie>, params: List<NameValuePair>?): String? {
    val cookieStore = BasicCookieStore()
    cookieStore.addCookies(cookies.toTypedArray())
    val post = HttpPost(baseUrl)
    post.config = RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).build()
    if (params != null) {
        post.entity = UrlEncodedFormEntity(params, DEFAULT_REQUEST_PARAMS_CHARSET)
    }
    return EntityUtils.toString(HttpClients.custom().setDefaultCookieStore(cookieStore).build().execute(post).entity)
}
