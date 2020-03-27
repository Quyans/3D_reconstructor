package com.hjq.demo.helper;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 软键盘工具类
 */
public final class KeyboardUtils {

    /**
     * 显示软键盘
     *
     * @param view      依附的View
     */
    public static void showKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param view      依附的View
     */
    public static void hideKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 切换软键盘
     *
     * @param view      依附的View
     */
    public static void toggleSoftInput(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, 0);
        }
    }
}
