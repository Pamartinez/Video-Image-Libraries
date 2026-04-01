package com.samsung.android.gallery.app.ui.list.albums.pictures.header;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThumbnailLoadedListener {
    public final /* synthetic */ AlbumPicturesHeaderListAdapter d;
    public final /* synthetic */ AlbumPicturesHeaderViewHolder e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ a(AlbumPicturesHeaderListAdapter albumPicturesHeaderListAdapter, AlbumPicturesHeaderViewHolder albumPicturesHeaderViewHolder, MediaItem mediaItem) {
        this.d = albumPicturesHeaderListAdapter;
        this.e = albumPicturesHeaderViewHolder;
        this.f = mediaItem;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$onBindViewHolder$1(this.e, this.f, bitmap, uniqueKey, thumbKind);
    }
}
