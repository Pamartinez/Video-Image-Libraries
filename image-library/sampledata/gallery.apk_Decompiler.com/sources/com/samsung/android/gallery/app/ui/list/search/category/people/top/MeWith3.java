package com.samsung.android.gallery.app.ui.list.search.category.people.top;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MeWith3 extends TopPeopleLayout {
    public MeWith3(int i2, int i7, int i8) {
        super(i2, i7, i8);
    }

    public int getLeft(int i2, int i7, int i8, boolean z) {
        int i10 = i8 / 2;
        if (i2 == 1 || i2 == 3) {
            return i10 - (i7 / 2);
        }
        int i11 = this.mItemSpacing;
        int i12 = this.mMainCreatureSize;
        int i13 = (((i11 - i12) / 2) + i10) - i7;
        int D = C0086a.D(i12, i11, 2, i10);
        if (!z ? i2 != 2 : i2 == 2) {
            return D;
        }
        return i13;
    }

    public int getTop(int i2, int i7, int i8) {
        if (i2 == 1) {
            return 0;
        }
        int i10 = this.mItemSpacing;
        if (i2 == 3) {
            return i8 + i10;
        }
        return i8 - i10;
    }
}
