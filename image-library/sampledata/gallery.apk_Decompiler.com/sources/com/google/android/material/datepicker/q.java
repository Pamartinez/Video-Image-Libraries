package com.google.android.material.datepicker;

import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends AccessibilityDelegateCompat {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1462a;
    public final /* synthetic */ Object b;

    public /* synthetic */ q(int i2, Object obj) {
        this.f1462a = i2;
        this.b = obj;
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String str;
        switch (this.f1462a) {
            case 0:
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCollectionInfo((Object) null);
                return;
            default:
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                p pVar = (p) this.b;
                if (pVar.f1461o.getVisibility() == 0) {
                    str = pVar.getString(R.string.mtrl_picker_toggle_to_year_selection);
                } else {
                    str = pVar.getString(R.string.mtrl_picker_toggle_to_day_selection);
                }
                accessibilityNodeInfoCompat.setHintText(str);
                return;
        }
    }
}
