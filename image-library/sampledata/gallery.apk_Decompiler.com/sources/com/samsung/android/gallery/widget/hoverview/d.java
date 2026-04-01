package com.samsung.android.gallery.widget.hoverview;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ HoverPreviewListAdapter d;
    public final /* synthetic */ HoverPreviewViewHolder e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Context g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Bitmap f3205h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f3206i;

    public /* synthetic */ d(HoverPreviewListAdapter hoverPreviewListAdapter, HoverPreviewViewHolder hoverPreviewViewHolder, MediaItem mediaItem, Context context, Bitmap bitmap, int i2) {
        this.d = hoverPreviewListAdapter;
        this.e = hoverPreviewViewHolder;
        this.f = mediaItem;
        this.g = context;
        this.f3205h = bitmap;
        this.f3206i = i2;
    }

    public final void run() {
        this.d.lambda$onBindViewHolder$0(this.e, this.f, this.g, this.f3205h, this.f3206i);
    }
}
