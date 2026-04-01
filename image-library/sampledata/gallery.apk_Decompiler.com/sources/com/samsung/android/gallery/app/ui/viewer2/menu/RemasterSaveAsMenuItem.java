package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.SaveAsRemasterCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterSaveAsMenuItem extends ViewerMenuItem {
    public RemasterSaveAsMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.save_as_copy);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "RemasterSaveAsMenuItem failed: null item");
            return false;
        }
        new SaveAsRemasterCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).include("location://revitalized/fileList", "location://revitalized/single").validate(ViewerMenuItem.IS_NOT_BROKEN).validate(new c(18)).validate(new c(19));
    }
}
