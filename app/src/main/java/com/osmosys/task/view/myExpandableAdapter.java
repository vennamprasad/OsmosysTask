package com.osmosys.task.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.osmosys.task.R;

import java.util.HashMap;
import java.util.List;

public class myExpandableAdapter extends BaseExpandableListAdapter {
    private int lastExpandedGroupPosition = -1;
    private ExpandableListView expandableList;
    private Context context;
    private List<Data> listDataHeader;
    private HashMap<Data, List<Data>> listDataChild;

    public myExpandableAdapter(Context context, HashMap<Data, List<Data>> listDataChild, List<Data> listDataHeader, ExpandableListView expandableList) {

        this.context = context;
        this.listDataChild = listDataChild;
        this.expandableList = expandableList;
        this.listDataHeader = listDataHeader;
    }


    @Override

    public void onGroupExpanded(int groupPosition) {

        if (groupPosition != lastExpandedGroupPosition) {
            expandableList.collapseGroup(lastExpandedGroupPosition);
        }
        super.onGroupExpanded(groupPosition);
        lastExpandedGroupPosition = groupPosition;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return listDataChild.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View row = convertView;

        CustomHolder holder = null;


        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_group, parent, false);

            holder = new CustomHolder();

            holder.txtParentList = (TextView) row.findViewById(R.id.grp_title);

            row.setTag(holder);
        } else {
            holder = (CustomHolder) row.getTag();
        }

        List<Records> d = listDataHeader.get(groupPosition).getmRecords();
        holder.txtParentList.setText(d.get(groupPosition).getmName());

        return row;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View row = convertView;

        CustomCHolder holderc;

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item, parent, false);

            holderc = new CustomCHolder();
            holderc.txtChildList = (TextView) row.findViewById(R.id.child_title);
            String s = (String) getChild(groupPosition, childPosition);
            holderc.txtChildList.setText(s);
            row.setTag(holderc);
        } else {
            holderc = (CustomCHolder) row.getTag();
        }

        return row;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class CustomHolder {
        TextView txtParentList;

    }

    static class CustomCHolder {
        TextView txtChildList;

    }
}