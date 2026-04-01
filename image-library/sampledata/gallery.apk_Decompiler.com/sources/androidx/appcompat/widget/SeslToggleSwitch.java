package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslToggleSwitch extends SwitchCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnBeforeCheckedChangeListener {
    }

    public SeslToggleSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setChecked(boolean z) {
        super.setChecked(z);
    }

    public void setCheckedInternal(boolean z) {
        super.setChecked(z);
    }

    public void setOnBeforeCheckedChangeListener(OnBeforeCheckedChangeListener onBeforeCheckedChangeListener) {
    }
}
