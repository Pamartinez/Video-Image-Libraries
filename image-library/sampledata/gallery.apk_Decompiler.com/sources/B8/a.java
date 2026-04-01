package B8;

import com.samsung.android.gallery.module.album.AlbumNewLabelUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumNewLabelUpdater e;

    public /* synthetic */ a(AlbumNewLabelUpdater albumNewLabelUpdater, int i2) {
        this.d = i2;
        this.e = albumNewLabelUpdater;
    }

    public final void run() {
        int i2 = this.d;
        AlbumNewLabelUpdater albumNewLabelUpdater = this.e;
        switch (i2) {
            case 0:
                albumNewLabelUpdater.lambda$refresh$1();
                return;
            default:
                albumNewLabelUpdater.lambda$refresh$2();
                return;
        }
    }
}
