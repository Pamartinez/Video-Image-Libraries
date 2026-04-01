package com.samsung.android.gallery.support.utils;

import android.media.MediaScannerConnection;
import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaScannerConnection.OnScanCompletedListener f3159a;

    public /* synthetic */ A(MediaScannerConnection.OnScanCompletedListener onScanCompletedListener) {
        this.f3159a = onScanCompletedListener;
    }

    public final void onScanCompleted(String str, Uri uri) {
        MediaScanner.lambda$scanFiles$2(0, this.f3159a, str, uri);
    }
}
