package com.hjq.demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hjq.demo.R;
import com.hjq.demo.http.glide.GlideApp;

import org.w3c.dom.ls.LSException;

import java.util.List;
import java.util.zip.Inflater;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int count = 0;
    private List data = null;

    public GridViewAdapter(Context context,List data){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.count = data.size();
    }

    static class ViewHolder{
        public ImageView mImageView;
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
            convertView = mLayoutInflater.inflate(R.layout.layout_gv_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = convertView.findViewById(R.id.iv_item);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        Glide.with(mContext).load("https://www.baidu.com/img/bd_logo1.png").into(viewHolder.mImageView);
//        Glide.with(mContext)
        GlideApp.with(mContext)
                .load(data.get(position))
                .into(viewHolder.mImageView);
        return convertView;
    }


//    if (convertView == null) {
//        convertView = LayoutInflater.from(mContext).inflate(
//                R.layout.grid_item, parent, false);
//    }
//    TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
//    final ToggleButton iv = BaseViewHolder.get(convertView, R.id.iv_item);
//iv.setBackgroundResource(imgs[position]);
//tv.setText(img_text[position]);
//// iv.setOnClickListener(new OnClickListener() {
////
//// @Override
//// public void onClick(View v) {
//// // TODO Auto-generated method stub
//// iv.setBackgroundResource(imgs[position]);
//// }
//// });
//return convertView;

}
