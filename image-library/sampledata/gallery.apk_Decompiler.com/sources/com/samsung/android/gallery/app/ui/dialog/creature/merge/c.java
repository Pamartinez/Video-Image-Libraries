package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureMultipleDialog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ThumbnailLoadedListener {
    public final /* synthetic */ MergeCreatureMultipleDialog.FaceViewHolder d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ c(MergeCreatureMultipleDialog.FaceViewHolder faceViewHolder, MediaItem mediaItem) {
        this.d = faceViewHolder;
        this.e = mediaItem;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$loadThumbnail$2(this.e, bitmap, uniqueKey, thumbKind);
    }
}
