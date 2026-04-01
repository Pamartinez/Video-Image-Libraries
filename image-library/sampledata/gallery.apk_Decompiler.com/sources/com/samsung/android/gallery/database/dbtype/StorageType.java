package com.samsung.android.gallery.database.dbtype;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum StorageType {
    None(0),
    Local(1),
    Cloud(2),
    LocalCloud(3),
    Sharing(4),
    Mtp(5),
    UriItem(6),
    WebItem(7),
    Stream(8),
    ContactItem(9),
    RemoteItem(10),
    PrivateItem(11),
    TempItem(12),
    Invalid(13);
    
    private int mValue;

    private StorageType(int i2) {
        this.mValue = i2;
    }

    public int toInt() {
        return this.mValue;
    }

    public static int toInt(StorageType storageType, int i2) {
        return storageType != null ? storageType.toInt() : i2;
    }

    public static StorageType valueOf(int i2) {
        switch (i2) {
            case 1:
                return Local;
            case 2:
                return Cloud;
            case 3:
                return LocalCloud;
            case 4:
                return Sharing;
            case 5:
                return Mtp;
            case 6:
                return UriItem;
            case 7:
                return WebItem;
            case 8:
                return Stream;
            case 9:
                return ContactItem;
            case 10:
                return RemoteItem;
            case 11:
                return PrivateItem;
            default:
                return None;
        }
    }
}
