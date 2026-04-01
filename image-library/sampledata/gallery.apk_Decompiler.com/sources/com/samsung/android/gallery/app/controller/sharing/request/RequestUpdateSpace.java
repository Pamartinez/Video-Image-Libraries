package com.samsung.android.gallery.app.controller.sharing.request;

import N3.c;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestUpdateSpace extends AbsRequest {
    public RequestUpdateSpace(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestUpdateSpace$0(String str, Integer num) {
        String str2 = this.TAG;
        Log.sh(str2, "requestUpdateSpace result" + Logger.v(num));
        if (!MdeResultCode.isSuccess(num.intValue()) && str != null) {
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue(), (int) R.string.could_not_rename_shared_album);
        }
    }

    private void requestUpdateSpace() {
        Log.sh(this.TAG, "requestUpdateSpace called");
        Object[] objArr = this.mParams;
        String str = (String) objArr[2];
        MdeSharingHelper.requestSpaceUpdate((String) objArr[1], str, (String) objArr[3], new c(29, this, str));
    }

    public int getNetworkErrorStringId() {
        if (((String) this.mParams[2]) != null) {
            return R.string.could_not_rename_shared_album_network_status;
        }
        return -1;
    }

    public void onPostExecute(boolean z) {
        if (z) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
    }

    public void request() {
        requestUpdateSpace();
    }
}
