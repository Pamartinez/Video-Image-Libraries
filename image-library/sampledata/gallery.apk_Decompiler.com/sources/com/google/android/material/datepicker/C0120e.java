package com.google.android.material.datepicker;

import android.widget.BaseAdapter;
import java.util.Calendar;

/* renamed from: com.google.android.material.datepicker.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0120e extends BaseAdapter {
    public final Calendar d;
    public final int e;
    public final int f;

    public C0120e() {
        Calendar c5 = D.c((Calendar) null);
        this.d = c5;
        this.e = c5.getMaximum(7);
        this.f = c5.getFirstDayOfWeek();
    }

    public final int getCount() {
        return this.e;
    }

    public final Object getItem(int i2) {
        int i7 = this.e;
        if (i2 >= i7) {
            return null;
        }
        int i8 = i2 + this.f;
        if (i8 > i7) {
            i8 -= i7;
        }
        return Integer.valueOf(i8);
    }

    public final long getItemId(int i2) {
        return 0;
    }

    /* JADX WARNING: type inference failed for: r4v5, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r3, android.view.View r4, android.view.ViewGroup r5) {
        /*
            r2 = this;
            r0 = r4
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r4 != 0) goto L_0x0010
            r4 = 2131493286(0x7f0c01a6, float:1.8610048E38)
            r0 = 0
            android.view.View r4 = c0.C0086a.d(r5, r4, r5, r0)
            r0 = r4
            android.widget.TextView r0 = (android.widget.TextView) r0
        L_0x0010:
            int r4 = r2.f
            int r3 = r3 + r4
            int r4 = r2.e
            if (r3 <= r4) goto L_0x0018
            int r3 = r3 - r4
        L_0x0018:
            java.util.Calendar r2 = r2.d
            r4 = 7
            r2.set(r4, r3)
            android.content.res.Resources r3 = r0.getResources()
            android.content.res.Configuration r3 = r3.getConfiguration()
            java.util.Locale r3 = r3.locale
            r1 = 4
            java.lang.String r3 = r2.getDisplayName(r4, r1, r3)
            r0.setText(r3)
            android.content.Context r3 = r5.getContext()
            r5 = 2131887733(0x7f120675, float:1.9410081E38)
            java.lang.String r3 = r3.getString(r5)
            r5 = 2
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r2 = r2.getDisplayName(r4, r5, r1)
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r2 = java.lang.String.format(r3, r2)
            r0.setContentDescription(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.C0120e.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public C0120e(int i2) {
        Calendar c5 = D.c((Calendar) null);
        this.d = c5;
        this.e = c5.getMaximum(7);
        this.f = i2;
    }
}
