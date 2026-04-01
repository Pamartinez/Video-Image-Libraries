package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreferenceCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaseSwitchPreference extends SwitchPreferenceCompat implements IBasePreference {
    private String mContentDescription;
    private View mView;

    public BaseSwitchPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        applyCustomLayout();
    }

    private void applyCustomLayout() {
        if (supportCustomLayout()) {
            setLayoutResource(getLayoutId());
        }
        if (supportCustomWidgetLayout()) {
            setWidgetLayoutResource(getWidgetLayoutId());
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View view = preferenceViewHolder.itemView;
        this.mView = view;
        view.setContentDescription(this.mContentDescription);
    }

    public BaseSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        applyCustomLayout();
    }
}
