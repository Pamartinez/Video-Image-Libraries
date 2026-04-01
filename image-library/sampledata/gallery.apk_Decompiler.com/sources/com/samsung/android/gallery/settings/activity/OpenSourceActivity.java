package com.samsung.android.gallery.settings.activity;

import N2.j;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OpenSourceActivity extends BaseActivity {
    private WebView mWebView;
    private FrameLayout mWebViewContainer;

    private String getTextColor() {
        int color = getColor(R$color.open_source_license_text_color);
        return String.format("#%08X", new Object[]{Integer.valueOf((color >> 24) & ((color << 8) | ScoverState.TYPE_NFC_SMART_COVER))});
    }

    private void loadWebView() {
        try {
            WebView webView = this.mWebView;
            webView.loadData(URLEncoder.encode(String.format("<html><head></head><meta name='viewport' content='width=device-width, user-scalable=yes'/><body style='color:" + getTextColor() + ";'><pre>%s</pre></body></html><div style='height:50px'></div>", new Object[]{readTextFromAsset()}), "utf-8").replaceAll("\\+", "%20"), "text/html", "utf-8");
            this.mWebView.setBackgroundColor(0);
            WebSettings settings = this.mWebView.getSettings();
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
            settings.setLoadWithOverviewMode(true);
            settings.setDefaultFontSize(13);
        } catch (UnsupportedEncodingException e) {
            String str = this.TAG;
            Log.e(str, "loadWebView failed. e=" + e.getMessage());
        }
    }

    private String readTextFromAsset() {
        BufferedReader bufferedReader;
        StringBuilder sb2 = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("NOTICE.txt"), StandardCharsets.UTF_8));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb2.append(10);
                sb2.append(readLine);
            }
            bufferedReader.close();
        } catch (IOException e) {
            j.r(e, new StringBuilder("readTextFromAsset failed. e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return sb2.toString();
        throw th;
    }

    public void bindView() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.open_source_license_web_view_container);
        this.mWebViewContainer = frameLayout;
        if (frameLayout != null) {
            WebView webView = new WebView(getApplicationContext());
            this.mWebView = webView;
            this.mWebViewContainer.addView(webView);
            loadWebView();
        }
    }

    public String getActivityTitle() {
        return getString(R$string.open_source_licences);
    }

    public int getLayoutId() {
        return R$layout.activity_open_source_layout;
    }

    public /* bridge */ /* synthetic */ void initActionBar() {
        super.initActionBar();
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onDestroy() {
        FrameLayout frameLayout = this.mWebViewContainer;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            this.mWebViewContainer = null;
        }
        WebView webView = this.mWebView;
        if (webView != null) {
            webView.removeAllViews();
            this.mWebView.destroy();
            this.mWebView = null;
        }
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
    }

    public /* bridge */ /* synthetic */ boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public /* bridge */ /* synthetic */ void setBackPressedCallbackEnabled(boolean z) {
        super.setBackPressedCallbackEnabled(z);
    }

    public /* bridge */ /* synthetic */ void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }
}
