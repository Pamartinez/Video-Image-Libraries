package com.samsung.android.gallery.module.map.transition;

import a6.g;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.AbsTask;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerCollection;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import java.util.Objects;
import q5.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddMarkerTask extends AbsTask {
    private final double[] mAnimateFrom;
    private final ICluster<MapItem> mCluster;
    private final MarkerCollection mNewCollection;

    public AddMarkerTask(double[] dArr, ICluster<MapItem> iCluster, MarkerCollection markerCollection, BaseMarkerManager baseMarkerManager) {
        this.mPriority = AbsTask.TASK_PRIORITY.ADD;
        this.mAnimateFrom = dArr;
        this.mCluster = iCluster;
        this.mNewCollection = markerCollection;
        this.mMarkerManager = baseMarkerManager;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$perform$0(DrawMarkerTask drawMarkerTask, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        drawMarkerTask.mDecodedBitmap = bitmap;
        drawMarkerTask.mIsReady = true;
    }

    public void perform(TransitionQueueScheduler transitionQueueScheduler) {
        ICluster<MapItem> iCluster = this.mCluster;
        if (iCluster != null && iCluster.getTopItem() != null && this.mCluster.getTopItem().getMediaItem() != null) {
            ThumbnailInterface mediaItem = this.mCluster.getTopItem().getMediaItem();
            DrawMarkerTask drawMarkerTask = new DrawMarkerTask(this.mAnimateFrom, this.mCluster, this.mNewCollection, this.mMarkerManager);
            transitionQueueScheduler.addTask(drawMarkerTask);
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.SMALL_CROP_KIND;
            Objects.requireNonNull(mediaItem);
            instance.loadThumbnail(mediaItem, thumbKind, new i(mediaItem, 0), new g(20, drawMarkerTask));
        }
    }

    public String tag() {
        return "AddMarkerTask";
    }
}
