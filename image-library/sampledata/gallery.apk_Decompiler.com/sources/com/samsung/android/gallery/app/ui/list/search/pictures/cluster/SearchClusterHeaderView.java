package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.search.pictures.SearchCountHeaderView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import l4.b;
import n4.C0489a;
import n4.C0491c;
import o6.p;
import w5.C0534a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterHeaderView extends SearchCountHeaderView {
    private TextView mAllResultTitle;
    private final Blackboard mBlackboard;
    private ViewGroup mClusterContainer;
    private final SubscriberListener mClusterDataLoadListener = new C0534a(this, 0);
    private final EventContext mEventContext;
    private SearchClusterResultContainer mSearchClusterResultContainer;
    private final SubscriberListener mTopResultDataLoadListener = new C0534a(this, 1);

    public SearchClusterHeaderView(ISearchPicturesView iSearchPicturesView, boolean z) {
        super(iSearchPicturesView.getHeaderTargetView(), z);
        this.mBlackboard = iSearchPicturesView.getBlackboard();
        this.mEventContext = iSearchPicturesView.getPresenter();
    }

    private boolean hasTopResult(HashMap<ClusterResultType, ClusterResult> hashMap) {
        for (ClusterResultType clusterResultType : hashMap.keySet()) {
            if (clusterResultType == ClusterResultType.TOP_RESULT) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadClusterData$0(ClusterResultType clusterResultType) {
        HashMap<ClusterResultType, ClusterResult> clusterResultMap = this.mSearchClusterResultContainer.getClusterResultMap();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.ViewClusterResultMenuOption) && clusterResultMap.get(clusterResultType).getType() == ClusterResultType.CAROUSELS) {
            clusterResultMap.get(clusterResultType).setVisibility(false);
        } else if (clusterResultMap.size() == 1 && clusterResultMap.get(clusterResultType).getType() == ClusterResultType.CAROUSELS) {
            clusterResultMap.get(clusterResultType).updateBottomMargin(0);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePreviousClusterVisible$1(HashMap hashMap, HashMap hashMap2, ClusterResultType clusterResultType) {
        if (!hashMap.containsKey(clusterResultType)) {
            ((ClusterResult) hashMap2.get(clusterResultType)).setVisibility(false);
        }
    }

    /* access modifiers changed from: private */
    public void loadClusterData(Object obj, Bundle bundle) {
        if (obj != null && this.mSearchClusterResultContainer != null) {
            int i2 = 0;
            HashMap hashMap = (HashMap) ((Object[]) obj)[0];
            updatePreviousClusterVisible(hashMap);
            boolean hasTopResult = hasTopResult(hashMap);
            ViewUtils.setVisibleOrGone(this.mAllResultTitle, hasTopResult);
            ViewGroup viewGroup = this.mClusterContainer;
            if (hasTopResult) {
                i2 = getContext().getResources().getDimensionPixelOffset(R.dimen.search_clusters_result_container_bottom_margin);
            }
            ViewMarginUtils.setBottomMargin(viewGroup, i2);
            this.mSearchClusterResultContainer.loadClusterDataIncludeCarousel(hashMap, new p(22, this));
        }
    }

    /* access modifiers changed from: private */
    public void loadTopResultData(Object obj, Bundle bundle) {
        if (obj != null && this.mSearchClusterResultContainer != null) {
            ViewUtils.setVisibleOrGone(this.mAllResultTitle, true);
            ViewMarginUtils.setBottomMargin(this.mClusterContainer, getContext().getResources().getDimensionPixelOffset(R.dimen.search_clusters_result_container_bottom_margin));
            this.mSearchClusterResultContainer.loadTopResultData((ClusterResultsEntity) ((Object[]) obj)[0]);
        } else if (obj == null) {
            ViewUtils.setVisibleOrGone(this.mAllResultTitle, false);
            ViewMarginUtils.setBottomMargin(this.mClusterContainer, 0);
        }
    }

    private void updatePreviousClusterVisible(HashMap<ClusterResultType, ClusterResult> hashMap) {
        HashMap<ClusterResultType, ClusterResult> clusterResultMap = this.mSearchClusterResultContainer.getClusterResultMap();
        if (clusterResultMap != null && !clusterResultMap.isEmpty()) {
            clusterResultMap.keySet().stream().forEach(new C0491c(21, hashMap, clusterResultMap));
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.subscribeOnCurrent("data://user/SearchClusterResultOnHeader", this.mClusterDataLoadListener);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mClusterContainer = (ViewGroup) this.mView.findViewById(R.id.search_clusters_result_container);
        this.mAllResultTitle = (TextView) this.mView.findViewById(R.id.search_cluster_divider_title);
    }

    public int getLayoutId() {
        return R.layout.recycler_item_search_pictures_header_cluster_result;
    }

    public SearchClusterResultContainer getSearchClusterResultContainer() {
        return this.mSearchClusterResultContainer;
    }

    public List<GalleryListView> getSelectableListView() {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            return searchClusterResultContainer.getSelectableListView();
        }
        return null;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer == null || !searchClusterResultContainer.handleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        Optional.ofNullable(this.mSearchClusterResultContainer).ifPresent(new C0489a(i2, 18));
    }

    public void initHeaderItem() {
        if (this.mSearchClusterResultContainer == null) {
            this.mSearchClusterResultContainer = new SearchClusterResultContainer(this.mClusterContainer, this.mEventContext);
        }
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        SearchClusterResultContainer searchClusterResultContainer;
        if (!super.isVirtualCtrlKeyPressedAllowablePoint(motionEvent) || (searchClusterResultContainer = this.mSearchClusterResultContainer) == null || !searchClusterResultContainer.isVirtualCtrlKeyPressedAllowablePoint(motionEvent)) {
            return false;
        }
        return true;
    }

    public void onPause() {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            searchClusterResultContainer.onPause();
        }
    }

    public void onResume() {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            searchClusterResultContainer.onResume();
        }
    }

    public void recycle() {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer != null) {
            searchClusterResultContainer.onDestroy();
        }
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.unsubscribe("data://user/SearchClusterResultOnHeader", this.mClusterDataLoadListener);
        }
    }

    public void setEnabled(boolean z) {
        Optional.ofNullable(this.mSearchClusterResultContainer).ifPresent(new b(z, 14));
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        return true;
    }

    public void showCountHeaderOnly(boolean z) {
        ViewUtils.setVisibleOrGone(this.mView.findViewById(R.id.search_clusters_result_container), !z);
    }

    public boolean startSimpleAutoScroller(int i2) {
        SearchClusterResultContainer searchClusterResultContainer = this.mSearchClusterResultContainer;
        if (searchClusterResultContainer == null || !searchClusterResultContainer.startSimpleAutoScroller(i2)) {
            return false;
        }
        return true;
    }
}
