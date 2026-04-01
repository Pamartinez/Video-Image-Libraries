package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import A2.d;
import A4.C0368c;
import A4.C0369d;
import A4.C0372g;
import A4.I;
import A4.O;
import C3.C0391a;
import C4.o;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailResizer;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumsBaseView;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleViewHolder;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.app.ui.list.hover.HoverParams;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.module.album.AlbumNewLabelUpdater;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsBaseViewAdapter<V extends IAlbumsBaseView> extends BaseListViewAdapter<V> implements IAlbumBaseViewAdapter {
    protected int mCreateVhCounter;
    protected ArrayList<Integer> mDeletePositions = new ArrayList<>();
    private final boolean mIsPickerMode;
    protected int mItemHeight = -1;
    private final SubscriberListener mNewLabelUpdateListener = new C0391a(1, this);
    /* access modifiers changed from: private */
    public final AlbumsBaseViewHolderBindHelper mViewHolderBindHelper = new AlbumsBaseViewHolderBindHelper(new O(16, this));
    protected final AlbumsViewHolderFactory mViewHolderFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FolderBgDataHolder {
        Bitmap mBitmap = null;
        ListViewHolder mViewHolder = null;

        public void set(Bitmap bitmap, ListViewHolder listViewHolder) {
            if (this.mBitmap == null) {
                this.mBitmap = bitmap;
            }
            if (this.mViewHolder == null) {
                this.mViewHolder = listViewHolder;
            }
        }
    }

    public AlbumsBaseViewAdapter(V v, String str) {
        super(v, str);
        initResourceValues();
        this.mViewHolderFactory = createViewHolderFactory(getContext());
        this.mIsPickerMode = PickerUtil.isPickerMode(this.mBlackBoard);
        subscribeLatestAlbumIdChanged();
    }

    private int getAppBarVisibleHeight() {
        if (((IAlbumsBaseView) this.mView).getAppbarLayout() == null) {
            return 0;
        }
        return ((IAlbumsBaseView) this.mView).getAppbarLayout().getVisibleHeight();
    }

    private Bitmap getSubAlbumBitmap(int i2, int i7, MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            return getFolderBitmap();
        }
        if (MediaItemUtil.isEmptyAlbum(mediaItem)) {
            return getBitmapForEmptyAlbum(mediaItem, true);
        }
        Bitmap brokenMediaItem = getBrokenMediaItem(mediaItem);
        if (brokenMediaItem != null) {
            return brokenMediaItem;
        }
        RectF cropRectRatio = mediaItem.getCropRectRatio();
        String thumbCacheKey = mediaItem.getThumbCacheKey();
        ThumbKind calculateChildInFolder = ThumbKind.calculateChildInFolder(i7, i2, mediaItem.isCustomCover());
        if (cropRectRatio == null) {
            cropRectRatio = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(thumbCacheKey, calculateChildInFolder, cropRectRatio);
        if (memCache == null) {
            return ThumbnailLoader.getInstance().getMemCache(thumbCacheKey, calculateChildInFolder, (RectF) null);
        }
        return memCache;
    }

    private MediaItem getSubFolderSubAlbum(MediaItem mediaItem) {
        return (!mediaItem.isFolder() || mediaItem.getAlbumsInFolder().length == 0) ? mediaItem : mediaItem.getAlbumsInFolder()[0];
    }

    private boolean handlePayloads(ListViewHolder listViewHolder, List<Object> list, int i2) {
        boolean z;
        if (list.contains("moveModeChange")) {
            updateForMoveMode(listViewHolder);
            z = true;
        } else {
            z = false;
        }
        if (list.contains("new_label")) {
            if (!ViewHolderValue.isHeader(listViewHolder.getViewType()) && !this.mIsPickerMode) {
                listViewHolder.setNewLabel(getLatestAlbum());
            }
            z = true;
        }
        if (!list.contains("albumSyncViewUpdate")) {
            return z;
        }
        if (Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_STATUS) && listViewHolder.isAlbumSyncEnable() && !ViewHolderValue.isHeader(listViewHolder.getViewType())) {
            updateAlbumSyncData(listViewHolder, i2);
            updateAlbumSyncMargin(listViewHolder, this.mGalleryListView.getDepth(), false);
            listViewHolder.setAlbumSyncView();
        }
        return true;
    }

    private boolean hasNewAlbum(MediaItem[] mediaItemArr) {
        if (mediaItemArr == null || !Arrays.stream(mediaItemArr).anyMatch(new I(3, this))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isNewAlbum(MediaItem mediaItem) {
        if (mediaItem.getAlbumID() == getLatestAlbum()) {
            return true;
        }
        return false;
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
        Log.d(this.TAG, "onNewLabelUpdated", Integer.valueOf(i2));
        ThreadUtil.postOnUiThread(new C0372g(16, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$scrollToDataPosition$6(int i2) {
        Optional.ofNullable(this.mGalleryListView).ifPresent(new C0369d(i2, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFolderViewBitmaps$3(ThumbnailRequestHolder thumbnailRequestHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        thumbnailRequestHolder.setResult(bitmap);
        this.mFgHandler.sendThumbLoadDoneMessage(thumbnailRequestHolder);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setItemHeight$2(ListViewHolder listViewHolder) {
        this.mItemHeight = listViewHolder.itemView.getHeight();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$smoothScrollToPosition$4(boolean z, LinearLayoutManager linearLayoutManager, int i2, GalleryAppBarLayout galleryAppBarLayout) {
        if (z) {
            galleryAppBarLayout.setExpanded(false, true);
            return;
        }
        View findViewByPosition = linearLayoutManager.findViewByPosition(i2);
        if (findViewByPosition != null && this.mGalleryListView.getHeight() - getAppBarVisibleHeight() < findViewByPosition.getBottom()) {
            galleryAppBarLayout.setExpanded(false, true);
        }
    }

    private void setEmptyAlbumViewBitmap(ListViewHolder listViewHolder) {
        ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(listViewHolder);
        MediaItem mediaItem = thumbnailRequestHolder.getMediaItem();
        if (mediaItem == null || thumbnailRequestHolder.getPosition() == -1) {
            String str = this.TAG;
            Log.e(str, "setEmptyAlbumViewBitmap failed " + listViewHolder);
            return;
        }
        Bitmap bitmapForEmptyAlbum = getBitmapForEmptyAlbum(mediaItem.clone(), false);
        if (bitmapForEmptyAlbum != null) {
            thumbnailRequestHolder.setResult(bitmapForEmptyAlbum);
            this.mFgHandler.sendThumbLoadDoneMessage(thumbnailRequestHolder);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001e, code lost:
        r4 = new com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setFolderViewBitmaps(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r12, com.samsung.android.gallery.module.data.MediaItem r13) {
        /*
            r11 = this;
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder[] r0 = r12.getFolderImageHolders()
            if (r0 == 0) goto L_0x0059
            if (r13 == 0) goto L_0x0059
            com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter$FolderBgDataHolder r1 = new com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter$FolderBgDataHolder
            r1.<init>()
            com.samsung.android.gallery.module.data.MediaItem[] r13 = r13.getItemsInFolder()
            int r2 = r0.length
            int r13 = r13.length
            int r13 = java.lang.Math.min(r2, r13)
            r2 = 0
        L_0x0018:
            if (r2 >= r13) goto L_0x0059
            r3 = r0[r2]
            if (r3 == 0) goto L_0x0056
            com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder r4 = new com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder
            r4.<init>(r3)
            com.samsung.android.gallery.module.data.MediaItem r5 = r11.getSubFolderSubAlbum(r4, r12)
            if (r5 != 0) goto L_0x002a
            goto L_0x0056
        L_0x002a:
            android.graphics.Bitmap r6 = r11.getSubAlbumBitmap(r2, r13, r5)
            if (r6 == 0) goto L_0x003e
            r4.setResult(r6)
            A4.g r5 = new A4.g
            r7 = 15
            r5.<init>(r7, r4)
            com.samsung.android.gallery.support.utils.ThreadUtil.runOnUiThread(r5)
            goto L_0x0053
        L_0x003e:
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r7 = com.samsung.android.gallery.module.thumbnail.ThumbnailLoader.getInstance()
            boolean r8 = r5.isCustomCover()
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r8 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.calculateChildInFolder(r13, r2, r8)
            A4.H r9 = new A4.H
            r10 = 3
            r9.<init>((int) r10, (java.lang.Object) r11, (java.lang.Object) r4)
            r7.loadThumbnail(r5, r8, r4, r9)
        L_0x0053:
            r1.set(r6, r3)
        L_0x0056:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter.setFolderViewBitmaps(com.samsung.android.gallery.widget.listviewholder.ListViewHolder, com.samsung.android.gallery.module.data.MediaItem):void");
    }

    private void updateAlbumSyncData(ListViewHolder listViewHolder, int i2) {
        int i7;
        MediaItem loadMediaItemSync = loadMediaItemSync(getMediaItemPosition(i2));
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem != null && loadMediaItemSync != null && mediaItem.getAlbumID() == loadMediaItemSync.getAlbumID() && CloudData.of(mediaItem).albumSyncStatus != (i7 = CloudData.of(loadMediaItemSync).albumSyncStatus)) {
            CloudData.of(mediaItem).albumSyncStatus = i7;
        }
    }

    public boolean bindImageOnScrollIdle(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ListViewHolder[] folderImageHolders;
        if (mediaItem == null || !mediaItem.isFolder() || (folderImageHolders = listViewHolder.getFolderImageHolders()) == null) {
            return super.bindImageOnScrollIdle(listViewHolder, mediaItem);
        }
        FolderBgDataHolder folderBgDataHolder = new FolderBgDataHolder();
        MediaItem[] itemsInFolder = mediaItem.getItemsInFolder();
        int min = Math.min(folderImageHolders.length, itemsInFolder.length);
        int i2 = 0;
        for (int i7 = 0; i7 < min; i7++) {
            ListViewHolder listViewHolder2 = folderImageHolders[i7];
            if (listViewHolder2 != null) {
                Bitmap subAlbumBitmap = getSubAlbumBitmap(i7, min, getSubFolderSubAlbum(itemsInFolder[i7]));
                if (subAlbumBitmap != null) {
                    listViewHolder2.bindImage(subAlbumBitmap);
                } else {
                    i2++;
                }
                folderBgDataHolder.set(subAlbumBitmap, listViewHolder2);
            }
        }
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    public void bindNewAlbumLabel(ListViewHolder listViewHolder) {
        if (!this.mIsPickerMode) {
            this.mViewHolderBindHelper.bindNewAlbumLabel(listViewHolder, getLatestAlbum());
        }
    }

    public void checkVisibleViewHoldersForXLarge() {
        if (supportHighQuality()) {
            super.checkVisibleViewHoldersForXLarge();
        }
    }

    public ThumbnailResizer createThumbnailResizer() {
        return new ThumbnailResizer(this) {
            public boolean needXLargeThumbnail(ListViewHolder listViewHolder, int i2) {
                MediaItem mediaItem;
                if (i2 < 0) {
                    i2 = listViewHolder.getViewPosition();
                }
                if (!isCandidate(listViewHolder, i2) || (mediaItem = listViewHolder.getMediaItem()) == null || mediaItem.isFolder()) {
                    return false;
                }
                return true;
            }
        };
    }

    public /* bridge */ /* synthetic */ ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        return (ListViewHolder) createViewHolder(viewGroup, i2);
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new AlbumsViewHolderFactory(context);
    }

    public void destroy() {
        unSubscribeLatestAlbumIdChanged();
        super.destroy();
    }

    public Bitmap getBitmapForEmptyAlbum(MediaItem mediaItem, boolean z) {
        if (MediaItemUtil.isEmptyAlbum(mediaItem) || (mediaItem.isFolder() && mediaItem.isEmpty())) {
            return ThumbnailLoader.getInstance().getDefaultAlbumImage(z);
        }
        return null;
    }

    public long getDataId(MediaItem mediaItem) {
        return (long) mediaItem.getAlbumID();
    }

    public List<Long> getDataIds() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mMediaData.getCount(); i2++) {
            MediaItem read = this.mMediaData.read(i2);
            if (read != null) {
                arrayList.add(Long.valueOf((long) read.getAlbumID()));
            }
        }
        return arrayList;
    }

    public Bitmap getFolderBitmap() {
        return ThumbnailLoader.getInstance().getDefaultAlbumImage(true);
    }

    public String getGridInfoTextForVoiceAssistant(int i2) {
        Context context = getContext();
        if (context == null) {
            return "";
        }
        if (i2 > 1) {
            return context.getResources().getQuantityString(R.plurals.grid_view_with_thumbnails_in_each_row, i2, new Object[]{Integer.valueOf(i2)});
        }
        return context.getResources().getString(R.string.list_view);
    }

    public int getHeaderCount() {
        return 0;
    }

    public int getHintItemViewType(int i2, int i7) {
        if (i2 == 0 && supportHeader()) {
            return -3;
        }
        if (i2 == getItemCount() - 1 && hasFooter()) {
            return -4;
        }
        if (i7 == 1) {
            return 1;
        }
        return 2;
    }

    public int getItemHeight(int i2) {
        return this.mItemHeight;
    }

    public int getItemViewType(int i2) {
        return getHintItemViewType(i2, getGridSize());
    }

    public int getLatestAlbum() {
        return AlbumNewLabelUpdater.getInstance().getLatestAlbumId();
    }

    public int getMaxScroll() {
        int i2;
        int gridSize = getGridSize();
        int itemCount = getItemCount();
        int i7 = itemCount / gridSize;
        if (itemCount % gridSize > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return this.mGalleryListView.getPaddingBottom() + (getItemHeight(0) * (i7 + i2));
    }

    public int getNextRowIndex(int i2, int i7) {
        return Math.min(getGridSize() + i2, i7 - 1);
    }

    public int getPrevRowIndex(int i2) {
        return Math.max(0, i2 - getGridSize());
    }

    public int getSelectableMaxCount() {
        if (PickerUtil.isMultiplePickLaunchMode(this.mBlackBoard)) {
            return PickerUtil.getMaxPickCount(this.mBlackBoard);
        }
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager != null) {
            return selectionManager.getMaxCount();
        }
        return -1;
    }

    public int getSelectedDataCount(ArrayList<Integer> arrayList) {
        return dataOnlyListCount(arrayList);
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public AlbumsViewHolderFactory getViewHolderFactory(int i2) {
        return this.mViewHolderFactory;
    }

    public void initResourceValues() {
        this.mViewHolderBindHelper.initResources(getContext(), isSupportListView());
    }

    public boolean isEnabledDragOnAdvancedMouseEvent() {
        if (!DeviceInfo.isAdvancedMouseCompat(getContext()) || ((IAlbumsBaseView) this.mView).isMoveMode()) {
            return false;
        }
        return true;
    }

    public boolean isMoveHerePossible(ListViewHolder listViewHolder) {
        MediaItem mediaItem;
        if (!listViewHolder.isFolder() || (mediaItem = listViewHolder.getMediaItem()) == null) {
            return false;
        }
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackBoard.read("data://album_move");
        if (mediaItemArr == null) {
            return true;
        }
        for (MediaItem albumID : mediaItemArr) {
            if (mediaItem.getAlbumID() == albumID.getAlbumID()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNeedToSyncClusterDivider(ListViewHolder listViewHolder, MediaItem mediaItem, boolean z) {
        int viewPosition = listViewHolder.getViewPosition();
        if (!(this.mSelectionManager.isSelected(Integer.valueOf(viewPosition)) ^ z) || viewPosition < 0) {
            return super.isNeedToSyncClusterDivider(listViewHolder, mediaItem, z);
        }
        return true;
    }

    public boolean isPinchSupported() {
        if (((IAlbumsBaseView) this.mView).isMoveMode() || !super.isPinchSupported()) {
            return false;
        }
        return true;
    }

    public boolean isSupportListView() {
        return !isDexMode();
    }

    public boolean needBadgeOnViewAllOfEssentialAlbums() {
        ArrayList<MediaItem> fullData;
        if (!this.mIsPickerMode && (fullData = this.mMediaData.getFullData()) != null) {
            Iterator<MediaItem> it = fullData.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (!next.isAlbumLvFirst()) {
                    if (next.isFolder() || next.isMergedAlbum()) {
                        if (hasNewAlbum(next.getAlbumsInFolder())) {
                            return true;
                        }
                    } else if (isNewAlbum(next)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void notifyAlbumSyncChanged() {
        notifyItemRangeChanged(0, getItemCount(), "albumSyncViewUpdate");
    }

    public void notifyMoveModeChanged() {
        notifyItemRangeChanged(0, getItemCount(), "moveModeChange");
    }

    public void onAbortDelete() {
        this.mDeletePositions.clear();
    }

    public void onHoverInternal(ListViewHolder listViewHolder, MotionEvent motionEvent) {
        if (listViewHolder.getViewType() != 0) {
            ((HoverHandler) this.mHoverHandler.get()).onHover(listViewHolder, motionEvent, new HoverParams.HoverParamsBuilder(getLocationKey()).setContainer((ViewGroup) ((IAlbumsBaseView) this.mView).getView()).setItem(listViewHolder.getMediaItem()).setDataPosition(getMediaItemPosition(listViewHolder.getViewPosition())).setAlbumType(true).setHideOption(false).build());
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (isData(i2)) {
            postAnalyticsLogItemClick(mediaItem);
        }
        super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (!PickerUtil.isNormalLaunchMode(this.mBlackBoard) || ((IAlbumsBaseView) this.mView).isMoveMode()) {
            return false;
        }
        ((IAlbumsBaseView) this.mView).postAnalyticsLog(AnalyticsEventId.EVENT_LONG_PRESS_ALBUM);
        return super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public void onNewItemCreated(String str, String str2, int i2, String str3, int i7) {
        int createNewItem = this.mMediaData.createNewItem(str, str2, i2, str3, i7);
        if (createNewItem != -1) {
            updateCluster();
            notifyItemInserted(getViewPosition(createNewItem));
            if (getDataCount() > 1) {
                smoothScrollToPosition(getViewPosition(createNewItem));
            }
        }
    }

    public void onPrepareDelete(LinearLayoutManager linearLayoutManager) {
        this.mDeletePositions.clear();
        ArrayList<Integer> selectedList = this.mSelectionManager.getSelectedList();
        if (selectedList != null && !selectedList.isEmpty()) {
            this.mDeletePositions.addAll(selectedList);
        }
        if (!this.mDeletePositions.isEmpty()) {
            this.mDeletePositions.sort(Comparator.reverseOrder());
        }
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        while (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
            if (!this.mDeletePositions.contains(Integer.valueOf(findFirstVisibleItemPosition))) {
                findFirstVisibleItemPosition++;
            } else {
                return;
            }
        }
        this.mDeletePositions.clear();
    }

    public void onStartDelete() {
        if (!this.mDeletePositions.isEmpty()) {
            Iterator<Integer> it = this.mDeletePositions.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                this.mMediaData.removeItemAt(intValue);
                notifyItemRemoved(intValue);
            }
            this.mDeletePositions.clear();
        }
    }

    public void postAnalyticsLogItemClick(MediaItem mediaItem) {
        if (mediaItem != null && AlbumTitleHelper.isTranslatedAlbum(mediaItem.getAlbumID()).booleanValue()) {
            AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_VIEW_ALBUM;
            ((IAlbumsBaseView) this.mView).postAnalyticsLog(analyticsEventId, mediaItem.getTitle() + "_" + (mediaItem.isAlbumLvFirst() ? 1 : 0));
        }
    }

    public boolean preBindImageOnScrollIdle(ListViewHolder listViewHolder, MediaItem mediaItem) {
        Bitmap bitmapForEmptyAlbum = getBitmapForEmptyAlbum(mediaItem, false);
        if (bitmapForEmptyAlbum == null) {
            return super.preBindImageOnScrollIdle(listViewHolder, mediaItem);
        }
        listViewHolder.bindImage(bitmapForEmptyAlbum);
        return true;
    }

    public void refreshAlbumNewLabel(EventMessage eventMessage) {
        AlbumNewLabelUpdater.getInstance().refresh(eventMessage);
    }

    public void requestThumbnail(ListViewHolder listViewHolder, int i2) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem != null) {
            if (mediaItem.isFolder()) {
                setFolderViewBitmaps(listViewHolder, mediaItem);
                return;
            } else if (MediaItemUtil.isEmptyAlbum(mediaItem)) {
                setEmptyAlbumViewBitmap(listViewHolder);
                return;
            }
        }
        super.requestThumbnail(listViewHolder, i2);
    }

    public void scrollToDataPosition(int i2, long j2) {
        int viewPosition = getViewPosition(i2);
        Log.d(this.TAG, "scrollToDataPosition", Integer.valueOf(i2), Integer.valueOf(viewPosition));
        this.mGalleryListView.postDelayed(new C0368c(this, viewPosition, 3), j2);
    }

    public void setItemHeight(ListViewHolder listViewHolder) {
        listViewHolder.itemView.post(new d(23, this, listViewHolder));
    }

    public void setListeners(ListViewHolder listViewHolder) {
        super.setListeners(listViewHolder);
        if (listViewHolder instanceof AlbumTitleViewHolder) {
            ((AlbumTitleViewHolder) listViewHolder).setBorderListener(new AlbumTitleViewHolder.BorderListener() {
                public Drawable getBorder(boolean z) {
                    return AlbumsBaseViewAdapter.this.mViewHolderBindHelper.getBorderDrawable(getDepth(), z);
                }

                public int getDepth() {
                    return AlbumsBaseViewAdapter.this.mGalleryListView.getDepth();
                }

                public boolean isDexMode() {
                    return ((IAlbumsBaseView) AlbumsBaseViewAdapter.this.mView).isDexMode();
                }
            });
        }
    }

    public void smoothScrollToPosition(int i2) {
        int i7;
        AlbumsBaseViewAdapter albumsBaseViewAdapter;
        boolean z;
        if (this.mGalleryListView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mGalleryListView.getLayoutManager();
            if (i2 > linearLayoutManager.findLastVisibleItemPosition()) {
                z = true;
            } else {
                z = false;
            }
            albumsBaseViewAdapter = this;
            i7 = i2;
            Optional.ofNullable(((IAlbumsBaseView) this.mView).getAppbarLayout()).ifPresent(new o(albumsBaseViewAdapter, z, linearLayoutManager, i7, 0));
        } else {
            albumsBaseViewAdapter = this;
            i7 = i2;
        }
        albumsBaseViewAdapter.mGalleryListView.scrollToPositionWithAnimation(i7);
    }

    public void startDragSelection(int i2) {
        super.startDragSelection(i2);
        if (!isDragSelectSupported()) {
            getDragSelectTouchListener().setIsActive(false);
        }
    }

    public void subscribeLatestAlbumIdChanged() {
        this.mBlackBoard.subscribe("data://user/latest_album_id_changed", this.mNewLabelUpdateListener);
    }

    public boolean supportHeader() {
        return false;
    }

    public boolean supportHighQuality() {
        GridSpans gridSpans;
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView != null && ((IAlbumsBaseView) iBaseListView).isOngoingPinchAnimation()) {
            return false;
        }
        IBaseListView iBaseListView2 = this.mView;
        if (iBaseListView2 != null) {
            gridSpans = ((IAlbumsBaseView) iBaseListView2).getGridSpans();
        } else {
            gridSpans = null;
        }
        if (gridSpans == null || gridSpans.indexMax() != gridSpans.index) {
            return false;
        }
        return true;
    }

    public boolean supportHover() {
        return true;
    }

    public boolean supportVideoPreview() {
        return false;
    }

    public void unSubscribeLatestAlbumIdChanged() {
        this.mBlackBoard.unsubscribe("data://user/latest_album_id_changed", this.mNewLabelUpdateListener);
    }

    public void updateAlbumBlurInfo(ListViewHolder listViewHolder, int i2, int i7) {
        listViewHolder.updateBlurInfo(i2, i7);
    }

    public void updateAlbumSyncMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        this.mViewHolderBindHelper.updateAlbumSyncMargin(listViewHolder, i2, z);
    }

    public void updateAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        this.mViewHolderBindHelper.updateAlbumTypeMargin(listViewHolder, i2, z);
    }

    public void updateBorders(ListViewHolder listViewHolder, int i2) {
        this.mViewHolderBindHelper.updateBorders(listViewHolder, i2);
    }

    public void updateEmptyAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        this.mViewHolderBindHelper.updateEmptyAlbumViewSize(listViewHolder, i2, z);
    }

    public void updateFolderChildViewSize(ListViewHolder[] listViewHolderArr, int i2, int i7) {
        this.mViewHolderBindHelper.updateFolderChildViewSize(listViewHolderArr, i2, i7);
    }

    public void updateFolderViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        this.mViewHolderBindHelper.updateFolderViewSize(listViewHolder, i2, z);
    }

    public void updateForMoveMode(ListViewHolder listViewHolder) {
        float f;
        View rootView = listViewHolder.getRootView();
        if (!((IAlbumsBaseView) this.mView).isMoveMode() || isMoveHerePossible(listViewHolder)) {
            f = 1.0f;
        } else {
            f = 0.5f;
        }
        rootView.setAlpha(f);
    }

    public void updateTitleContainerMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        this.mViewHolderBindHelper.updateTitleContainerMargin(listViewHolder, i2, z);
    }

    public void updateVirtualAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        this.mViewHolderBindHelper.updateVirtualAlbumViewSize(listViewHolder, i2, z);
    }

    public int getItemViewType(int i2, int i7) {
        return getHintItemViewType(i2, i7);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        ListViewHolder createViewHolder = getViewHolderFactory(i2).createViewHolder(viewGroup, i2);
        if (ThreadUtil.isMainThread() && !"c".equals(createViewHolder.itemView.getTag(-100))) {
            int i7 = this.mCreateVhCounter + 1;
            this.mCreateVhCounter = i7;
            if (i7 > 10) {
                String str = this.TAG;
                Log.w(str, "onCreateViewHolder#ui {#" + this.mCreateVhCounter + ",typ=" + i2 + "} unexpected +" + (System.currentTimeMillis() - currentTimeMillis));
            }
        }
        return createViewHolder;
    }

    private MediaItem getSubFolderSubAlbum(ThumbnailRequestHolder thumbnailRequestHolder, ListViewHolder listViewHolder) {
        MediaItem mediaItem = thumbnailRequestHolder.getMediaItem();
        if (mediaItem == null) {
            String str = this.TAG;
            Log.e(str, "Fatal : key.getMediaItem() is null : " + listViewHolder);
            return null;
        } else if (thumbnailRequestHolder.getPosition() != -1) {
            return mediaItem.clone();
        } else {
            String str2 = this.TAG;
            Log.e(str2, "Fatal : no media item or invalid view position to request Thumb : " + listViewHolder);
            return null;
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (!handlePayloads(listViewHolder, list, i2) || list.contains("checkBox")) {
            if (!isDivider(i2) && isData(i2)) {
                setItemHeight(listViewHolder);
            }
            super.onBindViewHolder(listViewHolder, i2, list);
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        bindNewAlbumLabel(listViewHolder);
        updateAlbumTypeMargin(listViewHolder, this.mGalleryListView.getDepth(), false);
        updateAlbumSyncMargin(listViewHolder, this.mGalleryListView.getDepth(), false);
        updateBorders(listViewHolder, this.mGalleryListView.getDepth());
        updateFolderViewSize(listViewHolder, this.mGalleryListView.getDepth(), false);
        updateForMoveMode(listViewHolder);
        updateEmptyAlbumViewSize(listViewHolder, this.mGalleryListView.getDepth(), false);
        updateVirtualAlbumViewSize(listViewHolder, this.mGalleryListView.getDepth(), false);
        updateTitleContainerMargin(listViewHolder, this.mGalleryListView.getDepth(), false);
        updateAlbumBlurInfo(listViewHolder, this.mGalleryListView.getDepth(), this.mGalleryListView.getMaxDepth());
    }

    public void updateCluster() {
    }
}
