package com.hjq.demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjq.demo.R;
import com.hjq.demo.http.glide.GlideApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSException;

import java.util.List;
import java.util.zip.Inflater;

public class ModelListInfoAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int count = 0;
    //
    private JSONArray data = null;

    public ModelListInfoAdapter(Context context, JSONArray data){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.data = data;

        this.count = data.length();
        System.out.println(this.count);
    }

    static class ViewHolder{
        public ImageView mImageView;
        public TextView nameTextView;
        public TextView timeTextView;
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
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.item_model_brief_info,null);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = convertView.findViewById(R.id.iv_modelBriefInfo);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_modelBriefInfoName);
            viewHolder.timeTextView = convertView.findViewById(R.id.tv_modelBriefInfoTime);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        Glide.with(mContext).load("https://www.baidu.com/img/bd_logo1.png").into(viewHolder.mImageView);
//        Glide.with(mContext)

        try {
            viewHolder.nameTextView.setText(data.getJSONObject(position).getString("name"));
            viewHolder.timeTextView.setText(data.getJSONObject(position).getString("buildTime"));
            GlideApp.with(mContext)
                    .load(data.getJSONObject(position).getString("profile"))
                    .into(viewHolder.mImageView);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
