package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.PreferenceViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThreadMaxPreference extends FixedValuePreference {
    private static final String[] VALUE_LIST = {"3", "6", "9", "12"};

    public ThreadMaxPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String getDefaultValue() {
        return "3";
    }

    public /* bridge */ /* synthetic */ int getLayoutId() {
        return super.getLayoutId();
    }

    public String[] getValueList() {
        return VALUE_LIST;
    }

    public /* bridge */ /* synthetic */ void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
    }

    public /* bridge */ /* synthetic */ boolean supportCustomLayout() {
        return super.supportCustomLayout();
    }
}
