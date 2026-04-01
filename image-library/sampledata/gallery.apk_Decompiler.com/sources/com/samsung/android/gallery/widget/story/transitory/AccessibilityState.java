package com.samsung.android.gallery.widget.story.transitory;

import A.a;
import A4.L;
import B4.c;
import android.view.accessibility.AccessibilityManager;
import bc.C0584a;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AccessibilityState implements AccessibilityManager.AccessibilityStateChangeListener {
    private AccessibilityManager mAccessibilityManager;
    private boolean mIsDestroyed;
    private Consumer<Boolean> mListener;
    private boolean mVoiceServiceEnabled;

    public AccessibilityState() {
        Log.i("AccessibilityState", "create " + Logger.getSimpleName((Object) this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAccessibilityState$0() {
        boolean z;
        try {
            if (!this.mIsDestroyed) {
                AccessibilityManager accessibilityManager = this.mAccessibilityManager;
                if (accessibilityManager == null) {
                    this.mAccessibilityManager = (AccessibilityManager) AppResources.getAppContext().getSystemService("accessibility");
                } else {
                    accessibilityManager.removeAccessibilityStateChangeListener(this);
                }
                this.mAccessibilityManager.addAccessibilityStateChangeListener(this);
                this.mVoiceServiceEnabled = loadState();
                if (this.mAccessibilityManager != null) {
                    z = true;
                } else {
                    z = false;
                }
                Log.i("AccessibilityState", "initAccessibilityState ", Boolean.valueOf(z), Boolean.valueOf(this.mVoiceServiceEnabled));
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to initAccessibilityState="), "AccessibilityState");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAccessibilityStateChanged$2(boolean z) {
        Optional.ofNullable(this.mListener).ifPresent(new L(z, 22));
    }

    private boolean loadState() {
        AccessibilityManager accessibilityManager;
        if (!SdkConfig.atLeast(SdkConfig.GED.R) || (accessibilityManager = this.mAccessibilityManager) == null) {
            return false;
        }
        return accessibilityManager.semIsScreenReaderEnabled();
    }

    public void destroy() {
        this.mIsDestroyed = true;
        this.mListener = null;
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        if (accessibilityManager != null) {
            accessibilityManager.removeAccessibilityStateChangeListener(this);
        }
        Log.i("AccessibilityState", "destroy " + Logger.getSimpleName((Object) this));
    }

    public void initAccessibilityState(Consumer<Boolean> consumer) {
        this.mIsDestroyed = false;
        this.mListener = consumer;
        SimpleThreadPool.getInstance().execute(new C0584a(0, this));
    }

    public boolean isVoiceServiceEnabled() {
        return this.mVoiceServiceEnabled;
    }

    public void onAccessibilityStateChanged(boolean z) {
        boolean z3 = this.mVoiceServiceEnabled;
        this.mVoiceServiceEnabled = z;
        if (z3 != z) {
            ThreadUtil.runOnUiThread(new c((Object) this, z, 18));
        }
    }
}
