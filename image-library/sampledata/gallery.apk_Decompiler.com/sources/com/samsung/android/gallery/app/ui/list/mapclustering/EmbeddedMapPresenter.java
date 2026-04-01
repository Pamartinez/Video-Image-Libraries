package com.samsung.android.gallery.app.ui.list.mapclustering;

import U5.c;
import U9.b;
import V8.a;
import com.samsung.android.gallery.app.ui.list.mapclustering.IEmbeddedMapView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.model.MapLatLng;
import com.samsung.android.gallery.module.map.model.MapLatLngBounds;
import com.samsung.android.gallery.module.map.model.MapVisibleRegion;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmbeddedMapPresenter<V extends IEmbeddedMapView> extends ClusteringMapPresenter<V> {
    private MapLatLngBounds mBounds;
    private final AtomicBoolean mUpdateZoomLevel = new AtomicBoolean(false);
    private final ArrayList<MediaItem> mValidItems = new ArrayList<>();
    private final ArrayList<double[]> mValidLocations = new ArrayList<>();

    public EmbeddedMapPresenter(Blackboard blackboard, V v, String str) {
        super(blackboard, v, str);
    }

    private float getZoomLevel(MapLatLngBounds mapLatLngBounds) {
        double d;
        MapLatLngBounds mapLatLngBounds2 = this.mBounds;
        if (mapLatLngBounds2 == null) {
            return (float) this.mFirstZoomLevel;
        }
        MapLatLng mapLatLng = mapLatLngBounds.northeast;
        double d2 = mapLatLng.latitude;
        MapLatLng mapLatLng2 = mapLatLngBounds.southwest;
        double d3 = d2 - mapLatLng2.latitude;
        double d5 = mapLatLng.longitude - mapLatLng2.longitude;
        MapLatLng mapLatLng3 = mapLatLngBounds2.northeast;
        double d6 = mapLatLng3.latitude;
        MapLatLng mapLatLng4 = mapLatLngBounds2.southwest;
        double d7 = d6 - mapLatLng4.latitude;
        double d9 = mapLatLng3.longitude - mapLatLng4.longitude;
        float f = 21.0f;
        while (f > 2.0f && (d3 <= d7 || d5 <= d9)) {
            if (f % 1.0f == 0.0f) {
                d = 1.5d;
            } else {
                d = 1.3333333730697632d;
            }
            d3 *= d;
            d5 *= d;
            f -= 0.5f;
        }
        return Math.max(2.0f, f - 0.2f);
    }

    private void initBounds(ArrayList<MediaItem> arrayList) {
        MapLatLngBounds.Builder builder = new MapLatLngBounds.Builder();
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            builder.include(new MapLatLng(next.getLatitude(), next.getLongitude()));
        }
        this.mBounds = builder.build();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createClusterData$1(MediaItem mediaItem) {
        getCurrentClusterManager().addItem(createClusterItem(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createClusterData$2(ArrayList arrayList) {
        this.mValidLocations.clear();
        this.mValidItems.clear();
        this.mValidItems.addAll(arrayList);
        initBounds(arrayList);
    }

    private void setValidLocations() {
        Iterator<MediaItem> it = this.mValidItems.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            double latitude = next.getLatitude();
            double longitude = next.getLongitude();
            try {
                if (MapUtil.isValidLocation(latitude, longitude)) {
                    this.mValidLocations.add(new double[]{latitude, longitude});
                }
            } catch (NumberFormatException unused) {
                StringCompat stringCompat = this.TAG;
                Log.w(stringCompat, "fail to validate. ignore : " + latitude + GlobalPostProcInternalPPInterface.SPLIT_REGEX + longitude);
            }
        }
    }

    public void createClusterData(MediaData mediaData) {
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < mediaData.getCount(); i2++) {
            MediaItem read = mediaData.read(i2);
            if (!(read == null || (read.getLatitude() == MapUtil.INVALID_LOCATION && read.getLongitude() == MapUtil.INVALID_LOCATION))) {
                arrayList.add(read);
            }
        }
        arrayList.sort(Comparator.comparing(new a(7)).reversed());
        if (arrayList.get(0) != null) {
            j2 = ((MediaItem) arrayList.get(0)).getFileId();
        } else {
            j2 = -1;
        }
        setEntryItemKey(j2);
        arrayList.forEach(new b(9, this));
        ThreadUtil.runOnUiThread(new c(18, this, arrayList));
        Log.d(this.TAG, "createClusterData +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public MapLatLngBounds getBounds() {
        return this.mBounds;
    }

    public ArrayList<double[]> getValidLocations() {
        if (this.mValidLocations.isEmpty()) {
            setValidLocations();
        }
        return this.mValidLocations;
    }

    public void initFirstMapViewZoomLevel() {
        if (hasMap() && getCurrentClusterManager().isFirstTry()) {
            setMapZoomLevel(21.0f);
            this.mUpdateZoomLevel.set(true);
        }
    }

    public void onCameraIdle() {
        if (this.mUpdateZoomLevel.getAndSet(false)) {
            MapVisibleRegion visibleRegion = ((IEmbeddedMapView) this.mView).getVisibleRegion();
            if (visibleRegion == null) {
                setMapZoomLevel((float) this.mFirstZoomLevel);
            } else {
                setMapZoomLevel(getZoomLevel(visibleRegion.latLngBounds));
            }
        }
    }

    public void loadInitialLocation() {
    }
}
