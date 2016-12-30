package com.vito.work.jxau.plugin

import com.vito.work.jxau.core.DomainUrl
import com.vito.work.jxau.plugin.domain_entity.PersonalScore
import org.apache.http.cookie.Cookie

/**
 * Created by lingzhiyuan.
 * Date : 16/6/14.
 * Time : 10:37.
 * Description:
 *
 */

// Path for Personal Score
const val PersonalScorePath = "/SystemManage/CJManage/GetXsCjByXh/"

fun fetchPersonalScore(cookies: List<Cookie>): List<PersonalScore>? {
    val ticketCookie = cookies.lastOrNull { it.value.contains("ticket=") }
    val urlSuffix = ticketCookie?.name
    val urlForScore = "$DomainUrl$PersonalScorePath$urlSuffix"
    val data: List<PersonalScore>? = com.vito.work.jxau.core.invokeStandardAPI(urlForScore, null, cookies)
    return data
}