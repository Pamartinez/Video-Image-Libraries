package com.samsung.android.gallery.app.ui.list.picker.albums;

import V8.a;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.mx.AlbumKind;
import com.samsung.android.gallery.app.ui.list.albums.mx.IMxAlbumsView;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumsPickerView;
import com.samsung.android.gallery.app.ui.list.picker.albums.MxAlbumsPickerPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.app.ui.viewholders.MxAlbumsDividerViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsPickerFragment<V extends IAlbumsPickerView, P extends MxAlbumsPickerPresenter> extends AlbumsPickerFragment<V, P> implements IAlbumsPickerView, IMxAlbumsView {
    public void bindView(View view) {
        super.bindView(view);
        this.mRecyclerView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.BOTTOM);
    }

    public AlbumKind getAlbumKind(int i2) {
        if (getAdapter() == null) {
            return AlbumKind.REPRESENTATIVE_ALBUMS;
        }
        return getAdapter().getAlbumKind(getAdapter().getViewPosition(i2));
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return new MxAlbumsPinchAnimationManager((PinchLayoutManager) this.mLayoutManager);
    }

    public int getSharingLimitCount() {
        return 0;
    }

    public boolean isEssentialViewMode() {
        return PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums);
    }

    public boolean onFolderCreated(int i2, String str, ArrayList<Integer> arrayList) {
        return false;
    }

    public boolean onFolderCreatedFromDrag(int i2, String str) {
        return false;
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (getAdapter() == null || !ViewHolderValue.isDivider(listViewHolder.getItemViewType())) {
            super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        } else {
            ((MxAlbumsPickerPresenter) this.mPresenter).moveToAllAlbums();
        }
    }

    public MxAlbumsLayoutManager createLayoutManager() {
        return new MxAlbumsLayoutManager(getContext(), getListView().getColumnCount()) {
            public int[] getItemPadding(int i2) {
                int albumHorizontalPadding = GridMarginHelper.getAlbumHorizontalPadding(MxAlbumsPickerFragment.this.mRecyclerView);
                int albumVerticalPadding = GridMarginHelper.getAlbumVerticalPadding(MxAlbumsPickerFragment.this.mRecyclerView);
                return new int[]{albumHorizontalPadding, albumVerticalPadding, albumHorizontalPadding, albumVerticalPadding};
            }

            public int getSpacing(int i2) {
                if (isListView(i2)) {
                    return 0;
                }
                return MxAlbumsPickerFragment.this.getGridSpacing();
            }

            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                MxAlbumsPickerFragment.this.onGridChanged(i2, spanCount);
                if (getAdapter() != null) {
                    getAdapter().updateGridSize();
                }
            }
        };
    }

    public MxAlbumsViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new MxAlbumsViewAdapter<MxAlbumsPickerFragment<V, P>>(this, getLocationKey()) {
            public void enableViewAll(MxAlbumsDividerViewHolder mxAlbumsDividerViewHolder) {
                boolean z;
                if (isSelectionMode() || MxAlbumsPickerFragment.this.isMoveMode() || ((Boolean) Optional.ofNullable(((MxAlbumsPickerPresenter) MxAlbumsPickerFragment.this.mPresenter).getCurrentItem()).map(new a(15)).orElse(Boolean.FALSE)).booleanValue()) {
                    z = false;
                } else {
                    z = true;
                }
                mxAlbumsDividerViewHolder.setViewAllTextEnabled(z);
                if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                    mxAlbumsDividerViewHolder.hideTitle();
                }
            }

            public int getHeaderCount() {
                if (supportHeader()) {
                    return super.getHeaderCount();
                }
                return 0;
            }

            public boolean needToInflateHeaderView() {
                return false;
            }

            public boolean supportHeader() {
                if (!MxAlbumsPickerFragment.this.isEssentialViewMode() || LocationKey.isFolder(getLocationKey())) {
                    return false;
                }
                return true;
            }

            public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
                super.onBindViewHolder(listViewHolder, i2);
                if (isData(i2) && !((MxAlbumsPickerFragment) this.mView).isItemEnabled(listViewHolder.getMediaItem())) {
                    ViewUtils.setViewEnabled(listViewHolder.itemView, false);
                }
            }

            public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
                ListViewHolder onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
                if (onCreateViewHolder.getViewType() == -5) {
                    onCreateViewHolder.setEnable(false);
                }
                return onCreateViewHolder;
            }
        };
    }

    public MxAlbumsPickerPresenter createPresenter(IAlbumsPickerView iAlbumsPickerView) {
        return new MxAlbumsPickerPresenter(this.mBlackboard, iAlbumsPickerView);
    }

    public MxAlbumsViewAdapter getAdapter() {
        return (MxAlbumsViewAdapter) super.getAdapter();
    }

    public void updateSharingBadge() {
    }

    public void onFolderCreationFailed(boolean z) {
    }

    public void onPrepareUngrouping(ArrayList<Integer> arrayList) {
    }
}
