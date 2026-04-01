package com.samsung.android.gallery.module.fileio.redact;

import android.media.MediaScannerConnection;
import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FileCreator f3020a;

    public /* synthetic */ a(FileCreator fileCreator) {
        this.f3020a = fileCreator;
    }

    public final void onScanCompleted(String str, Uri uri) {
        this.f3020a.lambda$handleFile$0(str, uri);
    }
}
