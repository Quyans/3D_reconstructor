package com.hjq.demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
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

import com.hjq.demo.other.IntentKey;
import com.hjq.demo.ui.adapter.GridViewAdapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

public class RebuildActivity extends MyActivity {


    private Button mBtnSelectImg;
    ImageView mImageView;
    @BindView(R.id.gv_build)
    GridView mGridView;
    @BindView(R.id.fl_build)
    FrameLayout mFrameLayout;
    @BindView(R.id.btn_rebuild_upload)
    Button mBtnUpload;
    @BindView(R.id.btn_download)
    Button mBtnDownload;

    private List<String> data_all;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rebuild;
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {

//        mImageView = findViewById(R.id.iv_build_img);
        mBtnSelectImg = findViewById(R.id.btn_rebuild_selectImg);
        mBtnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoActivity.start(getActivity(),20, new PhotoActivity.OnPhotoSelectListener() {

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
                String actionUrl = IntentKey.baseAPI +"/model/create";
//                String actionUrl = "/batch/upload";
                String TAG = "Upload";
                RequestManager requestManager = new RequestManager(RebuildActivity.this);
                HashMap hashMap = new HashMap();
                File file;
                for (String url : data_all){
                    file = new File(url);
                    System.out.println(file.getName());
                    hashMap.put(file.getName(),file);
                }

                //这里之后添加了textview再动态修改
                hashMap.put("name","第一个模型");

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
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG = "download";



                RequestManager requestManager = new RequestManager(RebuildActivity.this);
                requestManager.downLoadFile("uploadFiles/100_7102.JPG"
                        , "/sdcard/DCIM/Download_test/"
                        , new RequestManager.ReqCallBack<Object>() {
                            @Override
                            public void onReqSuccess(Object result) throws IOException {
                                System.out.println("成功");
                            }

                            @Override
                            public void onReqFailed(String errorMsg) {
                                Log.i("下载",errorMsg.toString());
                            }
                        }
                );


//                final String url = "http://192.168.1.10:8080/uploadFiles/100_7102.JPG";
//                final long startTime = System.currentTimeMillis();
//                Log.i("DOWNLOAD", "startTime=" + startTime);
//
//                Request request = new Request.Builder().url(url).build();
//                new OkHttpClient().newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        // 下载失败
//                        e.printStackTrace();
//                        Log.i("DOWNLOAD", "download failed");
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Sink sink = null;
//                        BufferedSink bufferedSink = null;
//                        try {
//                            String mSDCardPath= Environment.getExternalStorageDirectory().getAbsolutePath();
////                            File dest = new File(mSDCardPath,   url.substring(url.lastIndexOf("/") + 1));
//                            File dest = new File("/sdcard/DCIM/",url.substring(url.lastIndexOf("/") + 1));
//                            sink = Okio.sink(dest);
//                            bufferedSink = Okio.buffer(sink);
//                            bufferedSink.writeAll(response.body().source());
//
//                            bufferedSink.close();
//                            Log.i("DOWNLOAD", "download success");
//                            Log.i("DOWNLOAD", "totalTime=" + (System.currentTimeMillis() - startTime));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Log.i("DOWNLOAD", "download failed");
//                        } finally {
//                            if (bufferedSink != null) {
//                                bufferedSink.close();
//                            }
//
//                        }
//
//
//                    }
//                });
            };

    });


    }
}