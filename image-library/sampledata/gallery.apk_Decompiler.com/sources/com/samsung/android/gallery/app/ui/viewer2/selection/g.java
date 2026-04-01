package com.samsung.android.gallery.app.ui.viewer2.selection;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ SelectionViewAdapter d;
    public final /* synthetic */ SelectionViewHolder e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Runnable g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2608h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f2609i;

    public /* synthetic */ g(SelectionViewAdapter selectionViewAdapter, SelectionViewHolder selectionViewHolder, int i2, Runnable runnable, MediaItem mediaItem, long j2) {
        this.d = selectionViewAdapter;
        this.e = selectionViewHolder;
        this.f = i2;
        this.g = runnable;
        this.f2608h = mediaItem;
        this.f2609i = j2;
    }

    public final void run() {
        this.d.lambda$loadOnIdle$2(this.e, this.f, this.g, this.f2608h, this.f2609i);
    }
}
