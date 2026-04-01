package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.os.Bundle;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupMenuHelper$accessibilityDelegate$1", "Landroid/view/View$AccessibilityDelegate;", "performAccessibilityAction", "", "host", "Landroid/view/View;", "action", "", "args", "Landroid/os/Bundle;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PopupMenuHelper$accessibilityDelegate$1 extends View.AccessibilityDelegate {
    public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
        j.e(view, "host");
        if (i2 == 16) {
            return false;
        }
        return super.performAccessibilityAction(view, i2, bundle);
    }
}
