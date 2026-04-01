package com.samsung.android.gallery.module.story;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ExportType {
    NONE(-1),
    SAVE(1796),
    SHARE(1797),
    SAVE_SIMPLE(1801),
    SHARE_SIMPLE(1801),
    ADD_TO_SHARED_ALBUM(1798);
    
    private final int mRequestCode;

    private ExportType(int i2) {
        this.mRequestCode = i2;
    }

    public static ExportType getType(int i2) {
        if (i2 == 1796 || i2 == 1801) {
            return SAVE;
        }
        if (i2 == 1797 || i2 == 1802) {
            return SHARE;
        }
        if (i2 == 1798) {
            return ADD_TO_SHARED_ALBUM;
        }
        return NONE;
    }

    public int getRequestCode() {
        return this.mRequestCode;
    }

    public boolean isAddToSharedAlbum() {
        return ADD_TO_SHARED_ALBUM.equals(this);
    }

    public boolean isSave() {
        return SAVE.equals(this);
    }

    public boolean isShare() {
        return SHARE.equals(this);
    }
}
