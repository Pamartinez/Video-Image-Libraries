package com.samsung.android.gallery.settings.ui;

import androidx.preference.PreferenceScreen;
import com.samsung.android.gallery.settings.widget.SearchPreference;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.settings.ui.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0658m implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsBaseFragment e;

    public /* synthetic */ C0658m(LabsBaseFragment labsBaseFragment, int i2) {
        this.d = i2;
        this.e = labsBaseFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        LabsBaseFragment labsBaseFragment = this.e;
        switch (i2) {
            case 0:
                labsBaseFragment.addPreferenceBottomPadding((PreferenceScreen) obj);
                return;
            default:
                labsBaseFragment.lambda$initPreferences$1((SearchPreference) obj);
                return;
        }
    }
}
