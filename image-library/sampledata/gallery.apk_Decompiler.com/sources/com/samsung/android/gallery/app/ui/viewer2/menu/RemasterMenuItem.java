package com.samsung.android.gallery.app.ui.viewer2.menu;

import Q7.h;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.OnDemandRemasterViewerCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterMenuItem extends ViewerMenuItem {
    public RemasterMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.remaster_picture);
    }

    /* access modifiers changed from: private */
    public void invokeRemasterSelected() {
        ThreadUtil.postOnUiThread(new h(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invokeRemasterSelected$2() {
        this.mActionInvoker.invoke(ViewerAction.REMASTER_VIEW_SELECTED, Boolean.FALSE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !Features.isEnabled(Features.IS_REPAIR_MODE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return supportedRemaster(mediaItem);
    }

    private boolean supportImage(MediaItem mediaItem) {
        boolean z;
        if (!PreferenceFeatures.OneUi6x.IS_ONE_UI_60 || !mediaItem.isBmp()) {
            z = false;
        } else {
            z = true;
        }
        if (mediaItem == null || mediaItem.isRemasterSaved() || ViewerMenuItem.hasPortraitChanged(mediaItem) || (!mediaItem.isJpeg() && !mediaItem.isPng() && !mediaItem.isHeif() && !z)) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "remaster Menu Select failed: null item");
            return false;
        } else if (ViewerMenuItem.isLowStorage()) {
            Utils.showToast(this.mEventContext.getContext(), (int) R.string.operation_failed_low_storage, 1);
            Log.w(this.TAG, "Couldn't execute remaster viewer by low storage");
            return false;
        } else {
            new OnDemandRemasterViewerCmd().execute(this.mEventContext, currentItem, Long.valueOf(MediaItemSuggest.getRevitalizedType(currentItem)), new h(this, 0), Boolean.FALSE);
            return true;
        }
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash").validate(ViewerMenuItem.IS_NOT_UPSM).validate(new c(17)).validate(ViewerMenuItem.IS_NOT_VIDEO).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new b(this, 16));
    }

    public boolean supportedRemaster(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER) || !supportImage(mediaItem)) {
            return false;
        }
        return true;
    }

    public RemasterMenuItem(EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        super(eventContext, actionInvoker, i2);
    }
}
