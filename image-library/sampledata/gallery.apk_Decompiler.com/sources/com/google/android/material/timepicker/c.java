package com.google.android.material.timepicker;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends AccessibilityDelegateCompat {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ClockFaceView f1558a;

    public c(ClockFaceView clockFaceView) {
        this.f1558a = clockFaceView;
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        int intValue = ((Integer) view.getTag(R.id.material_value_index)).intValue();
        if (intValue > 0) {
            accessibilityNodeInfoCompat.setTraversalAfter((View) this.f1558a.k.get(intValue - 1));
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, intValue, 1, false, view.isSelected()));
        accessibilityNodeInfoCompat.setClickable(true);
        accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
    }

    public final boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
        if (i2 != 16) {
            return super.performAccessibilityAction(view, i2, bundle);
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        ClockFaceView clockFaceView = this.f1558a;
        view.getHitRect(clockFaceView.f1548h);
        float centerX = (float) clockFaceView.f1548h.centerX();
        float centerY = (float) clockFaceView.f1548h.centerY();
        long j2 = uptimeMillis;
        clockFaceView.g.onTouchEvent(MotionEvent.obtain(uptimeMillis, j2, 0, centerX, centerY, 0));
        clockFaceView.g.onTouchEvent(MotionEvent.obtain(uptimeMillis, j2, 1, centerX, centerY, 0));
        return true;
    }
}
