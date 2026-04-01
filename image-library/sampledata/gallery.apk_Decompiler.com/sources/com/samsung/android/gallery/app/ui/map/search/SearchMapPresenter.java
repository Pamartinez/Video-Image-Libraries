package com.samsung.android.gallery.app.ui.map.search;

import A.a;
import N2.j;
import W4.f;
import a6.g;
import android.content.Context;
import android.location.Location;
import androidx.window.embedding.c;
import b7.C0430b;
import com.samsung.android.gallery.app.ui.map.LocationPermissionUtil;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapPresenter;
import com.samsung.android.gallery.app.ui.map.search.ISearchMapView;
import com.samsung.android.gallery.module.data.LocationValue;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.clustering.ClusterManager;
import com.samsung.android.gallery.module.map.clustering.MercatorProjection;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMapPresenter<V extends ISearchMapView> extends GalleryMapPresenter<V> {
    private final ClusterManager mClusterManager;
    private boolean mForceClusterOnCameraIdle = false;
    private double[] mLocation;
    private final ISearchMapView mView;

    public SearchMapPresenter(Blackboard blackboard, V v, LocationValue locationValue) {
        super(blackboard, v);
        this.mView = v;
        Objects.requireNonNull(v);
        this.mClusterManager = new ClusterManager(new f(v, 3), new MercatorProjection());
        if (locationValue != null) {
            onDataChanged(locationValue);
        }
    }

    private void cluster(double d) {
        this.mClusterManager.cluster(d);
    }

    private boolean isZoomLevelChanged(double d) {
        return this.mClusterManager.isZoomLevelChanged(d);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveTo$3(double[] dArr) {
        this.mView.animateCamera(new double[]{dArr[0], dArr[1], 14.0d});
        this.mView.updateVisibleMarkers();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToCurrentLocation$2(Location location) {
        moveTo(new double[]{location.getLatitude(), location.getLongitude()});
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChanged$0() {
        startCluster(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChanged$1() {
        try {
            resetClusterItems();
            ThreadUtil.postOnUiThread(new C0430b(this, 0));
        } catch (NullPointerException e) {
            MediaData mediaData = getMediaData();
            if (mediaData == null || !mediaData.isDataAvailable()) {
                Log.e("SearchMapPresenter", "fail to handle on data change. maybe destroyed : " + mediaData);
                return;
            }
            e.printStackTrace();
        }
    }

    private void moveTo(double[] dArr) {
        if (this.mLocation == null) {
            Log.e("SearchMapPresenter", "moveTo skip. null latest location");
        } else {
            ThreadUtil.postOnUiThread(new c(5, this, dArr));
        }
    }

    private void resetClusterItems() {
        this.mClusterManager.clearItems();
        MediaData mediaData = getMediaData();
        for (int i2 = 0; i2 < mediaData.getCount(); i2++) {
            MediaItem read = mediaData.read(i2);
            if (read != null) {
                this.mClusterManager.addItem(createClusterItem(read));
            }
        }
    }

    private void updateLatestInfo(LocationValue locationValue) {
        this.mLocation = new double[]{locationValue.latitude, locationValue.longitude};
        setEntryItemKey(locationValue.id);
    }

    public double[] getLocation() {
        return this.mLocation;
    }

    public MediaData getMediaData() {
        return this.mView.getMediaData();
    }

    public final boolean hasMap() {
        return this.mView.hasMap();
    }

    public boolean isMenuInitRequired() {
        return false;
    }

    public void moveToCurrentLocation() {
        String str;
        try {
            Context context = getContext();
            if (context == null || !LocationPermissionUtil.isDeviceLocationStatusAvailable(context, R.string.moreinfo_location_editor_gps_popup_body) || !LocationPermissionUtil.hasLocationPermission(getActivity(), true, this.mView.getPermissionLauncher())) {
                if (context != null) {
                    str = "no permission";
                } else {
                    str = "null context";
                }
                Log.e("SearchMapPresenter", "moveToCurrentLocation failed. ".concat(str));
                return;
            }
            double[] currentLocation = MapUtil.getCurrentLocation(context);
            if (!MapUtil.isValidLocation(currentLocation[0], currentLocation[1])) {
                Log.d("SearchMapPresenter", "moveToCurrentLocation#request");
                MapUtil.requestCurrentLocation(context, new g(9, this));
                return;
            }
            Log.d("SearchMapPresenter", "moveToCurrentLocation");
            moveTo(currentLocation);
        } catch (Exception e) {
            a.s(e, new StringBuilder("moveToCurrentLocation failed. e="), "SearchMapPresenter");
        }
    }

    public boolean needToForceClusterOnCameraIdle() {
        if (!this.mClusterManager.isFirstTry() || this.mForceClusterOnCameraIdle) {
            return true;
        }
        return false;
    }

    public void onDataChanged(LocationValue locationValue) {
        if (getMediaData() != null) {
            updateLatestInfo(locationValue);
            ThreadUtil.postOnBgThread(new C0430b(this, 1));
        }
    }

    public void startCluster() {
        startCluster(false);
    }

    public void startCluster(boolean z) {
        double mapZoom = this.mView.getMapZoom();
        if (!hasMap() || mapZoom == -1.0d) {
            Log.i("SearchMapPresenter", "Skip clustering : dataChanged[" + z + "]");
            this.mForceClusterOnCameraIdle = z;
        } else if (z || isZoomLevelChanged(mapZoom)) {
            StringBuilder sb2 = new StringBuilder("Start cluster : zoom[");
            sb2.append(mapZoom);
            sb2.append("], dataChanged[");
            sb2.append(z);
            j.y(sb2, "]", "SearchMapPresenter");
            this.mForceClusterOnCameraIdle = false;
            cluster(mapZoom);
        } else {
            Log.d("SearchMapPresenter", "Data and zoom level is not changed. update markers..");
            this.mView.updateVisibleMarkers();
        }
    }
}
