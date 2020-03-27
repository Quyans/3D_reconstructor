package com.hjq.demo.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.demo.R;
import com.hjq.demo.common.MyAdapter;

import butterknife.BindView;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 状态数据列表
 */
public final class StatusAdapter extends MyAdapter<String> {

    public StatusAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    final class ViewHolder extends MyAdapter.ViewHolder {

        @BindView(R.id.tv_status_text)
        TextView mTextView;

        ViewHolder() {
            super(R.layout.item_status);
        }

        @Override
        public void onBindView(int position) {
            mTextView.setText(getItem(position));
        }
    }
}