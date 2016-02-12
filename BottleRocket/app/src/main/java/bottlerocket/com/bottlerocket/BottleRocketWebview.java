package bottlerocket.com.bottlerocket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.logging.Logger;

/**
 * Created by Sneha on 12-Feb-16.
 */
public class BottleRocketWebview extends Activity
{
    WebView webView = null;
    public Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_web_view);

        Bundle bdl = getIntent().getExtras();
        String url = bdl.get("URL").toString();

        String newUrl = url.replaceAll("[^a-zA-Z]+", " ").replaceAll("\\s", "").toLowerCase();
        Toast.makeText(getApplicationContext(), "Loading http://www."+newUrl+".com", Toast.LENGTH_SHORT).show();

        webView = (WebView)findViewById(R.id.webview);
        webView.loadUrl("http://www."+newUrl+".com");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
               /* FrameLayout progressBarFrame = (FrameLayout) BottleRocketWebview.this.findViewById(R.id.loadProgressBarFrame);
                if (webView.getVisibility() == View.VISIBLE) {
                    progressBarFrame.setVisibility(View.VISIBLE);
                    progressBarFrame.bringToFront();
                    setBrowserProgress(progress);
                } else {
                    progressBarFrame.setVisibility(View.GONE);
                }*/
            }
        });


         handler = new Handler() {

            BottleRocketWebview mActivity = BottleRocketWebview.this;

            @Override
            public void handleMessage(Message msg) {

                if(mActivity != null) {

                    switch (msg.what)
                    {
                        case 500:
                            changeProgressBarBackGround();
                            break;

                        case 1000:
                            int val = msg.what;
                            changeProgressBar(val);
                            break;
                    }
                }
            }
        };


    }

    public void setBrowserProgress(final int progress)
    {
        try
        {
            Message msg = new Message();
            msg.what = 1000;
            msg.arg1 = progress;
            handler.sendMessage(msg);
        }
        catch (Exception ex)
        {

        }
    }

    public void changeProgressBar(final int progress)
    {
        try
        {
            FrameLayout progressBarFrame = (FrameLayout) BottleRocketWebview.this.findViewById(R.id.loadProgressBarFrame);
            ProgressBar progressBar = (ProgressBar) BottleRocketWebview.this.findViewById(R.id.loadProgressBar);
            progressBar.setProgress(progress);
            if (progress == 100)
            {
                progressBarFrame.setVisibility(View.GONE);
            }
            else
            {
                progressBarFrame.setVisibility(View.VISIBLE);
            }
            handler.sendEmptyMessage(500);

        }
        catch (Exception ex)
        {

        }
    }

    private static int progressBGChange = 0;

    public  void changeProgressBarBackGround()
    {
        try
        {

            ProgressBar progress = ((ProgressBar) BottleRocketWebview.this.findViewById(R.id.loadProgressBar));
            if (progress.getVisibility() == View.VISIBLE)
            {
                if (progressBGChange % 2 == 0)
                {
                    progress.setProgressDrawable(BottleRocketWebview.this.getResources().getDrawable(R.drawable.progress_bar_states_change));
                }
                else
                {
                    progress.setProgressDrawable(BottleRocketWebview.this.getResources().getDrawable(R.drawable.progress_bar_states));
                }
                progressBGChange++;
                handler.sendEmptyMessageDelayed(500, 1000);
            }
            else
            {
                handler.removeMessages(500);
            }


        }
        catch (Exception ex)
        {

        }
    }
}
