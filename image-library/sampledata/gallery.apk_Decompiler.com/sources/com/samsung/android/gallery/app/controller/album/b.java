package com.samsung.android.gallery.app.controller.album;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CloudDownloadCopyCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ CloudDownloadCopyCmd d;
    public final /* synthetic */ EventContext e;
    public final /* synthetic */ List f;
    public final /* synthetic */ MediaItem[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ CloudDownloadCopyCmd.ICloudDownloadListener f2501h;

    public /* synthetic */ b(CloudDownloadCopyCmd cloudDownloadCopyCmd, EventContext eventContext, List list, MediaItem[] mediaItemArr, CloudDownloadCopyCmd.ICloudDownloadListener iCloudDownloadListener) {
        this.d = cloudDownloadCopyCmd;
        this.e = eventContext;
        this.f = list;
        this.g = mediaItemArr;
        this.f2501h = iCloudDownloadListener;
    }

    public final void accept(Object obj) {
        this.d.lambda$startCloudDownloadCopy$1(this.e, this.f, this.g, this.f2501h, (Boolean) obj);
    }
}
