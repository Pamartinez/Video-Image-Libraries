package U1;

import Q1.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.appcompat.R$styleable;
import com.samsung.android.photoremaster.engine.R;
import com.samsung.android.sdk.cover.ScoverState;
import h2.p;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final b f855a;
    public final b b = new b();

    /* renamed from: c  reason: collision with root package name */
    public final float f856c;
    public final float d;
    public final float e;
    public final float f;
    public final float g;

    /* renamed from: h  reason: collision with root package name */
    public final float f857h;

    /* renamed from: i  reason: collision with root package name */
    public final int f858i;

    /* renamed from: j  reason: collision with root package name */
    public final int f859j;
    public final int k;

    public c(Context context, b bVar) {
        AttributeSet attributeSet;
        int i2;
        boolean z;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        boolean z3;
        int next;
        bVar = bVar == null ? new b() : bVar;
        int i24 = bVar.d;
        if (i24 != 0) {
            try {
                XmlResourceParser xml = context.getResources().getXml(i24);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                } else if (TextUtils.equals(xml.getName(), "badge")) {
                    attributeSet = Xml.asAttributeSet(xml);
                    i2 = attributeSet.getStyleAttribute();
                } else {
                    throw new XmlPullParserException("Must have a <" + "badge" + "> start tag");
                }
            } catch (IOException | XmlPullParserException e7) {
                Throwable th = e7;
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(i24));
                notFoundException.initCause(th);
                throw notFoundException;
            }
        } else {
            attributeSet = null;
            i2 = 0;
        }
        AttributeSet attributeSet2 = attributeSet;
        i2 = i2 == 0 ? R.style.Widget_MaterialComponents_Badge : i2;
        Context context2 = context;
        TypedArray d2 = p.d(context2, attributeSet2, a.f627c, com.sec.android.gallery3d.R.attr.badgeStyle, i2, new int[0]);
        Resources resources = context2.getResources();
        this.f856c = (float) d2.getDimensionPixelSize(4, -1);
        this.f858i = context2.getResources().getDimensionPixelSize(com.sec.android.gallery3d.R.dimen.mtrl_badge_horizontal_edge_offset);
        this.f859j = context2.getResources().getDimensionPixelSize(com.sec.android.gallery3d.R.dimen.mtrl_badge_text_horizontal_edge_offset);
        this.d = (float) d2.getDimensionPixelSize(14, -1);
        this.e = d2.getDimension(12, resources.getDimension(com.sec.android.gallery3d.R.dimen.m3_badge_size));
        this.g = d2.getDimension(17, resources.getDimension(com.sec.android.gallery3d.R.dimen.m3_badge_with_text_size));
        this.f = d2.getDimension(3, resources.getDimension(com.sec.android.gallery3d.R.dimen.m3_badge_size));
        this.f857h = d2.getDimension(13, resources.getDimension(com.sec.android.gallery3d.R.dimen.m3_badge_with_text_size));
        this.k = d2.getInt(24, 1);
        b bVar2 = this.b;
        int i25 = bVar.l;
        bVar2.l = i25 == -2 ? ScoverState.TYPE_NFC_SMART_COVER : i25;
        int i26 = bVar.n;
        if (i26 != -2) {
            bVar2.n = i26;
        } else if (d2.hasValue(23)) {
            this.b.n = d2.getInt(23, 0);
        } else {
            this.b.n = -1;
        }
        String str = bVar.m;
        if (str != null) {
            this.b.m = str;
        } else if (d2.hasValue(7)) {
            this.b.m = d2.getString(7);
        }
        b bVar3 = this.b;
        bVar3.r = bVar.r;
        CharSequence charSequence = bVar.s;
        bVar3.s = charSequence == null ? context2.getString(com.sec.android.gallery3d.R.string.mtrl_badge_numberless_content_description) : charSequence;
        b bVar4 = this.b;
        int i27 = bVar.t;
        bVar4.t = i27 == 0 ? com.sec.android.gallery3d.R.plurals.mtrl_badge_content_description : i27;
        int i28 = bVar.u;
        bVar4.u = i28 == 0 ? com.sec.android.gallery3d.R.string.mtrl_exceed_max_badge_number_content_description : i28;
        Boolean bool = bVar.w;
        if (bool == null || bool.booleanValue()) {
            z = true;
        } else {
            z = false;
        }
        bVar4.w = Boolean.valueOf(z);
        b bVar5 = this.b;
        int i29 = bVar.f852o;
        bVar5.f852o = i29 == -2 ? d2.getInt(21, -2) : i29;
        b bVar6 = this.b;
        int i30 = bVar.f853p;
        bVar6.f853p = i30 == -2 ? d2.getInt(22, -2) : i30;
        b bVar7 = this.b;
        Integer num = bVar.f849h;
        if (num == null) {
            i7 = d2.getResourceId(5, 2131952278);
        } else {
            i7 = num.intValue();
        }
        bVar7.f849h = Integer.valueOf(i7);
        b bVar8 = this.b;
        Integer num2 = bVar.f850i;
        if (num2 == null) {
            i8 = d2.getResourceId(6, 0);
        } else {
            i8 = num2.intValue();
        }
        bVar8.f850i = Integer.valueOf(i8);
        b bVar9 = this.b;
        Integer num3 = bVar.f851j;
        if (num3 == null) {
            i10 = d2.getResourceId(15, 2131952278);
        } else {
            i10 = num3.intValue();
        }
        bVar9.f851j = Integer.valueOf(i10);
        b bVar10 = this.b;
        Integer num4 = bVar.k;
        if (num4 == null) {
            i11 = d2.getResourceId(16, 0);
        } else {
            i11 = num4.intValue();
        }
        bVar10.k = Integer.valueOf(i11);
        b bVar11 = this.b;
        Integer num5 = bVar.e;
        if (num5 == null) {
            i12 = B1.a.u(context2, d2, 1).getDefaultColor();
        } else {
            i12 = num5.intValue();
        }
        bVar11.e = Integer.valueOf(i12);
        b bVar12 = this.b;
        Integer num6 = bVar.g;
        if (num6 == null) {
            i13 = d2.getResourceId(8, R.style.TextAppearance_MaterialComponents_Badge);
        } else {
            i13 = num6.intValue();
        }
        bVar12.g = Integer.valueOf(i13);
        Integer num7 = bVar.f;
        if (num7 != null) {
            this.b.f = num7;
        } else if (d2.hasValue(9)) {
            this.b.f = Integer.valueOf(B1.a.u(context2, d2, 9).getDefaultColor());
        } else {
            int intValue = this.b.g.intValue();
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(intValue, R$styleable.TextAppearance);
            obtainStyledAttributes.getDimension(R$styleable.TextAppearance_android_textSize, 0.0f);
            ColorStateList u = B1.a.u(context2, obtainStyledAttributes, R$styleable.TextAppearance_android_textColor);
            B1.a.u(context2, obtainStyledAttributes, R$styleable.TextAppearance_android_textColorHint);
            B1.a.u(context2, obtainStyledAttributes, R$styleable.TextAppearance_android_textColorLink);
            obtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, 0);
            obtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, 1);
            int i31 = R$styleable.TextAppearance_fontFamily;
            i31 = !obtainStyledAttributes.hasValue(i31) ? R$styleable.TextAppearance_android_fontFamily : i31;
            obtainStyledAttributes.getResourceId(i31, 0);
            obtainStyledAttributes.getString(i31);
            obtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
            B1.a.u(context2, obtainStyledAttributes, R$styleable.TextAppearance_android_shadowColor);
            obtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDx, 0.0f);
            obtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDy, 0.0f);
            obtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowRadius, 0.0f);
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(intValue, a.f615C);
            obtainStyledAttributes2.hasValue(0);
            obtainStyledAttributes2.getFloat(0, 0.0f);
            obtainStyledAttributes2.recycle();
            this.b.f = Integer.valueOf(u.getDefaultColor());
        }
        b bVar13 = this.b;
        Integer num8 = bVar.v;
        if (num8 == null) {
            i14 = d2.getInt(2, 8388661);
        } else {
            i14 = num8.intValue();
        }
        bVar13.v = Integer.valueOf(i14);
        b bVar14 = this.b;
        Integer num9 = bVar.f854x;
        if (num9 == null) {
            i15 = d2.getDimensionPixelSize(11, resources.getDimensionPixelSize(com.sec.android.gallery3d.R.dimen.mtrl_badge_long_text_horizontal_padding));
        } else {
            i15 = num9.intValue();
        }
        bVar14.f854x = Integer.valueOf(i15);
        b bVar15 = this.b;
        Integer num10 = bVar.y;
        if (num10 == null) {
            i16 = d2.getDimensionPixelSize(10, resources.getDimensionPixelSize(com.sec.android.gallery3d.R.dimen.m3_badge_with_text_vertical_padding));
        } else {
            i16 = num10.intValue();
        }
        bVar15.y = Integer.valueOf(i16);
        b bVar16 = this.b;
        Integer num11 = bVar.z;
        if (num11 == null) {
            i17 = d2.getDimensionPixelOffset(18, 0);
        } else {
            i17 = num11.intValue();
        }
        bVar16.z = Integer.valueOf(i17);
        b bVar17 = this.b;
        Integer num12 = bVar.f845A;
        if (num12 == null) {
            i18 = d2.getDimensionPixelOffset(25, 0);
        } else {
            i18 = num12.intValue();
        }
        bVar17.f845A = Integer.valueOf(i18);
        b bVar18 = this.b;
        Integer num13 = bVar.B;
        if (num13 == null) {
            i19 = d2.getDimensionPixelOffset(19, bVar18.z.intValue());
        } else {
            i19 = num13.intValue();
        }
        bVar18.B = Integer.valueOf(i19);
        b bVar19 = this.b;
        Integer num14 = bVar.f846C;
        if (num14 == null) {
            i20 = d2.getDimensionPixelOffset(26, bVar19.f845A.intValue());
        } else {
            i20 = num14.intValue();
        }
        bVar19.f846C = Integer.valueOf(i20);
        b bVar20 = this.b;
        Integer num15 = bVar.f847F;
        if (num15 == null) {
            i21 = d2.getDimensionPixelOffset(20, 0);
        } else {
            i21 = num15.intValue();
        }
        bVar20.f847F = Integer.valueOf(i21);
        b bVar21 = this.b;
        Integer num16 = bVar.D;
        if (num16 == null) {
            i22 = 0;
        } else {
            i22 = num16.intValue();
        }
        bVar21.D = Integer.valueOf(i22);
        b bVar22 = this.b;
        Integer num17 = bVar.E;
        if (num17 == null) {
            i23 = 0;
        } else {
            i23 = num17.intValue();
        }
        bVar22.E = Integer.valueOf(i23);
        b bVar23 = this.b;
        Boolean bool2 = bVar.f848G;
        if (bool2 == null) {
            z3 = d2.getBoolean(0, false);
        } else {
            z3 = bool2.booleanValue();
        }
        bVar23.f848G = Boolean.valueOf(z3);
        d2.recycle();
        Locale locale = bVar.q;
        if (locale == null) {
            this.b.q = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            this.b.q = locale;
        }
        this.f855a = bVar;
    }
}
