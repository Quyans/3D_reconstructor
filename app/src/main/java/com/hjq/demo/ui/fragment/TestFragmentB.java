package com.hjq.demo.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.hjq.demo.R;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.http.glide.GlideApp;
import com.hjq.demo.ui.activity.AllModelList;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.widget.view.CountdownView;
import com.hjq.widget.view.SwitchButton;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 项目自定义控件展示
 */
public final class TestFragmentB extends MyFragment<HomeActivity>
        implements SwitchButton.OnCheckedChangeListener {


    public static TestFragmentB newInstance() {
        return new TestFragmentB();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_b;
    }

    @Override
    protected void initView() {

//
//        setOnClickListener(R.id.cv_test_countdown);
        setOnClickListener(R.id.btn_goModelList);
    }





    @Override
    protected void initData() {
//        GlideApp.with(this)
//                .load(R.drawable.bg_launcher)
//                .circleCrop()

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_goModelList) {
            Intent intent = new Intent(getContext(), AllModelList.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    /**
     * {@link SwitchButton.OnCheckedChangeListener}
     */

    @Override
    public void onCheckedChanged(SwitchButton button, boolean isChecked) {
        toast(isChecked);
    }
}