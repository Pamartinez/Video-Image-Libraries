package c2;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.chip.Chip;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends ExploreByTouchHelper {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Chip f1054a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(Chip chip, Chip chip2) {
        super(chip2);
        this.f1054a = chip;
    }

    public final int getVirtualViewAt(float f, float f5) {
        Rect rect = Chip.w;
        Chip chip = this.f1054a;
        if (!chip.d() || !chip.getCloseIconTouchBounds().contains(f, f5)) {
            return 0;
        }
        return 1;
    }

    public final void getVisibleVirtualViews(List list) {
        e eVar;
        list.add(0);
        Rect rect = Chip.w;
        Chip chip = this.f1054a;
        if (chip.d() && (eVar = chip.d) != null && eVar.f1072M && chip.g != null) {
            list.add(1);
        }
    }

    public final boolean onPerformActionForVirtualView(int i2, int i7, Bundle bundle) {
        boolean z = false;
        if (i7 == 16) {
            Chip chip = this.f1054a;
            if (i2 == 0) {
                return chip.performClick();
            }
            if (i2 == 1) {
                chip.playSoundEffect(0);
                View.OnClickListener onClickListener = chip.g;
                if (onClickListener != null) {
                    onClickListener.onClick(chip);
                    z = true;
                }
                if (chip.s) {
                    chip.r.sendEventForVirtualView(1, 1);
                }
            }
        }
        return z;
    }

    public final void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        boolean z;
        Chip chip = this.f1054a;
        e eVar = chip.d;
        if (eVar == null || !eVar.S) {
            z = false;
        } else {
            z = true;
        }
        accessibilityNodeInfoCompat.setCheckable(z);
        accessibilityNodeInfoCompat.setClickable(chip.isClickable());
        accessibilityNodeInfoCompat.setClassName(chip.getAccessibilityClassName());
        accessibilityNodeInfoCompat.setText(chip.getText());
    }

    public final void onPopulateNodeForVirtualView(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        CharSequence charSequence = "";
        if (i2 == 1) {
            Chip chip = this.f1054a;
            CharSequence closeIconContentDescription = chip.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                accessibilityNodeInfoCompat.setContentDescription(closeIconContentDescription);
            } else {
                CharSequence text = chip.getText();
                Context context = chip.getContext();
                if (!TextUtils.isEmpty(text)) {
                    charSequence = text;
                }
                accessibilityNodeInfoCompat.setContentDescription(context.getString(R.string.mtrl_chip_close_icon_content_description, new Object[]{charSequence}).trim());
            }
            accessibilityNodeInfoCompat.setBoundsInParent(chip.getCloseIconTouchBoundsInt());
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            accessibilityNodeInfoCompat.setEnabled(chip.isEnabled());
            return;
        }
        accessibilityNodeInfoCompat.setContentDescription(charSequence);
        accessibilityNodeInfoCompat.setBoundsInParent(Chip.w);
    }

    public final void onVirtualViewKeyboardFocusChanged(int i2, boolean z) {
        if (i2 == 1) {
            Chip chip = this.f1054a;
            chip.m = z;
            chip.refreshDrawableState();
        }
    }
}
