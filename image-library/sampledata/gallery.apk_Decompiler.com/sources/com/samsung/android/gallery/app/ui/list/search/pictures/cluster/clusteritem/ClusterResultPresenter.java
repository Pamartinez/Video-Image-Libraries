package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import q8.a;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterResultPresenter extends Subscriber implements MediaData.OnDataChangeListener {
    private ArrayList<HashMap<ClusterResultType, ArrayList<String>>> mCarouselClusterKeyList;
    private HashMap<ClusterResultType, String> mCarouselClusterMap;
    private ArrayList<String> mClusterKeyList;
    protected ClusterResultsEntity mEntity;
    protected final EventContext mHandler;
    private ArrayList<String> mIdList;
    protected MediaData mMediaData;
    protected final IClusterResult mView;

    public ClusterResultPresenter(EventContext eventContext, IClusterResult iClusterResult) {
        super(eventContext.getBlackboard());
        this.mHandler = eventContext;
        this.mView = iClusterResult;
    }

    private void loadClusterInner(ArrayList<String> arrayList) {
        this.mClusterKeyList = arrayList;
        initializeData(false);
    }

    private void parseCarouselCluster() {
        this.mCarouselClusterMap = new HashMap<>();
        for (Map.Entry entry : this.mCarouselClusterKeyList.get(0).entrySet()) {
            this.mCarouselClusterMap.put((ClusterResultType) entry.getKey(), getClusterKeyNames((ArrayList) entry.getValue()));
        }
    }

    public void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    public void destroy() {
        closeMediaData();
        super.onDestroy();
    }

    public HashMap<ClusterResultType, String> getCarouselClusterMap() {
        return this.mCarouselClusterMap;
    }

    public String getClusterKeyNames() {
        return (String) this.mClusterKeyList.stream().map(new a(21)).collect(Collectors.joining("','", "'", "'"));
    }

    public String getIds() {
        return "\"" + this.mIdList.get(0).replace("[", "").replace("]", "") + "\"";
    }

    public MediaData getMediaData() {
        return this.mMediaData;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        return false;
    }

    public void initializeData(boolean z) {
        closeMediaData();
        if (z) {
            parseCarouselCluster();
        }
        openMediaData();
    }

    public void loadCluster(String str, ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.mIdList = arrayList2;
        arrayList2.add(str);
        loadClusterInner(arrayList);
    }

    public void loadClusterIncludeCarousel(ArrayList<HashMap<ClusterResultType, ArrayList<String>>> arrayList) {
        this.mCarouselClusterKeyList = arrayList;
        initializeData(true);
    }

    public void onDataChanged() {
        if (ThreadUtil.isMainThread()) {
            onDataChangedOnUi();
        } else {
            ThreadUtil.postOnUiThread(new e(25, this));
        }
    }

    public void onDataChangedOnUi() {
        this.mView.onDataChangedOnUi(this.mMediaData);
    }

    public void onDataRangeChanged(int i2, int i7) {
    }

    public void openMediaData() {
        if (this.mMediaData == null) {
            String locationKey = this.mView.getLocationKey();
            StringCompat stringCompat = this.TAG;
            Log.s(stringCompat, "openMediaData - lkey: " + locationKey);
            MediaData open = MediaDataFactory.getInstance(this.mHandler.getBlackboard()).open(locationKey);
            this.mMediaData = open;
            open.register(this);
        }
    }

    public void setEntity(ClusterResultsEntity clusterResultsEntity) {
        this.mEntity = clusterResultsEntity;
        initializeData(true);
    }

    private String getClusterKeyNames(ArrayList<String> arrayList) {
        return (String) arrayList.stream().map(new a(22)).collect(Collectors.joining("','", "'", "'"));
    }

    public void onDataRangeChanged(int i2, int i7, Object obj) {
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }

    public void onDataRangeInserted(int i2, int i7) {
    }
}
