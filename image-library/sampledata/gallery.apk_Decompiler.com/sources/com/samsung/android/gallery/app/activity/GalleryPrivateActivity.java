package com.samsung.android.gallery.app.activity;

import A4.C0367b;
import Ba.g;
import C3.m;
import C3.n;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.ui.list.trash.PrivateTrashFragment;
import com.samsung.android.gallery.module.secured.KeepStorageManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.BiometricPromptCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryPrivateActivity extends GalleryActivity {
    final Handler mBgHandler;
    final KeepStorageDelegate mKeepStorageDelegate;
    private final Runnable mLockCallback = new m(this, 1);
    View mLockView;
    BiometricPromptCompat mPrompt;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GalleryActivityHandlerImpl extends GalleryActivityHandler {
        public GalleryActivityHandlerImpl(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
            super(blackboard, iGalleryActivityView);
        }

        /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, java.lang.Runnable] */
        public void onThumbLoadDone(Object obj, Bundle bundle) {
            super.onThumbLoadDone(obj, bundle);
            SimpleThreadPool.getInstance().execute(new Object());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class KeepStorageDelegate {
        final KeepStorageManager mKeepStorageManager = KeepStorageManager.getInstance();
        final AtomicBoolean mLockState = new AtomicBoolean(true);

        public void close() {
            if (!this.mLockState.get() && this.mKeepStorageManager.close("Activity")) {
                this.mLockState.set(true);
            }
        }

        public void closeSession() {
            close();
            this.mKeepStorageManager.closeSession("Activity");
        }

        public void open() {
            if (this.mLockState.get() && this.mKeepStorageManager.open("Activity")) {
                this.mLockState.set(false);
            }
        }

        public void openSession() {
            this.mKeepStorageManager.openSession("Activity");
            this.mLockState.set(!this.mKeepStorageManager.open("Activity"));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewNavigatorImpl extends ViewNavigator {
        public ViewNavigatorImpl(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
            super(blackboard, iGalleryActivityView);
        }

        public ViewNavigatorController createDefaultController() {
            return new ViewNavigatorControllerImpl(this.mBlackboard, this.mActivityView);
        }
    }

    public GalleryPrivateActivity() {
        KeepStorageDelegate keepStorageDelegate = new KeepStorageDelegate();
        this.mKeepStorageDelegate = keepStorageDelegate;
        Handler handler = new Handler(ThreadUtil.createBackgroundThreadLooper(this.TAG));
        this.mBgHandler = handler;
        handler.post(new i(1, keepStorageDelegate));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$6() {
        BiometricPromptCompat biometricPromptCompat = this.mPrompt;
        if (biometricPromptCompat != null) {
            biometricPromptCompat.dismiss();
        }
        lockUi();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0() {
        this.mBlackboard.post("command://MoveURL", "location://albums/private/fileList");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(AtomicInteger atomicInteger, View view) {
        if (atomicInteger.get() == 0) {
            GalleryPreference.getInstanceCache().saveState("private_album_entry", atomicInteger.incrementAndGet());
        }
        showPrompt();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(Integer num) {
        if (num.intValue() == 0) {
            unlockUi();
        } else if (num.intValue() == 2) {
            Log.e(this.TAG, "auth not available");
            BiometricPromptCompat.setupScreenLock(this);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDestroy$4() {
        this.mBgHandler.removeCallbacksAndMessages((Object) null);
        this.mBgHandler.getLooper().quitSafely();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDestroy$5() {
        this.mKeepStorageDelegate.closeSession();
        ThreadUtil.postOnBgThread(new m(this, 3));
    }

    public Subscriber createActivityHandler(Blackboard blackboard) {
        return new GalleryActivityHandlerImpl(blackboard, this);
    }

    public Subscriber createViewNavigator(Blackboard blackboard) {
        return new ViewNavigatorImpl(blackboard, this);
    }

    public boolean isUiLocked() {
        View view = this.mLockView;
        if (view == null || view.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public void lockUi() {
        View view = this.mLockView;
        if (view != null) {
            view.setVisibility(0);
        }
        this.mBlackboard.publish("command://LockPrivateAlbum", Boolean.TRUE);
    }

    public void onBackPressed() {
        if (isUiLocked()) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBgHandler.post(new m(this, 0));
        getWindow().setFlags(SerializeOptions.SORT, SerializeOptions.SORT);
        AtomicInteger atomicInteger = new AtomicInteger(GalleryPreference.getInstanceCache().loadInt("private_album_entry", 0));
        View inflate = getLayoutInflater().inflate(R.layout.unlock_private_album_fragment_layout, (ViewGroup) null, false);
        this.mLockView = inflate;
        inflate.setBackgroundResource(R.color.default_background);
        this.mLockView.setOnClickListener(new n(0));
        this.mLockView.findViewById(R.id.lock_icon_layout).setOnClickListener(new g(1, this, atomicInteger));
        ((ViewGroup) findViewById(R.id.content_layout)).addView(this.mLockView);
        this.mPrompt = new BiometricPromptCompat().setTitle(R.string.unlock_private_album).setCallback(new C0367b(17, this));
        if (atomicInteger.get() > 0) {
            showPrompt();
        }
    }

    public void onDestroy() {
        View view = this.mLockView;
        if (view != null) {
            view.removeCallbacks(this.mLockCallback);
            ViewUtils.removeSelf(this.mLockView);
            this.mLockView = null;
        }
        BiometricPromptCompat biometricPromptCompat = this.mPrompt;
        if (biometricPromptCompat != null) {
            biometricPromptCompat.release();
            this.mPrompt = null;
        }
        this.mBlackboard.erase("command://LockPrivateAlbum");
        super.onDestroy();
        this.mBgHandler.post(new m(this, 2));
    }

    public void onResume() {
        super.onResume();
        Handler handler = this.mBgHandler;
        KeepStorageDelegate keepStorageDelegate = this.mKeepStorageDelegate;
        Objects.requireNonNull(keepStorageDelegate);
        handler.post(new i(2, keepStorageDelegate));
    }

    public void onStop() {
        DialogFragment dialogFragment = (DialogFragment) this.mBlackboard.read("data://user/dialog/recently", null);
        if (dialogFragment != null && dialogFragment.isAdded()) {
            Log.d(this.TAG, "onStop > dismiss dialog", Logger.getSimpleName((Object) dialogFragment));
            dialogFragment.dismiss();
            this.mBlackboard.erase("data://user/dialog/recently");
        }
        super.onStop();
        View view = this.mLockView;
        if (view != null) {
            view.postDelayed(this.mLockCallback, 180);
        }
        Handler handler = this.mBgHandler;
        KeepStorageDelegate keepStorageDelegate = this.mKeepStorageDelegate;
        Objects.requireNonNull(keepStorageDelegate);
        handler.post(new i(0, keepStorageDelegate));
    }

    public boolean preOnBackPressed(String str) {
        if (!isUiLocked()) {
            return super.preOnBackPressed(str);
        }
        finish();
        return true;
    }

    public void showPrompt() {
        BiometricPromptCompat biometricPromptCompat = this.mPrompt;
        if (biometricPromptCompat != null) {
            biometricPromptCompat.authenticate(this);
        }
    }

    public void unlockUi() {
        View view = this.mLockView;
        if (view != null) {
            view.setVisibility(8);
        }
        this.mBlackboard.publish("command://LockPrivateAlbum", Boolean.FALSE);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewNavigatorControllerImpl extends ViewNavigatorController {
        public ViewNavigatorControllerImpl(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
            super(blackboard, iGalleryActivityView);
        }

        public boolean commitFragmentByLocationKey(String str, String str2) {
            if (!"location://private/trash".equals(str2)) {
                return super.commitFragmentByLocationKey(str, str2);
            }
            setMvpFragment(new PrivateTrashFragment(), str, str2);
            return true;
        }

        public void showDialog(DialogFragment dialogFragment, Bundle bundle, String str) {
            super.showDialog(dialogFragment, bundle, str);
            this.mBlackboard.publish("data://user/dialog/recently", dialogFragment);
        }

        public void onNavigatorCreated() {
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreate$1(View view) {
    }
}
