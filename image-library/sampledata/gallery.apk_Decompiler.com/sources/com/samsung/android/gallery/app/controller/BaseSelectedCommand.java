package com.samsung.android.gallery.app.controller;

import A4.A;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseSelectedCommand extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(EventContext eventContext, MediaItem[] mediaItemArr) {
        super.onPreExecute(eventContext, mediaItemArr);
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        if ((objArr == null || objArr.length == 0) && eventContext.isSelectionMode()) {
            Log.d(this.TAG, "onPreExecute", Integer.valueOf(eventContext.getSelectedItemCount()));
            loadAndExecute(eventContext, new A(12, (Object) this, (Object) eventContext));
            return;
        }
        super.onPreExecute(eventContext, objArr);
    }
}
