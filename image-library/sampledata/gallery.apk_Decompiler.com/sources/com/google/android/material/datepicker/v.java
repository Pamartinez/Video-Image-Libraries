package com.google.android.material.datepicker;

import android.widget.BaseAdapter;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends BaseAdapter {
    public static final int g = D.c((Calendar) null).getMaximum(4);

    /* renamed from: h  reason: collision with root package name */
    public static final int f1473h = ((D.c((Calendar) null).getMaximum(7) + D.c((Calendar) null).getMaximum(5)) - 1);
    public final u d;
    public C0118c e;
    public final C0117b f;

    public v(u uVar, C0117b bVar) {
        this.d = uVar;
        this.f = bVar;
        throw null;
    }

    public final int a() {
        int i2 = this.f.f1451h;
        u uVar = this.d;
        Calendar calendar = uVar.d;
        int i7 = calendar.get(7);
        if (i2 <= 0) {
            i2 = calendar.getFirstDayOfWeek();
        }
        int i8 = i7 - i2;
        if (i8 < 0) {
            return i8 + uVar.g;
        }
        return i8;
    }

    /* renamed from: b */
    public final Long getItem(int i2) {
        if (i2 < a() || i2 > c()) {
            return null;
        }
        Calendar a7 = D.a(this.d.d);
        a7.set(5, (i2 - a()) + 1);
        return Long.valueOf(a7.getTimeInMillis());
    }

    public final int c() {
        return (a() + this.d.f1470h) - 1;
    }

    public final int getCount() {
        return f1473h;
    }

    public final long getItemId(int i2) {
        return (long) (i2 / this.d.g);
    }

    /* JADX WARNING: type inference failed for: r6v9, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r5, android.view.View r6, android.view.ViewGroup r7) {
        /*
            r4 = this;
            android.content.Context r0 = r7.getContext()
            com.google.android.material.datepicker.c r1 = r4.e
            if (r1 != 0) goto L_0x000f
            com.google.android.material.datepicker.c r1 = new com.google.android.material.datepicker.c
            r1.<init>(r0)
            r4.e = r1
        L_0x000f:
            r0 = r6
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r6 != 0) goto L_0x001f
            r6 = 2131493285(0x7f0c01a5, float:1.8610046E38)
            android.view.View r6 = c0.C0086a.d(r7, r6, r7, r1)
            r0 = r6
            android.widget.TextView r0 = (android.widget.TextView) r0
        L_0x001f:
            int r6 = r4.a()
            int r6 = r5 - r6
            if (r6 < 0) goto L_0x0055
            com.google.android.material.datepicker.u r7 = r4.d
            int r2 = r7.f1470h
            if (r6 < r2) goto L_0x002e
            goto L_0x0055
        L_0x002e:
            r2 = 1
            int r6 = r6 + r2
            r0.setTag(r7)
            android.content.res.Resources r7 = r0.getResources()
            android.content.res.Configuration r7 = r7.getConfiguration()
            java.util.Locale r7 = r7.locale
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r3 = "%d"
            java.lang.String r6 = java.lang.String.format(r7, r3, r6)
            r0.setText(r6)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L_0x005d
        L_0x0055:
            r6 = 8
            r0.setVisibility(r6)
            r0.setEnabled(r1)
        L_0x005d:
            java.lang.Long r4 = r4.getItem(r5)
            if (r4 != 0) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            if (r0 != 0) goto L_0x0067
        L_0x0066:
            return r0
        L_0x0067:
            r0.getContext()
            java.util.Calendar r4 = com.google.android.material.datepicker.D.b()
            r4.getTimeInMillis()
            r4 = 0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.v.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public final boolean hasStableIds() {
        return true;
    }
}
