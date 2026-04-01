package h2;

import B1.b;
import G0.c;
import R1.a;
import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicCompat;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.samsung.android.sdk.cover.ScoverState;
import t1.C0280e;
import u2.C0287b;
import u2.C0290e;

/* renamed from: h2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0208c {

    /* renamed from: A  reason: collision with root package name */
    public Typeface f1736A;
    public C0287b B;

    /* renamed from: C  reason: collision with root package name */
    public C0287b f1737C;
    public TextUtils.TruncateAt D = TextUtils.TruncateAt.END;
    public CharSequence E;

    /* renamed from: F  reason: collision with root package name */
    public CharSequence f1738F;

    /* renamed from: G  reason: collision with root package name */
    public boolean f1739G;

    /* renamed from: H  reason: collision with root package name */
    public boolean f1740H = true;

    /* renamed from: I  reason: collision with root package name */
    public Bitmap f1741I;

    /* renamed from: J  reason: collision with root package name */
    public float f1742J;

    /* renamed from: K  reason: collision with root package name */
    public float f1743K;
    public float L;

    /* renamed from: M  reason: collision with root package name */
    public float f1744M;

    /* renamed from: N  reason: collision with root package name */
    public float f1745N;

    /* renamed from: O  reason: collision with root package name */
    public int f1746O;

    /* renamed from: P  reason: collision with root package name */
    public int[] f1747P;
    public boolean Q;
    public final TextPaint R;
    public final TextPaint S;
    public TimeInterpolator T;
    public TimeInterpolator U;
    public float V;

    /* renamed from: W  reason: collision with root package name */
    public float f1748W;

    /* renamed from: X  reason: collision with root package name */
    public float f1749X;
    public ColorStateList Y;
    public float Z;

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f1750a;
    public float a0;
    public float b;
    public float b0;

    /* renamed from: c  reason: collision with root package name */
    public boolean f1751c;

    /* renamed from: c0  reason: collision with root package name */
    public ColorStateList f1752c0;
    public final float d;
    public float d0;
    public final Rect e;

    /* renamed from: e0  reason: collision with root package name */
    public float f1753e0;
    public final Rect f;

    /* renamed from: f0  reason: collision with root package name */
    public float f1754f0;
    public final RectF g;

    /* renamed from: g0  reason: collision with root package name */
    public StaticLayout f1755g0;

    /* renamed from: h  reason: collision with root package name */
    public int f1756h = 16;
    public float h0;

    /* renamed from: i  reason: collision with root package name */
    public int f1757i = 16;

    /* renamed from: i0  reason: collision with root package name */
    public float f1758i0;

    /* renamed from: j  reason: collision with root package name */
    public float f1759j = 15.0f;

    /* renamed from: j0  reason: collision with root package name */
    public float f1760j0;
    public float k = 15.0f;
    public CharSequence k0;
    public ColorStateList l;
    public int l0 = 1;
    public ColorStateList m;

    /* renamed from: m0  reason: collision with root package name */
    public float f1761m0 = 0.0f;
    public int n;

    /* renamed from: n0  reason: collision with root package name */
    public float f1762n0 = 1.0f;

    /* renamed from: o  reason: collision with root package name */
    public float f1763o;
    public int o0 = 1;

    /* renamed from: p  reason: collision with root package name */
    public float f1764p;
    public float q;
    public float r;
    public float s;
    public float t;
    public Typeface u;
    public Typeface v;
    public Typeface w;

    /* renamed from: x  reason: collision with root package name */
    public Typeface f1765x;
    public Typeface y;
    public Typeface z;

    public C0208c(ViewGroup viewGroup) {
        this.f1750a = viewGroup;
        TextPaint textPaint = new TextPaint(129);
        this.R = textPaint;
        this.S = new TextPaint(textPaint);
        this.f = new Rect();
        this.e = new Rect();
        this.g = new RectF();
        this.d = 0.5f;
        h(viewGroup.getContext().getResources().getConfiguration());
    }

    public static int a(int i2, int i7, float f5) {
        float f8 = 1.0f - f5;
        return Color.argb(Math.round((((float) Color.alpha(i7)) * f5) + (((float) Color.alpha(i2)) * f8)), Math.round((((float) Color.red(i7)) * f5) + (((float) Color.red(i2)) * f8)), Math.round((((float) Color.green(i7)) * f5) + (((float) Color.green(i2)) * f8)), Math.round((((float) Color.blue(i7)) * f5) + (((float) Color.blue(i2)) * f8)));
    }

    public static float g(float f5, float f8, float f10, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f10 = timeInterpolator.getInterpolation(f10);
        }
        return a.a(f5, f8, f10);
    }

    public final boolean b(CharSequence charSequence) {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat;
        boolean z3 = true;
        if (ViewCompat.getLayoutDirection(this.f1750a) != 1) {
            z3 = false;
        }
        if (!this.f1740H) {
            return z3;
        }
        if (z3) {
            textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
        } else {
            textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        }
        return textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
    }

    public final void c(float f5, boolean z3) {
        float f8;
        float f10;
        Typeface typeface;
        boolean z7;
        Layout.Alignment alignment;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        if (this.E != null) {
            float width = (float) this.f.width();
            float width2 = (float) this.e.width();
            if (Math.abs(f5 - 1.0f) < 1.0E-5f) {
                f10 = this.k;
                f8 = this.d0;
                this.f1742J = 1.0f;
                typeface = this.u;
            } else {
                float f11 = this.f1759j;
                float f12 = this.f1753e0;
                Typeface typeface2 = this.f1765x;
                if (Math.abs(f5 - 0.0f) < 1.0E-5f) {
                    this.f1742J = 1.0f;
                } else {
                    this.f1742J = g(this.f1759j, this.k, f5, this.U) / this.f1759j;
                }
                float f13 = this.k / this.f1759j;
                float f14 = width2 * f13;
                if (z3 || this.f1751c || f14 <= width) {
                    width = width2;
                } else {
                    width = Math.min(width / f13, width2);
                }
                f10 = f11;
                f8 = f12;
                typeface = typeface2;
            }
            int i2 = (width > 0.0f ? 1 : (width == 0.0f ? 0 : -1));
            TextPaint textPaint = this.R;
            if (i2 > 0) {
                if (this.f1743K != f10) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                if (this.f1754f0 != f8) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (this.f1736A != typeface) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                StaticLayout staticLayout = this.f1755g0;
                if (staticLayout == null || width == ((float) staticLayout.getWidth())) {
                    z12 = false;
                } else {
                    z12 = true;
                }
                if (z9 || z10 || z12 || z11 || this.Q) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                this.f1743K = f10;
                this.f1754f0 = f8;
                this.f1736A = typeface;
                this.Q = false;
                if (this.f1742J != 1.0f) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                textPaint.setLinearText(z13);
            } else {
                z7 = false;
            }
            if (this.f1738F == null || z7) {
                textPaint.setTextSize(this.f1743K);
                textPaint.setTypeface(this.f1736A);
                textPaint.setLetterSpacing(this.f1754f0);
                boolean b5 = b(this.E);
                this.f1739G = b5;
                int i7 = this.l0;
                if (i7 <= 1 || (b5 && !this.f1751c)) {
                    i7 = 1;
                }
                if (i7 == 1) {
                    alignment = Layout.Alignment.ALIGN_NORMAL;
                } else {
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(this.f1756h, b5 ? 1 : 0) & 7;
                    if (absoluteGravity == 1) {
                        alignment = Layout.Alignment.ALIGN_CENTER;
                    } else if (absoluteGravity != 5) {
                        if (this.f1739G) {
                            alignment = Layout.Alignment.ALIGN_OPPOSITE;
                        } else {
                            alignment = Layout.Alignment.ALIGN_NORMAL;
                        }
                    } else if (this.f1739G) {
                        alignment = Layout.Alignment.ALIGN_NORMAL;
                    } else {
                        alignment = Layout.Alignment.ALIGN_OPPOSITE;
                    }
                }
                k kVar = new k(this.E, textPaint, (int) width);
                kVar.l = this.D;
                kVar.k = b5;
                kVar.e = alignment;
                kVar.f1773j = false;
                kVar.f = i7;
                float f15 = this.f1761m0;
                float f16 = this.f1762n0;
                kVar.g = f15;
                kVar.f1771h = f16;
                kVar.f1772i = this.o0;
                StaticLayout staticLayout2 = (StaticLayout) Preconditions.checkNotNull(kVar.a());
                this.f1755g0 = staticLayout2;
                this.f1738F = staticLayout2.getText();
            }
        }
    }

    public final void d(Canvas canvas) {
        int save = canvas.save();
        if (this.f1738F != null) {
            RectF rectF = this.g;
            if (rectF.width() > 0.0f && rectF.height() > 0.0f) {
                float f5 = this.f1743K;
                TextPaint textPaint = this.R;
                textPaint.setTextSize(f5);
                float f8 = this.s;
                float f10 = this.t;
                float f11 = this.f1742J;
                if (f11 != 1.0f && !this.f1751c) {
                    canvas.scale(f11, f11, f8, f10);
                }
                if (this.l0 <= 1 || ((this.f1739G && !this.f1751c) || (this.f1751c && this.b <= this.d))) {
                    canvas.translate(f8, f10);
                    this.f1755g0.draw(canvas);
                } else {
                    int alpha = textPaint.getAlpha();
                    canvas.translate(this.s - ((float) this.f1755g0.getLineStart(0)), f10);
                    if (!this.f1751c) {
                        textPaint.setAlpha((int) (this.f1760j0 * ((float) alpha)));
                        if (Build.VERSION.SDK_INT >= 31) {
                            float f12 = this.L;
                            float f13 = this.f1744M;
                            float f14 = this.f1745N;
                            int i2 = this.f1746O;
                            textPaint.setShadowLayer(f12, f13, f14, ColorUtils.setAlphaComponent(i2, (Color.alpha(i2) * textPaint.getAlpha()) / ScoverState.TYPE_NFC_SMART_COVER));
                        }
                        this.f1755g0.draw(canvas);
                    }
                    if (!this.f1751c) {
                        textPaint.setAlpha((int) (this.f1758i0 * ((float) alpha)));
                    }
                    int i7 = Build.VERSION.SDK_INT;
                    if (i7 >= 31) {
                        float f15 = this.L;
                        float f16 = this.f1744M;
                        float f17 = this.f1745N;
                        int i8 = this.f1746O;
                        textPaint.setShadowLayer(f15, f16, f17, ColorUtils.setAlphaComponent(i8, (Color.alpha(i8) * textPaint.getAlpha()) / ScoverState.TYPE_NFC_SMART_COVER));
                    }
                    int lineBaseline = this.f1755g0.getLineBaseline(0);
                    CharSequence charSequence = this.k0;
                    float f18 = (float) lineBaseline;
                    Canvas canvas2 = canvas;
                    canvas2.drawText(charSequence, 0, charSequence.length(), 0.0f, f18, textPaint);
                    if (i7 >= 31) {
                        textPaint.setShadowLayer(this.L, this.f1744M, this.f1745N, this.f1746O);
                    }
                    if (!this.f1751c) {
                        String trim = this.k0.toString().trim();
                        if (trim.endsWith("…")) {
                            trim = C0280e.d(1, 0, trim);
                        }
                        String str = trim;
                        textPaint.setAlpha(alpha);
                        canvas2.drawText(str, 0, Math.min(this.f1755g0.getLineEnd(0), str.length()), 0.0f, f18, textPaint);
                    }
                    canvas = canvas2;
                }
                canvas.restoreToCount(save);
            }
        }
    }

    public final float e() {
        float f5 = this.k;
        TextPaint textPaint = this.S;
        textPaint.setTextSize(f5);
        textPaint.setTypeface(this.u);
        textPaint.setLetterSpacing(this.d0);
        return -textPaint.ascent();
    }

    public final int f(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.f1747P;
        if (iArr != null) {
            return colorStateList.getColorForState(iArr, 0);
        }
        return colorStateList.getDefaultColor();
    }

    public final void h(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 31) {
            Typeface typeface = this.w;
            if (typeface != null) {
                this.v = Gd.a.S(configuration, typeface);
            }
            Typeface typeface2 = this.z;
            if (typeface2 != null) {
                this.y = Gd.a.S(configuration, typeface2);
            }
            Typeface typeface3 = this.v;
            if (typeface3 == null) {
                typeface3 = this.w;
            }
            this.u = typeface3;
            Typeface typeface4 = this.y;
            if (typeface4 == null) {
                typeface4 = this.z;
            }
            this.f1765x = typeface4;
            i(true);
        }
    }

    public final void i(boolean z3) {
        float f5;
        float f8;
        int i2;
        float f10;
        float f11;
        StaticLayout staticLayout;
        boolean z7 = z3;
        ViewGroup viewGroup = this.f1750a;
        if ((viewGroup.getHeight() > 0 && viewGroup.getWidth() > 0) || z7) {
            c(1.0f, z7);
            CharSequence charSequence = this.f1738F;
            TextPaint textPaint = this.R;
            if (!(charSequence == null || (staticLayout = this.f1755g0) == null)) {
                this.k0 = TextUtils.ellipsize(charSequence, textPaint, (float) staticLayout.getWidth(), this.D);
            }
            CharSequence charSequence2 = this.k0;
            if (charSequence2 != null) {
                this.h0 = textPaint.measureText(charSequence2, 0, charSequence2.length());
            } else {
                this.h0 = 0.0f;
            }
            int absoluteGravity = GravityCompat.getAbsoluteGravity(this.f1757i, this.f1739G ? 1 : 0);
            int i7 = absoluteGravity & 112;
            Rect rect = this.f;
            if (i7 == 48) {
                this.f1764p = (float) rect.top;
            } else if (i7 != 80) {
                this.f1764p = ((float) rect.centerY()) - ((textPaint.descent() - textPaint.ascent()) / 2.0f);
            } else {
                this.f1764p = textPaint.ascent() + ((float) rect.bottom);
            }
            int i8 = absoluteGravity & 8388615;
            if (i8 == 1) {
                this.r = ((float) rect.centerX()) - (this.h0 / 2.0f);
            } else if (i8 != 5) {
                this.r = (float) rect.left;
            } else {
                this.r = ((float) rect.right) - this.h0;
            }
            c(0.0f, z7);
            StaticLayout staticLayout2 = this.f1755g0;
            if (staticLayout2 != null) {
                f5 = (float) staticLayout2.getHeight();
            } else {
                f5 = 0.0f;
            }
            StaticLayout staticLayout3 = this.f1755g0;
            if (staticLayout3 == null || this.l0 <= 1) {
                CharSequence charSequence3 = this.f1738F;
                if (charSequence3 != null) {
                    f8 = textPaint.measureText(charSequence3, 0, charSequence3.length());
                } else {
                    f8 = 0.0f;
                }
            } else {
                f8 = (float) staticLayout3.getWidth();
            }
            StaticLayout staticLayout4 = this.f1755g0;
            if (staticLayout4 != null) {
                i2 = staticLayout4.getLineCount();
            } else {
                i2 = 0;
            }
            this.n = i2;
            int absoluteGravity2 = GravityCompat.getAbsoluteGravity(this.f1756h, this.f1739G ? 1 : 0);
            int i10 = absoluteGravity2 & 112;
            Rect rect2 = this.e;
            if (i10 == 48) {
                this.f1763o = (float) rect2.top;
            } else if (i10 != 80) {
                this.f1763o = ((float) rect2.centerY()) - (f5 / 2.0f);
            } else {
                this.f1763o = textPaint.descent() + (((float) rect2.bottom) - f5);
            }
            int i11 = absoluteGravity2 & 8388615;
            if (i11 == 1) {
                this.q = ((float) rect2.centerX()) - (f8 / 2.0f);
            } else if (i11 != 5) {
                this.q = (float) rect2.left;
            } else {
                this.q = ((float) rect2.right) - f8;
            }
            Bitmap bitmap = this.f1741I;
            if (bitmap != null) {
                bitmap.recycle();
                this.f1741I = null;
            }
            q(this.b);
            float f12 = this.b;
            boolean z9 = this.f1751c;
            float f13 = this.d;
            RectF rectF = this.g;
            if (z9) {
                if (f12 < f13) {
                    rect = rect2;
                }
                rectF.set(rect);
            } else {
                rectF.left = g((float) rect2.left, (float) rect.left, f12, this.T);
                rectF.top = g(this.f1763o, this.f1764p, f12, this.T);
                rectF.right = g((float) rect2.right, (float) rect.right, f12, this.T);
                rectF.bottom = g((float) rect2.bottom, (float) rect.bottom, f12, this.T);
            }
            if (!this.f1751c) {
                this.s = g(this.q, this.r, f12, this.T);
                this.t = g(this.f1763o, this.f1764p, f12, this.T);
                q(f12);
                f10 = f12;
            } else if (f12 < f13) {
                this.s = this.q;
                this.t = this.f1763o;
                q(0.0f);
                f10 = 0.0f;
            } else {
                this.s = this.r;
                this.t = this.f1764p - ((float) Math.max(0, 0));
                q(1.0f);
                f10 = 1.0f;
            }
            FastOutSlowInInterpolator fastOutSlowInInterpolator = a.b;
            this.f1758i0 = 1.0f - g(0.0f, 1.0f, 1.0f - f12, fastOutSlowInInterpolator);
            ViewCompat.postInvalidateOnAnimation(viewGroup);
            this.f1760j0 = g(1.0f, 0.0f, f12, fastOutSlowInInterpolator);
            ViewCompat.postInvalidateOnAnimation(viewGroup);
            ColorStateList colorStateList = this.m;
            ColorStateList colorStateList2 = this.l;
            if (colorStateList != colorStateList2) {
                textPaint.setColor(a(f(colorStateList2), f(this.m), f10));
            } else {
                textPaint.setColor(f(colorStateList));
            }
            int i12 = Build.VERSION.SDK_INT;
            float f14 = this.d0;
            float f15 = this.f1753e0;
            if (f14 != f15) {
                textPaint.setLetterSpacing(g(f15, f14, f12, fastOutSlowInInterpolator));
            } else {
                textPaint.setLetterSpacing(f14);
            }
            this.L = a.a(this.Z, this.V, f12);
            this.f1744M = a.a(this.a0, this.f1748W, f12);
            this.f1745N = a.a(this.b0, this.f1749X, f12);
            int a7 = a(f(this.f1752c0), f(this.Y), f12);
            this.f1746O = a7;
            textPaint.setShadowLayer(this.L, this.f1744M, this.f1745N, a7);
            if (this.f1751c) {
                int alpha = textPaint.getAlpha();
                if (f12 <= f13) {
                    f11 = a.b(1.0f, 0.0f, 0.0f, f13, f12);
                } else {
                    f11 = a.b(0.0f, 1.0f, f13, 1.0f, f12);
                }
                textPaint.setAlpha((int) (f11 * ((float) alpha)));
                if (i12 >= 31) {
                    float f16 = this.L;
                    float f17 = this.f1744M;
                    float f18 = this.f1745N;
                    int i13 = this.f1746O;
                    textPaint.setShadowLayer(f16, f17, f18, ColorUtils.setAlphaComponent(i13, (Color.alpha(i13) * textPaint.getAlpha()) / ScoverState.TYPE_NFC_SMART_COVER));
                }
            }
            ViewCompat.postInvalidateOnAnimation(viewGroup);
        }
    }

    public final void j(ColorStateList colorStateList) {
        if (this.m != colorStateList || this.l != colorStateList) {
            this.m = colorStateList;
            this.l = colorStateList;
            i(false);
        }
    }

    public final void k(int i2) {
        ViewGroup viewGroup = this.f1750a;
        C0290e eVar = new C0290e(viewGroup.getContext(), i2);
        ColorStateList colorStateList = eVar.f1948j;
        if (colorStateList != null) {
            this.m = colorStateList;
        }
        float f5 = eVar.k;
        if (f5 != 0.0f) {
            this.k = f5;
        }
        ColorStateList colorStateList2 = eVar.f1944a;
        if (colorStateList2 != null) {
            this.Y = colorStateList2;
        }
        this.f1748W = eVar.e;
        this.f1749X = eVar.f;
        this.V = eVar.g;
        this.d0 = eVar.f1947i;
        C0287b bVar = this.f1737C;
        if (bVar != null) {
            bVar.f1939i = true;
        }
        b bVar2 = new b(13, this);
        eVar.a();
        this.f1737C = new C0287b(bVar2, eVar.n);
        eVar.c(viewGroup.getContext(), this.f1737C);
        i(false);
    }

    public final void l(int i2) {
        if (this.f1757i != i2) {
            this.f1757i = i2;
            i(false);
        }
    }

    public final boolean m(Typeface typeface) {
        C0287b bVar = this.f1737C;
        if (bVar != null) {
            bVar.f1939i = true;
        }
        if (this.w == typeface) {
            return false;
        }
        this.w = typeface;
        Typeface S10 = Gd.a.S(this.f1750a.getContext().getResources().getConfiguration(), typeface);
        this.v = S10;
        if (S10 == null) {
            S10 = this.w;
        }
        this.u = S10;
        return true;
    }

    public final void n(int i2) {
        ViewGroup viewGroup = this.f1750a;
        C0290e eVar = new C0290e(viewGroup.getContext(), i2);
        ColorStateList colorStateList = eVar.f1948j;
        if (colorStateList != null) {
            this.l = colorStateList;
        }
        float f5 = eVar.k;
        if (f5 != 0.0f) {
            this.f1759j = f5;
        }
        ColorStateList colorStateList2 = eVar.f1944a;
        if (colorStateList2 != null) {
            this.f1752c0 = colorStateList2;
        }
        this.a0 = eVar.e;
        this.b0 = eVar.f;
        this.Z = eVar.g;
        this.f1753e0 = eVar.f1947i;
        C0287b bVar = this.B;
        if (bVar != null) {
            bVar.f1939i = true;
        }
        c cVar = new c(13, (Object) this);
        eVar.a();
        this.B = new C0287b(cVar, eVar.n);
        eVar.c(viewGroup.getContext(), this.B);
        i(false);
    }

    public final boolean o(Typeface typeface) {
        C0287b bVar = this.B;
        if (bVar != null) {
            bVar.f1939i = true;
        }
        if (this.z == typeface) {
            return false;
        }
        this.z = typeface;
        Typeface S10 = Gd.a.S(this.f1750a.getContext().getResources().getConfiguration(), typeface);
        this.y = S10;
        if (S10 == null) {
            S10 = this.z;
        }
        this.f1765x = S10;
        return true;
    }

    public final void p(float f5) {
        float f8;
        float f10;
        float clamp = MathUtils.clamp(f5, 0.0f, 1.0f);
        if (clamp != this.b) {
            this.b = clamp;
            boolean z3 = this.f1751c;
            float f11 = this.d;
            Rect rect = this.f;
            Rect rect2 = this.e;
            RectF rectF = this.g;
            if (z3) {
                if (clamp < f11) {
                    rect = rect2;
                }
                rectF.set(rect);
            } else {
                rectF.left = g((float) rect2.left, (float) rect.left, clamp, this.T);
                rectF.top = g(this.f1763o, this.f1764p, clamp, this.T);
                rectF.right = g((float) rect2.right, (float) rect.right, clamp, this.T);
                rectF.bottom = g((float) rect2.bottom, (float) rect.bottom, clamp, this.T);
            }
            if (!this.f1751c) {
                this.s = g(this.q, this.r, clamp, this.T);
                this.t = g(this.f1763o, this.f1764p, clamp, this.T);
                q(clamp);
                f8 = clamp;
            } else if (clamp < f11) {
                this.s = this.q;
                this.t = this.f1763o;
                q(0.0f);
                f8 = 0.0f;
            } else {
                this.s = this.r;
                this.t = this.f1764p - ((float) Math.max(0, 0));
                q(1.0f);
                f8 = 1.0f;
            }
            FastOutSlowInInterpolator fastOutSlowInInterpolator = a.b;
            this.f1758i0 = 1.0f - g(0.0f, 1.0f, 1.0f - clamp, fastOutSlowInInterpolator);
            ViewGroup viewGroup = this.f1750a;
            ViewCompat.postInvalidateOnAnimation(viewGroup);
            this.f1760j0 = g(1.0f, 0.0f, clamp, fastOutSlowInInterpolator);
            ViewCompat.postInvalidateOnAnimation(viewGroup);
            ColorStateList colorStateList = this.m;
            ColorStateList colorStateList2 = this.l;
            TextPaint textPaint = this.R;
            if (colorStateList != colorStateList2) {
                textPaint.setColor(a(f(colorStateList2), f(this.m), f8));
            } else {
                textPaint.setColor(f(colorStateList));
            }
            int i2 = Build.VERSION.SDK_INT;
            float f12 = this.d0;
            float f13 = this.f1753e0;
            if (f12 != f13) {
                textPaint.setLetterSpacing(g(f13, f12, clamp, fastOutSlowInInterpolator));
            } else {
                textPaint.setLetterSpacing(f12);
            }
            this.L = a.a(this.Z, this.V, clamp);
            this.f1744M = a.a(this.a0, this.f1748W, clamp);
            this.f1745N = a.a(this.b0, this.f1749X, clamp);
            int a7 = a(f(this.f1752c0), f(this.Y), clamp);
            this.f1746O = a7;
            textPaint.setShadowLayer(this.L, this.f1744M, this.f1745N, a7);
            if (this.f1751c) {
                int alpha = textPaint.getAlpha();
                if (clamp <= f11) {
                    f10 = a.b(1.0f, 0.0f, 0.0f, f11, clamp);
                } else {
                    f10 = a.b(0.0f, 1.0f, f11, 1.0f, clamp);
                }
                textPaint.setAlpha((int) (f10 * ((float) alpha)));
                if (i2 >= 31) {
                    float f14 = this.L;
                    float f15 = this.f1744M;
                    float f16 = this.f1745N;
                    int i7 = this.f1746O;
                    textPaint.setShadowLayer(f14, f15, f16, ColorUtils.setAlphaComponent(i7, (Color.alpha(i7) * textPaint.getAlpha()) / ScoverState.TYPE_NFC_SMART_COVER));
                }
            }
            ViewCompat.postInvalidateOnAnimation(viewGroup);
        }
    }

    public final void q(float f5) {
        c(f5, false);
        ViewCompat.postInvalidateOnAnimation(this.f1750a);
    }
}
