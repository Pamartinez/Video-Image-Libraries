package com.samsung.android.gallery.app.ui.viewer2.menu;

import A4.C0385u;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.account.AwesomeIntelligenceServiceManager;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AwesomeIntelligenceMenuItem extends ViewerMenuItem {
    private static final String[] EXCLUDED_LOCATION_KEYS = {"location://sharing/albums/fileList", "location://trash", "location://family/shared/trash", "location://mtp/fileList", "location://dynamicViewList", "location://AllDayTimeLapse", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView"};

    public AwesomeIntelligenceMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.intelligent_features);
    }

    private void executeInternal() {
        this.mActionInvoker.invoke(ViewerAction.CLEAR_INPUT_BLOCK, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.EXECUTE_AWESOME_INTELLIGENCE, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0() {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null || currentItem.isCloudOnly() || currentItem.isVideo()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !isFlipCover();
    }

    public boolean onMenuSelectInternal(View view) {
        if (!isDim()) {
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_FS_GEN_EDIT.toString());
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.RetailMode)) {
                executeInternal();
                return false;
            } else if (AwesomeIntelligenceServiceManager.getInstance().needToShowAiIntroduction(this.mEventContext.getContext())) {
                AwesomeIntelligenceServiceManager.getInstance().startActivity(this.mEventContext.getActivity());
                return false;
            } else if (!SamsungAccountManager.isSamsungAiSupportAccount(this.mEventContext.getContext())) {
                Log.d(this.TAG, "fail to execute. restricted account");
                AwesomeIntelligenceServiceManager.getInstance().showRestrictedAccountToast(this.mEventContext.getContext());
                return false;
            } else {
                executeInternal();
                return false;
            }
        } else {
            Utils.showToast(this.mEventContext.getContext(), (int) R.string.intelligent_features_only_work);
            return false;
        }
    }

    public void setMenuAttribute() {
        setFastOptionMenu().setShowAsActionFlag(2).setIconResId(R.drawable.gallery_ic_detail_intelligence);
        setEnabledDimCondition(new C0385u(9, this)).exclude(EXCLUDED_LOCATION_KEYS).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new b(this, 0));
    }
}
