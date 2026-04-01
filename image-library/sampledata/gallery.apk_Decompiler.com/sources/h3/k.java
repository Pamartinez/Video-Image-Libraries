package H3;

import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileOpCmd e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ String g;

    public /* synthetic */ k(FileOpCmd fileOpCmd, MediaItem mediaItem, String str, int i2) {
        this.d = i2;
        this.e = fileOpCmd;
        this.f = mediaItem;
        this.g = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$startRenameAlbumCmd$15(this.f, this.g);
                return;
            default:
                this.e.lambda$startAddToAlbumDialogCmd$8(this.f, this.g);
                return;
        }
    }
}
