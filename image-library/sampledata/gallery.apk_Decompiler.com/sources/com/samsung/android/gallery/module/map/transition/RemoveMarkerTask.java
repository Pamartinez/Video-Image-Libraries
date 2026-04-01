package com.samsung.android.gallery.module.map.transition;

import com.samsung.android.gallery.module.map.transition.AbsTask;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveMarkerTask extends AbsTask {
    private final double[] mAnimateTo;
    private final MarkerWithPosition mMarkerToRemove;

    public RemoveMarkerTask(double[] dArr, MarkerWithPosition markerWithPosition, BaseMarkerManager baseMarkerManager) {
        this.mPriority = AbsTask.TASK_PRIORITY.REMOVE;
        this.mAnimateTo = dArr;
        this.mMarkerToRemove = markerWithPosition;
        this.mMarkerManager = baseMarkerManager;
    }

    public void perform(TransitionQueueScheduler transitionQueueScheduler) {
        this.mMarkerManager.getCurrentMarkerCollection().removeMarker(this.mMarkerToRemove.getCluster(), this.mMarkerToRemove);
        double[] dArr = this.mAnimateTo;
        if (dArr == null) {
            this.mMarkerToRemove.getMarker().remove();
        } else {
            transitionQueueScheduler.addTask(new AnimateMarkerTask(true, dArr, this.mMarkerToRemove.getPosition(), this.mMarkerToRemove, this.mMarkerManager));
        }
    }

    public String tag() {
        return "RemoveMarkerTask";
    }
}
