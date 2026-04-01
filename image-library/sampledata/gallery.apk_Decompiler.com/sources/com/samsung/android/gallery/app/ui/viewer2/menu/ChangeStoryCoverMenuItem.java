package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.story.ChangeStoryCoverCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeStoryCoverMenuItem extends ViewerMenuItem {
    public ChangeStoryCoverMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.set_as_story_cover);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "ChangeStoryCover Menu Select failed: null item");
            return false;
        }
        new ChangeStoryCoverCmd().execute(this.mEventContext, currentItem, Boolean.FALSE);
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).validate(new a(5)).validate(new a(6));
    }
}
