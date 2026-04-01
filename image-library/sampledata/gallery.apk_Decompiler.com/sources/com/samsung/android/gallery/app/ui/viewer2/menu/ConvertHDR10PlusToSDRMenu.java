package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ConvertHdrToSdrCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConvertHDR10PlusToSDRMenu extends ViewerMenuItem {
    public ConvertHDR10PlusToSDRMenu(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.convert_from_hdr10plus_to_sdr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return supportMode(mediaItem);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isCloudOnly(mediaItem);
    }

    public boolean onMenuSelectInternal(View view) {
        this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_STOP, new Object[0]);
        ConvertHdrToSdrCmd convertHdrToSdrCmd = new ConvertHdrToSdrCmd();
        EventContext eventContext = this.mEventContext;
        convertHdrToSdrCmd.execute(eventContext, eventContext.getCurrentItem());
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList").validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_APV).validate(new b(this, 3)).validate(new a(8));
    }

    public boolean supportMode(MediaItem mediaItem) {
        if (mediaItem == null || !RecordingMode.isHdr10Plus(mediaItem.getRecordingMode())) {
            return false;
        }
        return true;
    }

    public ConvertHDR10PlusToSDRMenu(EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        super(eventContext, actionInvoker, i2);
    }
}
