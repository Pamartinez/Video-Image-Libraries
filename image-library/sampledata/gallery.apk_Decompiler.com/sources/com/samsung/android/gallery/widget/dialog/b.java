package com.samsung.android.gallery.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import com.samsung.android.gallery.widget.dialog.MissingPackage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MissingPackage.DownloadBuilder e;
    public final /* synthetic */ Context f;

    public /* synthetic */ b(MissingPackage.DownloadBuilder downloadBuilder, Context context, int i2) {
        this.d = i2;
        this.e = downloadBuilder;
        this.f = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                this.e.lambda$createUpdateAppGuideDialog$1(this.f, dialogInterface, i2);
                return;
            default:
                this.e.lambda$createDownloadAppGuideDialog$0(this.f, dialogInterface, i2);
                return;
        }
    }
}
