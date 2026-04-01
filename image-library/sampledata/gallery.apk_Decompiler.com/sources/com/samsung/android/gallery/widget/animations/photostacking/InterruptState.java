package com.samsung.android.gallery.widget.animations.photostacking;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum InterruptState {
    NONE,
    CANCEL,
    FAIL,
    SUCCESS;

    public static boolean isCancel(InterruptState interruptState) {
        if (interruptState == CANCEL) {
            return true;
        }
        return false;
    }

    public static boolean isFail(InterruptState interruptState) {
        if (interruptState == FAIL) {
            return true;
        }
        return false;
    }

    public static boolean isPlaying(InterruptState interruptState) {
        if (interruptState == NONE) {
            return true;
        }
        return false;
    }

    public static boolean isSuccess(InterruptState interruptState) {
        if (interruptState == SUCCESS) {
            return true;
        }
        return false;
    }
}
