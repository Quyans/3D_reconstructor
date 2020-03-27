package com.hjq.demo.ui.fragment;

import com.hjq.demo.R;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.ui.activity.CopyActivity;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 可进行拷贝的副本
 */
public final class CopyFragment extends MyFragment<CopyActivity> {

    public static CopyFragment newInstance() {
        return new CopyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_copy;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}