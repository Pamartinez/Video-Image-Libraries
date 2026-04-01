package com.samsung.android.gallery.app.controller.sharing.request;

import M3.d;
import Qb.c;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.IntentExtra$InternalKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.mobileservice.social.group.Group;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestCreateFamilySpace extends AbsRequest {
    public RequestCreateFamilySpace(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private void composeUrisToAdd(Blackboard blackboard, Intent intent) {
        ArrayList<Uri> arrayList;
        MediaItem[] mediaItemArr = (MediaItem[]) blackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length <= 0) {
            arrayList = null;
        } else {
            arrayList = MdeAlbumHelper.convertMediaItemsToUris(mediaItemArr);
        }
        Optional.ofNullable(arrayList).ifPresent(new d(intent, 3));
    }

    private void createSpace(Intent intent) {
        try {
            getContext().startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.TAG, "Fail start service");
        }
    }

    private Intent getIntent(Blackboard blackboard) {
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.MdeSharingService");
        intent.setAction("com.samsung.android.gallery.app.service.MdeSharingService.REQUEST_CREATE_GROUP");
        intent.putExtra(IntentExtra$InternalKey.KEY_SHARED_GROUP_TYPE, Group.GROUP_TYPE_SA_FAMILY_GROUP);
        intent.putExtra("blackboard_name", blackboard.getName());
        intent.putExtra("sharedAlbumTitle", getContext().getString(R.string.shared_family_album));
        composeUrisToAdd(blackboard, intent);
        return intent;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$composeUrisToAdd$1(Intent intent, ArrayList arrayList) {
        intent.putExtra(IntentExtra$InternalKey.KEY_CREATE_GROUP_FOR_SHARE, !arrayList.isEmpty());
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$0(Blackboard blackboard) {
        createSpace(getIntent(blackboard));
    }

    public int getNetworkErrorStringId() {
        return R.string.could_not_create_shared_album_network_status;
    }

    public void request() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new c(17, this));
    }
}
