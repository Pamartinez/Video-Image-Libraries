package com.samsung.android.gallery.app.ui.viewer2.common.state;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum OverlayViewState {
    hide,
    show,
    edit_details,
    ai_processing;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface StateListener {
        OverlayViewState getOverlayViewState();
    }

    public static boolean isAiProcessing(StateListener stateListener) {
        if (stateListener.getOverlayViewState() == ai_processing) {
            return true;
        }
        return false;
    }

    public static boolean isFullViewState(OverlayViewState overlayViewState) {
        if (overlayViewState == show || overlayViewState == edit_details) {
            return true;
        }
        return false;
    }

    public static boolean isHide(OverlayViewState overlayViewState) {
        return overlayViewState == hide;
    }

    public static boolean isShow(OverlayViewState overlayViewState) {
        return !isHide(overlayViewState);
    }

    public static boolean isHide(StateListener stateListener) {
        return isHide(stateListener.getOverlayViewState());
    }

    public static boolean isShow(StateListener stateListener) {
        return isShow(stateListener.getOverlayViewState());
    }
}
