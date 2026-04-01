package com.samsung.android.gallery.app.ui.map.staticmarker;

import B8.e;
import a6.g;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import bc.d;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StaticMarkerMapFragment extends GalleryMapFragment {
    private double mLatitude;
    private double mLongitude;
    private MarkerViewHolder mMarkerViewHolder;
    private MediaItem mMediaItem;
    private View mParentView;
    private MapContainer.OnSnapshotReadyListener mSnapshotReady;

    /* access modifiers changed from: private */
    /* renamed from: bindImage */
    public void lambda$loadFinish$0(UniqueKey uniqueKey, Bitmap bitmap) {
        if (this.mMarkerViewHolder != null && equalsItem(this.mMediaItem, uniqueKey)) {
            this.mMarkerViewHolder.setVisibility(0);
            this.mMarkerViewHolder.bind(this.mMediaItem);
            this.mMarkerViewHolder.bindImage(bitmap);
        }
    }

    private boolean equalsItem(MediaItem mediaItem, UniqueKey uniqueKey) {
        if (mediaItem == null || uniqueKey == null || uniqueKey.getKey() != mediaItem.hashCode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void loadFinish(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new d((Object) this, (Object) uniqueKey, (Object) bitmap, 2));
    }

    public View getParentView() {
        return this.mParentView;
    }

    public void keepMarker() {
        this.mMarkerViewHolder = null;
    }

    public void loadMarkerBitmap() {
        if (this.mMediaItem != null) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            MediaItem mediaItem = this.mMediaItem;
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            Objects.requireNonNull(mediaItem);
            instance.getOrLoad(mediaItem, thumbKind, new e(mediaItem, 1), new g(16, this));
            return;
        }
        Log.w(this.TAG, "loadMarkerBitmap skip");
    }

    public void moveCamera() {
        MapContainer mapContainer = this.mMapContainer;
        if (mapContainer != null) {
            mapContainer.moveCamera(this.mLatitude, this.mLongitude);
        }
    }

    public void onDestroy() {
        MarkerViewHolder markerViewHolder = this.mMarkerViewHolder;
        if (markerViewHolder != null) {
            markerViewHolder.recycle();
        }
        super.onDestroy();
    }

    public void onMapReady(Object obj) {
        super.onMapReady(obj);
        this.mMapContainer.setAllGesturesEnabled(false);
        this.mMapContainer.setZoomControlsEnabled(false);
        moveCamera();
    }

    public void onMapReload() {
        super.onMapReload();
        loadMarkerBitmap();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mParentView == null) {
            View view2 = (View) view.getParent();
            this.mParentView = view2;
            this.mMarkerViewHolder = new MarkerViewHolder(view2.findViewById(R.id.map_view_marker_holder));
        }
        loadMarkerBitmap();
    }

    public void setLocation(double d, double d2) {
        this.mLatitude = d;
        this.mLongitude = d2;
        moveCamera();
    }

    public void setMapSnapshotReadyListener(MapContainer.OnSnapshotReadyListener onSnapshotReadyListener) {
        this.mSnapshotReady = onSnapshotReadyListener;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void snapshotReady(Bitmap bitmap) {
        MapContainer.OnSnapshotReadyListener onSnapshotReadyListener = this.mSnapshotReady;
        if (onSnapshotReadyListener != null) {
            onSnapshotReadyListener.onSnapshotReady(bitmap);
        }
    }

    public boolean supportToolbar() {
        return false;
    }

    public void onPrepareSharedTransitionV2() {
    }

    public void setScreenMode() {
    }
}
