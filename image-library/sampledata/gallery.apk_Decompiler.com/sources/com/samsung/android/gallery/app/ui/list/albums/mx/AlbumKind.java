package com.samsung.android.gallery.app.ui.list.albums.mx;

import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum AlbumKind {
    REPRESENTATIVE_ALBUMS(0, R.string.essential_albums),
    SHARED_ALBUMS(1, R.string.shared_albums),
    NON_ESSENTIAL_ALBUMS(2, R.string.tab_tag_albums);
    
    private final int mIndex;
    private int mTitle;

    private AlbumKind(int i2, int i7) {
        this.mTitle = i7;
        this.mIndex = i2;
    }

    public static AlbumKind value(int i2) {
        if (i2 == 1) {
            return SHARED_ALBUMS;
        }
        if (i2 == 2) {
            return NON_ESSENTIAL_ALBUMS;
        }
        return REPRESENTATIVE_ALBUMS;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public int getTitleResId() {
        return this.mTitle;
    }

    public void updateTitle(int i2) {
        this.mTitle = i2;
    }
}
