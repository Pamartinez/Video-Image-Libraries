package com.samsung.android.gallery.app.ui.list.picker.search;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.delegate.PickerSelectableChecker;
import com.samsung.android.gallery.app.controller.delegate.StoryContentsPickChecker;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchPicturesPickerAdapter<V extends ISearchPicturesView> extends SearchPicturesAdapter<V> {
    private final boolean mDimDisabled;
    private final LaunchIntent mLaunchIntent;
    private final ArrayList<MediaItem> mSelectedItemsList = new ArrayList<>();
    private final ArrayList<MediaItem> mUnselectedItemsList = new ArrayList<>();

    public SearchPicturesPickerAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
        boolean z3;
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackBoard.read("data://launch_intent", null);
        this.mLaunchIntent = launchIntent;
        if (PickerUtil.isMultiplePickLaunchMode(this.mBlackBoard) && launchIntent != null) {
            initSelectableChecker(launchIntent);
        }
        if (launchIntent == null || !launchIntent.isPickForStoryContents()) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mDimDisabled = z3;
    }

    private void initSelectableChecker(LaunchIntent launchIntent) {
        Blackboard blackboard;
        String itemCheckerDataKey = launchIntent.getItemCheckerDataKey();
        if (!TextUtils.isEmpty(itemCheckerDataKey)) {
            String stringExtra = launchIntent.getIntent().getStringExtra("blackboard_name");
            if (stringExtra == null) {
                blackboard = null;
            } else {
                blackboard = Blackboard.getInstance(stringExtra);
            }
            if (blackboard != null && !blackboard.isEmpty(itemCheckerDataKey)) {
                this.mSelectableChecker = (SelectableChecker) blackboard.read(itemCheckerDataKey);
                blackboard.erase(itemCheckerDataKey);
            }
        }
        if (this.mSelectableChecker == null) {
            if (launchIntent.isPickForStoryContents()) {
                this.mSelectableChecker = new StoryContentsPickChecker(this.mBlackBoard);
            } else {
                this.mSelectableChecker = new PickerSelectableChecker(this.mBlackBoard);
            }
        }
        setSelectableChecker(this.mSelectableChecker);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreClipboard$1(Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet linkedHashSet, LinkedHashSet linkedHashSet2) {
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView != null && !((ISearchPicturesView) iBaseListView).isDestroyed()) {
            super.restoreClipboard(runnable, timeTickLog, linkedHashSet, linkedHashSet2);
            if (PickerUtil.isMultiplePickLaunchMode(this.mBlackBoard)) {
                this.mBlackBoard.postEvent(EventMessage.obtain(1073));
            }
        }
    }

    public int getSelectableMaxCount() {
        return PickerUtil.getMaxPickCount(this.mBlackBoard);
    }

    public int getSelectedItemCount() {
        return Clipboard.getInstance(this.mBlackBoard).getTotalCount();
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, int i7) {
        MediaItem mediaItemFromCache;
        super.onBindViewHolder(listViewHolder, i2, i7);
        if (this.mDimDisabled && this.mSelectableChecker != null && (mediaItemFromCache = getMediaItemFromCache(i2)) != null && isData(i2) && !this.mSelectableChecker.isSelectable(mediaItemFromCache)) {
            listViewHolder.setSelectable(false);
        }
    }

    public boolean onCheckBoxClicked(ListViewHolder listViewHolder, int i2) {
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView != null) {
            if (((ISearchPicturesView) iBaseListView).setInputBlock(this.TAG + "_onCheckBoxClicked")) {
                return super.onCheckBoxClicked(listViewHolder, i2);
            }
        }
        listViewHolder.setChecked(this.mSelectionManager.isSelected(Integer.valueOf(i2)));
        return false;
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
        a aVar;
        if (runnable == null) {
            aVar = null;
        } else {
            aVar = new a(runnable);
        }
        ThreadUtil.postOnBgThread(new b(this, aVar, timeTickLog, linkedHashSet, linkedHashSet2));
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

    public void removeRemainedDataFromClipboard(LinkedHashSet<Long> linkedHashSet) {
    }
}
