package A4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;

/* renamed from: A4.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0380o implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2255h;

    public /* synthetic */ C0380o(Object obj, int i2, int i7, int i8, int i10) {
        this.d = i10;
        this.e = obj;
        this.f = i2;
        this.g = i7;
        this.f2255h = i8;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                int i2 = this.g;
                int i7 = this.f2255h;
                BaseListFragment.lambda$adjustBottomMoveBarMargin$4((View) this.e, this.f, i2, i7);
                return;
            case 1:
                int i8 = this.g;
                int i10 = this.f2255h;
                BaseListFragment.lambda$adjustBottomBarMargin$3((View) this.e, this.f, i8, i10);
                return;
            default:
                ((AiEditHandler) this.e).lambda$updatePosition$13(this.f, this.g, this.f2255h);
                return;
        }
    }
}
