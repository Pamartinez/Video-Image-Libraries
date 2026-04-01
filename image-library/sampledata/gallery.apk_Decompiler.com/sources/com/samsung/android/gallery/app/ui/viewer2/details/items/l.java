package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemDebugExif;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ DetailsItemDebugExif.DebugExifAdapter d;
    public final /* synthetic */ DetailsData e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ String[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String[] f2584h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2585i;

    public /* synthetic */ l(DetailsItemDebugExif.DebugExifAdapter debugExifAdapter, DetailsData detailsData, MediaItem mediaItem, String[] strArr, String[] strArr2, int i2) {
        this.d = debugExifAdapter;
        this.e = detailsData;
        this.f = mediaItem;
        this.g = strArr;
        this.f2584h = strArr2;
        this.f2585i = i2;
    }

    public final void run() {
        this.d.lambda$new$6(this.e, this.f, this.g, this.f2584h, this.f2585i);
    }
}
