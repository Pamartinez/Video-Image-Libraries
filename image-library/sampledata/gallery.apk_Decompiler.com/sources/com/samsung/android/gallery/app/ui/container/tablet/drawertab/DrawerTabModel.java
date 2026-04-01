package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.view.View;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerTabModel {
    private IBaseListView mBaseListView;

    private boolean animatedByPadding() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView == null || iBaseListView.isEmptyViewShowing() || this.mBaseListView.isSharing()) {
            return true;
        }
        return false;
    }

    private boolean hasGalleryGridLayoutManager() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView == null || !iBaseListView.hasLayoutManager() || !(this.mBaseListView.getLayoutManager() instanceof GalleryGridLayoutManager)) {
            return false;
        }
        return true;
    }

    private boolean hasPinchLayoutManager() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView == null || !iBaseListView.hasLayoutManager() || !(this.mBaseListView.getLayoutManager() instanceof PinchLayoutManager)) {
            return false;
        }
        return true;
    }

    private boolean isAlbumsListDepth() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView == null || !iBaseListView.isAlbum() || this.mBaseListView.isDexMode() || this.mBaseListView.getListView().getDepth() != 0) {
            return false;
        }
        return true;
    }

    private boolean isBaseListView(IBaseFragment iBaseFragment) {
        return iBaseFragment instanceof IBaseListView;
    }

    private boolean isOngoingPinchAnimation() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView == null || !iBaseListView.isOngoingPinchAnimation()) {
            return false;
        }
        return true;
    }

    private boolean isShowHeaderOnly() {
        GalleryGridLayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            View findViewByPosition = layoutManager.findViewByPosition(layoutManager.findLastVisibleItemPosition());
            if (layoutManager.findLastVisibleItemPosition() == 0 && findViewByPosition != null && layoutManager.getItemViewType(findViewByPosition) == -3) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isSimilarModeAnimating() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView == null || !iBaseListView.isSimilarModeAnimating()) {
            return false;
        }
        return true;
    }

    private boolean isTimelineYearDepth() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView == null || !iBaseListView.supportYearTimeline() || this.mBaseListView.getListView().getDepth() != this.mBaseListView.getListView().getMaxDepth()) {
            return false;
        }
        return true;
    }

    public IBaseListView getBaseListView() {
        return this.mBaseListView;
    }

    public GalleryGridLayoutManager getLayoutManager() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView != null) {
            return (GalleryGridLayoutManager) iBaseListView.getLayoutManager();
        }
        return null;
    }

    public boolean isListViewAnimating() {
        if (isOngoingPinchAnimation() || isSimilarModeAnimating()) {
            return true;
        }
        return false;
    }

    public boolean isListViewTouchOngoing() {
        IBaseListView iBaseListView = this.mBaseListView;
        if (iBaseListView == null || !iBaseListView.isTouchOngoing()) {
            return false;
        }
        return true;
    }

    public void setChild(IBaseFragment iBaseFragment) {
        IBaseListView iBaseListView;
        if (isBaseListView(iBaseFragment)) {
            iBaseListView = (IBaseListView) iBaseFragment;
        } else {
            iBaseListView = null;
        }
        this.mBaseListView = iBaseListView;
    }

    public boolean supportGridLayoutManager() {
        if (!hasGalleryGridLayoutManager() || animatedByPadding()) {
            return false;
        }
        return true;
    }

    public boolean supportSlideAnimation() {
        if (!hasPinchLayoutManager() || this.mBaseListView.isDestroyed() || animatedByPadding()) {
            return false;
        }
        return true;
    }

    public boolean withWidthAnimation() {
        if (isTimelineYearDepth() || isAlbumsListDepth() || isShowHeaderOnly()) {
            return true;
        }
        return false;
    }
}
