package com.samsung.android.gallery.app.ui.list.mapclustering;

import N2.j;
import U3.a;
import U5.c;
import V3.b;
import W4.f;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapViewV2;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.list.ListMenuHandler;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.clustering.ClusterManager;
import com.samsung.android.gallery.module.map.clustering.MercatorProjection;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusteringMapPresenterV2<V extends IClusteringMapViewV2> extends PicturesPresenter<V> {
    private final String mClusterDate;
    private final ClusterManager mClusterManager;
    protected long mEntryItemKey;
    private boolean mForceClusterOnCameraIdle = false;
    private final AtomicBoolean mIgnoreNextDataChanged = new AtomicBoolean(false);
    private double[] mInitialLocation = new double[2];
    private boolean mIsModifiedDateSort;
    private final AtomicBoolean mIsRecentEntryItem = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean mIsSelectedLocationShown = true;
    private String mTitle;
    private final long[] mapDateRange;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MenuUpdater extends ListMenuUpdater {
        public MenuUpdater(V v) {
            super(v, ClusteringMapPresenterV2.this);
        }

        private void updateMenuVisible(MenuDataBinding menuDataBinding, int i2, boolean z) {
            if (menuDataBinding.hasBinding(i2)) {
                menuDataBinding.setVisible(i2, z);
            }
        }

        private void updateNormalMenuVisibility(MenuDataBinding menuDataBinding) {
            boolean isDataShowing = ((IClusteringMapViewV2) ClusteringMapPresenterV2.this.mView).isDataShowing();
            updateMenuVisible(menuDataBinding, R.id.action_select, isDataShowing);
            updateMenuVisible(menuDataBinding, R.id.action_slideshow, isDataShowing);
        }

        private void updateViewAllMenu(MenuDataBinding menuDataBinding) {
            boolean z;
            if (!ClusteringMapPresenterV2.this.mIsSelectedLocationShown || !((IClusteringMapViewV2) ClusteringMapPresenterV2.this.mView).supportViewAll()) {
                z = false;
            } else {
                z = true;
            }
            updateMenuVisible(menuDataBinding, R.id.action_view_all, z);
            updateMenuVisible(menuDataBinding, R.id.action_view_selected, !ClusteringMapPresenterV2.this.mIsSelectedLocationShown);
        }

        public boolean supportCreateMenu() {
            if (!((IClusteringMapViewV2) ClusteringMapPresenterV2.this.mView).isDataShowing() || !super.supportCreateMenu()) {
                return false;
            }
            return true;
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            MenuDataBinding menuDataBinding;
            super.updateOptionsMenuAction(menu, selectionMode);
            if (!ClusteringMapPresenterV2.this.isSelectionMode() && (menuDataBinding = ClusteringMapPresenterV2.this.getMenuDataBinding()) != null) {
                updateNormalMenuVisibility(menuDataBinding);
                updateViewAllMenu(menuDataBinding);
            }
        }
    }

    public ClusteringMapPresenterV2(Blackboard blackboard, V v) {
        super(blackboard, v);
        boolean z = true;
        Objects.requireNonNull(v);
        this.mClusterManager = new ClusterManager(new f(v, 1), new MercatorProjection(), new a(17, v));
        loadInitialLocation();
        String argValue = ArgumentsUtil.getArgValue(((IClusteringMapViewV2) this.mView).getLocationKey(), "cluster_map_date", "");
        this.mClusterDate = argValue;
        this.mapDateRange = StringCompat.toLongArray(argValue, "-");
        String argValue2 = ArgumentsUtil.getArgValue(((IClusteringMapViewV2) this.mView).getLocationKey(), "sort_by", "");
        if (!TextUtils.isEmpty(argValue2)) {
            this.mIsModifiedDateSort = SortByType.getSortBy(Integer.parseInt(argValue2)) != 20 ? false : z;
        }
    }

    private void addClusterItem(MediaData mediaData) {
        if (mediaData != null) {
            ArrayList<MediaItem> cloneExtraData = cloneExtraData(mediaData);
            if (cloneExtraData == null || cloneExtraData.isEmpty() || !mediaData.isFullyLoaded()) {
                for (int i2 = 0; i2 < mediaData.getCount(); i2++) {
                    MediaItem read = mediaData.read(i2);
                    if (isValidLocationItem(read) && !isFilterableDateRange(read)) {
                        if (this.mIsRecentEntryItem.compareAndSet(true, false)) {
                            this.mEntryItemKey = read.getFileId();
                        }
                        this.mClusterManager.addItem(createMapItem(read));
                    }
                }
                return;
            }
            Iterator<MediaItem> it = cloneExtraData.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (isValidLocationItem(next) && !isFilterableDateRange(next)) {
                    if (this.mIsRecentEntryItem.compareAndSet(true, false)) {
                        this.mEntryItemKey = next.getFileId();
                    }
                    this.mClusterManager.addItem(createMapItem(next, new a(18, mediaData)));
                }
            }
        }
    }

    private ArrayList<MediaItem> cloneExtraData(MediaData mediaData) {
        if (mediaData.getExtraData() != null) {
            return new ArrayList<>(mediaData.getExtraData());
        }
        return null;
    }

    private synchronized void cluster(double d) {
        this.mClusterManager.cluster(d);
    }

    private MapItem createMapItem(MediaItem mediaItem) {
        return new MapItem(mediaItem, mediaItem.getLatitude(), mediaItem.getLongitude(), mediaItem.getFileId() == this.mEntryItemKey);
    }

    private double[] getFirstValidLocation(JSONObject jSONObject) {
        String[] split = jSONObject.getString("latitudes").split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        String[] split2 = jSONObject.getString("longitudes").split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        int min = Math.min(split.length, split2.length);
        int i2 = 0;
        while (i2 < min) {
            try {
                double parseDouble = Double.parseDouble(split[i2]);
                double parseDouble2 = Double.parseDouble(split2[i2]);
                if (MapUtil.isValidLocation(parseDouble, parseDouble2)) {
                    return new double[]{parseDouble, parseDouble2};
                }
                i2++;
            } catch (NumberFormatException unused) {
                Log.w("ClusteringMapPresenterV2", "fail to validate. ignore : " + split[i2] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + split2[i2]);
            }
        }
        return new double[]{MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION};
    }

    private String getRestrictedTitle() {
        if (getContext() == null) {
            return "";
        }
        return getContext().getString(R.string.map);
    }

    private String getTitle() {
        String str;
        if (getContext() == null) {
            return "";
        }
        if (!this.mIsSelectedLocationShown || (str = this.mTitle) == null) {
            return getContext().getString(R.string.map);
        }
        return str;
    }

    private boolean isFilterableDateRange(MediaItem mediaItem) {
        long[] jArr;
        if (!this.mIsSelectedLocationShown || TextUtils.isEmpty(this.mClusterDate) || this.mIsModifiedDateSort || (jArr = this.mapDateRange) == null || jArr.length != 2) {
            return false;
        }
        if (jArr[0] > mediaItem.getDateTaken() || mediaItem.getDateTaken() > this.mapDateRange[1]) {
            return true;
        }
        return false;
    }

    private boolean isValidLocationItem(MediaItem mediaItem) {
        if (mediaItem == null || !MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude())) {
            return false;
        }
        return true;
    }

    private boolean isZoomLevelChanged(double d) {
        return this.mClusterManager.isZoomLevelChanged(d);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ThumbnailInterface lambda$addClusterItem$2(MediaData mediaData, long j2) {
        if (mediaData.isDataAvailable()) {
            return mediaData.readById(j2);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateClusterItems$0() {
        startCluster(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateClusterItems$1(MediaData mediaData) {
        try {
            resetClusterItems(mediaData);
            ThreadUtil.postOnUiThread(new b(8, this));
        } catch (NullPointerException e) {
            if (mediaData == null || !mediaData.isDataAvailable()) {
                Log.e("ClusteringMapPresenterV2", "fail to handle on data change. maybe destroyed : " + mediaData);
                return;
            }
            e.printStackTrace();
        }
    }

    private void loadInitialLocation() {
        JSONObject jSONObject = (JSONObject) this.mBlackboard.read("data://user/map/InitialLocation");
        if (jSONObject == null) {
            Log.e("ClusteringMapPresenterV2", "Initial location is invalid");
            return;
        }
        try {
            if (jSONObject.has("latitude") && jSONObject.has("longitude")) {
                this.mInitialLocation[0] = jSONObject.getDouble("latitude");
                this.mInitialLocation[1] = jSONObject.getDouble("longitude");
            } else if (jSONObject.has("latitudes") || jSONObject.has("longitudes")) {
                this.mInitialLocation = getFirstValidLocation(jSONObject);
            }
            if (supportEntryItem() && jSONObject.has("entryItem")) {
                this.mEntryItemKey = jSONObject.getLong("entryItem");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private synchronized void resetClusterItems(MediaData mediaData) {
        this.mClusterManager.clearItems();
        addClusterItem(mediaData);
    }

    private boolean supportEntryItem() {
        return true;
    }

    /* access modifiers changed from: private */
    public void toggleViewAll() {
        boolean z = !this.mIsSelectedLocationShown;
        this.mIsSelectedLocationShown = z;
        ((IClusteringMapViewV2) this.mView).reopenMapData(z);
        updateToolbar(getToolbar());
        updateViewAllMenu();
    }

    private void updateViewAllMenu() {
        boolean z;
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null) {
            if (!this.mIsSelectedLocationShown || !((IClusteringMapViewV2) this.mView).supportViewAll()) {
                z = false;
            } else {
                z = true;
            }
            menuDataBinding.setVisible((int) R.id.action_view_all, z);
            menuDataBinding.setVisible((int) R.id.action_view_selected, !this.mIsSelectedLocationShown);
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding create = MenuFactory.create(((IClusteringMapViewV2) this.mView).getListLocationKey(), ((IClusteringMapViewV2) this.mView).getListLocationKey());
        create.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_view_selected, false));
        create.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_view_all, false));
        return create;
    }

    public MenuHandler createMenuHandler() {
        return new ListMenuHandler() {
            public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId != R.id.action_view_all && itemId != R.id.action_view_selected) {
                    return super.onItemSelected(eventContext, menuItem);
                }
                ClusteringMapPresenterV2.this.toggleViewAll();
                return true;
            }
        };
    }

    public void forceReloadData() {
        if (!((IClusteringMapViewV2) this.mView).isDestroyed()) {
            V v = this.mView;
            MediaData mediaData = ((IClusteringMapViewV2) v).getMediaData(((IClusteringMapViewV2) v).getListLocationKey());
            if (mediaData != null) {
                mediaData.reopen(((IClusteringMapViewV2) this.mView).getListLocationKey());
                if (((IClusteringMapViewV2) this.mView).clearAdvancedMouseFocus()) {
                    ((IClusteringMapViewV2) this.mView).invalidateOptionsMenu();
                }
            }
        }
    }

    public double[] getInitialLocations() {
        return this.mInitialLocation;
    }

    public String getItemCountText(int i2) {
        Context context = getContext();
        if (context == null) {
            Log.se("ClusteringMapPresenterV2", "Couldn't get item count -> Context is null");
            return "";
        }
        Resources resources = context.getResources();
        if (i2 == 1) {
            return resources.getString(R.string.speak_folder_name_1_item);
        }
        return String.format(resources.getString(R.string.speak_folder_name_n_items), new Object[]{Integer.valueOf(i2)});
    }

    public String getLocationKey() {
        return ArgumentsUtil.removeArgs(((IClusteringMapViewV2) this.mView).getListLocationKey());
    }

    public boolean hasEntryItem() {
        if (this.mEntryItemKey > 0) {
            return true;
        }
        return false;
    }

    public final boolean hasMap() {
        return ((IClusteringMapViewV2) this.mView).hasMap();
    }

    public void ignoreNextDataChanged() {
        this.mIgnoreNextDataChanged.set(true);
    }

    public boolean needToForceClusterOnCameraIdle() {
        if (!this.mClusterManager.isFirstTry() || this.mForceClusterOnCameraIdle) {
            return true;
        }
        return false;
    }

    public void notifyDataChanged(MediaData mediaData) {
        if (!this.mIgnoreNextDataChanged.getAndSet(false)) {
            updateClusterItems(mediaData);
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        String str;
        if (TextUtils.isEmpty(this.mClusterDate)) {
            str = ((IClusteringMapViewV2) this.mView).getListLocationKey();
        } else {
            str = ArgumentsUtil.appendArgs(((IClusteringMapViewV2) this.mView).getListLocationKey(), "cluster_map_date", this.mClusterDate);
        }
        new VuLauncher(this.mBlackboard).requestBitmapUrgent().launch(str, i2, mediaItem);
    }

    public void onLocationKeyAssigned() {
        super.onLocationKeyAssigned();
        String argValue = ArgumentsUtil.getArgValue(super.getLocationKey(), "title");
        this.mTitle = argValue;
        if (!TextUtils.isEmpty(argValue)) {
            this.mTitle = this.mTitle.replaceAll("-\n", "");
        }
    }

    public void onViewResume() {
        super.onViewResume();
        if (((IClusteringMapViewV2) this.mView).isRecentEntryItem()) {
            this.mIsRecentEntryItem.set(true);
            ((IClusteringMapViewV2) this.mView).reopenMapData(false);
        }
    }

    public void startCluster() {
        startCluster(false);
    }

    public void updateClusterItems(MediaData mediaData) {
        ThreadUtil.postOnBgThread(new c(17, this, mediaData));
    }

    public void updateToolbar(Toolbar toolbar) {
        String str;
        if (toolbar != null) {
            if (isSelectionMode()) {
                toolbar.setNavigationIcon((Drawable) null);
            } else {
                setNavigationUpButton(toolbar);
            }
            if (Features.isEnabled(Features.IS_USA_DEVICE)) {
                str = getRestrictedTitle();
            } else {
                str = getTitle();
            }
            toolbar.setTitle((CharSequence) str);
            GalleryAppBarLayout appbarLayout = ((IClusteringMapViewV2) this.mView).getAppbarLayout();
            if (appbarLayout != null) {
                appbarLayout.setTitle((CharSequence) str);
            }
        }
    }

    private MapItem createMapItem(MediaItem mediaItem, MapItem.MediaItemProvider mediaItemProvider) {
        return new MapItem(mediaItem.getFileId(), mediaItem.getLatitude(), mediaItem.getLongitude(), mediaItem.getFileId() == this.mEntryItemKey, mediaItemProvider);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new MenuUpdater(v);
    }

    public void startCluster(boolean z) {
        double mapZoom = ((IClusteringMapViewV2) this.mView).getMapZoom();
        if (!hasMap() || mapZoom == -1.0d) {
            Log.i("ClusteringMapPresenterV2", "Skip clustering : dataChanged[" + z + "]");
            this.mForceClusterOnCameraIdle = z;
        } else if (z || isZoomLevelChanged(mapZoom)) {
            StringBuilder sb2 = new StringBuilder("Start cluster : zoom[");
            sb2.append(mapZoom);
            sb2.append("], dataChanged[");
            sb2.append(z);
            j.y(sb2, "]", "ClusteringMapPresenterV2");
            this.mForceClusterOnCameraIdle = false;
            cluster(mapZoom);
        } else {
            Log.e("ClusteringMapPresenterV2", "Data and zoom level is not changed. Update markers..");
            ((IClusteringMapViewV2) this.mView).updateVisibleMarkers();
        }
    }
}
