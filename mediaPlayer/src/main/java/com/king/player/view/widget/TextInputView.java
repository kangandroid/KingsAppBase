package com.king.player.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.king.player.R;

public class TextInputView extends FrameLayout {
    private TextView tvTitle;
    private EditText etContent;
    private ImageView ivClear;

    public TextInputView(@NonNull Context context) {
        this(context, null);
    }

    public TextInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TextInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateView(context, attrs);
    }

    private void inflateView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextInputView);
        int inputType = typedArray.getInt(R.styleable.TextInputView_android_inputType, 0);
        String inputHint = typedArray.getString(R.styleable.TextInputView_inputHint);
        String title = typedArray.getString(R.styleable.TextInputView_inputTitle);
        typedArray.recycle();
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_input_view, this);
        tvTitle = inflate.findViewById(R.id.tv_title);
        etContent = inflate.findViewById(R.id.et_content);
        ivClear = inflate.findViewById(R.id.iv_clear);
        tvTitle.setText(title);
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    ivClear.setVisibility(VISIBLE);
                } else {
                    ivClear.setVisibility(INVISIBLE);
                }
            }
        });
        etContent.setHint(inputHint);
        etContent.setInputType(inputType);
        ivClear.setOnClickListener(v -> etContent.setText(""));
    }

    public void addTextWatcher(TextWatcher watcher) {
        etContent.addTextChangedListener(watcher);
    }


    public void setContent(String content) {
        etContent.setText(content);
    }
}
