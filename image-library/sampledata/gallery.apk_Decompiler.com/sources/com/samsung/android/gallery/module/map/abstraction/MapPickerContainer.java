package com.samsung.android.gallery.module.map.abstraction;

import U5.b;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.location.LocationManager;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import com.samsung.android.gallery.module.map.manager.AddressHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MapPickerContainer<MAP> implements IMapGetter<MAP> {
    protected final String TAG = getClass().getSimpleName();
    private String mAddressText;
    protected final WeakReference<Context> mContextRef;
    private double[] mCurrentLocationCache = {MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION};
    private View mDimLayer;
    LayoutProvider mLayoutProvider;
    protected double[] mLocation = {MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION};
    protected MAP mMap;
    private ISimpleMarker mMarker;
    private AddressHelper.GetSimpleAddress mMarkerTitleTask;
    protected MapClickListener mOnClickListener;
    /* access modifiers changed from: protected */
    public MarkerDragEndListener mOnMarkerDragEndListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface LayoutProvider {
        View inflateDimLayout(View view);

        View inflateMarkerInfo(Context context, String str);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MapClickListener {
        void onMapClicked(double d, double d2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MarkerDragEndListener {
    }

    public MapPickerContainer(Context context) {
        this.mContextRef = new WeakReference<>(context);
    }

    private void clearTask() {
        AddressHelper.GetSimpleAddress getSimpleAddress = this.mMarkerTitleTask;
        if (getSimpleAddress != null) {
            getSimpleAddress.setUpdateListener((AddressHelper.OnAddressUpdateListener) null);
            this.mMarkerTitleTask.cancel(true);
            this.mMarkerTitleTask = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestByLocationManager$1(Runnable runnable, Location location) {
        storeCurrentLocation(location.getLatitude(), location.getLongitude());
        runnable.run();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startTitleTask$0(Context context, AddressCompat addressCompat) {
        String str;
        if (addressCompat == null || TextUtils.isEmpty(addressCompat.addressSimple)) {
            str = context.getString(R$string.location);
        } else {
            str = addressCompat.addressSimple;
        }
        addMarker(str);
    }

    private void removeMarker() {
        ISimpleMarker iSimpleMarker = this.mMarker;
        if (iSimpleMarker != null) {
            iSimpleMarker.remove();
            this.mMarker = null;
        }
    }

    private void saveAddressText(String str) {
        this.mAddressText = str;
    }

    private void saveLocation(double d, double d2) {
        this.mLocation = new double[]{d, d2};
    }

    private void startTitleTask() {
        clearTask();
        Context context = this.mContextRef.get();
        if (context == null) {
            Log.e(this.TAG, "fail startTitleTask");
            return;
        }
        LocationManager instance = LocationManager.getInstance();
        double[] dArr = this.mLocation;
        instance.loadLocationSimple(dArr[0], dArr[1], new b(29, this, context));
    }

    public abstract ISimpleMarker addMarker(MAP map, double[] dArr, Bitmap bitmap, String str);

    public void addMarker(String str) {
        removeMarker();
        Context context = this.mContextRef.get();
        Object map = getMap();
        if (context == null || map == null) {
            String str2 = this.TAG;
            Log.e(str2, "addMarker fail :" + context + ArcCommonLog.TAG_COMMA + map);
            return;
        }
        this.mMarker = addMarker(map, this.mLocation, BitmapUtils.getBitmapFromDrawable(context.getDrawable(R$drawable.gallery_ic_mapview_location)), str);
    }

    public String getAddressText() {
        return this.mAddressText;
    }

    public View getInfoWindowView(String str) {
        Context context = this.mContextRef.get();
        if (context == null) {
            Log.e(this.TAG, "fail to getInfoWindow");
            return null;
        } else if (!TextUtils.isEmpty(str)) {
            return this.mLayoutProvider.inflateMarkerInfo(context, str);
        } else {
            Log.w(this.TAG, "do not show info window view on marker");
            return null;
        }
    }

    public double[] getLocation() {
        if (this.mMarker != null) {
            return this.mLocation;
        }
        return new double[]{0.0d, 0.0d};
    }

    public double[] getStoredCurrentLocation() {
        return this.mCurrentLocationCache;
    }

    public abstract View getView();

    public abstract void moveCamera();

    public void moveCamera(double d, double d2, String str, String str2) {
        saveLocation(d, d2);
        saveAddressText(str);
        moveCamera();
        addMarker(str2);
    }

    public void onCreate(Bundle bundle) {
        View inflateDimLayout = this.mLayoutProvider.inflateDimLayout(getView());
        if (inflateDimLayout != null) {
            this.mDimLayer = inflateDimLayout;
        }
    }

    public void onDestroy() {
        if (this.mDimLayer != null) {
            ViewGroup viewGroup = (ViewGroup) getView();
            if (viewGroup != null) {
                viewGroup.removeView(this.mDimLayer);
            }
            this.mDimLayer = null;
        }
        clearTask();
        removeMarker();
    }

    public void onMapReady(MAP map) {
        if (getMap() != null || map == null) {
            String str = this.TAG;
            Log.e(str, "onMapReady fail : " + getMap() + ArcCommonLog.TAG_COMMA + map);
            return;
        }
        setMap(map);
        setInfoWindowAdapter(map);
        setMapListeners(map);
        setZoomControlsEnabled(map, true);
        setRotateGesturesEnabled(map, false);
        setCompassEnabled(map, false);
        double[] dArr = this.mLocation;
        if (MapUtil.isValidLocation(dArr[0], dArr[1])) {
            moveCamera();
            startTitleTask();
        }
    }

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onStart();

    public abstract void onStop();

    public boolean requestByLocationManager(Context context, Runnable runnable) {
        double[] currentLocation = MapUtil.getCurrentLocation(context);
        if (MapUtil.isValidLocation(currentLocation[0], currentLocation[1])) {
            storeCurrentLocation(currentLocation[0], currentLocation[1]);
            runnable.run();
            return true;
        }
        MapUtil.requestCurrentLocation(context, new O3.b(21, this, runnable));
        return false;
    }

    public abstract void requestCurrentLocationUpdate(Context context, Runnable runnable);

    public void saveData(double d, double d2, String str) {
        saveLocation(d, d2);
        saveAddressText(str);
    }

    public abstract void setCompassEnabled(MAP map, boolean z);

    public abstract void setInfoWindowAdapter(MAP map);

    public void setLayoutProvider(LayoutProvider layoutProvider) {
        this.mLayoutProvider = layoutProvider;
    }

    public abstract void setMap(MAP map);

    public abstract void setMapListeners(MAP map);

    public void setOnMapClickListener(MapClickListener mapClickListener) {
        this.mOnClickListener = mapClickListener;
    }

    public void setOnMarkerDragEndListener(MarkerDragEndListener markerDragEndListener) {
        this.mOnMarkerDragEndListener = markerDragEndListener;
    }

    public abstract void setRotateGesturesEnabled(MAP map, boolean z);

    public abstract void setZoomControlsEnabled(MAP map, boolean z);

    public void storeCurrentLocation(double d, double d2) {
        this.mCurrentLocationCache = new double[]{d, d2};
    }
}
