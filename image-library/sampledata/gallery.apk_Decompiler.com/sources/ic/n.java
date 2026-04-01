package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.ScaleDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    public /* synthetic */ n(int i2, int i7, int i8, int i10) {
        this.d = i2;
        this.e = i7;
        this.f = i8;
        this.g = i10;
    }

    public final void accept(Object obj) {
        ((ScaleDelegate) obj).setPadding(this.d, this.e, this.f, this.g);
    }
}
