package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PageItem {
    protected final String TAG = getClass().getSimpleName();

    public boolean equalItems(PageItem pageItem) {
        return false;
    }

    public abstract int getType();

    public boolean isCollage() {
        return false;
    }

    public String name() {
        return this.TAG;
    }
}
