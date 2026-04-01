package R5;

import com.samsung.android.gallery.app.ui.list.sharings.storage.SharingStorageUseFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingStorageUseFragment e;

    public /* synthetic */ a(SharingStorageUseFragment sharingStorageUseFragment, int i2) {
        this.d = i2;
        this.e = sharingStorageUseFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SharingStorageUseFragment sharingStorageUseFragment = this.e;
        long[] jArr = (long[]) obj;
        switch (i2) {
            case 0:
                sharingStorageUseFragment.lambda$updateMyQuotaValues$1(jArr);
                return;
            default:
                sharingStorageUseFragment.lambda$updateFamilyQuotaValues$2(jArr);
                return;
        }
    }
}
