package com.samsung.android.gallery.app.ui.list.sharings.pinch;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsInvitationViewHolder;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingPinchViewHolderFactory {
    private LayoutCache mCacheLoader;
    protected final int mGridLayoutId;
    protected final float mGridRoundRadius;
    protected final LayoutInflater mLayoutInflater;
    protected final int mListLayoutId;
    protected final float mListRoundRadius;

    public SharingPinchViewHolderFactory(Context context) {
        int i2;
        int i7;
        LayoutInflater from = LayoutInflater.from(context);
        this.mLayoutInflater = from;
        boolean z = PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR;
        if (z) {
            i2 = R.layout.recycler_item_sharing_blur_grid_layout;
        } else {
            i2 = R.layout.recycler_item_sharing_grid_layout;
        }
        this.mGridLayoutId = i2;
        if (z) {
            i7 = R.layout.recycler_item_sharing_blur_list_layout;
        } else {
            i7 = R.layout.recycler_item_sharing_list_layout;
        }
        this.mListLayoutId = i7;
        this.mCacheLoader = LayoutCache.getInstance();
        Resources resources = from.getContext().getResources();
        this.mGridRoundRadius = resources.getDimension(R.dimen.sharing_grid_radius);
        this.mListRoundRadius = resources.getDimension(R.dimen.sharing_list_radius);
    }

    private ListViewHolder createGridViewHolder(ViewGroup viewGroup, int i2) {
        ListViewHolder createViewHolder = createViewHolder(this.mGridLayoutId, viewGroup, i2);
        createViewHolder.setThumbnailShape(1, this.mGridRoundRadius);
        return createViewHolder;
    }

    private ListViewHolder createInvitationViewHolder(ViewGroup viewGroup, int i2) {
        return new SharingsInvitationViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_sharing_invitation_layout, viewGroup, false), i2);
    }

    private ListViewHolder createLastInvitationViewHolder(ViewGroup viewGroup, int i2) {
        return new SharingsInvitationViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_sharing_last_invitation_layout, viewGroup, false), i2);
    }

    private ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        ListViewHolder createViewHolder = createViewHolder(this.mListLayoutId, viewGroup, i2);
        createViewHolder.setThumbnailShape(1, this.mListRoundRadius);
        return createViewHolder;
    }

    public ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 2) {
            return createGridViewHolder(viewGroup, i2);
        }
        if (i2 == 1) {
            return createListViewHolder(viewGroup, i2);
        }
        if (ViewHolderValue.isFirstDivider(i2)) {
            return createLastInvitationViewHolder(viewGroup, i2);
        }
        if (ViewHolderValue.isDivider(i2)) {
            return createInvitationViewHolder(viewGroup, i2);
        }
        return createGridViewHolder(viewGroup, i2);
    }

    private ListViewHolder createViewHolder(int i2, ViewGroup viewGroup, int i7) {
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View cachedItemView = layoutCache.getCachedItemView(i2);
            if (cachedItemView == null) {
                this.mCacheLoader = null;
            }
            view = cachedItemView;
        }
        if (view == null) {
            view = this.mLayoutInflater.inflate(i2, viewGroup, false);
        }
        return new SharingItemPinchViewHolder(view, i7);
    }
}
