package ic;

import android.view.ViewParent;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;
import com.samsung.android.gallery.widget.videoview.mediaplayer.ScaleDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ ViewParent d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f3273h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f3274i;

    public /* synthetic */ h(ViewParent viewParent, boolean z, int i2, int i7, int i8, int i10) {
        this.d = viewParent;
        this.e = z;
        this.f = i2;
        this.g = i7;
        this.f3273h = i8;
        this.f3274i = i10;
    }

    public final void accept(Object obj) {
        MediaPlayerViewImp.lambda$onLayout$20(this.d, this.e, this.f, this.g, this.f3273h, this.f3274i, (ScaleDelegate) obj);
    }
}
