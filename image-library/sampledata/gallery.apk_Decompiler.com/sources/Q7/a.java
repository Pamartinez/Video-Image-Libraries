package Q7;

import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditMenuItem e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ a(EditMenuItem editMenuItem, Runnable runnable, int i2) {
        this.d = i2;
        this.e = editMenuItem;
        this.f = runnable;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                Runnable runnable = this.f;
                this.e.lambda$handleDirectorsViewEdit$5(runnable, (MediaItem) obj);
                return;
            case 1:
                Runnable runnable2 = this.f;
                this.e.lambda$executeAfterDownloadInSharing$7(runnable2, (MediaItem) obj);
                return;
            case 2:
                this.e.lambda$handleEditor$2(this.f, obj);
                return;
            default:
                this.e.lambda$handleEditor$3(this.f, obj);
                return;
        }
    }
}
