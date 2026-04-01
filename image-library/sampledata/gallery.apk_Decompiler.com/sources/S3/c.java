package S3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ChooseSharedAlbumCmd e;

    public /* synthetic */ c(ChooseSharedAlbumCmd chooseSharedAlbumCmd, int i2) {
        this.d = i2;
        this.e = chooseSharedAlbumCmd;
    }

    public final void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        ChooseSharedAlbumCmd chooseSharedAlbumCmd = this.e;
        switch (i2) {
            case 0:
                chooseSharedAlbumCmd.addContentsToSharedAlbum(eventContext, arrayList);
                return;
            default:
                chooseSharedAlbumCmd.onConfirmed(eventContext, arrayList);
                return;
        }
    }
}
