package com.hjq.demo.ui.activity;

import android.app.Activity;
import java.util.LinkedList;

import android.os.Bundle;

import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;


public class AddComActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcom);

        initCtrl();
    }

    

    /**
     * 初始化控件
     */
    private void initCtrl()
    {
        llContentView = (LinearLayout) this.findViewById(R.id.content_view);
        etContent1 = (EditText) this.findViewById(R.id.et_content1);
        listIBTNAdd = new LinkedList<ImageButton>();
        listIBTNDel = new LinkedList<ImageButton>();

        // “+”按钮（第一个）
        ImageButton ibtnAdd1 = (ImageButton) this.findViewById(R.id.ibn_add1);
        ibtnAdd1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 获取尺寸变化比例
                iETContentHeight = etContent1.getHeight();
                fDimRatio = iETContentHeight / 80;

                addContent(v);
            }
        });

        listIBTNAdd.add(ibtnAdd1);
        listIBTNDel.add(null);  // 第一组隐藏了“-”按钮，所以为null
    }

    /**
     * 添加一组新控件
     * @param v 事件触发控件，其实就是触发添加事件对应的“+”按钮
     */
    private void addContent(View v) {
        if (v == null) {
            return;
        }

        // 判断第几个“+”按钮触发了事件
        int iIndex = -1;
        for (int i = 0; i < listIBTNAdd.size(); i++) {
            if (listIBTNAdd.get(i) == v) {
                iIndex = i;
                break;
            }
        }

        if (iIndex >= 0) {
            // 控件实际添加位置为当前触发位置点下一位
            iIndex += 1;

            // 开始添加控件

            // 1.创建外围LinearLayout控件
            LinearLayout layout = new LinearLayout(AddComActivity.this);
            LinearLayout.LayoutParams lLayoutlayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 设置margin
            lLayoutlayoutParams.setMargins(0, (int) (fDimRatio * 5), 0, 0);
            layout.setLayoutParams(lLayoutlayoutParams);
            // 设置属性
            layout.setBackgroundColor(Color.argb(255, 162, 205, 90));   // #FFA2CD5A
            layout.setPadding((int) (fDimRatio * 5), (int) (fDimRatio * 5),
                    (int) (fDimRatio * 5), (int) (fDimRatio * 5));
            layout.setOrientation(LinearLayout.VERTICAL);

            // 2.创建内部EditText控件
            EditText etContent = new EditText(AddComActivity.this);
            LinearLayout.LayoutParams etParam = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, iETContentHeight);
            etContent.setLayoutParams(etParam);
            // 设置属性
            etContent.setBackgroundColor(Color.argb(255, 255, 255, 255));   // #FFFFFFFF
            etContent.setGravity(Gravity.LEFT);
            etContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            etContent.setPadding((int) (fDimRatio * 5), 0, 0, 0);
            etContent.setTextSize(16);
            // 将EditText放到LinearLayout里
            layout.addView(etContent);

            // 3.创建“+”和“-”按钮外围控件RelativeLayout
            RelativeLayout rlBtn = new RelativeLayout(AddComActivity.this);
            RelativeLayout.LayoutParams rlParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
//          rlParam.setMargins(0, (int) (fDimRatio * 5), 0, 0);
            rlBtn.setPadding(0, (int) (fDimRatio * 5), 0, 0);
            rlBtn.setLayoutParams(rlParam);

            // 4.创建“+”按钮
            ImageButton btnAdd = new ImageButton(AddComActivity.this);
            RelativeLayout.LayoutParams btnAddParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 靠右放置
            btnAddParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            btnAdd.setLayoutParams(btnAddParam);
            // 设置属性
            btnAdd.setBackgroundResource(R.drawable.bg_build_image);
            btnAdd.setId(btnIDIndex);
            // 设置点击操作
            btnAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    addContent(v);
                }
            });
            // 将“+”按钮放到RelativeLayout里
            rlBtn.addView(btnAdd);
            listIBTNAdd.add(iIndex, btnAdd);

            // 5.创建“-”按钮
            ImageButton btnDelete = new ImageButton(AddComActivity.this);
            btnDelete.setBackgroundResource(R.drawable.bg_build_image);
            RelativeLayout.LayoutParams btnDeleteAddParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            btnDeleteAddParam.setMargins(0, 0, (int) (fDimRatio * 5), 0);
            // “-”按钮放在“+”按钮左侧
            btnDeleteAddParam.addRule(RelativeLayout.LEFT_OF, btnIDIndex);
            btnDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    deleteContent(v);
                }
            });
            // 将“-”按钮放到RelativeLayout里
            rlBtn.addView(btnDelete, btnDeleteAddParam);
            listIBTNDel.add(iIndex, btnDelete);

            // 6.将RelativeLayout放到LinearLayout里
            layout.addView(rlBtn);

            // 7.将layout同它内部的所有控件加到最外围的llContentView容器里
            llContentView.addView(layout, iIndex);

            btnIDIndex++;
        }
    }

    /**
     * 删除一组控件
     * @param v 事件触发控件，其实就是触发删除事件对应的“-”按钮
     */
    private void deleteContent(View v) {
        if (v == null) {
            return;
        }

        // 判断第几个“-”按钮触发了事件
        int iIndex = -1;
        for (int i = 0; i < listIBTNDel.size(); i++) {
            if (listIBTNDel.get(i) == v) {
                iIndex = i;
                break;
            }
        }
        if (iIndex >= 0) {
            listIBTNAdd.remove(iIndex);
            listIBTNDel.remove(iIndex);

            // 从外围llContentView容器里删除第iIndex控件
            llContentView.removeViewAt(iIndex);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }  */

}

