package com.samsung.android.gallery.app.ui.list.abstraction;

import A.a;
import A4.C0367b;
import A4.C0372g;
import A4.C0384t;
import A4.C0385u;
import A4.C0387w;
import A4.D;
import A4.G;
import A4.M;
import A4.N;
import A4.O;
import Fa.C0571z;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.drm.DrmManager;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.ListAutoDragHandler;
import com.samsung.android.gallery.widget.listview.scroller.FastScroller;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseListViewAdapter<V extends IBaseListView> extends AbsListViewAdapter<V> implements FastScroller.LayoutInformer {
    static final boolean DEBUG_LOGGABLE = Log.isLoggable("BaseListViewAdapter", 3);
    protected final ListViewAdapterHandler<?> mBgHandler = createListViewAdapterHandler(ThreadUtil.createBackgroundThreadLooper("LIST"));
    protected int mDataCount;
    protected final ListViewAdapterHandler<?> mFgHandler = createListViewAdapterHandler(ThreadUtil.getMainThreadLooper());
    private int mGridViewSize;
    protected String mLocationKey;
    protected final MediaData mMediaData;
    private String mPrevScreenId;
    private ThumbKind mThumbnailKind;
    private final ThumbnailResizer mThumbnailResizer = createThumbnailResizer();

    public BaseListViewAdapter(V v, String str) {
        super(v);
        MediaData mediaData = getMediaData(v, str);
        this.mMediaData = mediaData;
        this.mDataCount = mediaData.getCount();
        this.mLocationKey = str;
        setThumbnailKind();
    }

    private boolean bindImageOfHighQuality(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.XLARGE_NC_KIND;
        Bitmap memCache = instance.getMemCache(mediaItem, thumbKind);
        if (memCache == null) {
            return false;
        }
        listViewHolder.setThumbKind(thumbKind);
        listViewHolder.bindImage(memCache);
        return true;
    }

    private void bindItemToView(ListViewHolder listViewHolder, int i2, int i7, MediaItem mediaItem) {
        listViewHolder.bind(mediaItem);
        if (listViewHolder.getImage() != null) {
            bindThumbnail(listViewHolder, i2, i7, mediaItem);
        }
    }

    private void bindViewHolderInternal(ListViewHolder listViewHolder, int i2, int i7) {
        AdvancedMouseFocusManager advancedMouseFocusManager;
        if (!bindViewHolderOnScrollIdle(listViewHolder, i2, i7)) {
            bindViewHolderOnScrolling(listViewHolder, i2, i7);
        }
        if (!useAdvancedMouseDragAndDrop()) {
            return;
        }
        if ((!isSelectionMode() || PreferenceFeatures.OneUi7x.SUPPORT_MOUSE_USABILITY_V2) && (advancedMouseFocusManager = getAdvancedMouseFocusManager()) != null && advancedMouseFocusManager.getTotalCount() > 0) {
            listViewHolder.setFocusedFilterOnAdvancedMouseEvent(advancedMouseFocusManager.containsViewPosition(i2));
        }
    }

    private void bindViewHolderOnScrolling(ListViewHolder listViewHolder, int i2, int i7) {
        MediaItem mediaItemFromCache = getMediaItemFromCache(i2, i7);
        if (mediaItemFromCache != null) {
            if (DEBUG_LOGGABLE) {
                a.k(i2, "cache load mediaItem : ", this.TAG);
            }
            bindItemToView(listViewHolder, i2, i7, mediaItemFromCache);
        } else if (isQuickScrollRunning()) {
            delayLoadItemForQuickScroll(listViewHolder, i2);
        } else {
            delayLoadItemForScroll(listViewHolder, i2, i7);
        }
    }

    private void delayLoadItemForQuickScroll(ListViewHolder listViewHolder, int i2) {
        this.mBgHandler.sendMessageDelayed(0, i2, getGridSize(), listViewHolder);
    }

    private String[] getDataIdListFromCursor(Cursor cursor, TimeTickLog timeTickLog) {
        if (cursor == null || !cursor.moveToFirst()) {
            com.samsung.android.gallery.support.utils.Log.e(this.TAG, "fail to get file ids from cursor");
            return null;
        }
        String[] split = cursor.getString(0).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        String str = this.TAG;
        com.samsung.android.gallery.support.utils.Log.d(str, "cursor count size  = " + split.length + ", id(First|Last) = " + split[0] + " | " + split[split.length - 1] + ArcCommonLog.TAG_COMMA + timeTickLog.getElapsedTime() + " ms");
        return split;
    }

    private int getRootTop(View view) {
        int i2 = 0;
        while (view.getId() != R.id.fragment_container) {
            i2 += view.getTop();
            ViewParent parent = view.getParent();
            if (!(parent instanceof View)) {
                break;
            }
            view = (View) parent;
        }
        return i2;
    }

    private boolean isInvalidDrm(MediaItem mediaItem) {
        if (!mediaItem.isDrm() || mediaItem.isCloudOnly() || mediaItem.isSharing() || FileUtils.isEmptyDummyImage(mediaItem.getPath()) || DrmManager.getInstance().isValidRights(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    public static boolean isViewSame(ListViewHolder listViewHolder, int i2) {
        try {
            int viewPosition = listViewHolder.getViewPosition();
            if (viewPosition == -1) {
                viewPosition = listViewHolder.getViewPosition();
            }
            if (viewPosition == i2) {
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$0(ListViewHolder listViewHolder, int i2, int i7) {
        if (isViewSame(listViewHolder, i2)) {
            this.mBgHandler.sendMessage(2, i2, i7, listViewHolder);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getSelectedItems$3(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer[] lambda$prepareExtendedShare$4(int i2) {
        return new Integer[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$requestThumbnail$1(ListViewHolder listViewHolder, ThumbnailRequestHolder thumbnailRequestHolder) {
        return !isViewSame(listViewHolder, thumbnailRequestHolder.getPosition());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestXLargeForAllVisible$2(ListViewHolder listViewHolder) {
        requestXLargeThumbnail(listViewHolder.getViewPosition());
    }

    private boolean tryBindImageDirectly(ListViewHolder listViewHolder, MediaItem mediaItem, boolean z) {
        if (listViewHolder.getThumbKind() == null || listViewHolder.getThumbKind().size() < getThumbnailKind().size()) {
            listViewHolder.setThumbKind(getThumbnailKind());
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Bitmap memCache = instance.getMemCache(mediaItem, listViewHolder.getThumbKind());
        if (memCache == null) {
            return false;
        }
        if (instance.isReplacedThumbnail(memCache)) {
            mediaItem.setBroken(true);
        } else if (getThumbnailKind() != ThumbKind.SMALL_CROP_KIND && Math.max(memCache.getWidth(), memCache.getHeight()) <= 128) {
            String str = this.TAG;
            com.samsung.android.gallery.support.utils.Log.w(str, "tryBindImageDirectly " + memCache + ArcCommonLog.TAG_COMMA + mediaItem);
        }
        listViewHolder.bindImage(memCache);
        if (z) {
            this.mThumbnailResizer.requestXLargeThumbnail(listViewHolder);
        }
        return true;
    }

    private boolean useAdvancedMouseDragAndDrop() {
        return this.mView.useAdvancedMouseDragAndDrop();
    }

    public void acquireReadLock(String str) {
        this.mMediaData.acquireReadLock(str);
    }

    public boolean bindImageOnScrollIdle(ListViewHolder listViewHolder, MediaItem mediaItem) {
        boolean z = false;
        if (listViewHolder.getImage() == null) {
            return false;
        }
        if (preBindImageOnScrollIdle(listViewHolder, mediaItem)) {
            return true;
        }
        if (!supportHighQuality() || !(z = this.mThumbnailResizer.needXLargeThumbnail(listViewHolder, -1)) || !bindImageOfHighQuality(listViewHolder, mediaItem)) {
            return tryBindImageDirectly(listViewHolder, mediaItem, z);
        }
        return true;
    }

    public void bindThumbnail(ListViewHolder listViewHolder, int i2, int i7, MediaItem mediaItem) {
        listViewHolder.setImageUid(mediaItem.getPath());
        ImageView image = listViewHolder.getImage();
        if (image.getWidth() == 0) {
            image.post(new M((Object) this, (Object) listViewHolder, i2, i7, 0));
            return;
        }
        ListViewHolder listViewHolder2 = listViewHolder;
        int i8 = i2;
        int i10 = i7;
        if (!isYearData(listViewHolder2.getViewType()) && !isMonthForViewingData(listViewHolder2.getViewType())) {
            Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, listViewHolder2.getThumbKind());
            if (memCache == null || ThumbnailLoader.getInstance().isReplacedThumbnail(memCache)) {
                ThumbnailLoader instance = ThumbnailLoader.getInstance();
                Bitmap memCache2 = instance.getMemCache("ppp/" + mediaItem.getFileId(), listViewHolder2.getThumbKind(), mediaItem.getCropRectRatio());
                if (memCache2 == null && mediaItem.hasFilterAndTone()) {
                    ThumbnailLoader instance2 = ThumbnailLoader.getInstance();
                    memCache2 = instance2.getMemCache("effect/" + mediaItem.getFileId(), listViewHolder2.getThumbKind(), mediaItem.getCropRectRatio());
                }
                if (memCache2 != null) {
                    listViewHolder2.bindImage(memCache2);
                    if (DEBUG_LOGGABLE) {
                        com.samsung.android.gallery.support.utils.Log.v(this.TAG, "bindThumbnail with ppp cache");
                    }
                }
            } else {
                listViewHolder2.bindImage(memCache);
                if (DEBUG_LOGGABLE) {
                    com.samsung.android.gallery.support.utils.Log.v(this.TAG, "bindThumbnail with cache");
                    return;
                }
                return;
            }
        }
        this.mBgHandler.sendMessage(2, i8, i10, listViewHolder2);
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        MediaItem loadMediaItemSync;
        if (!this.mGalleryListView.isScrollIdle() || ViewHolderValue.isDivider(listViewHolder.getViewType()) || (loadMediaItemSync = loadMediaItemSync(getMediaItemPosition(i2, i7))) == null) {
            return false;
        }
        listViewHolder.bind(loadMediaItemSync);
        listViewHolder.setImageUid(loadMediaItemSync.getPath());
        return bindImageOnScrollIdle(listViewHolder, loadMediaItemSync);
    }

    public void checkVisibleViewHoldersForXLarge() {
        this.mThumbnailResizer.checkVisibleViewHoldersForXLarge();
    }

    public ListViewAdapterHandler<?> createListViewAdapterHandler(Looper looper) {
        return new ListViewAdapterHandler<>(looper, this);
    }

    public ThumbnailResizer createThumbnailResizer() {
        return new ThumbnailResizer(this);
    }

    public void delayLoadItemForScroll(ListViewHolder listViewHolder, int i2, int i7) {
        this.mBgHandler.sendMessage(0, i2, i7, listViewHolder);
    }

    public void destroy() {
        super.destroy();
        ListAutoDragHandler.stopTimer();
        setOnAutoDragHandler((ListAutoDragHandler) null);
        removePendingJobs();
        this.mBgHandler.getLooper().quitSafely();
    }

    public Bitmap getBrokenMediaItem(MediaItem mediaItem) {
        if (!mediaItem.isBroken() && !isInvalidDrm(mediaItem) && !TrashData.of(mediaItem).drm) {
            return null;
        }
        mediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    public int getDataCount() {
        return this.mMediaData.getCount();
    }

    public List<Long> getDataIds() {
        return this.mMediaData.getFileIds();
    }

    public ArrayList<Pair<String, Integer>> getDividerList() {
        return null;
    }

    @Deprecated
    public Cursor getFilesIdsCursor(long j2, boolean z) {
        return null;
    }

    public int getFirstVisibleIndex() {
        return this.mGalleryListView.findFirstVisibleItemPositionCompat();
    }

    public int getFooterViewHeight() {
        return 0;
    }

    public int getFullLoadedCount() {
        return getItemCount();
    }

    public int getGridItemSpacing() {
        return 0;
    }

    public int getGridSize() {
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView != null) {
            return galleryListView.getColumnCount();
        }
        return -1;
    }

    public int getHeaderViewHeight() {
        return 0;
    }

    public int getItemCount() {
        return this.mMediaData.getCount();
    }

    public int getItemHeight(int i2) {
        ListViewHolder viewHolderForAdapterPosition = getViewHolderForAdapterPosition(i2);
        if (viewHolderForAdapterPosition != null) {
            return viewHolderForAdapterPosition.itemView.getHeight();
        }
        return 0;
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public int getMaxScroll() {
        com.samsung.android.gallery.support.utils.Log.e(this.TAG, "fail to get max Scroll");
        new InternalException("not supported. override to use").post();
        return 0;
    }

    public MediaData getMediaData(V v, String str) {
        return v.getMediaData(str);
    }

    public MediaItem getMediaItemFromCache(int i2) {
        return this.mMediaData.readCache(getMediaItemPosition(i2));
    }

    public int getMediaItemPosition(int i2) {
        return this.mMediaData.getPosition(i2);
    }

    public MediaItem getMediaItemSync(int i2) {
        if (isData(i2)) {
            return loadMediaItemSync(getMediaItemPosition(i2));
        }
        return null;
    }

    public int getNextRowIndex(int i2, int i7) {
        int gridSize = getGridSize();
        for (int i8 = 0; i8 < gridSize; i8++) {
            int i10 = i2 + i8;
            ListViewHolder viewHolderForAdapterPosition = getViewHolderForAdapterPosition(i10);
            if (viewHolderForAdapterPosition != null && viewHolderForAdapterPosition.itemView.getX() == 0.0f) {
                return i10;
            }
        }
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("fail to find next row : ");
        int i11 = i2 + gridSize;
        sb2.append(i11);
        com.samsung.android.gallery.support.utils.Log.e(str, sb2.toString());
        return i11;
    }

    public int getPredictGridViewSize() {
        return (this.mView.getContentView().getWidth() / getGridSize()) - getGridItemSpacing();
    }

    public int getPrevRowIndex(int i2) {
        int gridSize = getGridSize();
        for (int i7 = 0; i7 < gridSize; i7++) {
            int i8 = i2 - i7;
            if (i8 < 0) {
                return 0;
            }
            ListViewHolder viewHolderForAdapterPosition = getViewHolderForAdapterPosition(i8);
            if (viewHolderForAdapterPosition != null && viewHolderForAdapterPosition.itemView.getX() == 0.0f) {
                return i8;
            }
        }
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("fail to find prev row : ");
        int i10 = i2 - gridSize;
        sb2.append(i10);
        com.samsung.android.gallery.support.utils.Log.e(str, sb2.toString());
        return i10;
    }

    public String getPrevScreenId() {
        return this.mPrevScreenId;
    }

    public int getRowHeight(int i2) {
        return getItemHeight(i2);
    }

    public int getScrollStart() {
        return Math.max(0, this.mView.getToolbar().getBottom() - getRootTop(this.mGalleryListView));
    }

    public MediaItem[] getSelectedItems() {
        boolean z;
        ArrayList<Integer> arrayList;
        if (this.mView.isDestroyed() || !this.mView.getPresenter().isOnAdvancedMouseFocused()) {
            z = false;
        } else {
            z = true;
        }
        if (useClipBoardForNormalSelectionMode() && !z) {
            return getSelectedItemsFromBuffer();
        }
        GalleryListView listView = this.mView.getListView();
        if (listView != null) {
            if (!this.mView.isDestroyed() && this.mView.getPresenter().isPopupMenuItemFocused() && !listView.isSelectionMode()) {
                MediaItem mediaItem = (MediaItem) this.mView.getBlackboard().read("data://focused_item", null);
                if (mediaItem != null) {
                    return (MediaItem[]) Collections.singletonList(mediaItem).toArray(new MediaItem[1]);
                }
                return new MediaItem[0];
            } else if (listView.isSelectionMode() || z) {
                if (z) {
                    arrayList = (ArrayList) Optional.ofNullable(getAdvancedMouseFocusManager()).map(new C0384t(13)).orElse((Object) null);
                } else {
                    arrayList = listView.getSelectedItemList();
                }
                if (arrayList != null && !arrayList.isEmpty()) {
                    String str = this.mView.hashTag() + ".getSelectedItems";
                    try {
                        this.mMediaData.acquireReadLock(str);
                        return (MediaItem[]) arrayList.stream().map(new D(0, this)).filter(new C0571z(4)).toArray(new C0387w(1));
                    } finally {
                        this.mMediaData.releaseReadLock(str);
                    }
                }
            }
        }
        return new MediaItem[0];
    }

    public int getSpanSize(int i2) {
        return 0;
    }

    public int getSplitSpacing() {
        return 0;
    }

    public ThumbKind getThumbnailKind() {
        return this.mThumbnailKind;
    }

    @Deprecated
    public String[] getTotalDataIdListFromCursor(TimeTickLog timeTickLog) {
        boolean z;
        Cursor filesIdsCursor;
        int fullLoadedCount = getFullLoadedCount();
        timeTickLog.tick();
        ArrayList arrayList = new ArrayList();
        long j2 = -1;
        int i2 = fullLoadedCount;
        boolean z3 = false;
        while (true) {
            if (fullLoadedCount > 200000) {
                z = true;
            } else {
                z = false;
            }
            try {
                filesIdsCursor = getFilesIdsCursor(j2, z);
                String[] dataIdListFromCursor = getDataIdListFromCursor(filesIdsCursor, timeTickLog);
                Collections.addAll(arrayList, dataIdListFromCursor);
                if (filesIdsCursor != null) {
                    filesIdsCursor.close();
                }
                if (z3 || fullLoadedCount <= 200000) {
                    break;
                }
                j2 = Long.parseLong(dataIdListFromCursor[dataIdListFromCursor.length - 1]);
                i2 -= 200000;
                if (i2 <= 0) {
                    z3 = true;
                }
            } catch (Exception e) {
                com.samsung.android.gallery.support.utils.Log.e(this.TAG, e.getMessage());
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        timeTickLog.tick();
        a.A(new Object[]{Integer.valueOf(arrayList.size()), timeTickLog}, new StringBuilder("getTotalDataIdList"), this.TAG);
        return (String[]) arrayList.toArray(new String[0]);
        throw th;
    }

    public ListViewHolder getViewHolderForAdapterPosition(int i2) {
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView != null) {
            return (ListViewHolder) galleryListView.findViewHolderForAdapterPosition(i2);
        }
        return null;
    }

    public boolean isDataAvailable() {
        return this.mMediaData.isDataAvailable();
    }

    public boolean isDisableShareSheet(MediaItem[] mediaItemArr) {
        int length = mediaItemArr.length;
        int i2 = -1;
        int i7 = 0;
        while (i7 < length) {
            MediaItem mediaItem = mediaItemArr[i7];
            if (i2 == -1 || i2 == mediaItem.getBucketID()) {
                i2 = mediaItem.getBucketID();
                i7++;
            } else {
                com.samsung.android.gallery.support.utils.Log.d(this.TAG, "ShareSheet has selected files in more then two albums");
                return true;
            }
        }
        return false;
    }

    public boolean isHideScroller() {
        return this.mView.isHideScroller();
    }

    public /* bridge */ /* synthetic */ boolean isPreviewAvailable() {
        return super.isPreviewAvailable();
    }

    public boolean isQuickScrollRunning() {
        return this.mGalleryListView.isQuickScrollRunning();
    }

    public void loadMediaItemAsync(int i2, MediaItemLoader.OnMediaItemLoadingListener onMediaItemLoadingListener, MediaItemLoader.LoadingStatusInformer loadingStatusInformer) {
        MediaData mediaData = this.mMediaData;
        Objects.requireNonNull(onMediaItemLoadingListener);
        O o2 = new O(0, onMediaItemLoadingListener);
        Objects.requireNonNull(loadingStatusInformer);
        mediaData.readAsync(i2, o2, new C0385u(1, loadingStatusInformer));
    }

    public MediaItem loadMediaItemForShare(int i2) {
        return getMediaItemSync(getViewPosition(i2));
    }

    public final MediaItem loadMediaItemSync(int i2) {
        try {
            return this.mMediaData.read(i2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean needChangeThumbnailKind(int i2) {
        if (i2 == 1 || getGridSize() == 1) {
            return false;
        }
        return true;
    }

    public void onBindImageInternal(int i2, ListViewHolder listViewHolder) {
        V v = this.mView;
        if (v != null) {
            v.publishThumbnailLoadDone();
        }
    }

    public void onDataChanged() {
        this.mDataCount = this.mMediaData.getCount();
        super.onDataChanged();
    }

    public void onDataRangeInserted(int i2, int i7) {
        this.mDataCount = this.mMediaData.getCount();
    }

    public void onDividerSelected() {
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_TOUCH_TIME_GROUP_SELECT);
    }

    public void onEmptySpaceSecondaryClick(PointF pointF) {
        this.mView.onEmptySpaceSecondaryClick(pointF);
    }

    public void onExitSelectMode(boolean z) {
        this.mBlackBoard.post("lifecycle://ON_ExitSelectionMode", Boolean.valueOf(z));
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && isPreviewAvailable()) {
            checkPreviewCandidate();
        }
    }

    public final void onLayoutChanged(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (this.mGalleryListView != null) {
            int i15 = i13 - i11;
            int i16 = i8 - i2;
            int splitSpacing = ((i16 - getSplitSpacing()) / getGridSize()) - getGridItemSpacing();
            if (i16 != i15 || splitSpacing != this.mGridViewSize) {
                this.mGridViewSize = splitSpacing;
                setThumbnailKind();
                ThumbKind thumbnailKind = getThumbnailKind();
                ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
                if (thumbnailKind == thumbKind && this.mGalleryListView.getColumnCount() == this.mGalleryListView.getMaxColumnCount()) {
                    ThumbnailLoader.getInstance().increaseMemCache(thumbKind);
                } else if (ThumbKind.isSmallKind(getThumbnailKind()) && isMonthForViewing()) {
                    ThumbnailLoader.getInstance().increaseMemCache(ThumbKind.SMALL_CROP_KIND);
                } else if (ThumbKind.isXSmallKind(getThumbnailKind()) && isMonthForViewing()) {
                    ThumbnailLoader.getInstance().increaseMemCache(ThumbKind.XSMALL_KIND);
                }
            }
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.mView.onListItemClick(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (this.mView.onListItemLongClick(listViewHolder, i2, mediaItem) || super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7)) {
            return true;
        }
        return false;
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
        this.mView.onListItemSecondaryClick(listViewHolder, i2, mediaItem, pointF);
    }

    public void onPostLongPressSelectMode() {
        this.mView.postAnalyticsLog(this.mPrevScreenId, AnalyticsEventId.EVENT_LONG_PRESS_THUMBNAIL);
    }

    public void onPrepareLongPressSelectMode() {
        this.mPrevScreenId = this.mView.getScreenId();
    }

    public void onQuickScrollEnd() {
        checkVisibleViewHoldersForXLarge();
    }

    public void onScrollStateIdle() {
        checkVisibleViewHoldersForXLarge();
        super.onScrollStateIdle();
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        super.onSelected(i2, z, z3);
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && isPreviewAvailable()) {
            checkPreviewCandidate();
        }
    }

    public void onStartSelectMode() {
        if (this.mSelectionManager.isMultipleSelectMode()) {
            this.mBlackBoard.post("lifecycle://ON_EnterSelectionMode", getLocationKey());
        }
    }

    public boolean preBindImageOnScrollIdle(ListViewHolder listViewHolder, MediaItem mediaItem) {
        Bitmap brokenMediaItem = getBrokenMediaItem(mediaItem);
        if (brokenMediaItem == null) {
            return false;
        }
        listViewHolder.bindImage(brokenMediaItem);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.util.ArrayList<java.lang.Integer>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void prepareExtendedShare() {
        /*
            r4 = this;
            V r0 = r4.mView
            com.samsung.android.gallery.widget.listview.GalleryListView r0 = r0.getListView()
            boolean r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET
            if (r1 == 0) goto L_0x0070
            if (r0 == 0) goto L_0x0070
            V r1 = r4.mView
            boolean r1 = r1.isDestroyed()
            r2 = 0
            if (r1 != 0) goto L_0x003c
            V r1 = r4.mView
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter r1 = r1.getPresenter()
            boolean r1 = r1.isOnAdvancedMouseFocused()
            if (r1 == 0) goto L_0x003c
            com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager r0 = r4.getAdvancedMouseFocusManager()
            java.util.Optional r0 = java.util.Optional.ofNullable(r0)
            A4.t r1 = new A4.t
            r3 = 13
            r1.<init>(r3)
            java.util.Optional r0 = r0.map(r1)
            java.lang.Object r0 = r0.orElse(r2)
            r2 = r0
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            goto L_0x005b
        L_0x003c:
            boolean r1 = r4.useClipBoardForNormalSelectionMode()
            if (r1 == 0) goto L_0x0051
            com.samsung.android.gallery.module.data.MediaItem[] r1 = r4.getSelectedItemsFromBuffer()
            boolean r1 = r4.isDisableShareSheet(r1)
            if (r1 != 0) goto L_0x005b
            java.util.ArrayList r2 = r0.getSelectedItemList()
            goto L_0x005b
        L_0x0051:
            boolean r1 = r0.isSelectionMode()
            if (r1 == 0) goto L_0x005b
            java.util.ArrayList r2 = r0.getSelectedItemList()
        L_0x005b:
            if (r2 == 0) goto L_0x0067
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x0067
            r4.prepareExtendedShare(r2)
            return
        L_0x0067:
            V r4 = r4.mView
            androidx.fragment.app.FragmentActivity r4 = r4.getActivity()
            com.samsung.android.gallery.app.provider.ShareProvider.clearExtendedShareList(r4)
        L_0x0070:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter.prepareExtendedShare():void");
    }

    public void preparePinchAnimation(int i2) {
        if (this.mGalleryListView != null && needChangeThumbnailKind(i2)) {
            this.mGridViewSize = ((this.mGalleryListView.getWidth() - getSplitSpacing()) / i2) - getGridItemSpacing();
            setThumbnailKind();
        }
    }

    public void releaseReadLock(String str) {
        this.mMediaData.releaseReadLock(str);
    }

    public void removePendingJobs() {
        this.mFgHandler.removeCallbacksAndMessages((Object) null);
        this.mBgHandler.removeCallbacksAndMessages((Object) null);
    }

    public void requestThumbnail(ListViewHolder listViewHolder, int i2) {
        ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(listViewHolder);
        MediaItem mediaItem = thumbnailRequestHolder.getMediaItem();
        if (mediaItem == null || thumbnailRequestHolder.getPosition() == -1) {
            String str = this.TAG;
            com.samsung.android.gallery.support.utils.Log.e(str, "Fatal : no media item or invalid view position to request Thumb : " + listViewHolder);
            return;
        }
        listViewHolder.setThumbKind(getThumbnailKind());
        Bitmap brokenMediaItem = getBrokenMediaItem(mediaItem);
        if (brokenMediaItem != null) {
            setBitmapWithBind(brokenMediaItem, thumbnailRequestHolder, getThumbnailKind());
        } else {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, listViewHolder.getThumbKind(), thumbnailRequestHolder, new G(this), new N(listViewHolder, thumbnailRequestHolder, 0));
        }
    }

    public void requestXLargeForAllVisible() {
        getAllVisibleViewHolders().iterator().forEachRemaining(new C0367b(3, this));
    }

    public void requestXLargeThumbnail(int i2) {
        this.mThumbnailResizer.requestXLargeThumbnail(i2);
    }

    public void resetThumbKind() {
        this.mGridViewSize = 0;
        setThumbnailKind();
    }

    public void restoreClipboardWithCursorDataIdList(String[] strArr, Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet<Integer> linkedHashSet, LinkedHashSet<Long> linkedHashSet2) {
        try {
            int i2 = 0;
            for (String valueOf : strArr) {
                Long valueOf2 = Long.valueOf(valueOf);
                if (Clipboard.getInstance(this.mBlackBoard).contains(valueOf2)) {
                    linkedHashSet.add(Integer.valueOf(getViewPosition(i2)));
                    linkedHashSet2.remove(valueOf2);
                }
                i2++;
            }
            timeTickLog.tick();
            if (!useClipBoardForNormalSelectionMode()) {
                com.samsung.android.gallery.support.utils.Log.d(this.TAG, "clear remained ids from clipboard");
                Iterator<Long> it = linkedHashSet2.iterator();
                while (it.hasNext()) {
                    Clipboard.getInstance(this.mBlackBoard).remove(it.next());
                }
            }
            this.mSelectionManager.replaceSelection(getItemCount(), new ArrayList(linkedHashSet));
            timeTickLog.tick();
            this.mSelectionManager.syncAllDivider(supportHeader());
            timeTickLog.tick();
            com.samsung.android.gallery.support.utils.Log.d(this.TAG, "restoreClipboard(id-list)" + Logger.vt(timeTickLog));
            ThreadUtil.postOnUiThread(new C0372g(1, this));
            ThreadUtil.postOnUiThread(runnable);
        } catch (NullPointerException e) {
            if (this.mBlackBoard == null) {
                com.samsung.android.gallery.support.utils.Log.e(this.TAG, "fail restore");
                return;
            }
            throw e;
        }
    }

    public void scrollToIndexWithOffset(int i2, int i7) {
        this.mGalleryListView.scrollToPositionWithOffset(i2, i7);
    }

    public void setBitmapWithBind(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        V v = this.mView;
        if (v != null && !v.isDestroyed()) {
            ThumbnailRequestHolder thumbnailRequestHolder = (ThumbnailRequestHolder) uniqueKey;
            String tag = thumbnailRequestHolder.getTag();
            String path = thumbnailRequestHolder.getPath();
            if (tag != null && tag.equals(path) && isViewSame(thumbnailRequestHolder.getViewHolder(), thumbnailRequestHolder.getPosition())) {
                ThumbKind thumbKind2 = thumbnailRequestHolder.getViewHolder().getThumbKind();
                ThumbKind thumbKind3 = ThumbKind.XLARGE_NC_KIND;
                if (thumbKind2 == thumbKind3) {
                    if (thumbKind != thumbKind3) {
                        return;
                    }
                } else if (supportHighQuality() && this.mThumbnailResizer.needXLargeThumbnail(thumbnailRequestHolder.getViewHolder(), thumbnailRequestHolder.getPosition())) {
                    this.mThumbnailResizer.requestXLargeThumbnail(thumbnailRequestHolder.getViewHolder());
                }
                thumbnailRequestHolder.setResult(bitmap);
                this.mFgHandler.sendThumbLoadDoneMessage(thumbnailRequestHolder);
            }
        }
    }

    public void setQuickScrollState(boolean z) {
        this.mGalleryListView.setQuickScrollState(z);
        if (!z) {
            onQuickScrollEnd();
        }
    }

    public void setThumbnailKind() {
        if (isDexMode()) {
            this.mThumbnailKind = ThumbKind.MEDIUM_KIND;
        }
        int i2 = this.mGridViewSize;
        if (i2 == 0) {
            i2 = getPredictGridViewSize();
        }
        ThumbKind optimalThumbKind = ThumbKind.getOptimalThumbKind(getContext(), i2);
        ThumbKind thumbKind = this.mThumbnailKind;
        if (thumbKind != optimalThumbKind) {
            if (thumbKind != null) {
                this.mFgHandler.sendReloadThumbMessage();
            }
            this.mThumbnailKind = optimalThumbKind;
        }
    }

    public void sizeDownThumbnail(int i2) {
        this.mThumbnailResizer.sizeDownThumbnail(i2);
    }

    public void startDragSelection(int i2) {
        V v = this.mView;
        if (!v.setInputBlock(this.TAG + "_startDragSelection")) {
            com.samsung.android.gallery.support.utils.Log.w(this.TAG, "startDragSelection skip by InputBlock");
        } else {
            super.startDragSelection(i2);
        }
    }

    public /* bridge */ /* synthetic */ void stopPreview(boolean z) {
        super.stopPreview(z);
    }

    public boolean supportHighQuality() {
        return false;
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        return this.mMediaData.readCache(getMediaItemPosition(i2, i7));
    }

    public int getMediaItemPosition(int i2, int i7) {
        return getMediaItemPosition(i2);
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        listViewHolder.recycle();
        super.onViewRecycled(listViewHolder);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        onBindViewHolder(listViewHolder, i2, getGridSize());
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, int i7) {
        listViewHolder.setThumbKind(getThumbnailKind());
        bindViewHolderInternal(listViewHolder, i2, i7);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        super.onBindViewHolder(listViewHolder, i2, list);
        this.mView.afterBindViewHolder(listViewHolder, i2);
    }

    private void prepareExtendedShare(ArrayList<Integer> arrayList) {
        if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET && this.mView.supportShareSheet()) {
            Integer[] numArr = (Integer[]) arrayList.stream().map(new D(1, this)).toArray(new C0387w(2));
            if (numArr.length > 0) {
                ShareProvider.prepareExtendedShareList(getContext(), this.mBlackBoard, new D(2, this), getDataCount(), ((Integer) Arrays.stream(numArr).min(new D6.a(26)).orElse(0)).intValue(), ((Integer) Arrays.stream(numArr).max(new D6.a(26)).orElse(0)).intValue(), (Runnable) null);
            } else {
                com.samsung.android.gallery.support.utils.Log.w(this.TAG, "prepareExtendedShare fail");
            }
        }
    }

    public void onDensityChange() {
    }

    public void onGridSizeChanged() {
    }

    public void onClusterChanged(int i2) {
    }

    public void onUpdateViewHolder(int i2) {
    }
}
