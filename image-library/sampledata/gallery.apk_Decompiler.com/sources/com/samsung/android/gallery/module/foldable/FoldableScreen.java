package com.samsung.android.gallery.module.foldable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum FoldableScreen {
    NONE,
    MAIN,
    FLIP_COVER,
    FOLD_SUB;

    public boolean equals(FoldableScreen foldableScreen) {
        if (this == foldableScreen) {
            return true;
        }
        return false;
    }
}
