package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import androidx.preference.Preference;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.settings.ui.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0659n implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0659n(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        switch (this.d) {
            case 0:
                return LabsBaseFragment.lambda$addSwitchPreference$8((BooleanFeatures) this.e, (Consumer) this.f, preference, obj);
            default:
                return LabsBaseFragment.lambda$initSwitchPreference$12((BooleanFeatures) this.e, (Consumer) this.f, preference, obj);
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        return ((SettingCloud2) this.e).lambda$initSamsungCloudSync$4((Context) this.f, preference);
    }
}
