package com.hjq.demo.ui.dialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.BaseAdapter;
import com.hjq.base.BaseDialog;
import com.hjq.demo.R;
import com.hjq.demo.common.MyAdapter;
import com.hjq.toast.ToastUtils;
import com.hjq.umeng.Platform;
import com.hjq.umeng.UmengClient;
import com.hjq.umeng.UmengShare;

import java.util.ArrayList;
import java.util.List;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 分享对话框
 */
public final class ShareDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder>
            implements BaseAdapter.OnItemClickListener {

        private final ShareAdapter mAdapter;

        private final UmengShare.ShareData mData;

        private UmengShare.OnShareListener mListener;

        public Builder(Context context) {
            super(context);

            setContentView(R.layout.dialog_share);

            final List<ShareBean> data = new ArrayList<>();
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_wechat), getString(R.string.share_platform_wechat), Platform.WECHAT));
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_moment), getString(R.string.share_platform_moment), Platform.CIRCLE));
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_qq), getString(R.string.share_platform_qq), Platform.QQ));
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_qzone), getString(R.string.share_platform_qzone), Platform.QZONE));
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_link), getString(R.string.share_platform_link), null));

            mAdapter = new ShareAdapter(context);
            mAdapter.setData(data);
            mAdapter.setOnItemClickListener(this);

            RecyclerView recyclerView = findViewById(R.id.rv_share_list);
            recyclerView.setLayoutManager(new GridLayoutManager(context, data.size()));
            recyclerView.setAdapter(mAdapter);

            mData = new UmengShare.ShareData(context);
        }

        public Builder setShareTitle(String title) {
            mData.setShareTitle(title);
            return this;
        }

        public Builder setShareDescription(String description) {
            mData.setShareDescription(description);
            return this;
        }

        public Builder setShareLogo(String url) {
            mData.setShareLogo(url);
            return this;
        }

        public Builder setShareLogo(@DrawableRes int id) {
            mData.setShareLogo(id);
            return this;
        }

        public Builder setShareUrl(String url) {
            mData.setShareUrl(url);
            return this;
        }

        public Builder setListener(UmengShare.OnShareListener listener) {
            mListener = listener;
            return this;
        }

        /**
         * {@link BaseAdapter.OnItemClickListener}
         */
        @Override
        public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
            Platform platform = mAdapter.getItem(position).getSharePlatform();
            if (platform != null) {
                UmengClient.share(getActivity(), platform, mData, mListener);
            } else {
                // 复制到剪贴板
                getSystemService(ClipboardManager.class).setPrimaryClip(ClipData.newPlainText("url", mData.getShareUrl()));
                ToastUtils.show(R.string.share_platform_copy_hint);
            }
            dismiss();
        }
    }

    private static class ShareAdapter extends MyAdapter<ShareBean> {

        private ShareAdapter(Context context) {
            super(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder();
        }

        final class ViewHolder extends MyAdapter.ViewHolder {

            private final ImageView mImageView;
            private final TextView mTextView;

            private ViewHolder() {
                super(R.layout.item_share);
                mImageView = (ImageView) findViewById(R.id.iv_share_image);
                mTextView = (TextView) findViewById(R.id.tv_share_text);
            }

            @Override
            public void onBindView(int position) {
                ShareBean bean = getItem(position);
                mImageView.setImageDrawable(bean.getShareIcon());
                mTextView.setText(bean.getShareName());
            }
        }
    }

    private static class ShareBean {

        /** 分享图标 */
        private final Drawable mShareIcon;
        /** 分享名称 */
        private final String mShareName;
        /** 分享平台 */
        private final Platform mSharePlatform;

        private ShareBean(Drawable icon, String name, Platform platform) {
            mShareIcon = icon;
            mShareName = name;
            mSharePlatform = platform;
        }

        private Drawable getShareIcon() {
            return mShareIcon;
        }

        private String getShareName() {
            return mShareName;
        }

        private Platform getSharePlatform() {
            return mSharePlatform;
        }
    }
}