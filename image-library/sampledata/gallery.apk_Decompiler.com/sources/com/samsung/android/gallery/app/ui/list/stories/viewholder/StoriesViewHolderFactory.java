package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesViewHolderFactory {
    private LayoutCache mCacheLoader;
    private final int mDataLayoutId;
    private DimenHelper mDimenHelper;
    protected final LayoutInflater mLayoutInflater;

    public StoriesViewHolderFactory(Context context) {
        this(LayoutInflater.from(context));
    }

    private ListViewHolder createHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new HeaderViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_empty_container_layout, viewGroup, false), i2);
    }

    private int getDataLayoutId() {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return R.layout.recycler_item_stories_pinch_image_layout;
        }
        return R.layout.recycler_item_stories_image_layout;
    }

    public ListViewHolder createDataViewHolder(ViewGroup viewGroup, int i2) {
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View cachedItemView = layoutCache.getCachedItemView(this.mDataLayoutId);
            if (cachedItemView == null) {
                this.mCacheLoader = null;
            }
            view = cachedItemView;
        }
        if (view == null) {
            view = this.mLayoutInflater.inflate(this.mDataLayoutId, viewGroup, false);
        }
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return new StoriesPinchViewHolder(view, i2, this.mDimenHelper);
        }
        return new StoriesViewHolder(view, i2);
    }

    public ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        if (ViewHolderValue.isHeader(i2)) {
            return createHeaderViewHolder(viewGroup, i2);
        }
        return createDataViewHolder(viewGroup, i2);
    }

    public StoriesViewHolderFactory(Context context, DimenHelper dimenHelper) {
        this(LayoutInflater.from(context));
        this.mDimenHelper = dimenHelper;
    }

    private StoriesViewHolderFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
        this.mCacheLoader = LayoutCache.getInstance();
        this.mDataLayoutId = getDataLayoutId();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HeaderViewHolder extends ListViewHolder {
        public HeaderViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void recycle() {
            super.recycle();
            ViewUtils.removeAllViews((ViewGroup) this.itemView);
        }

        public void bindView(View view) {
        }
    }
}
