import com.google.gson.annotations.SerializedName

class Feature() {
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("featureName")
    val featureName: String = ""
    @SerializedName("status")
    val status: Int = 0
    @SerializedName("isSmartFeature")
    val isSmartFeature: String = ""
    @SerializedName("deviceTypeID")
    val deviceTypeID: String = ""
}