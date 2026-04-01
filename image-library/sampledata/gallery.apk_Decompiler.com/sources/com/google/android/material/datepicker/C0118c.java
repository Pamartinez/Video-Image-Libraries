package com.google.android.material.datepicker;

import Q1.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.sec.android.gallery3d.R;
import og.k;
import x2.C0338e;

/* renamed from: com.google.android.material.datepicker.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0118c {

    /* renamed from: a  reason: collision with root package name */
    public final C0338e f1454a;
    public final C0338e b;

    public C0118c(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(k.N(context, R.attr.materialCalendarStyle, p.class.getCanonicalName()).data, a.f633x);
        C0338e.h(context, obtainStyledAttributes.getResourceId(4, 0));
        C0338e.h(context, obtainStyledAttributes.getResourceId(2, 0));
        C0338e.h(context, obtainStyledAttributes.getResourceId(3, 0));
        C0338e.h(context, obtainStyledAttributes.getResourceId(5, 0));
        ColorStateList u = B1.a.u(context, obtainStyledAttributes, 7);
        this.f1454a = C0338e.h(context, obtainStyledAttributes.getResourceId(9, 0));
        C0338e.h(context, obtainStyledAttributes.getResourceId(8, 0));
        this.b = C0338e.h(context, obtainStyledAttributes.getResourceId(10, 0));
        new Paint().setColor(u.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
