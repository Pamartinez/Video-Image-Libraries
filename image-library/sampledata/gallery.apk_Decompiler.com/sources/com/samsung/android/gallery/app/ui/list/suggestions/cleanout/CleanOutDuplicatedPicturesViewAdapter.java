package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutDuplicatedPicturesViewAdapter extends BaseCleanOutPicturesViewAdapter {
    public CleanOutDuplicatedPicturesViewAdapter(ICleanOutPicturesView iCleanOutPicturesView, String str, View view) {
        super(iCleanOutPicturesView, str, view);
    }

    public int getItemHeight(int i2) {
        if (isHeader(i2) || isDivider(i2)) {
            return super.getItemHeight(i2);
        }
        return getMeasuredItemHeight();
    }

    public int getMaxScroll() {
        int measuredItemHeight = getMeasuredItemHeight();
        int itemHeight = getItemHeight(getDividerIndex(0));
        return getHeaderViewHeight() + this.mMultiClusterAdapter.getMaxScroll(getGridSize(), (float) measuredItemHeight, itemHeight, itemHeight);
    }

    public void initData() {
        this.mMultiClusterAdapter = new CleanOutClusterHeaderAdapter(this.mMediaData);
    }
}
