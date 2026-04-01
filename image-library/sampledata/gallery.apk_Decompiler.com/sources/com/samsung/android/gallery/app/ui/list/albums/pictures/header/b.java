package com.samsung.android.gallery.app.ui.list.albums.pictures.header;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ AlbumPicturesHeaderListAdapter d;
    public final /* synthetic */ AlbumPicturesHeaderViewHolder e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Bitmap g;

    public /* synthetic */ b(AlbumPicturesHeaderListAdapter albumPicturesHeaderListAdapter, AlbumPicturesHeaderViewHolder albumPicturesHeaderViewHolder, MediaItem mediaItem, Bitmap bitmap) {
        this.d = albumPicturesHeaderListAdapter;
        this.e = albumPicturesHeaderViewHolder;
        this.f = mediaItem;
        this.g = bitmap;
    }

    public final void run() {
        this.d.lambda$onBindViewHolder$0(this.e, this.f, this.g);
    }
}
