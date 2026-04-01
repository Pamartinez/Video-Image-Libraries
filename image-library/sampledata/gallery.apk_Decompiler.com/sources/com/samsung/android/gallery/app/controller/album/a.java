package com.samsung.android.gallery.app.controller.album;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CloudDownloadCopyCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ CloudDownloadCopyCmd d;
    public final /* synthetic */ List e;
    public final /* synthetic */ String f;
    public final /* synthetic */ EventContext g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MediaItem[] f2499h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ CloudDownloadCopyCmd.ICloudDownloadListener f2500i;

    public /* synthetic */ a(CloudDownloadCopyCmd cloudDownloadCopyCmd, List list, String str, EventContext eventContext, MediaItem[] mediaItemArr, CloudDownloadCopyCmd.ICloudDownloadListener iCloudDownloadListener) {
        this.d = cloudDownloadCopyCmd;
        this.e = list;
        this.f = str;
        this.g = eventContext;
        this.f2499h = mediaItemArr;
        this.f2500i = iCloudDownloadListener;
    }

    public final void run() {
        this.d.lambda$startCloudDownloadCopy$2(this.e, this.f, this.g, this.f2499h, this.f2500i);
    }
}
