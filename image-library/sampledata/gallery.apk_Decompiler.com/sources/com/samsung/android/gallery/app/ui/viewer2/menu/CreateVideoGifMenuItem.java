package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.CreateVideoGifCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateVideoGifMenuItem extends ViewerMenuItem {
    public CreateVideoGifMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.create_gif);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !MediaItemUtil.isUsbFile(mediaItem);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$3(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isCloudOnly(mediaItem);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        Context context = this.mEventContext.getContext();
        if (currentItem == null || context == null) {
            return false;
        }
        CreateVideoGifCmd createVideoGifCmd = new CreateVideoGifCmd();
        EventContext eventContext = this.mEventContext;
        createVideoGifCmd.execute(eventContext, currentItem, eventContext.getEventContextData("app_transition_seek_position"));
        return false;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://dynamicViewList", "location://AllDayTimeLapse", "location://trash", "location://superSlowViewList", "location://highlightViewList", "location://LongExposure", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new a(14)).validate(ViewerMenuItem.IS_NOT_SEP_LITE).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(new a(15)).validate(new a(16));
        validate(new a(17));
    }
}
