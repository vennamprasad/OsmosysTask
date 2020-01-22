package com.osmosys.task.view

import Base_data
import PackageFeatures
import Records_data
import android.content.res.AssetManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.osmosys.task.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set


class MainActivity : AppCompatActivity() {
    var listAdapter: android.widget.ExpandableListAdapter? = null
    var listDataHeader: ArrayList<Records_data>? = null
    var listDataChild: HashMap<String, List<PackageFeatures>>? = null
    private var lastExpandedPosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
          /*  val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            val width = metrics.widthPixels
            exp_list.setIndicatorBounds(width - GetPixelFromDips(50F), width - GetPixelFromDips(10F));*/
            prepareListData()
            exp_list.setOnGroupClickListener { parent, v, groupPosition, id -> false }
            exp_list.setOnGroupExpandListener { groupPosition ->
                try {
                    if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition
                    ) {
                        exp_list.collapseGroup(lastExpandedPosition)
                    }
                    lastExpandedPosition = groupPosition
                    Toast.makeText(applicationContext, listDataHeader?.get(groupPosition)?.name + " Expanded", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {
                    Log.e("setOnGroupExpand", e.toString())
                }
            }
            exp_list.setOnGroupCollapseListener { groupPosition ->
                try {
                    Toast.makeText(applicationContext, (listDataHeader?.get(groupPosition))?.name + " Collapsed", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.e("setOnGroupCollapse", e.toString())
                }
            }
            exp_list.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(applicationContext, "${listDataHeader?.get(groupPosition)!!.name} : ${listDataChild?.get(listDataHeader?.get(groupPosition)?.name)?.get(childPosition)!!.feature!!.featureName}", Toast.LENGTH_SHORT).show()
                false
            }
        } catch (e: Exception) {
            Log.e("setOnChild-", e.toString())
        }
    }

    fun GetPixelFromDips(pixels: Float): Int { // Get the screen's density scale
        val scale = resources.displayMetrics.density
        // Convert the dps to pixels, based on density scale
        return (pixels * scale + 0.5f).toInt()
    }

    /*
     * Preparing the list data
     */
    private fun prepareListData() {
        try {
            listDataHeader = ArrayList()
            listDataChild = HashMap()
            //access asset
            val am: AssetManager = applicationContext.getAssets()
            val ins: InputStream = am.open("sample_data.json")
            val datastr: String = ins.bufferedReader().use { it.readText() }
            val dataobj: Base_data = Gson().fromJson(datastr, Base_data::class.java)
            // Adding child data
            listDataHeader = dataobj.data!!.records
            // Adding child data
            for (i in 0..dataobj.data.records.size - 1) {
                listDataChild!![listDataHeader!!.get(i).name] =
                    dataobj.data.records[i].packageFeatures
            }
            listAdapter = ExpandableListAdapter(this, listDataHeader!!, listDataChild!!)
            exp_list.setAdapter(listAdapter)
        } catch (e: Exception) {
            Log.e("prepareListData", e.toString())
        }
    }
}