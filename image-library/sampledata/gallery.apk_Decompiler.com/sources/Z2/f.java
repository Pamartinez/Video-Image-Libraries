package z2;

import He.F;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.google.android.material.snackbar.SnackbarContentLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends FloatPropertyCompat {

    /* renamed from: a  reason: collision with root package name */
    public float f2206a = 0.0f;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2207c;
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ SnackbarContentLayout f;
    public final /* synthetic */ q g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(q qVar, int i2, int i7, int i8, int i10, SnackbarContentLayout snackbarContentLayout) {
        super("size");
        this.g = qVar;
        this.b = i2;
        this.f2207c = i7;
        this.d = i8;
        this.e = i10;
        this.f = snackbarContentLayout;
    }

    public final float getValue(Object obj) {
        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) obj;
        return this.f2206a;
    }

    public final void setValue(Object obj, float f5) {
        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) obj;
        float min = Math.min(4.0f * f5, 1.0f);
        this.g.h((int) F.M((float) this.b, (float) this.f2207c, min), (int) F.M((float) this.d, (float) this.e, min), this.f);
        this.f2206a = f5;
    }
}
