package L7;

import android.view.View;
import com.google.android.material.chip.SeslExpandableContainer;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsSlideHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements View.OnScrollChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2389a;
    public final /* synthetic */ Object b;

    public /* synthetic */ l(int i2, Object obj) {
        this.f2389a = i2;
        this.b = obj;
    }

    public final void onScrollChange(View view, int i2, int i7, int i8, int i10) {
        int i11 = this.f2389a;
        Object obj = this.b;
        switch (i11) {
            case 0:
                ((DetailsSlideHandler) obj).lambda$setScrollListener$21(view, i2, i7, i8, i10);
                return;
            default:
                int i12 = SeslExpandableContainer.f1445o;
                ((SeslExpandableContainer) obj).b();
                return;
        }
    }
}
