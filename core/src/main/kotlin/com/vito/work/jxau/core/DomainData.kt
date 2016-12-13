package com.vito.work.jxau.core

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by lingzhiyuan.
 * Date : 16/6/13.
 * Time : 14:34.
 * Description:
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class DomainData<T>(
        var Message: String?,
        var Result: Boolean = false,
        var Data: List<T>?,
        var OtherData: Any?,
        var totalCount: Int?,
        var success: Boolean = false
                        )