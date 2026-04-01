package com.samsung.android.gallery.app.controller.externals;

import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PrintDocumentAdapter;
import com.samsung.android.gallery.app.controller.externals.StartPrintCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ StartPrintCmd.PrintBitmapAdapter d;
    public final /* synthetic */ CancellationSignal e;
    public final /* synthetic */ PrintDocumentAdapter.WriteResultCallback f;
    public final /* synthetic */ ParcelFileDescriptor g;

    public /* synthetic */ c(StartPrintCmd.PrintBitmapAdapter printBitmapAdapter, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback, ParcelFileDescriptor parcelFileDescriptor) {
        this.d = printBitmapAdapter;
        this.e = cancellationSignal;
        this.f = writeResultCallback;
        this.g = parcelFileDescriptor;
    }

    public final void run() {
        this.d.lambda$onWrite$0(this.e, this.f, this.g);
    }
}
