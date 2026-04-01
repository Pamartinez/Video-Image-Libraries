package H3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileOpCmd e;
    public final /* synthetic */ String f;

    public /* synthetic */ h(FileOpCmd fileOpCmd, String str, int i2) {
        this.d = i2;
        this.e = fileOpCmd;
        this.f = str;
    }

    public final void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        switch (this.d) {
            case 0:
                this.e.lambda$startAddToAlbumDialogCmd$7(this.f, eventContext, arrayList);
                return;
            default:
                this.e.lambda$startDragToAlbumDialogCmd$12(this.f, eventContext, arrayList);
                return;
        }
    }
}
