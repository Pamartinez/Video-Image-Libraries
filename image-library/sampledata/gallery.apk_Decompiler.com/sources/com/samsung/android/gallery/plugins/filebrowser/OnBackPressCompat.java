package com.samsung.android.gallery.plugins.filebrowser;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Stack;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class OnBackPressCompat extends FragmentManager.FragmentLifecycleCallbacks {
    FragmentManager fragmentManager;
    private final Stack<Fragment> fragmentStack = new Stack<>();
    Runnable onBackPressHandler;
    private final OnBackPressedCallback onBackPressedCallback;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BackAllowCallback {
        boolean isBackAllowed() {
            return true;
        }
    }

    public OnBackPressCompat(FragmentActivity fragmentActivity) {
        AnonymousClass1 r0 = new OnBackPressedCallback(false) {
            public void handleOnBackPressed() {
                if (!isEnabled()) {
                    Log.e("OnBackPressCompat", "handleOnBackPressed skip");
                } else if (OnBackPressCompat.this.onBackPressHandler != null) {
                    setEnabled(false);
                    OnBackPressCompat.this.onBackPressHandler.run();
                    setEnabled(true);
                }
            }
        };
        this.onBackPressedCallback = r0;
        fragmentActivity.getOnBackPressedDispatcher().addCallback(fragmentActivity, r0);
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        this.fragmentManager = supportFragmentManager;
        supportFragmentManager.registerFragmentLifecycleCallbacks(this, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyStateChange$0(boolean z) {
        this.onBackPressedCallback.setEnabled(z);
    }

    public void notifyStateChange() {
        Fragment fragment;
        if (this.fragmentStack.isEmpty()) {
            fragment = null;
        } else {
            fragment = this.fragmentStack.peek();
        }
        boolean z = true;
        if (this.fragmentStack.size() <= 1 && (!(fragment instanceof BackAllowCallback) || ((BackAllowCallback) fragment).isBackAllowed())) {
            z = false;
        }
        Log.d("OnBackPressCompat", "notifyStateChange", Integer.valueOf(this.fragmentStack.size()), Boolean.valueOf(z));
        this.uiHandler.post(new s(this, z));
    }

    public void onFragmentAttached(FragmentManager fragmentManager2, Fragment fragment, Context context) {
        this.fragmentStack.push(fragment);
    }

    public void onFragmentDetached(FragmentManager fragmentManager2, Fragment fragment) {
        Fragment pop = this.fragmentStack.pop();
        if (pop == fragment) {
            if (!this.fragmentStack.isEmpty()) {
                notifyStateChange();
            }
            this.uiHandler.post(new j((Object) this, (Object) fragmentManager2, (Object) fragment, 3));
            return;
        }
        throw new IllegalStateException("wrong fragment is detached fragment=" + fragment + ", but expected is " + pop);
    }

    public void release() {
        FragmentManager fragmentManager2 = this.fragmentManager;
        if (fragmentManager2 != null) {
            fragmentManager2.unregisterFragmentLifecycleCallbacks(this);
        }
    }

    public OnBackPressCompat setOnBackPressHandler(Runnable runnable) {
        this.onBackPressHandler = runnable;
        return this;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFragmentDetached$2(FragmentManager fragmentManager2, Fragment fragment) {
    }
}
