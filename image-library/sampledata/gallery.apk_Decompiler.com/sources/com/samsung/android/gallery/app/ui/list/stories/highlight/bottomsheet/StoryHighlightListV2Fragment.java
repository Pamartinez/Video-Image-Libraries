package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import A4.C0376k;
import D5.c;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.fragment.app.Fragment;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.IStoryHighlightListV2View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Presenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.abstraction.Mode;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.AutoScroller;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.ReorderHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomSheetDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animations.IThemeColor;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;
import g6.g;
import java.util.Objects;
import java.util.Optional;
import k6.b;
import k6.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightListV2Fragment<V extends IStoryHighlightListV2View, P extends StoryHighlightListV2Presenter> extends PicturesFragment<V, P> implements IStoryHighlightListV2View {
    private int mBottomBarPadding;
    private Callback mCallback;
    private EventHandler mEventHandler;
    private ReorderHandler mReorderHandler;
    private View mRootLayout;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
    }

    public StoryHighlightListV2Fragment(String str, Callback callback) {
        setLocationKey(str);
        this.mCallback = callback;
    }

    private void createReorderHandler() {
        if (this.mReorderHandler == null) {
            ReorderHandler reorderHandler = new ReorderHandler(getListView(), new d(this, 0));
            this.mReorderHandler = reorderHandler;
            reorderHandler.setReorderListener(new ReorderCallback(this));
            this.mReorderHandler.setAutoScroller(new AutoScroller(this));
            if (getAdapter() != null) {
                getAdapter().setReorderPosSupplier(new d(this, 1));
            }
        }
    }

    private int getListViewBottomPadding() {
        return getResources().getDimensionPixelOffset(R.dimen.story_highlight_list_expend_offset) + getVerticalInsets();
    }

    private int getVerticalInsets() {
        Rect systemInsets = WindowUtils.getSystemInsets(this.mRecyclerView.getRootWindowInsets());
        return systemInsets.top + systemInsets.bottom;
    }

    private boolean isBottomBarMode() {
        StoryHighlightListV2Adapter adapter = getAdapter();
        if (adapter == null || adapter.isCurationEditMode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createLayoutManager$1(View view) {
        Optional.ofNullable(getAdapter()).ifPresent(new c(view, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$createReorderHandler$3() {
        boolean z = true;
        if (getSelectedItemCount() != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$createReorderHandler$4() {
        return Integer.valueOf(this.mReorderHandler.getReorderingPosition());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleResolutionChange$2() {
        adjustBottomBarMargin(this.mRecyclerView);
    }

    private void removeBasicToolbarTopPadding() {
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.seslSetUserTopPadding(0);
        }
    }

    private boolean supportReorder() {
        return PocFeatures.isEnabled(PocFeatures.StoryContentsReorder);
    }

    private void tryReorder(ListViewHolder listViewHolder) {
        if (supportReorder() && !isSelectionMode()) {
            createReorderHandler();
            this.mReorderHandler.tryReorder(listViewHolder);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mRootLayout = view.findViewById(R.id.story_root_layout);
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new DummyDragAndDropDelegate();
    }

    public SimpleAutoScroller createSimpleAutoScroller(int i2) {
        return super.createSimpleAutoScroller(i2);
    }

    public String getDataLocationKey() {
        return getLocationWithExtraArgs();
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount()) {
            public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
                return new StoryHighlightListV2ViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_story_highlight_list_v2_layout, viewGroup, false), i2);
            }
        };
    }

    public EventHandler getEventHandler() {
        return this.mEventHandler;
    }

    public int getLayoutId() {
        return R.layout.fragment_story_highlight_list_v2_layout;
    }

    public String getLocationWithExtraArgs() {
        return getLocationKey().replace("location://story/albums/storyHighlight", "location://story/albums/storyHighlightList");
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return new StoryHighlightListAnimationManager((PinchLayoutManager) this.mLayoutManager, createClusterInfo(), supportPivotOnFingerPos());
    }

    public int getPreferenceDefault() {
        return 1;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.STORY_HIGHLIGHT_LIST_GRID_SIZE;
    }

    public String getScreenId() {
        StoryHighlightListV2Adapter adapter = getAdapter();
        if (adapter != null) {
            if (Mode.EDIT_CURATION.equals(adapter.getMode())) {
                return AnalyticsScreenId.SCREEN_STORY_HIGHLIGHT_MANAGE_CONTENTS.toString();
            }
            if (Mode.REMOVE.equals(adapter.getMode()) || Mode.SELECT.equals(adapter.getMode())) {
                return AnalyticsScreenId.SCREEN_STORY_HIGHLIGHT_LIST_EDIT.toString();
            }
        }
        return AnalyticsScreenId.SCREEN_STORY_HIGHLIGHT_LIST.toString();
    }

    public int getStartPinchDepth() {
        return loadPinchDepth();
    }

    public IThemeColor getThemeColor() {
        return new IThemeColor() {
        };
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        ThreadUtil.postOnUiThreadDelayed(new b(0, this), 100);
    }

    public void initView(View view) {
        super.initView(view);
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setBackgroundResource(R.drawable.story_highlight_list_actionbar_background);
            ViewUtils.setVisibleOrGone(this.mToolbar, true);
        }
        ViewMarginUtils.setBottomPadding(this.mRootLayout, getListViewBottomPadding());
    }

    public void invalidateOptionsMenu() {
        if (!isDestroyed()) {
            ((StoryHighlightListV2Presenter) this.mPresenter).invalidateOptionsMenu();
        }
    }

    public void invalidateSelectionToolbar() {
        updateSelectionToolBar();
    }

    public void invalidateToolbar() {
        StoryHighlightListV2Presenter storyHighlightListV2Presenter = (StoryHighlightListV2Presenter) this.mPresenter;
        if (storyHighlightListV2Presenter != null) {
            if (isTouchOngoing()) {
                getListView().addOnTouchUpListener(new C0376k(1, storyHighlightListV2Presenter));
            } else {
                storyHighlightListV2Presenter.invalidateOptionsMenu();
            }
            updateToolbarSelectionCount(getToolbar());
        }
    }

    public boolean needToRegisterInsetListener() {
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        ViewMarginUtils.setPadding(view, 0);
        adjustBottomBarMargin(view);
        ViewMarginUtils.setBottomPadding(this.mRootLayout, getListViewBottomPadding());
        return windowInsets;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        removeBasicToolbarTopPadding();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCallback = null;
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            super.onGridChanged(i2, i7);
        }
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_LIST_SELECT);
        Callback callback = this.mCallback;
        if (callback != null) {
            ((BottomSheetDelegate) ((a) callback).e).moveToHighlight(i2);
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        tryReorder(listViewHolder);
        return super.onListItemLongClick(listViewHolder, i2, mediaItem);
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            ViewMarginUtils.setBottomPadding(this.mRootLayout, getListViewBottomPadding());
        }
    }

    public void onSheetSlide(float f) {
        boolean z;
        if (isSelectionMode()) {
            int i2 = 0;
            if (f < 1.0f) {
                z = true;
            } else {
                z = false;
            }
            View bottomBar = getBottomBar();
            boolean isVisible = ViewUtils.isVisible(bottomBar);
            if (z) {
                if (isVisible) {
                    getBlackboard().post("command://HideBottomBar", Boolean.FALSE);
                }
            } else if (!isVisible && getSelectedItemCount() > 0) {
                getBlackboard().post("command://ShowBottomBar", Boolean.FALSE);
                if (isBottomBarMode()) {
                    i2 = ViewUtils.getHeight(bottomBar);
                }
                updateListViewBottomPadding(i2);
            }
        }
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.mEventHandler = eventHandler;
    }

    public void startPostponedEnterTransition() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            parentFragment.startPostponedEnterTransition();
        } else {
            super.startPostponedEnterTransition();
        }
    }

    public void startPostponedEnterTransitionV2() {
        MvpBaseFragment mvpBaseFragment = (MvpBaseFragment) getParentFragment();
        if (mvpBaseFragment != null) {
            mvpBaseFragment.startPostponedEnterTransitionV2();
        } else {
            super.startPostponedEnterTransitionV2();
        }
    }

    public boolean supportHeader() {
        return false;
    }

    public void updateListViewBottomPadding(int i2) {
        this.mBottomBarPadding = i2;
        ViewMarginUtils.setBottomPadding(this.mRootLayout, getListViewBottomPadding());
        ViewMarginUtils.setBottomPadding(this.mRecyclerView, this.mBottomBarPadding);
        this.mRecyclerView.setClipToPadding(false);
        adjustBottomBarMargin(this.mRootLayout);
    }

    public void updateSelectionToolBar() {
        StoryHighlightListV2Adapter adapter = getAdapter();
        if (adapter == null || !adapter.isCurationEditMode() || adapter.isCurationEditPrepared()) {
            super.updateSelectionToolBar();
        }
    }

    public void updateToolbarSelectionCount(GalleryToolbar galleryToolbar) {
        if (getToolbar() != null) {
            getToolbar().disableLayoutTransition();
        }
        super.updateToolbarSelectionCount(galleryToolbar);
    }

    public boolean useParentToolbar() {
        return true;
    }

    public PicturesLayoutManager createLayoutManager() {
        AnonymousClass2 r0 = new StoryHighlightListLayoutManager(getListView(), getColumnCount()) {
            public int getSpacing(int i2) {
                return StoryHighlightListV2Fragment.this.getGridSpacing(i2);
            }

            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                Log.d(StoryHighlightListV2Fragment.this.tag(), A.a.d(spanCount, i2, "setSpanCount {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
                int access$000 = StoryHighlightListV2Fragment.this.getOldGridSize(i2, spanCount);
                StoryHighlightListV2Fragment.this.updateAdapter(i2, access$000);
                StoryHighlightListV2Fragment.this.onGridChanged(i2, access$000);
            }
        };
        r0.setSpanSizeLookup(createSpanSizeLookUp(r0));
        r0.setAttachCallback(new g(12, this));
        return r0;
    }

    public StoryHighlightListV2Adapter createListViewAdapter(GalleryListView galleryListView) {
        String locationKey = getLocationKey();
        StoryHighlightListV2Presenter storyHighlightListV2Presenter = (StoryHighlightListV2Presenter) this.mPresenter;
        Objects.requireNonNull(storyHighlightListV2Presenter);
        return new StoryHighlightListV2Adapter(this, locationKey, new k6.c(storyHighlightListV2Presenter, 0));
    }

    public StoryHighlightListV2Presenter<IStoryHighlightListV2View> createPresenter(IStoryHighlightListV2View iStoryHighlightListV2View) {
        return new StoryHighlightListV2Presenter<>(this.mBlackboard, iStoryHighlightListV2View);
    }

    public StoryHighlightListV2Adapter<IStoryHighlightListV2View> getAdapter() {
        return (StoryHighlightListV2Adapter) super.getAdapter();
    }

    public void onPrepareSharedTransitionV2() {
    }

    public void postAnalyticsLog() {
    }

    public void setScreenMode() {
    }
}
