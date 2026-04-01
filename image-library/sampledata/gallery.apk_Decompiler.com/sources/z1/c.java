package Z1;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.button.MaterialButton;
import com.sec.android.gallery3d.R;
import o1.C0246a;
import v2.C0299a;
import x2.C0339f;
import x2.C0340g;
import x2.C0344k;
import x2.C0353t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final MaterialButton f959a;
    public C0344k b;

    /* renamed from: c  reason: collision with root package name */
    public int f960c;
    public int d;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f961h;

    /* renamed from: i  reason: collision with root package name */
    public PorterDuff.Mode f962i;

    /* renamed from: j  reason: collision with root package name */
    public ColorStateList f963j;
    public ColorStateList k;
    public ColorStateList l;
    public C0340g m;
    public boolean n = false;

    /* renamed from: o  reason: collision with root package name */
    public boolean f964o = false;

    /* renamed from: p  reason: collision with root package name */
    public boolean f965p = false;
    public boolean q;
    public boolean r = true;
    public RippleDrawable s;
    public int t;

    public c(MaterialButton materialButton, C0344k kVar) {
        this.f959a = materialButton;
        this.b = kVar;
    }

    public final C0353t a() {
        RippleDrawable rippleDrawable = this.s;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        if (this.s.getNumberOfLayers() > 2) {
            return (C0353t) this.s.getDrawable(2);
        }
        return (C0353t) this.s.getDrawable(1);
    }

    public final C0340g b(boolean z) {
        RippleDrawable rippleDrawable = this.s;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (C0340g) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(z ^ true ? 1 : 0);
    }

    public final void c(C0344k kVar) {
        this.b = kVar;
        if (b(false) != null) {
            b(false).setShapeAppearanceModel(kVar);
        }
        if (b(true) != null) {
            b(true).setShapeAppearanceModel(kVar);
        }
        if (a() != null) {
            a().setShapeAppearanceModel(kVar);
        }
    }

    public final void d(int i2, int i7) {
        MaterialButton materialButton = this.f959a;
        int paddingStart = ViewCompat.getPaddingStart(materialButton);
        int paddingTop = materialButton.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(materialButton);
        int paddingBottom = materialButton.getPaddingBottom();
        int i8 = this.e;
        int i10 = this.f;
        this.f = i7;
        this.e = i2;
        if (!this.f964o) {
            e();
        }
        ViewCompat.setPaddingRelative(materialButton, paddingStart, (paddingTop + i2) - i8, paddingEnd, (paddingBottom + i7) - i10);
    }

    public final void e() {
        int i2;
        C0340g gVar = new C0340g(this.b);
        MaterialButton materialButton = this.f959a;
        gVar.i(materialButton.getContext());
        DrawableCompat.setTintList(gVar, this.f963j);
        PorterDuff.Mode mode = this.f962i;
        if (mode != null) {
            DrawableCompat.setTintMode(gVar, mode);
        }
        ColorStateList colorStateList = this.k;
        gVar.d.f2107j = (float) this.f961h;
        gVar.invalidateSelf();
        C0339f fVar = gVar.d;
        if (fVar.d != colorStateList) {
            fVar.d = colorStateList;
            gVar.onStateChange(gVar.getState());
        }
        C0340g gVar2 = new C0340g(this.b);
        gVar2.setTint(0);
        float f5 = (float) this.f961h;
        if (this.n) {
            i2 = C0246a.W(R.attr.colorSurface, materialButton);
        } else {
            i2 = 0;
        }
        gVar2.d.f2107j = f5;
        gVar2.invalidateSelf();
        ColorStateList valueOf = ColorStateList.valueOf(i2);
        C0339f fVar2 = gVar2.d;
        if (fVar2.d != valueOf) {
            fVar2.d = valueOf;
            gVar2.onStateChange(gVar2.getState());
        }
        C0340g gVar3 = new C0340g(this.b);
        this.m = gVar3;
        DrawableCompat.setTint(gVar3, -1);
        RippleDrawable rippleDrawable = new RippleDrawable(C0299a.b(this.l), new InsetDrawable(new LayerDrawable(new Drawable[]{gVar2, gVar}), this.f960c, this.e, this.d, this.f), this.m);
        this.s = rippleDrawable;
        materialButton.setInternalBackground(rippleDrawable);
        C0340g b5 = b(false);
        if (b5 != null) {
            b5.j((float) this.t);
            b5.setState(materialButton.getDrawableState());
        }
    }

    public final void f() {
        int i2 = 0;
        C0340g b5 = b(false);
        C0340g b8 = b(true);
        if (b5 != null) {
            ColorStateList colorStateList = this.k;
            b5.d.f2107j = (float) this.f961h;
            b5.invalidateSelf();
            C0339f fVar = b5.d;
            if (fVar.d != colorStateList) {
                fVar.d = colorStateList;
                b5.onStateChange(b5.getState());
            }
            if (b8 != null) {
                float f5 = (float) this.f961h;
                if (this.n) {
                    i2 = C0246a.W(R.attr.colorSurface, this.f959a);
                }
                b8.d.f2107j = f5;
                b8.invalidateSelf();
                ColorStateList valueOf = ColorStateList.valueOf(i2);
                C0339f fVar2 = b8.d;
                if (fVar2.d != valueOf) {
                    fVar2.d = valueOf;
                    b8.onStateChange(b8.getState());
                }
            }
        }
    }
}
