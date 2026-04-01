package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureMultipleDialog;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ MergeCreatureMultipleDialog.FaceViewHolder d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ d(MergeCreatureMultipleDialog.FaceViewHolder faceViewHolder, Bitmap bitmap, MediaItem mediaItem) {
        this.d = faceViewHolder;
        this.e = bitmap;
        this.f = mediaItem;
    }

    public final void run() {
        this.d.lambda$loadThumbnail$1(this.e, this.f);
    }
}
