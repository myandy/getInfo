package com.myth.getinfo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity implements android.view.KeyEvent.Callback
{

    WebView mWebView;

    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = (WebView) findViewById(R.id.wv);
        if (url != null && !url.isEmpty())
        {
            Log.d("url", url);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setSupportZoom(true);
            mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            mWebView.requestFocus();
            mWebView.loadUrl(url);
        }
        mWebView.setWebViewClient(new WebViewClient()
        {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient()
        {

            @Override
            public void onProgressChanged(WebView view, int newProgress)
            {

                if (newProgress == 100)
                {

                    // handler.sendEmptyMessage(CLOSE_DIA);

                }

                super.onProgressChanged(view, newProgress);

            }
        });
        mWebView.setWebChromeClient(new WebChromeClient()
        {

            public void onProgressChanged(WebView view, int progress)
            {
                setTitle("页面加载中，请稍候..." + progress + "%");
                setProgress(progress * 100);

                if (progress == 100)
                {
                    setTitle(R.string.app_name);
                }
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (mWebView.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
