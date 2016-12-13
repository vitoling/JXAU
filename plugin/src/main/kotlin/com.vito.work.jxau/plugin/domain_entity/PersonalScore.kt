package com.vito.work.jxau.plugin.domain_entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by lingzhiyuan.
 * Date : 16/6/14.
 * Time : 10:25.
 * Description:
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class PersonalScore(
        var Xh: String?,
        var Xm: String?,
        var Kcmc: String?,
        var Kcdm: String?,
        var Jgbj: Int?,
        var Pscj: String?,
        var Kscj: String?,
        var Zpcj: String?,
        var Bkcj: String?,
        var Cxcj: String?,
        var Bz: String?,
        var Xs: Int?,
        var Xq: String?,
        var Kslb: String?,
        var Ksbh: String?,
        var Bjdm: String?,
        var Bjmc: String?,
        var Kclb: String?,
        var Kssj: String?,
        var Cbls: String?,
        var Cbsj: String?,
        var Cbip: String?,
        var Sjcj: Int?,
        var ID: Int?,
        var SetField: String?,
        var OrderBy: Any?,
        var limit: Int?,
        var start: Int?
                        )