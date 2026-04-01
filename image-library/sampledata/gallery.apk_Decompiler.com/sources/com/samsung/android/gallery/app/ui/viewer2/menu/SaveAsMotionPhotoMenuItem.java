package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.MediaCaptureCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.abstraction.MediaCaptureMode;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveAsMotionPhotoMenuItem extends ViewerMenuItem {
    public SaveAsMotionPhotoMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.save_as_video);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !Features.isEnabled(Features.IS_GED);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$2(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isMotionPhoto()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$4(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isCloudOnly(mediaItem);
    }

    public boolean onMenuSelectInternal(View view) {
        AnalyticsEventId analyticsEventId;
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "SaveAsMotionPhotoMenuItem Menu Select failed: null item");
            return false;
        }
        this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_STOP, new Object[0]);
        new MediaCaptureCmd().execute(this.mEventContext, currentItem, ConvertingUsageType.NONE, MediaCaptureMode.NONE);
        if (MediaItemUtil.getMotionPhotoViewMode(currentItem) == MotionPhotoViewMode.BOOMERANG) {
            analyticsEventId = AnalyticsEventId.EVENT_MOTION_PHOTO_SAVE_AS_BOOMERANG;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_MOTION_PHOTO_SAVE_AS_SLOW_MO;
        }
        postAnalyticsLog(analyticsEventId.toString());
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://trash", "location://family/shared/trash", "location://mtp/fileList", "location://LongExposure").validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(new l(0)).validate(new l(1)).validate(new l(2)).validate(new l(3)).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new l(4));
    }
}
