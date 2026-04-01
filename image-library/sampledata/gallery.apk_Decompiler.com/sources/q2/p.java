package q2;

import android.util.FloatProperty;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends FloatProperty {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1887a = 1;
    public final /* synthetic */ u b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public p(FloatingToolbarLayout floatingToolbarLayout) {
        super("titleAlphaAnimProperty");
        this.b = floatingToolbarLayout;
    }

    public final Object get(Object obj) {
        float f;
        switch (this.f1887a) {
            case 0:
                View view = (View) obj;
                j.e(view, "view");
                return Float.valueOf(view.getAlpha());
            default:
                Toolbar toolbar = (Toolbar) obj;
                j.e(toolbar, "toolbar");
                if (toolbar.getTitleTextView() == null) {
                    f = 0.0f;
                } else {
                    f = toolbar.getTitleTextView().getAlpha();
                }
                return Float.valueOf(f);
        }
    }

    public final void setValue(Object obj, float f) {
        switch (this.f1887a) {
            case 0:
                View view = (View) obj;
                j.e(view, "view");
                view.setAlpha(f);
                this.b.getClass();
                return;
            default:
                j.e((Toolbar) obj, "toolbar");
                ((FloatingToolbarLayout) this.b).setAlphaForToolbarTitleViGroup(f);
                return;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public p(u uVar) {
        super("AlphaAnim");
        this.b = uVar;
    }
}
