package com.hjq.demo.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.demo.R;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.helper.InputTextHelper;
import com.hjq.demo.http.OkHttp.RequestManager;
import com.hjq.demo.http.model.HttpData;
import com.hjq.demo.http.request.GetCodeApi;
import com.hjq.demo.http.request.RegisterApi;
import com.hjq.demo.http.response.RegisterBean;
import com.hjq.demo.other.IntentKey;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.widget.view.CountdownView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 注册界面
 */
public final class RegisterActivity extends MyActivity {

    @BindView(R.id.tv_register_title)
    TextView mTitleView;

    @BindView(R.id.et_register_phone)
    EditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_register_code)
    EditText mCodeView;

    @BindView(R.id.et_register_nickname)
    EditText mNicknameView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_register_password2)
    EditText mPasswordView2;

    @BindView(R.id.btn_register_commit)
    Button mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        // 给这个 View 设置沉浸式，避免状态栏遮挡
        ImmersionBar.setTitleBar(this, mTitleView);

        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mCodeView)
                .addView(mNicknameView)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length() == 11 &&
                        mPasswordView1.getText().toString().length() >= 6 &&
                        mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString()))
                .build();

        setOnClickListener(R.id.cv_register_countdown, R.id.btn_register_commit);
    }

    @Override
    protected void initData() {

    }

    @Override
    public ImmersionBar createStatusBarConfig() {
        // 不要把整个布局顶上去
        return super.createStatusBarConfig().keyboardEnable(true);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_register_countdown:
                // 获取验证码
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }

                if (true) {
                    toast(R.string.common_code_send_hint);
                    mCountdownView.start();
                    return;
                }

                // 获取验证码

                EasyHttp.post(this)
                        .api(new GetCodeApi()
                        .setPhone(mPhoneView.getText().toString()))
                        .request(new HttpCallback<HttpData<Void>>(this) {

                            @Override
                            public void onSucceed(HttpData<Void> data) {
                                toast(R.string.common_code_send_hint);
                                mCountdownView.start();
                            }

                            @Override
                            public void onFail(Exception e) {
                                super.onFail(e);
                                mCountdownView.start();
                            }
                        });
                break;
            case R.id.btn_register_commit:
                //在这里确定是否注册成功
//                if (true) {
//                    LoginActivity.start(getActivity(), mPhoneView.getText().toString(), mPasswordView1.getText().toString());
//                    setResult(RESULT_OK);
//                    finish();
//                    return;
//                }
                // 提交注册
                /*
                EasyHttp.post(this)
                        .api(new RegisterApi()
                        .setPhone(mPhoneView.getText().toString())
                        .setCode(mCodeView.getText().toString())
                        .setPassword(mPasswordView1.getText().toString()))
                        .request(new HttpCallback<HttpData<RegisterBean>>(this) {

                            @Override
                            public void onSucceed(HttpData<RegisterBean> data) {
                                LoginActivity.start(getActivity(), mPhoneView.getText().toString(), mPasswordView1.getText().toString());
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
                 */
                HashMap hashMap = new HashMap();
                hashMap.put("phone", mPhoneView.getText().toString());
                hashMap.put("nickname",mNicknameView.getText().toString());
                hashMap.put("password",mPasswordView1.getText().toString());
                RequestManager requestManager = new RequestManager(RegisterActivity.this);
                requestManager.requestAsyn(IntentKey.Register_api, 1, hashMap, new RequestManager.ReqCallBack<Object>() {
                    @Override
                    public void onReqSuccess(Object result) throws IOException, JSONException {
                        JSONObject jsonObject = new JSONObject(result.toString());

                        int status = jsonObject.getInt("status");
                        Log.d("Resiter_test", String.valueOf(jsonObject.getInt("status")));

                        if (status==200){
                            System.out.println("传过来的状态吗是数字");

                            Log.d("Register",result.toString());
                            toast("注册成功");
                            //直接跳转到登录界面
                            LoginActivity.start(getActivity(), mPhoneView.getText().toString(), mPasswordView1.getText().toString());
                            setResult(RESULT_OK);
                            finish();

                        }


                    }

                    @Override
                    public void onReqFailed(String errorMsg) {
                        Log.d("Register",errorMsg);
                    }
                });

                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}