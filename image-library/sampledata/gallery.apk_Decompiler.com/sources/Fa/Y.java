package Fa;

import android.os.Bundle;
import com.samsung.android.gallery.settings.ui.SharingNotificationPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Y implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingNotificationPresenter e;

    public /* synthetic */ Y(SharingNotificationPresenter sharingNotificationPresenter, int i2) {
        this.d = i2;
        this.e = sharingNotificationPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SharingNotificationPresenter sharingNotificationPresenter = this.e;
        switch (i2) {
            case 0:
                sharingNotificationPresenter.onSharingNotificationChanged(obj, bundle);
                return;
            case 1:
                sharingNotificationPresenter.onSharingAlbumNotificationChanged(obj, bundle);
                return;
            case 2:
                sharingNotificationPresenter.onSharingPostNotificationChanged(obj, bundle);
                return;
            default:
                sharingNotificationPresenter.onSharingMemberNotificationChanged(obj, bundle);
                return;
        }
    }
}
