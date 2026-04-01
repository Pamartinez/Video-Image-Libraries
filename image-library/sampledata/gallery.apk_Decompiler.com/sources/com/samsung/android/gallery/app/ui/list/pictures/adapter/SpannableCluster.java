package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpannableCluster extends NoDividerCluster {
    protected int mGridSize = -1;
    protected int mRowCount = -1;
    private final SpecProvider mSpecProvider;

    public SpannableCluster(MediaData mediaData, SpecProvider specProvider) {
        super(mediaData);
        mediaData.getSpanIndexer();
        this.mSpecProvider = specProvider;
    }

    public int getColumnSpan(int i2, int i7) {
        return 0;
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        return (int) ((((float) this.mRowCount) / ((float) i2)) * f);
    }

    public int getRowCount(int i2) {
        return 0;
    }

    public Integer[] getRowInfo(int i2, int i7) {
        return null;
    }

    public int getRowSpan(int i2, int i7) {
        return 0;
    }

    public SpanInfo getSpanInfo(int i2, int i7) {
        return null;
    }

    public int getStartSpan(int i2, int i7) {
        return 0;
    }

    public void recalculatePosition(int i2) {
        this.mSpecProvider.getColumnCount();
        throw null;
    }
}
