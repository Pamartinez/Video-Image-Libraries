package com.samsung.android.gallery.support.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.WatchDog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((MediaScanner.AnonymousClass1) obj).lambda$onMediaScannerConnected$0();
                return;
            case 1:
                MediaScannerConnection.scanFile(AppResources.getAppContext(), (String[]) obj, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
                return;
            case 2:
                ((WatchDog.WatchDogPrinter) obj).lambda$println$0();
                return;
            case 3:
                MediaHelper.releaseRetriever((MediaMetadataRetriever) obj);
                return;
            case 4:
                ((MediaScanner) obj).lambda$new$0();
                return;
            default:
                MemoryUtils.lambda$forceFree$3((Bitmap[]) obj);
                return;
        }
    }
}
