package com.vito.work.jxau.plugin

import com.vito.work.jxau.plugin.domain_entity.PersonalTimetable
import org.apache.http.cookie.Cookie
import org.apache.http.message.BasicNameValuePair

/**
 * Created by lingzhiyuan.
 * Date : 16/6/14.
 * Time : 10:40.
 * Description:
 *
 */

// Path for Personal Timetable
const val PersonalTimetablePath = "/PaikeManage/KebiaoInfo/GetStudentKebiaoByXq/"

fun fetchPersonalTimetable(cookies: List<Cookie>, term: Int): List<PersonalTimetable>? {

    val ticketCookie = cookies.lastOrNull { it.value.contains("ticket=") }
    val urlSuffix = ticketCookie?.name
    val urlForPersonalSchedule = "${com.vito.work.jxau.core.DomainUrl}${PersonalTimetablePath}$urlSuffix"
    val params = mutableListOf<BasicNameValuePair>()
    params.add(BasicNameValuePair("xq", term.toString()))
    params.add(BasicNameValuePair("start", 0.toString()))
    params.add(BasicNameValuePair("limit", 1000.toString()))
    val data = com.vito.work.jxau.core.invokeStandardAPI<PersonalTimetable>(urlForPersonalSchedule, params, cookies)
    return data
}