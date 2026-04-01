package com.samsung.android.gallery.app.ui.list.mapclustering;

import N2.j;
import W4.e;
import W4.f;
import android.text.TextUtils;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapView;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.map.clustering.ClusterManager;
import com.samsung.android.gallery.module.map.clustering.MercatorProjection;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusteringMapPresenter<V extends IClusteringMapView> extends GalleryMapPresenter<V> {
    private final ClusterManager mClusterManager;
    private final MediaData.OnDataChangeListener mDataChangeListener;
    private String mEntryItemPath;
    protected double mFirstZoomLevel;
    private boolean mForceClusterOnCameraIdle;
    private double mLatitude;
    private double mLongitude;
    private MediaData mMapMediaData;
    private final String sDataLocationKey;

    public ClusteringMapPresenter(Blackboard blackboard, V v, String str) {
        super(blackboard, v);
        this.mForceClusterOnCameraIdle = false;
        this.mFirstZoomLevel = 13.0d;
        AnonymousClass1 r42 = new MediaData.SimpleDataChangeListener() {
            public void onDataChanged() {
                ClusteringMapPresenter.this.onMapMediaDataChanged();
            }
        };
        this.mDataChangeListener = r42;
        loadInitialLocation();
        this.sDataLocationKey = str;
        Objects.requireNonNull(v);
        this.mClusterManager = new ClusterManager(new f(v, 0), new MercatorProjection());
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(str);
        this.mMapMediaData = open;
        open.register(r42);
    }

    private void cluster(double d) {
        getCurrentClusterManager().cluster(d);
    }

    private boolean isZoomLevelChanged(double d) {
        return getCurrentClusterManager().isZoomLevelChanged(d);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMapMediaDataChanged$0() {
        startCluster(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMapMediaDataChanged$1() {
        try {
            resetClusterData();
            ((IClusteringMapView) this.mView).retryMarkerClicked();
            ThreadUtil.postOnUiThread(new e(this, 1));
        } catch (NullPointerException e) {
            MediaData mediaData = this.mMapMediaData;
            if (mediaData == null || !mediaData.isDataAvailable()) {
                StringCompat stringCompat = this.TAG;
                Log.e(stringCompat, "fail to handle on data change. maybe destroyed : " + this.mMapMediaData);
                return;
            }
            e.printStackTrace();
        }
    }

    private void resetClusterData() {
        this.mClusterManager.clearItems();
        createClusterData(this.mMapMediaData);
    }

    public void createClusterData(MediaData mediaData) {
        long j2 = -1;
        String str = null;
        for (int i2 = 0; i2 < this.mMapMediaData.getCount(); i2++) {
            MediaItem read = this.mMapMediaData.read(i2);
            if (read != null) {
                if (isEntryItem(read)) {
                    j2 = read.getFileId();
                }
                if (TextUtils.equals(this.mEntryItemPath, read.getPath())) {
                    str = read.getPath();
                }
                this.mClusterManager.addItem(createClusterItem(read));
            }
        }
        StringCompat stringCompat = this.TAG;
        StringBuilder j3 = j.j(j2, "Find item key (", ArcCommonLog.TAG_COMMA);
        j3.append(getEntryItemKey());
        j3.append("), item path (");
        j3.append(Logger.getEncodedString(str + ArcCommonLog.TAG_COMMA + this.mEntryItemPath));
        j3.append(")");
        Log.d(stringCompat, j3.toString());
    }

    public void forceReloadData() {
        MediaData mediaData = this.mMapMediaData;
        if (mediaData != null) {
            mediaData.reopen(this.sDataLocationKey);
        }
    }

    public ClusterManager getCurrentClusterManager() {
        return this.mClusterManager;
    }

    public ArrayList<double[]> getValidLocations() {
        return new ArrayList<>(Arrays.asList(new double[][]{new double[]{this.mLatitude, this.mLongitude}}));
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 101) {
            forceReloadData();
            return true;
        } else if (i2 != 3017) {
            return false;
        } else {
            return true;
        }
    }

    public final boolean hasMap() {
        return ((IClusteringMapView) this.mView).hasMap();
    }

    public void initFirstMapViewZoomLevel() {
        if (hasMap() && getCurrentClusterManager().isFirstTry()) {
            setMapZoomLevel((float) this.mFirstZoomLevel);
        }
    }

    public void loadInitialLocation() {
        JSONObject jSONObject = (JSONObject) this.mBlackboard.read("data://user/map/InitialLocation");
        if (jSONObject == null) {
            Log.e(this.TAG, "Initial location is invalid");
            return;
        }
        try {
            this.mLatitude = jSONObject.getDouble("latitude");
            this.mLongitude = jSONObject.getDouble("longitude");
            if (jSONObject.has("entryItem")) {
                setEntryItemKey(jSONObject.getLong("entryItem"));
            }
            if (jSONObject.has(FileApiContract.Parameter.PATH)) {
                this.mEntryItemPath = jSONObject.getString(FileApiContract.Parameter.PATH);
            }
            if (jSONObject.has("mapZoomLevel")) {
                this.mFirstZoomLevel = jSONObject.getDouble("mapZoomLevel");
            }
            Log.d(this.TAG, "loadInitialLocation", Double.valueOf(this.mLatitude), Double.valueOf(this.mLongitude), Long.valueOf(getEntryItemKey()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean needToForceClusterOnCameraIdle() {
        if (!getCurrentClusterManager().isFirstTry() || this.mForceClusterOnCameraIdle) {
            return true;
        }
        return false;
    }

    public void onMapMediaDataChanged() {
        ThreadUtil.postOnBgThread(new e(this, 0));
    }

    public void onNavigationPressed(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_UP_KEY);
        BlackboardUtils.publishBackKeyEventWithDelay(this.mBlackboard);
        Log.majorEvent("onNavigationPressed : " + Logger.getEncodedString(ThreadUtil.getCallStack()));
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        this.mMapMediaData.unregister(this.mDataChangeListener);
        this.mMapMediaData.close();
        this.mMapMediaData = null;
    }

    public final void setMapZoomLevel(float f) {
        ((IClusteringMapView) this.mView).setMapZoomLevel(f);
    }

    public void startCluster() {
        startCluster(false);
    }

    public void startCluster(boolean z) {
        double mapZoom = ((IClusteringMapView) this.mView).getMapZoom();
        if (!hasMap() || mapZoom == -1.0d) {
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "Skip clustering : dataChanged[" + z + "]");
            this.mForceClusterOnCameraIdle = z;
        } else if (z || isZoomLevelChanged(mapZoom)) {
            StringCompat stringCompat2 = this.TAG;
            Log.d(stringCompat2, "Start cluster : zoom[" + mapZoom + "], dataChanged[" + z + "]");
            this.mForceClusterOnCameraIdle = false;
            cluster(mapZoom);
        } else {
            Log.e(this.TAG, "Data and zoom level is not changed. Update markers..");
            ((IClusteringMapView) this.mView).updateVisibleMarkers();
        }
    }

    public ClusteringMapPresenter(Blackboard blackboard, V v) {
        this(blackboard, v, "location://map");
    }

    public void onMapReady() {
    }
}
