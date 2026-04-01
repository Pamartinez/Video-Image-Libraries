package u2;

import B1.a;
import D1.f;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextPaint;
import android.util.Log;
import androidx.appcompat.R$styleable;
import androidx.core.content.res.ResourcesCompat;

/* renamed from: u2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0290e {

    /* renamed from: a  reason: collision with root package name */
    public final ColorStateList f1944a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final int f1945c;
    public final int d;
    public final float e;
    public final float f;
    public final float g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f1946h;

    /* renamed from: i  reason: collision with root package name */
    public final float f1947i;

    /* renamed from: j  reason: collision with root package name */
    public final ColorStateList f1948j;
    public float k;
    public final int l;
    public boolean m = false;
    public Typeface n;

    public C0290e(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, R$styleable.TextAppearance);
        this.k = obtainStyledAttributes.getDimension(R$styleable.TextAppearance_android_textSize, 0.0f);
        this.f1948j = a.u(context, obtainStyledAttributes, R$styleable.TextAppearance_android_textColor);
        a.u(context, obtainStyledAttributes, R$styleable.TextAppearance_android_textColorHint);
        a.u(context, obtainStyledAttributes, R$styleable.TextAppearance_android_textColorLink);
        this.f1945c = obtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, 0);
        this.d = obtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, 1);
        int i7 = R$styleable.TextAppearance_fontFamily;
        i7 = !obtainStyledAttributes.hasValue(i7) ? R$styleable.TextAppearance_android_fontFamily : i7;
        this.l = obtainStyledAttributes.getResourceId(i7, 0);
        this.b = obtainStyledAttributes.getString(i7);
        obtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
        this.f1944a = a.u(context, obtainStyledAttributes, R$styleable.TextAppearance_android_shadowColor);
        this.e = obtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDx, 0.0f);
        this.f = obtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDy, 0.0f);
        this.g = obtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i2, Q1.a.f615C);
        this.f1946h = obtainStyledAttributes2.hasValue(0);
        this.f1947i = obtainStyledAttributes2.getFloat(0, 0.0f);
        obtainStyledAttributes2.recycle();
    }

    public final void a() {
        String str;
        Typeface typeface = this.n;
        int i2 = this.f1945c;
        if (typeface == null && (str = this.b) != null) {
            this.n = Typeface.create(str, i2);
        }
        if (this.n == null) {
            int i7 = this.d;
            if (i7 == 1) {
                this.n = Typeface.SANS_SERIF;
            } else if (i7 == 2) {
                this.n = Typeface.SERIF;
            } else if (i7 != 3) {
                this.n = Typeface.DEFAULT;
            } else {
                this.n = Typeface.MONOSPACE;
            }
            this.n = Typeface.create(this.n, i2);
        }
    }

    public final Typeface b(Context context) {
        if (this.m) {
            return this.n;
        }
        if (!context.isRestricted()) {
            try {
                Typeface font = ResourcesCompat.getFont(context, this.l);
                this.n = font;
                if (font != null) {
                    this.n = Typeface.create(font, this.f1945c);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e7) {
                Log.d("TextAppearance", "Error loading font " + this.b, e7);
            }
        }
        a();
        this.m = true;
        return this.n;
    }

    public final void c(Context context, f fVar) {
        Typeface typeface;
        int i2 = this.l;
        if (i2 != 0) {
            typeface = ResourcesCompat.getCachedFont(context, i2);
        } else {
            typeface = null;
        }
        if (typeface != null) {
            b(context);
        } else {
            a();
        }
        if (i2 == 0) {
            this.m = true;
        }
        if (this.m) {
            fVar.G(this.n, true);
            return;
        }
        try {
            ResourcesCompat.getFont(context, i2, new C0288c(this, fVar), (Handler) null);
        } catch (Resources.NotFoundException unused) {
            this.m = true;
            fVar.F(1);
        } catch (Exception e7) {
            Log.d("TextAppearance", "Error loading font " + this.b, e7);
            this.m = true;
            fVar.F(-3);
        }
    }

    public final void d(Context context, TextPaint textPaint, f fVar) {
        int i2;
        int i7;
        e(context, textPaint, fVar);
        ColorStateList colorStateList = this.f1948j;
        if (colorStateList != null) {
            i2 = colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor());
        } else {
            i2 = -16777216;
        }
        textPaint.setColor(i2);
        ColorStateList colorStateList2 = this.f1944a;
        if (colorStateList2 != null) {
            i7 = colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor());
        } else {
            i7 = 0;
        }
        textPaint.setShadowLayer(this.g, this.e, this.f, i7);
    }

    public final void e(Context context, TextPaint textPaint, f fVar) {
        Typeface typeface;
        int i2 = this.l;
        if (i2 != 0) {
            typeface = ResourcesCompat.getCachedFont(context, i2);
        } else {
            typeface = null;
        }
        if (typeface != null) {
            f(context, textPaint, b(context));
            return;
        }
        a();
        f(context, textPaint, this.n);
        c(context, new C0289d(this, context, textPaint, fVar));
    }

    public final void f(Context context, TextPaint textPaint, Typeface typeface) {
        boolean z;
        float f5;
        Typeface S = Gd.a.S(context.getResources().getConfiguration(), typeface);
        if (S != null) {
            typeface = S;
        }
        textPaint.setTypeface(typeface);
        int i2 = (~typeface.getStyle()) & this.f1945c;
        if ((i2 & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        textPaint.setFakeBoldText(z);
        if ((i2 & 2) != 0) {
            f5 = -0.25f;
        } else {
            f5 = 0.0f;
        }
        textPaint.setTextSkewX(f5);
        textPaint.setTextSize(this.k);
        if (this.f1946h) {
            textPaint.setLetterSpacing(this.f1947i);
        }
    }
}
