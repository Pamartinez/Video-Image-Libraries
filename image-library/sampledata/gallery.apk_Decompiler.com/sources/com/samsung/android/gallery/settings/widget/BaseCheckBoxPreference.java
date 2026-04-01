package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.CheckBoxPreference;
import com.samsung.android.gallery.settings.R$dimen;
import com.samsung.android.gallery.settings.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaseCheckBoxPreference extends CheckBoxPreference {
    public BaseCheckBoxPreference(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        init(context);
    }

    private int getLayoutId() {
        return R$layout.check_box_preference_layout;
    }

    private void init(Context context) {
        setLayoutResource(getLayoutId());
        seslSetDividerStartOffset(context.getResources().getDimensionPixelOffset(R$dimen.check_box_preference_divider_padding_start));
    }

    public BaseCheckBoxPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }

    public BaseCheckBoxPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }
}
