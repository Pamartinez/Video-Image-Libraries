package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JumpToTimelineMenuItem extends ViewerMenuItem {
    public JumpToTimelineMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.view_pictures_around_this_date);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (str == null || !str.startsWith("location://search/fileList")) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        if (this.mEventContext.getCurrentItem() == null) {
            Log.d(this.TAG, "null item");
            return false;
        }
        this.mActionInvoker.invoke(ViewerAction.CLEAR_INPUT_BLOCK, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.JUMP_TO_TIMELINE, new Object[0]);
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).validate(new c(6));
    }
}
