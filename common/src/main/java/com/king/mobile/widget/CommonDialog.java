package com.king.mobile.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.gyf.immersionbar.ImmersionBar;
import com.king.mobile.util.R;

public abstract class CommonDialog extends DialogFragment {
    public static final String DIALOG_POSITION_TOP = "DIALOG_POSITION_TOP";
    public static final String DIALOG_POSITION_BOTTOM = "DIALOG_POSITION_BOTTOM";
    public static final String DIALOG_POSITION_LEFT = "DIALOG_POSITION_LEFT";
    public static final String DIALOG_POSITION_RIGHT = "DIALOG_POSITION_RIGHT";
    public static final String DIALOG_POSITION_FULL_SCREEN = "DIALOG_POSITION_FULL_SCREEN";

    private String dialogPositon = DIALOG_POSITION_FULL_SCREEN;
    private int height = ViewGroup.LayoutParams.WRAP_CONTENT;
    protected Activity mActivity;
    private Window mWindow;
    private View mRootView;

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        //点击外部消失
        dialog.setCanceledOnTouchOutside(true);
        mWindow = dialog.getWindow();
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(getAnimation());
        if (dialogPositon == DIALOG_POSITION_TOP || dialogPositon == DIALOG_POSITION_BOTTOM) {
            mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);
        } else if (dialogPositon == DIALOG_POSITION_RIGHT || dialogPositon == DIALOG_POSITION_LEFT) {
            mWindow.setLayout(height, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    private int getAnimation() {
        switch (dialogPositon) {
            case DIALOG_POSITION_BOTTOM:
                return R.style.BottomAnimation;
            case DIALOG_POSITION_TOP:
                return R.style.TopAnimation;
            case DIALOG_POSITION_LEFT:
                return R.style.LeftAnimation;
            case DIALOG_POSITION_RIGHT:
                return R.style.RightAnimation;
            case DIALOG_POSITION_FULL_SCREEN:
                return R.style.FadeAnimation;
            default:
                return R.style.FadeAnimation;
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    protected abstract @LayoutRes
    int setLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.with(this).init();
        initView(mRootView);
    }

    protected abstract void initView(View mRootView);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialog);
    }
}
