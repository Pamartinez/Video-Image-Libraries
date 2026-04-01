package com.samsung.android.gallery.app.ui.list.mapclustering;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapPresenter;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapView;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UpdatableMapFragment<V extends IClusteringMapView, P extends ClusteringMapPresenter<V>> extends ClusteringMapFragment<V, P> implements IClusteringMapView {
    private String getDataLocationKey() {
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "dataKey");
        if (!TextUtils.isEmpty(argValue)) {
            return argValue;
        }
        return getLocationKey();
    }

    public int getToolbarTitleId() {
        return R.string.places_visited;
    }

    public boolean isFilterable() {
        return true;
    }

    public void moveCamera() {
        moveCameraForMultipleLocations();
    }

    public ClusteringMapPresenter createPresenter(IClusteringMapView iClusteringMapView) {
        return new FilterableClusteringMapPresenter(getBlackboard(), this, getDataLocationKey());
    }
}
