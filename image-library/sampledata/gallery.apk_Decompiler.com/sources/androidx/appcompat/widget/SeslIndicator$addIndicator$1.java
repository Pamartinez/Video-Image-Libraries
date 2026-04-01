package androidx.appcompat.widget;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R$string;
import androidx.appcompat.widget.SeslIndicator;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"androidx/appcompat/widget/SeslIndicator$addIndicator$1", "Landroid/view/View$AccessibilityDelegate;", "Landroid/view/View;", "host", "Landroid/view/accessibility/AccessibilityNodeInfo;", "info", "Lme/x;", "onInitializeAccessibilityNodeInfo", "(Landroid/view/View;Landroid/view/accessibility/AccessibilityNodeInfo;)V", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslIndicator$addIndicator$1 extends View.AccessibilityDelegate {
    final /* synthetic */ SeslIndicator.PageIndicatorMarker $dot;
    final /* synthetic */ SeslIndicator this$0;

    public SeslIndicator$addIndicator$1(SeslIndicator seslIndicator, SeslIndicator.PageIndicatorMarker pageIndicatorMarker) {
        this.this$0 = seslIndicator;
        this.$dot = pageIndicatorMarker;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        j.e(view, "host");
        j.e(accessibilityNodeInfo, "info");
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setContentDescription(this.this$0.getResources().getString(R$string.sesl_appbar_suggest_pagination, new Object[]{Integer.valueOf(this.this$0.indicator.indexOf(this.$dot) + 1), Integer.valueOf(this.this$0.getSize())}));
    }
}
