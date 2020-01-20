import com.google.gson.annotations.SerializedName
import com.osmosys.task.model.Attachment
import com.osmosys.task.model.ChildPackage

class Records() {
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
    @SerializedName("attachment")
    val attachment: ArrayList<Attachment> = ArrayList()
    @SerializedName("amenityType")
    val amenityType: AmenityType? = null
    @SerializedName("packageFeatures")
    val packageFeatures: ArrayList<PackageFeatures> = ArrayList()
    @SerializedName("childPackages")
    val childPackages: ArrayList<ChildPackage> = ArrayList()
}