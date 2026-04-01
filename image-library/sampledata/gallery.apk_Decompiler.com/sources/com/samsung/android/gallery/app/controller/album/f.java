package com.samsung.android.gallery.app.controller.album;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CloudDownloadCopyCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements CloudDownloadCopyCmd.ICloudDownloadListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EventContext f2503a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2504c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ f(EventContext eventContext, String str, String str2, boolean z, int i2, boolean z3) {
        this.f2503a = eventContext;
        this.b = str;
        this.f2504c = str2;
        this.d = z;
        this.e = i2;
        this.f = z3;
    }

    public final void a(MediaItem[] mediaItemArr) {
        FileOpCmdHelper.lambda$startService$0(this.f2503a, this.b, this.f2504c, this.d, this.e, this.f, mediaItemArr);
    }
}
