package com.samsung.android.gallery.module.motionphoto;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MotionPlayViewer {
    PHOTO(false, false),
    PHOTO_PREVIEW(false, false),
    VIDEO_FILM(true, false),
    VIDEO_BUTTON(true, false),
    PHOTO_MOTION_OFF(false, true),
    BOOMERANG(true, true),
    SLOW_MO(true, true);
    
    public final boolean isOriginVideo;
    public final boolean isPhoto;
    public final boolean isVideo;
    public final boolean isViewMode;
    public final boolean isViewModeVideo;

    private MotionPlayViewer(boolean z, boolean z3) {
        boolean z7;
        this.isPhoto = !z;
        this.isVideo = z;
        boolean z9 = false;
        if (z3 || !z) {
            z7 = false;
        } else {
            z7 = true;
        }
        this.isOriginVideo = z7;
        this.isViewMode = z3;
        if (z && z3) {
            z9 = true;
        }
        this.isViewModeVideo = z9;
    }

    public static boolean isViewModeChanged(MotionPlayViewer motionPlayViewer, MotionPlayViewer motionPlayViewer2) {
        if (motionPlayViewer == motionPlayViewer2) {
            return false;
        }
        if (motionPlayViewer.isViewMode || motionPlayViewer2.isViewMode) {
            return true;
        }
        return false;
    }
}
