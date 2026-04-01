package com.samsung.android.gallery.app.ui.list.albums.pictures;

import K5.a;
import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumsBaseView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPanePresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPaneFragment<V extends IAlbumsBaseView, P extends AlbumsPanePresenter<?>> extends AlbumsBaseFragment<V, P> {
    private boolean mEnableNestedScroll = true;
    private boolean mIsSplitMode;
    private MediaData mMediaDataPictures;
    private final MediaData.OnDataChangeListener mPicturesChangeListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            AlbumsPaneFragment.this.updateBorder();
        }

        public void onDataChanged() {
            ThreadUtil.postOnUiThread(new a(this));
        }
    };

    public AlbumsPaneFragment() {
        setLocationKey("location://albums/pane");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setNestedScroll$0(GalleryListView galleryListView) {
        galleryListView.setNestedScrollingEnabled(this.mEnableNestedScroll);
    }

    /* access modifiers changed from: private */
    public void updateBorder() {
        AlbumsBaseLayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null && this.mListAdapter != null) {
            int cacheViewCount = getListView().getCacheViewCount(layoutManager);
            int max = Math.max(0, layoutManager.findFirstVisibleItemPosition() - cacheViewCount);
            this.mListAdapter.notifyItemRangeChanged(max, (Math.min(this.mListAdapter.getItemCount(), layoutManager.findLastVisibleItemPosition() + cacheViewCount) - max) + 1, "album_changed");
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        updateFadingEdge();
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumsViewDummyAdapter(getListView()) {
            public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
                return new AlbumsPaneViewHolderFactory(context);
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_albums_pane_layout;
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        updateFadingEdge();
    }

    public void initializeScroll() {
        super.initializeScroll();
        if (this.mRecyclerView != null && getContext() != null) {
            this.mRecyclerView.setScrollerExtraPadding(getContext().getResources().getDimensionPixelOffset(R.dimen.albums_pane_scrollbar_extra_padding));
        }
    }

    public boolean isGridView() {
        return true;
    }

    public boolean isSplitMode() {
        return this.mIsSplitMode;
    }

    public int[] loadPinchColumnResource() {
        return new int[]{1};
    }

    public boolean needToRegisterInsetListener() {
        return false;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://albums/fileList", true);
        this.mMediaDataPictures = open;
        open.register(this.mPicturesChangeListener);
    }

    public void onDetach() {
        super.onDetach();
        this.mMediaDataPictures.unregister(this.mPicturesChangeListener);
        this.mMediaDataPictures.close();
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 1027) {
            return false;
        }
        super.onHandleEvent(eventMessage);
        return false;
    }

    public void setNestedScroll(boolean z) {
        if (this.mEnableNestedScroll != z) {
            this.mEnableNestedScroll = z;
            Optional.ofNullable(this.mRecyclerView).ifPresent(new a(8, this));
        }
    }

    public void setSplitMode(boolean z) {
        this.mIsSplitMode = z;
    }

    public boolean supportFastScroll() {
        return false;
    }

    public boolean supportSelection() {
        return false;
    }

    public void updateFadingEdge() {
        GalleryRecyclerView.FadingEdgeDirection fadingEdgeDirection;
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView != null) {
            if (isLandscape()) {
                fadingEdgeDirection = GalleryRecyclerView.FadingEdgeDirection.ALL;
            } else {
                fadingEdgeDirection = GalleryRecyclerView.FadingEdgeDirection.BOTTOM;
            }
            galleryListView.setFadingEdge(fadingEdgeDirection);
        }
    }

    public boolean useAdvancedMouseDragAndDrop() {
        return false;
    }

    public AlbumsPaneLayoutManager createLayoutManager() {
        return new AlbumsPaneLayoutManager(getContext(), 1);
    }

    public AlbumsPaneViewAdapter<?> createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumsPaneViewAdapter<>(this, getLocationKey());
    }

    public AlbumsPanePresenter<?> createPresenter(IAlbumsBaseView iAlbumsBaseView) {
        return new AlbumsPanePresenter<>(this.mBlackboard, iAlbumsBaseView);
    }

    public void onSelectAll() {
    }

    public void postAnalyticsLog() {
    }

    public void selectAll() {
    }

    public void setScreenMode() {
    }

    public void updateSelectionToolBar() {
    }

    public void enterSelectionMode(int i2) {
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
