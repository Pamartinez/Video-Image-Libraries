package com.samsung.android.gallery.app.controller.sharing;

import N3.c;
import O3.y;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeNotificationManager;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShowSharingInvitationsJoinDialogCmd extends BaseCommand {
    private String mGroupId;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestJoinSharedAlbum$0(EventContext eventContext, RequestCmdType requestCmdType) {
        new RequestSharedAlbumCmd().execute(eventContext, requestCmdType, this.mGroupId);
    }

    /* access modifiers changed from: private */
    public void requestJoinSharedAlbum(EventContext eventContext, ArrayList<Object> arrayList) {
        RequestCmdType requestCmdType;
        if (arrayList == null || arrayList.isEmpty()) {
            requestCmdType = null;
        } else {
            requestCmdType = (RequestCmdType) arrayList.get(0);
        }
        Optional.ofNullable(requestCmdType).ifPresent(new c(18, this, eventContext));
        MdeNotificationManager.getInstance(getContext()).cancel(MediaItemMde.toUid(this.mGroupId));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        this.mGroupId = objArr[0];
        if (objArr.length > 1) {
            str = objArr[1];
        } else {
            str = "";
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SharedInvitation").appendArg("space_name", str).build()).setOnDataCompleteListener(new y(24, this)).start();
    }
}
