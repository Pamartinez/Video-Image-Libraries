package com.samsung.android.gallery.app.ui.container.menu;

import B2.i;
import android.view.View;
import com.samsung.android.gallery.settings.activity.e;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabTouchDelegate {
    private OnMenuTabTouchListener mTouchListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMenuTabTouchListener {
        void onMenuTabTouched();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addOnTabTouchListener$0(OnMenuTabTouchListener onMenuTabTouchListener, View view) {
        this.mTouchListener = onMenuTabTouchListener;
        view.setOnTouchListener(new i(26, this));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        if (r4 != 3) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouched(android.view.View r3, android.view.MotionEvent r4) {
        /*
            r2 = this;
            int r4 = r4.getAction()
            r0 = 1
            if (r4 == 0) goto L_0x0022
            if (r4 == r0) goto L_0x000d
            r2 = 3
            if (r4 == r2) goto L_0x001d
            goto L_0x0025
        L_0x000d:
            com.samsung.android.gallery.app.ui.container.menu.BottomTabTouchDelegate$OnMenuTabTouchListener r2 = r2.mTouchListener
            java.util.Optional r2 = java.util.Optional.ofNullable(r2)
            ic.l r4 = new ic.l
            r1 = 12
            r4.<init>(r1)
            r2.ifPresent(r4)
        L_0x001d:
            r2 = 0
            r3.setPressed(r2)
            goto L_0x0025
        L_0x0022:
            r3.setPressed(r0)
        L_0x0025:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.menu.BottomTabTouchDelegate.onTouched(android.view.View, android.view.MotionEvent):boolean");
    }

    public void addOnTabTouchListener(View view, OnMenuTabTouchListener onMenuTabTouchListener) {
        Optional.ofNullable(view).ifPresent(new e(25, this, onMenuTabTouchListener));
    }

    public void removeMenuTabTouchListener() {
        this.mTouchListener = null;
    }
}
