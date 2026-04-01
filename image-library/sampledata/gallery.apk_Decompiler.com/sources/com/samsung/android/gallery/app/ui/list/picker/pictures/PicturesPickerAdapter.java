package com.samsung.android.gallery.app.ui.list.picker.pictures;

import A.a;
import B5.c;
import a5.C0417a;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.delegate.PickerSelectableChecker;
import com.samsung.android.gallery.app.controller.delegate.SharedContentsPickChecker;
import com.samsung.android.gallery.app.controller.delegate.StoryContentsPickChecker;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesPickerAdapter<V extends IPicturesView> extends PicturesHeaderViewAdapter<V> {
    private String mAlbumPath;
    private final boolean mDimDisabled;
    private boolean mDimEnabledForSinglePick;
    protected LaunchIntent mLaunchIntent;
    private final ArrayList<MediaItem> mSelectedItemsList = new ArrayList<>();
    private final ArrayList<MediaItem> mUnselectedItemsList = new ArrayList<>();

    public PicturesPickerAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
        boolean z3;
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackBoard.read("data://launch_intent");
        this.mLaunchIntent = launchIntent;
        boolean z7 = false;
        if (launchIntent == null || !launchIntent.isPickForStoryContents()) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mDimDisabled = z3;
        if (PickerUtil.isMultiplePickLaunchMode(this.mBlackBoard)) {
            initSelectableChecker(this.mLaunchIntent);
        } else if (!z3 && PickerUtil.isSinglePickLaunchMode(this.mBlackBoard)) {
            LaunchIntent launchIntent2 = this.mLaunchIntent;
            this.mDimEnabledForSinglePick = (launchIntent2 == null || !launchIntent2.isRequireCrop()) ? true : z7;
        }
    }

    private SelectableChecker<MediaItem> createSelectableChecker(Blackboard blackboard) {
        LaunchIntent launchIntent = this.mLaunchIntent;
        if (launchIntent != null && launchIntent.isPickForSharedAlbum()) {
            return new SharedContentsPickChecker(blackboard);
        }
        LaunchIntent launchIntent2 = this.mLaunchIntent;
        if (launchIntent2 == null || !launchIntent2.isPickForStoryContents()) {
            return new PickerSelectableChecker(blackboard);
        }
        return new StoryContentsPickChecker(blackboard);
    }

    private boolean hasSharedPositionFromViewer() {
        return false;
    }

    private void initSelectableChecker(LaunchIntent launchIntent) {
        Blackboard blackboard;
        if (launchIntent != null) {
            String itemCheckerDataKey = launchIntent.getItemCheckerDataKey();
            if (!TextUtils.isEmpty(itemCheckerDataKey)) {
                String stringExtra = launchIntent.getIntent().getStringExtra("blackboard_name");
                if (stringExtra == null) {
                    blackboard = null;
                } else {
                    blackboard = Blackboard.getInstance(stringExtra);
                }
                if (blackboard != null && !blackboard.isEmpty(itemCheckerDataKey)) {
                    this.mSelectableChecker = (SelectableChecker) blackboard.pop(itemCheckerDataKey);
                }
            }
        }
        if (this.mSelectableChecker == null) {
            this.mSelectableChecker = createSelectableChecker(this.mBlackBoard);
        }
        setSelectableChecker(this.mSelectableChecker);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getFilesIdsCursor$1(boolean z, long j2, QueryParams queryParams) {
        if (z) {
            queryParams.setLimit(200000);
        }
        queryParams.maxFileId = j2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreClipboard$0(TimeTickLog timeTickLog, Runnable runnable, LinkedHashSet linkedHashSet, LinkedHashSet linkedHashSet2) {
        PicturesPickerAdapter picturesPickerAdapter;
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView != null && !((IPicturesView) iBaseListView).isDestroyed()) {
            try {
                if (PickerUtil.isMultiplePickLaunchMode(this.mBlackBoard)) {
                    picturesPickerAdapter = this;
                    try {
                        picturesPickerAdapter.restoreClipboardWithCursorDataIdList(getTotalDataIdListFromCursor(timeTickLog), runnable, timeTickLog, linkedHashSet, linkedHashSet2);
                        picturesPickerAdapter.mBlackBoard.postEvent(EventMessage.obtain(1073));
                    } catch (Exception e) {
                        e = e;
                        Exception exc = e;
                        a.s(exc, new StringBuilder("restoreClipboard failed. e="), picturesPickerAdapter.TAG);
                    }
                } else {
                    super.restoreClipboard(runnable, timeTickLog, linkedHashSet, linkedHashSet2);
                }
            } catch (Exception e7) {
                e = e7;
                picturesPickerAdapter = this;
                Exception exc2 = e;
                a.s(exc2, new StringBuilder("restoreClipboard failed. e="), picturesPickerAdapter.TAG);
            }
        }
    }

    public boolean checkVisualCueItem(MediaItem mediaItem) {
        if (!this.mLaunchIntent.isCoverPick() || this.mLaunchIntent.isCoverHistoryItemPick()) {
            return false;
        }
        if (mediaItem.isSharing()) {
            return this.mLaunchIntent.getCurrentCoverItemId().equals(MediaItemMde.getItemId(mediaItem));
        }
        return this.mLaunchIntent.getCurrentCoverItemId().equals(String.valueOf(mediaItem.getFileId()));
    }

    @Deprecated
    public Cursor getFilesIdsCursor(long j2, boolean z) {
        return DbCompat.query(DbKey.TIMELINE_FILE_IDS, new C0417a(j2, z));
    }

    public int getFullLoadedCount() {
        if (isFullyLoaded()) {
            return super.getFullLoadedCount();
        }
        return new MpHelper().getTimelineFileCount();
    }

    public int getHintItemViewType(int i2, int i7) {
        if (i2 != 0 || !isDivider(i2)) {
            return super.getHintItemViewType(i2, i7);
        }
        return -2;
    }

    public int getItemViewType(int i2) {
        if (i2 != 0 || !isDivider(i2)) {
            return super.getItemViewType(i2);
        }
        return -2;
    }

    public int getSelectableMaxCount() {
        return PickerUtil.getMaxPickCount(this.mBlackBoard);
    }

    public int getSelectedItemCount() {
        return Clipboard.getInstance(this.mBlackBoard).getTotalCount();
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
        if (this.mDimEnabledForSinglePick) {
            listViewHolder.setChecked(true);
        }
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        super.onSelected(i2, z, z3);
        if (!isGroupCheckBoxClicked()) {
            return;
        }
        if (z) {
            this.mSelectedItemsList.add(getMediaItemFromCache(i2));
        } else {
            this.mUnselectedItemsList.add(getMediaItemFromCache(i2));
        }
    }

    public void restoreClipboard(Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet<Integer> linkedHashSet, LinkedHashSet<Long> linkedHashSet2) {
        ThreadUtil.postOnBgThread(new c(this, timeTickLog, runnable, linkedHashSet, linkedHashSet2, 19));
    }

    public void resume() {
        if (!hasSharedPositionFromViewer()) {
            notifyDataSetChanged();
        }
    }

    public void sendItemSelectedSyncEvent(boolean z, int i2) {
        Blackboard blackboard;
        int i7;
        if (!isGroupCheckBoxClicked() && (blackboard = this.mBlackBoard) != null) {
            if (z) {
                i7 = 1020;
            } else {
                i7 = 1021;
            }
            blackboard.postEvent(EventMessage.obtain(i7, new ArrayList(Arrays.asList(new MediaItem[]{getMediaItemFromCache(i2)}))));
        }
    }

    public boolean syncClusterData(int i2) {
        boolean syncClusterData = super.syncClusterData(i2);
        if (this.mBlackBoard != null) {
            if (this.mSelectedItemsList.size() > 0) {
                this.mBlackBoard.postEvent(EventMessage.obtain(1020, this.mSelectedItemsList.clone()));
                this.mSelectedItemsList.clear();
                return syncClusterData;
            } else if (this.mUnselectedItemsList.size() > 0) {
                this.mBlackBoard.postEvent(EventMessage.obtain(1021, this.mUnselectedItemsList.clone()));
                this.mUnselectedItemsList.clear();
            }
        }
        return syncClusterData;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        MediaItem mediaItemFromCache = getMediaItemFromCache(i2);
        if (mediaItemFromCache != null) {
            if (this.mAlbumPath == null && isData(i2)) {
                this.mAlbumPath = FileUtils.getDirectoryFromPath(mediaItemFromCache.getPath(), false);
            }
            if (checkVisualCueItem(mediaItemFromCache)) {
                ((ImageViewHolder) listViewHolder).drawVisualCue();
            }
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, int i7) {
        MediaItem mediaItemFromCache;
        super.onBindViewHolder(listViewHolder, i2, i7);
        if (this.mDimDisabled && this.mSelectableChecker != null && (mediaItemFromCache = getMediaItemFromCache(i2)) != null && isData(i2) && !this.mSelectableChecker.isSelectable(mediaItemFromCache)) {
            listViewHolder.setSelectable(false);
        }
    }
}
