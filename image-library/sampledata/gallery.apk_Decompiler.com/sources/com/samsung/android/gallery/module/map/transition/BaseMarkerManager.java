package com.samsung.android.gallery.module.map.transition;

import J.g;
import Tb.a;
import W4.b;
import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.manager.IMarkerIconManager;
import com.samsung.android.gallery.module.map.manager.MarkerIconManager;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerCollection;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import q5.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseMarkerManager {
    protected final String TAG = tag();
    private MarkerWithPosition mClickedMarkerWithPosition;
    private Set<ICluster<MapItem>> mCurrentClusters;
    private MarkerCollection mCurrentCollection;
    private double mCurrentZoom;
    private MarkerWithPosition mEntryMarker;
    private long mFocusedId;
    private IMarkerIconManager mIconManager;
    private boolean mNeedToShowEntryMarker = true;
    private final TransitionQueueHandler mUpdateHandler;

    public BaseMarkerManager(Context context) {
        this.mIconManager = createMarkerIconManager(context);
        this.mUpdateHandler = new TransitionQueueHandler(context);
        this.mCurrentCollection = new MarkerCollection();
        this.mCurrentClusters = Collections.newSetFromMap(new ConcurrentHashMap());
    }

    private void changeMarkerIcon(IMapMarker iMapMarker, Bitmap bitmap) {
        try {
            iMapMarker.setIcon(bitmap);
        } catch (IllegalArgumentException e) {
            String str = this.TAG;
            Log.e(str, "changeMarkerIcon failed. e=" + e.getMessage());
        }
    }

    private void clearAll() {
        this.mEntryMarker = null;
        this.mClickedMarkerWithPosition = null;
        this.mCurrentCollection.clear();
        this.mUpdateHandler.clear();
    }

    public static Set<ICluster<MapItem>> getClusters(BaseMarkerManager baseMarkerManager) {
        return baseMarkerManager.mCurrentClusters;
    }

    public static MarkerCollection getCurrentCollection(BaseMarkerManager baseMarkerManager) {
        return baseMarkerManager.mCurrentCollection;
    }

    public static MarkerWithPosition getEntryMarker(BaseMarkerManager baseMarkerManager) {
        return baseMarkerManager.mEntryMarker;
    }

    private void hideHighlightMarker(IMapMarker iMapMarker, ICluster<MapItem> iCluster) {
        highlightMarker(false, iMapMarker, iCluster);
    }

    private void highlightMarker(boolean z, IMapMarker iMapMarker, ICluster<MapItem> iCluster) {
        ThumbnailInterface mediaItem = iCluster.getTopItem().getMediaItem();
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.SMALL_CROP_KIND;
        Objects.requireNonNull(mediaItem);
        instance.getOrLoad(mediaItem, thumbKind, new i(mediaItem, 0), new g((Object) this, iMapMarker, (ICluster) iCluster, z, mediaItem, 4));
    }

    private boolean isSameLocation(MarkerWithPosition markerWithPosition, MarkerWithPosition markerWithPosition2) {
        if (markerWithPosition == null || markerWithPosition2 == null || markerWithPosition.getPosition()[0] != markerWithPosition2.getPosition()[0] || markerWithPosition.getPosition()[1] != markerWithPosition2.getPosition()[1]) {
            return false;
        }
        return true;
    }

    private boolean isSameMarker(IMapMarker iMapMarker, IMapMarker iMapMarker2) {
        if (iMapMarker == null || iMapMarker2 == null || !iMapMarker.getId().equals(iMapMarker2.getId())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMarkerIncludeFocusedId$10(long j2, MapItem mapItem) {
        if (mapItem.getFileId() == j2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Stream lambda$getMarkerIncludeFocusedId$13(ICluster iCluster) {
        return this.mCurrentCollection.getAllMarkers().stream().filter(new b(iCluster, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getMarkerIncludeFocusedId$6(MapItem mapItem) {
        if (mapItem.getFileId() == this.mFocusedId) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getMarkerIncludeFocusedId$7(ICluster iCluster) {
        return iCluster.getItems().stream().anyMatch(new a(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Stream lambda$getMarkerIncludeFocusedId$9(ICluster iCluster) {
        return this.mCurrentCollection.getAllMarkers().stream().filter(new b(iCluster, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$highlightMarker$4(IMapMarker iMapMarker, Bitmap bitmap, ICluster iCluster, boolean z, ThumbnailInterface thumbnailInterface) {
        changeMarkerIcon(iMapMarker, makeIcon(bitmap, iCluster.getSize(), z, thumbnailInterface));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$highlightMarker$5(IMapMarker iMapMarker, ICluster iCluster, boolean z, ThumbnailInterface thumbnailInterface, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThumbnailInterface thumbnailInterface2 = thumbnailInterface;
        ICluster iCluster2 = iCluster;
        ThreadUtil.runOnUiThread(new a(this, iMapMarker, bitmap, iCluster2, z, thumbnailInterface2, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onMarkerClicked$2(IMapMarker iMapMarker, MarkerWithPosition markerWithPosition) {
        return isSameMarker(markerWithPosition.getMarker(), iMapMarker);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onMarkerClicked$3(MarkerWithPosition markerWithPosition) {
        return isSameLocation(markerWithPosition, this.mClickedMarkerWithPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$updateMarkerCollection$0(MapItem mapItem) {
        if (mapItem.getFileId() == this.mFocusedId) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$updateMarkerCollection$1(MarkerWithPosition markerWithPosition) {
        return markerWithPosition.getCluster().getItems().stream().anyMatch(new a(this, 4));
    }

    private Bitmap makeIcon(Bitmap bitmap, int i2, boolean z, ThumbnailInterface thumbnailInterface) {
        return this.mIconManager.makeIcon(bitmap, i2, z, thumbnailInterface);
    }

    public static void setEntryMarker(BaseMarkerManager baseMarkerManager, IMapMarker iMapMarker, ICluster<MapItem> iCluster) {
        baseMarkerManager.mEntryMarker = new MarkerWithPosition(iMapMarker, iCluster);
    }

    public static void setIconManager(BaseMarkerManager baseMarkerManager, IMarkerIconManager iMarkerIconManager) {
        baseMarkerManager.mIconManager = iMarkerIconManager;
    }

    public static void setOnTransitionEndListener(BaseMarkerManager baseMarkerManager, Runnable runnable) {
        baseMarkerManager.mUpdateHandler.setOnTransitionEndListener(runnable);
    }

    private void showHighlightMarker(IMapMarker iMapMarker, ICluster<MapItem> iCluster) {
        highlightMarker(true, iMapMarker, iCluster);
    }

    public abstract IMapMarker addMarker(double[] dArr, Bitmap bitmap);

    public MarkerWithPosition addNewMarker(ICluster<MapItem> iCluster, Bitmap bitmap) {
        boolean z = this.mNeedToShowEntryMarker && iCluster.isEntryItem();
        Bitmap makeIcon = makeIcon(bitmap, iCluster.getSize(), z, iCluster.getTopItem().getMediaItem());
        MarkerWithPosition createMarkerWithPosition = createMarkerWithPosition(addMarker(iCluster.getPosition(), makeIcon), iCluster);
        if (z) {
            Log.d(this.TAG, "addNewMarker (EntryMarker Bitmap)", makeIcon);
            this.mEntryMarker = createMarkerWithPosition;
        }
        return createMarkerWithPosition;
    }

    public abstract void animateMarker(MarkerWithPosition markerWithPosition, float f, double[] dArr, double[] dArr2, float f5);

    public void clearUpdateVisibleMarkers() {
        this.mUpdateHandler.queue(new Transition(this, this.mCurrentClusters, true, true));
    }

    public IMarkerIconManager createMarkerIconManager(Context context) {
        return new MarkerIconManager(context);
    }

    public abstract MarkerWithPosition createMarkerWithPosition(IMapMarker iMapMarker, ICluster<MapItem> iCluster);

    public void destroy() {
        clearAll();
    }

    public IMapMarker getClickedMarker() {
        MarkerWithPosition markerWithPosition = this.mClickedMarkerWithPosition;
        if (markerWithPosition != null) {
            return markerWithPosition.getMarker();
        }
        return null;
    }

    public MarkerCollection getCurrentMarkerCollection() {
        return this.mCurrentCollection;
    }

    public double getCurrentZoom() {
        return this.mCurrentZoom;
    }

    public abstract double getMapZoom();

    public MarkerWithPosition getMarkerIncludeFocusedId() {
        if (this.mFocusedId > 0) {
            return (MarkerWithPosition) this.mCurrentCollection.getAllClusters().stream().filter(new a(this, 0)).flatMap(new c(this, 0)).findFirst().orElse((Object) null);
        }
        return null;
    }

    public void hideEntryMarker(IMapMarker iMapMarker) {
        MarkerWithPosition markerWithPosition;
        ICluster<MapItem> cluster;
        if (this.mNeedToShowEntryMarker && (markerWithPosition = this.mEntryMarker) != null && !markerWithPosition.getMarker().equals(iMapMarker) && (cluster = this.mEntryMarker.getCluster()) != null) {
            hideHighlightMarker(this.mEntryMarker.getMarker(), cluster);
        }
        this.mNeedToShowEntryMarker = false;
    }

    public abstract boolean isVisible(double[] dArr);

    public ICluster<MapItem> onMarkerClicked(IMapMarker iMapMarker) {
        MarkerWithPosition orElse = this.mCurrentCollection.getAllMarkers().stream().filter(new T3.a(4, this, iMapMarker)).findFirst().orElse((Object) null);
        if (this.mClickedMarkerWithPosition != null) {
            if (orElse == null || !isSameMarker(orElse.getMarker(), this.mClickedMarkerWithPosition.getMarker())) {
                if (orElse == null) {
                    orElse = this.mCurrentCollection.getAllMarkers().stream().filter(new a(this, 2)).findFirst().orElse((Object) null);
                }
                ICluster<MapItem> findClusterByMarker = this.mCurrentCollection.findClusterByMarker(this.mClickedMarkerWithPosition.getMarker());
                if (findClusterByMarker != null) {
                    hideHighlightMarker(this.mClickedMarkerWithPosition.getMarker(), findClusterByMarker);
                    this.mClickedMarkerWithPosition = null;
                }
            } else {
                this.mFocusedId = orElse.getCluster().getTopItem().getFileId();
                return orElse.getCluster();
            }
        }
        if (orElse == null) {
            return null;
        }
        this.mClickedMarkerWithPosition = orElse;
        this.mFocusedId = orElse.getCluster().getTopItem().getFileId();
        showHighlightMarker(orElse.getMarker(), orElse.getCluster());
        return orElse.getCluster();
    }

    public void removeClickedMarker() {
        ICluster<MapItem> findClusterByMarker;
        MarkerWithPosition markerWithPosition = this.mClickedMarkerWithPosition;
        if (!(markerWithPosition == null || (findClusterByMarker = this.mCurrentCollection.findClusterByMarker(markerWithPosition.getMarker())) == null)) {
            hideHighlightMarker(this.mClickedMarkerWithPosition.getMarker(), findClusterByMarker);
        }
        this.mClickedMarkerWithPosition = null;
        this.mFocusedId = -1;
    }

    public void removeOldMarker(MarkerWithPosition markerWithPosition) {
        this.mCurrentCollection.removeMarker(markerWithPosition.getCluster(), markerWithPosition);
        markerWithPosition.getMarker().remove();
    }

    public void setMarkerTitleVisibility(boolean z) {
        this.mIconManager.setMarkerTitleVisibility(z);
    }

    public void startMarkerTransition(Set<ICluster<MapItem>> set) {
        boolean z;
        if (set != null && !set.equals(this.mCurrentCollection.getAllClusters())) {
            double mapZoom = getMapZoom();
            this.mCurrentClusters = set;
            Set<ICluster<MapItem>> set2 = this.mCurrentClusters;
            if (this.mCurrentZoom < mapZoom) {
                z = true;
            } else {
                z = false;
            }
            this.mUpdateHandler.queue(new Transition(this, set2, z));
            this.mCurrentZoom = mapZoom;
        }
    }

    public abstract String tag();

    public void unsetEntryMarker() {
        this.mNeedToShowEntryMarker = false;
        this.mEntryMarker = null;
    }

    public void updateMarkerCollection(MarkerCollection markerCollection) {
        this.mCurrentCollection = markerCollection;
        if (this.mFocusedId >= 0) {
            this.mClickedMarkerWithPosition = markerCollection.getAllMarkers().stream().filter(new a(this, 3)).findFirst().orElse(this.mClickedMarkerWithPosition);
        }
    }

    public void updateMarkerSize(Context context) {
        clearAll();
        this.mIconManager.updateSize(context);
    }

    public void updateVisibleMarkers() {
        this.mUpdateHandler.queue(new Transition(this, this.mCurrentClusters));
    }

    public abstract void updateVisibleRegion();

    public static void setIconManager(BaseMarkerManager baseMarkerManager, IMarkerIconManager iMarkerIconManager, int i2, int i7) {
        baseMarkerManager.mIconManager = iMarkerIconManager;
        if (i2 > 0 && i7 > 0) {
            iMarkerIconManager.setBackgroundResource(i2, i7);
        }
    }

    public MarkerWithPosition getMarkerIncludeFocusedId(long j2) {
        if (j2 > 0) {
            return (MarkerWithPosition) this.mCurrentCollection.getAllClusters().stream().filter(new b(j2, 6)).flatMap(new c(this, 1)).findFirst().orElse((Object) null);
        }
        return null;
    }

    public static MarkerWithPosition addNewMarker(BaseMarkerManager baseMarkerManager, ICluster<MapItem> iCluster, Bitmap bitmap) {
        boolean z = false;
        boolean z3 = baseMarkerManager.mNeedToShowEntryMarker && iCluster.isEntryItem();
        long j2 = baseMarkerManager.mFocusedId;
        boolean z7 = j2 != -1 && iCluster.isItemIncluded(j2);
        int size = iCluster.getSize();
        if (z3 || z7) {
            z = true;
        }
        Bitmap makeIcon = baseMarkerManager.makeIcon(bitmap, size, z, iCluster.getTopItem().getMediaItem());
        MarkerWithPosition createMarkerWithPosition = baseMarkerManager.createMarkerWithPosition(baseMarkerManager.addMarker(iCluster.getPosition(), makeIcon), iCluster);
        if (z3) {
            Log.d(baseMarkerManager.TAG, "addNewMarker (EntryMarker Bitmap)", makeIcon);
            baseMarkerManager.mEntryMarker = createMarkerWithPosition;
        }
        return createMarkerWithPosition;
    }
}
