package com.bns.mobile.domain.model

//data class ParamList (
//    var responseCode : String? = null,
//    var list : List<Param>? = listOf(),
//        )

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ParamList {
        @SerializedName("list")
        @Expose
        var list: List<Param>? = listOf()
}