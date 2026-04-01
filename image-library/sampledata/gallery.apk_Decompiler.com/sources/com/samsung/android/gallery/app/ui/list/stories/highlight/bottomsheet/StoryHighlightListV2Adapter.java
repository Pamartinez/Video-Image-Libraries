package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import Ad.C0720a;
import G6.a;
import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.IStoryHighlightListV2View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.abstraction.Mode;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.abstraction.SelectInfoSupplier;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightListV2Adapter<V extends IStoryHighlightListV2View> extends PicturesViewAdapter<V> implements SelectInfoSupplier {
    private final AtomicBoolean mCurationEditPrepared = new AtomicBoolean(false);
    private Mode mMode = Mode.CURATION;
    private Supplier<Integer> mReorderPosSupplier;
    private final Consumer<Boolean> mSelectCallback;
    private SelectableChecker<MediaItem> mSelectableChecker;

    public StoryHighlightListV2Adapter(V v, String str, Consumer<Boolean> consumer) {
        super(v, str, false);
        this.mSelectCallback = consumer;
        setHasStableIds(true);
    }

    private SelectableChecker<MediaItem> createSelectableChecker() {
        return new SelectableChecker<MediaItem>() {
            public int getMaxCount() {
                return StoryHighlightListV2Adapter.this.getItemCount() - 1;
            }

            public boolean isSupported(MediaItem mediaItem) {
                return true;
            }

            public void showExceedMaxCountToast(Context context) {
                String str;
                int maxCount = getMaxCount();
                if (maxCount > 1) {
                    str = context.getString(R.string.max_size_reached, new Object[]{Integer.valueOf(maxCount)});
                } else {
                    str = context.getString(R.string.max_size_reached_for_one);
                }
                Utils.showToast(context, str);
            }
        };
    }

    private void disableLongClick(ListViewHolder listViewHolder) {
        listViewHolder.setOnItemLongClickListener((ListViewHolder.OnItemLongClickListener) null);
        listViewHolder.setOnSecondaryClickListener((ListViewHolder.OnItemSecondaryClickListener) null);
    }

    private int getListViewBottomPadding() {
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView == null) {
            return 0;
        }
        return galleryListView.getPaddingBottom();
    }

    private void initEditCurationItems() {
        ArrayList<MediaItem> fullData;
        if (isCurationEditMode() && (fullData = this.mMediaData.getFullData()) != null) {
            for (int i2 = 0; i2 < fullData.size(); i2++) {
                if (MediaItemStory.isUserCuration(fullData.get(i2))) {
                    selectItem(i2, true, false);
                }
            }
        }
    }

    private void initSelectableChecker() {
        if (this.mSelectableChecker == null) {
            this.mSelectableChecker = createSelectableChecker();
        }
        setSelectableChecker(this.mSelectableChecker);
    }

    private boolean isCurationData() {
        return "highlight_list_full".equals(ArgumentsUtil.getArgValue(this.mMediaData.getLocationReference(), "highlight_list_mode"));
    }

    private boolean supportLongClick() {
        return true;
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        MediaItem loadMediaItemSync;
        if (!this.mGalleryListView.isScrollIdle() || ViewHolderValue.isDivider(listViewHolder.getViewType()) || (loadMediaItemSync = loadMediaItemSync(getMediaItemPosition(i2, i7))) == null) {
            return super.bindViewHolderOnScrollIdle(listViewHolder, i2, i7);
        }
        listViewHolder.bind(loadMediaItemSync);
        listViewHolder.setImageUid(loadMediaItemSync.getPath());
        return bindImageOnScrollIdle(listViewHolder, loadMediaItemSync);
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new StoryHighlightListViewHolderFactory(context);
    }

    public MediaItem[] getHideItems() {
        ArrayList<MediaItem> fullData = this.mMediaData.getFullData();
        if (fullData == null) {
            return new MediaItem[0];
        }
        ArrayList arrayList = (ArrayList) fullData.stream().filter(new C0464a(8)).collect(Collectors.toCollection(new C0720a(1)));
        arrayList.removeAll(new ArrayList(Arrays.asList(((IStoryHighlightListV2View) this.mView).getSelectedItems())));
        return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
    }

    public long getItemId(int i2) {
        int i7;
        MediaItem mediaItemSync = getMediaItemSync(i2);
        if (mediaItemSync != null) {
            i7 = mediaItemSync.getComplexHashCode();
        } else {
            i7 = -i2;
        }
        return (long) i7;
    }

    public int getMaxScroll() {
        return super.getMaxScroll() + getListViewBottomPadding();
    }

    public Mode getMode() {
        return this.mMode;
    }

    public int getSelectedCount() {
        if (isSelectionMode()) {
            return this.mSelectionManager.getSelectedItemCount();
        }
        return 0;
    }

    public MediaItem[] getShowItems() {
        ArrayList<MediaItem> fullData = this.mMediaData.getFullData();
        if (fullData == null) {
            return new MediaItem[0];
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(((IStoryHighlightListV2View) this.mView).getSelectedItems()));
        arrayList.removeAll((ArrayList) fullData.stream().filter(new C0464a(8)).collect(Collectors.toCollection(new C0720a(1))));
        return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
    }

    public boolean isAllSelected() {
        if (getSelectedCount() == getDataCount()) {
            return true;
        }
        return false;
    }

    public boolean isCurationEditMode() {
        return Mode.EDIT_CURATION.equals(this.mMode);
    }

    public boolean isCurationEditPrepared() {
        return this.mCurationEditPrepared.get();
    }

    public boolean isMultiPick() {
        return true;
    }

    public boolean isSelectedEntireShowItems() {
        ArrayList<Integer> arrayList;
        ArrayList<MediaItem> fullData = this.mMediaData.getFullData();
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager != null) {
            arrayList = selectionManager.getSelectedList();
        } else {
            arrayList = null;
        }
        if (fullData == null) {
            fullData = new ArrayList<>();
        }
        if (arrayList != null) {
            arrayList.sort(Collections.reverseOrder());
            Iterator<Integer> it = arrayList.iterator();
            while (it.hasNext()) {
                fullData.remove(it.next().intValue());
            }
        }
        return fullData.stream().noneMatch(new C0464a(8));
    }

    public MediaItem loadMediaItemForShare(int i2) {
        MediaItem loadMediaItemForShare = super.loadMediaItemForShare(i2);
        if (MediaItemStory.isLiveEffectItem(loadMediaItemForShare)) {
            Optional.ofNullable(getContext()).ifPresent(new a(loadMediaItemForShare, 13));
        }
        return loadMediaItemForShare;
    }

    public void onDataChanged() {
        super.onDataChanged();
        if (!isCurationEditMode()) {
            this.mCurationEditPrepared.set(false);
        } else if (isCurationData() && !this.mCurationEditPrepared.get()) {
            initEditCurationItems();
            this.mCurationEditPrepared.set(true);
            ((IStoryHighlightListV2View) this.mView).invalidateSelectionToolbar();
            Log.d(this.TAG, "setInitialCurationItems");
        }
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        String str;
        super.onSelected(i2, z, z3);
        Consumer<Boolean> consumer = this.mSelectCallback;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(z));
        }
        if (isCurationEditPrepared() && Mode.EDIT_CURATION.equals(this.mMode)) {
            IStoryHighlightListV2View iStoryHighlightListV2View = (IStoryHighlightListV2View) this.mView;
            AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_STORY_MANAGE_CONTENTS_ITEM_SELECT;
            if (z) {
                str = AnalyticsDetail.EVENT_DETAIL_STORY_HIGHLIGHT_LIST_CURATION_EDIT_SELECT.toString();
            } else {
                str = AnalyticsDetail.EVENT_DETAIL_STORY_HIGHLIGHT_LIST_CURATION_EDIT_DESELECT.toString();
            }
            iStoryHighlightListV2View.postAnalyticsLog(analyticsEventId, str);
        }
    }

    public void onStartSelectMode() {
        super.onStartSelectMode();
        initSelectableChecker();
    }

    public boolean selectAll() {
        Consumer<Boolean> consumer;
        if (!super.selectAll() || (consumer = this.mSelectCallback) == null) {
            return true;
        }
        consumer.accept(Boolean.TRUE);
        return true;
    }

    public void setListeners(ListViewHolder listViewHolder) {
        super.setListeners(listViewHolder);
        if (!supportLongClick()) {
            disableLongClick(listViewHolder);
        }
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
        this.mCurationEditPrepared.set(false);
    }

    public void setReorderPosSupplier(Supplier<Integer> supplier) {
        this.mReorderPosSupplier = supplier;
    }

    public void setThumbnailShape(View view) {
        setThumbnailShape((ListViewHolder) this.mGalleryListView.findContainingViewHolder(view));
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2) {
        setViewHolderMargin(iPinchViewHolder, i2, GridDimenHelper.getMarginFromDepth(this.mGalleryListView, i2));
    }

    public boolean supportHover() {
        return false;
    }

    private void setThumbnailShape(ListViewHolder listViewHolder) {
        if (listViewHolder != null) {
            listViewHolder.setThumbnailShape(1, (float) GridDimenHelper.getRadiusFromDepth(this.mGalleryListView, getGridSize()));
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        setThumbnailShape(listViewHolder);
        super.onBindViewHolder(listViewHolder, i2);
        if (this.mReorderPosSupplier != null) {
            ViewUtils.setAlpha(listViewHolder.getDecoView(2), i2 == this.mReorderPosSupplier.get().intValue() ? 0.0f : 1.0f);
        }
    }
}
