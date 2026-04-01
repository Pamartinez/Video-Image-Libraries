package com.samsung.android.gallery.module.remotegallery;

import com.samsung.android.gallery.support.utils.MediaScannerListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements MediaScannerListener {
    public final /* synthetic */ String d;
    public final /* synthetic */ Runnable e;

    public /* synthetic */ i(String str, Runnable runnable) {
        this.d = str;
        this.e = runnable;
    }

    public final void onCompleted() {
        RemoteGalleryUtil.lambda$download$0(this.d, this.e);
    }
}
