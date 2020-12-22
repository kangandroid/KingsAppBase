package com.king.player.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.king.mobile.util.ScreenAdapter;
import com.king.player.R;

import static android.util.TypedValue.COMPLEX_UNIT_PT;

public class ItemActionView extends FrameLayout {

    private TextView tvTitle;
    private TextView tvSubTitle;

    public ItemActionView(Context context) {
        this(context, null);
    }

    public ItemActionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ItemActionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateView(context, attrs);

    }

    private void inflateView(Context context, AttributeSet attrs) {
        tvTitle = new TextView(context);
        tvSubTitle = new TextView(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemActionView);
        String title = typedArray.getString(R.styleable.ItemActionView_title);
        String subTitle = typedArray.getString(R.styleable.ItemActionView_sub_title);
        typedArray.recycle();
        tvTitle.setTextColor(getResources().getColor(R.color.textBlack));
        tvTitle.setTextSize(COMPLEX_UNIT_PT,32);
        tvTitle.setText(title);
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        tvSubTitle.setText(subTitle);
        tvSubTitle.setTextColor(getResources().getColor(R.color.textBlack));
        tvSubTitle.setTextSize(COMPLEX_UNIT_PT,28);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_enter);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvSubTitle.setCompoundDrawables(null,null, drawable,null);
        tvSubTitle.setGravity(Gravity.CENTER);
        int wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT;
        LayoutParams titleParams = new LayoutParams(wrapContent, wrapContent);
        LayoutParams subTitleParams = new LayoutParams(wrapContent, wrapContent);
        titleParams.gravity = Gravity.CENTER | Gravity.START;
        titleParams.leftMargin = (int) ScreenAdapter.pt2px(context, 40);
        addView(tvTitle, titleParams);
        subTitleParams.gravity = Gravity.CENTER | Gravity.END;
        subTitleParams.rightMargin = (int) ScreenAdapter.pt2px(context, 40);
        addView(tvSubTitle, subTitleParams);
    }


}
