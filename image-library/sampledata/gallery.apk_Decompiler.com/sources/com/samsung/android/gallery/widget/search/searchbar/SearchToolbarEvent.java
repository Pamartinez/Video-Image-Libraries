package com.samsung.android.gallery.widget.search.searchbar;

import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchToolbarEvent {
    public Object obj;
    public int what;

    private SearchToolbarEvent(int i2, Object obj2) {
        this.what = i2;
        this.obj = obj2;
    }

    public static SearchToolbarEvent obtain(int i2, Object obj2) {
        return new SearchToolbarEvent(i2, obj2);
    }

    public String toString() {
        return "SearchToolbarMessage{" + this.what + ArcCommonLog.TAG_COMMA + this.obj + "}";
    }

    public static SearchToolbarEvent obtain(int i2) {
        return new SearchToolbarEvent(i2, (Object) null);
    }
}
