package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.FaceClusterMergeView;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThumbnailLoadedListener {
    public final /* synthetic */ FaceClusterMergeView d;
    public final /* synthetic */ ImageView e;
    public final /* synthetic */ FaceClusterMergeView.MergeInfo f;

    public /* synthetic */ a(FaceClusterMergeView faceClusterMergeView, ImageView imageView, FaceClusterMergeView.MergeInfo mergeInfo) {
        this.d = faceClusterMergeView;
        this.e = imageView;
        this.f = mergeInfo;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$setItem$4(this.e, this.f, bitmap, uniqueKey, thumbKind);
    }
}
