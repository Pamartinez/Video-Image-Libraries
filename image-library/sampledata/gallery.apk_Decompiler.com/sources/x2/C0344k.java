package x2;

import Q1.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import o1.C0246a;
import og.k;

/* renamed from: x2.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0344k {

    /* renamed from: a  reason: collision with root package name */
    public C0246a f2122a = new Object();
    public C0246a b = new Object();

    /* renamed from: c  reason: collision with root package name */
    public C0246a f2123c = new Object();
    public C0246a d = new Object();
    public C0336c e = new C0334a(0.0f);
    public C0336c f = new C0334a(0.0f);
    public C0336c g = new C0334a(0.0f);

    /* renamed from: h  reason: collision with root package name */
    public C0336c f2124h = new C0334a(0.0f);

    /* renamed from: i  reason: collision with root package name */
    public C0338e f2125i = new Object();

    /* renamed from: j  reason: collision with root package name */
    public C0338e f2126j = new Object();
    public C0338e k = new Object();
    public C0338e l = new Object();

    public static C0343j a(Context context, int i2, int i7, C0334a aVar) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
        if (i7 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, i7);
        }
        TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(a.f620J);
        try {
            int i8 = obtainStyledAttributes.getInt(0, 0);
            int i10 = obtainStyledAttributes.getInt(3, i8);
            int i11 = obtainStyledAttributes.getInt(4, i8);
            int i12 = obtainStyledAttributes.getInt(2, i8);
            int i13 = obtainStyledAttributes.getInt(1, i8);
            C0336c c5 = c(obtainStyledAttributes, 5, aVar);
            C0336c c6 = c(obtainStyledAttributes, 8, c5);
            C0336c c8 = c(obtainStyledAttributes, 9, c5);
            C0336c c10 = c(obtainStyledAttributes, 7, c5);
            C0336c c11 = c(obtainStyledAttributes, 6, c5);
            C0343j jVar = new C0343j();
            jVar.f2117a = k.l(i10);
            jVar.e = c6;
            jVar.b = k.l(i11);
            jVar.f = c8;
            jVar.f2118c = k.l(i12);
            jVar.g = c10;
            jVar.d = k.l(i13);
            jVar.f2119h = c11;
            return jVar;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static C0343j b(Context context, AttributeSet attributeSet, int i2, int i7) {
        C0334a aVar = new C0334a((float) 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.B, i2, i7);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return a(context, resourceId, resourceId2, aVar);
    }

    public static C0336c c(TypedArray typedArray, int i2, C0336c cVar) {
        TypedValue peekValue = typedArray.peekValue(i2);
        if (peekValue != null) {
            int i7 = peekValue.type;
            if (i7 == 5) {
                return new C0334a((float) TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics()));
            }
            if (i7 == 6) {
                return new C0341h(peekValue.getFraction(1.0f, 1.0f));
            }
        }
        return cVar;
    }

    public final boolean d(RectF rectF) {
        boolean z;
        boolean z3;
        boolean z7;
        Class<C0338e> cls = C0338e.class;
        if (!this.l.getClass().equals(cls) || !this.f2126j.getClass().equals(cls) || !this.f2125i.getClass().equals(cls) || !this.k.getClass().equals(cls)) {
            z = false;
        } else {
            z = true;
        }
        float a7 = this.e.a(rectF);
        if (this.f.a(rectF) == a7 && this.f2124h.a(rectF) == a7 && this.g.a(rectF) == a7) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!(this.b instanceof C0342i) || !(this.f2122a instanceof C0342i) || !(this.f2123c instanceof C0342i) || !(this.d instanceof C0342i)) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (!z || !z3 || !z7) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [x2.j, java.lang.Object] */
    public final C0343j e() {
        ? obj = new Object();
        obj.f2117a = this.f2122a;
        obj.b = this.b;
        obj.f2118c = this.f2123c;
        obj.d = this.d;
        obj.e = this.e;
        obj.f = this.f;
        obj.g = this.g;
        obj.f2119h = this.f2124h;
        obj.f2120i = this.f2125i;
        obj.f2121j = this.f2126j;
        obj.k = this.k;
        obj.l = this.l;
        return obj;
    }
}
