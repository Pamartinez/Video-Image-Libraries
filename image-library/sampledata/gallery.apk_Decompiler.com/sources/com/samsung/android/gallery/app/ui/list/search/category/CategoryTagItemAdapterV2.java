package com.samsung.android.gallery.app.ui.list.search.category;

import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryTagItemAdapterV2 extends BaseListViewAdapter<ISearchView> {
    private final boolean mIsFromExpansion;
    private final CategoryPropertyHelper mPropertyHelper;
    private final CategoryItemViewHolderFactory mViewHolderFactory;

    public CategoryTagItemAdapterV2(ISearchView iSearchView, String str, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(iSearchView, str);
        this.mIsFromExpansion = z;
        this.mViewHolderFactory = createViewHolderFactory(iSearchView.getContext());
        this.mPropertyHelper = categoryPropertyHelper;
    }

    private CategoryItemViewHolderFactory createViewHolderFactory(Context context) {
        return new CategoryItemViewHolderFactory(context);
    }

    public int getItemCount() {
        if (this.mIsFromExpansion) {
            return this.mDataCount;
        }
        return Math.min(this.mDataCount, this.mPropertyHelper.getMaxDisplayCountOnCard(getContext()));
    }

    public int getItemViewType(int i2) {
        return this.mPropertyHelper.getItemViewType();
    }

    public MediaData getMediaData(ISearchView iSearchView, String str) {
        MediaData mediaData = super.getMediaData(iSearchView, str);
        if (mediaData != null) {
            return mediaData;
        }
        return MediaDataFactory.getInstance(this.mBlackBoard).create(str);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        Log.sw(this.TAG, "unexpected viewHolder create");
        return this.mViewHolderFactory.createListViewHolder(viewGroup, i2, this.mIsFromExpansion);
    }
}
