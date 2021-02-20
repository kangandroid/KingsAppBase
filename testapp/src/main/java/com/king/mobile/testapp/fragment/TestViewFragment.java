package com.king.mobile.testapp.fragment;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.king.mobile.testapp.R;
import com.king.mobile.testapp.activity.MySurfaceView;

public class TestViewFragment extends Fragment {
    private String name = "default_name";

    public TestViewFragment() {
    }

    public TestViewFragment(String name) {
        this.name = name;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        print("onAttach");

    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        print("onCreateAnimation：transit=" + transit + " | enter = " + enter + " | nextAnim = " + nextAnim);
        return super.onCreateAnimation(transit, enter, enter ? R.anim.slide_in : R.anim.slide_out);
    }

    @Nullable
    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        print("onCreateAnimator：transit=" + transit + " | enter = " + enter + " | nextAnim = " + nextAnim);
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        print("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        print("onCreateView");
        return new MySurfaceView(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(v -> TestViewFragment.this.setUserVisibleHint(false));
        print("onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        print("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        print("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        print("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        print("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        print("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        print("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        print("onDetach");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        print("onHiddenChanged=" + hidden);
    }


    void print(String s) {
        System.out.println("TestFragment----------"+name+"---------:" + s);
    }
}
