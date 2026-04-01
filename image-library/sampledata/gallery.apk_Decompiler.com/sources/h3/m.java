package H3;

import com.samsung.android.gallery.app.controller.album.ReorderAlbumCmd;
import com.samsung.android.gallery.app.controller.album.UpdateOrderCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements UpdateOrderCmd.OrderUpdateCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ReorderAlbumCmd f2336a;

    public /* synthetic */ m(ReorderAlbumCmd reorderAlbumCmd) {
        this.f2336a = reorderAlbumCmd;
    }

    public final void onOrderUpdated() {
        this.f2336a.reloadAlbum();
    }
}
