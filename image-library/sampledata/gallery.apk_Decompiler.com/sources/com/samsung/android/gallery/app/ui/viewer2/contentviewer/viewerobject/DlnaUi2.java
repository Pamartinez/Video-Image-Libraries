package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import B4.c;
import android.content.res.Configuration;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.ocr.MOCRLang;
import com.sec.android.gallery3d.R;
import o5.C0496a;
import v7.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DlnaUi2 extends ViewerObject implements FragmentLifeCycle {
    private View mDlnaButton;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class InputBlock {
        private static final int RELEASE_INPUT_BLOCKING_TIMER = Timer.getTimerId();
        private static boolean sInputBlocking;
        private static final Timer.OnTimer sReleaseInputBlock = new Object();

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$static$0(int i2) {
            if (i2 != -1) {
                sInputBlocking = false;
            }
        }

        public static boolean set(long j2) {
            if (sInputBlocking) {
                Log.rm("DlnaInputBlock", "InputBlock");
                return false;
            }
            sInputBlocking = true;
            Timer.startTimer(RELEASE_INPUT_BLOCKING_TIMER, j2, sReleaseInputBlock);
            return true;
        }
    }

    private boolean isButtonChanged(boolean z) {
        boolean z3 = true;
        if (!isDlnaButtonInflated()) {
            return true;
        }
        if (this.mDlnaButton.getVisibility() != 0) {
            z3 = false;
        }
        return z3 ^ z;
    }

    private boolean isDlnaButtonInflated() {
        if (this.mDlnaButton != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDlnaButton$0(boolean z) {
        if (!this.mModel.isViewConfirmed() || !isButtonChanged(z)) {
            StringCompat stringCompat = this.TAG;
            Log.rm(stringCompat, "updateDlnaButton skip {" + z + "}");
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.rm(stringCompat2, "updateDlnaButton {" + z + "}");
        if (!z) {
            setDlnaButtonEnabled(false);
        } else if (isDlnaButtonInflated()) {
            setDlnaButtonEnabled(true);
        } else {
            this.mActionInvoker.invoke(ViewerAction.DLNA_UI_INFLATE, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onButtonClicked(View view) {
        if (InputBlock.set(2000)) {
            this.mActionInvoker.invoke(ViewerAction.CONNECT_ORIGINAL_CONTENTS, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onDlnaButtonView(Object... objArr) {
        View findViewById = objArr[0].findViewById(R.id.dlna_button);
        this.mDlnaButton = findViewById;
        findViewById.setOnClickListener(new C0496a(24, this));
        updateTouchArea();
    }

    private void setDlnaButtonEnabled(boolean z) {
        if (isDlnaButtonInflated()) {
            StringCompat stringCompat = this.TAG;
            Log.rm(stringCompat, "setDlnaButtonEnabled {" + z + "}");
            if (z) {
                SimpleAnimator.setVisibility(this.mDlnaButton, 0, (int) MOCRLang.KHMER);
            } else {
                ViewUtils.setVisibility(this.mDlnaButton, 8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateDlnaButton(Object... objArr) {
        ThreadUtil.postOnUiThread(new c((Object) this, objArr[0].booleanValue(), 28));
    }

    private void updateTouchArea() {
        ViewUtils.setTouchAreaComposite(this.mDlnaButton, R.dimen.decor_button_touch_area);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.DLNA_UI_VIEW, new j(this, 0));
        this.mActionInvoker.add(ViewerAction.UPDATE_DLNA_BUTTON, new j(this, 1));
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateTouchArea();
    }

    public void onViewDetached() {
        super.onViewDetached();
        setDlnaButtonEnabled(false);
    }
}
