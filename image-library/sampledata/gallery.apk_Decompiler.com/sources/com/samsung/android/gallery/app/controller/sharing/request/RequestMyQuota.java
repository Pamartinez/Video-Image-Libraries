package com.samsung.android.gallery.app.controller.sharing.request;

import Ba.h;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestMyQuota extends AbsRequest {
    public RequestMyQuota(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestQuota$0(Long l, Long l8) {
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("requestQuota[Me] : ");
        sb2.append(Logger.getEncodedString("totalUsage [" + l + "] limit [" + l8 + "]"));
        Log.sh(str, sb2.toString());
        this.mBlackboard.post("command://SharingUpdateMyQuota", new Object[]{l, l8});
    }

    public int getNetworkErrorStringId() {
        return R.string.check_network_connection_description;
    }

    public void request() {
        requestQuota();
    }

    public void requestQuota() {
        Log.sh(this.TAG, "requestQuota[Me] called");
        MdeSharingHelper.getMyQuota(new h(14, this));
    }
}
