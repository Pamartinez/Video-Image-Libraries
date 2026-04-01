package com.samsung.android.gallery.app.ui.list.picker.albums;

import A8.C0545b;
import Gb.a;
import U5.c;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.delegate.PickerSelectableChecker;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceAdapter;
import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumFolderPicker;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderPickerAdapter<V extends IAlbumFolderPicker> extends AlbumFolderChoiceAdapter<V> {
    private int mHeaderHeight;
    private final HashMap<Integer, List<Integer>> mMergedAlbumIds = new HashMap<>();

    public AlbumFolderPickerAdapter(V v, String str) {
        super(v, str);
        setSelectableChecker(new PickerSelectableChecker(this.mBlackBoard));
        if (PreferenceFeatures.OneUi6x.SUPPORT_PICKER_PRESELECTED) {
            loadPreselectedItems();
        }
    }

    private void addToClipBoard(MediaItem mediaItem, Set<Integer> set) {
        if (mediaItem.isMergedAlbum()) {
            MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
            for (MediaItem albumID : albumsInFolder) {
                if (set.contains(Integer.valueOf(albumID.getAlbumID()))) {
                    this.mClipBoard.add(Long.valueOf((long) mediaItem.getAlbumID()));
                    this.mMergedAlbumIds.put(Integer.valueOf(mediaItem.getAlbumID()), (List) Arrays.stream(albumsInFolder).map(new a(7)).collect(Collectors.toList()));
                    return;
                }
            }
        } else if (mediaItem.isFolder()) {
            for (MediaItem addToClipBoard : mediaItem.getChildAlbums()) {
                addToClipBoard(addToClipBoard, set);
            }
        } else if (set.contains(Integer.valueOf(mediaItem.getAlbumID()))) {
            this.mClipBoard.add(Long.valueOf((long) mediaItem.getAlbumID()));
        }
    }

    private String getPreselectedIdString() {
        String str = (String) Blackboard.getApplicationInstance().pop("data://selected_albums");
        if (TextUtils.isEmpty(str)) {
            LaunchIntent launchIntent = (LaunchIntent) this.mBlackBoard.read("data://launch_intent");
            str = null;
            if (launchIntent != null) {
                return (String) launchIntent.getExtra("picker_preselected_sem_id", null);
            }
        }
        return str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(ListViewHolder listViewHolder) {
        this.mHeaderHeight = listViewHolder.itemView.getHeight();
    }

    private void loadAlbumIdList(MediaItem mediaItem, ArrayList<String> arrayList) {
        if (mediaItem.isFolder()) {
            for (MediaItem loadAlbumIdList : mediaItem.getChildAlbums()) {
                loadAlbumIdList(loadAlbumIdList, arrayList);
            }
            return;
        }
        arrayList.add(String.valueOf(mediaItem.getAlbumID()));
    }

    private void loadPreselectedItems() {
        ArrayList<MediaItem> fullData;
        String preselectedIdString = getPreselectedIdString();
        if (!TextUtils.isEmpty(preselectedIdString)) {
            Set set = (Set) Arrays.stream(preselectedIdString.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new e(18)).collect(Collectors.toSet());
            MediaData mediaData = ((IAlbumFolderPicker) this.mView).getMediaData((String) null);
            if (mediaData != null && (fullData = mediaData.getFullData()) != null && !fullData.isEmpty()) {
                for (int i2 = 0; i2 < fullData.size(); i2++) {
                    MediaItem mediaItem = fullData.get(i2);
                    if (mediaItem != null) {
                        addToClipBoard(mediaItem, set);
                    }
                }
            }
        }
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new AlbumFolderPickerViewHolderFactory(context) {
            public boolean isFlipWidgetWithCoverScreen() {
                return ((IAlbumFolderPicker) AlbumFolderPickerAdapter.this.mView).isFlipWidgetWithCoverScreen();
            }
        };
    }

    public int getHintItemViewHeight(int i2, int i7, int i8) {
        if (!supportHeader() || !isHeader(i2)) {
            return this.mItemHeight;
        }
        return this.mHeaderHeight;
    }

    public int getHintSpanSize(int i2, int i7) {
        if (!supportHeader() || !isHeader(i2)) {
            return 1;
        }
        return i7;
    }

    public int getHintStartSpan(int i2, int i7) {
        if (!supportHeader()) {
            return i2 % i7;
        }
        if (isHeader(i2)) {
            return 0;
        }
        return (i2 - 1) % i7;
    }

    public int getItemHeight(int i2) {
        if (!supportHeader() || !isHeader(i2)) {
            return super.getItemHeight(i2);
        }
        return this.mHeaderHeight;
    }

    public int getMaxScroll() {
        int i2;
        int gridSize = getGridSize();
        int itemCount = getItemCount() - (supportHeader() ? 1 : 0);
        int i7 = itemCount / gridSize;
        if (itemCount % gridSize > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return (getItemHeight(1) * (i7 + i2)) + this.mHeaderHeight;
    }

    public int getSelectableMaxCount() {
        if (!((IAlbumFolderPicker) this.mView).isFlipWidget()) {
            return 0;
        }
        return PickerUtil.getMaxPickCount(this.mBlackBoard);
    }

    public int[] getSelectedAlbumIds() {
        ArrayList arrayList = new ArrayList();
        Iterator<Long> it = this.mClipBoard.cloneSet().iterator();
        while (it.hasNext()) {
            int longValue = (int) it.next().longValue();
            if (this.mMergedAlbumIds.containsKey(Integer.valueOf(longValue))) {
                arrayList.addAll(this.mMergedAlbumIds.get(Integer.valueOf(longValue)));
            } else {
                arrayList.add(Integer.valueOf(longValue));
            }
        }
        return arrayList.stream().mapToInt(new C0545b(16)).toArray();
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (!isEnableAlbum(mediaItem)) {
            return;
        }
        if (mediaItem.isFolder()) {
            onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
        } else {
            super.handleItemClick(listViewHolder, i2, mediaItem, i7);
        }
    }

    public boolean isEnableAlbum(MediaItem mediaItem) {
        if (!super.isEnableAlbum(mediaItem) || mediaItem.isEmptyAlbum() || mediaItem.getCount() <= 0) {
            return false;
        }
        return true;
    }

    public GalleryListAdapter.SelectableType isItemSelectable(int i2) {
        MediaItem mediaItem;
        ListViewHolder viewHolderForAdapterPosition = getViewHolderForAdapterPosition(i2);
        if (viewHolderForAdapterPosition != null) {
            mediaItem = viewHolderForAdapterPosition.getMediaItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem == null || !isEnableAlbum(mediaItem) || mediaItem.isFolder()) {
            return GalleryListAdapter.SelectableType.UNSUPPORTED;
        }
        int selectableMaxCount = getSelectableMaxCount();
        if (this.mSelectionManager.isSelected(Integer.valueOf(i2)) || selectableMaxCount <= 0 || getSelectedItemCount() != selectableMaxCount) {
            return GalleryListAdapter.SelectableType.SUPPORT;
        }
        return GalleryListAdapter.SelectableType.REACHED_MAX_COUNT;
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        MediaItem read;
        super.onSelected(i2, z, z3);
        if (z && (read = this.mMediaData.read(getMediaItemPosition(i2))) != null && read.isMergedAlbum()) {
            this.mMergedAlbumIds.put(Integer.valueOf(read.getAlbumID()), (List) Arrays.stream(read.getAlbumsInFolder()).map(new a(7)).collect(Collectors.toList()));
        }
    }

    public void restoreClipboard(Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet<Integer> linkedHashSet, LinkedHashSet<Long> linkedHashSet2) {
        ArrayList<MediaItem> fullData;
        if (PreferenceFeatures.OneUi6x.SUPPORT_PICKER_PRESELECTED) {
            MediaData mediaData = ((IAlbumFolderPicker) this.mView).getMediaData((String) null);
            if (mediaData != null && (fullData = mediaData.getFullData()) != null && !fullData.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Iterator<MediaItem> it = fullData.iterator();
                while (it.hasNext()) {
                    loadAlbumIdList(it.next(), arrayList);
                }
                restoreClipboardWithCursorDataIdList((String[]) arrayList.toArray(new String[0]), runnable, timeTickLog, linkedHashSet, linkedHashSet2);
                return;
            }
            return;
        }
        super.restoreClipboard(runnable, timeTickLog, linkedHashSet, linkedHashSet2);
    }

    public boolean supportHeader() {
        return ((IAlbumFolderPicker) this.mView).isFlipWidget();
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        if (!supportHeader() || !isHeader(i2)) {
            super.onBindViewHolder(listViewHolder, i2);
        } else {
            listViewHolder.itemView.post(new c(22, this, listViewHolder));
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        super.onBindViewHolder(listViewHolder, i2, list);
        if (!((IAlbumFolderPicker) this.mView).isFlipWidgetWithCoverScreen()) {
            return;
        }
        if (isHeader(i2)) {
            ViewUtils.setTextColor(((AlbumFolderPickerHeaderViewHolder) listViewHolder).getHeaderDescriptionView(), R.color.white_color);
            return;
        }
        ViewUtils.setTextColor(listViewHolder.getTitleView(), R.color.album_folder_picker_title_text_color_for_widget);
        ViewUtils.setTextColor(listViewHolder.getSubTitleView(), R.color.album_folder_picker_sub_title_text_color_for_widget);
    }
}
