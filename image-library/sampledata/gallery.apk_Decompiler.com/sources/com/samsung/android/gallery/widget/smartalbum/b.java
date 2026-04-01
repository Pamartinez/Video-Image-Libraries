package com.samsung.android.gallery.widget.smartalbum;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ SmartAlbumStoryItem d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ b(SmartAlbumStoryItem smartAlbumStoryItem, MediaItem mediaItem, Bitmap bitmap) {
        this.d = smartAlbumStoryItem;
        this.e = mediaItem;
        this.f = bitmap;
    }

    public final void run() {
        this.d.lambda$updateDetails$0(this.e, this.f);
    }
}
