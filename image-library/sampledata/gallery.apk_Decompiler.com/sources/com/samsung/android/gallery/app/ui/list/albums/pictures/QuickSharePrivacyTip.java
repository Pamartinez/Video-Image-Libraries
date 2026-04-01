package com.samsung.android.gallery.app.ui.list.albums.pictures;

import Fb.k;
import H.a;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.widget.SemTipPopup;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickSharePrivacyTip {
    private static final int PRIVATE_TIP_TIMER = Timer.getTimerId();
    private SemTipPopup mTip;

    private boolean isShowing() {
        SemTipPopup semTipPopup = this.mTip;
        if (semTipPopup == null || !semTipPopup.isShowing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showTip$1(int i2) {
        this.mTip.dismiss(true);
    }

    /* access modifiers changed from: private */
    /* renamed from: showTip */
    public void lambda$show$0(View view) {
        SemTipPopup semTipPopup = new SemTipPopup(view);
        this.mTip = semTipPopup;
        semTipPopup.setMessage(AppResources.getString(R.string.view_files_received_using_private_sharing));
        this.mTip.setExpanded(true);
        this.mTip.show(-1);
        Timer.startTimer(PRIVATE_TIP_TIMER, 7000, new k(1, this));
    }

    public void onConfigurationChanged(Toolbar toolbar) {
        if (isShowing()) {
            Timer.stopTimer(PRIVATE_TIP_TIMER);
            this.mTip.dismiss(false);
            show(toolbar, true);
        }
    }

    public void show(Toolbar toolbar, boolean z) {
        if (toolbar == null) {
            Log.e("QuickSharePrivacyTip", "show failed, toolbar is null");
            return;
        }
        View findViewById = toolbar.findViewById(R.id.action_quick_share_privacy);
        if (findViewById == null) {
            Log.e("QuickSharePrivacyTip", "show failed, share privacy menu is null");
            return;
        }
        PreferenceCache preferenceCache = PreferenceCache.QuickSharePrivacyTip;
        if (!preferenceCache.getBoolean() || z) {
            preferenceCache.setBoolean(true);
            findViewById.postDelayed(new a(29, this, findViewById), 300);
        }
    }
}
