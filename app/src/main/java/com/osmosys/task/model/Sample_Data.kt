import com.google.gson.annotations.SerializedName

class Sample_Data() {
    @SerializedName("limit")
    val limit: Int = 0
    @SerializedName("start")
    val start: Int = 0
    @SerializedName("nextStart")
    val nextStart: Int = 0
    @SerializedName("records")
    val records: ArrayList<Records> = ArrayList()
    @SerializedName("total")
    val total: Int = 0
}