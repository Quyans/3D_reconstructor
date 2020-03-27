package com.hjq.demo.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.hjq.base.BaseDialog;
import com.hjq.base.action.AnimAction;
import com.hjq.demo.R;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 等待加载对话框
 */
public final class WaitDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        private final TextView mMessageView;

        public Builder(Context context) {
            super(context);
            setContentView(R.layout.dialog_wait);
            setAnimStyle(AnimAction.TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);

            mMessageView = findViewById(R.id.tv_wait_message);
        }

        public Builder setMessage(@StringRes int id) {
            return setMessage(getString(id));
        }
        public Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            mMessageView.setVisibility(text == null ? View.GONE : View.VISIBLE);
            return this;
        }
    }
}