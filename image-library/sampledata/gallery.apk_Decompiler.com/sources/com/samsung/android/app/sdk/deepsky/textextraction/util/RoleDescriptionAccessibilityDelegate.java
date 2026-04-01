package com.samsung.android.app.sdk.deepsky.textextraction.util;

import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/RoleDescriptionAccessibilityDelegate;", "Landroidx/core/view/AccessibilityDelegateCompat;", "", "roleDescription", "<init>", "(Ljava/lang/String;)V", "Landroid/view/View;", "host", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "info", "Lme/x;", "onInitializeAccessibilityNodeInfo", "(Landroid/view/View;Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;)V", "Ljava/lang/String;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RoleDescriptionAccessibilityDelegate extends AccessibilityDelegateCompat {
    private final String roleDescription;

    public RoleDescriptionAccessibilityDelegate(String str) {
        j.e(str, "roleDescription");
        this.roleDescription = str;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        j.e(view, "host");
        j.e(accessibilityNodeInfoCompat, "info");
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setRoleDescription(this.roleDescription);
    }
}
