package K7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.VuDelegateComposite;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ ViewerObjectComposite f;
    public final /* synthetic */ ArrayList g;

    public /* synthetic */ b(int i2, ViewerObjectComposite viewerObjectComposite, ArrayList arrayList, int i7) {
        this.d = i7;
        this.e = i2;
        this.f = viewerObjectComposite;
        this.g = arrayList;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                VuDelegateComposite.lambda$onPageInvalidateInternal$1(this.e, this.f, this.g, (AbsVuDelegate) obj);
                return;
            default:
                VuDelegateComposite.lambda$onPageSelectedInternal$0(this.e, this.f, this.g, (AbsVuDelegate) obj);
                return;
        }
    }
}
