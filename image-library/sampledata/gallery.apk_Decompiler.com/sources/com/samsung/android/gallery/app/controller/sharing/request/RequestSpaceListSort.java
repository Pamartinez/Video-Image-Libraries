package com.samsung.android.gallery.app.controller.sharing.request;

import Qb.c;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.mde.MdeDataSyncHelper;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestSpaceListSort extends AbsRequest {
    public RequestSpaceListSort(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$0(String str) {
        String str2 = this.TAG;
        Log.sh(str2, "requestSpaceListSort result : " + str);
        refreshData();
    }

    private void refreshData() {
        MediaData mediaData = getEventContext().getMediaData();
        if (mediaData != null) {
            mediaData.reopen(getEventContext().getLocationKey());
        }
    }

    public void request() {
        Log.sh(this.TAG, "Sort space list");
        MdeDataSyncHelper.requestSpaceListSort((String) this.mParams[1], new c(22, this));
    }
}
