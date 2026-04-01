package com.samsung.android.gallery.app.ui.list.abstraction;

import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Lazy;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.DragSelectTouchListener;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsListViewAdapter<V extends IBaseListView> extends GalleryListAdapter<ListViewHolder> {
    private static final Runnable DRAG_UPDATER = new Object();
    /* access modifiers changed from: protected */
    public GalleryListView mGalleryListView;
    protected Lazy<HoverHandler> mHoverHandler = new Lazy<>(new e(1, this));
    private final ListViewHolder.OnHoverListener mOnHoverListener = new l(this);
    final View.OnLayoutChangeListener mOnLayoutChangeListener;
    private int mPrevAppbarOffset = Integer.MAX_VALUE;
    final RecyclerView.OnScrollListener mScrollListener;
    final ListViewHolder.SelectListener mSelectListener;
    ThumbnailPreviewDelegate mThumbnailPreviewDelegate;
    /* access modifiers changed from: protected */
    public V mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimpleAppbarVisibleListener<V extends IBaseListView> implements DragSelectTouchListener.OnAppbarVisibleListener {
        V view;

        public SimpleAppbarVisibleListener(V v) {
            this.view = v;
        }

        public boolean isAppbarVisible() {
            GalleryAppBarLayout appbarLayout = this.view.getAppbarLayout();
            if (appbarLayout == null || !appbarLayout.isPartiallyVisible()) {
                return false;
            }
            return true;
        }

        public void setExpand(boolean z) {
            GalleryAppBarLayout appbarLayout = this.view.getAppbarLayout();
            if (appbarLayout != null) {
                appbarLayout.setExpanded(z);
            }
        }
    }

    public AbsListViewAdapter(V v) {
        super(v.getBlackboard());
        k kVar = new k(this);
        this.mOnLayoutChangeListener = kVar;
        AnonymousClass1 r1 = new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                if (i2 == 0) {
                    AbsListViewAdapter.this.onScrollStateIdle();
                } else if (i2 == 1) {
                    AbsListViewAdapter.this.onScrollStateDragging();
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
                AbsListViewAdapter.this.onListViewScrolled(recyclerView, i2, i7);
            }
        };
        this.mScrollListener = r1;
        this.mSelectListener = new ListViewHolder.SelectListener() {
            public int getSelectedCount() {
                return AbsListViewAdapter.this.getSelectedItemCount();
            }

            public boolean isListMoveMode() {
                V v = AbsListViewAdapter.this.mView;
                if (v == null || !v.isMoveMode()) {
                    return false;
                }
                return true;
            }

            public boolean isListSelectionMode() {
                return AbsListViewAdapter.this.isSelectionMode();
            }

            public boolean isListSingSelectionMode() {
                return AbsListViewAdapter.this.isSingleSelectionMode();
            }

            public void onTouchUp() {
                AbsListViewAdapter.this.getDragSelectTouchListener().setLongPressedAfterSelectionMode(false);
            }
        };
        setDragUpdater(DRAG_UPDATER);
        this.mView = v;
        GalleryListView listView = v.getListView();
        this.mGalleryListView = listView;
        listView.addOnLayoutChangeListener(kVar);
        this.mGalleryListView.addOnScrollListener(r1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ HoverHandler lambda$new$0() {
        return new HoverHandler(this.mView.getPresenter(), this.mBlackBoard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$2(ListViewHolder listViewHolder, MotionEvent motionEvent) {
        if (this.mView.isSelectionMode()) {
            return false;
        }
        onHoverInternal(listViewHolder, motionEvent);
        return false;
    }

    public void checkPreviewCandidate() {
        checkPreviewCandidate(0);
    }

    public ThumbnailPreview<?> createThumbnailPreview() {
        return new ThumbnailPreview<>(this.mView);
    }

    public void destroy() {
        if (!this.mHoverHandler.isEmpty()) {
            this.mHoverHandler.get().recycle();
            this.mHoverHandler.remove();
        }
        this.mGalleryListView.destroy();
        this.mGalleryListView.removeOnLayoutChangeListener(this.mOnLayoutChangeListener);
        this.mGalleryListView.removeOnScrollListener(this.mScrollListener);
        this.mGalleryListView = null;
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW) {
            stopPreview(false);
        }
        this.mView = null;
        super.destroy();
    }

    public final ArrayList<ListViewHolder> getAllVisibleViewHolders() {
        ArrayList<ListViewHolder> arrayList = new ArrayList<>();
        GalleryListView galleryListView = this.mGalleryListView;
        if (!(galleryListView == null || galleryListView.getLayoutManager() == null)) {
            int findLastVisibleItemPositionCompat = galleryListView.findLastVisibleItemPositionCompat();
            for (int findFirstVisibleItemPositionCompat = galleryListView.findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= findLastVisibleItemPositionCompat; findFirstVisibleItemPositionCompat++) {
                RecyclerView.ViewHolder findViewHolderForLayoutPosition = galleryListView.findViewHolderForLayoutPosition(findFirstVisibleItemPositionCompat);
                if (findViewHolderForLayoutPosition instanceof ListViewHolder) {
                    arrayList.add((ListViewHolder) findViewHolderForLayoutPosition);
                }
            }
        }
        return arrayList;
    }

    public boolean isDexMode() {
        return this.mView.isDexMode();
    }

    public boolean isPreviewAvailable() {
        return false;
    }

    public void onAppbarOffsetChanged(int i2) {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && isPreviewAvailable() && this.mPrevAppbarOffset != i2) {
            this.mPrevAppbarOffset = i2;
            checkPreviewCandidate(600);
        }
    }

    public void onDataChanged() {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && isPreviewAvailable()) {
            stopPreview(false);
            checkPreviewCandidate();
        }
    }

    public abstract void onLayoutChanged(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14);

    public void onListViewScrolled(RecyclerView recyclerView, int i2, int i7) {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && isPreviewAvailable()) {
            checkPreviewCandidate();
        }
    }

    public void onScrollStateDragging() {
        if (supportHover()) {
            this.mHoverHandler.get().removeHoverPreview();
        }
    }

    public void onScrollStateIdle() {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && isPreviewAvailable()) {
            checkPreviewCandidate();
        }
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        ThumbnailPreviewDelegate thumbnailPreviewDelegate;
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && supportVideoPreview() && isPreviewAvailable() && (thumbnailPreviewDelegate = this.mThumbnailPreviewDelegate) != null && (listViewHolder instanceof PreviewViewHolder)) {
            thumbnailPreviewDelegate.onPreviewHolderRecycled((PreviewViewHolder) listViewHolder);
        }
        super.onViewRecycled(listViewHolder);
    }

    public void setListeners(ListViewHolder listViewHolder) {
        super.setListeners(listViewHolder);
        if (supportHover() && PickerUtil.isNormalLaunchMode(this.mBlackBoard) && !DeviceInfo.isDexMode(getContext())) {
            listViewHolder.setOnHoverListener(this.mOnHoverListener);
        }
        if (listViewHolder.useSelectListener()) {
            listViewHolder.setSelectListener(this.mSelectListener);
        }
    }

    public void startDragSelection(int i2) {
        int i7;
        super.startDragSelection(i2);
        try {
            int i8 = 0;
            if (this.mView.getToolbar() != null) {
                i7 = this.mView.getToolbar().getHeight();
            } else {
                i7 = 0;
            }
            int bottomTabHeight = this.mView.getBottomTabHeight();
            if (i7 > 0 || bottomTabHeight > 0) {
                i8 = 1;
            }
            int systemInsetsTop = WindowUtils.getSystemInsetsTop(this.mView.getListView().getRootWindowInsets());
            int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(this.mView.getListView().getRootWindowInsets());
            if (systemInsetsTop > 0 || systemInsetsBottom > 0) {
                i7 += systemInsetsTop;
                bottomTabHeight += systemInsetsBottom;
                i8++;
            }
            getDragSelectTouchListener().withTopOffset(i7).withBottomOffset(bottomTabHeight).withMaxScrollVelocityLevel(i8).withOnAppbarVisibleListener(new SimpleAppbarVisibleListener(this.mView));
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
        }
    }

    public void stopPreview(boolean z) {
        ThumbnailPreviewDelegate thumbnailPreviewDelegate;
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && supportVideoPreview() && (thumbnailPreviewDelegate = this.mThumbnailPreviewDelegate) != null) {
            thumbnailPreviewDelegate.stopAllPreview(z);
        }
    }

    public boolean supportHover() {
        return false;
    }

    public boolean supportVideoPreview() {
        return true;
    }

    public final void checkPreviewCandidate(long j2) {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && supportVideoPreview()) {
            V v = this.mView;
            if (v == null || !v.isViewResumed() || !isPreviewAvailable()) {
                stopPreview(false);
                return;
            }
            if (this.mThumbnailPreviewDelegate == null) {
                this.mThumbnailPreviewDelegate = new ThumbnailPreviewDelegate(createThumbnailPreview());
            }
            if (j2 > 0) {
                this.mThumbnailPreviewDelegate.checkCandidateDelayed(j2);
            } else {
                this.mThumbnailPreviewDelegate.checkCandidate();
            }
        }
    }

    public void onHoverInternal(ListViewHolder listViewHolder, MotionEvent motionEvent) {
    }
}
