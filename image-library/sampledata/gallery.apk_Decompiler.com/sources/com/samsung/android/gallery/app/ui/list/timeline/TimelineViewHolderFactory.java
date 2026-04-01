package com.samsung.android.gallery.app.ui.list.timeline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.ConcatImageViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.TimelineImageViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.TimelinePreviewViewHolder;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelineViewHolderFactory extends PicturesViewHolderFactory {
    private LayoutCache mCacheLoader;

    public TimelineViewHolderFactory(Context context) {
        this(LayoutInflater.from(context));
    }

    public ListViewHolder createDataViewHolder(ViewGroup viewGroup, int i2, int i7) {
        ListViewHolder listViewHolder;
        int dataLayoutId = getDataLayoutId(i2);
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View cachedItemView = layoutCache.getCachedItemView(dataLayoutId);
            if (cachedItemView == null) {
                this.mCacheLoader = null;
            }
            view = cachedItemView;
        }
        if (view == null) {
            view = this.mLayoutInflater.inflate(dataLayoutId, viewGroup, false);
        }
        if (useConcatThumbnail(i2)) {
            return new ConcatImageViewHolder(view, i2);
        }
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW) {
            listViewHolder = new TimelinePreviewViewHolder(view, i2);
        } else {
            listViewHolder = new TimelineImageViewHolder(view, i2);
        }
        if (i7 > 1 && viewGroup != null) {
            resizeDataView(viewGroup, view, i7);
        }
        return listViewHolder;
    }

    public TimelineViewHolderFactory(LayoutInflater layoutInflater) {
        super(layoutInflater);
        this.mCacheLoader = LayoutCache.getInstance();
    }
}
