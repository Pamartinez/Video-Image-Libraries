package S1;

import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q implements WindowInsetsAnimationControlListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SeslImmersiveScrollBehavior f790a;

    public q(SeslImmersiveScrollBehavior seslImmersiveScrollBehavior) {
        this.f790a = seslImmersiveScrollBehavior;
    }

    public final void onCancelled(WindowInsetsAnimationController windowInsetsAnimationController) {
        this.f790a.x();
    }

    public final void onFinished(WindowInsetsAnimationController windowInsetsAnimationController) {
        SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = this.f790a;
        seslImmersiveScrollBehavior.Y = null;
        seslImmersiveScrollBehavior.f1381X = null;
        seslImmersiveScrollBehavior.f1382c0 = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        if (r1 == 1) goto L_0x003d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onReady(android.view.WindowInsetsAnimationController r5, int r6) {
        /*
            r4 = this;
            com.google.android.material.appbar.SeslImmersiveScrollBehavior r4 = r4.f790a
            android.view.View r6 = r4.L
            if (r6 == 0) goto L_0x0050
            r6 = 0
            r4.f1381X = r6
            r4.Y = r5
            android.content.Context r5 = r4.f1375K
            boolean r5 = androidx.core.util.SeslDisplayUtils.isPinEdgeEnabled(r5)
            r6 = 0
            if (r5 == 0) goto L_0x003c
            android.view.WindowInsets r5 = r4.b0
            int r0 = android.view.WindowInsets.Type.navigationBars()
            android.graphics.Insets r5 = r5.getInsets(r0)
            android.content.Context r0 = r4.f1375K
            int r0 = androidx.core.util.SeslDisplayUtils.getPinnedEdgeWidth(r0)
            android.content.Context r1 = r4.f1375K
            int r1 = androidx.core.util.SeslDisplayUtils.getEdgeArea(r1)
            int r2 = r5.left
            if (r0 != r2) goto L_0x0034
            if (r1 != 0) goto L_0x0034
            r3 = r0
            r0 = r6
            r6 = r3
            goto L_0x003d
        L_0x0034:
            int r5 = r5.right
            if (r0 != r5) goto L_0x003c
            r5 = 1
            if (r1 != r5) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r0 = r6
        L_0x003d:
            int r5 = r4.R
            float r5 = (float) r5
            int r1 = r4.T
            float r1 = (float) r1
            android.view.WindowInsetsAnimationController r4 = r4.Y
            int r5 = (int) r5
            int r1 = (int) r1
            android.graphics.Insets r5 = android.graphics.Insets.of(r6, r5, r0, r1)
            r6 = 1065353216(0x3f800000, float:1.0)
            r4.setInsetsAndAlpha(r5, r6, r6)
        L_0x0050:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: S1.q.onReady(android.view.WindowInsetsAnimationController, int):void");
    }
}
