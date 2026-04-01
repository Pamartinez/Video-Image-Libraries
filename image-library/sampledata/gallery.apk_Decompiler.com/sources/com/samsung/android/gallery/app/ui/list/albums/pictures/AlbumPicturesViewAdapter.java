package com.samsung.android.gallery.app.ui.list.albums.pictures;

import A4.C0387w;
import L5.b;
import M4.p;
import M4.q;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapter;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import e5.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPicturesViewAdapter<V extends IAlbumPicturesView> extends PicturesHeaderViewAdapter<V> {
    private int mCurrentMode = -1;
    private final int mHeaderViewTopPadding;

    public AlbumPicturesViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
        if (PickerUtil.isNormalLaunchMode(this.mBlackBoard)) {
            Clipboard.getInstance(this.mBlackBoard).clear();
        }
        this.mHeaderViewTopPadding = getContext().getResources().getDimensionPixelOffset(R.dimen.album_pictures_side_spacing);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$restoreClipboard$0(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unSelectAll$1(Blackboard blackboard) {
        removeUnselectAllItemsFromClipBoard(Clipboard.getInstance(blackboard));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unSelectAll$2(SelectionManager selectionManager) {
        selectionManager.unSelectAll();
        ThreadUtil.postOnUiThread(new p(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unSelectAll$3() {
        Optional.ofNullable(this.mBlackBoard).ifPresent(new q(this, 0));
        Optional.ofNullable(this.mSelectionManager).ifPresent(new q(this, 1));
    }

    private void removeUnselectAllItemsFromClipBoard(Clipboard clipboard) {
        if (isFullyLoaded()) {
            TimeTickLog timeTickLog = new TimeTickLog("removeUnselectAllItemsFromClipBoard");
            Iterator it = new ArrayList(getDataIds()).iterator();
            while (it.hasNext()) {
                Long l = (Long) it.next();
                clipboard.remove(l);
                this.mSelectionBuffer.remove(l);
            }
            String str = this.TAG;
            Log.d(str, "removeUnselectAllItemsFromClipBoard : " + timeTickLog.getElapsedTime());
        }
    }

    public void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        super.attachHeaderViewToHolder(listViewHolder);
        if (ViewHolderValue.isHeader(listViewHolder.getViewType())) {
            ViewMarginUtils.setTopPadding(listViewHolder.getRootView(), this.mHeaderViewTopPadding);
        }
    }

    public void fixTimelineMode() {
        MultiClusterAdapter.TimelineModeLookup createTimelineModeLookup = createTimelineModeLookup();
        setCurrentMode(((m) createTimelineModeLookup).d.getTimelineModeLookup(getGridSize()));
    }

    public int getCurrentMode() {
        return this.mCurrentMode;
    }

    @Deprecated
    public Cursor getFilesIdsCursor(long j2, boolean z) {
        MediaItem mediaItem = (MediaItem) this.mBlackBoard.read("data://albums/current", null);
        if (mediaItem == null) {
            return null;
        }
        QueryParams queryParams = new QueryParams();
        if (PickerUtil.isNormalLaunchMode(this.mBlackBoard)) {
            queryParams.addGroupType(GroupType.SINGLE_TAKEN);
        }
        int loadSortBy = GalleryPreference.getInstance().loadSortBy(String.valueOf(mediaItem.getAlbumID()), 12);
        if (loadSortBy != 12) {
            queryParams.setSortBy(loadSortBy);
        }
        if (z) {
            queryParams.setLimit(200000);
        }
        queryParams.addAlbumId(mediaItem.getAlbumID()).setMaxFileId(j2);
        queryParams.setDbKey(DbKey.ALBUM_FILE_IDS);
        return DbCompat.query(queryParams);
    }

    public int getFullLoadedCount() {
        if (isFullyLoaded()) {
            return getItemCount();
        }
        MediaItem mediaItem = (MediaItem) this.mBlackBoard.read("data://albums/current", null);
        if (mediaItem == null) {
            return getItemCount();
        }
        int[] albumCount = new MpHelper(new QueryParams(GroupType.BURST, GroupType.SINGLE_TAKEN).addAlbumId(mediaItem.getAlbumID())).getAlbumCount();
        return albumCount[0] + albumCount[1];
    }

    public TextView getHeaderDescriptionView() {
        View view = this.mHeaderView;
        if (view == null || !ViewUtils.isVisible(view.findViewById(R.id.tip_card_layout))) {
            return null;
        }
        return super.getHeaderDescriptionView();
    }

    public int getHeaderViewHeight() {
        return super.getHeaderViewHeight() + this.mHeaderViewTopPadding;
    }

    public int getPredictGridViewSize() {
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView == null || !((IAlbumPicturesView) iBaseListView).isSplitMode()) {
            return super.getPredictGridViewSize();
        }
        return ((((IAlbumPicturesView) this.mView).getContentView().getWidth() - ((IAlbumPicturesView) this.mView).getAlbumListWidth()) / getGridSize()) - getGridItemSpacing();
    }

    public int getSplitSpacing() {
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView == null || !((IAlbumPicturesView) iBaseListView).isSplitMode()) {
            return 0;
        }
        return ((IAlbumPicturesView) this.mView).getAlbumListWidth();
    }

    public int getTimelineModeLookup(int i2) {
        int i7 = this.mCurrentMode;
        if (i7 != -1) {
            return i7;
        }
        return ((IAlbumPicturesView) this.mView).getGridSpans().clusterOf(i2);
    }

    public void initData() {
        super.initData();
        setCurrentMode(-1);
    }

    public boolean isDisableShareSheet(MediaItem[] mediaItemArr) {
        MediaItem mediaItem = (MediaItem) ((IAlbumPicturesView) this.mView).getBlackboard().read("data://albums/current", null);
        boolean z = false;
        if (mediaItem == null || (!mediaItem.isVirtualAlbum() && !mediaItem.isMergedAlbum())) {
            int length = mediaItemArr.length;
            int i2 = 0;
            int i7 = -1;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                MediaItem mediaItem2 = mediaItemArr[i2];
                if (i7 != -1 && i7 != mediaItem2.getBucketID()) {
                    Log.d(this.TAG, "ShareSheet has selected files in more then two albums");
                    z = true;
                    break;
                }
                i7 = mediaItem2.getBucketID();
                i2++;
            }
            if (mediaItem == null || mediaItem.getAlbumID() == i7) {
                return z;
            }
            Log.d(this.TAG, "ShareSheet located at different album");
            return true;
        }
        for (MediaItem of2 : mediaItemArr) {
            int i8 = DetailsData.of(of2).parentAlbumId;
            if (i8 == 0 || mediaItem.getAlbumID() != i8) {
                Log.d(this.TAG, "ShareSheet has selected files are not in virtual or merged album");
                return true;
            }
        }
        return false;
    }

    public boolean isHideDecoItem(int i2) {
        return isHideDecoItem(i2, ((IAlbumPicturesView) this.mView).isSplitMode());
    }

    public boolean needChangeThumbnailKind(int i2) {
        GalleryListView galleryListView;
        if (!super.needChangeThumbnailKind(i2) || (galleryListView = this.mGalleryListView) == null || galleryListView.getMaxColumnCount() <= getGridSize()) {
            return false;
        }
        return true;
    }

    public void onDataRangeInserted(int i2, int i7) {
        super.onDataRangeInserted(i2, i7);
        if (isSelectionMode()) {
            this.mSelectionManager.updateTotalCount(getItemCount());
        }
    }

    public void resetTimelineMode() {
        setCurrentMode(-1);
    }

    public void restoreClipboard(Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet<Integer> linkedHashSet, LinkedHashSet<Long> linkedHashSet2) {
        if (((MediaItem) this.mBlackBoard.read("data://albums/current", null)) != null) {
            restoreClipboardWithCursorDataIdList((String[]) getDataIds().stream().map(new b(5)).toArray(new C0387w(21)), runnable, timeTickLog, linkedHashSet, linkedHashSet2);
            return;
        }
        Log.e(this.TAG, "no data item");
    }

    public void setCurrentMode(int i2) {
        this.mCurrentMode = i2;
    }

    public void setGridSize(int i2, int i7) {
        boolean z;
        if (i2 >= i7) {
            z = true;
        } else {
            z = false;
        }
        this.mHideDecoIcons = z;
    }

    public boolean skipEmptyView(boolean z) {
        if (!z || !((IAlbumPicturesView) this.mView).skipEmptyView()) {
            return false;
        }
        return true;
    }

    public void unSelectAll() {
        if (useClipBoardForNormalSelectionMode()) {
            ThreadUtil.postOnBgThread(new p(this, 0));
        } else {
            super.unSelectAll();
        }
    }

    public boolean useClipBoardForNormalSelectionMode() {
        return isSelectionMode();
    }

    public boolean isHideDecoItem(int i2, boolean z) {
        return ((IAlbumPicturesView) this.mView).getGridSpans().isDecoHide(i2, z);
    }
}
