package z5;

import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchScreenShotHeaderView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchScreenShotHeaderView e;
    public final /* synthetic */ OnHeaderClickListener f;

    public /* synthetic */ n(SearchScreenShotHeaderView searchScreenShotHeaderView, OnHeaderClickListener onHeaderClickListener, int i2) {
        this.d = i2;
        this.e = searchScreenShotHeaderView;
        this.f = onHeaderClickListener;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$setHeaderItemClickListener$0(this.f, (MediaItem) obj);
                return;
            default:
                this.e.lambda$setHeaderItemClickListener$1(this.f, (MediaItem) obj);
                return;
        }
    }
}
