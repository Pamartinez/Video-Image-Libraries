package g2;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.samsung.android.sdk.cover.ScoverState;
import com.sec.android.gallery3d.R;
import o1.C0246a;
import og.k;

/* renamed from: g2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0197a {
    public static final int f = ((int) Math.round(5.1000000000000005d));

    /* renamed from: a  reason: collision with root package name */
    public final boolean f1730a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f1731c;
    public final int d;
    public final float e;

    public C0197a(Context context) {
        Integer num;
        int i2;
        Integer num2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12 = 0;
        boolean K6 = k.K(context, R.attr.elevationOverlayEnabled, false);
        TypedValue J4 = k.J(context, R.attr.elevationOverlayColor);
        Integer num3 = null;
        if (J4 != null) {
            int i13 = J4.resourceId;
            if (i13 != 0) {
                i11 = ContextCompat.getColor(context, i13);
            } else {
                i11 = J4.data;
            }
            num = Integer.valueOf(i11);
        } else {
            num = null;
        }
        if (num != null) {
            i2 = num.intValue();
        } else {
            i2 = 0;
        }
        TypedValue J7 = k.J(context, R.attr.elevationOverlayAccentColor);
        if (J7 != null) {
            int i14 = J7.resourceId;
            if (i14 != 0) {
                i10 = ContextCompat.getColor(context, i14);
            } else {
                i10 = J7.data;
            }
            num2 = Integer.valueOf(i10);
        } else {
            num2 = null;
        }
        if (num2 != null) {
            i7 = num2.intValue();
        } else {
            i7 = 0;
        }
        TypedValue J10 = k.J(context, R.attr.colorSurface);
        if (J10 != null) {
            int i15 = J10.resourceId;
            if (i15 != 0) {
                i8 = ContextCompat.getColor(context, i15);
            } else {
                i8 = J10.data;
            }
            num3 = Integer.valueOf(i8);
        }
        i12 = num3 != null ? num3.intValue() : i12;
        float f5 = context.getResources().getDisplayMetrics().density;
        this.f1730a = K6;
        this.b = i2;
        this.f1731c = i7;
        this.d = i12;
        this.e = f5;
    }

    public final int a(float f5, int i2) {
        float f8;
        int i7;
        if (!this.f1730a || ColorUtils.setAlphaComponent(i2, ScoverState.TYPE_NFC_SMART_COVER) != this.d) {
            return i2;
        }
        float f10 = this.e;
        if (f10 <= 0.0f || f5 <= 0.0f) {
            f8 = 0.0f;
        } else {
            f8 = Math.min(((((float) Math.log1p((double) (f5 / f10))) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        }
        int alpha = Color.alpha(i2);
        int c02 = C0246a.c0(f8, ColorUtils.setAlphaComponent(i2, ScoverState.TYPE_NFC_SMART_COVER), this.b);
        if (f8 > 0.0f && (i7 = this.f1731c) != 0) {
            c02 = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i7, f), c02);
        }
        return ColorUtils.setAlphaComponent(c02, alpha);
    }
}
