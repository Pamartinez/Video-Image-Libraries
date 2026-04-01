package com.samsung.android.gallery.settings.widget;

import android.content.DialogInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FixedValuePreference e;

    public /* synthetic */ b(FixedValuePreference fixedValuePreference, int i2) {
        this.d = i2;
        this.e = fixedValuePreference;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        FixedValuePreference fixedValuePreference = this.e;
        switch (i7) {
            case 0:
                fixedValuePreference.onItemClick(dialogInterface, i2);
                return;
            case 1:
                fixedValuePreference.onDoneClicked(dialogInterface, i2);
                return;
            default:
                fixedValuePreference.onCancelClicked(dialogInterface, i2);
                return;
        }
    }
}
