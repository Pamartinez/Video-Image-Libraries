package H3;

import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileOpCmd e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ g(FileOpCmd fileOpCmd, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = fileOpCmd;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$startAddToAlbumDialogCmd$9(this.f);
                return;
            case 1:
                this.e.lambda$addItemsToAutoAlbum$11(this.f);
                return;
            default:
                this.e.lambda$startDragToAlbumDialogCmd$14(this.f);
                return;
        }
    }
}
