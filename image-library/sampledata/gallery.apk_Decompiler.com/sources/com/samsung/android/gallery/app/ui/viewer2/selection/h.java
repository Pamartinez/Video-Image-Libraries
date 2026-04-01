package com.samsung.android.gallery.app.ui.viewer2.selection;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ SelectionViewAdapter d;
    public final /* synthetic */ SelectionViewHolder e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Bitmap f2610h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f2611i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Runnable f2612j;

    public /* synthetic */ h(SelectionViewAdapter selectionViewAdapter, SelectionViewHolder selectionViewHolder, MediaItem mediaItem, int i2, Bitmap bitmap, long j2, Runnable runnable) {
        this.d = selectionViewAdapter;
        this.e = selectionViewHolder;
        this.f = mediaItem;
        this.g = i2;
        this.f2610h = bitmap;
        this.f2611i = j2;
        this.f2612j = runnable;
    }

    public final void run() {
        this.d.lambda$decodeOriginal$3(this.e, this.f, this.g, this.f2610h, this.f2611i, this.f2612j);
    }
}
