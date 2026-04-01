package com.samsung.android.gallery.app.ui.list.stories.pictures;

import G6.a;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.story.StartHighlightPlayerCmd;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IViewCache;
import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.CoverFactory;
import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StoryCover;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeader;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.StoryRelatedView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.animations.IThemeColor;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.InterceptTouchListener;
import com.samsung.android.gallery.widget.listview.TouchParentLayout;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import n4.C0489a;
import o6.p;
import v7.w;
import y6.C0541a;
import y6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesFragment<V extends IStoryPicturesView, P extends StoryPicturesPresenter> extends PicturesFragment<V, P> implements IStoryPicturesView {
    /* access modifiers changed from: private */
    public boolean mIsLayoutAnimDone;
    private StoryRelatedView<IStoryPicturesView> mRelatedView;
    protected TouchParentLayout mRootLayout;
    private StoryCover<IStoryPicturesView> mStoryCover;
    private StoryHeader mStoryHeader;
    protected ViewGroup mStoryLayout;
    private IThemeColor mThemeColor;
    private StoryPicturesFragment<V, P>.ItemTouchCallBack mTouchCallBack;
    private final IViewCache mViewCache = new IViewCache() {
        public View loadOrCreate(int i2) {
            return StoryPicturesFragment.this.loadOrCreateView(i2);
        }

        public void restore(int i2, View view) {
            StoryPicturesFragment.this.restoreLayout(i2, view);
        }
    };

    private void bindHeaderView() {
        StoryHeader storyHeader = getStoryHeader();
        MediaItem headerItem = ((StoryPicturesPresenter) this.mPresenter).getHeaderItem();
        if (headerItem == null || isEqualItem(headerItem, storyHeader.getHeaderItem())) {
            Log.d(this.TAG, "header item is not ready yet");
        } else {
            storyHeader.bind(headerItem);
        }
    }

    /* access modifiers changed from: private */
    public StoryHeader getStoryHeader() {
        if (this.mStoryHeader == null) {
            this.mStoryHeader = new StoryHeader(this, this.mViewCache);
        }
        return this.mStoryHeader;
    }

    /* access modifiers changed from: private */
    public void initRelatedView() {
        if (this.mRelatedView == null && getContext() != null && isListPrepared()) {
            this.mRelatedView = createRelatedView();
        }
    }

    private boolean isListPrepared() {
        if (isDestroyed() || getAdapter() == null || !isLayoutAnimDone()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleResolutionChange$4(StoryCover storyCover) {
        storyCover.handleResolutionChange(getResources().getConfiguration());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(StoryCover storyCover) {
        storyCover.onBindView(getHeaderItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1() {
        Optional.ofNullable(this.mStoryCover).ifPresent(new C0541a(this, 1));
    }

    /* access modifiers changed from: private */
    public void onCoverClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (isSelectionMode()) {
            return;
        }
        if (PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW) {
            new StartHighlightPlayerCmd().execute(this.mPresenter, Integer.valueOf(MediaItemStory.getStoryId(mediaItem)));
            return;
        }
        ImageView image = listViewHolder.getImage();
        if (image != null) {
            String str = "SpotifyCover#" + mediaItem.getFileId();
            image.setVisibility(0);
            this.mDelegate.bindImage(listViewHolder, mediaItem, false);
            SharedTransition.addSharedElement(this.mBlackboard, image, str, new TransitionInfo(mediaItem, listViewHolder.getBitmap(), str));
        }
        ((StoryPicturesPresenter) this.mPresenter).moveStorySpotifySlideShow();
    }

    /* access modifiers changed from: private */
    public void requestInitRelatedView() {
        if (isActivityBusy()) {
            ThreadUtil.postOnUiThread(new b(this, 1));
        } else {
            initRelatedView();
        }
    }

    private void startLayoutAnimation() {
        if (!supportEnterScrollUpVI()) {
            StoryHeader storyHeader = getStoryHeader();
            Objects.requireNonNull(storyHeader);
            storyHeader.setLayoutAnimationDone();
            return;
        }
        this.mStoryLayout.scheduleLayoutAnimation();
        this.mStoryLayout.setLayoutAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                StoryPicturesFragment.this.mIsLayoutAnimDone = true;
                StoryHeader Y = StoryPicturesFragment.this.getStoryHeader();
                Objects.requireNonNull(Y);
                Y.setLayoutAnimationDone();
                Log.d(StoryPicturesFragment.this.TAG, "LayoutAnimation finished");
                StoryPicturesFragment.this.requestInitRelatedView();
            }
        });
    }

    private boolean supportEnterScrollUpVI() {
        return true;
    }

    private void updateDimen(Context context) {
        if (context != null) {
            ((StoryPicturesLayoutManager) getLayoutManager()).initDimen(context);
            ((StoryPicturesLayoutManager) getLayoutManager()).updateRatioSpacing(StoryHelper.getSpacingByRatio(context));
        }
    }

    private void updateHeaderItems() {
        getStoryHeader().updateHeaderItems();
    }

    private void updateLayout() {
        if (getContext() != null) {
            updateDimen(getContext());
            StoryRelatedView<IStoryPicturesView> storyRelatedView = this.mRelatedView;
            if (storyRelatedView != null) {
                storyRelatedView.updateView(getContext());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (r1 != null) goto L_0x002e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addSharedTransition(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r4, com.samsung.android.gallery.module.data.MediaItem r5, int r6, boolean r7) {
        /*
            r3 = this;
            com.samsung.android.gallery.support.utils.PreferenceFeatures r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.SuggestedEffectOnStory
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.isEnabled(r0)
            if (r0 == 0) goto L_0x0049
            android.widget.ImageView r7 = r4.getImage()
            if (r7 == 0) goto L_0x0048
            r0 = 0
            com.samsung.android.gallery.widget.utils.ViewUtils.setVisibility(r7, r0)
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r1 = r3.mListAdapter
            if (r1 == 0) goto L_0x002d
            java.lang.String r1 = com.samsung.android.gallery.module.data.EffectItemBuilder.getEffectType(r5)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x002d
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r1 = r3.mListAdapter
            int r2 = r1.getViewPosition(r6)
            com.samsung.android.gallery.module.data.MediaItem r1 = r1.getMediaItemSync(r2)
            if (r1 == 0) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r1 = r5
        L_0x002e:
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate r2 = r3.mDelegate
            if (r5 == r1) goto L_0x0033
            r0 = 1
        L_0x0033:
            r2.bindImage(r4, r1, r0)
            com.samsung.android.gallery.support.blackboard.Blackboard r3 = r3.mBlackboard
            java.lang.String r5 = com.samsung.android.gallery.widget.abstraction.SharedTransition.getTransitionName(r1)
            com.samsung.android.gallery.widget.abstraction.TransitionInfo r0 = new com.samsung.android.gallery.widget.abstraction.TransitionInfo
            android.graphics.Bitmap r4 = r4.getBitmap()
            r0.<init>((com.samsung.android.gallery.module.data.MediaItem) r1, (android.graphics.Bitmap) r4, (int) r6)
            com.samsung.android.gallery.widget.abstraction.SharedTransition.addSharedElement(r3, r7, r5, r0)
        L_0x0048:
            return
        L_0x0049:
            super.addSharedTransition(r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesFragment.addSharedTransition(com.samsung.android.gallery.widget.listviewholder.ListViewHolder, com.samsung.android.gallery.module.data.MediaItem, int, boolean):void");
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mStoryLayout = (ViewGroup) view.findViewById(R.id.story_layout);
        this.mRootLayout = (TouchParentLayout) view.findViewById(R.id.story_root_layout);
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.setClipToPadding(false);
            listView.setClipChildren(false);
        }
    }

    public StoryRelatedView<IStoryPicturesView> createRelatedView() {
        return new StoryRelatedView<>(this, getContext(), getHeaderItem());
    }

    public GridLayoutManager.SpanSizeLookup createSpanSizeLookUp(GridLayoutManager gridLayoutManager) {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int i2) {
                StoryPicturesViewAdapter adapter = StoryPicturesFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getSpanSize(i2);
                }
                return 1;
            }
        };
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount()) {
            public PicturesViewHolderFactory createViewHolderFactory(Context context) {
                return new StoryPicturesViewHolderFactory(context);
            }
        };
    }

    public MediaItem getHeaderItem() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((StoryPicturesPresenter) p6).getHeaderItem();
        }
        return null;
    }

    public int getLayoutId() {
        return R.layout.fragment_story_pictures_layout;
    }

    public PreviewViewHolder getPreviewableViewHolder() {
        StoryCover<IStoryPicturesView> storyCover = this.mStoryCover;
        if (storyCover != null) {
            return storyCover.getPreviewableViewHolder();
        }
        return null;
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_EVENT_DETAIL_VIEW_SELECTION.toString();
        }
        return AnalyticsScreenId.SCREEN_EVENT_DETAIL_VIEW_NORMAL.toString();
    }

    public ArrayList<View> getSharedViewList(Blackboard blackboard) {
        return (ArrayList) blackboard.pop("data://shared_view/story");
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public MediaItem getStoryAlbumById(int i2) {
        if (!isDestroyed()) {
            return ((StoryPicturesPresenter) this.mPresenter).getStoryAlbumById(i2);
        }
        return null;
    }

    public IThemeColor getThemeColor() {
        if (this.mThemeColor == null) {
            this.mThemeColor = new IThemeColor() {
            };
        }
        return this.mThemeColor;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        StoryHeader storyHeader = this.mStoryHeader;
        if (storyHeader != null) {
            storyHeader.destroy();
            this.mStoryHeader = null;
            bindHeaderView();
            BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
            if (baseListViewAdapter != null) {
                baseListViewAdapter.setHeaderView(getStoryHeader().getView());
            }
        }
        Optional.ofNullable(this.mStoryCover).ifPresent(new C0489a(i2, 20));
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        StoryHeader storyHeader = this.mStoryHeader;
        if (storyHeader != null) {
            storyHeader.handleOrientationChange(i2);
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        updateLayout();
        Optional.ofNullable(this.mStoryCover).ifPresent(new C0541a(this, 0));
    }

    public void initCoverView() {
        if (this.mStoryCover == null) {
            this.mStoryCover = CoverFactory.create(this, this.mViewCache);
        }
        this.mStoryCover.initCoverView();
        this.mStoryCover.setCoverClickListener(new p(26, this));
    }

    public void initView(View view) {
        super.initView(view);
        initCoverView();
        bindHeaderView();
        startLayoutAnimation();
    }

    public void initializeAdapter() {
        super.initializeAdapter();
        requestInitRelatedView();
    }

    public boolean isEqualItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || !((StoryPicturesPresenter) this.mPresenter).equalsItem(mediaItem, mediaItem2)) {
            return false;
        }
        return true;
    }

    public boolean isLayoutAnimDone() {
        if (!supportEnterScrollUpVI() || this.mIsLayoutAnimDone) {
            return true;
        }
        return false;
    }

    public boolean isOptionMenuOpened() {
        return ((StoryPicturesPresenter) this.mPresenter).isOptionViewOpened();
    }

    public int[] loadPinchColumnResource() {
        return getResources().getIntArray(R.array.story_pictures_column_count);
    }

    public void notifyFooterChanged(View view) {
        StoryPicturesViewAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.setFooterView(view);
            adapter.notifyItemChanged(adapter.getFooterViewPosition());
        }
    }

    public void notifyStoriesDataChanged() {
        if (this.mRelatedView != null && getHeaderItem() != null) {
            this.mRelatedView.loadData(getHeaderItem());
        }
    }

    public boolean onBackPressed() {
        if (((StoryPicturesPresenter) this.mPresenter).onBackPressed()) {
            return true;
        }
        return super.onBackPressed();
    }

    public void onBindView(View view) {
        super.onBindView(view);
        if (this.mTouchCallBack == null) {
            StoryPicturesFragment<V, P>.ItemTouchCallBack itemTouchCallBack = new ItemTouchCallBack();
            this.mTouchCallBack = itemTouchCallBack;
            new ItemTouchHelper(itemTouchCallBack).attachToRecyclerView(getListView());
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ((StoryPicturesPresenter) this.mPresenter).onConfigurationChanged();
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        Optional.ofNullable(this.mStoryCover).ifPresent(new w(16));
    }

    public void onDestroy() {
        super.onDestroy();
        StoryCover<IStoryPicturesView> storyCover = this.mStoryCover;
        if (storyCover != null) {
            storyCover.destroy();
            this.mStoryCover = null;
        }
        StoryHeader storyHeader = this.mStoryHeader;
        if (storyHeader != null) {
            storyHeader.destroy();
            this.mStoryHeader = null;
        }
        StoryRelatedView<IStoryPicturesView> storyRelatedView = this.mRelatedView;
        if (storyRelatedView != null) {
            storyRelatedView.destroy();
            this.mRelatedView = null;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        Optional.ofNullable(this.mStoryCover).ifPresent(new w(19));
    }

    public boolean onHandleOptionMenu(ListViewHolder listViewHolder, int i2) {
        return ((StoryPicturesPresenter) this.mPresenter).onHandleOptionMenu(listViewHolder, i2);
    }

    public void onListItemSecondaryClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, PointF pointF) {
        if (setInputBlock(this.TAG + "_onListItemSecondaryClick")) {
            createPopupMenu((ViewGroup) getListView().getParent(), new PointF(listViewHolder.getRootView().getX() + pointF.x, listViewHolder.getRootView().getY() + pointF.y), mediaItem);
        }
    }

    public void onOptionSaveSelected(ListViewHolder listViewHolder) {
        ((StoryPicturesPresenter) this.mPresenter).onOptionSaveSelected(listViewHolder);
    }

    public void onOptionShareSelected(ListViewHolder listViewHolder) {
        ((StoryPicturesPresenter) this.mPresenter).onOptionShareSelected(listViewHolder);
    }

    public void onPause() {
        super.onPause();
        StoryHeader storyHeader = this.mStoryHeader;
        if (storyHeader != null) {
            storyHeader.pause();
        }
        Optional.ofNullable(this.mStoryCover).ifPresent(new w(15));
    }

    public void onPrepareSharedTransitionV2() {
        SharedTransition.onPrepareOthers(this.mBlackboard, this);
    }

    public void onRelatedItemClick(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ((StoryPicturesPresenter) this.mPresenter).onRelatedItemClick(mediaItem);
    }

    public void onResume() {
        super.onResume();
        Optional.ofNullable(this.mStoryHeader).ifPresent(new w(17));
        Optional.ofNullable(this.mStoryCover).ifPresent(new w(18));
    }

    public void onStart() {
        super.onStart();
        updateDimen(getContext());
    }

    public void onTagViewSelected(MediaItem mediaItem) {
        ((StoryPicturesPresenter) this.mPresenter).moveToSearchView(mediaItem);
    }

    public void onViewCreated(View view, Bundle bundle) {
        String str;
        boolean isViewReady = isViewReady();
        super.onViewCreated(view, bundle);
        if (isViewReady && getHeaderItem() != null && getHeaderItem().isHeif()) {
            ThreadUtil.postponeOnUiThread(new b(this, 0));
        }
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("onViewCreated ");
        if (getHeaderItem() == null) {
            str = "headerItem is null";
        } else {
            str = "storyId {" + MediaItemStory.getStoryId(getHeaderItem()) + "} storyCategoryType {" + MediaItemStory.getStoryCategoryType(getHeaderItem()) + "} storyType {" + MediaItemStory.getStoryType(getHeaderItem()) + "}";
        }
        sb2.append(str);
        Log.d(stringCompat, sb2.toString());
    }

    public void setCustomAnimations(FragmentTransaction fragmentTransaction, IBaseFragment iBaseFragment) {
        if (iBaseFragment != null && iBaseFragment.supportExitDefaultTransition()) {
            fragmentTransaction.setCustomAnimations(0, 0, R.anim.depth_out_exit, 0);
        }
    }

    public void setEnabledHeader(boolean z) {
        float f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        StoryHeader storyHeader = this.mStoryHeader;
        if (storyHeader != null) {
            storyHeader.setEnableHeader(f, z);
        }
        StoryRelatedView<IStoryPicturesView> storyRelatedView = this.mRelatedView;
        if (storyRelatedView != null) {
            storyRelatedView.setEnabled(f, z);
        }
    }

    public boolean supportDeleteAnimation() {
        return false;
    }

    public boolean supportRealRatio() {
        return true;
    }

    public boolean supportTimeline() {
        return true;
    }

    public void updateHeader(MediaItem mediaItem) {
        if (isDestroyed()) {
            Log.e(this.TAG, "Fragment is already detached");
            return;
        }
        if (getToolbar() != null) {
            ((StoryPicturesPresenter) this.mPresenter).updateToolbar(getToolbar());
        }
        bindHeaderView();
        Optional.ofNullable(this.mStoryCover).ifPresent(new a(mediaItem, 18));
    }

    public void updateHeaderContents(MediaItem mediaItem) {
        if (isDestroyed()) {
            Log.e(this.TAG, "Fragment is already detached");
        } else {
            updateHeaderItems();
        }
    }

    public boolean useCustomScrollbar() {
        return false;
    }

    public PicturesLayoutManager createLayoutManager() {
        StoryPicturesLayoutManager storyPicturesLayoutManager = new StoryPicturesLayoutManager(getListView(), getColumnCount());
        storyPicturesLayoutManager.setSpanSizeLookup(createSpanSizeLookUp(storyPicturesLayoutManager));
        return storyPicturesLayoutManager;
    }

    public StoryPicturesViewAdapter<IStoryPicturesView> createListViewAdapter(GalleryListView galleryListView) {
        return new StoryPicturesViewAdapter<>(this, getLocationKey(), getStoryHeader().getView(), true);
    }

    public StoryPicturesPresenter<IStoryPicturesView> createPresenter(IStoryPicturesView iStoryPicturesView) {
        return new StoryPicturesPresenter<>(this.mBlackboard, iStoryPicturesView);
    }

    public StoryPicturesViewAdapter<IStoryPicturesView> getAdapter() {
        return (StoryPicturesViewAdapter) this.mListAdapter;
    }

    public void onTransitionEnd() {
    }

    public void updateAppbarSelectionCount(int i2, int i7) {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ItemTouchCallBack extends ItemTouchHelper.SimpleCallback {
        public ItemTouchCallBack() {
            super(0, 12);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$onSwiped$0(MotionEvent motionEvent) {
            return ((StoryPicturesPresenter) StoryPicturesFragment.this.mPresenter).handleTouchEvent(StoryPicturesFragment.this.mRootLayout, motionEvent);
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (ViewHolderValue.isHeader(viewHolder.getItemViewType()) || ViewHolderValue.isFooter(viewHolder.getItemViewType())) {
                return 0;
            }
            return super.getMovementFlags(recyclerView, viewHolder);
        }

        public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
            return 0.1f;
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return false;
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
            boolean z;
            StringCompat access$200 = StoryPicturesFragment.this.TAG;
            StringBuilder sb2 = new StringBuilder(" onSwiped direction open?");
            boolean z3 = false;
            if (i2 == 4) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            Log.d(access$200, sb2.toString());
            StoryPicturesPresenter storyPicturesPresenter = (StoryPicturesPresenter) StoryPicturesFragment.this.mPresenter;
            ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
            if (i2 == 4) {
                z3 = true;
            }
            storyPicturesPresenter.onOptionViewSwiped(listViewHolder, z3);
            if (i2 == 4) {
                StoryPicturesFragment.this.mRootLayout.setListener(new a(this));
            } else {
                StoryPicturesFragment.this.mRootLayout.setListener((InterceptTouchListener) null);
            }
        }

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f5, int i2, boolean z) {
        }
    }
}
