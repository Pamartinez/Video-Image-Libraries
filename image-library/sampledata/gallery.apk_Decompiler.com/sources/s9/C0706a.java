package s9;

import com.samsung.android.gallery.module.location.LocationUpdater;

/* renamed from: s9.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0706a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LocationUpdater e;

    public /* synthetic */ C0706a(LocationUpdater locationUpdater, int i2) {
        this.d = i2;
        this.e = locationUpdater;
    }

    public final void run() {
        int i2 = this.d;
        LocationUpdater locationUpdater = this.e;
        switch (i2) {
            case 0:
                locationUpdater.lambda$updateLocation29$0();
                return;
            default:
                locationUpdater.lambda$updateLocation28$1();
                return;
        }
    }
}
