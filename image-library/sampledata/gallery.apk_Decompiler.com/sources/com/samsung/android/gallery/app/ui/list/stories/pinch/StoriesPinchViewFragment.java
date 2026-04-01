package com.samsung.android.gallery.app.ui.list.stories.pinch;

import A4.C0366a;
import A4.L;
import Bb.l;
import F6.a;
import F6.b;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.StoriesFragment;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseLayoutManager;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.list.stories.header.BasePinView;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinView61;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelper;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelperV2;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinchViewFragment<V extends IStoriesPinchView, P extends StoriesPinchViewPresenter> extends StoriesFragment<V, P> implements IStoriesPinchView {
    private DimenHelper mDimenHelper;
    protected ViewGroup mHeaderView;
    private SimpleAutoScroller mPinAutoScroller;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class LayoutUpdater implements ILayoutUpdater {
        public LayoutUpdater() {
        }

        public int getPinchDepth(int i2) {
            return StoriesPinchViewFragment.this.getDepthFromGridSize(i2);
        }

        public void gridChanged(int i2, int i7) {
            StoriesPinchViewFragment.this.onGridChanged(i2, i7);
        }

        public void updateDecoView(View view, int i2, int i7) {
            ListViewHolder listViewHolder;
            GalleryListView listView = StoriesPinchViewFragment.this.getListView();
            if (listView != null && (listViewHolder = (ListViewHolder) listView.findViewHolderForAdapterPosition(i2)) != null) {
                listViewHolder.updateDecoration(SerializeOptions.SORT, Integer.valueOf(StoriesPinchViewFragment.this.getDepthFromGridSize(i7)), Boolean.valueOf(StoriesPinchViewFragment.this.getLayoutManager().hintDrawerOpened(i7)));
            }
        }
    }

    private String getEmptyViewLabel(boolean z) {
        if (z) {
            return "";
        }
        return getString(R.string.empty_set_description_no_events);
    }

    private String getGridDescription(int i2) {
        return AppResources.getQuantityString(R.plurals.grid_view_with_thumbnails_in_each_row, i2, Integer.valueOf(i2));
    }

    /* access modifiers changed from: private */
    public void handePinEvent(int i2) {
        if (i2 == 0) {
            Optional.ofNullable(getAdapter()).ifPresent(new l(22));
        } else if (i2 == 1) {
            invalidateToolbar();
        } else if (i2 == 2) {
            updateEmptyView(this.mEmptyView);
        }
    }

    private ViewGroup inflateHeaderView() {
        return (ViewGroup) getLayoutInflater().inflate(getHeaderLayoutId(), (ViewGroup) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(BasePinView basePinView) {
        basePinView.setEventListener(new a(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPinAutoScroll$2() {
        Optional.ofNullable(getToolbar()).ifPresent(new C0366a(0));
        this.mPinAutoScroller = null;
    }

    /* access modifiers changed from: private */
    public void onGridChanged(int i2, int i7) {
        if (i2 != i7 && !isTabletDpi()) {
            savePinchDepth(i2);
            if (getLocationKey() != null && getLocationKey().equals(this.mBlackboard.read("location://variable/currentv1"))) {
                SeApiCompat.announceVoiceAssistant(getContext(), getGridDescription(i2));
            }
        }
        if (!useCustomScrollbar()) {
            resetScrollBar();
        }
    }

    private void resetLayout() {
        DimenHelper dimenHelper = this.mDimenHelper;
        if (dimenHelper != null) {
            dimenHelper.reset();
        }
        Optional.ofNullable(getAdapter()).ifPresent(new l(20));
    }

    private void resetScrollBar() {
        Optional.ofNullable(getListView()).ifPresent(new l(21));
    }

    private void startPinAutoScroll(int i2) {
        RecyclerView recyclerView;
        int i7 = i2 - 100000;
        if (getPinView() != null) {
            recyclerView = getPinView().getListView();
        } else {
            recyclerView = null;
        }
        this.mRecyclerView.scrollToPosition(0, false);
        SimpleAutoScroller withFinishAction = new SimpleAutoScroller(this.mBlackboard, recyclerView, i7).setAppbar(this.mAppBarLayout).setThemeColor(getThemeColor()).withStartAction(new b(this, 0)).withFinishAction(new b(this, 1));
        this.mPinAutoScroller = withFinishAction;
        withFinishAction.start();
    }

    private void updateEmptyView(View view) {
        ViewGroup viewGroup;
        if (supportPinView() && (viewGroup = this.mHeaderView) != null && view != null) {
            boolean z = true;
            ViewUtils.setVisibleOrGone(viewGroup.findViewById(R.id.text_divider), !isEmptyViewShowing());
            if (isEmptyViewShowing()) {
                ViewGroup viewGroup2 = (ViewGroup) view.findViewById(R.id.no_item_view_container);
                if (viewGroup2 != this.mHeaderView.getParent()) {
                    ViewUtils.removeSelf(this.mHeaderView);
                    viewGroup2.addView(this.mHeaderView, 0, new ViewGroup.LayoutParams(-1, -2));
                    ViewMarginUtils.setMargin(this.mHeaderView, 0);
                    ViewMarginUtils.setPadding(this.mHeaderView.findViewById(R.id.pin_list), 0);
                }
                MediaData childMediaData = this.mMediaData.getChildMediaData((String) null);
                if (childMediaData == null || childMediaData.getCount() <= 0) {
                    z = false;
                }
                ((NoItemView) view.findViewById(R.id.no_item_view)).setLabel(getEmptyViewLabel(z));
                Log.d(this.TAG, "emptyView is visible", Boolean.valueOf(z));
            }
        }
    }

    public void addHeaderSubView(ViewGroup viewGroup) {
        if (supportPinView()) {
            viewGroup.addView(getStoriesPinRootView(), 0);
        }
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        StorySharedTransitionHelper.addStoryAlbumTransition(this.mBlackboard, listViewHolder, mediaItem);
    }

    public DimenHelper createDimenHelper(Context context) {
        return new DimenHelperV2(context.getResources());
    }

    public View createHeaderView() {
        if (this.mHeaderView == null) {
            ViewGroup inflateHeaderView = inflateHeaderView();
            this.mHeaderView = inflateHeaderView;
            addHeaderSubView(inflateHeaderView);
        }
        return this.mHeaderView;
    }

    public StoriesPinchViewFragment<V, P>.LayoutUpdater createLayoutUpdater() {
        return new LayoutUpdater();
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new StoriesPinchViewAdapter(this, getLocationKey(), createHeaderView());
    }

    public BasePinView<IStoriesView> createStoriesPinView() {
        if (supportPinSelection()) {
            return new StoriesPinView61(this);
        }
        return super.createStoriesPinView();
    }

    public boolean delayScreenMode() {
        return true;
    }

    public DimenHelper getDimenHelper(Context context) {
        if (this.mDimenHelper == null) {
            this.mDimenHelper = createDimenHelper(context);
        }
        return this.mDimenHelper;
    }

    public String getFragmentTag(String str) {
        return "StoriesPinchViewFragment";
    }

    public int getHeaderLayoutId() {
        return R.layout.stories_pinch_header_divider_layout_v2;
    }

    public int getLayoutId() {
        return R.layout.fragment_stories_pinch_layout;
    }

    public String getLocationWithExtraArgs() {
        if (!PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE || !LocationKey.isStories(getLocationKey())) {
            return super.getLocationWithExtraArgs();
        }
        return ArgumentsUtil.appendArgs(getLocationKey(), "stories_cursor_cache", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    public int[] getPinchColumn() {
        return getPinchColumn(isDrawerOpen());
    }

    public int getPreferenceDefault() {
        return PreferenceFeatures.OneUi7x.STORY_ONE_UI_70 ^ true ? 1 : 0;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.STORIES_GRID_SIZE;
    }

    public int getSelectedCountForToolbar(boolean z) {
        int i2;
        int selectedCountForToolbar = super.getSelectedCountForToolbar(z);
        if (!supportPinSelection()) {
            return selectedCountForToolbar;
        }
        BasePinView<IStoriesView> basePinView = this.mStoriesPinView;
        if (basePinView != null) {
            i2 = basePinView.getSelectCount();
        } else {
            i2 = 0;
        }
        return selectedCountForToolbar + i2;
    }

    public int getSelectedItemCount() {
        int i2;
        int selectedItemCount = super.getSelectedItemCount();
        if (!supportPinSelection()) {
            return selectedItemCount;
        }
        BasePinView<IStoriesView> basePinView = this.mStoriesPinView;
        if (basePinView != null) {
            i2 = basePinView.getSelectCount();
        } else {
            i2 = 0;
        }
        return selectedItemCount + i2;
    }

    public MediaItem[] getSelectedItems() {
        BasePinView<IStoriesView> basePinView;
        MediaItem[] selectedItems = super.getSelectedItems();
        if (supportPinSelection() && (basePinView = this.mStoriesPinView) != null) {
            ArrayList<MediaItem> selectedItems2 = basePinView.getSelectedItems();
            if (!selectedItems2.isEmpty()) {
                ArrayList arrayList = new ArrayList(Arrays.asList(selectedItems));
                arrayList.addAll(selectedItems2);
                return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
            }
        }
        return selectedItems;
    }

    public int getStartPinchDepth() {
        if (isTabletDpi()) {
            return 0;
        }
        return loadPinchDepth();
    }

    public int getTotalSelectableCount() {
        int i2;
        int totalSelectableCount = super.getTotalSelectableCount();
        if (!supportPinSelection()) {
            return totalSelectableCount;
        }
        BasePinView<IStoriesView> basePinView = this.mStoriesPinView;
        if (basePinView != null) {
            i2 = basePinView.getDataCount();
        } else {
            i2 = 0;
        }
        return totalSelectableCount + i2;
    }

    public void handleDensityChange(int i2) {
        resetLayout();
        super.handleDensityChange(i2);
    }

    public void handleResolutionChange(int i2) {
        resetLayout();
        super.handleResolutionChange(i2);
    }

    public boolean hasData() {
        BasePinView<IStoriesView> basePinView;
        if (getDataCount() > 0) {
            return true;
        }
        if (!supportPinSelection() || (basePinView = this.mStoriesPinView) == null || basePinView.getDataCount() <= 0) {
            return false;
        }
        return true;
    }

    public void initView(View view) {
        super.initView(view);
        Optional.ofNullable(this.mStoriesPinView).ifPresent(new a(this, 0));
    }

    public boolean isReturnToTransitory(int i2) {
        if (i2 >= 100000) {
            return true;
        }
        return false;
    }

    public int[] loadPinchColumnResource() {
        return GridHelper.getInstance(getLocationKey()).getGridArray(getContext());
    }

    public int[] loadSplitPinchColumnResource() {
        return GridHelper.getInstance(getLocationKey()).getSplitGridArray(getContext());
    }

    public boolean onBackPressed() {
        SimpleAutoScroller simpleAutoScroller = this.mPinAutoScroller;
        if (simpleAutoScroller != null) {
            simpleAutoScroller.cancel();
            this.mPinAutoScroller = null;
        }
        return super.onBackPressed();
    }

    public void onDestroy() {
        super.onDestroy();
        ViewGroup viewGroup = this.mHeaderView;
        if (viewGroup != null) {
            ViewUtils.removeAllViews(viewGroup);
            this.mHeaderView = null;
        }
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        updateEmptyView(view);
    }

    public void onListItemFavoriteClick(View view, MediaItem mediaItem, int i2) {
        ((StoriesPinchViewPresenter) this.mPresenter).onListItemFavoriteClickInternal(view, mediaItem, i2);
    }

    public void onReturnTransitionEndV2() {
        this.mBlackboard.erase("data://story_transition_view_radius");
        this.mBlackboard.erase("data://story_transition_return_position");
    }

    public void onSelectAll() {
        BasePinView<IStoriesView> basePinView;
        if (supportPinSelection() && (basePinView = this.mStoriesPinView) != null) {
            basePinView.onSelectAll();
        }
        super.onSelectAll();
    }

    public void onSelectionModeChanged(boolean z) {
        super.onSelectionModeChanged(z);
        Optional.ofNullable(this.mStoriesPinView).ifPresent(new L(z, 4));
    }

    public void onUnSelectAll() {
        BasePinView<IStoriesView> basePinView;
        if (supportPinSelection() && (basePinView = this.mStoriesPinView) != null) {
            basePinView.onUnselectAll();
        }
        super.onUnSelectAll();
    }

    public void selectAll() {
        onSelectAll();
    }

    public void startDecoAnimationForReturn() {
        View view;
        if (!isDestroyed()) {
            int i2 = -1;
            int intValue = ((Integer) this.mBlackboard.read("data://story_transition_return_position", -1)).intValue();
            if (isReturnToTransitory(intValue)) {
                this.mBlackboard.publish("data://story_transition_view_radius", new int[]{0, getResources().getDimensionPixelOffset(R.dimen.stories_view_pin_item_round_radius)});
                return;
            }
            GalleryListView listView = getListView();
            if (getAdapter() != null) {
                i2 = getAdapter().getViewPosition(intValue);
            }
            ListViewHolder listViewHolder = (ListViewHolder) listView.findViewHolderForAdapterPosition(i2);
            if (listViewHolder != null) {
                view = listViewHolder.getDecoView(11);
            } else {
                view = null;
            }
            if (intValue >= 0) {
                this.mBlackboard.publish("data://story_transition_view_radius", new int[]{0, ((StoriesPinchViewPresenter) this.mPresenter).getTransitionViewRadius(this)});
            }
            if (view != null) {
                Log.d(this.TAG, "startDecoAnimationForReturn", Integer.valueOf(i2));
                ViewUtils.setAlpha(view, 0.0f);
                view.animate().alpha(1.0f).setStartDelay(300).setDuration(150).start();
            }
        }
    }

    public void startShrinkAnimation() {
        super.startShrinkAnimation();
        startDecoAnimationForReturn();
    }

    public void startSimpleAutoScroller(int i2) {
        if (isReturnToTransitory(i2)) {
            startPinAutoScroll(i2);
        } else {
            super.startSimpleAutoScroller(i2);
        }
    }

    public boolean supportPinSelection() {
        return supportPinView();
    }

    public boolean supportShrinkTransition() {
        return true;
    }

    public void updateFavorite(int i2, int i7, boolean z) {
        StoriesPinchViewAdapter storiesPinchViewAdapter = (StoriesPinchViewAdapter) getAdapter();
        if (storiesPinchViewAdapter != null) {
            if (getListView().getItemAnimator() instanceof SimpleItemAnimator) {
                ((SimpleItemAnimator) getListView().getItemAnimator()).setSupportsChangeAnimations(false);
            }
            storiesPinchViewAdapter.updateFavorite(i2, i7, z);
        }
    }

    public void updateLayout() {
        ((StoriesBaseLayoutManager) getLayoutManager()).initDimen(getContext());
    }

    public boolean useCustomScrollbar() {
        return true;
    }

    public GalleryGridLayoutManager createLayoutManager() {
        GalleryListView listView = getListView();
        Context context = listView.getContext();
        return new StoriesPinchLayoutManagerV2(context, createLayoutUpdater(), listView.getColumnCount(), getDimenHelper(context));
    }

    public StoriesPinchViewPresenter createPresenter(IStoriesPinchView iStoriesPinchView) {
        return new StoriesPinchViewPresenter(this.mBlackboard, iStoriesPinchView);
    }

    public GalleryGridLayoutManager getLayoutManager() {
        if (this.mLayoutManager == null) {
            this.mLayoutManager = createLayoutManager();
        }
        return (GalleryGridLayoutManager) this.mLayoutManager;
    }

    public StoriesPinchAnimationManager getPinchAnimationManager() {
        return new StoriesPinchAnimationManager((PinchLayoutManager) this.mLayoutManager, (GridInfo.ClusterInfo) null, true);
    }

    public int[] getPinchColumn(boolean z) {
        return z ? this.mSplitPinchColumnArray : this.mPinchColumnArray;
    }

    public void setScreenMode() {
    }
}
