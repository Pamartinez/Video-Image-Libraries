package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import Fa.F;
import H3.l;
import H7.m;
import H7.n;
import H7.o;
import H7.p;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.dialog.OverlayTipCompat;
import com.samsung.android.gallery.widget.tip.PopupTipCompat;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantSlowMoTipHandler extends ViewerObject implements FragmentLifeCycle {
    private static final boolean SUPPORT_INSTANT_SLOW_MO_ON_SHARE_SHEET = Features.isEnabled(Features.SUPPORT_INSTANT_SLOW_MO_ON_SHARE_SHEET);
    private PopupTipCompat mEditSharePopupTip;
    private boolean mInstantSlowMoPlayOn;
    private OverlayTipCompat mOverlayTip;
    private PopupTipCompat mPopupTip;

    private boolean checkEnableGuideHint(int i2) {
        if (!BottomSheetState.isClosed(this.mModel) || LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey()) || !InstantSlowMoUtils.checkInstantSlowMoGuidePreference(i2)) {
            return false;
        }
        return true;
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
    public int getOptionsPopupTipBottomMargin() {
        if (this.mModel.getSystemUi().isPortrait()) {
            return this.mModel.getContainerModel().getSystemInsets().bottom + getDimensionPixelOffset(R.dimen.fast_menu_imageview_height);
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int getOptionsPopupTipEndMargin() {
        if (this.mModel.getSystemUi().isPortrait()) {
            return getDimensionPixelOffset(R.dimen.video_instant_slow_mo_guide_text_margin_horizontal);
        }
        return RectUtils.getEnd(this.mModel.getContainerModel().getSystemInsets()) + getDimensionPixelOffset(R.dimen.video_instant_slow_mo_guide_text_margin_end);
    }

    /* access modifiers changed from: private */
    public int getOptionsPopupTipGravity() {
        if (this.mModel.getSystemUi().isPortrait()) {
            return 80;
        }
        return 8388661;
    }

    /* access modifiers changed from: private */
    public int getOptionsPopupTipStartMargin() {
        if (this.mModel.getSystemUi().isPortrait()) {
            return getDimensionPixelOffset(R.dimen.video_instant_slow_mo_guide_text_margin_horizontal);
        }
        return RectUtils.getStart(this.mModel.getContainerModel().getSystemInsets()) + getDimensionPixelOffset(R.dimen.video_instant_slow_mo_guide_text_margin_start);
    }

    /* access modifiers changed from: private */
    public int getOptionsPopupTipTopMargin() {
        int i2 = 0;
        if (this.mModel.getSystemUi().isPortrait()) {
            return 0;
        }
        Activity activity = this.mModel.getActivity();
        int statusBarHeight = this.mModel.getSystemUi().getStatusBarHeight(activity);
        if (!this.mModel.getContainerModel().isTableMode()) {
            i2 = SystemUi.getToolBarHeightWithPadding(activity);
        }
        return statusBarHeight + i2;
    }

    /* access modifiers changed from: private */
    public int getPopupTipHorizontalMargin() {
        float f;
        float f5 = (float) this.mModel.getActivity().getResources().getDisplayMetrics().widthPixels;
        if (this.mModel.getSystemUi().isPortrait()) {
            f = 0.17f;
        } else {
            f = 0.4f;
        }
        return ((int) (f5 * f)) / 2;
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
        return statusBarHeight + i2 + getDimensionPixelOffset(R.dimen.video_instant_slow_mo_guide_text_margin_top);
    }

    /* access modifiers changed from: private */
    public void handleInstantSlowMoPlay(Object[] objArr) {
        boolean z = this.mInstantSlowMoPlayOn;
        boolean booleanValue = objArr[0].booleanValue();
        this.mInstantSlowMoPlayOn = booleanValue;
        if (booleanValue) {
            this.mModel.setInstantSlowMoOptionsTipRecognizeEnabled(true);
        } else if (z) {
            showOptionsTip();
        }
    }

    private boolean isActivityDestroyed() {
        if (this.mModel.getActivity() == null || this.mModel.getActivity().isDestroyed()) {
            return true;
        }
        return false;
    }

    private boolean isTipsAlreadyShown(String str) {
        Boolean bool = (Boolean) this.mModel.getBlackboard().read(str);
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showInstantSlowMoEditAndShareGuide$3() {
        this.mEditSharePopupTip = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showInstantSlowMoTip$0(DialogInterface dialogInterface) {
        this.mOverlayTip = null;
        if (!isActivityDestroyed()) {
            this.mActionInvoker.invoke(ViewerAction.PLAY_PAUSE_CLICKED, Boolean.TRUE);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showInstantSlowMoTip$1() {
        this.mPopupTip = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showInstantSlowMoTip$2() {
        if (this.mOverlayTip == null && checkEnableGuideHint(1)) {
            Log.d(this.TAG, "show overlay tip");
            InstantSlowMoUtils.updateInstantSlowMoTipShown(this.mModel.getBlackboard());
            this.mActionInvoker.invoke(ViewerAction.PLAY_PAUSE_CLICKED, Boolean.FALSE);
            this.mOverlayTip = new OverlayTipCompat(this.mModel.getContext(), R.layout.instant_slow_mo_overlay_tip).setOnDismissListener(new o(0, this)).build().show();
        } else if (checkEnableGuideHint(3)) {
            View view = this.mModel.getContainerModel().getView();
            if (view == null) {
                Log.e(this.TAG, "failed to show popup tip, view is null");
                return;
            }
            Log.d(this.TAG, "show popup tip");
            InstantSlowMoUtils.updateInstantSlowMoTipShown(this.mModel.getBlackboard());
            this.mPopupTip = new PopupTipCompat((ViewGroup) view, R.layout.viewer_instant_slow_mo_guide_layout, R.id.instant_slow_mo_guide_main).setMargin(new m(this, 2), new m(this, 3), new m(this, 2), (IntSupplier) null).setIgnoreRootViewTouch(true).setDismissListener(new n(this, 1)).build().show();
        }
    }

    /* access modifiers changed from: private */
    public void onVideoStarted(Object... objArr) {
        if (supportInstantSlowMoTip()) {
            showInstantSlowMoTip();
        }
    }

    private void refresh() {
        if (this.mModel.getStateHelper().supportInstantSlowMoPlay(this.mModel.getMediaItem())) {
            Optional.ofNullable(this.mOverlayTip).ifPresent(new F(23));
            Optional.ofNullable(this.mPopupTip).ifPresent(new F(24));
            Optional.ofNullable(this.mEditSharePopupTip).ifPresent(new F(24));
            return;
        }
        Optional.ofNullable(this.mOverlayTip).ifPresent(new F(21));
        Optional.ofNullable(this.mPopupTip).ifPresent(new F(22));
        Optional.ofNullable(this.mEditSharePopupTip).ifPresent(new F(22));
    }

    private void showInstantSlowMoEditAndShareGuide() {
        View view = this.mModel.getContainerModel().getView();
        if (view == null) {
            Log.e(this.TAG, "failed to show popup tip, view is null");
            return;
        }
        this.mEditSharePopupTip = new PopupTipCompat((ViewGroup) view, R.layout.viewer_instant_slow_mo_options_popup_tip_layout, R.id.instant_slow_mo_popup_tip_main).setMargin(new m(this, 4), new m(this, 5), new m(this, 6), new m(this, 0)).setGravity(new m(this, 1)).setViewIdListDismissWhenClick(new int[]{R.id.tip_root_view, R.id.instant_slow_mo_popup_tip_main}).setDismissAnimationEnable(true).setDismissListener(new n(this, 0)).build().show();
        InstantSlowMoUtils.updateInstantSlowMoOptionsTipShown(this.mModel.getBlackboard());
    }

    private void showInstantSlowMoEditGuide() {
        ViewerMenuItem viewerMenuItem;
        ConcurrentHashMap<Class<?>, ViewerMenuItem> enabledMenuMap = this.mModel.getContainerModel().getEnabledMenuMap();
        if (enabledMenuMap != null) {
            viewerMenuItem = enabledMenuMap.get(EditMenuItem.class);
        } else {
            viewerMenuItem = null;
        }
        if (viewerMenuItem != null) {
            Log.d(this.TAG, "show edit tip");
            this.mActionInvoker.invoke(ViewerAction.SHOW_MENU_TIP_POPUP, Integer.valueOf(viewerMenuItem.getMenuId()), AppResources.getString(R.string.tap_edit_icon_to_permanently_add_slow_mo));
            InstantSlowMoUtils.updateInstantSlowMoOptionsTipShown(this.mModel.getBlackboard());
        }
    }

    private void showInstantSlowMoTip() {
        this.mThread.runOnUiThread(new l(3, this));
    }

    private void showOptionsTip() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO_SAVE_CLIP && !isTipsAlreadyShown("instant_slow_mo_option_menu_tip_shown")) {
            if (PickerUtil.isPickerMode(this.mModel.getBlackboard())) {
                Log.d(this.TAG, "showOptionsTip : in picker mode");
            } else if (supportInstantSlowMoEditAndShareTip()) {
                showInstantSlowMoEditAndShareGuide();
            } else if (supportInstantSlowMoEditTip()) {
                showInstantSlowMoEditGuide();
            }
        }
    }

    private boolean supportInstantSlowMoEditAndShareTip() {
        if (!SUPPORT_INSTANT_SLOW_MO_ON_SHARE_SHEET) {
            return false;
        }
        if (InstantSlowMoUtils.checkInstantSlowMoEditAndShareGuidePreference()) {
            return true;
        }
        this.mModel.getBlackboard().publish("instant_slow_mo_option_menu_tip_shown", Boolean.TRUE);
        return false;
    }

    private boolean supportInstantSlowMoEditTip() {
        if (SUPPORT_INSTANT_SLOW_MO_ON_SHARE_SHEET) {
            return false;
        }
        if (InstantSlowMoUtils.checkInstantSlowMoEditGuidePreference()) {
            return true;
        }
        this.mModel.getBlackboard().publish("instant_slow_mo_option_menu_tip_shown", Boolean.TRUE);
        return false;
    }

    private boolean supportInstantSlowMoTip() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if ((mediaItem != null && mediaItem.isLogVideo()) || !this.mModel.getStateHelper().supportInstantSlowMoPlay(mediaItem)) {
            return false;
        }
        if (mediaItem == null || !mediaItem.isMotionPhoto()) {
            if (isTipsAlreadyShown("instant_slow_mo_tip_shown")) {
                return false;
            }
            if (InstantSlowMoUtils.checkInstantSlowMoGuidePreference(3)) {
                return true;
            }
            this.mModel.getBlackboard().publish("instant_slow_mo_tip_shown", Boolean.TRUE);
            return false;
        } else if (!this.mModel.getMotionPlayViewer().isOriginVideo || !this.mModel.isPlaying()) {
            return false;
        } else {
            return true;
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new p(this, 0));
        this.mActionInvoker.add(ViewerAction.INSTANT_SLOW_MO_PLAY, new p(this, 1));
    }

    public void onConfigurationChanged(Configuration configuration) {
        refresh();
    }

    public void onViewDetached() {
        super.onViewDetached();
        Optional.ofNullable(this.mOverlayTip).ifPresent(new F(21));
        Optional.ofNullable(this.mPopupTip).ifPresent(new F(22));
        this.mOverlayTip = null;
        this.mPopupTip = null;
        this.mEditSharePopupTip = null;
        this.mInstantSlowMoPlayOn = false;
        this.mModel.setInstantSlowMoOptionsTipRecognizeEnabled(false);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        Optional.ofNullable(this.mOverlayTip).ifPresent(new F(21));
    }
}
