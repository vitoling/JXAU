package com.vito.work.jxau.plugin.domain_entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by lingzhiyuan.
 * Date : 16/6/14.
 * Time : 00:07.
 * Description:
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class PersonalTimetable(
        var Message: String?
                            )
