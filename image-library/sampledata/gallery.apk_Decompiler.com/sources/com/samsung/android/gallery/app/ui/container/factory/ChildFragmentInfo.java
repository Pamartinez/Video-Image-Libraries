package com.samsung.android.gallery.app.ui.container.factory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChildFragmentInfo {
    private boolean mIsAlbumFirstSelect = true;
    private boolean mIsTimeFirstSelect = true;

    public boolean isAlbumFirstSelect() {
        return this.mIsAlbumFirstSelect;
    }

    public boolean isTimeFirstSelect() {
        return this.mIsTimeFirstSelect;
    }

    public void setFirstSelect(String str, boolean z) {
        if ("location://timeline".equals(str)) {
            this.mIsTimeFirstSelect = z;
        } else if ("location://albums".equals(str)) {
            this.mIsAlbumFirstSelect = z;
        }
    }
}
