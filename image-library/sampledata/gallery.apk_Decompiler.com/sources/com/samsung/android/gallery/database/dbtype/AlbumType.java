package com.samsung.android.gallery.database.dbtype;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum AlbumType {
    None(0),
    Folder(1),
    Merged(2),
    Virtual(3),
    AutoUpdated(4),
    MyAlbum(5),
    SystemMerged(6);
    
    private final int mValue;

    private AlbumType(int i2) {
        this.mValue = i2;
    }

    public static AlbumType get(int i2) {
        switch (i2) {
            case 1:
                return Folder;
            case 2:
                return Merged;
            case 3:
                return Virtual;
            case 4:
                return AutoUpdated;
            case 5:
                return MyAlbum;
            case 6:
                return SystemMerged;
            default:
                return None;
        }
    }

    public static boolean isAutoAlbum(AlbumType albumType) {
        return albumType == AutoUpdated;
    }

    public static boolean isFolder(AlbumType albumType) {
        if (albumType == Folder) {
            return true;
        }
        return false;
    }

    public static boolean isMergedAlbum(int i2) {
        AlbumType albumType = get(i2);
        return albumType == Merged || albumType == SystemMerged;
    }

    public static boolean isVirtual(AlbumType albumType) {
        if (albumType == Virtual) {
            return true;
        }
        return false;
    }

    public int toInt() {
        return this.mValue;
    }

    public static boolean isAutoAlbum(int i2) {
        return get(i2) == AutoUpdated;
    }

    public static boolean isMergedAlbum(AlbumType albumType, boolean z) {
        if (albumType != SystemMerged) {
            return z && albumType == Merged;
        }
        return true;
    }
}
