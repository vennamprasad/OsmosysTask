package com.osmosys.task.view

import PackageFeatures
import Records_data
import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.osmosys.task.R
import kotlinx.android.synthetic.main.list_group.view.*
import java.util.*

class ExpandableListAdapter(
    private val _context: Context,
    private val _listDataHeader: List<Records_data>,
    private val _listDataChild: HashMap<String, List<PackageFeatures>>
) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosititon: Int): String? {
        return _listDataChild.get(_listDataHeader.get(groupPosition).name)?.get(childPosititon)
            ?.feature?.featureName
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        var result: Long = 0
        try {
            result = childPosition.toLong()
        } catch (e: Exception) {
            Log.e("getChildId:-", e.toString())
        }
        return result
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {
        var convertView1: View? = convertView
        try {
            val childText: String = getChild(groupPosition, childPosition) as String
            if (convertView1 == null) {
                val infalInflater: LayoutInflater = this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView1 = infalInflater.inflate(R.layout.list_item, null);
            }

            val txtListChild: TextView? = convertView1?.findViewById(R.id.child_title)

            txtListChild?.setText(childText);
        } catch (e: Exception) {
            Log.e("getChildView:-", e.toString())
        }
        return convertView1!!
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return _listDataChild.get(_listDataHeader.get(groupPosition).name)?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): String? {
        return _listDataHeader.get(groupPosition).name
    }

    override fun getGroupCount(): Int {
        return _listDataHeader.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {
        val headerTitle: String
        var convertView1 = convertView
        try {
            headerTitle = getGroup(groupPosition) as String
            if (convertView1 == null) {
                val infalInflater: LayoutInflater = this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView1 = infalInflater.inflate(R.layout.list_group, null)
            }
            convertView1?.grp_title?.setTypeface(null, Typeface.BOLD)
            convertView1?.grp_title?.text = headerTitle

            if (isExpanded) {
                convertView1?.img!!.setImageResource(R.drawable.down_button_64px);
            } else {
                convertView1?.img!!.setImageResource(R.drawable.next_page_64px);
            }
        } catch (e: Exception) {
            Log.e("ELA", e.toString())
        }
        return convertView1!!
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}