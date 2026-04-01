package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutPicturesViewAdapter extends BaseCleanOutPicturesViewAdapter {
    public CleanOutPicturesViewAdapter(ICleanOutPicturesView iCleanOutPicturesView, String str, View view) {
        super(iCleanOutPicturesView, str, view);
    }

    public int getHintItemViewType(int i2, int i7) {
        return 0;
    }

    public int getItemHeight(int i2) {
        return getMeasuredItemHeight();
    }

    public int getItemViewType(int i2) {
        return 0;
    }

    public int getMaxScroll() {
        int i2;
        int measuredItemHeight = getMeasuredItemHeight();
        int gridSize = getGridSize();
        int dataCount = getDataCount();
        int i7 = dataCount / gridSize;
        if (dataCount % gridSize > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return (i7 + i2) * measuredItemHeight;
    }

    public boolean supportHeader() {
        return false;
    }
}
