package com.samsung.android.gallery.app.ui.list.stories.category;

import A4.J;
import F6.f;
import K.a;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.list.stories.category.IStoriesCategory2View;
import com.samsung.android.gallery.app.ui.list.stories.category.StoriesCategory2Presenter;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.header.SelectionHelper;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchLayoutManagerV2;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewFragment;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelper;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelperV70;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelperV85;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.animations.IThemeColor;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCategory2Fragment<V extends IStoriesCategory2View, P extends StoriesCategory2Presenter> extends StoriesPinchViewFragment<V, P> implements IStoriesCategory2View {
    private static final boolean STORIES_TOP_COLOR_EFFECT = PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect);
    StoriesCategory2Header mHeader;
    private final CategoryTransition<IStoriesCategory2View> mTransition = new CategoryTransition<>(this);

    public StoriesCategory2Fragment() {
        setLocationKey("location://story/albums");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onCreate$0() {
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSimpleAutoScroller$1(int i2, Boolean bool) {
        if (!bool.booleanValue()) {
            super.startSimpleAutoScroller(i2);
            this.mBlackboard.erase("data://story_transition_return_position");
        }
    }

    private void updateDivider() {
        Optional.ofNullable(this.mHeader).ifPresent(new c(4));
    }

    public void addHeaderSubView(ViewGroup viewGroup) {
        this.mHeader = new StoriesCategory2Header(this, viewGroup);
        ((StoriesCategory2Presenter) this.mPresenter).onHeaderCreated();
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        StorySharedTransitionHelper.addStoryAlbumTransition(this.mBlackboard, listViewHolder, mediaItem);
    }

    public DimenHelper createDimenHelper(Context context) {
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            return new DimenHelperV85(context.getResources());
        }
        return new DimenHelperV70(context.getResources());
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new StoriesCategory2Adapter(this, getLocationKey(), createHeaderView());
    }

    public boolean delayScreenMode() {
        return false;
    }

    public String getFragmentTag(String str) {
        return "StoriesCategory2Fragment";
    }

    public StoriesCategory2Header getHeader() {
        return this.mHeader;
    }

    public int getHeaderLayoutId() {
        return R.layout.stories_header_categories_layout;
    }

    public SelectionHelper<Integer> getHeaderSelectionHelper() {
        StoriesCategory2Header storiesCategory2Header = this.mHeader;
        if (storiesCategory2Header != null) {
            return storiesCategory2Header.getHeaderSelectionHelper();
        }
        return null;
    }

    public int getLayoutId() {
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_80) {
            return R.layout.fragment_stories_category2_v8_layout;
        }
        return R.layout.fragment_stories_category2_layout;
    }

    public String getLocationWithExtraArgs() {
        if (PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE) {
            return ArgumentsUtil.appendArgs(getLocationKey(), "stories_cursor_cache", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        return super.getLocationWithExtraArgs();
    }

    public MediaData getMediaData(String str) {
        if ("location://story/albums".equals(str)) {
            return this.mMediaData;
        }
        return this.mMediaData.getChildMediaData(str);
    }

    public int getSelectedCountForToolbar(boolean z) {
        int selectedCountForToolbar = super.getSelectedCountForToolbar(z);
        StoriesCategory2Header storiesCategory2Header = this.mHeader;
        if (storiesCategory2Header != null) {
            return selectedCountForToolbar + storiesCategory2Header.getSelectedCount();
        }
        return selectedCountForToolbar;
    }

    public int getSelectedItemCount() {
        int selectedItemCount = super.getSelectedItemCount();
        StoriesCategory2Header storiesCategory2Header = this.mHeader;
        if (storiesCategory2Header != null) {
            return selectedItemCount + storiesCategory2Header.getSelectedCount();
        }
        return selectedItemCount;
    }

    public MediaItem[] getSelectedItems() {
        MediaItem[] selectedItems = super.getSelectedItems();
        StoriesCategory2Header storiesCategory2Header = this.mHeader;
        if (storiesCategory2Header != null) {
            ArrayList<MediaItem> selectedItems2 = storiesCategory2Header.getSelectedItems();
            if (!selectedItems2.isEmpty()) {
                ArrayList arrayList = new ArrayList(Arrays.asList(selectedItems));
                arrayList.addAll(selectedItems2);
                return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
            }
        }
        return selectedItems;
    }

    public IThemeColor getThemeColor() {
        if (STORIES_TOP_COLOR_EFFECT) {
            return new a(28);
        }
        return null;
    }

    public int getTotalSelectableCount() {
        int i2;
        int totalSelectableCount = super.getTotalSelectableCount();
        StoriesCategory2Header storiesCategory2Header = this.mHeader;
        if (storiesCategory2Header != null) {
            i2 = storiesCategory2Header.getSelectableTotalItemCount();
        } else {
            i2 = 0;
        }
        return totalSelectableCount + i2;
    }

    public int getTransitionRadius() {
        return ((StoriesCategory2Presenter) this.mPresenter).getTransitionViewRadius(this);
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        Optional.ofNullable(this.mHeader).ifPresent(new d(i2, 0));
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        Optional.ofNullable((StoriesCategory2Presenter) this.mPresenter).ifPresent(new c(2));
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        Optional.ofNullable(this.mHeader).ifPresent(new d(i2, 1));
        Optional.ofNullable((StoriesCategory2Presenter) this.mPresenter).ifPresent(new c(8));
    }

    public void internalEvent(InternalEvent internalEvent, Object... objArr) {
        P p6 = this.mPresenter;
        if (p6 == null || !((StoriesCategory2Presenter) p6).onHandleInternalEvent(internalEvent, objArr)) {
            Optional.ofNullable(this.mHeader).ifPresent(new e(internalEvent, objArr));
        }
    }

    public boolean isImmediateTransitionRequired() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() != 0 || this.mMediaData.getChildCount() != 0 || isResumed()) {
            return false;
        }
        return true;
    }

    public void onAppbarVisibleRatio(float f) {
        super.onAppbarVisibleRatio(f);
        ((StoriesCategory2Presenter) this.mPresenter).onAppbarVisibleRatio(f);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mTransition.onAttach();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSystemUi.setCustomHasNoStatusBar(new f(9));
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        updateDivider();
        Optional.ofNullable(this.mHeader).ifPresent(new c(3));
    }

    public void onDestroy() {
        Optional.ofNullable(this.mHeader).ifPresent(new c(1));
        super.onDestroy();
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (!((StoriesCategory2Presenter) this.mPresenter).handleCategoryItemClick(listViewHolder, i2, mediaItem, i7)) {
            super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if (((StoriesCategory2Presenter) this.mPresenter).handleCategoryItemLongClick(listViewHolder, i2, mediaItem) || super.onListItemLongClick(listViewHolder, i2, mediaItem)) {
            return true;
        }
        return false;
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        Optional.ofNullable(this.mHeader).ifPresent(new f(z, 0));
        Optional.ofNullable((StoriesCategory2Presenter) this.mPresenter).ifPresent(new c(9));
    }

    public void onPause() {
        super.onPause();
        Optional.ofNullable(this.mHeader).ifPresent(new c(0));
    }

    public void onResume() {
        super.onResume();
        Optional.ofNullable(this.mHeader).ifPresent(new c(6));
    }

    public void onSelectAll() {
        internalEvent(InternalEvent.SELECT_ALL, Boolean.TRUE);
        super.onSelectAll();
    }

    public void onSelectionModeChanged(boolean z) {
        super.onSelectionModeChanged(z);
        internalEvent(InternalEvent.SELECT_MODE_CHANGE, Boolean.valueOf(z));
    }

    public void onStop() {
        super.onStop();
        Optional.ofNullable(this.mHeader).ifPresent(new c(7));
    }

    public void onUnSelectAll() {
        internalEvent(InternalEvent.SELECT_ALL, Boolean.FALSE);
        super.onUnSelectAll();
    }

    public void requestPreview() {
        Optional.ofNullable((StoriesCategory2Presenter) this.mPresenter).ifPresent(new c(5));
    }

    public void setScreenMode() {
        enterFullListScreen(false);
    }

    public void startDecoAnimationForReturn() {
        this.mTransition.startDecoAnimationForReturn();
    }

    public final void startShrinkAnimation() {
        this.mTransition.startShrinkAnimation();
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
            startDecoAnimationForReturn();
            return;
        }
        super.startShrinkAnimation();
    }

    public void startSimpleAutoScroller(int i2) {
        this.mTransition.startSimpleAutoScroller(i2, new J((Object) this, i2, 5));
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportPinView() {
        return false;
    }

    public boolean supportShrinkTransition() {
        return LocationKey.isStoryHighlight(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard));
    }

    public boolean supportTabLayout() {
        return !PreferenceFeatures.OneUi8x.COLLECTION_TAB;
    }

    public void updateExtraStartPadding(float f) {
        internalEvent(InternalEvent.UPDATE_EXTRA_PADDING, Float.valueOf(f));
    }

    public GalleryGridLayoutManager createLayoutManager() {
        GalleryListView listView = getListView();
        Context context = listView.getContext();
        return new StoriesPinchLayoutManagerV2<IStoriesPinchView>(context, createLayoutUpdater(), listView.getColumnCount(), getDimenHelper(context)) {
            public int getHintColumnSpan(int i2, int i7) {
                if (this.mListAdapter.getItemViewType(i2) == 0) {
                    return 1;
                }
                return i7;
            }
        };
    }

    public StoriesCategory2Presenter createPresenter(IStoriesCategory2View iStoriesCategory2View) {
        return new StoriesCategory2Presenter(getBlackboard(), iStoriesCategory2View);
    }

    public StoriesCategory2Adapter getAdapter() {
        return (StoriesCategory2Adapter) super.getAdapter();
    }
}
