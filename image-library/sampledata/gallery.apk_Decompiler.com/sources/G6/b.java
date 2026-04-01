package g6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ExportHandler e;

    public /* synthetic */ b(ExportHandler exportHandler, int i2) {
        this.d = i2;
        this.e = exportHandler;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ExportHandler exportHandler = this.e;
        switch (i2) {
            case 0:
                exportHandler.lambda$handleExport$0((Boolean) obj);
                return;
            case 1:
                exportHandler.lambda$executeExportDone$5((MediaItem) obj);
                return;
            default:
                exportHandler.lambda$executeExportDone$4((MediaItem) obj);
                return;
        }
    }
}
