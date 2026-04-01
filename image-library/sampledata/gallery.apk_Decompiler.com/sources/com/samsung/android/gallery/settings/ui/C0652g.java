package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import android.content.DialogInterface;
import com.samsung.android.gallery.settings.ui.FixFaceTable;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.settings.ui.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0652g implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ Context e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0652g(Context context, FixFaceTable.WrongFaceData wrongFaceData, FixFaceTable fixFaceTable) {
        this.f = fixFaceTable;
        this.g = wrongFaceData;
        this.e = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                ((FixFaceTable) this.f).lambda$showDeleteConfirmDlg$7((FixFaceTable.WrongFaceData) this.g, this.e, dialogInterface, i2);
                return;
            default:
                ((SettingCloud) this.f).lambda$showDialogSyncOff$20(this.e, (Consumer) this.g, dialogInterface, i2);
                return;
        }
    }

    public /* synthetic */ C0652g(SettingCloud settingCloud, Context context, Consumer consumer) {
        this.f = settingCloud;
        this.e = context;
        this.g = consumer;
    }
}
