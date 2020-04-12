package com.hjq.demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.http.OkHttp.RequestManager;
import com.hjq.demo.http.glide.GlideApp;
import com.hjq.demo.ui.adapter.GridViewAdapter;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class RebuildActivity extends MyActivity {


    private Button mBtnSelectImg;
    ImageView mImageView;
    @BindView(R.id.gv_build)
    GridView mGridView;
    @BindView(R.id.fl_build)
    FrameLayout mFrameLayout;
    @BindView(R.id.btn_rebuild_upload)
    Button mBtnUpload;

    private List<String> data_all;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rebuild;
    }

    @Override
    protected void initView() {

//        mImageView = findViewById(R.id.iv_build_img);
        mBtnSelectImg = findViewById(R.id.btn_rebuild_selectImg);
        mBtnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoActivity.start(getActivity(),9, new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelected(List<String> data) {
                        //设置imageView
//                        mImageView.setVisibility(View.VISIBLE);
//                        GlideApp.with(getActivity())
//                                .load(data.get(0))
//                                .into(mImageView);

                        //设置展示frame的padding
                        mFrameLayout.setPadding(30,30,30,30);
                        //设置上传按钮
                        mBtnUpload.setEnabled(true);
                        //在这里动态加载item
                        GridViewAdapter gridViewAdapter = new GridViewAdapter(RebuildActivity.this,data);
                        data_all = data;
                        mGridView.setAdapter(gridViewAdapter);
                    }

                    @Override
                    public void onCancel() {
                        toast("取消了");
                    }
                });
            }
        });
        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actionUrl = "batch/upload";
                String TAG = "Upload";
                RequestManager requestManager = new RequestManager(RebuildActivity.this);
                HashMap hashMap = new HashMap();
                File file;
                for (String url : data_all){
                    file = new File(url);
                    System.out.println(file.getName());
                    hashMap.put(file.getName(),file);
                }

                requestManager.upLoadFile(actionUrl,hashMap,new RequestManager.ReqCallBack(){
                    @Override
                    public void onReqSuccess(Object result) {
                        Log.i(TAG, "success!!!!");
                        Log.i(TAG, result.toString());
                    }

                    @Override
                    public void onReqFailed(String errorMsg) {
                        Log.i(TAG, "wrong!!!!!!");
                        Log.i(TAG, errorMsg);
                    }
                } );



//                Log.i("上传文件1",data_all.get(0));

            }
        });
    }

    @Override
    protected void initData() {

    }
}
