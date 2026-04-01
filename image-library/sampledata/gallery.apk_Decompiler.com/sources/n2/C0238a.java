package n2;

import Te.G;
import android.graphics.RectF;
import androidx.dynamicanimation.animation.FloatPropertyCompat;

/* renamed from: n2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0238a extends FloatPropertyCompat {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1843a;
    public final /* synthetic */ G b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0238a(G g, G g3, int i2) {
        super("rectLeft");
        this.f1843a = i2;
        this.b = g3;
        switch (i2) {
            case 1:
                super("rectTop");
                return;
            case 2:
                super("rectRight");
                return;
            case 3:
                super("rectBottom");
                return;
            default:
                return;
        }
    }

    public final float getValue(Object obj) {
        float f;
        switch (this.f1843a) {
            case 0:
                f = ((RectF) obj).left;
                break;
            case 1:
                f = ((RectF) obj).top;
                break;
            case 2:
                f = ((RectF) obj).right;
                break;
            default:
                f = ((RectF) obj).bottom;
                break;
        }
        return f * 100.0f;
    }

    public final void setValue(Object obj, float f) {
        switch (this.f1843a) {
            case 0:
                RectF rectF = (RectF) obj;
                rectF.left = f / 100.0f;
                G.c(this.b, rectF);
                return;
            case 1:
                RectF rectF2 = (RectF) obj;
                rectF2.top = f / 100.0f;
                G.c(this.b, rectF2);
                return;
            case 2:
                RectF rectF3 = (RectF) obj;
                rectF3.right = f / 100.0f;
                G.c(this.b, rectF3);
                return;
            default:
                RectF rectF4 = (RectF) obj;
                rectF4.bottom = f / 100.0f;
                G.c(this.b, rectF4);
                return;
        }
    }
}
