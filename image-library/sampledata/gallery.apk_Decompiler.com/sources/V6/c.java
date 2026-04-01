package V6;

import com.samsung.android.gallery.app.ui.map.MapSnapshotPublisher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MapSnapshotPublisher e;
    public final /* synthetic */ double f;
    public final /* synthetic */ double g;

    public /* synthetic */ c(MapSnapshotPublisher mapSnapshotPublisher, double d2, double d3, int i2) {
        this.d = i2;
        this.e = mapSnapshotPublisher;
        this.f = d2;
        this.g = d3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onSnapShotRequest$0(this.f, this.g);
                return;
            default:
                this.e.lambda$checkChinaMapReady$1(this.f, this.g);
                return;
        }
    }
}
