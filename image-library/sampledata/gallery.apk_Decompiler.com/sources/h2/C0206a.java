package h2;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.NavigationMenuItemView;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import z2.q;
import z2.r;

/* renamed from: h2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0206a extends AccessibilityDelegateCompat {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1735a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0206a(int i2, Object obj) {
        this.f1735a = i2;
        this.b = obj;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.f1735a) {
            case 0:
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                accessibilityEvent.setChecked(((CheckableImageButton) this.b).d);
                return;
            default:
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                return;
        }
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        switch (this.f1735a) {
            case 0:
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                CheckableImageButton checkableImageButton = (CheckableImageButton) this.b;
                accessibilityNodeInfoCompat.setCheckable(checkableImageButton.e);
                accessibilityNodeInfoCompat.setChecked(checkableImageButton.d);
                return;
            case 1:
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCheckable(((NavigationMenuItemView) this.b).l);
                return;
            default:
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.addAction((int) MediaDefs.Meta.SEF.SEF_MIN_SIZE);
                accessibilityNodeInfoCompat.setDismissable(true);
                return;
        }
    }

    public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
        switch (this.f1735a) {
            case 2:
                if (i2 != 1048576) {
                    return super.performAccessibilityAction(view, i2, bundle);
                }
                ((r) ((q) this.b)).a(3);
                return true;
            default:
                return super.performAccessibilityAction(view, i2, bundle);
        }
    }
}
