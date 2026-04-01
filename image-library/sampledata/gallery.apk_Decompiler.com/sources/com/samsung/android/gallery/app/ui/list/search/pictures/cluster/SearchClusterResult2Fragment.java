package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesMultipleHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.ISearchClusterResultView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResult2Presenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;
import v5.a;
import v7.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResult2Fragment<V extends ISearchClusterResultView, P extends SearchClusterResult2Presenter<V>> extends SearchPicturesFragment<V, P> implements ISearchClusterResultView {
    private SearchClusterHeaderView getClusterHeaderView() {
        SearchHeaderView headerView = getHeaderView();
        if (headerView instanceof SearchClusterHeaderView) {
            return (SearchClusterHeaderView) headerView;
        }
        return null;
    }

    private SearchClusterResultContainer getClusterResultContainer() {
        if (getClusterHeaderView() != null) {
            return getClusterHeaderView().getSearchClusterResultContainer();
        }
        return null;
    }

    private void handleSecondaryButtonClickedFromSubList(EventMessage eventMessage) {
        Object[] objArr;
        if (setInputBlock(this.TAG + "_onListItemSecondaryClickEvent") && (objArr = (Object[]) eventMessage.obj) != null && objArr.length == 2) {
            createPopupMenu((ViewGroup) getListView().getRootView(), (PointF) objArr[0], (MediaItem) objArr[1]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$enterSelectionMode$0() {
        return Boolean.valueOf(isAutoDragPossible());
    }

    private void sendSelectAllEvent(boolean z) {
        int i2;
        Blackboard blackboard = getBlackboard();
        if (z) {
            i2 = 1041;
        } else {
            i2 = ErrorCodeConvertor.ERROR_NOT_SUPPORTED_API;
        }
        blackboard.postEvent(EventMessage.obtain(i2));
    }

    private boolean startSimpleAutoScrollerForHeader(int i2) {
        if (getClusterHeaderView() == null || !getClusterHeaderView().startSimpleAutoScroller(i2)) {
            return false;
        }
        return true;
    }

    private void updateEmptyViewVisible() {
        if (getDataCount() != 0 && isEmptyViewShowing()) {
            ViewUtils.setVisibleOrGone(this.mEmptyView, false);
        }
    }

    public void addLayoutListenerForAutoScroll() {
        if (supportShrinkTransition()) {
            int returnPosition = SharedTransition.getReturnPosition(this.mBlackboard);
            if (getAdapter() == null || getAdapter().getItemCount() <= returnPosition) {
                this.mBlackboard.erase("data://shrink_active");
            } else {
                super.addLayoutListenerForAutoScroll();
            }
        }
    }

    public void bindHeaderData(SearchHeaderView searchHeaderView) {
        if (getHeaderType() == SearchHeaderView.HeaderType.CLUSTER_RESULT) {
            ((SearchClusterResult2Presenter) this.mPresenter).clusterKeyDataLoaded();
        } else {
            super.bindHeaderData(searchHeaderView);
        }
    }

    public boolean canScrollVertically() {
        return !((Boolean) this.mBlackboard.read("data://dragging_selection_on_sub_list", Boolean.FALSE)).booleanValue();
    }

    public void clearSearchCluster() {
        getMultipleHeaderContainer().clearAll();
        Optional.ofNullable(getClusterResultContainer()).ifPresent(new w(2));
    }

    public void enterSelectionMode(int i2) {
        super.enterSelectionMode(i2);
        this.mBlackboard.publish("data://user/AutoDragPossible", new a(2, this));
    }

    public void exitSelectionMode(boolean z) {
        super.exitSelectionMode(z);
        this.mBlackboard.erase("data://user/AutoDragPossible");
    }

    public HashMap<String, Supplier<Object>> getHeaderUpdateSupplierMap() {
        HashMap<String, Supplier<Object>> headerUpdateSupplierMap = super.getHeaderUpdateSupplierMap();
        ((SearchClusterResult2Presenter) this.mPresenter).addHeaderUpdateSupplier(headerUpdateSupplierMap);
        return headerUpdateSupplierMap;
    }

    public String getLocationWithExtraArgs() {
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            return ArgumentsUtil.appendArgs(getLocationKey(), "disable_day_merge", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        return super.getLocationWithExtraArgs();
    }

    public SearchPicturesMultipleHeaderContainer getMultipleHeaderContainer() {
        if (this.mMultipleHeaderContainer == null) {
            this.mMultipleHeaderContainer = new SearchPicturesMultipleHeaderContainer(getContext()).supportViewByDate();
        }
        return this.mMultipleHeaderContainer;
    }

    public boolean hasClusterResult() {
        return ((SearchClusterResult2Presenter) this.mPresenter).hasClusterResult();
    }

    public boolean isPicturesOnlyMode() {
        return true;
    }

    public void loadClusterDataIncludeCarousel(HashMap hashMap) {
        this.mBlackboard.post("data://user/SearchClusterResultOnHeader", new Object[]{hashMap});
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        Object eventContextData = ((SearchClusterResult2Presenter) this.mPresenter).getEventContextData("ocr_ids");
        if (eventContextData != null) {
            Logger.dumpLog(printWriter, str + "OCR ids : " + eventContextData);
        }
        Object eventContextData2 = ((SearchClusterResult2Presenter) this.mPresenter).getEventContextData("frame_id_map");
        if (eventContextData2 != null) {
            Logger.dumpLog(printWriter, str + "Video Frame idMap : " + eventContextData2);
        }
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        setContainerVisibility(!isEmptyViewShowing());
        updateEmptyViewVisible();
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8014) {
            getMultipleHeaderContainer().resetViewBy();
            return true;
        } else if (i2 == 1044) {
            handleSecondaryButtonClickedFromSubList(eventMessage);
            return true;
        } else if (getClusterHeaderView() == null || !getClusterHeaderView().handleEvent(eventMessage)) {
            return super.onHandleEvent(eventMessage);
        } else {
            return true;
        }
    }

    public void onSelectAll() {
        super.onSelectAll();
        sendSelectAllEvent(true);
    }

    public void onStartDragViaItemLongClickedEvent(ListViewHolder listViewHolder, MediaItem mediaItem) {
        MediaItem[] selectedItems;
        if (isAutoDragPossible() && (selectedItems = getSelectedItems()) != null) {
            this.mDragAndDropDelegate.startDrag(selectedItems, listViewHolder);
            performHaptic(14);
        }
    }

    public void onUnSelectAll() {
        super.onUnSelectAll();
        sendSelectAllEvent(false);
    }

    public void startSimpleAutoScroller(int i2) {
        if (!startSimpleAutoScrollerForHeader(i2)) {
            super.startSimpleAutoScroller(i2);
        }
    }

    public boolean supportExpand() {
        return !ArgumentsUtil.getArgValue(getLocationKey(), "need_keyword_search", false);
    }

    public boolean supportMenu() {
        return true;
    }

    public boolean supportViewPool() {
        return false;
    }

    public void updateItemCounts() {
        super.updateCountVisible(true);
    }

    public PicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SearchClusterResult2Adapter(this, getLocationKey(), createHeaderView(), isSupportRealRatio());
    }

    public SearchClusterResult2Presenter createPresenter(ISearchClusterResultView iSearchClusterResultView) {
        return new SearchClusterResult2Presenter(this.mBlackboard, iSearchClusterResultView);
    }

    public void setPendingLayoutChange() {
    }

    public void setContainerVisibility(boolean z) {
    }

    public void loadClusterData(String str, HashMap hashMap) {
    }
}
