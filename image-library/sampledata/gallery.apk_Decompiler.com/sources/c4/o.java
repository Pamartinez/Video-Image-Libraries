package C4;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewHolderBindHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2294h;

    public /* synthetic */ o(Object obj, boolean z, Object obj2, int i2, int i7) {
        this.d = i7;
        this.g = obj;
        this.e = z;
        this.f2294h = obj2;
        this.f = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((AlbumsBaseViewAdapter) this.g).lambda$smoothScrollToPosition$4(this.e, (LinearLayoutManager) this.f2294h, this.f, (GalleryAppBarLayout) obj);
                return;
            default:
                ((AlbumsBaseViewHolderBindHelper) this.g).lambda$updateTitleContainerMargin$3(this.e, (MediaItem) this.f2294h, this.f, (View) obj);
                return;
        }
    }
}
