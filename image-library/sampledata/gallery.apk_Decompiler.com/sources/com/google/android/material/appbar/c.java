package com.google.android.material.appbar;

import android.os.Bundle;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import com.google.android.material.appbar.AppBarLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends AccessibilityDelegateCompat {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f1390a;
    public final /* synthetic */ CoordinatorLayout b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AppBarLayout.BaseBehavior f1391c;

    public c(CoordinatorLayout coordinatorLayout, AppBarLayout.BaseBehavior baseBehavior, AppBarLayout appBarLayout) {
        this.f1391c = baseBehavior;
        this.f1390a = appBarLayout;
        this.b = coordinatorLayout;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0015, code lost:
        r0 = r4.b;
        r4 = r4.f1391c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onInitializeAccessibilityNodeInfo(android.view.View r5, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r6) {
        /*
            r4 = this;
            super.onInitializeAccessibilityNodeInfo(r5, r6)
            java.lang.Class<android.widget.ScrollView> r5 = android.widget.ScrollView.class
            java.lang.String r5 = r5.getName()
            r6.setClassName(r5)
            com.google.android.material.appbar.AppBarLayout r5 = r4.f1390a
            int r0 = r5.getTotalScrollRange()
            if (r0 != 0) goto L_0x0015
            goto L_0x0075
        L_0x0015:
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = r4.b
            com.google.android.material.appbar.AppBarLayout$BaseBehavior r4 = r4.f1391c
            android.view.View r0 = com.google.android.material.appbar.AppBarLayout.BaseBehavior.h(r4, r0)
            if (r0 != 0) goto L_0x0020
            goto L_0x0075
        L_0x0020:
            r4.getClass()
            int r1 = r5.getChildCount()
            r2 = 0
        L_0x0028:
            if (r2 >= r1) goto L_0x0075
            android.view.View r3 = r5.getChildAt(r2)
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            S1.d r3 = (S1.d) r3
            int r3 = r3.f777a
            if (r3 == 0) goto L_0x0072
            int r1 = r4.d()
            int r2 = r5.getTotalScrollRange()
            int r2 = -r2
            r3 = 1
            if (r1 == r2) goto L_0x004c
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r1 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD
            r6.addAction((androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) r1)
            r6.setScrollable(r3)
        L_0x004c:
            int r4 = r4.d()
            if (r4 == 0) goto L_0x0075
            r4 = -1
            boolean r4 = r0.canScrollVertically(r4)
            if (r4 == 0) goto L_0x0069
            int r4 = r5.getDownNestedPreScrollRange()
            int r4 = -r4
            if (r4 == 0) goto L_0x0075
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r4 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD
            r6.addAction((androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) r4)
            r6.setScrollable(r3)
            return
        L_0x0069:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r4 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD
            r6.addAction((androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) r4)
            r6.setScrollable(r3)
            return
        L_0x0072:
            int r2 = r2 + 1
            goto L_0x0028
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.c.onInitializeAccessibilityNodeInfo(android.view.View, androidx.core.view.accessibility.AccessibilityNodeInfoCompat):void");
    }

    public final boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
        AppBarLayout appBarLayout = this.f1390a;
        if (i2 == 4096) {
            appBarLayout.setExpanded(false);
            return true;
        } else if (i2 != 8192) {
            return super.performAccessibilityAction(view, i2, bundle);
        } else {
            AppBarLayout.BaseBehavior baseBehavior = this.f1391c;
            if (baseBehavior.d() != 0) {
                View h5 = AppBarLayout.BaseBehavior.h(baseBehavior, this.b);
                if (h5.canScrollVertically(-1)) {
                    int i7 = -appBarLayout.getDownNestedPreScrollRange();
                    if (i7 != 0) {
                        this.f1391c.onNestedPreScroll(this.b, this.f1390a, h5, 0, i7, new int[]{0, 0}, 1);
                        return true;
                    }
                } else {
                    appBarLayout.setExpanded(true);
                    return true;
                }
            }
            return false;
        }
    }
}
