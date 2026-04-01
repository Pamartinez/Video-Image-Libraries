package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import A.a;
import Gb.b;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumNewLabelUpdater;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.badge.UsbBadgeManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.mtp.UsbStorageState;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.sec.android.gallery3d.R;
import h.C0199b;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import n0.C0235b;
import n5.e;
import o6.p;
import p4.g;
import p4.h;
import p4.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerTabViewAdapter extends RecyclerView.Adapter<DrawerTabItemViewHolder> {
    private static final boolean SUPPORT_VIDEO_STUDIO = Features.isEnabled(Features.SUPPORT_VIDEO_STUDIO);
    private final Blackboard mBlackboard;
    private LayoutCache mCacheLoader;
    private List<DrawerTabItem> mDefaultItemData;
    private int mDrawerState;
    private boolean mFocusedItemFound = false;
    private int mFocusedItemId = -1;
    private final AtomicBoolean mFromAllAlbum;
    private List<DrawerTabItem> mItemData;
    private OnDrawerItemClickListener mListener;
    private MediaData mMediaDataAlbum;
    private MediaData mMediaDataAllAlbum;
    private MediaData mMediaDataFavorite;
    private MediaData mMediaDataRecent;
    boolean mMtpTabEnabled;
    private final SubscriberListener mNewLabelUpdateListener;
    final SubscriberListener mOnUsbStorageChangedListener;
    private final Stack<Integer> mParentIdStack = new Stack<>();
    private String mPreviousFocused;
    boolean mSharedAlbumsTabEnabled;
    private final TabItemEnableCondition mTabItemEnableCondition;
    private UsbBadgeManager mUsbBadgeManager;
    boolean mUsbTabEnabled;
    private final Map<String, Integer> mUsbVolumeTable = new HashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDrawerItemClickListener {
    }

    public DrawerTabViewAdapter(Context context, Blackboard blackboard) {
        h hVar = new h(0, this);
        this.mNewLabelUpdateListener = hVar;
        this.mFromAllAlbum = new AtomicBoolean(false);
        this.mPreviousFocused = null;
        h hVar2 = new h(1, this);
        this.mOnUsbStorageChangedListener = hVar2;
        this.mBlackboard = blackboard;
        initValues(context);
        this.mItemData = new ArrayList(getDefaultItemData());
        blackboard.subscribe("data://user/latest_album_id_changed", hVar);
        setSuggestionBadge((DrawerTabItemViewHolder) null, (DrawerTabItem) null, true);
        setUsbStorageBadge();
        this.mTabItemEnableCondition = new TabItemEnableCondition(blackboard, new i(0, this));
        setHasStableIds(true);
        this.mCacheLoader = LayoutCache.getInstance();
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            Blackboard.getApplicationInstance().subscribeOnUi("event/UsbStorageVolumeChanged", hVar2);
        }
    }

    private void addDefaultItem(DrawerTabItem drawerTabItem) {
        int i2 = 0;
        while (i2 < getDefaultItemData().size()) {
            DrawerTabItem drawerTabItem2 = getDefaultItemData().get(i2);
            if (drawerTabItem2 == null || drawerTabItem2.getDefaultOrder() <= drawerTabItem.getDefaultOrder()) {
                i2++;
            } else {
                getDefaultItemData().add(i2, drawerTabItem);
                return;
            }
        }
    }

    private void addTab(String str) {
        DrawerTabItem drawerTabItem = new DrawerTabItem(str);
        int i2 = 0;
        while (i2 < this.mItemData.size()) {
            DrawerTabItem drawerTabItem2 = this.mItemData.get(i2);
            if (drawerTabItem2 == null || drawerTabItem2.getDefaultOrder() <= drawerTabItem.getDefaultOrder()) {
                i2++;
            } else {
                this.mItemData.add(i2, drawerTabItem);
                notifyItemRangeInserted(i2, 1);
                addDefaultItem(drawerTabItem);
                return;
            }
        }
        this.mItemData.add(drawerTabItem);
        notifyItemRangeInserted(this.mItemData.size(), 1);
        addDefaultItem(drawerTabItem);
    }

    private void bindNewAlbumLabel(DrawerTabItemViewHolder drawerTabItemViewHolder, DrawerTabItem drawerTabItem) {
        if (drawerTabItem != null && drawerTabItem.needNewAlbumLabel(getLatestAlbumId())) {
            drawerTabItemViewHolder.updateEndDecorViews();
        }
    }

    private void collapseList(DrawerTabItemViewHolder drawerTabItemViewHolder, DrawerTabItem drawerTabItem) {
        int i2;
        if (drawerTabItem.isExpanded()) {
            drawerTabItemViewHolder.rotateExpandIcon(drawerTabItem);
            int indexOfItem = getIndexOfItem(drawerTabItem);
            int i7 = 0;
            while (true) {
                i2 = indexOfItem + 1;
                if (getDataCount() <= i2 || getItemData(i2).getDepth() <= drawerTabItem.getDepth()) {
                    notifyItemRangeRemoved(i2, i7);
                    drawerTabItem.setExpanded(false);
                } else {
                    this.mItemData.remove(i2);
                    i7++;
                }
            }
            notifyItemRangeRemoved(i2, i7);
            drawerTabItem.setExpanded(false);
        }
    }

    private DrawerTabItem createItem(MediaItem mediaItem, int i2, boolean z, boolean z3) {
        DrawerTabItem drawerTabItem = new DrawerTabItem(mediaItem, i2);
        drawerTabItem.setExpanded(z);
        drawerTabItem.setSelectFocused(z3);
        return drawerTabItem;
    }

    /* access modifiers changed from: private */
    /* renamed from: expandList */
    public void lambda$onBindViewHolder$3(DrawerTabItemViewHolder drawerTabItemViewHolder, DrawerTabItem drawerTabItem) {
        if (isDrawerOpened() && !drawerTabItem.isExpanded()) {
            int indexOfItem = getIndexOfItem(drawerTabItem);
            int i2 = indexOfItem + 1;
            List<DrawerTabItem> subItemsList = getSubItemsList(drawerTabItem, drawerTabItem.getDepth() + 1);
            if (!subItemsList.isEmpty()) {
                drawerTabItemViewHolder.rotateExpandIcon(drawerTabItem);
                int i7 = i2;
                for (DrawerTabItem next : subItemsList) {
                    this.mItemData.add(i7, next);
                    if (next.getAlbumItem() != null && next.getAlbumItem().getAlbumID() == this.mFocusedItemId) {
                        next.setSelectFocused(true);
                    }
                    i7++;
                    next.setEnabled(this.mTabItemEnableCondition.isEnabled(next.getLocationKey()));
                }
                notifyItemRangeInserted(i2, (i7 - indexOfItem) - 1);
                drawerTabItem.setExpanded(true);
            }
        }
    }

    private void expandListWithoutNotify(DrawerTabItem drawerTabItem, boolean z) {
        if ((isDrawerOpened() || SheetBehaviorCompat.isInTransition(this.mDrawerState)) && !z && !drawerTabItem.isExpanded()) {
            int indexOf = this.mItemData.indexOf(drawerTabItem) + 1;
            List<DrawerTabItem> subItemsList = getSubItemsList(drawerTabItem, drawerTabItem.getDepth() + 1);
            if (!subItemsList.isEmpty()) {
                for (DrawerTabItem add : subItemsList) {
                    this.mItemData.add(indexOf, add);
                    indexOf++;
                }
                drawerTabItem.setExpanded(true);
            }
        }
    }

    private void expandParentGroupList(int i2, boolean z) {
        this.mParentIdStack.clear();
        this.mFocusedItemFound = false;
        ArrayList<MediaItem> albumAllItems = getAlbumAllItems((String) null);
        if (albumAllItems != null) {
            Iterator<MediaItem> it = albumAllItems.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (this.mFocusedItemFound) {
                    break;
                }
                searchFocusedItem(next, i2);
            }
            for (int i7 = 0; i7 < getDataCount() && !this.mParentIdStack.empty(); i7++) {
                DrawerTabItem itemData = getItemData(i7);
                if (isAlbumsTab(itemData)) {
                    expandListWithoutNotify(itemData, z);
                } else {
                    MediaItem albumItem = itemData.getAlbumItem();
                    if (albumItem != null && albumItem.getAlbumID() == this.mParentIdStack.get(0).intValue()) {
                        this.mParentIdStack.remove(0);
                        expandListWithoutNotify(itemData, z);
                    }
                }
            }
        }
    }

    private DrawerTabItem findItemById(List<DrawerTabItem> list, int i2) {
        for (DrawerTabItem next : list) {
            if (next.getAlbumItem() != null && next.getAlbumItem().getAlbumID() == i2) {
                return next;
            }
        }
        return null;
    }

    private ArrayList<MediaItem> getAlbumAllItems(String str) {
        if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums) || !isAllAlbum(str)) {
            if (this.mMediaDataAlbum == null) {
                this.mMediaDataAlbum = getMediaData();
            }
            MediaData mediaData = this.mMediaDataAlbum;
            if (mediaData != null && mediaData.getCount() != 0) {
                return this.mMediaDataAlbum.getAllData();
            }
            Log.w("DrawerTabViewAdapter", "getAlbumAllItems : 0");
            return null;
        }
        if (this.mMediaDataAllAlbum == null) {
            this.mMediaDataAllAlbum = getAllAlbumMediaData();
        }
        MediaData mediaData2 = this.mMediaDataAllAlbum;
        if (mediaData2 != null && mediaData2.getCount() != 0) {
            return this.mMediaDataAllAlbum.getAllData();
        }
        Log.w("DrawerTabViewAdapter", "getAllAlbumAllItems : 0");
        return null;
    }

    private MediaItem getCurrentFolder() {
        return (MediaItem) this.mBlackboard.read("data://current_folder", null);
    }

    private int getDataCount() {
        return this.mItemData.size();
    }

    private int getIndexOfAlbum() {
        for (DrawerTabItem next : getDefaultItemData()) {
            if (isAlbumsTab(next)) {
                return getIndexOfItem(next);
            }
        }
        return 1;
    }

    private int getIndexOfItem(DrawerTabItem drawerTabItem) {
        return this.mItemData.indexOf(drawerTabItem);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getLastIndexOfAlbum() {
        /*
            r4 = this;
            java.util.List r0 = r4.getDefaultItemData()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            r2 = 1
            if (r1 == 0) goto L_0x0027
            java.lang.Object r1 = r0.next()
            com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem r1 = (com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem) r1
            boolean r3 = r1.isStories()
            if (r3 != 0) goto L_0x0021
            boolean r3 = r1.isVideos()
            if (r3 == 0) goto L_0x0008
        L_0x0021:
            int r4 = r4.getIndexOfItem(r1)
            int r4 = r4 - r2
            return r4
        L_0x0027:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter.getLastIndexOfAlbum():int");
    }

    private int getLatestAlbumId() {
        return AlbumNewLabelUpdater.getInstance().getLatestAlbumId();
    }

    private int getLayoutId(int i2) {
        if (i2 == 2) {
            return R.layout.recycler_item_drawer_child_tab_layout;
        }
        return R.layout.recycler_item_drawer_tab_layout;
    }

    private MediaItem getMxVirtualAlbumItem(String str, MediaItem mediaItem) {
        MediaData mxVirtualMediaData;
        MediaItem read;
        int i2;
        if (mediaItem.getCount() > 0 || (mxVirtualMediaData = getMxVirtualMediaData(str)) == null || mxVirtualMediaData.getRealCount() == 0 || (read = mxVirtualMediaData.read(0)) == null) {
            return mediaItem;
        }
        if (LocationKey.isMxVirtualFavoriteAlbum(str)) {
            i2 = BucketUtils.VirtualBucketHolder.favorite;
        } else {
            i2 = BucketUtils.VirtualBucketHolder.recent;
        }
        read.setAlbumID(i2);
        read.setVirtualAlbum(true);
        read.setAlbumType(AlbumType.Virtual);
        return read;
    }

    private MediaData getMxVirtualMediaData(String str) {
        if (LocationKey.isMxVirtualFavoriteAlbum(str)) {
            if (this.mMediaDataFavorite == null) {
                this.mMediaDataFavorite = MediaDataFactory.getInstance(this.mBlackboard).open("location://albums/fileList/mxVirtual/favorite", true);
            }
            return this.mMediaDataFavorite;
        } else if (!LocationKey.isMxVirtualRecentAlbum(str)) {
            return null;
        } else {
            if (this.mMediaDataRecent == null) {
                this.mMediaDataRecent = MediaDataFactory.getInstance(this.mBlackboard).open("location://albums/fileList/mxVirtual/recent", true);
            }
            return this.mMediaDataRecent;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.util.ArrayList] */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem> getSubItemsList(com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem r5, int r6) {
        /*
            r4 = this;
            com.samsung.android.gallery.module.data.MediaItem r0 = r5.getAlbumItem()
            r1 = 0
            if (r0 != 0) goto L_0x0019
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.mFromAllAlbum
            boolean r0 = r0.get()
            if (r0 == 0) goto L_0x0010
            goto L_0x0014
        L_0x0010:
            java.lang.String r1 = r5.getLocationKey()
        L_0x0014:
            java.util.ArrayList r1 = r4.getAlbumAllItems(r1)
            goto L_0x002c
        L_0x0019:
            boolean r5 = r0.isMergedAlbum()
            if (r5 != 0) goto L_0x002c
            java.util.ArrayList r1 = new java.util.ArrayList
            com.samsung.android.gallery.module.data.MediaItem[] r5 = r0.getItemsInFolder()
            java.util.List r5 = java.util.Arrays.asList(r5)
            r1.<init>(r5)
        L_0x002c:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            if (r1 == 0) goto L_0x005f
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x005f
            java.util.Iterator r0 = r1.iterator()
        L_0x003d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r0.next()
            com.samsung.android.gallery.module.data.MediaItem r1 = (com.samsung.android.gallery.module.data.MediaItem) r1
            com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem r2 = new com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem
            r2.<init>((com.samsung.android.gallery.module.data.MediaItem) r1, (int) r6)
            com.samsung.android.gallery.app.ui.container.tablet.drawertab.TabItemEnableCondition r1 = r4.mTabItemEnableCondition
            java.lang.String r3 = r2.getLocationKey()
            boolean r1 = r1.isEnabled(r3)
            r2.setEnabled(r1)
            r5.add(r2)
            goto L_0x003d
        L_0x005f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter.getSubItemsList(com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem, int):java.util.List");
    }

    private boolean isAlbumsTab(DrawerTabItem drawerTabItem) {
        if (drawerTabItem == null) {
            return false;
        }
        if (drawerTabItem.isAlbums() || drawerTabItem.isAllAlbums()) {
            return true;
        }
        return false;
    }

    private boolean isAllAlbum(String str) {
        DrawerTabItem itemData;
        if (str == null || LocationKey.isAllAlbumsMatch(str)) {
            return true;
        }
        if (!LocationKey.isAlbumPictures(str) || (itemData = getItemData(getIndexOfAlbum())) == null || !LocationKey.isAllAlbumsMatch(itemData.getLocationKey())) {
            return false;
        }
        return true;
    }

    private boolean isDrawerOpened() {
        Blackboard blackboard = this.mBlackboard;
        return ((Boolean) blackboard.read("data://drawer_opened", Boolean.valueOf(DrawerUtil.isDrawerDefaultOpen(blackboard)))).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        notifyItemRangeChanged(0, getItemCount(), "new_label");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Object obj, Bundle bundle) {
        int i2;
        if (obj instanceof Integer) {
            i2 = ((Integer) obj).intValue();
        } else {
            i2 = -1;
        }
        Log.d("DrawerTabViewAdapter", "onNewLabelUpdated", Integer.valueOf(i2));
        ThreadUtil.postOnUiThread(new i(1, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onBindViewHolder$2() {
        return !supportTooltip();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshTabItemEnableCondition$11(DrawerTabItem drawerTabItem) {
        drawerTabItem.setEnabled(this.mTabItemEnableCondition.isEnabled(drawerTabItem.getLocationKey()));
        drawerTabItem.setExpandable(this.mTabItemEnableCondition.isExpandable());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$removeAllUsbTab$10(int i2) {
        return this.mItemData.get(i2).getLocationKey().startsWith("location://albums/otg/fileList");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnItemClickListener$4(MediaItem mediaItem) {
        this.mBlackboard.postEvent(EventMessage.obtain(2004, mediaItem.getAlbumID(), new Object[]{Boolean.TRUE, mediaItem.getPath(), mediaItem}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnItemClickListener$5(DrawerTabItem drawerTabItem, DrawerTabItemViewHolder drawerTabItemViewHolder, View view) {
        OnDrawerItemClickListener onDrawerItemClickListener;
        UsbBadgeManager usbBadgeManager;
        if (drawerTabItem.isClickable()) {
            if (drawerTabItemViewHolder.is2ndDepthAlbum() || LocationKey.isMxVirtualAlbum(drawerTabItemViewHolder.getLocationKey())) {
                this.mBlackboard.postEvent(EventMessage.obtain(8521));
                this.mBlackboard.publish("data://albums/current", drawerTabItemViewHolder.getMediaItem());
                Optional.ofNullable(drawerTabItem.getAlbumItem()).ifPresent(new g(0, this));
            }
            if (Features.isEnabled(Features.SUPPORT_USB_STORAGE) && drawerTabItem.isUsb() && (usbBadgeManager = this.mUsbBadgeManager) != null) {
                usbBadgeManager.use(ArgumentsUtil.getArgValue(drawerTabItem.getLocationKey(), "name"));
                drawerTabItem.setShowNewBadge(false);
                notifyItemRangeChanged(drawerTabItemViewHolder.getAbsoluteAdapterPosition(), 1, "new_badge_update");
            }
            OnDrawerItemClickListener onDrawerItemClickListener2 = this.mListener;
            if (onDrawerItemClickListener2 != null) {
                ((p) onDrawerItemClickListener2).b(drawerTabItemViewHolder.getLocationKey());
            }
        } else if (drawerTabItem.isEnabled() && isAlbumsTab(drawerTabItem) && LocationKey.isChildOfAlbums(this.mPreviousFocused) && (onDrawerItemClickListener = this.mListener) != null) {
            ((p) onDrawerItemClickListener).b(drawerTabItemViewHolder.getLocationKey());
        } else if (drawerTabItem.supportExpand()) {
            updateExpandableTab(drawerTabItemViewHolder, drawerTabItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSuggestionBadge$6(DrawerTabItemViewHolder drawerTabItemViewHolder) {
        drawerTabItemViewHolder.updateEndDecorViews();
        drawerTabItemViewHolder.updateCollapsedBadgeVisibility(SheetBehaviorCompat.isCollapsed(this.mDrawerState));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSuggestionBadge$7(DrawerTabItem drawerTabItem, boolean z, DrawerTabItemViewHolder drawerTabItemViewHolder) {
        boolean hasNewSuggestions = BadgeHelper.hasNewSuggestions();
        if (drawerTabItem.showNewBadge() != hasNewSuggestions) {
            drawerTabItem.setShowNewBadge(hasNewSuggestions);
            if (!z && drawerTabItemViewHolder != null) {
                ThreadUtil.postOnUiThread(new C0199b(19, this, drawerTabItemViewHolder));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateUsbTab$9(DrawerTabItem drawerTabItem) {
        this.mItemData.add(drawerTabItem);
        getDefaultItemData().add(drawerTabItem);
    }

    /* access modifiers changed from: private */
    public boolean onPopupMenuTriggered(PopupMenuData popupMenuData) {
        if (supportTooltip()) {
            return false;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(1114, popupMenuData));
        return true;
    }

    /* access modifiers changed from: private */
    public void onUsbStorageChanged(Object obj, Bundle bundle) {
        updateUsbVolumes();
        UsbBadgeManager usbBadgeManager = this.mUsbBadgeManager;
        if (usbBadgeManager != null) {
            usbBadgeManager.loadTable();
        }
        this.mBlackboard.post("command://UpdateBottomNavigationItem", (Object) null);
    }

    private void refreshData(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        List<DrawerTabItem> list = this.mItemData;
        ArrayList arrayList = new ArrayList(getDefaultItemData());
        int indexOfAlbum = getIndexOfAlbum();
        int lastIndexOfAlbum = getLastIndexOfAlbum();
        DrawerTabItem drawerTabItem = list.get(indexOfAlbum);
        refreshMxVirtualAlbumData(arrayList, str);
        if (drawerTabItem.isExpanded()) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int i2 = indexOfAlbum + 1;
            for (int i7 = i2; i7 <= lastIndexOfAlbum; i7++) {
                arrayList3.add(list.get(i7));
            }
            ArrayList<MediaItem> albumAllItems = getAlbumAllItems(str);
            if (albumAllItems != null) {
                Iterator<MediaItem> it = albumAllItems.iterator();
                while (it.hasNext()) {
                    setAlbumData(1, arrayList2, arrayList3, it.next());
                }
            }
            if (!arrayList2.isEmpty()) {
                Log.d("DrawerTabViewAdapter", "refreshData: newAlbumList Size = " + arrayList2);
                arrayList.addAll(i2, arrayList2);
                DrawerTabItem drawerTabItem2 = (DrawerTabItem) arrayList.get(indexOfAlbum);
                if (drawerTabItem2 != null) {
                    drawerTabItem2.setExpanded(drawerTabItem.isExpanded());
                }
            }
        }
        this.mItemData.clear();
        this.mItemData = arrayList;
        a.x(new StringBuilder("refreshData: time = "), currentTimeMillis, "DrawerTabViewAdapter");
    }

    private void refreshMxVirtualAlbumData(List<DrawerTabItem> list, String str) {
        if (LocationKey.isMxVirtualAlbum(str)) {
            for (DrawerTabItem next : list) {
                if (str.equals(next.getLocationKey()) && next.getAlbumItem() != null) {
                    next.setAlbumItem(getMxVirtualAlbumItem(str, next.getAlbumItem()));
                }
            }
        }
    }

    private void refreshTabItemEnableCondition() {
        this.mItemData.forEach(new g(2, this));
    }

    private void removeAllUsbTab(Predicate<DrawerTabItem> predicate) {
        int orElse = IntStream.range(0, this.mItemData.size()).filter(new b(5, this)).findFirst().orElse(-1);
        if (orElse >= 0) {
            this.mItemData.removeIf(predicate);
            getDefaultItemData().removeIf(predicate);
            notifyItemRangeRemoved(orElse, (int) this.mItemData.stream().filter(predicate).count());
        }
    }

    private void removeTab(String str) {
        int itemPos = getItemPos(str);
        DrawerTabItem itemData = getItemData(itemPos);
        if (itemData != null && this.mItemData.remove(itemData)) {
            notifyItemRangeRemoved(itemPos, 1);
            getDefaultItemData().remove(itemData);
        }
    }

    private void searchFocusedItem(MediaItem mediaItem, int i2) {
        this.mParentIdStack.push(Integer.valueOf(mediaItem.getAlbumID()));
        if (mediaItem.getAlbumID() == i2) {
            this.mFocusedItemFound = true;
            return;
        }
        MediaItem[] itemsInFolder = mediaItem.getItemsInFolder();
        int length = itemsInFolder.length;
        int i7 = 0;
        while (i7 < length) {
            MediaItem mediaItem2 = itemsInFolder[i7];
            if (!this.mFocusedItemFound) {
                if (mediaItem2.getAlbumID() == i2) {
                    this.mParentIdStack.push(Integer.valueOf(mediaItem2.getAlbumID()));
                    this.mFocusedItemFound = true;
                    return;
                }
                searchFocusedItem(mediaItem2, i2);
                i7++;
            } else {
                return;
            }
        }
        if (!this.mFocusedItemFound) {
            this.mParentIdStack.pop();
        }
    }

    private void setAlbumData(int i2, List<DrawerTabItem> list, List<DrawerTabItem> list2, MediaItem mediaItem) {
        DrawerTabItem findItemById = findItemById(list2, mediaItem.getAlbumID());
        boolean z = true;
        if (!mediaItem.isFolder() || findItemById == null || !findItemById.isExpanded()) {
            if (findItemById == null || !findItemById.isSelectFocused()) {
                z = false;
            }
            list.add(createItem(mediaItem, i2, false, z));
            return;
        }
        int i7 = i2 + 1;
        list.add(createItem(mediaItem, i2, true, findItemById.isSelectFocused()));
        for (MediaItem albumData : mediaItem.getItemsInFolder()) {
            setAlbumData(i7, list, list2, albumData);
        }
    }

    private void setOnItemClickListener(DrawerTabItemViewHolder drawerTabItemViewHolder, DrawerTabItem drawerTabItem) {
        drawerTabItemViewHolder.getLayout().setOnClickListener(new gc.b(this, drawerTabItem, drawerTabItemViewHolder, 1));
    }

    private void setOnPopupMenuTriggerListener(DrawerTabItemViewHolder drawerTabItemViewHolder, DrawerTabItem drawerTabItem) {
        j jVar;
        if (drawerTabItem.supportPopupMenu()) {
            jVar = new j(this);
        } else {
            jVar = null;
        }
        drawerTabItemViewHolder.setOnPopupMenuTriggerListener(jVar);
    }

    private boolean supportTooltip() {
        return !isDrawerOpened();
    }

    private void updateAlbumItemLocationKey(boolean z, boolean z3) {
        String str;
        DrawerTabItem itemData = getItemData(getIndexOfAlbum());
        if (z) {
            str = "location://albums/all";
        } else {
            str = "location://albums";
        }
        itemData.updateLocationKey(str);
        expandListWithoutNotify(itemData, z3);
    }

    private void updateExpandButton(DrawerTabItemViewHolder drawerTabItemViewHolder, DrawerTabItem drawerTabItem) {
        if (drawerTabItem.supportExpand()) {
            drawerTabItemViewHolder.setExpandButtonVisibility(0);
            drawerTabItemViewHolder.initExpandIconRotation();
            return;
        }
        drawerTabItemViewHolder.setExpandButtonVisibility(8);
    }

    private void updateExpandableTab(DrawerTabItemViewHolder drawerTabItemViewHolder, DrawerTabItem drawerTabItem) {
        if (drawerTabItem.isExpandable()) {
            if (drawerTabItem.isExpanded()) {
                collapseList(drawerTabItemViewHolder, drawerTabItem);
            } else {
                lambda$onBindViewHolder$3(drawerTabItemViewHolder, drawerTabItem);
            }
        }
    }

    private void updateUsbVolumes() {
        List<StorageVolumeCompat> usbStorageVolumes = FileUtils.getUsbStorageVolumes(AppResources.getAppContext());
        if (!usbStorageVolumes.isEmpty()) {
            int intValue = this.mUsbVolumeTable.values().stream().max(new D6.a(26)).orElse(0).intValue();
            for (StorageVolumeCompat next : usbStorageVolumes) {
                if (!this.mUsbVolumeTable.containsKey(next.name)) {
                    intValue++;
                    this.mUsbVolumeTable.put(next.name, Integer.valueOf(intValue));
                }
            }
        }
    }

    public void changeFocus(String str, String str2, boolean z) {
        if (LocationKey.isAllAlbumsMatch(str)) {
            this.mFromAllAlbum.set(true);
        }
        int argValue = ArgumentsUtil.getArgValue(str, "id", 0);
        if (LocationKey.isAlbumsMatch(str)) {
            if (this.mFromAllAlbum.getAndSet(false)) {
                refreshData(str);
            }
            updateAlbumItemLocationKey(false, z);
        } else if (LocationKey.isAllAlbumsMatch(str) && !LocationKey.isAllAlbumsMatch(str2)) {
            refreshData(str);
            updateAlbumItemLocationKey(true, z);
        } else if (LocationKey.isAlbumPictures(str)) {
            if (LocationKey.isAllAlbumsMatch(str2)) {
                refreshData(str);
                refreshTabItemEnableCondition();
                updateAlbumItemLocationKey(true, z);
            }
            if (LocationKey.isMxVirtualAlbum(str)) {
                refreshData(str);
            } else {
                if (argValue == 0) {
                    argValue = ArgumentsUtil.getArgValue(str, "mergedAlbumId", 0);
                }
                expandParentGroupList(argValue, z);
            }
        } else if (LocationKey.isFolder(str)) {
            MediaItem currentFolder = getCurrentFolder();
            if (currentFolder != null) {
                argValue = currentFolder.getFolderID();
            }
            expandParentGroupList(argValue, z);
        }
        for (int i2 = 0; i2 < getDataCount(); i2++) {
            DrawerTabItem itemData = getItemData(i2);
            if (itemData != null) {
                MediaItem albumItem = itemData.getAlbumItem();
                if (albumItem != null) {
                    if (argValue != albumItem.getAlbumID() || !LocationKey.isEqualIgnoringArgs(itemData.getLocationKey(), str)) {
                        itemData.setSelectFocused(false);
                    } else {
                        itemData.setSelectFocused(true);
                        this.mFocusedItemId = argValue;
                    }
                } else if (itemData.getLocationKey().equals(str)) {
                    itemData.setSelectFocused(true);
                    this.mFocusedItemId = -1;
                } else {
                    itemData.setSelectFocused(false);
                }
            }
        }
        this.mPreviousFocused = str;
        if (!isDrawerOpened()) {
            updateCollapsedAlbumsTabFocus(true);
        }
        notifyDataSetChangedWrapper();
    }

    public void dataChangedOnUi(boolean z, boolean z3) {
        if (!z || LocationKey.getCurrentLocation().equals("location://albums")) {
            refreshData(this.mPreviousFocused);
            if (z3) {
                refreshAlbumNewLabel((EventMessage) null);
            }
            refreshTabItemEnableCondition();
            notifyDataSetChangedWrapper();
            return;
        }
        notifyItemChanged(getIndexOfAlbum(), "album_expand");
    }

    public void destroy() {
        this.mBlackboard.unsubscribe("data://user/latest_album_id_changed", this.mNewLabelUpdateListener);
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            Blackboard.getApplicationInstance().unsubscribe("event/UsbStorageVolumeChanged", this.mOnUsbStorageChangedListener);
        }
    }

    public MediaData getAllAlbumMediaData() {
        return MediaDataFactory.getInstance(this.mBlackboard).open("location://albums/all", true);
    }

    public List<DrawerTabItem> getDefaultItemData() {
        if (this.mDefaultItemData == null) {
            DrawerDefaultItemBuilder usbTabEnabled = new DrawerDefaultItemBuilder().setSharedAlbumsTabEnabled(this.mSharedAlbumsTabEnabled).setMtpTabEnabled(this.mMtpTabEnabled).setUsbTabEnabled(this.mUsbTabEnabled);
            if (this.mUsbTabEnabled) {
                usbTabEnabled.setUsbVolumeTable(this.mUsbVolumeTable);
            }
            this.mDefaultItemData = usbTabEnabled.build();
        }
        return this.mDefaultItemData;
    }

    public String getFocusedKey() {
        return this.mPreviousFocused;
    }

    public int getItemCount() {
        return getDataCount();
    }

    public DrawerTabItem getItemData(int i2) {
        if (i2 == -1) {
            return null;
        }
        return this.mItemData.get(i2);
    }

    public long getItemId(int i2) {
        DrawerTabItem drawerTabItem = this.mItemData.get(i2);
        if (drawerTabItem != null) {
            return drawerTabItem.getUniqueId();
        }
        return super.getItemId(i2);
    }

    public int getItemPos(String str) {
        for (int i2 = 0; i2 < getDataCount(); i2++) {
            DrawerTabItem itemData = getItemData(i2);
            if (itemData != null && str.equals(itemData.getLocationKey())) {
                return i2;
            }
        }
        return -1;
    }

    public int getItemViewType(int i2) {
        DrawerTabItem itemData = getItemData(i2);
        if (itemData == null) {
            return 0;
        }
        if (SUPPORT_VIDEO_STUDIO && itemData.isVideoStudio()) {
            return 1;
        }
        if (LocationKey.isChildOfAlbums(itemData.getLocationKey())) {
            return 2;
        }
        return 0;
    }

    public MediaData getMediaData() {
        return MediaDataFactory.getInstance(this.mBlackboard).open("location://albums", true);
    }

    public TabItemEnableCondition getTabItemEnableCondition() {
        return this.mTabItemEnableCondition;
    }

    public void initValues(Context context) {
        int i2;
        boolean z;
        boolean z3;
        if (DrawerUtil.isDrawerDefaultOpen(context)) {
            i2 = 3;
        } else {
            i2 = 4;
        }
        this.mDrawerState = i2;
        boolean z7 = false;
        if (PreferenceFeatures.OneUi8x.REMOVE_SHARED_DRAWER_TAB_MENU || !MdeSharingService.getInstance().isServiceSupported()) {
            z = false;
        } else {
            z = true;
        }
        this.mSharedAlbumsTabEnabled = z;
        if (!PickerUtil.isNormalLaunchMode(this.mBlackboard) || !MtpClient.getInstance(context).isAvailable()) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mMtpTabEnabled = z3;
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE) && UsbStorageState.getInstance().isMounted()) {
            z7 = true;
        }
        this.mUsbTabEnabled = z7;
        if (z7) {
            updateUsbVolumes();
            UsbBadgeManager usbBadgeManager = new UsbBadgeManager();
            this.mUsbBadgeManager = usbBadgeManager;
            usbBadgeManager.loadTable();
        }
    }

    public void notifyDataSetChangedWrapper() {
        notifyDataSetChanged();
    }

    public void refreshAlbumNewLabel(EventMessage eventMessage) {
        AlbumNewLabelUpdater.getInstance().refresh(eventMessage);
    }

    public void setDrawerItemClickListener(OnDrawerItemClickListener onDrawerItemClickListener) {
        this.mListener = onDrawerItemClickListener;
    }

    public void setState(int i2) {
        this.mDrawerState = i2;
    }

    public void setSuggestionBadge(DrawerTabItemViewHolder drawerTabItemViewHolder, DrawerTabItem drawerTabItem, boolean z) {
        if (drawerTabItem == null) {
            drawerTabItem = getItemData(getItemPos("location://suggestions"));
        }
        DrawerTabItem drawerTabItem2 = drawerTabItem;
        if (drawerTabItem2 != null && drawerTabItem2.isSuggestions()) {
            ThreadUtil.postOnBgThreadDelayed(new F8.a((Object) this, (Object) drawerTabItem2, z, (Object) drawerTabItemViewHolder, 9), 300);
        }
    }

    public void setUsbStorageBadge() {
        if (this.mUsbBadgeManager != null) {
            for (DrawerTabItem next : this.mItemData) {
                if (next != null && next.isUsb()) {
                    next.setShowNewBadge(!this.mUsbBadgeManager.isUsed(ArgumentsUtil.getArgValue(next.getLocationKey(), "name")));
                }
            }
        }
    }

    public void updateBadgeOnTab(String str, boolean z) {
        int i2 = 0;
        while (i2 < getDataCount()) {
            DrawerTabItem itemData = getItemData(i2);
            if (itemData == null || !TextUtils.equals(itemData.getLocationKey(), str)) {
                i2++;
            } else if (itemData.showNewBadge() != z) {
                itemData.setShowNewBadge(z);
                notifyItemChanged(i2, "new_badge_update");
                return;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = getIndexOfAlbum();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateCollapsedAlbumsTabFocus(boolean r3) {
        /*
            r2 = this;
            java.lang.String r0 = r2.mPreviousFocused
            boolean r0 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isChildOfAlbums(r0)
            if (r0 == 0) goto L_0x001a
            int r0 = r2.getIndexOfAlbum()
            com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem r1 = r2.getItemData(r0)
            if (r1 == 0) goto L_0x001a
            r1.setSelectFocused(r3)
            java.lang.String r3 = "collapsed_albums_tab_focus_update"
            r2.notifyItemChanged(r0, r3)
        L_0x001a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter.updateCollapsedAlbumsTabFocus(boolean):void");
    }

    public void updateExpandedAlbumsTab(boolean z, String str) {
        DrawerTabItem itemData;
        if (z) {
            String str2 = (String) this.mBlackboard.read("location://variable/currentv1");
            if (LocationKey.isChildOfAlbums(str2)) {
                changeFocus(str2, str, false);
                return;
            }
            return;
        }
        int indexOfAlbum = getIndexOfAlbum();
        DrawerTabItem itemData2 = getItemData(indexOfAlbum);
        if (itemData2 != null && itemData2.isExpanded()) {
            while (true) {
                int i2 = indexOfAlbum + 1;
                if (getDataCount() <= i2 || (itemData = getItemData(i2)) == null || itemData.getDepth() <= itemData2.getDepth()) {
                    itemData2.setExpanded(false);
                    notifyDataSetChanged();
                } else {
                    this.mItemData.remove(i2);
                }
            }
            itemData2.setExpanded(false);
            notifyDataSetChanged();
        }
    }

    public void updateItemsEnableStatus() {
        refreshTabItemEnableCondition();
        notifyDataSetChangedWrapper();
    }

    public boolean updateMtpTab(boolean z) {
        boolean z3;
        if (this.mMtpTabEnabled == z) {
            return false;
        }
        this.mMtpTabEnabled = z;
        if (z) {
            addTab("location://mtp");
        } else {
            removeTab("location://mtp");
        }
        DrawerTabItem itemData = getItemData(getItemPos("location:///VideoStudio"));
        if (itemData != null) {
            if (z || this.mUsbTabEnabled) {
                z3 = true;
            } else {
                z3 = false;
            }
            itemData.setEnableDivider(z3);
            notifyItemRangeChanged(0, getItemCount(), "updateLayout");
        }
        return true;
    }

    public boolean updateSharedAlbumsTab(boolean z) {
        if (this.mSharedAlbumsTabEnabled == z) {
            return false;
        }
        this.mSharedAlbumsTabEnabled = z;
        if (z) {
            addTab("location://sharing/albums");
            return true;
        }
        removeTab("location://sharing/albums");
        return true;
    }

    public boolean updateUsbTab(boolean z) {
        boolean z3;
        C0464a aVar = new C0464a(19);
        if (this.mUsbTabEnabled == z && this.mItemData.stream().filter(aVar).count() == ((long) FileUtils.getUsbStorageVolumes(AppResources.getAppContext()).size())) {
            return false;
        }
        this.mUsbTabEnabled = z;
        if (z) {
            removeAllUsbTab(aVar);
            int intValue = this.mUsbVolumeTable.values().stream().max(new D6.a(26)).orElse(0).intValue();
            PriorityQueue priorityQueue = new PriorityQueue(Comparator.comparing(new e(15)));
            for (StorageVolumeCompat next : FileUtils.getUsbStorageVolumes(AppResources.getAppContext())) {
                intValue++;
                DrawerTabItem drawerTabItem = new DrawerTabItem(new UriBuilder("location://albums/otg/fileList").appendArg("id", ((Integer) Optional.ofNullable(this.mUsbVolumeTable.get(next.name)).orElse(Integer.valueOf(intValue))).intValue()).appendArg("name", next.name).appendArg("directory", next.directory + "/DCIM/Camera/").build());
                UsbBadgeManager usbBadgeManager = this.mUsbBadgeManager;
                if (usbBadgeManager != null && !usbBadgeManager.isUsed(next.name)) {
                    drawerTabItem.setShowNewBadge(true);
                }
                priorityQueue.add(drawerTabItem);
            }
            priorityQueue.forEach(new g(1, this));
            notifyItemRangeChanged(0, this.mItemData.size());
        } else {
            removeAllUsbTab(aVar);
        }
        DrawerTabItem itemData = getItemData(getItemPos("location:///VideoStudio"));
        if (itemData != null) {
            if (z || this.mMtpTabEnabled) {
                z3 = true;
            } else {
                z3 = false;
            }
            itemData.setEnableDivider(z3);
            notifyItemRangeChanged(0, getItemCount(), "updateLayout");
        }
        return true;
    }

    public DrawerTabItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        int layoutId = getLayoutId(i2);
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (!(layoutCache == null || i2 == 2)) {
            View cachedDrawerItemView = layoutCache.getCachedDrawerItemView(layoutId);
            if (cachedDrawerItemView == null) {
                this.mCacheLoader = null;
            } else {
                cachedDrawerItemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            }
            view = cachedDrawerItemView;
        }
        if (view == null) {
            view = C0086a.d(viewGroup, layoutId, viewGroup, false);
        }
        return new DrawerTabItemViewHolder(view);
    }

    public void onViewRecycled(DrawerTabItemViewHolder drawerTabItemViewHolder) {
        drawerTabItemViewHolder.recycle();
    }

    public void onBindViewHolder(DrawerTabItemViewHolder drawerTabItemViewHolder, int i2) {
        DrawerTabItem itemData = getItemData(i2);
        if (itemData != null) {
            drawerTabItemViewHolder.bind(itemData);
            updateExpandButton(drawerTabItemViewHolder, itemData);
            bindNewAlbumLabel(drawerTabItemViewHolder, itemData);
            setOnItemClickListener(drawerTabItemViewHolder, itemData);
            setOnPopupMenuTriggerListener(drawerTabItemViewHolder, itemData);
            setSuggestionBadge(drawerTabItemViewHolder, itemData, false);
            setUsbStorageBadge();
            drawerTabItemViewHolder.updateCollapsedBadgeVisibility(SheetBehaviorCompat.isCollapsed(this.mDrawerState));
            drawerTabItemViewHolder.setOnLongClickListener(new j(this));
        }
    }

    public void onBindViewHolder(DrawerTabItemViewHolder drawerTabItemViewHolder, int i2, List<Object> list) {
        DrawerTabItem itemData = getItemData(i2);
        if (list.contains("album_expand")) {
            ThreadUtil.postOnUiThreadDelayed(new C0235b(this, drawerTabItemViewHolder, itemData, 8), 100);
        } else if (list.contains("new_badge_update")) {
            drawerTabItemViewHolder.updateEndDecorViews();
            drawerTabItemViewHolder.updateCollapsedBadgeVisibility(SheetBehaviorCompat.isCollapsed(this.mDrawerState));
        } else {
            if (list.contains("new_label")) {
                if (itemData != null && itemData.needNewAlbumLabel(getLatestAlbumId())) {
                    drawerTabItemViewHolder.updateEndDecorViews();
                    return;
                }
            } else if (list.contains("collapsed_albums_tab_focus_update")) {
                if (isAlbumsTab(itemData)) {
                    drawerTabItemViewHolder.updateFocus();
                    return;
                }
            } else if (list.contains("updateLayout")) {
                if ("location:///VideoStudio".equals(drawerTabItemViewHolder.getLocationKey())) {
                    drawerTabItemViewHolder.bind(itemData);
                    return;
                }
                return;
            }
            super.onBindViewHolder(drawerTabItemViewHolder, i2, list);
        }
    }
}
