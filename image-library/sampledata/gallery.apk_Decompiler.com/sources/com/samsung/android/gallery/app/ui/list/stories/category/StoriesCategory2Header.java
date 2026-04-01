package com.samsung.android.gallery.app.ui.list.stories.category;

import T3.e;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.app.ui.list.stories.header.SelectionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCategory2Header {
    StoriesCategory2HeaderAdapter mAdapter;
    SelectionHelper<Integer> mHeaderSelectionHelper = new SelectionHelper<>();
    private final View.OnLayoutChangeListener mLayoutChanged = new h(this);
    GalleryListView mListView;
    TextView mMainCount;
    View mOthersHeaderLayout;
    View mParent;
    IStoriesCategory2View mView;

    public StoriesCategory2Header(IStoriesCategory2View iStoriesCategory2View, ViewGroup viewGroup) {
        this.mView = iStoriesCategory2View;
        this.mParent = viewGroup;
        GalleryListView galleryListView = (GalleryListView) viewGroup.findViewById(R.id.my_recycler_view);
        this.mListView = galleryListView;
        galleryListView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.NONE);
        StoriesCategory2HeaderAdapter storiesCategory2HeaderAdapter = new StoriesCategory2HeaderAdapter(this.mView);
        this.mAdapter = storiesCategory2HeaderAdapter;
        this.mListView.setAdapter(storiesCategory2HeaderAdapter);
        registerLayoutListener();
    }

    private void clearSelection() {
        this.mHeaderSelectionHelper.clear();
    }

    private void clearView() {
        this.mListView.removeAllViews();
        this.mListView.removeAllCachedViews();
        ViewUtils.removeSelf(this.mOthersHeaderLayout);
        this.mOthersHeaderLayout = null;
    }

    private void execute(Consumer<StoriesCatBaseViewHolder> consumer) {
        Optional.ofNullable(getList()).ifPresent(new i(0, consumer));
    }

    private void invalidateLayout() {
        this.mAdapter.invalidateLayout(new g(0, this));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$execute$2(Consumer consumer, GalleryListView galleryListView) {
        int childCount = galleryListView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            Optional.ofNullable((StoriesCatBaseViewHolder) galleryListView.findContainingViewHolder(galleryListView.getChildAt(i2))).ifPresent(consumer);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invalidateLayout$1() {
        ItemProperty.reset(getList().getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLayoutChanged$0() {
        onHandleInternalEvent(InternalEvent.HEADER_SIZE_CHANGED, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onLayoutChanged(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (i13 - i11 != i8 - i2) {
            this.mParent.post(new g(2, this));
        }
    }

    private void registerLayoutListener() {
        this.mParent.addOnLayoutChangeListener(this.mLayoutChanged);
    }

    private void unregisterLayoutListener() {
        this.mParent.removeOnLayoutChangeListener(this.mLayoutChanged);
    }

    public List<GalleryListView> getAllList() {
        ArrayList arrayList = new ArrayList();
        GalleryListView list = getList();
        if (list != null) {
            int childCount = list.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                StoriesCatBaseViewHolder storiesCatBaseViewHolder = (StoriesCatBaseViewHolder) list.findContainingViewHolder(list.getChildAt(i2));
                if (storiesCatBaseViewHolder != null && (storiesCatBaseViewHolder.getListView() instanceof GalleryListView)) {
                    arrayList.add((GalleryListView) storiesCatBaseViewHolder.getListView());
                }
            }
        }
        return arrayList;
    }

    public SelectionHelper<Integer> getHeaderSelectionHelper() {
        return this.mHeaderSelectionHelper;
    }

    public GalleryListView getList() {
        return this.mListView;
    }

    public int getSelectableTotalItemCount() {
        return this.mAdapter.getSelectableTotalItemCount();
    }

    public int getSelectedCount() {
        return this.mHeaderSelectionHelper.getSize();
    }

    public ArrayList<MediaItem> getSelectedItems() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        onHandleInternalEvent(InternalEvent.SELECTED_ITEMS, arrayList);
        return arrayList;
    }

    public void handleDensityChange(int i2) {
        clearView();
        this.mAdapter.handleDensityChange();
        this.mListView.post(new g(1, this));
        invalidateLayout();
    }

    public void handleMultiWindowModeChanged(boolean z) {
        invalidateLayout();
    }

    public void handleResolutionChange(int i2) {
        invalidateLayout();
    }

    public void onDataChangedOnUi() {
        this.mAdapter.onDataChangedOnUi();
    }

    public void onDestroy() {
        this.mAdapter.destroy();
        unregisterLayoutListener();
        execute(new e(16));
    }

    public void onHandleInternalEvent(InternalEvent internalEvent, Object... objArr) {
        if (internalEvent == InternalEvent.SELECT_MODE_CHANGE) {
            if (objArr == null || objArr.length <= 0 || !objArr[0].booleanValue()) {
                clearSelection();
            }
            this.mAdapter.onHandleInternalEvent(internalEvent, objArr);
            return;
        }
        if (internalEvent == InternalEvent.UPDATE_BADGE) {
            StoriesCategory2HeaderAdapter storiesCategory2HeaderAdapter = this.mAdapter;
            storiesCategory2HeaderAdapter.notifyItemRangeChanged(0, storiesCategory2HeaderAdapter.getItemCount(), "PAYLOAD_UPDATE_BADGE");
        }
        this.mAdapter.onHandleInternalEvent(internalEvent, objArr);
    }

    public void onHeaderCatItemSelect(MediaItem mediaItem) {
        int storyId = MediaItemStory.getStoryId(mediaItem);
        if (this.mHeaderSelectionHelper.isSelected(Integer.valueOf(storyId))) {
            this.mHeaderSelectionHelper.remove(Integer.valueOf(storyId));
        } else {
            this.mHeaderSelectionHelper.add(Integer.valueOf(storyId));
        }
    }

    public void onPause() {
        execute(new e(14));
    }

    public void onResume() {
        execute(new e(13));
    }

    public void onStop() {
        execute(new e(15));
    }

    public void updateDivider() {
        boolean z = false;
        if (this.mOthersHeaderLayout == null || this.mMainCount == null) {
            View inflate = LayoutInflater.from(this.mParent.getContext()).inflate(R.layout.stories_category_item_others_header_layout, (ViewGroup) null);
            this.mOthersHeaderLayout = inflate;
            ((ViewGroup) this.mParent).addView(inflate);
            this.mMainCount = (TextView) this.mOthersHeaderLayout.findViewById(R.id.item_header_count);
            ((TextView) this.mOthersHeaderLayout.findViewById(R.id.item_header_name)).setText(R.string.other_stories);
            ViewUtils.setVisibleOrGone(this.mOthersHeaderLayout.findViewById(R.id.item_arrow), false);
        }
        int dataCount = this.mView.getDataCount();
        this.mMainCount.setText(String.valueOf(dataCount));
        View view = this.mOthersHeaderLayout;
        if (dataCount > 0) {
            z = true;
        }
        ViewUtils.setVisibleOrGone(view, z);
    }
}
