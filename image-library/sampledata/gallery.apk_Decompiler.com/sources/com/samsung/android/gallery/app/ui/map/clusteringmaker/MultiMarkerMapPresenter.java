package com.samsung.android.gallery.app.ui.map.clusteringmaker;

import W4.f;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapPresenter;
import com.samsung.android.gallery.app.ui.map.clusteringmaker.IMultiMarkerMapView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.map.clustering.ClusterManager;
import com.samsung.android.gallery.module.map.clustering.MercatorProjection;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiMarkerMapPresenter<V extends IMultiMarkerMapView> extends GalleryMapPresenter<V> {
    /* access modifiers changed from: private */
    public final ClusterManager mClusterManager;
    private final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        private void createFilteredData() {
            for (int i2 = 0; i2 < MultiMarkerMapPresenter.this.mMediaData.getCount(); i2++) {
                MediaItem read = MultiMarkerMapPresenter.this.mMediaData.read(i2);
                if (!(read == null || (read.getLatitude() == MapUtil.INVALID_LOCATION && read.getLongitude() == MapUtil.INVALID_LOCATION))) {
                    MultiMarkerMapPresenter.this.mClusterManager.addItem(MultiMarkerMapPresenter.this.createClusterItem(read));
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            MultiMarkerMapPresenter.this.startCluster(true);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$1() {
            try {
                resetFilteredData();
                ThreadUtil.postOnUiThread(new a(this, 1));
            } catch (NullPointerException e) {
                if (MultiMarkerMapPresenter.this.mMediaData == null || !MultiMarkerMapPresenter.this.mMediaData.isDataAvailable()) {
                    StringCompat access$100 = MultiMarkerMapPresenter.this.TAG;
                    Log.e(access$100, "fail to handle on data change. maybe destroyed : " + MultiMarkerMapPresenter.this.mMediaData);
                    return;
                }
                e.printStackTrace();
            }
        }

        private void resetFilteredData() {
            MultiMarkerMapPresenter.this.mClusterManager.clearItems();
            createFilteredData();
        }

        public void onDataChanged() {
            ThreadUtil.postOnBgThread(new a(this, 0));
        }
    };
    private final String mDataLocationKey;
    private boolean mForceClusterOnCameraIdle = false;
    private String mLatitudeList;
    private String mLongitudeList;
    /* access modifiers changed from: private */
    public MediaData mMediaData;

    public MultiMarkerMapPresenter(Blackboard blackboard, V v, String str, String str2) {
        super(blackboard, v);
        loadInitialLocation();
        Objects.requireNonNull(v);
        this.mClusterManager = new ClusterManager(new f(v, 2), new MercatorProjection());
        this.mDataLocationKey = str2;
        initMediaData();
    }

    private String getDataLocationKey() {
        return this.mDataLocationKey;
    }

    private void initMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(getDataLocationKey());
        this.mMediaData = open;
        open.register(this.mDataChangeListener);
    }

    private boolean isZoomLevelChanged(double d) {
        return this.mClusterManager.isZoomLevelChanged(d);
    }

    public ArrayList<double[]> getLargestClusteredLocations(double d) {
        return this.mClusterManager.getLargestClusteredLocations(d);
    }

    public ArrayList<double[]> getValidLocations() {
        if (TextUtils.isEmpty(this.mLatitudeList) || TextUtils.isEmpty(this.mLongitudeList)) {
            return new ArrayList<>();
        }
        ArrayList<double[]> arrayList = new ArrayList<>();
        String[] split = this.mLatitudeList.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        String[] split2 = this.mLongitudeList.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        int min = Math.min(split.length, split2.length);
        for (int i2 = 0; i2 < min; i2++) {
            try {
                double parseDouble = Double.parseDouble(split[i2]);
                double parseDouble2 = Double.parseDouble(split2[i2]);
                if (MapUtil.isValidLocation(parseDouble, parseDouble2)) {
                    arrayList.add(new double[]{parseDouble, parseDouble2});
                }
            } catch (NumberFormatException unused) {
                Log.w(this.TAG, "fail to validate. ignore : " + split[i2] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + split2[i2]);
            }
        }
        return arrayList;
    }

    public void loadInitialLocation() {
        JSONObject jSONObject = (JSONObject) this.mBlackboard.read("data://user/map/InitialLocation");
        if (jSONObject == null) {
            Log.e(this.TAG, "Initial location is invalid");
            return;
        }
        try {
            this.mLatitudeList = jSONObject.getString("latitudes");
            this.mLongitudeList = jSONObject.getString("longitudes");
            if (jSONObject.has("entryItem")) {
                setEntryItemKey(jSONObject.getLong("entryItem"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean needToForceClusterOnCameraIdle() {
        if (!this.mClusterManager.isFirstTry() || this.mForceClusterOnCameraIdle) {
            return true;
        }
        return false;
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        this.mMediaData.unregister(this.mDataChangeListener);
        this.mMediaData.close();
        this.mMediaData = null;
    }

    public void requestCluster(double d) {
        this.mClusterManager.cluster(d);
    }

    public void startCluster() {
        startCluster(false);
    }

    public void startCluster(boolean z) {
        double mapZoom = ((IMultiMarkerMapView) this.mView).getMapZoom();
        if (!((IMultiMarkerMapView) this.mView).hasMap() || mapZoom == -1.0d) {
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "Skip clustering : dataChanged[" + z + "]");
            this.mForceClusterOnCameraIdle = z;
        } else if (z || isZoomLevelChanged(mapZoom)) {
            StringCompat stringCompat2 = this.TAG;
            Log.d(stringCompat2, "Start cluster : zoom[" + mapZoom + "], dataChanged[" + z + "]");
            this.mForceClusterOnCameraIdle = false;
            this.mClusterManager.cluster(mapZoom);
        } else {
            Log.e(this.TAG, "Data and zoom level is not changed. Update markers..");
            ((IMultiMarkerMapView) this.mView).updateVisibleMarkers();
        }
    }
}
