package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingPicturesPresenter e;

    public /* synthetic */ k(SharingPicturesPresenter sharingPicturesPresenter, int i2) {
        this.d = i2;
        this.e = sharingPicturesPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SharingPicturesPresenter sharingPicturesPresenter = this.e;
        switch (i2) {
            case 0:
                sharingPicturesPresenter.onFamilyGroupMemberInfoChanged(obj, bundle);
                return;
            case 1:
                sharingPicturesPresenter.onViewChanged(obj, bundle);
                return;
            case 2:
                sharingPicturesPresenter.syncSharedAlbum(obj, bundle);
                return;
            case 3:
                sharingPicturesPresenter.updateGroupMember(obj, bundle);
                return;
            default:
                sharingPicturesPresenter.exit(obj, bundle);
                return;
        }
    }
}
