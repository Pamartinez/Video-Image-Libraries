package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasePreference extends Preference implements IBasePreference {
    public BasePreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        applyCustomLayout();
    }

    private void applyCustomLayout() {
        if (supportCustomLayout()) {
            setLayoutResource(getLayoutId());
        }
    }

    public BasePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        applyCustomLayout();
    }

    public BasePreference(Context context) {
        super(context);
        applyCustomLayout();
    }
}
