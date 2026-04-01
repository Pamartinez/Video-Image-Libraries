package S3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.AddContentsToSharedAlbumCmd;
import java.util.ArrayList;

/* renamed from: S3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0409a implements DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AddContentsToSharedAlbumCmd e;

    public /* synthetic */ C0409a(AddContentsToSharedAlbumCmd addContentsToSharedAlbumCmd, int i2) {
        this.d = i2;
        this.e = addContentsToSharedAlbumCmd;
    }

    public final void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        AddContentsToSharedAlbumCmd addContentsToSharedAlbumCmd = this.e;
        switch (i2) {
            case 0:
                addContentsToSharedAlbumCmd.addToSharedAlbum(eventContext, arrayList);
                return;
            default:
                addContentsToSharedAlbumCmd.onConfirmed(eventContext, arrayList);
                return;
        }
    }
}
