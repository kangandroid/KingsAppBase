package com.king.mobile.testapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.TextureView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SurfaceActivity extends AppCompatActivity implements GLSurfaceView.Renderer{

    private SurfaceTexture surfaceTexture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SurfaceView surfaceView = new SurfaceView(this);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
//        TextureView textureView = new TextureView(this);
        surfaceView.setZOrderOnTop(true);
        glSurfaceView.setRenderer(this);
        setContentView(surfaceView);

        surfaceTexture = new SurfaceTexture(9);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        surfaceTexture.release();
    }
}