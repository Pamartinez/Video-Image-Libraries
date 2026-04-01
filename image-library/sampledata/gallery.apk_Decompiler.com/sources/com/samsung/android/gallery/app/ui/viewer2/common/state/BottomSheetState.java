package com.samsung.android.gallery.app.ui.viewer2.common.state;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BottomSheetState {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Details {
        private static int getState(StateListener stateListener) {
            if (stateListener != null) {
                return stateListener.getDetailsState();
            }
            return 4;
        }

        public static boolean isClosed(StateListener stateListener) {
            if (getState(stateListener) == 4 || getState(stateListener) == 5) {
                return true;
            }
            return false;
        }

        public static boolean isExpanded(StateListener stateListener) {
            if (isFullExpanded(stateListener) || isPartialExpanded(stateListener)) {
                return true;
            }
            return false;
        }

        public static boolean isFullExpanded(StateListener stateListener) {
            if (getState(stateListener) == 3) {
                return true;
            }
            return false;
        }

        public static boolean isInTransition(StateListener stateListener) {
            if (getState(stateListener) == 1 || getState(stateListener) == 2) {
                return true;
            }
            return false;
        }

        public static boolean isPartialExpanded(StateListener stateListener) {
            if (getState(stateListener) == 100 || getState(stateListener) == 6) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface StateListener {
        int getDetailsState();
    }

    public static boolean isClosed(StateListener stateListener) {
        return Details.isClosed(stateListener);
    }

    public static boolean isExpanded(StateListener stateListener) {
        return Details.isExpanded(stateListener);
    }

    public static boolean isInTransition(StateListener stateListener) {
        return Details.isInTransition(stateListener);
    }
}
