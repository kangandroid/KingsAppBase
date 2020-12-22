package com.king.mobile.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.king.mobile.util.ColorUtil;
import com.king.mobile.util.R;

public class TitleBar extends LinearLayout {
    private Context mContext;
    private FrameLayout container;
    private FrameLayout titleContainer;
    private LinearLayout actionLeft;
    private LinearLayout actionRight;
    private TextView txTitle;
    private TextView txTitleRight;
    private TextView txTitleLeft;
    private ImageView iconLeft;
    private ImageView iconRight;

    private String mTitle;
    @ColorInt
    private int backgroundColor;
    @ColorInt
    private int titleTextColor;
    private Action leftAction;
    private Action rightAction;
    private ImageView imageBg;
    private Drawable gradientBg;
    private View titleView;

    public static Action ACTION_BACK_DARK;
    public static Action ACTION_BACK_LIGHT;

    static {
        OnClickListener onClickListener = v -> {
            Context context = v.getContext();
            if (context == null) return;
            if (context instanceof Activity) {
                ((Activity) context).onBackPressed();
            }
        };
        ACTION_BACK_DARK = new Action("", R.drawable.ic_back_white, onClickListener);
        ACTION_BACK_LIGHT = new Action("", R.drawable.ic_back_black, onClickListener);
    }

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar setTitleView(View titleView) {
        this.titleView = titleView;
        return this;
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflateView(context);
    }

    private void inflateView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_app_title, this);
        imageBg = findViewById(R.id.iv_title_bg);
        container = view.findViewById(R.id.action_container);
        titleContainer = view.findViewById(R.id.title_container);
        actionLeft = container.findViewById(R.id.action_left);
        iconLeft = actionLeft.findViewById(R.id.icon_left);
        txTitleLeft = actionLeft.findViewById(R.id.title_left);
        actionRight = container.findViewById(R.id.action_right);
        iconRight = actionRight.findViewById(R.id.icon_right);
        txTitleRight = actionRight.findViewById(R.id.title_right);
        txTitle = container.findViewById(R.id.title);
    }

    /**
     * 需要动态设置title背景图片时获取
     *
     * @return
     */
    ImageView getBgImage() {
        imageBg.setVisibility(VISIBLE);
        return imageBg;
    }

    public TitleBar gradient(GradientDrawable.Orientation orientation, @ColorInt int[] colors) {
        gradientBg = new GradientDrawable(orientation, colors);
        return this;
    }

    public TitleBar immersive(Activity activity, boolean isDark ) {
        ImmersionBar.with(activity)
                .statusBarDarkFont(isDark)
                .keyboardEnable(true)
                .titleBar(this)
                .init();
        return this;
    }

    public TitleBar immersive(Fragment fragment, boolean isDark) {
        ImmersionBar.with(fragment)
                .statusBarDarkFont(isDark)
                .keyboardEnable(true)
                .titleBar(this)
                .init();
        return this;
    }

    public TitleBar immersive(DialogFragment fragment, boolean isDark) {
        ImmersionBar.with(fragment)
                .statusBarDarkFont(isDark)
                .keyboardEnable(true)
                .titleBar(this)
                .init();
        return this;
    }

    public TitleBar setTitle(String title) {
        this.mTitle = title;
        return this;
    }
    public TitleBar setTitle(@StringRes int title) {
        this.mTitle = getResources().getString(title);
        return this;
    }

    public TitleBar setTitleBarColorRes(@ColorRes int color) {
        gradientBg = null;
        backgroundColor = ColorUtil.getColor(mContext, color);
        return this;
    }

    public TitleBar setTitleBarColorInt(@ColorInt int color) {
        gradientBg = null;
        backgroundColor = color;
        return this;
    }

    public TitleBar setTitleBarColor(String color) {
        gradientBg = null;
        backgroundColor = ColorUtil.getColor(color);
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
        if (imageBg.getVisibility() == VISIBLE) {
            setBackgroundColor(ColorUtil.getColor(mContext, R.color.transparent));
        } else if (gradientBg != null) {
            setBackground(gradientBg);
        } else {
            setBackgroundColor(backgroundColor);
        }
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
        if(titleView!=null){
            txTitle.setVisibility(GONE);
            titleContainer.addView(titleView);
        }else {
            txTitle.setVisibility(VISIBLE);
            txTitle.setText(mTitle);
            txTitle.setTextColor(titleTextColor);
        }
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
