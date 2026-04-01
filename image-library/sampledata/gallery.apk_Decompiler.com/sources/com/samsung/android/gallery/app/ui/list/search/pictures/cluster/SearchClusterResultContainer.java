package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import A9.c;
import Y3.C0415b;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResultFactory;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import g6.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import q8.a;
import qa.e;
import tb.C0709a;
import v7.w;
import w5.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResultContainer {
    private final ClusterResultFactory mClusterResultFactory;
    private final View mContainer;
    private final EventContext mEventContext;
    private final String mLocationKey;
    private final HashMap<ClusterResultType, ClusterResult> mSearchResultClusterMap = new HashMap<>();

    public SearchClusterResultContainer(View view, EventContext eventContext) {
        this.mContainer = view;
        this.mEventContext = eventContext;
        this.mClusterResultFactory = new ClusterResultFactory();
        this.mLocationKey = eventContext.getLocationKey();
        initializeClusterLayout();
    }

    private ClusterResult getSearchResultCluster(ClusterResultType clusterResultType) {
        Log.s("ClusterContainer", "getSearchResultCluster : " + clusterResultType);
        return this.mSearchResultClusterMap.computeIfAbsent(clusterResultType, new C0709a(2, this));
    }

    private void initializeClusterLayout() {
        View view;
        if (isReorderCluster()) {
            String argValue = ArgumentsUtil.getArgValue(this.mLocationKey, "field");
            argValue.getClass();
            char c5 = 65535;
            switch (argValue.hashCode()) {
                case -1165864931:
                    if (argValue.equals("bucket_display_name")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 552339397:
                    if (argValue.equals("locationtag")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 853207077:
                    if (argValue.equals("persontag")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 1717773957:
                    if (argValue.equals("storytag")) {
                        c5 = 3;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    view = this.mContainer.findViewById(R.id.albums_cluster_view_stub);
                    break;
                case 1:
                    view = this.mContainer.findViewById(R.id.locations_cluster_view_stub);
                    break;
                case 2:
                    view = this.mContainer.findViewById(R.id.people_cluster_view_stub);
                    break;
                case 3:
                    view = this.mContainer.findViewById(R.id.stories_cluster_view_stub);
                    break;
                default:
                    view = null;
                    break;
            }
            if (view != null) {
                ViewUtils.removeSelf(view);
                ((ViewGroup) this.mContainer).addView(view, 0);
            }
        }
    }

    private boolean isItemClicked() {
        return this.mSearchResultClusterMap.values().stream().anyMatch(new e(19));
    }

    private boolean isReorderCluster() {
        String argValue = ArgumentsUtil.getArgValue(this.mLocationKey, "collect_type");
        String argValue2 = ArgumentsUtil.getArgValue(this.mLocationKey, "field");
        if (!SearchWordCollector.Type.KEYWORD_AUTOCOMPLETE.toString().equals(argValue)) {
            return false;
        }
        if ("bucket_display_name".equals(argValue2) || "storytag".equals(argValue2) || "locationtag".equals(argValue2) || "persontag".equals(argValue2)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ClusterResult lambda$getSearchResultCluster$3(ClusterResultType clusterResultType) {
        return this.mClusterResultFactory.create(clusterResultType, this.mContainer, this.mEventContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadClusterData$0(String str, ClusterResultType clusterResultType, ArrayList arrayList) {
        getSearchResultCluster(clusterResultType).loadCluster(str, arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadClusterDataIncludeCarousel$1(ClusterResultType clusterResultType, ArrayList arrayList) {
        getSearchResultCluster(clusterResultType).loadClusterIncludeCarousel(arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadClusterDataIncludeCarousel$2(ClusterResult.OnUiUpdateListener onUiUpdateListener, ClusterResultType clusterResultType, ArrayList arrayList) {
        ClusterResult searchResultCluster = getSearchResultCluster(clusterResultType);
        searchResultCluster.onCreate();
        searchResultCluster.setOnUiUpdateListener(onUiUpdateListener);
        searchResultCluster.loadClusterIncludeCarousel(arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSimpleAutoScrollerInternal$8() {
        Optional.ofNullable(this).ifPresent(new w(3));
    }

    private boolean startSimpleAutoScrollerInternal(int i2) {
        int i7;
        GalleryListView galleryListView;
        if (isItemClicked()) {
            i7 = getClusterItemListViewPosition(i2);
            galleryListView = getAutoScrollerTargetView();
        } else {
            i7 = -1;
            galleryListView = null;
        }
        if (i7 >= 0 || galleryListView != null) {
            new SimpleAutoScroller(this.mEventContext.getBlackboard(), galleryListView, i7).setAlphaWeight(0.2f).withFinishAction(new t8.e(19, this)).start();
            return true;
        }
        Log.w((CharSequence) "ClusterContainer", "startSimpleAutoScrollerInternal failed", Integer.valueOf(i7), galleryListView);
        return false;
    }

    public GalleryListView getAutoScrollerTargetView() {
        for (ClusterResult next : this.mSearchResultClusterMap.values()) {
            if (next.isItemClicked()) {
                return next.getListView();
            }
        }
        return null;
    }

    public int getClusterItemListViewPosition(int i2) {
        for (ClusterResult next : this.mSearchResultClusterMap.values()) {
            if (next.isItemClicked()) {
                return next.getClusterItemListViewPosition(i2);
            }
        }
        return -1;
    }

    public HashMap<ClusterResultType, ClusterResult> getClusterResultMap() {
        return this.mSearchResultClusterMap;
    }

    public List<GalleryListView> getSelectableListView() {
        return (List) this.mSearchResultClusterMap.values().stream().filter(new e(18)).map(new a(20)).collect(Collectors.toList());
    }

    public boolean handleEvent(EventMessage eventMessage) {
        boolean z = false;
        for (ClusterResult handleEvent : this.mSearchResultClusterMap.values()) {
            z |= handleEvent.handleEvent(eventMessage);
        }
        return z;
    }

    public void handleOrientationChange(int i2) {
        this.mSearchResultClusterMap.forEach(new b(i2));
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        return this.mSearchResultClusterMap.values().stream().anyMatch(new h3.b(9, motionEvent));
    }

    public void loadClusterData(String str, HashMap<ClusterResultType, ArrayList<String>> hashMap) {
        hashMap.forEach(new A9.a(26, this, str));
    }

    public void loadClusterDataIncludeCarousel(HashMap<ClusterResultType, ArrayList<HashMap<ClusterResultType, ArrayList<String>>>> hashMap) {
        hashMap.forEach(new f(13, this));
    }

    public void loadTopResultData(ClusterResultsEntity clusterResultsEntity) {
        if (clusterResultsEntity != null) {
            ClusterResult searchResultCluster = getSearchResultCluster(ClusterResultType.TOP_RESULT);
            searchResultCluster.onCreate();
            searchResultCluster.setEntity(clusterResultsEntity);
        }
    }

    public void onDestroy() {
        this.mSearchResultClusterMap.forEach(new c(11));
    }

    public void onPause() {
        this.mSearchResultClusterMap.forEach(new c(9));
    }

    public void onResume() {
        this.mSearchResultClusterMap.forEach(new c(12));
    }

    public void resetClusterItemClicked() {
        this.mSearchResultClusterMap.forEach(new c(10));
    }

    public void setEnabled(boolean z) {
        this.mSearchResultClusterMap.forEach(new C0415b(z, 2));
    }

    public boolean startSimpleAutoScroller(int i2) {
        return startSimpleAutoScrollerInternal(i2);
    }

    public void loadClusterDataIncludeCarousel(HashMap<ClusterResultType, ArrayList<HashMap<ClusterResultType, ArrayList<String>>>> hashMap, ClusterResult.OnUiUpdateListener onUiUpdateListener) {
        hashMap.forEach(new A9.a(25, this, onUiUpdateListener));
    }
}
