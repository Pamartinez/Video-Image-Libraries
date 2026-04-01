package x2;

import G0.e;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import androidx.core.util.ObjectsCompat;
import f2.C0187a;
import g2.C0197a;
import java.util.BitSet;
import w2.C0317a;

/* renamed from: x2.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0340g extends Drawable implements C0353t {
    public static final /* synthetic */ int y = 0;
    public C0339f d;
    public final C0352s[] e;
    public final C0352s[] f;
    public final BitSet g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f2110h;

    /* renamed from: i  reason: collision with root package name */
    public final Matrix f2111i;

    /* renamed from: j  reason: collision with root package name */
    public final Path f2112j;
    public final Path k;
    public final RectF l;
    public final RectF m;
    public final Region n;

    /* renamed from: o  reason: collision with root package name */
    public final Region f2113o;

    /* renamed from: p  reason: collision with root package name */
    public C0344k f2114p;
    public final Paint q;
    public final Paint r;
    public final e s;
    public final C0346m t;
    public PorterDuffColorFilter u;
    public PorterDuffColorFilter v;
    public int w;

    /* renamed from: x  reason: collision with root package name */
    public final RectF f2115x;

    static {
        Paint paint = new Paint(1);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public C0340g() {
        this(new C0344k());
    }

    public final void b(RectF rectF, Path path) {
        C0339f fVar = this.d;
        RectF rectF2 = rectF;
        Path path2 = path;
        this.t.a(fVar.f2103a, fVar.f2106i, rectF2, this.s, path2);
        if (this.d.f2105h != 1.0f) {
            Matrix matrix = this.f2111i;
            matrix.reset();
            float f5 = this.d.f2105h;
            matrix.setScale(f5, f5, rectF2.width() / 2.0f, rectF2.height() / 2.0f);
            path2.transform(matrix);
        }
        path2.computeBounds(this.f2115x, true);
    }

    public final int c(int i2) {
        C0339f fVar = this.d;
        float f5 = fVar.m + 0.0f + fVar.l;
        C0197a aVar = fVar.b;
        if (aVar != null) {
            return aVar.a(f5, i2);
        }
        return i2;
    }

    public final void d(Canvas canvas, Paint paint, Path path, C0344k kVar, RectF rectF) {
        if (kVar.d(rectF)) {
            float a7 = kVar.f.a(rectF) * this.d.f2106i;
            canvas.drawRoundRect(rectF, a7, a7, paint);
            return;
        }
        canvas.drawPath(path, paint);
    }

    public void draw(Canvas canvas) {
        float f5;
        PorterDuffColorFilter porterDuffColorFilter = this.u;
        Paint paint = this.q;
        paint.setColorFilter(porterDuffColorFilter);
        int alpha = paint.getAlpha();
        int i2 = this.d.k;
        paint.setAlpha(((i2 + (i2 >>> 7)) * alpha) >>> 8);
        PorterDuffColorFilter porterDuffColorFilter2 = this.v;
        Paint paint2 = this.r;
        paint2.setColorFilter(porterDuffColorFilter2);
        paint2.setStrokeWidth(this.d.f2107j);
        int alpha2 = paint2.getAlpha();
        int i7 = this.d.k;
        paint2.setAlpha(((i7 + (i7 >>> 7)) * alpha2) >>> 8);
        boolean z = this.f2110h;
        Path path = this.f2112j;
        if (z) {
            float f8 = 0.0f;
            if (h()) {
                f5 = paint2.getStrokeWidth() / 2.0f;
            } else {
                f5 = 0.0f;
            }
            float f10 = -f5;
            C0344k kVar = this.d.f2103a;
            C0343j e7 = kVar.e();
            C0336c cVar = kVar.e;
            if (!(cVar instanceof C0341h)) {
                cVar = new C0335b(f10, cVar);
            }
            e7.e = cVar;
            C0336c cVar2 = kVar.f;
            if (!(cVar2 instanceof C0341h)) {
                cVar2 = new C0335b(f10, cVar2);
            }
            e7.f = cVar2;
            C0336c cVar3 = kVar.f2124h;
            if (!(cVar3 instanceof C0341h)) {
                cVar3 = new C0335b(f10, cVar3);
            }
            e7.f2119h = cVar3;
            C0336c cVar4 = kVar.g;
            if (!(cVar4 instanceof C0341h)) {
                cVar4 = new C0335b(f10, cVar4);
            }
            e7.g = cVar4;
            C0344k a7 = e7.a();
            this.f2114p = a7;
            float f11 = this.d.f2106i;
            RectF f12 = f();
            RectF rectF = this.m;
            rectF.set(f12);
            if (h()) {
                f8 = paint2.getStrokeWidth() / 2.0f;
            }
            rectF.inset(f8, f8);
            this.t.a(a7, f11, rectF, (e) null, this.k);
            b(f(), path);
            this.f2110h = false;
        }
        C0339f fVar = this.d;
        fVar.getClass();
        if (fVar.n > 0 && !this.d.f2103a.d(f())) {
            path.isConvex();
        }
        C0339f fVar2 = this.d;
        Paint.Style style = fVar2.f2109p;
        if (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL) {
            d(canvas, paint, path, fVar2.f2103a, f());
        }
        if (h()) {
            e(canvas);
        }
        paint.setAlpha(alpha);
        paint2.setAlpha(alpha2);
    }

    public void e(Canvas canvas) {
        float f5;
        C0344k kVar = this.f2114p;
        RectF f8 = f();
        RectF rectF = this.m;
        rectF.set(f8);
        boolean h5 = h();
        Paint paint = this.r;
        if (h5) {
            f5 = paint.getStrokeWidth() / 2.0f;
        } else {
            f5 = 0.0f;
        }
        rectF.inset(f5, f5);
        d(canvas, paint, this.k, kVar, rectF);
    }

    public final RectF f() {
        Rect bounds = getBounds();
        RectF rectF = this.l;
        rectF.set(bounds);
        return rectF;
    }

    public final float g() {
        return this.d.f2103a.e.a(f());
    }

    public int getAlpha() {
        return this.d.k;
    }

    public final Drawable.ConstantState getConstantState() {
        return this.d;
    }

    public int getOpacity() {
        return -3;
    }

    public void getOutline(Outline outline) {
        this.d.getClass();
        if (this.d.f2103a.d(f())) {
            outline.setRoundRect(getBounds(), g() * this.d.f2106i);
            return;
        }
        RectF f5 = f();
        Path path = this.f2112j;
        b(f5, path);
        C0187a.a(outline, path);
    }

    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.d.g;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    public final Region getTransparentRegion() {
        Rect bounds = getBounds();
        Region region = this.n;
        region.set(bounds);
        RectF f5 = f();
        Path path = this.f2112j;
        b(f5, path);
        Region region2 = this.f2113o;
        region2.setPath(path, region);
        region.op(region2, Region.Op.DIFFERENCE);
        return region;
    }

    public final boolean h() {
        Paint.Style style = this.d.f2109p;
        if ((style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.r.getStrokeWidth() > 0.0f) {
            return true;
        }
        return false;
    }

    public final void i(Context context) {
        this.d.b = new C0197a(context);
        o();
    }

    public final void invalidateSelf() {
        this.f2110h = true;
        super.invalidateSelf();
    }

    public boolean isStateful() {
        if (super.isStateful()) {
            return true;
        }
        ColorStateList colorStateList = this.d.e;
        if (colorStateList != null && colorStateList.isStateful()) {
            return true;
        }
        this.d.getClass();
        ColorStateList colorStateList2 = this.d.d;
        if (colorStateList2 != null && colorStateList2.isStateful()) {
            return true;
        }
        ColorStateList colorStateList3 = this.d.f2104c;
        if (colorStateList3 == null || !colorStateList3.isStateful()) {
            return false;
        }
        return true;
    }

    public final void j(float f5) {
        C0339f fVar = this.d;
        if (fVar.m != f5) {
            fVar.m = f5;
            o();
        }
    }

    public final void k(ColorStateList colorStateList) {
        C0339f fVar = this.d;
        if (fVar.f2104c != colorStateList) {
            fVar.f2104c = colorStateList;
            onStateChange(getState());
        }
    }

    public final void l(float f5) {
        C0339f fVar = this.d;
        if (fVar.f2106i != f5) {
            fVar.f2106i = f5;
            this.f2110h = true;
            invalidateSelf();
        }
    }

    public final boolean m(int[] iArr) {
        boolean z;
        Paint paint;
        int color;
        int colorForState;
        Paint paint2;
        int color2;
        int colorForState2;
        if (this.d.f2104c == null || (color2 = paint2.getColor()) == (colorForState2 = this.d.f2104c.getColorForState(iArr, color2))) {
            z = false;
        } else {
            (paint2 = this.q).setColor(colorForState2);
            z = true;
        }
        if (this.d.d == null || (color = paint.getColor()) == (colorForState = this.d.d.getColorForState(iArr, color))) {
            return z;
        }
        (paint = this.r).setColor(colorForState);
        return true;
    }

    public Drawable mutate() {
        this.d = new C0339f(this.d);
        return this;
    }

    public final boolean n() {
        PorterDuffColorFilter porterDuffColorFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.u;
        PorterDuffColorFilter porterDuffColorFilter3 = this.v;
        C0339f fVar = this.d;
        ColorStateList colorStateList = fVar.e;
        PorterDuff.Mode mode = fVar.f;
        if (colorStateList == null || mode == null) {
            int color = this.q.getColor();
            int c5 = c(color);
            this.w = c5;
            if (c5 != color) {
                porterDuffColorFilter = new PorterDuffColorFilter(c5, PorterDuff.Mode.SRC_IN);
            } else {
                porterDuffColorFilter = null;
            }
        } else {
            int c6 = c(colorStateList.getColorForState(getState(), 0));
            this.w = c6;
            porterDuffColorFilter = new PorterDuffColorFilter(c6, mode);
        }
        this.u = porterDuffColorFilter;
        this.d.getClass();
        this.v = null;
        this.d.getClass();
        if (!ObjectsCompat.equals(porterDuffColorFilter2, this.u) || !ObjectsCompat.equals(porterDuffColorFilter3, this.v)) {
            return true;
        }
        return false;
    }

    public final void o() {
        C0339f fVar = this.d;
        float f5 = fVar.m + 0.0f;
        fVar.n = (int) Math.ceil((double) (0.75f * f5));
        this.d.f2108o = (int) Math.ceil((double) (f5 * 0.25f));
        n();
        super.invalidateSelf();
    }

    public final void onBoundsChange(Rect rect) {
        this.f2110h = true;
        super.onBoundsChange(rect);
    }

    public boolean onStateChange(int[] iArr) {
        boolean z;
        boolean m4 = m(iArr);
        boolean n3 = n();
        if (m4 || n3) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public void setAlpha(int i2) {
        C0339f fVar = this.d;
        if (fVar.k != i2) {
            fVar.k = i2;
            super.invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.d.getClass();
        super.invalidateSelf();
    }

    public final void setShapeAppearanceModel(C0344k kVar) {
        this.d.f2103a = kVar;
        invalidateSelf();
    }

    public final void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    public void setTintList(ColorStateList colorStateList) {
        this.d.e = colorStateList;
        n();
        super.invalidateSelf();
    }

    public void setTintMode(PorterDuff.Mode mode) {
        C0339f fVar = this.d;
        if (fVar.f != mode) {
            fVar.f = mode;
            n();
            super.invalidateSelf();
        }
    }

    public C0340g(Context context, AttributeSet attributeSet, int i2, int i7) {
        this(C0344k.b(context, attributeSet, i2, i7).a());
    }

    public C0340g(C0344k kVar) {
        this(new C0339f(kVar));
    }

    public C0340g(C0339f fVar) {
        C0346m mVar;
        this.e = new C0352s[4];
        this.f = new C0352s[4];
        this.g = new BitSet(8);
        this.f2111i = new Matrix();
        this.f2112j = new Path();
        this.k = new Path();
        this.l = new RectF();
        this.m = new RectF();
        this.n = new Region();
        this.f2113o = new Region();
        Paint paint = new Paint(1);
        this.q = paint;
        Paint paint2 = new Paint(1);
        this.r = paint2;
        new C0317a();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            mVar = C0345l.f2127a;
        } else {
            mVar = new C0346m();
        }
        this.t = mVar;
        this.f2115x = new RectF();
        this.d = fVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        n();
        m(getState());
        this.s = new e((Object) this);
    }
}
