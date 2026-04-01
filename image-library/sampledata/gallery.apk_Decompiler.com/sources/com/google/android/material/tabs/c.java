package com.google.android.material.tabs;

import android.graphics.drawable.Drawable;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public Integer f1501a;
    public Drawable b;

    /* renamed from: c  reason: collision with root package name */
    public CharSequence f1502c;
    public int d;
    public View e;
    public TabLayout f;
    public d g;

    public final void a() {
        TabLayout tabLayout = this.f;
        if (tabLayout != null) {
            tabLayout.selectTab(this);
            return;
        }
        throw new IllegalArgumentException("Tab not attached to a TabLayout");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r0 == r4.d) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(android.graphics.drawable.Drawable r4) {
        /*
            r3 = this;
            r3.b = r4
            com.google.android.material.tabs.TabLayout r4 = r3.f
            int r0 = r4.tabGravity
            r1 = 1
            if (r0 == r1) goto L_0x000e
            int r0 = r4.mode
            r2 = 2
            if (r0 != r2) goto L_0x0011
        L_0x000e:
            r4.updateTabViews(r1)
        L_0x0011:
            com.google.android.material.tabs.d r3 = r3.g
            if (r3 == 0) goto L_0x0038
            r3.e()
            com.google.android.material.tabs.c r4 = r3.d
            if (r4 == 0) goto L_0x0034
            com.google.android.material.tabs.TabLayout r0 = r4.f
            if (r0 == 0) goto L_0x002c
            int r0 = r0.getSelectedTabPosition()
            r2 = -1
            if (r0 == r2) goto L_0x0034
            int r4 = r4.d
            if (r0 != r4) goto L_0x0034
            goto L_0x0035
        L_0x002c:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Tab not attached to a TabLayout"
            r3.<init>(r4)
            throw r3
        L_0x0034:
            r1 = 0
        L_0x0035:
            r3.setSelected(r1)
        L_0x0038:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.c.b(android.graphics.drawable.Drawable):void");
    }
}
