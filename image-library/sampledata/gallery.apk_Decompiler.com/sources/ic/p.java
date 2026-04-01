package ic;

import android.view.View;
import com.samsung.android.gallery.widget.capture.ClipViewDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Consumer {
    public final /* synthetic */ View d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ p(View view, boolean z, boolean z3) {
        this.d = view;
        this.e = z;
        this.f = z3;
    }

    public final void accept(Object obj) {
        ((ClipViewDelegate) obj).bindCaptureView(this.d, this.e, this.f, true);
    }
}
