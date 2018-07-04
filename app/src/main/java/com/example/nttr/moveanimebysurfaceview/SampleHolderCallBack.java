package com.example.nttr.moveanimebysurfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by nttr on 2018/01/19.
 * http://blog.oukasoft.com/%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%A0/%E3%80%90android%E3%80%91surfaceview%E3%82%92%E4%BD%BF%E3%81%A3%E3%81%A6%E3%82%B2%E3%83%BC%E3%83%A0%E3%81%A3%E3%81%BD%E3%81%84%E3%82%A2%E3%83%97%E3%83%AA%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%A6%E3%81%BF/
 */

public class SampleHolderCallBack implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder holder = null;
    private Thread thread = null;
    private boolean isAttached = true;

    private float dx = 10, dy = 10;
    private float width, height;
    private int   circle_x, circle_y;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO 自動生成されたメソッド・スタブ
        this.holder = holder;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        canvas.drawCircle(100, 200, 50, paint);
        holder.unlockCanvasAndPost(canvas);

        thread = new Thread(this);
        thread.start(); //スレッドを開始
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO 自動生成されたメソッド・スタブ
        this.width = width;
        this.height = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO 自動生成されたメソッド・スタブ
        isAttached = false;
        thread = null; //スレッドを終了
    }

    @Override
    public void run() {
        // TODO 自動生成されたメソッド・スタブ
        // メインループ（無限ループ）
        while( isAttached ) {

            Log.d("テスト", "ループなう");

            //丸の表示位置を動かす
            if (circle_x < 0 || circle_x > this.width) {
                dx = -dx;
            }
            if (circle_y < 0 || circle_y > this.height) {
                dy = -dy;
            }

            circle_x += dx;
            circle_y += dy;

            //描画処理を開始
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            canvas.drawCircle(circle_x, circle_y, 50, paint);

            //描画処理を終了
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
