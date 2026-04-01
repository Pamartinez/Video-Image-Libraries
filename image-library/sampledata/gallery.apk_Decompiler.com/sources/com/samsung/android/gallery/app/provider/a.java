package com.samsung.android.gallery.app.provider;

import com.samsung.android.gallery.app.provider.GalleryFileProvider;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements GalleryFileProvider.FileEventListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GalleryFileProvider.CapturedFileObserver f2511a;
    public final /* synthetic */ CountDownLatch b;

    public /* synthetic */ a(GalleryFileProvider.CapturedFileObserver capturedFileObserver, CountDownLatch countDownLatch) {
        this.f2511a = capturedFileObserver;
        this.b = countDownLatch;
    }
}
