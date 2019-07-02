package com.king.mobile.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    private int bacgroundColor;
    private int titleTextColor;
    private Action leftAction;
    private Action rightAction;

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
        stateBarPlaceholder.getLayoutParams().height = statusBarHeight;
        inflateView(context);
    }

    private void inflateView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_app_title, this);
        container = view.findViewById(R.id.container);

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

    public TitleBar setTitleBarColor(@ColorRes int color) {
        bacgroundColor = color;
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

    public void setTitleTextColor(@ColorRes int color) {
        titleTextColor = color;
    }

    class Action {
        @Nullable
        String title;
        @DrawableRes
        int icon;
        @NonNull
        View.OnClickListener action;

        public Action(String title, int icon, View.OnClickListener action) {
            this.title = title;
            this.icon = icon;
            this.action = action;
        }

    }
}
