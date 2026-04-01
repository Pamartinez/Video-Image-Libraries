package com.samsung.android.gallery.widget.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ScreenMode {
    Normal,
    Full,
    FullNos,
    FullList,
    FullListNos,
    Undecided;

    public static ScreenMode getFull(boolean z) {
        if (z) {
            return FullNos;
        }
        return Full;
    }

    public static ScreenMode getListFull(boolean z) {
        if (z) {
            return FullListNos;
        }
        return FullList;
    }

    public boolean isNoStatusBar() {
        if (this == FullNos || this == FullListNos) {
            return true;
        }
        return false;
    }
}
