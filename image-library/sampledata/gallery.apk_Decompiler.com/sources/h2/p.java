package h2;

import Q1.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.R$attr;
import com.sec.android.gallery3d.R;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class p {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f1776a = {R$attr.colorPrimary};
    public static final int[] b = {R.attr.colorPrimaryVariant};

    public static void a(Context context, AttributeSet attributeSet, int i2, int i7) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f625P, i2, i7);
        boolean z = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        if (z) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(R.attr.isMaterialTheme, typedValue, true) || (typedValue.type == 18 && typedValue.data == 0)) {
                c(context, b, "Theme.MaterialComponents");
            }
        }
        c(context, f1776a, "Theme.AppCompat");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        if (r0.getResourceId(0, -1) != -1) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(android.content.Context r5, android.util.AttributeSet r6, int[] r7, int r8, int r9, int... r10) {
        /*
            int[] r0 = Q1.a.f625P
            android.content.res.TypedArray r0 = r5.obtainStyledAttributes(r6, r0, r8, r9)
            r1 = 2
            r2 = 0
            boolean r1 = r0.getBoolean(r1, r2)
            if (r1 != 0) goto L_0x0012
            r0.recycle()
            return
        L_0x0012:
            int r1 = r10.length
            r3 = 1
            r4 = -1
            if (r1 != 0) goto L_0x001f
            int r5 = r0.getResourceId(r2, r4)
            if (r5 == r4) goto L_0x003a
        L_0x001d:
            r2 = r3
            goto L_0x003a
        L_0x001f:
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7, r8, r9)
            int r6 = r10.length
            r7 = r2
        L_0x0025:
            if (r7 >= r6) goto L_0x0036
            r8 = r10[r7]
            int r8 = r5.getResourceId(r8, r4)
            if (r8 != r4) goto L_0x0033
            r5.recycle()
            goto L_0x003a
        L_0x0033:
            int r7 = r7 + 1
            goto L_0x0025
        L_0x0036:
            r5.recycle()
            goto L_0x001d
        L_0x003a:
            r0.recycle()
            if (r2 == 0) goto L_0x0040
            return
        L_0x0040:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant)."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.p.b(android.content.Context, android.util.AttributeSet, int[], int, int, int[]):void");
    }

    public static void c(Context context, int[] iArr, String str) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        int i2 = 0;
        while (i2 < iArr.length) {
            if (obtainStyledAttributes.hasValue(i2)) {
                i2++;
            } else {
                obtainStyledAttributes.recycle();
                throw new IllegalArgumentException(C0212a.m("The style on this component requires your app theme to be ", str, " (or a descendant)."));
            }
        }
        obtainStyledAttributes.recycle();
    }

    public static TypedArray d(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i7, int... iArr2) {
        a(context, attributeSet, i2, i7);
        b(context, attributeSet, iArr, i2, i7, iArr2);
        return context.obtainStyledAttributes(attributeSet, iArr, i2, i7);
    }
}
