package R5;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.sharings.storage.SharingStorageUsePresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingStorageUsePresenter e;

    public /* synthetic */ c(SharingStorageUsePresenter sharingStorageUsePresenter, int i2) {
        this.d = i2;
        this.e = sharingStorageUsePresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SharingStorageUsePresenter sharingStorageUsePresenter = this.e;
        switch (i2) {
            case 0:
                sharingStorageUsePresenter.lambda$createSubscriberList$0(obj, bundle);
                return;
            default:
                sharingStorageUsePresenter.lambda$createSubscriberList$1(obj, bundle);
                return;
        }
    }
}
