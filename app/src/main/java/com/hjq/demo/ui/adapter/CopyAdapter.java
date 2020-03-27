package com.hjq.demo.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.ViewGroup;

import com.hjq.demo.R;
import com.hjq.demo.common.MyAdapter;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 可进行拷贝的副本
 */
public final class CopyAdapter extends MyAdapter<String> {

    public CopyAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    final class ViewHolder extends MyAdapter.ViewHolder {

        ViewHolder() {
            super(R.layout.item_copy);
        }

        @Override
        public void onBindView(int position) {

        }
    }
}