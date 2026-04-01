package r6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.IrregularGradientHelper;
import java.util.ArrayList;

/* renamed from: r6.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0512b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ IrregularGradientHelper e;
    public final /* synthetic */ ArrayList f;

    public /* synthetic */ C0512b(IrregularGradientHelper irregularGradientHelper, ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = irregularGradientHelper;
        this.f = arrayList;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setGradientBackground$0(this.f);
                return;
            default:
                this.e.lambda$setGradientBackground$1(this.f);
                return;
        }
    }
}
