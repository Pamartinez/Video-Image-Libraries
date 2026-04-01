package com.samsung.android.gallery.app.ui.list.mapclustering;

import V8.a;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.mapclustering.EmbeddedMapPresenter;
import com.samsung.android.gallery.app.ui.list.mapclustering.IEmbeddedMapView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.abstraction.MarkerManager;
import com.samsung.android.gallery.module.map.manager.IMarkerIconManager;
import com.samsung.android.gallery.module.map.model.MapLatLng;
import com.samsung.android.gallery.module.map.model.MapLatLngBounds;
import com.samsung.android.gallery.module.map.model.MapVisibleRegion;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EmbeddedMapFragment<V extends IEmbeddedMapView, P extends EmbeddedMapPresenter<V>> extends ClusteringMapFragment<V, P> implements IEmbeddedMapView {
    private MediaItem[] mData;
    private String mDataLocationKey;

    public EmbeddedMapFragment(String str, MediaItem[] mediaItemArr) {
        this.mDataLocationKey = str;
        this.mData = mediaItemArr;
    }

    private void enterFullViewMode() {
        publishInitialLocation();
        getBlackboard().publish(DataKey.DATA("location://map/filteredFromTripStory"), this.mData);
        getBlackboard().post("command://MoveURL", new UriBuilder("location://map/filteredFromTripStory").appendArg("dataKey", getDataLocationKey()).build());
    }

    private String getDataLocationKey() {
        return this.mDataLocationKey;
    }

    private String getLatitudeList() {
        return (String) ((EmbeddedMapPresenter) this.mPresenter).getValidLocations().stream().map(new a(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }

    private String getLongitudeList() {
        return (String) ((EmbeddedMapPresenter) this.mPresenter).getValidLocations().stream().map(new a(6)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }

    private void publishInitialLocation() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("latitudes", getLatitudeList());
            jSONObject.put("longitudes", getLongitudeList());
            jSONObject.put("entryItem", ((EmbeddedMapPresenter) this.mPresenter).getEntryItemKey());
            getBlackboard().publish("data://user/map/InitialLocation", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void attachIconManager(MarkerManager<?> markerManager, IMarkerIconManager iMarkerIconManager) {
        BaseMarkerManager.setIconManager(markerManager, iMarkerIconManager, R.drawable.gallery_map_view_popup_normal_selected, R.drawable.gallery_map_view_popup_normal_selected);
    }

    public void cameraIdle() {
        super.cameraIdle();
        ((EmbeddedMapPresenter) this.mPresenter).onCameraIdle();
    }

    public int getLayoutId() {
        return R.layout.fragment_embedded_map_layout;
    }

    public MapVisibleRegion getVisibleRegion() {
        return this.mMapContainer.getVisibleRegion();
    }

    public void initView(View view) {
        super.initView(view);
        MarkerManager markerManager = this.mMarkerManager;
        if (markerManager != null) {
            markerManager.setMarkerTitleVisibility(false);
        }
    }

    public boolean isFilterable() {
        return false;
    }

    public void moveCamera() {
        MapLatLngBounds bounds = ((EmbeddedMapPresenter) this.mPresenter).getBounds();
        if (bounds != null) {
            MapLatLng mapLatLng = bounds.northeast;
            double d = mapLatLng.latitude;
            MapLatLng mapLatLng2 = bounds.southwest;
            this.mMapContainer.moveCamera((d + mapLatLng2.latitude) / 2.0d, (mapLatLng.longitude + mapLatLng2.longitude) / 2.0d);
            return;
        }
        super.moveCamera();
    }

    public boolean needToRegisterInsetListener() {
        return false;
    }

    public void onMapClicked() {
        enterFullViewMode();
    }

    public void onMapReady(Object obj) {
        if (!isDestroyed()) {
            super.onMapReady(obj);
            this.mMapContainer.setAllGesturesEnabled(false);
        }
    }

    public void onMarkerClicked(Object obj) {
        enterFullViewMode();
    }

    public boolean supportToolbar() {
        return false;
    }

    public boolean supportZoom() {
        return false;
    }

    public EmbeddedMapPresenter createPresenter(IEmbeddedMapView iEmbeddedMapView) {
        return new EmbeddedMapPresenter(getBlackboard(), this, getDataLocationKey());
    }

    public void setScreenMode() {
    }
}
