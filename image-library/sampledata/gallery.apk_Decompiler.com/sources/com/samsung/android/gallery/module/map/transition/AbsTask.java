package com.samsung.android.gallery.module.map.transition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsTask {
    protected final String TAG = tag();
    boolean mIsReady = true;
    BaseMarkerManager mMarkerManager;
    public TASK_PRIORITY mPriority;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TASK_PRIORITY {
        REMOVE,
        ANIMATE,
        ADD,
        DRAW
    }

    public void destroy() {
        this.mMarkerManager = null;
    }

    public boolean isReady() {
        return this.mIsReady;
    }

    public abstract void perform(TransitionQueueScheduler transitionQueueScheduler);

    public abstract String tag();
}
