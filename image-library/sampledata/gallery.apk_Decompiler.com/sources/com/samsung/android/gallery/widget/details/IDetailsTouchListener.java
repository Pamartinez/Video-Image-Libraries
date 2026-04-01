package com.samsung.android.gallery.widget.details;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IDetailsTouchListener {
    boolean isLocked();

    boolean isMovable();

    boolean isMovableOnDetails();

    boolean isSupportedDragExit() {
        return true;
    }

    void onTouchSlideUpToExpand();

    boolean shouldBlockBehaviorTouchIntercept();

    void showRequestDismissKeyGuard();

    void onDetectedDragExit() {
    }
}
