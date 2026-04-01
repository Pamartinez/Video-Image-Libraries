package com.samsung.android.gallery.module.details;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum DetailsUpdateKey {
    DYNAMIC_VIDEO,
    SUPER_SLOW,
    CREATURES,
    TAG,
    STORY,
    NO_LOCATION_AUTH,
    NO_LOCATION,
    HAS_LOCATION(false),
    ADDRESS,
    POI,
    RELATED,
    CAPTURED_URL,
    CLIPPED_DATA,
    FPS,
    DUAL_PHOTO_MANIPULATOR,
    MAP_SNAP_BITMAP,
    MAP_MARKER_BITMAP,
    FILE_DATA,
    C2PA,
    DEFAULT;
    
    boolean mNeedRefine;

    public boolean needRefine() {
        return this.mNeedRefine;
    }

    private DetailsUpdateKey(boolean z) {
        this.mNeedRefine = z;
    }
}
