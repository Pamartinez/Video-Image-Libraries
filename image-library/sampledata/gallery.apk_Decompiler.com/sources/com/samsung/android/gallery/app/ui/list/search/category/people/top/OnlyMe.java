package com.samsung.android.gallery.app.ui.list.search.category.people.top;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnlyMe extends TopPeopleLayout {
    public OnlyMe(int i2, int i7, int i8) {
        super(i2, i7, i8);
    }

    public int getHeight() {
        return this.mMainCreatureSize + this.mBottomMargin;
    }

    public int getLeft(int i2, int i7, int i8, boolean z) {
        return (int) (((float) (i8 - i7)) / 2.0f);
    }

    public int getTop(int i2, int i7, int i8) {
        return 0;
    }
}
