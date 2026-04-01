package com.samsung.android.sdk.bixby2.labs.data;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0001¨\u0006\u0003"}, d2 = {"toViewNodeInfo", "Lcom/samsung/android/sdk/bixby2/labs/data/ViewNodeInfo;", "Landroid/view/accessibility/AccessibilityNodeInfo;", "bixbyappsdk_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewNodeInfoKt {
    public static final ViewNodeInfo toViewNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        String str;
        String str2;
        String str3;
        AccessibilityNodeInfo accessibilityNodeInfo2 = accessibilityNodeInfo;
        j.e(accessibilityNodeInfo2, "<this>");
        Rect rect = new Rect();
        accessibilityNodeInfo2.getBoundsInScreen(rect);
        ArrayList arrayList = new ArrayList();
        int childCount = accessibilityNodeInfo2.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo2.getChild(i2);
            if (child != null) {
                arrayList.add(toViewNodeInfo(child));
            }
        }
        boolean isCheckable = accessibilityNodeInfo2.isCheckable();
        boolean isChecked = accessibilityNodeInfo2.isChecked();
        String obj = accessibilityNodeInfo2.getClassName().toString();
        boolean isClickable = accessibilityNodeInfo2.isClickable();
        CharSequence contentDescription = accessibilityNodeInfo2.getContentDescription();
        if (contentDescription != null) {
            str2 = contentDescription.toString();
            str = null;
        } else {
            str2 = null;
            str = null;
        }
        boolean isEnabled = accessibilityNodeInfo2.isEnabled();
        String str4 = str;
        boolean isFocusable = accessibilityNodeInfo2.isFocusable();
        String str5 = str4;
        boolean isFocused = accessibilityNodeInfo2.isFocused();
        CharSequence hintText = accessibilityNodeInfo2.getHintText();
        if (hintText != null) {
            str3 = hintText.toString();
        } else {
            str3 = str5;
        }
        String str6 = str5;
        String str7 = str3;
        boolean isLongClickable = accessibilityNodeInfo2.isLongClickable();
        boolean isSelected = accessibilityNodeInfo2.isSelected();
        CharSequence text = accessibilityNodeInfo2.getText();
        if (text != null) {
            str6 = text.toString();
        }
        return new ViewNodeInfo(isCheckable, isChecked, obj, isClickable, str2, rect, isEnabled, isFocusable, isFocused, str7, isLongClickable, isSelected, str6, accessibilityNodeInfo2.isVisibleToUser() ^ true ? 1 : 0, accessibilityNodeInfo2.getChildCount(), arrayList);
    }
}
