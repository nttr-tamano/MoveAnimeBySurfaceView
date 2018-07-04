package com.example.nttr.moveanimebysurfaceview;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    SurfaceView mSurfaceView;
    SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurfaceView = new SampleSurficeView(this);
        mHolder = mSurfaceView.getHolder();

        //mHolder.addCallback(this);  // thisはこのアクティビティ自身

        // サーフェスビューを透明にする
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        mSurfaceView.setZOrderOnTop(true);


    }
}
