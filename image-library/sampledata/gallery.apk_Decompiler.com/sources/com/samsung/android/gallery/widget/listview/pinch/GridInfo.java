package com.samsung.android.gallery.widget.listview.pinch;

import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.pinch.v3.GridInfoSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GridInfo implements GridInfoSupplier {
    private int[] mActiveColumns;
    private final ClusterInfo mClusterInfo;
    private int mFrom = -1;
    private int mMax = -1;
    private int mTo = -1;
    private boolean mUseConcatThumbnailForce;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ClusterInfo {
        int getMinMonthGridSize();

        int getMonthForViewingFromGridSize();

        int getMonthForViewingToGridSize();

        int getMonthGridSize();

        int getYearGridSize();

        boolean isMonth(int i2);
    }

    public GridInfo(ClusterInfo clusterInfo) {
        this.mClusterInfo = clusterInfo;
    }

    private boolean animWith(int i2) {
        if (this.mFrom == i2 || calcRealToGrid() == i2) {
            return true;
        }
        return false;
    }

    public int calcRealToGrid() {
        return GridValue.revert(this.mTo);
    }

    public int from() {
        return this.mFrom;
    }

    public boolean fromConcatThumbnail() {
        return useConcatThumbnail(from());
    }

    public boolean fromMonthForViewing() {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo == null || !isFrom(clusterInfo.getMonthForViewingFromGridSize())) {
            return false;
        }
        return true;
    }

    public boolean fromYear() {
        return isYear(this.mFrom);
    }

    public int[] getActiveColumns() {
        return this.mActiveColumns;
    }

    public int getMinMonth() {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo != null) {
            return clusterInfo.getMinMonthGridSize();
        }
        return this.mMax;
    }

    public int getRealToGrid() {
        return this.mTo;
    }

    public boolean hasNoTargetGrid() {
        if (this.mTo == -1) {
            return true;
        }
        return false;
    }

    public boolean hasNoTransition() {
        if (this.mFrom == this.mTo) {
            return true;
        }
        return false;
    }

    public boolean isFrom(int i2) {
        if (this.mFrom == i2) {
            return true;
        }
        return false;
    }

    public boolean isFromRealRatio() {
        if (this.mFrom == 1) {
            return true;
        }
        return false;
    }

    public boolean isGridGettingLarger() {
        if (calcRealToGrid() > this.mFrom) {
            return true;
        }
        return false;
    }

    public boolean isMonth(int i2) {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo != null) {
            return clusterInfo.isMonth(i2);
        }
        if (i2 == this.mMax) {
            return true;
        }
        return false;
    }

    public boolean isMonthForViewing(int i2) {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo == null || i2 != clusterInfo.getMonthForViewingFromGridSize()) {
            return false;
        }
        return true;
    }

    public boolean isRealRatio(int i2) {
        if (GridValue.revert(i2) == 1) {
            return true;
        }
        return false;
    }

    public boolean isToRealRatio() {
        if (calcRealToGrid() == 1) {
            return true;
        }
        return false;
    }

    public boolean isYear(int i2) {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo == null || clusterInfo.getYearGridSize() != i2) {
            return false;
        }
        return true;
    }

    public void reset() {
        set(-1, -1, -1);
        this.mUseConcatThumbnailForce = false;
    }

    public void set(GridInfo gridInfo) {
        this.mFrom = gridInfo.from();
        this.mTo = gridInfo.to();
    }

    public void setActiveColumns(int[] iArr) {
        this.mActiveColumns = iArr;
    }

    public int to() {
        return this.mTo;
    }

    public boolean toYear() {
        return isYear(calcRealToGrid());
    }

    public void updateMonthForViewingToMonth() {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo != null) {
            this.mFrom = clusterInfo.getMonthForViewingFromGridSize();
            this.mTo = this.mClusterInfo.getMonthForViewingToGridSize();
        }
    }

    public void updateYearToMonth() {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo != null) {
            this.mFrom = clusterInfo.getYearGridSize();
            this.mTo = this.mClusterInfo.getMonthGridSize();
        }
    }

    public boolean useConcatThumbnail(int i2) {
        if (this.mUseConcatThumbnailForce || isYear(i2)) {
            return true;
        }
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !isMonthForViewing(i2)) {
            return false;
        }
        return true;
    }

    public void useConcatThumbnailForce() {
        this.mUseConcatThumbnailForce = true;
    }

    public boolean useCustomWidth(int i2) {
        if (isRealRatio(i2) || isYear(i2)) {
            return true;
        }
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !isMonthForViewing(i2)) {
            return false;
        }
        return true;
    }

    public boolean withMonth() {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo == null || !animWith(clusterInfo.getYearGridSize())) {
            return false;
        }
        return true;
    }

    public boolean withMonthForViewing() {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo == null || !animWith(clusterInfo.getMonthForViewingFromGridSize())) {
            return false;
        }
        return true;
    }

    public boolean withRealRatio() {
        return animWith(1);
    }

    public boolean withYear() {
        ClusterInfo clusterInfo = this.mClusterInfo;
        if (clusterInfo == null || !animWith(clusterInfo.getYearGridSize())) {
            return false;
        }
        return true;
    }

    public void set(int i2, int i7) {
        this.mFrom = i2;
        this.mTo = i7;
    }

    public void set(int i2, int i7, int i8) {
        this.mFrom = i2;
        this.mTo = i7;
        this.mMax = i8;
    }
}
