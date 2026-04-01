package com.samsung.android.gallery.settings.ui;

import android.content.DialogInterface;
import com.samsung.android.gallery.settings.ui.LabsDeveloperFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class z implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsDeveloperFragment.YearTimeSlot e;

    public /* synthetic */ z(LabsDeveloperFragment.YearTimeSlot yearTimeSlot, int i2) {
        this.d = i2;
        this.e = yearTimeSlot;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        LabsDeveloperFragment.YearTimeSlot yearTimeSlot = this.e;
        switch (i7) {
            case 0:
                yearTimeSlot.onNegativeClicked(dialogInterface, i2);
                return;
            default:
                yearTimeSlot.onPositiveClicked(dialogInterface, i2);
                return;
        }
    }
}
