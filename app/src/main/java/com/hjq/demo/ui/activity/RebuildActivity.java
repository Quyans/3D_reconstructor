package com.hjq.demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.http.glide.GlideApp;
import com.hjq.demo.ui.adapter.GridViewAdapter;

import java.util.List;

import butterknife.BindView;

public class RebuildActivity extends MyActivity {


    private Button mBtnSelectImg;
    ImageView mImageView;
    @BindView(R.id.gv_build)
    GridView mGridView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_rebuild;
    }

    @Override
    protected void initView() {
//        GridViewAdapter gridViewAdapter = new GridViewAdapter(10,);
//        mGridView.setAdapter();

        mImageView = findViewById(R.id.iv_build_img);
        mBtnSelectImg = findViewById(R.id.btn_rebuild_selectImg);
        mBtnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoActivity.start(getActivity(),2, new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelected(List<String> data) {
                        mImageView.setVisibility(View.VISIBLE);

                        for (int i = 0;i<data.size();i++){
                            GlideApp.with(getActivity())
                                    .load(data.get(i))
                                    .into(mImageView);
                        }

                    }

                    @Override
                    public void onCancel() {
                        toast("取消了");
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {

    }
}
