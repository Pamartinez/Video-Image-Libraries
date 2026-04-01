package com.samsung.android.gallery.settings.ui;

import android.content.DialogInterface;
import com.samsung.android.gallery.settings.ui.LabsUserTrialFragment;

/* renamed from: com.samsung.android.gallery.settings.ui.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0654i implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0654i(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        Object obj = this.e;
        switch (i7) {
            case 0:
                ((Runnable) obj).run();
                return;
            case 1:
                ((LabsUserTrialFragment.SearchLlmQpOperation) obj).lambda$new$2(dialogInterface, i2);
                return;
            default:
                ((SettingCloud) obj).lambda$showDialogSyncOff$19(dialogInterface, i2);
                return;
        }
    }
}
