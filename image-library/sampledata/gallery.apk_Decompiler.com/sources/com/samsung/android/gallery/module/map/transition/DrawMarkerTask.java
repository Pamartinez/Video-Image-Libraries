package com.samsung.android.gallery.module.map.transition;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.AbsTask;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerCollection;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawMarkerTask extends AbsTask {
    private final double[] mAnimateFrom;
    private final ICluster<MapItem> mCluster;
    Bitmap mDecodedBitmap;
    private final MarkerCollection mNewCollection;

    public DrawMarkerTask(double[] dArr, ICluster<MapItem> iCluster, MarkerCollection markerCollection, BaseMarkerManager baseMarkerManager) {
        this.mIsReady = false;
        this.mPriority = AbsTask.TASK_PRIORITY.DRAW;
        this.mAnimateFrom = dArr;
        this.mCluster = iCluster;
        this.mNewCollection = markerCollection;
        this.mMarkerManager = baseMarkerManager;
    }

    public void perform(TransitionQueueScheduler transitionQueueScheduler) {
        MarkerWithPosition addNewMarker = BaseMarkerManager.addNewMarker(this.mMarkerManager, this.mCluster, this.mDecodedBitmap);
        if (addNewMarker != null && addNewMarker.getMarker() != null) {
            this.mNewCollection.addMarker(addNewMarker, this.mCluster);
            if (this.mAnimateFrom != null) {
                transitionQueueScheduler.addTask(new AnimateMarkerTask(false, this.mCluster.getPosition(), this.mAnimateFrom, addNewMarker, this.mMarkerManager));
            }
        }
    }

    public String tag() {
        return "DrawMarkerTask";
    }
}
