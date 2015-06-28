package com.huscii.fornow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ian on 6/28/2015.
 */
public class EventExpandableListView extends BaseExpandableListAdapter{

    private Activity activity;
    private ArrayList<Object> childtems;
    private LayoutInflater inflater;
    private ArrayList<String> parentItems, child;

    public EventExpandableListView(ArrayList<String> parents, ArrayList<Object> children) {
        this.parentItems = parents;
        this.childtems = children;
    }

    public void setInflater(LayoutInflater inflater, Activity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }


    @Override
    public int getGroupCount() {
        return parentItems.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) childtems.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.event_body, null);
        }

        ((TextView) convertView).setText(parentItems.get(groupPosition));
        return convertView;


    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        child = (ArrayList<String>) childtems.get(groupPosition);
        TextView textView = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.event_head, null);
        }
        textView = (TextView) convertView.findViewById(R.id.eventTitle);
        textView.setText(child.get(childPosition));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, child.get(childPosition),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}