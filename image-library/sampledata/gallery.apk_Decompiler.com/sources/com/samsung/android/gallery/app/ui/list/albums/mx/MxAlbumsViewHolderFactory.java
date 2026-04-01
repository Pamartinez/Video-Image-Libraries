package com.samsung.android.gallery.app.ui.list.albums.mx;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.MxAlbumTitleCountReorderHolder;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.MxAlbumTitleCountViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.MxAlbumsDividerViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.MxAlbumsEmptyViewHolder;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsViewHolderFactory extends AlbumsViewHolderFactory {
    private LayoutCache mCacheLoader = LayoutCache.getInstance();

    public MxAlbumsViewHolderFactory(Context context) {
        super(context);
    }

    private ListViewHolder createDividerViewHolder(ViewGroup viewGroup, int i2) {
        int dividerLayoutId = getDividerLayoutId(i2);
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View view2 = layoutCache.getView(dividerLayoutId);
            if (view2 == null) {
                this.mCacheLoader = null;
            }
            view = view2;
        }
        if (view == null) {
            view = this.mLayoutInflater.inflate(dividerLayoutId, viewGroup, false);
        }
        return new MxAlbumsDividerViewHolder(view, i2);
    }

    private ListViewHolder createNoItemViewHolder(ViewGroup viewGroup, int i2) {
        return new MxAlbumsEmptyViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_mx_albums_empty_layout, viewGroup, false), i2);
    }

    private int getDividerLayoutId(int i2) {
        if (ViewHolderValue.isFirstDivider(i2)) {
            return R.layout.recycler_item_mx_albums_first_divider_layout;
        }
        return R.layout.recycler_item_mx_albums_divider_layout;
    }

    public ListViewHolder createGridViewHolder(View view, int i2, boolean z) {
        return new MxAlbumTitleCountViewHolder(view, i2, z);
    }

    public ListViewHolder createListViewHolder(View view, int i2, boolean z) {
        return new MxAlbumTitleCountReorderHolder(view, i2, this.mReorderEnabled, z);
    }

    public ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        if (ViewHolderValue.isDivider(i2)) {
            return createDividerViewHolder(viewGroup, i2);
        }
        if (i2 == -5) {
            return createNoItemViewHolder(viewGroup, i2);
        }
        return super.createViewHolder(viewGroup, i2);
    }
}
