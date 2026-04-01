package com.samsung.android.gallery.settings.ui;

import android.content.DialogInterface;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.samsung.android.gallery.settings.ui.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0650e implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AtomicInteger e;

    public /* synthetic */ C0650e(AtomicInteger atomicInteger, int i2) {
        this.d = i2;
        this.e = atomicInteger;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        AtomicInteger atomicInteger = this.e;
        switch (i7) {
            case 0:
                FixFaceTable.lambda$showSelectBucketDlg$4(atomicInteger, dialogInterface, i2);
                return;
            default:
                atomicInteger.set(i2);
                return;
        }
    }
}
