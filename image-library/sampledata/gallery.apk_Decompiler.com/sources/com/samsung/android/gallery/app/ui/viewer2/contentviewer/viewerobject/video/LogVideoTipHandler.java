package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import B7.d;
import Fa.F;
import Fb.h;
import H3.l;
import H7.q;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.widget.tip.PopupTipCompat;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LogVideoTipHandler extends ViewerObject implements FragmentLifeCycle {
    private PopupTipCompat mPopupTip;

    private boolean checkLogVideoTipPreference(int i2) {
        if (PreferenceCache.LogVideoTipCount.getInt() < i2) {
            return true;
        }
        return false;
    }

    private int getDimensionPixelOffset(int i2) {
        Resources resources;
        Activity activity = this.mModel.getActivity();
        if (activity != null) {
            resources = activity.getResources();
        } else {
            resources = null;
        }
        if (resources != null) {
            return resources.getDimensionPixelOffset(i2);
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int getPopupTipTopMargin() {
        int i2;
        Activity activity = this.mModel.getActivity();
        int statusBarHeight = this.mModel.getSystemUi().getStatusBarHeight(activity);
        if (this.mModel.getContainerModel().isTableMode()) {
            i2 = 0;
        } else {
            i2 = SystemUi.getToolBarHeightWithPadding(activity);
        }
        return statusBarHeight + i2 + getDimensionPixelOffset(R.dimen.video_log_video_tip_top_margin);
    }

    private boolean isLogVideoTipAlreadyShown() {
        Boolean bool = (Boolean) this.mModel.getBlackboard().read("log_video_tip_shown");
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean isShowLogVideoTip() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null || !mediaItem.isLogVideo() || LocationKey.isColorCorrectView(this.mModel.getContainerModel().getLocationKey()) || isLogVideoTipAlreadyShown()) {
            return false;
        }
        if (checkLogVideoTipPreference(3)) {
            return true;
        }
        this.mModel.getBlackboard().publish("log_video_tip_shown", Boolean.TRUE);
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showLogVideoTip$0() {
        this.mPopupTip = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showLogVideoTip$1() {
        View view = this.mModel.getContainerModel().getView();
        if (view == null) {
            Log.e(this.TAG, "failed to show popup tip, view is null");
            return;
        }
        updateLogVideoTipPreference();
        this.mPopupTip = new PopupTipCompat((ViewGroup) view, R.layout.viewer_log_video_guide_layout, R.id.log_video_guide_main).setMargin((IntSupplier) null, new q(0, this), (IntSupplier) null, (IntSupplier) null).setIgnoreRootViewTouch(true).setDismissListener(new h(14, this)).build().show();
    }

    /* access modifiers changed from: private */
    public void onVideoStarted(Object... objArr) {
        if (isShowLogVideoTip()) {
            showLogVideoTip();
        }
    }

    private void refresh() {
        Optional.ofNullable(this.mPopupTip).ifPresent(new F(24));
    }

    private void showLogVideoTip() {
        this.mThread.runOnUiThread(new l(4, this));
    }

    private void updateLogVideoTipPreference() {
        PreferenceCache.LogVideoTipCount.incrementAndGet();
        this.mModel.getBlackboard().publish("log_video_tip_shown", Boolean.TRUE);
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new d(5, this));
    }

    public void onConfigurationChanged(Configuration configuration) {
        refresh();
    }

    public void onViewDetached() {
        super.onViewDetached();
        Optional.ofNullable(this.mPopupTip).ifPresent(new F(22));
        this.mPopupTip = null;
    }
}
