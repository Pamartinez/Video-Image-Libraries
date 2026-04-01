package com.samsung.android.gallery.app.ui.list.stories.legacy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesBaseView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseViewHolder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesLegacyViewAdapter<V extends IStoriesBaseView> extends StoriesBaseViewAdapter<V> {
    private LayoutCache mCacheLoader = LayoutCache.getInstance();

    public StoriesLegacyViewAdapter(V v, String str) {
        super(v, str);
    }

    public int getItemViewLayoutId() {
        return R.layout.recycler_item_stories_image_layout_legacy;
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.STORY_COVER;
    }

    public ListViewHolder onCreateViewHolderInternal(View view, int i2) {
        return new StoriesBaseViewHolder(view, i2);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View cachedItemView = layoutCache.getCachedItemView(getItemViewLayoutId());
            if (cachedItemView == null) {
                this.mCacheLoader = null;
            }
            view = cachedItemView;
        }
        if (view == null) {
            String str = this.TAG;
            Log.w(str, "onCreateViewHolder : onMainThread. viewType=" + i2);
            view = LayoutInflater.from(viewGroup.getContext()).inflate(getItemViewLayoutId(), viewGroup, false);
        }
        return onCreateViewHolderInternal(view, i2);
    }
}
