package com.hjq.demo.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.w3c.dom.ls.LSException;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private int count = 0;
    private List data = null;
    GridViewAdapter(int count, List data){
        this.count = count;
        this.data = data;
    }
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
