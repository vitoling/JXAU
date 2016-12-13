package com.vito.work.jxau.plugin

import com.vito.work.jxau.core.fetchCookies
import org.junit.Test
import java.util.*

/**
 * Created by lingzhiyuan.
 * Date : 2016/12/13.
 * Time : 16:26.
 * Description:
 *
 */

@Test
fun test1(){

    val config = Properties()
    val loader = Thread.currentThread().contextClassLoader
    config.load(loader.getResourceAsStream("config.properties"))
    val username = config.getProperty("username")
    val password = config.getProperty("password")
    val cookies = fetchCookies(username, password)

    val list = fetchPersonalScore(cookies)
    list?.forEach(::println)

}

@Test
fun test2(){
    val config = Properties()
    val loader = Thread.currentThread().contextClassLoader
    config.load(loader.getResourceAsStream("config.properties"))
    val username = config.getProperty("username")
    val password = config.getProperty("password")
    val cookies = fetchCookies(username, password)

    val list2 = fetchPersonalTimetable(cookies, 20141)
    list2?.forEach { println(it.Message) }
}
