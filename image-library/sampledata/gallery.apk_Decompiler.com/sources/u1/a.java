package U1;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.sec.android.gallery3d.R;
import h2.l;
import h2.m;
import h2.p;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import u2.C0290e;
import x2.C0334a;
import x2.C0340g;
import x2.C0343j;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends Drawable implements l {
    public final WeakReference d;
    public final C0340g e;
    public final m f;
    public final Rect g = new Rect();

    /* renamed from: h  reason: collision with root package name */
    public final c f840h;

    /* renamed from: i  reason: collision with root package name */
    public float f841i;

    /* renamed from: j  reason: collision with root package name */
    public float f842j;
    public final int k;
    public float l;
    public float m;
    public float n;

    /* renamed from: o  reason: collision with root package name */
    public WeakReference f843o;

    /* renamed from: p  reason: collision with root package name */
    public WeakReference f844p;

    public a(Context context, b bVar) {
        int i2;
        int i7;
        FrameLayout frameLayout;
        C0290e eVar;
        WeakReference weakReference = new WeakReference(context);
        this.d = weakReference;
        p.c(context, p.b, "Theme.MaterialComponents");
        m mVar = new m(this);
        this.f = mVar;
        Paint.Align align = Paint.Align.CENTER;
        TextPaint textPaint = mVar.f1774a;
        textPaint.setTextAlign(align);
        c cVar = new c(context, bVar);
        this.f840h = cVar;
        boolean f5 = f();
        b bVar2 = cVar.b;
        if (f5) {
            i2 = bVar2.f851j.intValue();
        } else {
            i2 = bVar2.f849h.intValue();
        }
        if (f()) {
            i7 = bVar2.k.intValue();
        } else {
            i7 = bVar2.f850i.intValue();
        }
        C0340g gVar = new C0340g(C0344k.a(context, i2, i7, new C0334a((float) 0)).a());
        this.e = gVar;
        h();
        Context context2 = (Context) weakReference.get();
        if (!(context2 == null || mVar.g == (eVar = new C0290e(context2, bVar2.g.intValue())))) {
            mVar.b(eVar, context2);
            textPaint.setColor(bVar2.f.intValue());
            invalidateSelf();
            j();
            invalidateSelf();
        }
        int i8 = bVar2.f852o;
        if (i8 != -2) {
            this.k = ((int) Math.pow(10.0d, ((double) i8) - 1.0d)) - 1;
        } else {
            this.k = bVar2.f853p;
        }
        mVar.e = true;
        j();
        invalidateSelf();
        mVar.e = true;
        h();
        j();
        invalidateSelf();
        textPaint.setAlpha(getAlpha());
        invalidateSelf();
        ColorStateList valueOf = ColorStateList.valueOf(bVar2.e.intValue());
        if (gVar.d.f2104c != valueOf) {
            gVar.k(valueOf);
            invalidateSelf();
        }
        textPaint.setColor(bVar2.f.intValue());
        invalidateSelf();
        WeakReference weakReference2 = this.f843o;
        if (!(weakReference2 == null || weakReference2.get() == null)) {
            View view = (View) this.f843o.get();
            WeakReference weakReference3 = this.f844p;
            if (weakReference3 != null) {
                frameLayout = (FrameLayout) weakReference3.get();
            } else {
                frameLayout = null;
            }
            i(view, frameLayout);
        }
        j();
        setVisible(bVar2.w.booleanValue(), false);
    }

    public final void a() {
        invalidateSelf();
    }

    public final String b() {
        c cVar = this.f840h;
        b bVar = cVar.b;
        b bVar2 = cVar.b;
        String str = bVar.m;
        WeakReference weakReference = this.d;
        if (str != null) {
            int i2 = bVar.f852o;
            if (i2 == -2 || str == null || str.length() <= i2) {
                return str;
            }
            Context context = (Context) weakReference.get();
            if (context == null) {
                return "";
            }
            return String.format(context.getString(R.string.m3_exceed_max_badge_text_suffix), new Object[]{str.substring(0, i2 - 1), "…"});
        } else if (!g()) {
            return null;
        } else {
            if (this.k == -2 || e() <= this.k) {
                return NumberFormat.getInstance(bVar2.q).format((long) e());
            }
            Context context2 = (Context) weakReference.get();
            if (context2 == null) {
                return "";
            }
            return String.format(bVar2.q, context2.getString(R.string.mtrl_exceed_max_badge_number_suffix), new Object[]{Integer.valueOf(this.k), "+"});
        }
    }

    public final CharSequence c() {
        Context context;
        int i2;
        if (!isVisible()) {
            return null;
        }
        c cVar = this.f840h;
        b bVar = cVar.b;
        b bVar2 = cVar.b;
        if (bVar.m != null) {
            CharSequence charSequence = bVar.r;
            if (charSequence != null) {
                return charSequence;
            }
            return cVar.b.m;
        } else if (!g()) {
            return bVar2.s;
        } else {
            if (bVar2.t == 0 || (context = (Context) this.d.get()) == null) {
                return null;
            }
            if (this.k == -2 || e() <= (i2 = this.k)) {
                return context.getResources().getQuantityString(bVar2.t, e(), new Object[]{Integer.valueOf(e())});
            }
            return context.getString(bVar2.u, new Object[]{Integer.valueOf(i2)});
        }
    }

    public final FrameLayout d() {
        WeakReference weakReference = this.f844p;
        if (weakReference != null) {
            return (FrameLayout) weakReference.get();
        }
        return null;
    }

    public final void draw(Canvas canvas) {
        String b;
        int round;
        if (!getBounds().isEmpty() && getAlpha() != 0 && isVisible()) {
            this.e.draw(canvas);
            if (f() && (b = b()) != null) {
                Rect rect = new Rect();
                m mVar = this.f;
                mVar.f1774a.getTextBounds(b, 0, b.length(), rect);
                float exactCenterY = this.f842j - rect.exactCenterY();
                float f5 = this.f841i;
                if (rect.bottom <= 0) {
                    round = (int) exactCenterY;
                } else {
                    round = Math.round(exactCenterY);
                }
                canvas.drawText(b, f5, (float) round, mVar.f1774a);
            }
        }
    }

    public final int e() {
        int i2 = this.f840h.b.n;
        if (i2 != -1) {
            return i2;
        }
        return 0;
    }

    public final boolean f() {
        if (this.f840h.b.m == null && !g()) {
            return false;
        }
        return true;
    }

    public final boolean g() {
        b bVar = this.f840h.b;
        if (bVar.m == null && bVar.n != -1) {
            return true;
        }
        return false;
    }

    public final int getAlpha() {
        return this.f840h.b.l;
    }

    public final int getIntrinsicHeight() {
        return this.g.height();
    }

    public final int getIntrinsicWidth() {
        return this.g.width();
    }

    public final int getOpacity() {
        return -3;
    }

    public final void h() {
        int i2;
        int i7;
        Context context = (Context) this.d.get();
        if (context != null) {
            boolean f5 = f();
            c cVar = this.f840h;
            if (f5) {
                i2 = cVar.b.f851j.intValue();
            } else {
                i2 = cVar.b.f849h.intValue();
            }
            if (f()) {
                i7 = cVar.b.k.intValue();
            } else {
                i7 = cVar.b.f850i.intValue();
            }
            this.e.setShapeAppearanceModel(C0344k.a(context, i2, i7, new C0334a((float) 0)).a());
            invalidateSelf();
        }
    }

    public final void i(View view, FrameLayout frameLayout) {
        this.f843o = new WeakReference(view);
        this.f844p = new WeakReference(frameLayout);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        j();
        invalidateSelf();
    }

    public final boolean isStateful() {
        return false;
    }

    public final void j() {
        View view;
        float f5;
        int i2;
        float f8;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        int i7;
        float f15;
        float f16;
        float f17;
        float f18;
        WeakReference weakReference = this.d;
        Context context = (Context) weakReference.get();
        WeakReference weakReference2 = this.f843o;
        ViewGroup viewGroup = null;
        if (weakReference2 != null) {
            view = (View) weakReference2.get();
        } else {
            view = null;
        }
        if (context != null && view != null) {
            Rect rect = new Rect();
            Rect rect2 = this.g;
            rect.set(rect2);
            Rect rect3 = new Rect();
            view.getDrawingRect(rect3);
            WeakReference weakReference3 = this.f844p;
            if (weakReference3 != null) {
                viewGroup = (ViewGroup) weakReference3.get();
            }
            if (viewGroup != null) {
                viewGroup.offsetDescendantRectToMyCoords(view, rect3);
            }
            boolean f19 = f();
            c cVar = this.f840h;
            if (f19) {
                f5 = cVar.d;
            } else {
                f5 = cVar.f856c;
            }
            this.l = f5;
            if (f5 != -1.0f) {
                this.m = f5;
                this.n = f5;
            } else {
                if (f()) {
                    f17 = cVar.g;
                } else {
                    f17 = cVar.e;
                }
                this.m = (float) Math.round(f17 / 2.0f);
                if (f()) {
                    f18 = cVar.f857h;
                } else {
                    f18 = cVar.f;
                }
                this.n = (float) Math.round(f18 / 2.0f);
            }
            if (f()) {
                String b = b();
                float f20 = this.m;
                m mVar = this.f;
                if (!mVar.e) {
                    f15 = mVar.f1775c;
                } else {
                    mVar.a(b);
                    f15 = mVar.f1775c;
                }
                this.m = Math.max(f20, (f15 / 2.0f) + ((float) cVar.b.f854x.intValue()));
                float f21 = this.n;
                if (!mVar.e) {
                    f16 = mVar.d;
                } else {
                    mVar.a(b);
                    f16 = mVar.d;
                }
                float max = Math.max(f21, (f16 / 2.0f) + ((float) cVar.b.y.intValue()));
                this.n = max;
                this.m = Math.max(this.m, max);
            }
            b bVar = cVar.b;
            b bVar2 = cVar.b;
            int i8 = cVar.k;
            int intValue = bVar.f845A.intValue();
            if (f()) {
                intValue = bVar.f846C.intValue();
                Context context2 = (Context) weakReference.get();
                if (context2 != null) {
                    intValue = R1.a.c(R1.a.b(0.0f, 1.0f, 0.3f, 1.0f, context2.getResources().getConfiguration().fontScale - 1.0f), intValue, intValue - bVar.f847F.intValue());
                }
            }
            if (i8 == 0) {
                intValue -= Math.round(this.n);
            }
            int intValue2 = bVar.E.intValue() + intValue;
            int intValue3 = bVar2.v.intValue();
            if (intValue3 == 8388691 || intValue3 == 8388693) {
                this.f842j = (float) (rect3.bottom - intValue2);
            } else {
                this.f842j = (float) (rect3.top + intValue2);
            }
            if (f()) {
                i2 = bVar.B.intValue();
            } else {
                i2 = bVar2.z.intValue();
            }
            if (i8 == 1) {
                if (f()) {
                    i7 = cVar.f859j;
                } else {
                    i7 = cVar.f858i;
                }
                i2 += i7;
            }
            int intValue4 = bVar.D.intValue() + i2;
            int intValue5 = bVar2.v.intValue();
            if (intValue5 == 8388659 || intValue5 == 8388691) {
                if (ViewCompat.getLayoutDirection(view) == 0) {
                    f13 = (((float) rect3.left) - this.m) + ((float) intValue4);
                } else {
                    f13 = (((float) rect3.right) + this.m) - ((float) intValue4);
                }
                this.f841i = f13;
            } else {
                if (ViewCompat.getLayoutDirection(view) == 0) {
                    f14 = (((float) rect3.right) + this.m) - ((float) intValue4);
                } else {
                    f14 = (((float) rect3.left) - this.m) + ((float) intValue4);
                }
                this.f841i = f14;
            }
            if (bVar.f848G.booleanValue()) {
                View d2 = d();
                if (d2 != null) {
                    FrameLayout d3 = d();
                    if (d3 == null || d3.getId() != R.id.mtrl_anchor_parent) {
                        f10 = 0.0f;
                        f8 = 0.0f;
                    } else if (d2.getParent() instanceof View) {
                        f10 = d2.getY();
                        f8 = d2.getX();
                        d2 = (View) d2.getParent();
                    }
                } else if (view.getParent() instanceof View) {
                    float y = view.getY();
                    f8 = view.getX();
                    View view2 = (View) view.getParent();
                    f10 = y;
                    d2 = view2;
                }
                float y8 = d2.getY() + (this.f842j - this.n) + f10;
                float x9 = d2.getX() + (this.f841i - this.m) + f8;
                if (d2.getParent() instanceof View) {
                    f11 = ((this.f842j + this.n) - (((float) ((View) d2.getParent()).getHeight()) - d2.getY())) + f10;
                } else {
                    f11 = 0.0f;
                }
                if (d2.getParent() instanceof View) {
                    f12 = ((this.f841i + this.m) - (((float) ((View) d2.getParent()).getWidth()) - d2.getX())) + f8;
                } else {
                    f12 = 0.0f;
                }
                if (y8 < 0.0f) {
                    this.f842j = Math.abs(y8) + this.f842j;
                }
                if (x9 < 0.0f) {
                    this.f841i = Math.abs(x9) + this.f841i;
                }
                if (f11 > 0.0f) {
                    this.f842j -= Math.abs(f11);
                }
                if (f12 > 0.0f) {
                    this.f841i -= Math.abs(f12);
                }
            }
            float f22 = this.f841i;
            float f23 = this.f842j;
            float f24 = this.m;
            float f25 = this.n;
            rect2.set((int) (f22 - f24), (int) (f23 - f25), (int) (f22 + f24), (int) (f23 + f25));
            float f26 = this.l;
            int i10 = (f26 > -1.0f ? 1 : (f26 == -1.0f ? 0 : -1));
            C0340g gVar = this.e;
            if (i10 != 0) {
                C0343j e7 = gVar.d.f2103a.e();
                e7.e = new C0334a(f26);
                e7.f = new C0334a(f26);
                e7.g = new C0334a(f26);
                e7.f2119h = new C0334a(f26);
                gVar.setShapeAppearanceModel(e7.a());
            }
            if (!rect.equals(rect2)) {
                gVar.setBounds(rect2);
            }
        }
    }

    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public final void setAlpha(int i2) {
        c cVar = this.f840h;
        cVar.f855a.l = i2;
        cVar.b.l = i2;
        this.f.f1774a.setAlpha(getAlpha());
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
