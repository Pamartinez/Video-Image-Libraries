package K7;

import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.VuDelegateComposite;
import com.samsung.android.gallery.widget.videoview.mediaplayer.CaptureDelegate;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ c(ArrayList arrayList, boolean z, int i2) {
        this.f = z;
        this.e = i2;
        this.g = arrayList;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                VuDelegateComposite.lambda$onTableModeChangedInternal$2(this.f, this.e, (ArrayList) this.g, (AbsVuDelegate) obj);
                return;
            default:
                ((CaptureDelegate) obj).captureFrame((Consumer) this.g, this.e, this.f);
                return;
        }
    }

    public /* synthetic */ c(Consumer consumer, int i2, boolean z) {
        this.g = consumer;
        this.e = i2;
        this.f = z;
    }
}
