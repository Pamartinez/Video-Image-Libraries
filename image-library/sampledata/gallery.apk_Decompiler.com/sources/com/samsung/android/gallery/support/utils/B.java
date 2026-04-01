package com.samsung.android.gallery.support.utils;

import android.media.MediaScannerConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaScannerConnection e;

    public /* synthetic */ B(MediaScannerConnection mediaScannerConnection, int i2) {
        this.d = i2;
        this.e = mediaScannerConnection;
    }

    public final void run() {
        int i2 = this.d;
        MediaScannerConnection mediaScannerConnection = this.e;
        switch (i2) {
            case 0:
                mediaScannerConnection.connect();
                return;
            default:
                mediaScannerConnection.disconnect();
                return;
        }
    }
}
