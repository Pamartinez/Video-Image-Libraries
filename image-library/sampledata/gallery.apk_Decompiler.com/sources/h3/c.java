package H3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CreateAlbumCmd;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements DataCollectionDelegate.OnDataUpdateListener, DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ CreateAlbumCmd d;

    public /* synthetic */ c(CreateAlbumCmd createAlbumCmd) {
        this.d = createAlbumCmd;
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        this.d.createAlbum(eventContext, arrayList);
    }
}
