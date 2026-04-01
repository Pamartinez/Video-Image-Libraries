package com.samsung.android.gallery.settings.widget;

import androidx.preference.Preference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ FixedValuePreference d;

    public /* synthetic */ a(FixedValuePreference fixedValuePreference) {
        this.d = fixedValuePreference;
    }

    public final boolean onPreferenceClick(Preference preference) {
        return this.d.onPreferenceClick(preference);
    }
}
