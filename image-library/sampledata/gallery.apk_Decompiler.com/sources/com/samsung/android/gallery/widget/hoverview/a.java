package com.samsung.android.gallery.widget.hoverview;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThumbnailLoadedListener {
    public final /* synthetic */ HoverPreviewListAdapter d;
    public final /* synthetic */ HoverPreviewViewHolder e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Context g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f3203h;

    public /* synthetic */ a(HoverPreviewListAdapter hoverPreviewListAdapter, HoverPreviewViewHolder hoverPreviewViewHolder, MediaItem mediaItem, Context context, int i2) {
        this.d = hoverPreviewListAdapter;
        this.e = hoverPreviewViewHolder;
        this.f = mediaItem;
        this.g = context;
        this.f3203h = i2;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$onBindViewHolder$1(this.e, this.f, this.g, this.f3203h, bitmap, uniqueKey, thumbKind);
    }
}
