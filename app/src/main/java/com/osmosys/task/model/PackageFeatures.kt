import com.google.gson.annotations.SerializedName

class PackageFeatures() {
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("packageID")
    val packageID: Int = 0
    @SerializedName("featureID")
    val featureID: Int = 0
    @SerializedName("createdBy")
    val createdBy: Int = 0
    @SerializedName("createdOn")
    val createdOn: String = ""
    @SerializedName("status")
    val status: Int = 0
    @SerializedName("feature")
    val feature: Feature? = null
}