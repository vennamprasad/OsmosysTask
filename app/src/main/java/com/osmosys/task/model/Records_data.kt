import com.google.gson.annotations.SerializedName

public class Records_data() {
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("name")
    val name: String = ""
    @SerializedName("createdOn")
    val createdOn: String = ""
    @SerializedName("statusID")
    val statusID: Int = 0
    @SerializedName("isSubPackage")
    val isSubPackage: Int = 0
    @SerializedName("amenityType")
    val amenityType: AmenityType? = null
    @SerializedName("packageFeatures")
    val packageFeatures: ArrayList<PackageFeatures> = ArrayList()
}