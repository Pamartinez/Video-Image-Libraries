package com.samsung.android.gallery.module.fileio.compat;

import android.media.MediaScannerConnection;
import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FileOpApiSecured f3017a;

    public /* synthetic */ g(FileOpApiSecured fileOpApiSecured) {
        this.f3017a = fileOpApiSecured;
    }

    public final void onScanCompleted(String str, Uri uri) {
        this.f3017a.lambda$moveFromSecured$0(str, uri);
    }
}
