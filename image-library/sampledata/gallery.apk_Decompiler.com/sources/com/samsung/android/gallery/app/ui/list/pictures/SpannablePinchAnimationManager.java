package com.samsung.android.gallery.app.ui.list.pictures;

import android.graphics.RectF;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.AlbumPinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.PinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.SpannablePinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.StoriesPinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SpannablePinchAnimationManager extends PicturesPinchAnimationManagerV2 {
    public SpannablePinchAnimationManager(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo, boolean z) {
        super(pinchLayoutManager, clusterInfo, z);
    }

    private int getFirstVisibleDataPosition(int i2) {
        for (int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition < this.mLayoutManager.findLastVisibleItemPosition(); findFirstVisibleItemPosition++) {
            if (ViewHolderValue.isData(this.mLayoutManager.getHintItemViewType(findFirstVisibleItemPosition, i2))) {
                return findFirstVisibleItemPosition;
            }
        }
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    public boolean animWithUpdateLayoutParam(RectF[] rectFArr) {
        if (withRealRatio()) {
            return true;
        }
        if (!this.mGridInfo.withYear() && ((double) Math.abs((rectFArr[0].width() / rectFArr[0].height()) - (rectFArr[1].width() / rectFArr[1].height()))) < 0.01d) {
            return true;
        }
        return false;
    }

    public PinchFakeLayoutManager createFakeLayoutManager(ViewGroup viewGroup) {
        if (isAlbum()) {
            return new AlbumPinchFakeLayoutManager(this.mLayoutManager, getRecyclerView(), viewGroup, this.mPositionCache);
        }
        if (isStories()) {
            return new StoriesPinchFakeLayoutManager(this.mLayoutManager, getRecyclerView(), viewGroup, this.mPositionCache);
        }
        return new SpannablePinchFakeLayoutManager(this.mLayoutManager, getRecyclerView(), viewGroup, this.mPositionCache);
    }

    public void initializeFakeViewLayoutTransAnimation(int i2, boolean z) {
        SpannablePinchAnimationManager spannablePinchAnimationManager;
        if (this.mLayoutManager.isHintSpannable(this.mGridInfo.to())) {
            int startDataRow = this.mFakeLayoutManager.getStartDataRow();
            Iterator<ListViewHolder> it = this.mFakeLayoutManager.getFakeViewHolders().iterator();
            Integer num = null;
            int i7 = 0;
            while (it.hasNext()) {
                ListViewHolder next = it.next();
                SpanInfo hintSpanInfo = this.mLayoutManager.getHintSpanInfo(next.getViewPosition(), this.mGridInfo.to());
                if (num == null) {
                    num = Integer.valueOf(hintSpanInfo.getRow());
                }
                if (ViewHolderValue.isData(next.getViewType())) {
                    spannablePinchAnimationManager = this;
                    spannablePinchAnimationManager.addDataAnimator(next, ((hintSpanInfo.getRow() - num.intValue()) + startDataRow) - i7, hintSpanInfo.getColumn(), true, false);
                } else {
                    spannablePinchAnimationManager = this;
                    i7++;
                }
                this = spannablePinchAnimationManager;
            }
            return;
        }
        super.initializeFakeViewLayoutTransAnimation(i2, z);
    }

    public void initializeRecyclerViewTransAnimation(int i2, int i7) {
        SpannablePinchAnimationManager spannablePinchAnimationManager;
        if (this.mLayoutManager.isHintSpannable(this.mGridInfo.from())) {
            int row = this.mLayoutManager.getHintSpanInfo(i2, this.mGridInfo.from()).getRow();
            int firstVisibleDataPosition = getFirstVisibleDataPosition(this.mGridInfo.from());
            int i8 = 0;
            int i10 = 0;
            while (i8 < this.mLayoutManager.getChildCount()) {
                ListViewHolder listViewHolder = (ListViewHolder) this.getChildViewHolder(this.mLayoutManager.getChildAt(i8));
                if (listViewHolder == null || listViewHolder.getViewPosition() < firstVisibleDataPosition) {
                    spannablePinchAnimationManager = this;
                } else {
                    SpanInfo hintSpanInfo = this.mLayoutManager.getHintSpanInfo(listViewHolder.getViewPosition(), this.mGridInfo.from());
                    if (ViewHolderValue.isData(listViewHolder.getViewType())) {
                        spannablePinchAnimationManager = this;
                        spannablePinchAnimationManager.addDataAnimator(listViewHolder, (hintSpanInfo.getRow() - row) - i10, hintSpanInfo.getColumn(), false, false);
                    } else {
                        spannablePinchAnimationManager = this;
                        i10++;
                    }
                }
                i8++;
                this = spannablePinchAnimationManager;
            }
            return;
        }
        super.initializeRecyclerViewTransAnimation(i2, i7);
    }

    public boolean isNotSupportPivotOnFingerPos() {
        return true;
    }
}
