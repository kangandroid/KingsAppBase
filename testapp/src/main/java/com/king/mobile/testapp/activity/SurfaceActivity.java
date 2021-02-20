package com.king.mobile.testapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SurfaceActivity extends AppCompatActivity implements GLSurfaceView.Renderer{

    private SurfaceTexture surfaceTexture;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
//        TextureView textureView = new TextureView(this);
//        glSurfaceView.setRenderer(this);
        surfaceView = new SurfaceView(this);
        surfaceView.setZOrderOnTop(true);
        SurfaceHolder holder = surfaceView.getHolder();
        Canvas canvas = holder.lockCanvas();
        Bitmap bitmap = BitmapFactory.decodeStream(null);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap,0,0, paint);
        surfaceView.draw(canvas);
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