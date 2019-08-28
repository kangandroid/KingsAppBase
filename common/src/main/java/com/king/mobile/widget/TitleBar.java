package com.king.mobile.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.king.mobile.util.ColorUtil;
import com.king.mobile.util.R;
import com.king.mobile.util.ScreenUtils;

public class TitleBar extends LinearLayout {
    private Context mContext;
    private FrameLayout container;
    private LinearLayout actionLeft;
    private LinearLayout actionRight;
    private TextView txTitle;
    private TextView txTitleRight;
    private TextView txTitleLeft;
    private ImageView iconLeft;
    private ImageView iconRight;

    private View stateBarPlaceholder;
    private String mTitle;
    @ColorInt
    private int bacgroundColor;
    @ColorInt
    private int titleTextColor;
    private Action leftAction;
    private Action rightAction;
    private ImageView imageBg;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        this.setOrientation(VERTICAL);
        int statusBarHeight = ScreenUtils.getStatusBarHeight(context);
        stateBarPlaceholder = new View(context);
        this.addView(stateBarPlaceholder, 0);
        imageBg = new ImageView(context);
        stateBarPlaceholder.getLayoutParams().height = statusBarHeight;
        inflateView(context);
    }

    private void inflateView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_app_title, this);
        container = view.findViewById(R.id.action_container);
        actionLeft = container.findViewById(R.id.action_left);
        iconLeft = actionLeft.findViewById(R.id.icon_left);
        txTitleLeft = actionLeft.findViewById(R.id.title_left);
        actionRight = container.findViewById(R.id.action_right);
        iconRight = actionRight.findViewById(R.id.icon_right);
        txTitleRight = actionRight.findViewById(R.id.title_right);

        txTitle = container.findViewById(R.id.title);

    }

    public TitleBar setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public TitleBar setTitleBarColorRes(@ColorRes int color) {
        bacgroundColor = ColorUtil.getColor(mContext, color);
        return this;
    }

    public TitleBar setTitleBarColorInt(@ColorInt int color) {
        bacgroundColor = color;
        return this;
    }

    public TitleBar setTitleBarColor(String color) {
        bacgroundColor = ColorUtil.getColor(color);
        return this;
    }

    public TitleBar setRightAction(Action action) {
        rightAction = action;
        return this;
    }

    public TitleBar setLeftAction(Action action) {
        leftAction = action;
        return this;
    }

    public TitleBar setTitleTextColor(@ColorRes int color) {
        titleTextColor = ColorUtil.getColor(mContext, color);
        return this;
    }
    public TitleBar setTitleTextColorInt(@ColorInt int color) {
        titleTextColor = color;
        return this;
    }

    public TitleBar setTitleTextColor(String color) {
        titleTextColor = ColorUtil.getColor(color);
        return this;
    }

    public void invalidate() {
        setBackgroundColor(bacgroundColor);
        txTitleLeft.setTextColor(titleTextColor);
        if (leftAction != null) {
            actionLeft.setVisibility(VISIBLE);
            actionLeft.setOnClickListener(leftAction.action);
            if (leftAction.icon != 0) {
                iconLeft.setVisibility(VISIBLE);
                iconLeft.setImageResource(leftAction.icon);
            }
            if (!TextUtils.isEmpty(leftAction.title)) {
                txTitleLeft.setVisibility(VISIBLE);
                txTitleLeft.setText(leftAction.title);
            }
        } else {
            actionLeft.setVisibility(GONE);
        }
        txTitleRight.setTextColor(titleTextColor);
        if (rightAction != null) {
            actionRight.setVisibility(VISIBLE);
            actionRight.setOnClickListener(rightAction.action);
            if (rightAction.icon != 0) {
                iconRight.setVisibility(VISIBLE);
                iconRight.setImageResource(rightAction.icon);
            }
            if (!TextUtils.isEmpty(rightAction.title)) {
                txTitleRight.setVisibility(VISIBLE);
                txTitleRight.setText(rightAction.title);
            }
        } else {
            actionRight.setVisibility(GONE);
        }
        txTitle.setText(mTitle);
        txTitle.setTextColor(titleTextColor);
    }

    public static class Action {
        @Nullable
        String title;
        @DrawableRes
        int icon;
        @NonNull
        View.OnClickListener action;

        public Action(@Nullable String title, int icon, @NonNull View.OnClickListener action) {
            this.title = title;
            this.icon = icon;
            this.action = action;
        }

    }
}
