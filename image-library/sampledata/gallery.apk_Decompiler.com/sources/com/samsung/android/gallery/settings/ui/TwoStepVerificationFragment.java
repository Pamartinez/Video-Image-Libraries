package com.samsung.android.gallery.settings.ui;

import A9.b;
import Fa.a0;
import O3.l;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.module.authentication.TwoFactorAuth;
import com.samsung.android.gallery.module.authentication.TwoFactorAuthPageReqInfo;
import com.samsung.android.gallery.module.authentication.TwoFactorAuthWebViewClient;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseActivity;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TwoStepVerificationFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public final IBaseActivity mActivity;
    private final TwoFactorAuth.Listener mListener = new TwoFactorAuth.Listener() {
        public void onActivityFinished() {
            Optional.ofNullable(TwoStepVerificationFragment.this.getActivity()).ifPresent(new l(18));
        }

        public void onPageFinished() {
            ViewUtils.setVisibleOrGone(TwoStepVerificationFragment.this.mProgress, false);
        }

        public void onPageStarted() {
            boolean z = true;
            ViewUtils.setVisibleOrGone(TwoStepVerificationFragment.this.mProgress, true);
            IBaseActivity h5 = TwoStepVerificationFragment.this.mActivity;
            if (TwoStepVerificationFragment.this.mWebView == null || !TwoStepVerificationFragment.this.mWebView.canGoBack()) {
                z = false;
            }
            h5.setBackPressedCallbackEnabled(z);
        }
    };
    /* access modifiers changed from: private */
    public View mProgress;
    /* access modifiers changed from: private */
    public WebView mWebView;
    private FrameLayout mWebViewContainer;

    public TwoStepVerificationFragment(IBaseActivity iBaseActivity) {
        this.mActivity = iBaseActivity;
    }

    private void bindWebView() {
        setWebView();
        setWebViewClient();
        loadWebView();
    }

    /* access modifiers changed from: private */
    public void finishActivityWithToast() {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            Toast.makeText(activity, R$string.couldnt_connect_check_network_connection_description, 0).show();
            activity.finish();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadWebView$1() {
        if (TwoFactorAuthPageReqInfo.getInstance().refreshAccessToken(false)) {
            ThreadUtil.postOnUiThread(new b(this, TwoFactorAuthPageReqInfo.getInstance().getUrl(), TwoFactorAuthPageReqInfo.getInstance().getHeaderMap(), 18));
        } else {
            ThreadUtil.postOnUiThread(new a0(this, 1));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: loadUrl */
    public void lambda$loadWebView$0(String str, Map<String, String> map) {
        WebView webView;
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed() && (webView = this.mWebView) != null) {
            webView.loadUrl(str, map);
        }
    }

    private void loadWebView() {
        SimpleThreadPool.getInstance().execute(new a0(this, 0));
    }

    private void setWebView() {
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setSupportMultipleWindows(true);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    private void setWebViewClient() {
        TwoFactorAuthWebViewClient twoFactorAuthWebViewClient = new TwoFactorAuthWebViewClient();
        twoFactorAuthWebViewClient.setTwoStepVerificationListener(this.mListener);
        this.mWebView.setWebViewClient(twoFactorAuthWebViewClient);
    }

    public void bindViews(ViewGroup viewGroup) {
        Log.d(this.TAG, "bindViews");
        FrameLayout frameLayout = (FrameLayout) viewGroup.findViewById(R$id.two_step_verification_web_view_container);
        this.mWebViewContainer = frameLayout;
        if (frameLayout != null) {
            WebView webView = new WebView(this.mWebViewContainer.getContext().getApplicationContext());
            this.mWebView = webView;
            this.mWebViewContainer.addView(webView);
            this.mProgress = viewGroup.findViewById(R$id.two_step_verification_web_progress);
            bindWebView();
        }
    }

    public int getLayoutId() {
        return R$layout.fragment_two_step_verification_layout;
    }

    public int getTitleId() {
        return 0;
    }

    public boolean onBackPressed() {
        WebView webView = this.mWebView;
        if (webView == null || !webView.canGoBack()) {
            return false;
        }
        this.mWebView.goBack();
        return true;
    }

    public void onDestroyView() {
        super.onDestroyView();
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
        if (this.mProgress != null) {
            this.mProgress = null;
        }
    }

    public void updateFragment() {
    }
}
