package q2;

import android.util.FloatProperty;
import android.view.View;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends FloatProperty {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1879a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(String str, int i2) {
        super(str);
        this.f1879a = i2;
    }

    public final Object get(Object obj) {
        switch (this.f1879a) {
            case 0:
                View view = (View) obj;
                j.e(view, "view");
                return Float.valueOf(view.getAlpha());
            default:
                View view2 = (View) obj;
                j.e(view2, "view");
                return Float.valueOf(view2.getAlpha());
        }
    }

    public final void setValue(Object obj, float f) {
        switch (this.f1879a) {
            case 0:
                View view = (View) obj;
                j.e(view, "view");
                view.setAlpha(f);
                return;
            default:
                View view2 = (View) obj;
                j.e(view2, "view");
                view2.setAlpha(f);
                return;
        }
    }
}
