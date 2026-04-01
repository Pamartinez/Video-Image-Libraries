package com.samsung.android.gallery.app.ui.viewer2.selection;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ SelectionViewAdapter d;
    public final /* synthetic */ SelectionViewHolder e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Bitmap f2607h;

    public /* synthetic */ f(SelectionViewAdapter selectionViewAdapter, SelectionViewHolder selectionViewHolder, MediaItem mediaItem, int i2, Bitmap bitmap) {
        this.d = selectionViewAdapter;
        this.e = selectionViewHolder;
        this.f = mediaItem;
        this.g = i2;
        this.f2607h = bitmap;
    }

    public final void run() {
        this.d.lambda$loadOrDecoding$4(this.e, this.f, this.g, this.f2607h);
    }
}
