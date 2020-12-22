package com.king.player.search.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.util.ToastUtil;
import com.king.player.R;
import com.king.player.search.WebSiteInfo;
import com.king.player.search.WebsiteViewModel;
import com.king.player.search.ui.WebSiteDialog;

class WebsiteVieHolder extends RecyclerView.ViewHolder {

    private final ImageView ivChecked;
    private final TextView tvTitle;
    private final ImageView ivDelete;
    private final ImageView ivEdit;
    private WebsiteViewModel viewModel;

    public WebsiteVieHolder(View itemView) {
        super(itemView);
        Context context = itemView.getContext();
        if (context instanceof Activity) {
            viewModel = WebsiteViewModel.getInstance(((Activity) context).getApplication());
        }
        ivChecked = itemView.findViewById(R.id.iv_checked);
        tvTitle = itemView.findViewById(R.id.tv_title);
        ivDelete = itemView.findViewById(R.id.iv_delete);
        ivEdit = itemView.findViewById(R.id.iv_edit);

    }

    public void bindData(WebSiteInfo item) {
        itemView.setOnClickListener(v -> viewModel.setDefault(item, null, throwable -> ToastUtil.show("设置失败")));
        WebSiteDialog webSiteDialog = new WebSiteDialog(item);
        ivChecked.setVisibility(item.isDefault == 1 ? View.VISIBLE : View.INVISIBLE);
        tvTitle.setText(item.title);
        ivDelete.setOnClickListener(v -> viewModel.delete(item));
        ivEdit.setOnClickListener(v -> {
            Context context = v.getContext();
            if (context instanceof AppCompatActivity) {
                webSiteDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "WebsiteVieHolder");
            }
        });

    }
}