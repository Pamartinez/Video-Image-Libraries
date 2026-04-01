package Fb;

import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ c(int i2, boolean z, boolean z3) {
        this.d = i2;
        this.e = z;
        this.f = z3;
    }

    public final void accept(Object obj) {
        ((GalleryListAdapter) obj).selectItem(this.d, this.e, this.f);
    }
}
