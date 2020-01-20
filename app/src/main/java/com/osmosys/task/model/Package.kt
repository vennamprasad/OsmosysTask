package com.osmosys.task.model


import com.google.gson.annotations.SerializedName

class Package(){
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("isSubPackage")
    val isSubPackage: Int = 0
    @SerializedName("name")
    val name: String = ""
}