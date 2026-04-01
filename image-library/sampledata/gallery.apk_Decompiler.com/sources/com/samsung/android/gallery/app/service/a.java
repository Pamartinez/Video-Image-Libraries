package com.samsung.android.gallery.app.service;

import android.media.MediaScannerConnection;
import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FileOpData f2513a;
    public final /* synthetic */ long b;

    public /* synthetic */ a(FileOpData fileOpData, long j2) {
        this.f2513a = fileOpData;
        this.b = j2;
    }

    public final void onScanCompleted(String str, Uri uri) {
        this.f2513a.lambda$scanFolder$1(this.b, str, uri);
    }
}
