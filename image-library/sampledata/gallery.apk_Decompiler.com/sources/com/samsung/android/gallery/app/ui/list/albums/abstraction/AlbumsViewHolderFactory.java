package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountReorderHolder;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountViewHolder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsViewHolderFactory {
    private LayoutCache mCacheLoader;
    protected final int mGridLayoutId;
    protected final float mGridRoundRadius;
    protected final int mHeaderLayoutId;
    protected final LayoutInflater mLayoutInflater;
    protected final int mListLayoutId;
    protected final float mListRoundRadius;
    protected final boolean mReorderEnabled;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EmptyViewHolder extends ListViewHolder {
        public /* synthetic */ EmptyViewHolder(View view, int i2, int i7) {
            this(view, i2);
        }

        private EmptyViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void bindView(View view) {
        }
    }

    public AlbumsViewHolderFactory(Context context) {
        this(LayoutInflater.from(context));
    }

    private boolean getEnableSyncView() {
        if (!Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_STATUS) || Features.isEnabled(Features.IS_UPSM)) {
            return false;
        }
        return true;
    }

    public ListViewHolder createFooterViewHolder(ViewGroup viewGroup, int i2) {
        return new EmptyViewHolder(this.mLayoutInflater.inflate(this.mHeaderLayoutId, viewGroup, false), i2, 0);
    }

    public ListViewHolder createGridViewHolder(ViewGroup viewGroup, int i2) {
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View cachedItemView = layoutCache.getCachedItemView(this.mGridLayoutId);
            if (cachedItemView == null) {
                this.mCacheLoader = null;
            }
            view = cachedItemView;
        }
        if (view == null) {
            view = this.mLayoutInflater.inflate(this.mGridLayoutId, viewGroup, false);
        }
        ListViewHolder createGridViewHolder = createGridViewHolder(view, i2, getEnableSyncView());
        createGridViewHolder.setThumbnailShape(1, this.mGridRoundRadius);
        return createGridViewHolder;
    }

    public ListViewHolder createHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new EmptyViewHolder(this.mLayoutInflater.inflate(this.mHeaderLayoutId, viewGroup, false), i2, 0);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View cachedItemView = layoutCache.getCachedItemView(this.mListLayoutId);
            if (cachedItemView == null) {
                this.mCacheLoader = null;
            }
            view = cachedItemView;
        }
        if (view == null) {
            view = this.mLayoutInflater.inflate(this.mListLayoutId, viewGroup, false);
        }
        ListViewHolder createListViewHolder = createListViewHolder(view, i2, getEnableSyncView());
        createListViewHolder.setThumbnailShape(1, this.mListRoundRadius);
        return createListViewHolder;
    }

    public ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 2) {
            return createGridViewHolder(viewGroup, i2);
        }
        if (i2 == 1) {
            return createListViewHolder(viewGroup, i2);
        }
        if (ViewHolderValue.isHeader(i2)) {
            return createHeaderViewHolder(viewGroup, i2);
        }
        if (ViewHolderValue.isFooter(i2)) {
            return createFooterViewHolder(viewGroup, i2);
        }
        return createGridViewHolder(viewGroup, i2);
    }

    public int getGridLayoutId() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R.layout.recycler_item_albums_image_grid_layout;
        }
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.layout.recycler_item_mx_blur_albums_image_grid_layout;
        }
        return R.layout.recycler_item_mx_albums_image_grid_layout;
    }

    public int getGridRoundRadiusId() {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.dimen.album_view_corner_radius_grid_blur;
        }
        return R.dimen.album_view_corner_radius_grid;
    }

    public int getHeaderLayoutId() {
        return R.layout.recycler_item_empty_container_layout;
    }

    public int getListLayoutId() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R.layout.recycler_item_albums_image_list_layout;
        }
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.layout.recycler_item_mx_blur_albums_image_list_layout;
        }
        return R.layout.recycler_item_mx_albums_image_list_layout;
    }

    public int getListRoundRadiusId() {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.dimen.album_view_corner_radius_list_blur;
        }
        return R.dimen.album_view_corner_radius_list;
    }

    public AlbumsViewHolderFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
        this.mGridLayoutId = getGridLayoutId();
        this.mListLayoutId = getListLayoutId();
        this.mHeaderLayoutId = getHeaderLayoutId();
        this.mCacheLoader = LayoutCache.getInstance();
        this.mReorderEnabled = !Features.isEnabled(Features.IS_UPSM);
        Resources resources = layoutInflater.getContext().getResources();
        this.mGridRoundRadius = resources.getDimension(getGridRoundRadiusId());
        this.mListRoundRadius = resources.getDimension(getListRoundRadiusId());
    }

    public ListViewHolder createGridViewHolder(View view, int i2, boolean z) {
        return new AlbumTitleCountViewHolder(view, i2, z);
    }

    public ListViewHolder createListViewHolder(View view, int i2, boolean z) {
        return new AlbumTitleCountReorderHolder(view, i2, this.mReorderEnabled, z);
    }
}
