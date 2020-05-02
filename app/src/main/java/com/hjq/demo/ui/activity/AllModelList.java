package com.hjq.demo.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.BaseAdapter;
import com.hjq.demo.R;
import com.hjq.demo.action.StatusAction;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.http.OkHttp.RequestManager;
import com.hjq.demo.other.IntentKey;
import com.hjq.demo.ui.adapter.ModelListInfoAdapter;
import com.hjq.demo.ui.adapter.StatusAdapter;
import com.hjq.demo.ui.dialog.MenuDialog;
import com.hjq.demo.widget.HintLayout;
import com.hjq.widget.layout.WrapRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class AllModelList  extends MyActivity
        implements StatusAction,
        OnRefreshLoadMoreListener,
        BaseAdapter.OnItemClickListener{

    @BindView(R.id.hl_status_hint)
    HintLayout mHintLayout;
    @BindView(R.id.rl_status_refresh)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_status_list)
    WrapRecyclerView mRecyclerView;
    @BindView(R.id.gv_modelList)
    GridView gridView;

    private ModelListInfoAdapter mAdapter;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_model_list;
    }

    @Override
    protected void initView() {

        mRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        phone = "18560125097";
        LoadModel(phone);
    }

    @Override
    protected void initData() {


    }

    @Override
    public HintLayout getHintLayout() {
        return mHintLayout;
    }


    /**
     * {@link BaseAdapter.OnItemClickListener}
     *
     * @param recyclerView      RecyclerView对象
     * @param itemView          被点击的条目对象
     * @param position          被点击的条目位置
     */
    @Override
    public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
        toast(mAdapter.getItem(position));
    }

    /**
     * {@link OnRefreshLoadMoreListener}
     */

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        postDelayed(() -> {
//            mAdapter.clearData();
//            mAdapter.setData(analogData());
            LoadModel(phone);
            mRefreshLayout.finishRefresh();
            toast("刷新完成");
        }, 1000);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        postDelayed(() -> {
//            mAdapter.addData(analogData());

            mRefreshLayout.finishLoadMore();
            toast("加载完成");
        }, 1000);
    }

    private void LoadModel(String phone){
        RequestManager requestManager = new RequestManager(AllModelList.this);

        HashMap hashMap = new HashMap();
        hashMap.put("phone",phone);
        requestManager.requestAsyn(IntentKey.AllModelInfo_api, 0, hashMap, new RequestManager.ReqCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) throws IOException, JSONException {
                Log.d("allInfo",result.toString());
                JSONObject jsonObject = new JSONObject(result.toString());
                List list = new ArrayList<JSONObject>();
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                System.out.println(jsonArray);
                mAdapter = new ModelListInfoAdapter(getActivity(),jsonArray);
                gridView.setAdapter(mAdapter);
            }

            @Override
            public void onReqFailed(String errorMsg) {
                Log.d("error", errorMsg);
            }
        });
    }
}
