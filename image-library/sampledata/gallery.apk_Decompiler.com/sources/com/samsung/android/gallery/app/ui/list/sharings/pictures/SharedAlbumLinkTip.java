package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import Fb.k;
import M5.a;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.widget.SemTipPopup;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedAlbumLinkTip {
    private static final int ALBUM_LINK_TIP_TIMER = Timer.getTimerId();
    private SemTipPopup mTip;
    private final IPicturesView mView;

    public SharedAlbumLinkTip(IPicturesView iPicturesView) {
        this.mView = iPicturesView;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showAlbumLinkTip$1(int i2) {
        dismiss(true);
    }

    /* access modifiers changed from: private */
    /* renamed from: showAlbumLinkTip */
    public void lambda$show$0(View view) {
        if (this.mView.isViewActive()) {
            SemTipPopup semTipPopup = new SemTipPopup(view);
            this.mTip = semTipPopup;
            semTipPopup.setMessage(AppResources.getString(R.string.sharing_album_link_tip_description));
            this.mTip.setExpanded(true);
            this.mTip.show(-1);
            Timer.startTimer(ALBUM_LINK_TIP_TIMER, 7000, new k(2, this));
            PreferenceCache.SharedAlbumLinkTip.setBoolean(false);
        }
    }

    public void dismiss(boolean z) {
        if (this.mTip != null) {
            Timer.stopTimer(ALBUM_LINK_TIP_TIMER);
            this.mTip.dismiss(z);
            this.mTip = null;
        }
    }

    public void onConfigurationChanged(Toolbar toolbar) {
        SemTipPopup semTipPopup = this.mTip;
        if (semTipPopup != null && semTipPopup.isShowing()) {
            dismiss(false);
            show(toolbar, true);
        }
    }

    public void show(Toolbar toolbar, boolean z) {
        if (toolbar == null) {
            Log.e("SharedAlbumLinkTip", "toolbar is null, stop [show tip] operation");
            return;
        }
        View findViewById = toolbar.findViewById(R.id.sesl_action_bar_overflow_button);
        if (findViewById == null) {
            return;
        }
        if (PreferenceCache.SharedAlbumLinkTip.getBoolean() || z) {
            findViewById.postDelayed(new a(25, this, findViewById), 200);
        }
    }
}
