package x2;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.samsung.android.sdk.cover.ScoverState;
import g2.C0197a;

/* renamed from: x2.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0339f extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    public C0344k f2103a;
    public C0197a b;

    /* renamed from: c  reason: collision with root package name */
    public ColorStateList f2104c = null;
    public ColorStateList d = null;
    public ColorStateList e = null;
    public PorterDuff.Mode f = PorterDuff.Mode.SRC_IN;
    public Rect g = null;

    /* renamed from: h  reason: collision with root package name */
    public final float f2105h = 1.0f;

    /* renamed from: i  reason: collision with root package name */
    public float f2106i = 1.0f;

    /* renamed from: j  reason: collision with root package name */
    public float f2107j;
    public int k = ScoverState.TYPE_NFC_SMART_COVER;
    public float l = 0.0f;
    public float m = 0.0f;
    public int n = 0;

    /* renamed from: o  reason: collision with root package name */
    public int f2108o = 0;

    /* renamed from: p  reason: collision with root package name */
    public final Paint.Style f2109p = Paint.Style.FILL_AND_STROKE;

    public C0339f(C0344k kVar) {
        this.f2103a = kVar;
        this.b = null;
    }

    public final int getChangingConfigurations() {
        return 0;
    }

    public Drawable newDrawable() {
        C0340g gVar = new C0340g(this);
        gVar.f2110h = true;
        return gVar;
    }

    public C0339f(C0339f fVar) {
        this.f2103a = fVar.f2103a;
        this.b = fVar.b;
        this.f2107j = fVar.f2107j;
        this.f2104c = fVar.f2104c;
        this.d = fVar.d;
        this.f = fVar.f;
        this.e = fVar.e;
        this.k = fVar.k;
        this.f2105h = fVar.f2105h;
        this.f2108o = fVar.f2108o;
        this.f2106i = fVar.f2106i;
        this.l = fVar.l;
        this.m = fVar.m;
        this.n = fVar.n;
        this.f2109p = fVar.f2109p;
        if (fVar.g != null) {
            this.g = new Rect(fVar.g);
        }
    }
}
