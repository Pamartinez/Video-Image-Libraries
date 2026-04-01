package com.samsung.android.gallery.app.ui.map.search;

import J.g;
import U9.a;
import W4.b;
import W8.C0579a;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.map.factory.GalleryMapFactory;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.abstraction.MarkerManager;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.manager.IMarkerIconManager;
import com.samsung.android.gallery.module.map.manager.SmallMapMarkerIconManager;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import q5.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMarkerManagerDelegate {
    private IMarkerIconManager mIconManager;
    private final MarkerManager mMarkerManager;
    private boolean mShowCount = false;

    public SearchMarkerManagerDelegate(Context context, MapContainer mapContainer) {
        this.mMarkerManager = GalleryMapFactory.createMarkerManager(context, mapContainer);
    }

    private int getClusterCount(ICluster<MapItem> iCluster) {
        if (this.mShowCount) {
            return iCluster.getItems().stream().mapToInt(new a(1, this)).sum();
        }
        return -1;
    }

    private int getItemCount(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.isGroupShot()) {
            return 1;
        }
        return Math.max(thumbnailInterface.getCount(), 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int lambda$getClusterCount$2(MapItem mapItem) {
        return getItemCount(mapItem.getMediaItem());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$removeHighlightMarkers$4(MarkerManager markerManager) {
        markerManager.hideEntryMarker((IMapMarker) null);
        markerManager.removeClickedMarker();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHighlightMarker$0(IMapMarker iMapMarker, Bitmap bitmap, ICluster iCluster, boolean z, ThumbnailInterface thumbnailInterface) {
        try {
            iMapMarker.setIcon(this.mIconManager.makeIcon(bitmap, getClusterCount(iCluster), z, thumbnailInterface));
        } catch (IllegalArgumentException e) {
            Log.e("SearchMarkerManagerDelegate", "changeMarkerIcon failed. e=" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHighlightMarker$1(IMapMarker iMapMarker, ICluster iCluster, boolean z, ThumbnailInterface thumbnailInterface, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThumbnailInterface thumbnailInterface2 = thumbnailInterface;
        ICluster iCluster2 = iCluster;
        ThreadUtil.runOnUiThread(new Tb.a(this, iMapMarker, bitmap, iCluster2, z, thumbnailInterface2, 1));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateVisibleMarkers$3(long j2, MapItem mapItem) {
        if (mapItem.getMediaItem().getFileId() == j2) {
            return true;
        }
        return false;
    }

    private void updateHighlightMarker(boolean z, IMapMarker iMapMarker, ICluster<MapItem> iCluster) {
        if (this.mIconManager != null) {
            ThumbnailInterface mediaItem = iCluster.getTopItem().getMediaItem();
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.SMALL_CROP_KIND;
            Objects.requireNonNull(mediaItem);
            instance.getOrLoad(mediaItem, thumbKind, new i(mediaItem, 0), new g((Object) this, iMapMarker, (ICluster) iCluster, z, mediaItem, 3));
        }
    }

    public boolean changeEntryMarker(IMapMarker iMapMarker) {
        MarkerWithPosition entryMarker = BaseMarkerManager.getEntryMarker(this.mMarkerManager);
        String str = null;
        if (entryMarker == null || !entryMarker.getMarker().equals(iMapMarker)) {
            if (entryMarker != null) {
                ICluster<MapItem> cluster = entryMarker.getCluster();
                if (cluster != null) {
                    updateHighlightMarker(false, entryMarker.getMarker(), cluster);
                    cluster.setEntryItem(false);
                }
                str = entryMarker.getId();
            }
            ICluster<MapItem> findClusterByMarker = BaseMarkerManager.getCurrentCollection(this.mMarkerManager).findClusterByMarker(iMapMarker);
            if (findClusterByMarker != null) {
                updateHighlightMarker(true, iMapMarker, findClusterByMarker);
                findClusterByMarker.setEntryItem(true);
            }
            BaseMarkerManager.setEntryMarker(this.mMarkerManager, iMapMarker, findClusterByMarker);
        }
        return !TextUtils.equals(str, iMapMarker.getId());
    }

    public IMapMarker getClickedMarker() {
        MarkerManager markerManager = this.mMarkerManager;
        if (markerManager != null) {
            return markerManager.getClickedMarker();
        }
        return null;
    }

    public MarkerWithPosition getEntryMarkerItems() {
        return BaseMarkerManager.getEntryMarker(this.mMarkerManager);
    }

    public ThumbnailInterface getItem(IMapMarker iMapMarker) {
        ICluster<MapItem> findClusterByMarker = BaseMarkerManager.getCurrentCollection(this.mMarkerManager).findClusterByMarker(iMapMarker);
        if (findClusterByMarker != null) {
            return findClusterByMarker.getTopItem().getMediaItem();
        }
        return null;
    }

    public IMapMarker getMarkerIncludeFocusedId() {
        MarkerManager markerManager = this.mMarkerManager;
        MarkerWithPosition markerIncludeFocusedId = markerManager != null ? markerManager.getMarkerIncludeFocusedId() : null;
        if (markerIncludeFocusedId != null) {
            return markerIncludeFocusedId.getMarker();
        }
        return null;
    }

    public void onDestroy() {
        this.mMarkerManager.destroy();
    }

    public ICluster<MapItem> onMarkerClicked(IMapMarker iMapMarker) {
        return this.mMarkerManager.onMarkerClicked(iMapMarker);
    }

    public void removeHighlightMarkers() {
        Optional.ofNullable(this.mMarkerManager).ifPresent(new C0579a(16));
    }

    public void setIconManager(Context context, boolean z) {
        SmallMapMarkerIconManager smallMapMarkerIconManager = new SmallMapMarkerIconManager(context);
        this.mIconManager = smallMapMarkerIconManager;
        smallMapMarkerIconManager.setMarkerTitleVisibility(z);
        this.mShowCount = z;
        BaseMarkerManager.setIconManager(this.mMarkerManager, this.mIconManager);
    }

    public void setOnTransitionEndListener(Runnable runnable) {
        MarkerManager markerManager = this.mMarkerManager;
        if (markerManager != null) {
            BaseMarkerManager.setOnTransitionEndListener(markerManager, runnable);
        }
    }

    public void startMarkerTransition(Set<ICluster<MapItem>> set) {
        this.mMarkerManager.startMarkerTransition(set);
    }

    public void unsetEntryMarker() {
        Optional.ofNullable(this.mMarkerManager).ifPresent(new C0579a(17));
    }

    public void updateMarkerSize(Context context) {
        this.mMarkerManager.updateMarkerSize(context);
    }

    public void updateVisibleMarkers() {
        this.mMarkerManager.updateVisibleMarkers();
    }

    public void updateVisibleRegion() {
        this.mMarkerManager.updateVisibleRegion();
    }

    public long updateVisibleMarkers(long j2) {
        IMapMarker iMapMarker;
        if (j2 > 0) {
            for (ICluster next : BaseMarkerManager.getClusters(this.mMarkerManager)) {
                boolean anyMatch = next.getItems().stream().anyMatch(new b(j2, 1));
                if (next.isEntryItem() != anyMatch) {
                    next.setEntryItem(anyMatch);
                }
            }
            Iterator<MarkerWithPosition> it = BaseMarkerManager.getCurrentCollection(this.mMarkerManager).getAllMarkers().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MarkerWithPosition next2 = it.next();
                if (next2.getCluster().isEntryItem()) {
                    iMapMarker = next2.getMarker();
                    break;
                }
            }
        }
        iMapMarker = null;
        this.mMarkerManager.clearUpdateVisibleMarkers();
        if (iMapMarker == null) {
            return -1;
        }
        changeEntryMarker(iMapMarker);
        return -1;
    }

    public IMapMarker getMarkerIncludeFocusedId(long j2) {
        MarkerManager markerManager = this.mMarkerManager;
        MarkerWithPosition markerIncludeFocusedId = markerManager != null ? markerManager.getMarkerIncludeFocusedId(j2) : null;
        if (markerIncludeFocusedId != null) {
            return markerIncludeFocusedId.getMarker();
        }
        return null;
    }

    public void setIconManager(Context context, boolean z, int i2, int i7) {
        SmallMapMarkerIconManager smallMapMarkerIconManager = new SmallMapMarkerIconManager(context);
        this.mIconManager = smallMapMarkerIconManager;
        smallMapMarkerIconManager.setMarkerTitleVisibility(z);
        this.mShowCount = z;
        BaseMarkerManager.setIconManager(this.mMarkerManager, this.mIconManager, i2, i7);
    }
}
