package com.king.player.view.widget;

import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;

import com.king.mobile.widget.CommonPop;
import com.king.player.R;

public class ChooseSpeedPopView extends CommonPop {
    private SpeedChangeListener mlistener;
    private String defaultSpeed = "1";
    private RadioGroup speedSelector;
    private RadioGroup.OnCheckedChangeListener listener = (group, checkedId) -> {
        switch (checkedId) {
            case R.id.speed_1x:
                setSpeed("1");
                break;
            case R.id.speed_1_2x:
                setSpeed("1.2");
                break;
            case R.id.speed_1_5x:
                setSpeed("1.5");
                break;
            case R.id.speed_2x:
                setSpeed("2");
                break;
        }
    };

    private void setSpeed(String speed) {
        defaultSpeed = speed;
        if (mlistener != null) {
            mlistener.onChange(speed);
        }
        dismiss();
    }


    public ChooseSpeedPopView(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        speedSelector = view.findViewById(R.id.speed_selector);
        speedSelector.setOnCheckedChangeListener(listener);
        switch (defaultSpeed){
            case "1":
                speedSelector.check(R.id.speed_1x);
                break;
            case "1.2":
                speedSelector.check(R.id.speed_1_2x);
                break;
            case "1.5":
                speedSelector.check(R.id.speed_1_5x);
                break;
            case "2":
                speedSelector.check(R.id.speed_2x);
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.layout_speed_option;
    }

    public void setOnChangeListener(SpeedChangeListener mlistener) {
        this.mlistener = mlistener;
    }

    public void setDefaultSpeed(String defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }

    public interface SpeedChangeListener {
        void onChange(String speed);
    }
}
