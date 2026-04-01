package I4;

import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPinchAnimationManager;
import com.samsung.android.gallery.widget.pinch.IPinchAdapter;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2369a;
    public final /* synthetic */ MxAlbumsPinchAnimationManager b;

    public /* synthetic */ d(MxAlbumsPinchAnimationManager mxAlbumsPinchAnimationManager, int i2) {
        this.f2369a = i2;
        this.b = mxAlbumsPinchAnimationManager;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2369a;
        MxAlbumsPinchAnimationManager mxAlbumsPinchAnimationManager = this.b;
        IPinchAdapter iPinchAdapter = (IPinchAdapter) obj;
        switch (i2) {
            case 0:
                return mxAlbumsPinchAnimationManager.lambda$prepareDividerViewHolder$0(iPinchAdapter);
            default:
                return mxAlbumsPinchAnimationManager.lambda$prepareDividerViewHolder$1(iPinchAdapter);
        }
    }
}
