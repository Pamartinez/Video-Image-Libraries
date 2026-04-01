package I4;

import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import java.util.function.IntUnaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements IntUnaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MxAlbumsViewAdapter f2370a;

    public /* synthetic */ h(MxAlbumsViewAdapter mxAlbumsViewAdapter) {
        this.f2370a = mxAlbumsViewAdapter;
    }

    public final int applyAsInt(int i2) {
        return this.f2370a.getViewPosition(i2);
    }
}
