package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.FaceClusterMergeView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ FaceClusterMergeView d;
    public final /* synthetic */ FaceClusterMergeView.MergeInfo e;
    public final /* synthetic */ ImageView f;
    public final /* synthetic */ Bitmap g;

    public /* synthetic */ b(FaceClusterMergeView faceClusterMergeView, FaceClusterMergeView.MergeInfo mergeInfo, ImageView imageView, Bitmap bitmap) {
        this.d = faceClusterMergeView;
        this.e = mergeInfo;
        this.f = imageView;
        this.g = bitmap;
    }

    public final void run() {
        this.d.lambda$setItem$3(this.e, this.f, this.g);
    }
}
