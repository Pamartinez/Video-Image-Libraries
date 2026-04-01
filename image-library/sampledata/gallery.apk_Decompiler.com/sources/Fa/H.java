package Fa;

import com.samsung.android.gallery.settings.ui.LabsFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class H implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsFragment e;

    public /* synthetic */ H(LabsFragment labsFragment, int i2) {
        this.d = i2;
        this.e = labsFragment;
    }

    public final void run() {
        int i2 = this.d;
        LabsFragment labsFragment = this.e;
        switch (i2) {
            case 0:
                labsFragment.lambda$onHeapDumpClicked$26();
                return;
            default:
                labsFragment.lambda$onPrivateAlbumRestoreClicked$31();
                return;
        }
    }
}
