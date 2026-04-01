package com.samsung.android.gallery.app.ui.list.abstraction;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardDelegate;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBaseListView extends IMvpBaseView {
    void afterBindViewHolder(ListViewHolder listViewHolder, int i2);

    void changeViewDepthByWheelScroll(int i2);

    boolean checkTabSelectable();

    TipCardDelegate createTipCardDelegate() {
        return null;
    }

    void enterSelectionMode(int i2);

    void exitSelectionMode(boolean z);

    BaseListViewAdapter getAdapter();

    MediaItem[] getAllItems();

    int getAppBarVisibleHeight();

    GalleryAppBarLayout getAppbarLayout();

    View getBottomBar();

    int getBottomBarHeight();

    View getBottomMoveBar();

    int getDataCount();

    int getFirstItemDataPositionFromSelected();

    GridSpans getGridSpans();

    int getItemCount();

    RecyclerView.LayoutManager getLayoutManager();

    GalleryListView getListView();

    MediaData getMediaData(String str);

    Fragment getParentFragment();

    int[] getPinchColumn(boolean z);

    BaseListPresenter getPresenter();

    IReorderHandler getReorderHandler() {
        return null;
    }

    int getSelectedItemCount();

    MediaItem[] getSelectedItems();

    int getStatusBarHeight();

    boolean hasLayoutManager();

    void invalidateToolbar();

    boolean isActive();

    boolean isAlbum() {
        return false;
    }

    boolean isAllowAdvancedMouseEvent() {
        return false;
    }

    boolean isAppBarPartiallyVisible();

    boolean isAutoDragPossible();

    boolean isDataPrepared();

    boolean isDialogVisible();

    boolean isDrawerStateChanged();

    boolean isEmptyViewShowing();

    boolean isEnableOptionsMenu() {
        return false;
    }

    boolean isHideScroller();

    boolean isMoveMode() {
        return false;
    }

    boolean isOngoingPinchAnimation();

    boolean isPopupMenuShowing();

    boolean isSelectionMode();

    boolean isSharing() {
        return false;
    }

    boolean isSimilarModeAnimating();

    boolean isTouchOngoing();

    boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent, int i2);

    boolean onBottomSearchToolbarChanged(View view) {
        return false;
    }

    void onEmptySpaceSecondaryClick(PointF pointF);

    void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7);

    boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem);

    void onListItemSecondaryClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, PointF pointF);

    void prepareExtendedShare();

    void publishThumbnailLoadDone();

    void selectAll();

    void setFloatingToolbarScrollTransition(boolean z);

    void setSmartAlbumEnabled(boolean z);

    void showSingleContent(ListViewHolder listViewHolder);

    void startDragAndDropOnAdvancedMouseAction(int i2, ListViewHolder listViewHolder);

    void startShrinkAnimation();

    void stopScroll();

    boolean supportMonthForViewing();

    boolean supportShareSheet() {
        return true;
    }

    boolean supportSmartAlbum();

    boolean supportYearTimeline() {
        return false;
    }

    void updateDrawerStateToChildFragment(boolean z);

    void updateFadingEdge();

    void updateListColumn();

    void updateListViewBottomPadding(int i2);

    void updateSelectionToolBar();

    void updateTabMode(boolean z);

    void onSelectionModeChanged(boolean z) {
    }

    void operateClipboard(boolean z) {
    }

    void scrollListToPositionByDepth(int i2) {
    }

    void setDrawerStateChanged(boolean z) {
    }

    void updateExtraStartPadding(float f) {
    }
}
