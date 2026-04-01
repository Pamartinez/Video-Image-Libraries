package com.samsung.android.gallery.app.ui.list.picker.albums;

import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.picker.albums.IMxAlbumFolderPickerView;
import com.samsung.android.gallery.app.ui.viewholders.MxAlbumsDividerViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumFolderPickerAdapter<V extends IMxAlbumFolderPickerView> extends AlbumFolderPickerAdapter<V> {
    private final int mDividerHeight;
    protected final AlbumsViewHolderFactory mDividerViewHolderFactory;
    private final int mSideGap;

    public MxAlbumFolderPickerAdapter(V v, String str) {
        super(v, str);
        this.mDividerHeight = v.getResources().getDimensionPixelOffset(R.dimen.mx_albums_divider_height);
        this.mSideGap = v.getResources().getDimensionPixelOffset(R.dimen.album_grid_view_side_gap);
        this.mDividerViewHolderFactory = new MxAlbumsViewHolderFactory(v.getContext());
    }

    private int getDividerDeltaPosition(int i2) {
        return i2 - (((IMxAlbumFolderPickerView) this.mView).isEssentialMode() ? 1 : 0);
    }

    private MediaItem getDividerItem() {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setCount(this.mDataCount);
        return mediaItem;
    }

    private boolean isDataEmpty() {
        if (getDataCount() == 0) {
            return true;
        }
        return false;
    }

    private boolean isEmptyViewPosition(int i2) {
        if (i2 - (supportHeader() ? 1 : 0) == 1) {
            return true;
        }
        return false;
    }

    private boolean isFirstDivider(int i2) {
        if (i2 - (supportHeader() ? 1 : 0) == 0) {
            return true;
        }
        return false;
    }

    private void updateBadge(ListViewHolder listViewHolder) {
        if (((IMxAlbumFolderPickerView) this.mView).isEssentialMode()) {
            listViewHolder.updateDecoration(1, Boolean.valueOf(needBadgeOnViewAllOfEssentialAlbums()));
        }
    }

    public int getHintItemViewHeight(int i2, int i7, int i8) {
        if (isDivider(i2)) {
            return this.mDividerHeight;
        }
        return super.getHintItemViewHeight(i2, i7, i8);
    }

    public int getHintItemViewType(int i2, int i7) {
        if (((IMxAlbumFolderPickerView) this.mView).isEssentialMode()) {
            if (isFirstDivider(i2)) {
                return -1;
            }
            if (isDataEmpty() && isEmptyViewPosition(i2)) {
                return -5;
            }
        }
        return super.getHintItemViewType(i2, i7);
    }

    public int getHintSpanSize(int i2, int i7) {
        if ((!supportHeader() || !isHeader(i2)) && !isDivider(i2) && !isDataEmpty()) {
            return 1;
        }
        return i7;
    }

    public int getHintStartSpan(int i2, int i7) {
        if (supportHeader()) {
            if (isHeader(i2)) {
                return 0;
            }
            int i8 = 1;
            if (isDivider(i2)) {
                return 1;
            }
            if (((IMxAlbumFolderPickerView) this.mView).isEssentialMode()) {
                i8 = 2;
            }
            return (i2 - i8) % i7;
        } else if (isDivider(i2)) {
            return 0;
        } else {
            return (i2 - (((IMxAlbumFolderPickerView) this.mView).isEssentialMode() ? 1 : 0)) % i7;
        }
    }

    public int getItemCount() {
        return (((IMxAlbumFolderPickerView) this.mView).isEssentialMode() ? 1 : 0) + super.getItemCount() + (isDataEmpty() ? 1 : 0);
    }

    public int getItemHeight(int i2) {
        if (isDivider(i2)) {
            return this.mDividerHeight;
        }
        return super.getItemHeight(i2);
    }

    public int getItemViewType(int i2) {
        return getHintItemViewType(i2, getGridSize());
    }

    public int getMaxScroll() {
        int i2;
        int maxScroll = super.getMaxScroll();
        if (((IMxAlbumFolderPickerView) this.mView).isEssentialMode()) {
            i2 = this.mDividerHeight;
        } else {
            i2 = 0;
        }
        return maxScroll + i2;
    }

    public MediaItem getMediaItemFromCache(int i2) {
        if (isDivider(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2);
    }

    public int getMediaItemPosition(int i2) {
        return super.getMediaItemPosition(getDividerDeltaPosition(i2));
    }

    public AlbumsViewHolderFactory getViewHolderFactory(int i2) {
        if (ViewHolderValue.isDivider(i2) || isDataEmpty()) {
            return this.mDividerViewHolderFactory;
        }
        return super.getViewHolderFactory(i2);
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (ViewHolderValue.isDivider(listViewHolder.getItemViewType())) {
            onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
        } else {
            super.handleItemClick(listViewHolder, i2, mediaItem, i7);
        }
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2) {
        ViewMarginUtils.setHorizontalMargin(iPinchViewHolder.getDividerView(), this.mSideGap - ((IMxAlbumFolderPickerView) this.mView).getLayoutManager().getSpacing(i2));
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        if (isDivider(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2, i7);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        ListViewHolder onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
        if (onCreateViewHolder.getViewType() == -5) {
            onCreateViewHolder.setEnable(false);
        }
        return onCreateViewHolder;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (ViewHolderValue.isDivider(listViewHolder.getViewType())) {
            MxAlbumsDividerViewHolder mxAlbumsDividerViewHolder = (MxAlbumsDividerViewHolder) listViewHolder;
            mxAlbumsDividerViewHolder.setViewAllTextEnabled(true);
            mxAlbumsDividerViewHolder.hideTitle();
            updateBadge(listViewHolder);
        }
        super.onBindViewHolder(listViewHolder, i2, list);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        if (isDivider(i2)) {
            listViewHolder.bind(getDividerItem());
            setListeners(listViewHolder);
            setViewHolderMargin(listViewHolder, getGridSize());
            updateBadge(listViewHolder);
            return;
        }
        super.onBindViewHolder(listViewHolder, i2);
    }
}
