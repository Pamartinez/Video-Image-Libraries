package com.samsung.android.gallery.app.controller.sharing.request;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestInvitationAcceptance extends AbsRequest {
    public RequestInvitationAcceptance(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private void requestInvitationAcceptance() {
        this.mBlackboard.postEvent(EventMessage.obtain(6004));
        MdeGroupHelper.requestGroupInvitationAcceptance(this.mBlackboard, this.mAppContext, (String) this.mParams[1]);
    }

    public int getNetworkErrorStringId() {
        return R.string.sharing_invitations_check_your_network;
    }

    public void request() {
        requestInvitationAcceptance();
    }
}
