package com.osmosys.task.model


import com.google.gson.annotations.SerializedName

class ChildPackage(){
    @SerializedName("createdOn")
    val createdOn: String = ""
    @SerializedName("package")
    val packages: Package? = null
}