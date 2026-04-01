package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ImageTranscodeCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConvertHEIFToJPEGMenuItem extends ViewerMenuItem {
    public ConvertHEIFToJPEGMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.convert_from_heif_to_jpeg);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isLocal() || !mediaItem.isHeif()) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null || TextUtils.isEmpty(currentItem.getPath())) {
            Log.d(this.TAG, "ConvertHEIFToJPEGMenuItem failed: null item");
            return false;
        }
        new ImageTranscodeCmd().execute(this.mEventContext, currentItem.getPath(), new FileNameBuilder(currentItem.getPath()).setExtension("jpg").buildUnique());
        return true;
    }

    public void setMenuAttribute() {
        exclude("location://mtp/fileList", "location://trash", "location://LongExposure", "location://AllDayTimeLapse").validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new a(9));
    }
}
