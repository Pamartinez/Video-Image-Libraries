package com.samsung.android.gallery.settings.ui;

import android.content.DialogInterface;
import com.samsung.android.gallery.settings.ui.LabsBaseFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements DialogInterface.OnMultiChoiceClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean[] f3123a;

    public /* synthetic */ u(boolean[] zArr) {
        this.f3123a = zArr;
    }

    public final void onClick(DialogInterface dialogInterface, int i2, boolean z) {
        LabsBaseFragment.MultiChoiceDialogBuilder.lambda$attach$2(this.f3123a, dialogInterface, i2, z);
    }
}
