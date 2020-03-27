package com.hjq.demo.ui.dialog;

import android.content.Context;
import android.view.Gravity;

import com.hjq.base.BaseDialog;
import com.hjq.base.action.AnimAction;
import com.hjq.demo.R;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 可进行拷贝的副本
 */
public final class CopyDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        public Builder(Context context) {
            super(context);

            setContentView(R.layout.dialog_copy);
            setAnimStyle(AnimAction.BOTTOM);
            setGravity(Gravity.BOTTOM);
        }
    }
}