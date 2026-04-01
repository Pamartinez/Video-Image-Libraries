package com.samsung.android.gallery.module.authentication;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.samsung.android.gallery.module.authentication.TwoFactorAuth;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TwoFactorAuthWebViewClient extends WebViewClient {
    private TwoFactorAuth.Listener mListener;

    private boolean getResponse(Uri uri, String str) {
        return Boolean.parseBoolean(uri.getQueryParameter(str));
    }

    private boolean isResponse(String str) {
        if (str.contains("result") || str.contains("close")) {
            return true;
        }
        return false;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.mListener.onPageFinished();
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        Log.d("TwoStepVerificationWebViewClient", "onPageStarted url=" + Logger.getEncodedString(str));
        if (isResponse(str)) {
            Uri parse = Uri.parse(str);
            if (getResponse(parse, "result")) {
                Blackboard.getApplicationInstance().post("global://setting/twoStepVerification", 200);
            }
            if (getResponse(parse, "close")) {
                this.mListener.onActivityFinished();
                return;
            }
            return;
        }
        this.mListener.onPageStarted();
    }

    public void setTwoStepVerificationListener(TwoFactorAuth.Listener listener) {
        this.mListener = listener;
    }
}
