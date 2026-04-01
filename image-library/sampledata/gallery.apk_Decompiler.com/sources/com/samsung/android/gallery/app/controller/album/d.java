package com.samsung.android.gallery.app.controller.album;

import com.samsung.android.gallery.app.controller.album.CloudDownloadCopyCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ CloudDownloadCopyCmd.AnonymousClass1 d;
    public final /* synthetic */ List e;
    public final /* synthetic */ List f;
    public final /* synthetic */ MediaItem[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ CloudDownloadCopyCmd.ICloudDownloadListener f2502h;

    public /* synthetic */ d(CloudDownloadCopyCmd.AnonymousClass1 r1, List list, List list2, MediaItem[] mediaItemArr, CloudDownloadCopyCmd.ICloudDownloadListener iCloudDownloadListener) {
        this.d = r1;
        this.e = list;
        this.f = list2;
        this.g = mediaItemArr;
        this.f2502h = iCloudDownloadListener;
    }

    public final void run() {
        this.d.lambda$onReceiveResult$2(this.e, this.f, this.g, this.f2502h);
    }
}
