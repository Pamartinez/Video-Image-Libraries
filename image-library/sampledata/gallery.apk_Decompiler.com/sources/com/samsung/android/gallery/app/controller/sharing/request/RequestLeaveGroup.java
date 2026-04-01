package com.samsung.android.gallery.app.controller.sharing.request;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RequestLeaveGroup extends AbsRequest {
    public RequestLeaveGroup(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$0(String str, int i2) {
        if (!MdeResultCode.isSuccess(i2)) {
            String str2 = this.TAG;
            Log.sh(str2, "Failed to leave group: " + str);
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, i2, (int) R.string.could_not_remove_shared_album);
        }
    }

    public int getNetworkErrorStringId() {
        return R.string.could_not_remove_shared_album_network_status;
    }

    public void request() {
        String str;
        Object obj = this.mParams[1];
        if (obj != null) {
            str = obj.toString();
        } else {
            str = "";
        }
        if (!str.isEmpty()) {
            MdeGroupHelper.requestLocalGroupLeave(str, new b(this, str));
        }
    }
}
