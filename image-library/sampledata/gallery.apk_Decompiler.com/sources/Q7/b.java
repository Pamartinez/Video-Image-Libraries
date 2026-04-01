package Q7;

import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditMenuItem e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Runnable g;

    public /* synthetic */ b(EditMenuItem editMenuItem, MediaItem mediaItem, Runnable runnable, int i2) {
        this.d = i2;
        this.e = editMenuItem;
        this.f = mediaItem;
        this.g = runnable;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleEditor$4(this.f, this.g);
                return;
            default:
                this.e.lambda$executeAfterDownloadInSharing$6(this.f, this.g);
                return;
        }
    }
}
