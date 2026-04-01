package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsFastOptionMenuItem extends DetailsMenuItem {
    public DetailsFastOptionMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !isFlipCover();
    }

    public void setMenuAttribute() {
        boolean isFromUSB = LaunchIntent.isFromUSB(this.mEventContext.getBlackboard());
        if (DetailsMenuItem.SUPPORT_FAST_OPTION || isFromUSB) {
            setDetailsCommonAttribute();
            setFastOptionMenu().setShowAsActionFlag(2).setIconResId(R.drawable.gallery_ic_detail_info_i);
        } else {
            include("location://sharing/albums/fileList", "location://albums/private/fileList").setFastOptionMenu().setShowAsActionFlag(2).setIconResId(R.drawable.gallery_ic_detail_info_i);
        }
        validate(new b(this, 6));
    }
}
