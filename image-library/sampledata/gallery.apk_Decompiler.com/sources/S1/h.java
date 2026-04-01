package S1;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h implements e {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ Object e;

    public h(SeslImmersiveScrollBehavior seslImmersiveScrollBehavior) {
        this.e = seslImmersiveScrollBehavior;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:84:0x017a, code lost:
        if (r6 == 1) goto L_0x017e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x03c9  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x03e3  */
    /* JADX WARNING: Removed duplicated region for block: B:235:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0190  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onOffsetChanged(com.google.android.material.appbar.AppBarLayout r18, int r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            int r2 = r0.d
            switch(r2) {
                case 0: goto L_0x029d;
                default: goto L_0x0009;
            }
        L_0x0009:
            java.lang.Object r0 = r0.e
            com.google.android.material.appbar.SeslImmersiveScrollBehavior r0 = (com.google.android.material.appbar.SeslImmersiveScrollBehavior) r0
            com.google.android.material.appbar.AppBarLayout r2 = r0.f1372H
            java.lang.String r3 = "SeslImmersiveScrollBehavior"
            if (r2 == 0) goto L_0x0021
            boolean r2 = r2.isDetachedState()
            if (r2 != 0) goto L_0x001a
            goto L_0x0021
        L_0x001a:
            java.lang.String r0 = "AppBarLayout was DetachedState. Skip onOffsetChanged"
            android.util.Log.e(r3, r0)
            goto L_0x029c
        L_0x0021:
            boolean r2 = r0.V
            r4 = 0
            r5 = 0
            if (r2 != 0) goto L_0x0045
            android.view.View r1 = r0.f1377N
            if (r1 == 0) goto L_0x002e
            r1.setTranslationY(r5)
        L_0x002e:
            android.view.View r1 = r0.f1378O
            if (r1 == 0) goto L_0x0035
            r1.setTranslationY(r5)
        L_0x0035:
            android.view.View r1 = r0.Q
            if (r1 == 0) goto L_0x003c
            r1.setTranslationY(r5)
        L_0x003c:
            com.google.android.material.appbar.AppBarLayout r0 = r0.f1372H
            if (r0 == 0) goto L_0x029c
            r0.onImmOffsetChanged(r4)
            goto L_0x029c
        L_0x0045:
            android.view.View r2 = r0.Q
            if (r2 == 0) goto L_0x004e
            int r2 = r2.getHeight()
            goto L_0x004f
        L_0x004e:
            r2 = r4
        L_0x004f:
            float r6 = r18.seslGetCollapsedHeight()
            int r7 = r0.S
            int r7 = r7 + r2
            float r7 = (float) r7
            int r8 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            r9 = 1065353216(0x3f800000, float:1.0)
            if (r8 != 0) goto L_0x005f
            r10 = r9
            goto L_0x0060
        L_0x005f:
            r10 = r6
        L_0x0060:
            float r7 = r7 / r10
            int r10 = r18.getTotalScrollRange()
            int r11 = r18.seslGetAdditionalScrollRange()
            int r10 = r10 - r11
            int r10 = r10 + r1
            float r10 = (float) r10
            float r10 = r10 - r6
            int r11 = r0.R
            float r11 = (float) r11
            float r12 = r10 + r11
            float r7 = r7 * r10
            r13 = 1073741824(0x40000000, float:2.0)
            float r7 = r7 * r13
            float r11 = java.lang.Math.min(r11, r12)
            int r13 = r0.S
            float r13 = (float) r13
            float r14 = r7 + r13
            float r13 = java.lang.Math.min(r13, r14)
            float r13 = java.lang.Math.max(r13, r5)
            int r14 = r18.getBottom()
            float r14 = (float) r14
            int r14 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r14 > 0) goto L_0x022a
            boolean r14 = r0.w()
            r15 = 1
            if (r14 == 0) goto L_0x01a0
            android.view.View r6 = r0.Q
            if (r6 == 0) goto L_0x00cf
            int r6 = r6.getVisibility()
            r8 = 8
            if (r6 == r8) goto L_0x00cf
            if (r2 == 0) goto L_0x00cf
            float r6 = (float) r2
            float r6 = r6 + r7
            float r6 = java.lang.Math.min(r6, r13)
            android.view.View r8 = r0.Q
            int r14 = java.lang.Math.round(r6)
            int r14 = -r14
            float r14 = (float) r14
            r8.setTranslationY(r14)
            android.view.View r8 = r0.Q
            int r8 = r8.getVisibility()
            if (r8 != 0) goto L_0x00bf
            goto L_0x00c0
        L_0x00bf:
            r2 = r4
        L_0x00c0:
            float r2 = (float) r2
            float r2 = r2 + r6
            float r2 = java.lang.Math.max(r2, r5)
            int r6 = r18.getTotalScrollRange()
        L_0x00ca:
            float r6 = (float) r6
            float r2 = r2 + r6
            float r1 = (float) r1
            float r2 = r2 + r1
            goto L_0x00d8
        L_0x00cf:
            float r2 = java.lang.Math.max(r13, r5)
            int r6 = r18.getTotalScrollRange()
            goto L_0x00ca
        L_0x00d8:
            android.view.View r1 = r0.f1378O
            if (r1 == 0) goto L_0x00fa
            android.view.WindowInsets r1 = r0.b0
            boolean r1 = com.google.android.material.appbar.SeslImmersiveScrollBehavior.C(r1)
            if (r1 != 0) goto L_0x00f4
            android.view.View r1 = r0.f1378O
            int r6 = java.lang.Math.round(r7)
            int r6 = java.lang.Math.min(r4, r6)
            int r6 = -r6
            float r6 = (float) r6
            r1.setTranslationY(r6)
            goto L_0x0108
        L_0x00f4:
            android.view.View r1 = r0.f1378O
            r1.setTranslationY(r5)
            goto L_0x0108
        L_0x00fa:
            int r1 = r0.S
            if (r1 == 0) goto L_0x0108
            r0.z()
            android.view.View r1 = r0.f1378O
            if (r1 == 0) goto L_0x0108
            r1.setTranslationY(r5)
        L_0x0108:
            android.view.View r1 = r0.f1377N
            if (r1 == 0) goto L_0x0113
            float r6 = java.lang.Math.min(r5, r10)
            r1.setTranslationY(r6)
        L_0x0113:
            float r1 = r0.U
            int r1 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r1 == 0) goto L_0x0294
            r0.U = r12
            android.view.WindowInsetsAnimationController r1 = r0.Y
            if (r1 == 0) goto L_0x0294
            boolean r1 = r1.isFinished()
            if (r1 == 0) goto L_0x012c
            java.lang.String r1 = "AnimationController is already finished by App side"
            android.util.Log.e(r3, r1)
            goto L_0x0294
        L_0x012c:
            int r1 = (int) r13
            android.view.WindowInsetsAnimationController r3 = r0.Y
            if (r3 == 0) goto L_0x014c
            android.view.View r6 = r0.L
            if (r6 != 0) goto L_0x0136
            goto L_0x014c
        L_0x0136:
            android.graphics.Insets r3 = r3.getShownStateInsets()
            int r3 = r3.bottom
            if (r1 == r3) goto L_0x0140
            r1 = r15
            goto L_0x0141
        L_0x0140:
            r1 = r4
        L_0x0141:
            boolean r3 = r0.f1387j0
            if (r1 == r3) goto L_0x014c
            r0.f1387j0 = r1
            android.view.View r3 = r0.L
            androidx.reflect.view.SeslDecorViewReflector.semSetForceHideRoundedCorner(r3, r1)
        L_0x014c:
            android.content.Context r1 = r0.f1375K
            boolean r1 = androidx.core.util.SeslDisplayUtils.isPinEdgeEnabled(r1)
            if (r1 == 0) goto L_0x017d
            android.view.WindowInsets r1 = r0.b0
            int r3 = android.view.WindowInsets.Type.navigationBars()
            android.graphics.Insets r1 = r1.getInsets(r3)
            android.content.Context r3 = r0.f1375K
            int r3 = androidx.core.util.SeslDisplayUtils.getPinnedEdgeWidth(r3)
            android.content.Context r6 = r0.f1375K
            int r6 = androidx.core.util.SeslDisplayUtils.getEdgeArea(r6)
            int r8 = r1.left
            if (r3 != r8) goto L_0x0176
            if (r6 != 0) goto L_0x0176
            r16 = r4
            r4 = r3
            r3 = r16
            goto L_0x017e
        L_0x0176:
            int r1 = r1.right
            if (r3 != r1) goto L_0x017d
            if (r6 != r15) goto L_0x017d
            goto L_0x017e
        L_0x017d:
            r3 = r4
        L_0x017e:
            int r1 = r0.T
            float r1 = (float) r1
            float r7 = r7 + r1
            float r1 = java.lang.Math.min(r1, r7)
            float r1 = java.lang.Math.max(r1, r5)
            int r5 = r0.T
            float r6 = (float) r5
            float r6 = r6 - r13
            if (r5 == 0) goto L_0x0191
            r15 = r5
        L_0x0191:
            float r5 = (float) r15
            float r6 = r6 / r5
            android.view.WindowInsetsAnimationController r5 = r0.Y
            int r7 = (int) r11
            int r1 = (int) r1
            android.graphics.Insets r1 = android.graphics.Insets.of(r4, r7, r3, r1)
            r5.setInsetsAndAlpha(r1, r9, r6)
            goto L_0x0294
        L_0x01a0:
            android.view.View r3 = r0.f1377N
            if (r3 == 0) goto L_0x01a7
            r3.setTranslationY(r5)
        L_0x01a7:
            android.view.View r3 = r0.f1378O
            if (r3 == 0) goto L_0x01ae
            r3.setTranslationY(r5)
        L_0x01ae:
            com.google.android.material.appbar.AppBarLayout r3 = r0.f1372H
            if (r3 == 0) goto L_0x01e6
            int r3 = r3.getTotalScrollRange()
            int r3 = r3 + r1
            float r1 = (float) r3
            android.view.View r3 = r0.Q
            if (r3 == 0) goto L_0x01e5
            float r2 = (float) r2
            if (r8 == 0) goto L_0x01c0
            goto L_0x01c1
        L_0x01c0:
            r6 = r9
        L_0x01c1:
            float r3 = r2 / r6
            com.google.android.material.appbar.AppBarLayout r6 = r0.f1372H
            int r6 = r6.getBottom()
            float r6 = (float) r6
            float r6 = r6 * r3
            float r2 = r2 - r6
            android.view.View r3 = r0.Q
            float r6 = java.lang.Math.max(r2, r5)
            r3.setTranslationY(r6)
            android.view.View r3 = r0.Q
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r1 = r1 + r3
            float r2 = java.lang.Math.max(r2, r5)
            float r1 = r1 - r2
            int r1 = (int) r1
            float r5 = (float) r1
            goto L_0x01e6
        L_0x01e5:
            r5 = r1
        L_0x01e6:
            com.google.android.material.appbar.AppBarLayout r1 = r0.f1372H
            if (r1 != 0) goto L_0x01eb
            goto L_0x0227
        L_0x01eb:
            android.view.WindowInsetsAnimationController r2 = r0.Y
            android.view.View r3 = r0.f1376M
            if (r3 != 0) goto L_0x0200
            android.view.View r1 = r1.getRootView()
            r0.L = r1
            r3 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r1 = r1.findViewById(r3)
            r0.f1376M = r1
        L_0x0200:
            if (r2 != 0) goto L_0x020a
            android.os.CancellationSignal r1 = r0.f1381X
            if (r1 == 0) goto L_0x0227
            r1.cancel()
            goto L_0x0227
        L_0x020a:
            android.graphics.Insets r1 = r2.getCurrentInsets()
            int r1 = r1.bottom
            android.graphics.Insets r3 = r2.getShownStateInsets()
            int r3 = r3.bottom
            android.graphics.Insets r6 = r2.getHiddenStateInsets()
            int r6 = r6.bottom
            if (r1 != r3) goto L_0x0222
            r2.finish(r15)
            goto L_0x0227
        L_0x0222:
            if (r1 != r6) goto L_0x0227
            r2.finish(r4)
        L_0x0227:
            r2 = r5
            goto L_0x0294
        L_0x022a:
            com.google.android.material.appbar.AppBarLayout r2 = r0.f1372H
            if (r2 == 0) goto L_0x0235
            int r2 = r2.getTotalScrollRange()
            int r2 = r2 + r1
            float r1 = (float) r2
            goto L_0x0236
        L_0x0235:
            r1 = r5
        L_0x0236:
            boolean r2 = r0.d0
            if (r2 == 0) goto L_0x0249
            android.view.View r2 = r0.Q
            if (r2 == 0) goto L_0x0249
            r2.setTranslationY(r5)
            android.view.View r2 = r0.Q
            int r2 = r2.getHeight()
            float r2 = (float) r2
            float r1 = r1 + r2
        L_0x0249:
            r2 = r1
            boolean r1 = r0.d0
            if (r1 != 0) goto L_0x0294
            android.view.View r1 = r0.Q
            if (r1 == 0) goto L_0x0294
            android.view.WindowInsets r1 = r0.b0
            if (r1 == 0) goto L_0x0294
            boolean r1 = r0.D()
            if (r1 == 0) goto L_0x0277
            android.view.View r1 = r0.Q
            int r3 = r0.S
            int r3 = -r3
            float r3 = (float) r3
            r1.setTranslationY(r3)
            android.view.View r1 = r0.f1378O
            if (r1 == 0) goto L_0x0288
            float r1 = r1.getTranslationY()
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 == 0) goto L_0x0288
            android.view.View r1 = r0.f1378O
            r1.setTranslationY(r5)
            goto L_0x0288
        L_0x0277:
            android.view.View r1 = r0.f1378O
            if (r1 == 0) goto L_0x0288
            float r1 = r1.getTranslationY()
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 == 0) goto L_0x0288
            android.view.View r1 = r0.Q
            r1.setTranslationY(r5)
        L_0x0288:
            android.view.View r1 = r0.Q
            int r1 = r1.getHeight()
            float r1 = (float) r1
            float r2 = r2 + r1
            int r1 = r0.S
            float r1 = (float) r1
            float r2 = r2 + r1
        L_0x0294:
            com.google.android.material.appbar.AppBarLayout r0 = r0.f1372H
            if (r0 == 0) goto L_0x029c
            int r1 = (int) r2
            r0.onImmOffsetChanged(r1)
        L_0x029c:
            return
        L_0x029d:
            java.lang.Object r0 = r0.e
            com.google.android.material.appbar.CollapsingToolbarLayout r0 = (com.google.android.material.appbar.CollapsingToolbarLayout) r0
            r0.B = r1
            B0.a r2 = r0.f1357I
            java.lang.Object r2 = r2.d
            android.widget.FrameLayout r2 = (android.widget.FrameLayout) r2
            int r3 = -r1
            float r4 = (float) r3
            r5 = 1077936128(0x40400000, float:3.0)
            float r5 = r4 / r5
            r2.setTranslationY(r5)
            androidx.core.view.WindowInsetsCompat r5 = r0.D
            r6 = 0
            if (r5 == 0) goto L_0x02bc
            int r5 = r5.getSystemWindowInsetTop()
            goto L_0x02bd
        L_0x02bc:
            r5 = r6
        L_0x02bd:
            int r7 = r0.getChildCount()
            r8 = r6
        L_0x02c2:
            r9 = 1
            if (r8 >= r7) goto L_0x0326
            android.view.View r10 = r0.getChildAt(r8)
            android.view.ViewGroup$LayoutParams r11 = r10.getLayoutParams()
            S1.g r11 = (S1.g) r11
            S1.x r12 = com.google.android.material.appbar.CollapsingToolbarLayout.b(r10)
            android.view.ViewGroup r13 = r0.f
            if (r13 == 0) goto L_0x02f0
            boolean r13 = r10 instanceof androidx.appcompat.widget.ActionBarContextView
            if (r13 == 0) goto L_0x02f0
            r13 = r10
            androidx.appcompat.widget.ActionBarContextView r13 = (androidx.appcompat.widget.ActionBarContextView) r13
            boolean r13 = r13.getIsActionModeAccessibilityOn()
            if (r13 == 0) goto L_0x02eb
            android.view.ViewGroup r13 = r0.f
            r14 = 4
            r13.setImportantForAccessibility(r14)
            goto L_0x02f0
        L_0x02eb:
            android.view.ViewGroup r13 = r0.f
            r13.setImportantForAccessibility(r9)
        L_0x02f0:
            int r13 = r11.f780a
            if (r13 == r9) goto L_0x0303
            r9 = 2
            if (r13 == r9) goto L_0x02f8
            goto L_0x0323
        L_0x02f8:
            float r9 = r11.b
            float r9 = r9 * r4
            int r9 = java.lang.Math.round(r9)
            r12.b(r9)
            goto L_0x0323
        L_0x0303:
            S1.x r9 = com.google.android.material.appbar.CollapsingToolbarLayout.b(r10)
            android.view.ViewGroup$LayoutParams r11 = r10.getLayoutParams()
            S1.g r11 = (S1.g) r11
            int r13 = r0.getHeight()
            int r9 = r9.b
            int r13 = r13 - r9
            int r9 = r10.getHeight()
            int r13 = r13 - r9
            int r9 = r11.bottomMargin
            int r13 = r13 - r9
            int r9 = androidx.core.math.MathUtils.clamp((int) r3, (int) r6, (int) r13)
            r12.b(r9)
        L_0x0323:
            int r8 = r8 + 1
            goto L_0x02c2
        L_0x0326:
            r0.g()
            android.graphics.drawable.Drawable r3 = r0.s
            if (r3 == 0) goto L_0x0332
            if (r5 <= 0) goto L_0x0332
            androidx.core.view.ViewCompat.postInvalidateOnAnimation(r0)
        L_0x0332:
            boolean r3 = r0.f1363P
            if (r3 == 0) goto L_0x03e7
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            r4 = r18
            r4.getWindowVisibleDisplayFrame(r3)
            int r3 = r4.getTop()
            int r3 = java.lang.Math.abs(r3)
            int r5 = r0.getHeight()
            float r5 = (float) r5
            r7 = 1041395352(0x3e126e98, float:0.143)
            float r5 = r5 * r7
            r7 = 1120403456(0x42c80000, float:100.0)
            float r7 = r7 / r5
            float r3 = (float) r3
            r8 = 0
            float r10 = r3 - r8
            float r10 = r10 * r7
            r7 = 1132396544(0x437f0000, float:255.0)
            float r10 = r7 - r10
            int r11 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r11 >= 0) goto L_0x0363
            r10 = r8
            goto L_0x036e
        L_0x0363:
            int r11 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r11 > 0) goto L_0x036d
            if (r1 != 0) goto L_0x036e
            int r1 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r1 >= 0) goto L_0x036e
        L_0x036d:
            r10 = r7
        L_0x036e:
            float r10 = r10 / r7
            int r1 = r4.getBottom()
            float r11 = r0.T
            int r11 = (int) r11
            if (r1 <= r11) goto L_0x0381
            boolean r1 = r4.seslIsCollapsed()
            if (r1 == 0) goto L_0x037f
            goto L_0x0381
        L_0x037f:
            r1 = r6
            goto L_0x0382
        L_0x0381:
            r1 = r9
        L_0x0382:
            if (r1 == 0) goto L_0x0386
            r4 = r8
            goto L_0x0387
        L_0x0386:
            r4 = r10
        L_0x0387:
            r2.setAlpha(r4)
            android.view.ViewGroup r2 = r0.f
            boolean r4 = r2 instanceof androidx.appcompat.widget.Toolbar
            if (r4 == 0) goto L_0x0401
            androidx.appcompat.widget.Toolbar r2 = (androidx.appcompat.widget.Toolbar) r2
            r4 = 1065353216(0x3f800000, float:1.0)
            int r4 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x039c
            r2.setTitleAccessibilityEnabled(r6)
            goto L_0x03a3
        L_0x039c:
            int r4 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r4 != 0) goto L_0x03a3
            r2.setTitleAccessibilityEnabled(r9)
        L_0x03a3:
            if (r1 == 0) goto L_0x03aa
            r2.setTitleAccessibilityEnabled(r9)
        L_0x03a8:
            r8 = r7
            goto L_0x03c3
        L_0x03aa:
            int r1 = r0.getHeight()
            float r1 = (float) r1
            r4 = 1051931443(0x3eb33333, float:0.35)
            float r1 = r1 * r4
            r4 = 1125515264(0x43160000, float:150.0)
            float r4 = r4 / r5
            float r3 = r3 - r1
            float r3 = r3 * r4
            int r1 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r1 >= 0) goto L_0x03bd
            goto L_0x03c3
        L_0x03bd:
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 <= 0) goto L_0x03c2
            goto L_0x03a8
        L_0x03c2:
            r8 = r3
        L_0x03c3:
            int r1 = (int) r8
            float r8 = r8 / r7
            boolean r0 = r0.V
            if (r0 == 0) goto L_0x03d9
            r2.seslSetTitleAlpha(r8)
            android.graphics.drawable.Drawable r0 = r2.getBackground()
            if (r0 == 0) goto L_0x03d9
            android.graphics.drawable.Drawable r0 = r0.mutate()
            r0.setAlpha(r1)
        L_0x03d9:
            java.lang.CharSequence r0 = r2.getSubtitle()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0401
            r2.seslSetSubtitleAlpha(r8)
            goto L_0x0401
        L_0x03e7:
            boolean r2 = r0.f1368p
            if (r2 == 0) goto L_0x0401
            int r2 = r0.getHeight()
            int r3 = androidx.core.view.ViewCompat.getMinimumHeight(r0)
            int r2 = r2 - r3
            int r2 = r2 - r5
            h2.c r0 = r0.n
            int r1 = java.lang.Math.abs(r1)
            float r1 = (float) r1
            float r2 = (float) r2
            float r1 = r1 / r2
            r0.p(r1)
        L_0x0401:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: S1.h.onOffsetChanged(com.google.android.material.appbar.AppBarLayout, int):void");
    }

    public h(CollapsingToolbarLayout collapsingToolbarLayout) {
        this.e = collapsingToolbarLayout;
        int i2 = CollapsingToolbarLayout.f1351W;
        collapsingToolbarLayout.e();
    }
}
