package H3;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreateFolderCmd e;

    public /* synthetic */ d(CreateFolderCmd createFolderCmd, int i2) {
        this.d = i2;
        this.e = createFolderCmd;
    }

    public final void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        CreateFolderCmd createFolderCmd = this.e;
        switch (i2) {
            case 0:
                createFolderCmd.checkOrderAndCreateFolder(eventContext, arrayList);
                return;
            default:
                createFolderCmd.lambda$createEmptyFolder$2(eventContext, arrayList);
                return;
        }
    }
}
