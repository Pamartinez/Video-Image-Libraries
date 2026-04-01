package com.samsung.android.gallery.app.ui.list.search.category.people.top;

import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TopPeopleLayout {
    protected final int mBottomMargin;
    protected final int mItemSpacing;
    protected int mMainCreatureSize;
    protected final int mSubCreatureSize;

    public TopPeopleLayout(int i2, int i7, int i8) {
        this.mMainCreatureSize = i2;
        this.mSubCreatureSize = i7;
        this.mBottomMargin = i7 / 2;
        this.mItemSpacing = i8;
    }

    public static TopPeopleLayout create(int i2, int i7, int i8, int i10) {
        switch (i2) {
            case 1:
                return new OnlyMe(i7, i8, i10);
            case 2:
                return new MeWith1(i7, i8, i10);
            case 3:
                return new MeWith2(i7, i8, i10);
            case 4:
                return new MeWith3(i7, i8, i10);
            case 5:
                return new MeWith4(i7, i8, i10);
            case 6:
                return new MeWith5(i7, i8, i10);
            default:
                Log.e("TopPeopleLayout", "Failed to create top layout[ " + i2 + "]. Check the count");
                return null;
        }
    }

    public int getHeight() {
        return this.mMainCreatureSize + this.mItemSpacing + this.mSubCreatureSize + this.mBottomMargin;
    }

    public abstract int getLeft(int i2, int i7, int i8, boolean z);

    public abstract int getTop(int i2, int i7, int i8);
}
