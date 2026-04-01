package com.samsung.android.gallery.settings.ui;

import android.content.DialogInterface;
import androidx.preference.Preference;
import com.samsung.android.gallery.support.utils.BooleanFeatures;

/* renamed from: com.samsung.android.gallery.settings.ui.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0660o implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0660o(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                LabsBaseFragment.lambda$showSwitchConfirmDialog$2((Preference) this.e, (BooleanFeatures) this.f, dialogInterface, i2);
                return;
            default:
                ((SettingCloud) this.e).lambda$showDialog2Fa$22((String) this.f, dialogInterface, i2);
                return;
        }
    }
}
