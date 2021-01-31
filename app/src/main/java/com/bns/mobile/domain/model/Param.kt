package com.bns.mobile.domain.model

//data class Param (
//    var id : String? = null,
//    var name : String? = null,
//)


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Param {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}