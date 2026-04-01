package C4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewHolderBindHelper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsBaseViewHolderBindHelper e;
    public final /* synthetic */ int f;

    public /* synthetic */ p(AlbumsBaseViewHolderBindHelper albumsBaseViewHolderBindHelper, int i2, int i7) {
        this.d = i7;
        this.e = albumsBaseViewHolderBindHelper;
        this.f = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$updateFolderViewSize$0(this.f, (View) obj);
                return;
            default:
                this.e.lambda$updateFolderViewSize$1(this.f, (View) obj);
                return;
        }
    }
}
