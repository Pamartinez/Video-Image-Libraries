package com.samsung.android.app.sdk.deepsky.textextraction.util;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/util/ViewHelperUtils$addActionAccessibilityDelegate$1", "Landroid/view/View$AccessibilityDelegate;", "Landroid/view/View;", "host", "Landroid/view/accessibility/AccessibilityNodeInfo;", "info", "Lme/x;", "onInitializeAccessibilityNodeInfo", "(Landroid/view/View;Landroid/view/accessibility/AccessibilityNodeInfo;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewHelperUtils$addActionAccessibilityDelegate$1 extends View.AccessibilityDelegate {
    final /* synthetic */ boolean $isSelected;

    public ViewHelperUtils$addActionAccessibilityDelegate$1(boolean z) {
        this.$isSelected = z;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        int i2;
        j.e(view, "host");
        j.e(accessibilityNodeInfo, "info");
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        Context context = view.getContext();
        if (this.$isSelected) {
            i2 = R$string.unselect;
        } else {
            i2 = R$string.select;
        }
        String string = context.getString(i2);
        j.d(string, "getString(...)");
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, string));
    }
}
