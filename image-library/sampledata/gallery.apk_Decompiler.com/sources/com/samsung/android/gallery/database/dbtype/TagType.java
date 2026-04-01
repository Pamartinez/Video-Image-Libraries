package com.samsung.android.gallery.database.dbtype;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum TagType {
    NONE,
    LOCATION,
    POI;

    public static TagType get(int i2) {
        if (i2 == 2) {
            return LOCATION;
        }
        if (i2 != 7) {
            return NONE;
        }
        return POI;
    }
}
