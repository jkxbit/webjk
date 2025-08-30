
package com.raac.autojumper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView webView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://t.me/your_game_url"); // Replace with actual game URL

        handler.postDelayed(automateRunnable, 3000);
    }

    private Runnable automateRunnable = new Runnable() {
        @Override
        public void run() {
            simulateTouch();
            handler.postDelayed(this, 1500);
        }
    };

    private void simulateTouch() {
        long downTime = System.currentTimeMillis();
        long eventTime = System.currentTimeMillis();
        float x = webView.getWidth() / 2f;
        float y = webView.getHeight() / 2f;

        MotionEvent downEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, 0);
        MotionEvent upEvent = MotionEvent.obtain(downTime, eventTime + 100, MotionEvent.ACTION_UP, x, y, 0);

        webView.dispatchTouchEvent(downEvent);
        webView.dispatchTouchEvent(upEvent);
    }
}
