package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.graphics.Point;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareHeifWithConversionCmd;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.externals.ShareVideoWithConversionCmd;
import com.samsung.android.gallery.app.controller.externals.ShareWithWebLinkCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareMenuItem extends ViewerMenuItem {
    private static boolean ENABLE_PPP = false;

    public ShareMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.share_short);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if ((mediaItem == null || mediaItem.isBroken()) && !MediaItemUtil.hasValidSize(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !MediaItemUtil.isUsbFile(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$2(MediaItem mediaItem, String str) {
        return !isFlipCover();
    }

    private void updateInstantSlowMoOptionGuideRecognizedStatus() {
        Boolean bool;
        if (Features.isEnabled(Features.SUPPORT_INSTANT_SLOW_MO_ON_SHARE_SHEET) && (bool = (Boolean) this.mEventContext.getEventContextData("is_instant_slow_mo_options_tip_recognize_enabled")) != null && bool.booleanValue()) {
            PreferenceCache.InstantSlowMoEditAndShareGuide.setBoolean(true);
        }
    }

    public boolean isRotateRecoveryRequired() {
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        View view2;
        ViewerAction viewerAction;
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "Share Menu Select failed: null item");
            return false;
        }
        publishPopoverInfo(getMenuId(), view);
        boolean z = PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET;
        if (z) {
            ActionInvoker actionInvoker = this.mActionInvoker;
            if (LocationKey.isSecondDepthGroupPanelView(this.mEventContext.getLocationKey())) {
                viewerAction = ViewerAction.PREPARE_SINGLE_TAKEN_SHARE_SHEET;
            } else {
                viewerAction = ViewerAction.PREPARE_SHARE_SHEET;
            }
            actionInvoker.invoke(viewerAction, new Object[0]);
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO) {
            updateInstantSlowMoOptionGuideRecognizedStatus();
        }
        if (MediaItemUtil.isUrlAvailable(currentItem)) {
            ShareWithWebLinkCmd shareWithWebLinkCmd = new ShareWithWebLinkCmd();
            EventContext eventContext = this.mEventContext;
            shareWithWebLinkCmd.execute(eventContext, currentItem, eventContext.getEventContextData("shareComponent"));
        } else if (SettingPreference.HeifAutoConv.isEnabled() && MediaItemUtil.isHeifWithAutoConvertOn(currentItem)) {
            new ShareHeifWithConversionCmd().execute(this.mEventContext, currentItem);
        } else if (MediaItemUtil.isHdr10plusWithAutoConvertOn(currentItem) || MediaItemUtil.isSlowMoConvertible(currentItem)) {
            new ShareVideoWithConversionCmd().execute(this.mEventContext, currentItem, ConvertingUsageType.COMMON_SHARE);
        } else if (DynamicViewData.of(currentItem).dynamicViewPlayInfo != null || MediaItemUtil.isHighLightClip(currentItem) || MediaItemUtil.isSuperSlowClip(currentItem) || LocationKey.isColorCorrectView(this.mEventContext.getLocationKey())) {
            executeMediaCaptureCmd(currentItem, ConvertingUsageType.COMMON_SHARE);
        } else if (!z || (view2 = (View) this.mEventContext.getEventContextData("app_transition_view")) == null) {
            BaseCommand addConfig = new ShareViaCmd().addConfig(1);
            EventContext eventContext2 = this.mEventContext;
            addConfig.execute(eventContext2, new MediaItem[]{currentItem}, eventContext2.getEventContextData("shareComponent"));
        } else {
            view2.setTransitionName("sem_shared_element_chooser_preview_image");
            this.mActionInvoker.invoke(ViewerAction.RESTORE_VIDEO_DEFAULT_PREVIEW, new Object[0]);
            BaseCommand addParameter = new ShareViaCmd().addConfig(1).addParameter("app_transition_view", view2);
            EventContext eventContext3 = this.mEventContext;
            addParameter.execute(eventContext3, new MediaItem[]{currentItem}, eventContext3.getEventContextData("shareComponent"));
            return true;
        }
        return true;
    }

    public void publishPopoverInfo(int i2, View view) {
        if (SystemUi.supportPopoverUi(this.mEventContext.getContext(), true)) {
            RectF viewRect = ViewUtils.getViewRect(view);
            PopoverHelper.publishPopoverShareInfo(this.mEventContext.getBlackboard(), new Point((int) viewRect.left, (int) viewRect.top));
        }
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_share).setFastOptionMenu().setShowAsActionFlag(2).exclude("location://mtp/fileList", "location://trash", "location://dynamicViewList", "location://AllDayTimeLapse", "location://LongExposure", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView", "location://private/trash").validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_NOT_DRM_WO_PRIVATE).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).setSupportPpp(ENABLE_PPP).validate(new l(6)).validate(new l(7)).validate(new b(this, 20));
    }
}
