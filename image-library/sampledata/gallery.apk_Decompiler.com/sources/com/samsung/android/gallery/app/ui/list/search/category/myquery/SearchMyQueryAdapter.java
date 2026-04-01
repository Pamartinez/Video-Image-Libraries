package com.samsung.android.gallery.app.ui.list.search.category.myquery;

import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.ICategoryView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMyQueryAdapter extends BaseListViewAdapter<ICategoryView> {
    private final ArrayList<MediaItem> mDataList = new ArrayList<>();
    private final boolean mIsFromExpansion;
    private final CategoryPropertyHelper mPropertyHelper;
    private final CategoryItemViewHolderFactory mViewHolderFactory;

    public SearchMyQueryAdapter(ICategoryView iCategoryView, String str, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(iCategoryView, str);
        int count = this.mMediaData.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            this.mDataList.add(this.mMediaData.read(i2));
        }
        this.mIsFromExpansion = z;
        this.mViewHolderFactory = createViewHolderFactory(iCategoryView.getContext());
        this.mPropertyHelper = categoryPropertyHelper;
    }

    private CategoryItemViewHolderFactory createViewHolderFactory(Context context) {
        return new CategoryItemViewHolderFactory(context);
    }

    private void enableRemoveIcon(ListViewHolder listViewHolder) {
        listViewHolder.updateDecoration(512, Boolean.valueOf(((ICategoryView) this.mView).isEditMode()));
    }

    public int getDataCount() {
        return this.mDataList.size();
    }

    public int getItemCount() {
        return getDataCount();
    }

    public int getItemViewType(int i2) {
        return this.mPropertyHelper.getItemViewType();
    }

    public MediaItem getMediaItemFromCache(int i2) {
        return this.mDataList.get(i2);
    }

    public void removeItem(int i2) {
        if (this.mDataList.size() > i2) {
            this.mDataList.remove(i2);
            notifyDataSetChanged();
        }
    }

    public MediaData getMediaData(ICategoryView iCategoryView, String str) {
        MediaData mediaData = super.getMediaData(iCategoryView, str);
        if (mediaData != null) {
            return mediaData;
        }
        return MediaDataFactory.getInstance(this.mBlackBoard).create(str);
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        return getMediaItemFromCache(i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        enableRemoveIcon(listViewHolder);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createListViewHolder(viewGroup, i2, this.mIsFromExpansion);
    }

    public int getMediaItemPosition(int i2) {
        return i2;
    }
}
