package O5;

import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingHeaderViewDelegateV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingHeaderViewDelegateV2 e;
    public final /* synthetic */ String f;

    public /* synthetic */ a(SharingHeaderViewDelegateV2 sharingHeaderViewDelegateV2, String str, int i2) {
        this.d = i2;
        this.e = sharingHeaderViewDelegateV2;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setPeriodToViewAsync$2(this.f);
                return;
            default:
                this.e.lambda$setPeriodToViewAsync$1(this.f);
                return;
        }
    }
}
