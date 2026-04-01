package com.samsung.android.gallery.support.library.sef;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Drdh {
    /* access modifiers changed from: package-private */
    public final int dataType;
    final int option;
    final int value;

    public Drdh(int i2) {
        this.value = i2;
        this.dataType = i2 >>> 16;
        this.option = (i2 & 32768) == 0 ? 0 : 1;
    }

    public Drdh(int i2, int i7) {
        this.value = (i2 << 16) | (i7 == 1 ? 32768 : 0);
        this.dataType = i2;
        this.option = i7;
    }
}
