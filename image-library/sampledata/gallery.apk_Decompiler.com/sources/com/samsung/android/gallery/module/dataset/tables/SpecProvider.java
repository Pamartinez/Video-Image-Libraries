package com.samsung.android.gallery.module.dataset.tables;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SpecProvider {
    int getColumnCount() {
        return getSpec(2, 0)[1];
    }

    int[] getHeightSpec() {
        return getSpec(1, 1);
    }

    int getMonthXsColumnCount() {
        return getSpec(3, 0)[0];
    }

    int getRealRatioMaxColumnCount() {
        return getSpec(2, 0)[0];
    }

    int[] getSpec(int i2, int i7);

    int getWidthSpec(int i2) {
        return getSpec(0, i2)[2];
    }

    int getYearColumnCount() {
        return getSpec(4, 0)[0];
    }
}
