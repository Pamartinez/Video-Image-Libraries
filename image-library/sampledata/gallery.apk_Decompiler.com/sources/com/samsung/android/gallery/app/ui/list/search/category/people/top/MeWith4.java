package com.samsung.android.gallery.app.ui.list.search.category.people.top;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MeWith4 extends TopPeopleLayout {
    public MeWith4(int i2, int i7, int i8) {
        super(i2, i7, i8);
    }

    public int getLeft(int i2, int i7, int i8, boolean z) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17 = i8 / 2;
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 != 4) {
                    if (i2 != 5) {
                        i10 = (-i7) / 2;
                    } else {
                        int i18 = this.mMainCreatureSize;
                        if (z) {
                            i11 = (-i18) / 2;
                            i12 = this.mItemSpacing;
                        } else {
                            i13 = i18 / 2;
                            i14 = this.mItemSpacing;
                            i10 = i14 + i13;
                        }
                    }
                } else if (z) {
                    i15 = -i7;
                    i16 = this.mItemSpacing;
                } else {
                    i10 = this.mItemSpacing;
                }
                return i17 + i10;
            } else if (z) {
                i10 = this.mItemSpacing;
                return i17 + i10;
            } else {
                i15 = -i7;
                i16 = this.mItemSpacing;
            }
            i10 = i15 - i16;
            return i17 + i10;
        }
        int i19 = this.mMainCreatureSize;
        if (z) {
            i13 = i19 / 2;
            i14 = this.mItemSpacing;
            i10 = i14 + i13;
            return i17 + i10;
        }
        i11 = (-i19) / 2;
        i12 = this.mItemSpacing;
        i10 = (i11 - i12) - i7;
        return i17 + i10;
    }

    public int getTop(int i2, int i7, int i8) {
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2 || i2 == 5) {
            return i8 / 2;
        }
        return i8 + this.mItemSpacing;
    }
}
