package com.samsung.android.gallery.app.ui.list.mapclustering;

import W4.f;
import W4.g;
import android.text.TextUtils;
import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.map.clustering.ClusterManager;
import com.samsung.android.gallery.module.map.clustering.MercatorProjection;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterableClusteringMapPresenter<V extends IClusteringMapView> extends ClusteringMapPresenter<V> {
    private final ClusterManager mFilteredClusterManager;
    private MediaData mFilteredMediaData;
    private String mLatitudeList;
    private String mLongitudeList;
    private boolean mNeedToRequestData = true;
    private int mViewMode = 0;
    private final String sDataLocationKey;

    public FilterableClusteringMapPresenter(Blackboard blackboard, V v, String str) {
        super(blackboard, v);
        this.sDataLocationKey = str;
        Objects.requireNonNull(v);
        this.mFilteredClusterManager = new ClusterManager(new f(v, 0), new MercatorProjection());
        this.mFilteredMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(str);
    }

    private boolean changeViewMode(boolean z) {
        if (getMenuDataBinding() == null) {
            return false;
        }
        this.mViewMode = z ? 1 : 0;
        getMenuDataBinding().setVisible((int) R.id.action_view_all, !z);
        getMenuDataBinding().setVisible((int) R.id.action_view_selected, z);
        if (this.mViewMode == 1) {
            onMapMediaDataChanged();
        } else {
            lambda$onMapReady$0();
        }
        ((IClusteringMapView) this.mView).invalidateOptionsMenu();
        return true;
    }

    private void createFilteredData() {
        for (int i2 = 0; i2 < this.mFilteredMediaData.getCount(); i2++) {
            MediaItem read = this.mFilteredMediaData.read(i2);
            if (!(read == null || (read.getLatitude() == MapUtil.INVALID_LOCATION && read.getLongitude() == MapUtil.INVALID_LOCATION))) {
                this.mFilteredClusterManager.addItem(createClusterItem(read));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFilteredMediaDataChanged$1() {
        startCluster(true);
    }

    /* access modifiers changed from: private */
    /* renamed from: onFilteredMediaDataChanged */
    public void lambda$onMapReady$0() {
        resetFilteredData();
        ThreadUtil.postOnUiThread(new g(this, 0));
    }

    private void resetFilteredData() {
        this.mFilteredClusterManager.clearItems();
        createFilteredData();
    }

    public void forceReloadData() {
        super.forceReloadData();
        MediaData mediaData = this.mFilteredMediaData;
        if (mediaData != null) {
            mediaData.reopen(this.sDataLocationKey);
        }
    }

    public ClusterManager getCurrentClusterManager() {
        if (this.mViewMode == 0) {
            return this.mFilteredClusterManager;
        }
        return super.getCurrentClusterManager();
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

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 1118) {
            this.mFilteredMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(this.sDataLocationKey);
            ((IClusteringMapView) this.mView).retryMarkerClicked();
            lambda$onMapReady$0();
        }
        return super.handleEvent(eventMessage);
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
            if (jSONObject.has("mapZoomLevel")) {
                this.mFirstZoomLevel = jSONObject.getDouble("mapZoomLevel");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onMapMediaDataChanged() {
        if (this.mViewMode == 1) {
            super.onMapMediaDataChanged();
        }
    }

    public void onMapReady() {
        ThreadUtil.postOnBgThread(new g(this, 1));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_view_all) {
            if (this.mNeedToRequestData) {
                BlackboardUtils.publishDataRequest(this.mBlackboard, "location://map");
                this.mNeedToRequestData = false;
            }
            return changeViewMode(true);
        } else if (itemId != R.id.action_view_selected) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            return changeViewMode(false);
        }
    }

    public void onViewCreate() {
        super.onViewCreate();
        initMenu();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        this.mFilteredMediaData.close();
        this.mFilteredMediaData = null;
    }
}
