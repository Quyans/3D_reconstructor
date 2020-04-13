package com.hjq.demo.ui.activity;

import android.app.Activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Function;

import android.os.Bundle;

import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.hjq.demo.action.ToastAction;
import com.hjq.demo.http.OkHttp.RequestManager;

import org.json.JSONException;
import org.json.JSONObject;


public class AddComActivity extends MyActivity {
    // 外围的LinearLayout容器
    private LinearLayout llContentView;

    private EditText etContent1;

    // “+”按钮控件List
    private LinkedList<ImageButton> listIBTNAdd;
    // “+”按钮ID索引
    private int btnIDIndex = 1000;
    // “-”按钮控件List
    private LinkedList<ImageButton> listIBTNDel;

    private int iETContentHeight = 0;   // EditText控件高度
    private float fDimRatio = 1.0f; // 尺寸比例（实际尺寸/xml文件里尺寸）

    //http请求
    private String url;
    private OkHttpClient okHttpClient;

    /**
     * Http请求测试
     */
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String TAG = "OkHttp3";
    private Button mBtn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcom);

        mBtn_submit = findViewById(R.id.btn_submit);
        mBtn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 原生的okHTTP
                 */
//                toast("我是吐司");
////                String json0 = bowlingJson("Jesse", "Jake");
//
//
//
//                JSONObject json = new JSONObject();
//                try {
//                    json.put("userid", "test1");
//                    json.put("username", "name1");
//                    json.put("usersex", 3);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                //1. 创建OkhttpClient 对象
//                OkHttpClient client = new OkHttpClient();
//                RequestBody body = RequestBody.create(JSON, String.valueOf(json));
////                Log.i("Json文件",String.valueOf(body));
//                //2. 创建请求的Request 对象
//                Request request = new Request.Builder()
//                        .url(url)
//                        .get()
//                        .build();
//
//                //3. 在Okhttp中创建Call 对象，将request和Client进行绑定
//                Call call = client.newCall(request);
//                //4. 执行Call对象（call 是interface 实际执行的是RealCall）中的 `enqueue`方法
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        toast("失败");
////                        Toast.makeText(AddComActivity.this,"failure",Toast.LENGTH_LONG);
//                        Log.i(TAG, e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
////                        toast(response.body().string());
////                        Toast.makeText(AddComActivity.this,"success",Toast.LENGTH_LONG);
//                        Log.i(TAG, response.body().string());
//                    }
//                });

                /**
                 * 测试OkHttp工具类_post
                 */
//                url =  "OkHttp/";
//                RequestManager requestManager = new RequestManager(AddComActivity.this);
//
//                HashMap hashMap = new HashMap();
//                hashMap.put("name","name1");
//                hashMap.put("sex","1");
//                hashMap.put("id","id2");
//                requestManager.requestAsyn(url,1,hashMap,new RequestManager.ReqCallBack(){
//                    @Override
//                    public void onReqSuccess(Object result) {
//                        Log.i(TAG, result.toString());
//                    }
//
//                    @Override
//                    public void onReqFailed(String errorMsg) {
//                        Log.i(TAG, errorMsg.toString());
//                    }
//                } );


                /**
                 * 测试get并加参数
                 */
//                url =  "testGet/";
//                RequestManager requestManager = new RequestManager(AddComActivity.this);
//
//                HashMap hashMap = new HashMap();
//                hashMap.put("name","name1");
//                hashMap.put("sex","1");
//                hashMap.put("id","id2");
//                requestManager.requestAsyn(url,0,hashMap,new RequestManager.ReqCallBack(){
//                    @Override
//                    public void onReqSuccess(Object result) {
//                        Log.i(TAG, "success!!!!");
//                        Log.i(TAG, result.toString());
//                    }
//
//                    @Override
//                    public void onReqFailed(String errorMsg) {
//                        Log.i(TAG, "wrong!!!!!!");
//                        Log.i(TAG, errorMsg);
//                    }
//                } );

                url =  "user/findUserAll/";
                RequestManager requestManager = new RequestManager(AddComActivity.this);
                requestManager.requestAsyn(url,0,new HashMap(),new RequestManager.ReqCallBack(){
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



            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addcom;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    String bowlingJson(String player1, String player2) {



        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }
}




