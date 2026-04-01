package H3;

import com.samsung.android.gallery.app.controller.album.CreateAlbumAndMoveCmd;

/* renamed from: H3.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0397b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreateAlbumAndMoveCmd e;

    public /* synthetic */ C0397b(CreateAlbumAndMoveCmd createAlbumAndMoveCmd, int i2) {
        this.d = i2;
        this.e = createAlbumAndMoveCmd;
    }

    public final void run() {
        int i2 = this.d;
        CreateAlbumAndMoveCmd createAlbumAndMoveCmd = this.e;
        switch (i2) {
            case 0:
                createAlbumAndMoveCmd.moveToAlbum();
                return;
            default:
                createAlbumAndMoveCmd.lambda$moveToAlbum$1();
                return;
        }
    }
}
