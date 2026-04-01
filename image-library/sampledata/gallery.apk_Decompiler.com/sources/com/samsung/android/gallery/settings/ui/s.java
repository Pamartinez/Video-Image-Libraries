package com.samsung.android.gallery.settings.ui;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsBaseFragment;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Preference e;
    public final /* synthetic */ String[] f;
    public final /* synthetic */ LabsBaseFragment.ChoiceDialogBuilder g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Serializable f3120h;

    public /* synthetic */ s(LabsBaseFragment.ChoiceDialogBuilder choiceDialogBuilder, Preference preference, String[] strArr, Serializable serializable, int i2) {
        this.d = i2;
        this.g = choiceDialogBuilder;
        this.e = preference;
        this.f = strArr;
        this.f3120h = serializable;
    }

    public final boolean onPreferenceClick(Preference preference) {
        switch (this.d) {
            case 0:
                return this.g.lambda$attach$2(this.e, this.f, (AtomicInteger) this.f3120h, preference);
            default:
                return ((LabsBaseFragment.MultiChoiceDialogBuilder) this.g).lambda$attach$6(this.e, this.f, (boolean[]) this.f3120h, preference);
        }
    }
}
