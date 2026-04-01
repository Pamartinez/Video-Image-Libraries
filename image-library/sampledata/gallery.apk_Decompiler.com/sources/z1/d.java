package Z1;

import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends AccessibilityDelegateCompat {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MaterialButtonToggleGroup f966a;

    public d(MaterialButtonToggleGroup materialButtonToggleGroup) {
        this.f966a = materialButtonToggleGroup;
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int i2;
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        int i7 = MaterialButtonToggleGroup.n;
        if (view instanceof MaterialButton) {
            int i8 = 0;
            int i10 = 0;
            while (true) {
                MaterialButtonToggleGroup materialButtonToggleGroup = this.f966a;
                if (i8 >= materialButtonToggleGroup.getChildCount()) {
                    break;
                } else if (materialButtonToggleGroup.getChildAt(i8) == view) {
                    i2 = i10;
                    break;
                } else {
                    if ((materialButtonToggleGroup.getChildAt(i8) instanceof MaterialButton) && materialButtonToggleGroup.c(i8)) {
                        i10++;
                    }
                    i8++;
                }
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, i2, 1, false, ((MaterialButton) view).f1432o));
        }
        i2 = -1;
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, i2, 1, false, ((MaterialButton) view).f1432o));
    }
}
