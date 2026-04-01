package com.samsung.android.gallery.app.ui.list.albums.mx;

import A4.L;
import Fa.F;
import I4.b;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragAdapter;
import com.samsung.android.gallery.app.ui.list.albums.mx.IMxAlbumsView;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsContextMenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsFragment<V extends IMxAlbumsView, P extends MxAlbumsPresenter> extends AlbumsFragment<V, P> implements IMxAlbumsView {
    private void enableHeader(boolean z) {
        Optional.ofNullable(getAdapter()).ifPresent(new L(z, 12));
    }

    private boolean isNoItem(int i2) {
        if (i2 == -5) {
            return true;
        }
        return false;
    }

    public int calculateViewPoolExtra(AlbumsViewDummyAdapter albumsViewDummyAdapter, boolean z, int i2, int i7) {
        if (z && i2 < 12) {
            int min = Math.min(PreferenceCache.MxAlbumSharedCount.getInt(), 3);
            i2 += min;
            i7 += min;
        }
        return super.calculateViewPoolExtra(albumsViewDummyAdapter, z, i2, i7);
    }

    public AlbumKind getAlbumKind(int i2) {
        if (getAdapter() == null) {
            return AlbumKind.REPRESENTATIVE_ALBUMS;
        }
        return getAdapter().getAlbumKind(getAdapter().getViewPosition(i2));
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumsViewDummyAdapter(getListView()) {
            public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
                return new MxAlbumsViewHolderFactory(context);
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_albums_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return new MxAlbumsPinchAnimationManager((PinchLayoutManager) this.mLayoutManager);
    }

    public String getScreenId() {
        if (!isEssentialViewMode()) {
            return super.getScreenId();
        }
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_ESSENTIAL_ALBUM_VIEW_EDIT.toString();
        }
        return AnalyticsScreenId.SCREEN_ESSENTIAL_ALBUM_VIEW_NORMAL.toString();
    }

    public int getSharingLimitCount() {
        return getPinchColumn()[1];
    }

    public int getViewPositionOffset(AlbumsDragAdapter<?> albumsDragAdapter) {
        return super.getViewPositionOffset(albumsDragAdapter) + 1;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        Optional.ofNullable(getAdapter()).ifPresent(new F(29));
    }

    public void inflateEmptyView(View view) {
        if (!isEssentialViewMode()) {
            super.inflateEmptyView(view);
        }
    }

    public boolean isEssentialViewMode() {
        return PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums);
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent, int i2) {
        if (!super.isVirtualCtrlKeyPressedAllowablePoint(motionEvent, i2)) {
            return false;
        }
        ListViewHolder listViewHolder = (ListViewHolder) getListView().findViewHolderForLayoutPosition(i2);
        if (listViewHolder == null || listViewHolder.isVirtualCtrlKeyPressedAllowablePoint(motionEvent)) {
            return true;
        }
        return false;
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        Optional.ofNullable(getAdapter()).ifPresent(new F(27));
    }

    public void onEnterMoveMode() {
        super.onEnterMoveMode();
        enableHeader(false);
    }

    public void onExitMoveMode() {
        super.onExitMoveMode();
        enableHeader(true);
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 6014) {
            return super.onHandleEvent(eventMessage);
        }
        Optional.ofNullable(getAdapter()).ifPresent(new F(28));
        return true;
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (getAdapter() != null && ViewHolderValue.isDivider(listViewHolder.getItemViewType())) {
            ((MxAlbumsPresenter) this.mPresenter).onDividerClicked(getAdapter().getAlbumKind(i2));
        } else if (getAdapter() == null || !isNoItem(listViewHolder.getItemViewType())) {
            super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        } else {
            ((MxAlbumsPresenter) this.mPresenter).onNoItemClicked();
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isSharing()) {
            return super.onListItemLongClick(listViewHolder, i2, mediaItem);
        }
        new SharingsContextMenuHandler().handle(this, mediaItem);
        return true;
    }

    public void onResume() {
        super.onResume();
        if (((Boolean) Blackboard.getApplicationInstance().read("data://user/SecurityExceptionOnSecMp", Boolean.FALSE)).booleanValue()) {
            ((MxAlbumsPresenter) this.mPresenter).forceReloadData();
        }
    }

    public void onSelectionModeChanged(boolean z) {
        if (!isMoveMode()) {
            enableHeader(!z);
        }
    }

    public void updateSharingBadge() {
        Optional.ofNullable(getAdapter()).ifPresent(new b(0));
    }

    public void updateToolbarStartInset(GalleryToolbar galleryToolbar) {
        galleryToolbar.setContentInset();
    }

    public MxAlbumsPresenter createPresenter(IMxAlbumsView iMxAlbumsView) {
        return new MxAlbumsPresenter(this.mBlackboard, iMxAlbumsView);
    }

    public MxAlbumsLayoutManager createLayoutManager() {
        return new MxAlbumsLayoutManager(getContext(), getListView().getColumnCount()) {
            public int[] getItemPadding(int i2) {
                int albumHorizontalPadding = GridMarginHelper.getAlbumHorizontalPadding(MxAlbumsFragment.this.mRecyclerView);
                int albumVerticalPadding = GridMarginHelper.getAlbumVerticalPadding(MxAlbumsFragment.this.mRecyclerView);
                return new int[]{albumHorizontalPadding, albumVerticalPadding, albumHorizontalPadding, albumVerticalPadding};
            }

            public int getSpacing(int i2) {
                if (isListView(i2)) {
                    return 0;
                }
                return MxAlbumsFragment.this.getGridSpacing();
            }

            public boolean isAppbarCollapsed() {
                if (!(MxAlbumsFragment.this.mAppBarLayout == null || MxAlbumsFragment.this.mToolbar == null)) {
                    int visibleHeight = MxAlbumsFragment.this.mAppBarLayout.getVisibleHeight();
                    int top = MxAlbumsFragment.this.mToolbar.getTop() + MxAlbumsFragment.this.mToolbar.getHeight();
                    if (visibleHeight > 0 || Math.abs(visibleHeight) >= top) {
                        return false;
                    }
                    return true;
                }
                return false;
            }

            public boolean isAppbarVisible() {
                return MxAlbumsFragment.this.isAppBarPartiallyVisible();
            }

            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                MxAlbumsFragment.this.onGridChanged(i2, spanCount);
                if (getAdapter() != null) {
                    getAdapter().updateGridSize();
                }
            }
        };
    }

    public void inflateEmptyView() {
        View view;
        if (this.mEmptyView == null && (view = this.mBackupView) != null) {
            inflateEmptyView(view);
            initializeEmptyView();
            registerEmptyViewListener();
        }
    }

    public MxAlbumsViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new MxAlbumsViewAdapter(this, getLocationKey());
    }

    public MxAlbumsViewAdapter getAdapter() {
        return (MxAlbumsViewAdapter) super.getAdapter();
    }
}
