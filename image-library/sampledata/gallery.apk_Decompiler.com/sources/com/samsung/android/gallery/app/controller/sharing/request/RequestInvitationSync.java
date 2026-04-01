package com.samsung.android.gallery.app.controller.sharing.request;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestInvitationSync extends AbsRequest {
    public RequestInvitationSync(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    public void onPostExecute(boolean z) {
        super.onPostExecute(z);
    }

    public void request() {
        MdeGroupHelper.requestMyInvitationList();
    }
}
