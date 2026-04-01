package com.samsung.android.gallery.app.ui.list.search.category.people.top;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MeWith5 extends TopPeopleLayout {
    public MeWith5(int i2, int i7, int i8) {
        super(i2, i7, i8);
    }

    public int getLeft(int i2, int i7, int i8, boolean z) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16 = i8 / 2;
        if (i2 == 2) {
            int i17 = this.mMainCreatureSize;
            if (z) {
                i13 = i17 / 2;
                i14 = this.mItemSpacing;
                i10 = i14 + i13;
                return i16 + i10;
            }
            i11 = (-i17) / 2;
            i12 = this.mItemSpacing;
        } else if (i2 != 3) {
            if (i2 != 5) {
                if (i2 != 6) {
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
                i15 = (this.mItemSpacing - this.mMainCreatureSize) / 2;
                i10 = i15 - i7;
            } else {
                i10 = (this.mMainCreatureSize - this.mItemSpacing) / 2;
            }
            return i16 + i10;
        } else if (z) {
            i10 = (this.mMainCreatureSize - this.mItemSpacing) / 2;
            return i16 + i10;
        } else {
            i15 = (this.mItemSpacing - this.mMainCreatureSize) / 2;
            i10 = i15 - i7;
            return i16 + i10;
        }
        i15 = i11 - i12;
        i10 = i15 - i7;
        return i16 + i10;
    }

    public int getTop(int i2, int i7, int i8) {
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 == 4) {
                    return i8 + this.mItemSpacing;
                }
                if (i2 != 5) {
                    if (i2 != 6) {
                        return 0;
                    }
                }
            }
            return i8 - this.mItemSpacing;
        }
        return (i8 - this.mItemSpacing) - i7;
    }
}
