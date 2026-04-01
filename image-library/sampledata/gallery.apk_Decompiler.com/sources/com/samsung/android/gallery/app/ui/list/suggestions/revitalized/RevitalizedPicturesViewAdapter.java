package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import Ob.a;
import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevitalizedPicturesViewAdapter extends BaseListViewAdapter<IRevitalizedView> {
    private int mItemHeight = -1;
    private final RevitalizedPicturesViewHolderFactory mViewHolderFactory;

    public RevitalizedPicturesViewAdapter(IRevitalizedView iRevitalizedView, String str) {
        super(iRevitalizedView, str);
        this.mViewHolderFactory = createViewHolderFactory(iRevitalizedView.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ListViewHolder listViewHolder) {
        this.mItemHeight = listViewHolder.itemView.getHeight();
    }

    private void updateBodyText(ListViewHolder listViewHolder, MediaItem mediaItem) {
        long revitalizedType = MediaItemSuggest.getRevitalizedType(mediaItem);
        String revitalizedTypeDescription = RevitalizedDelegate.getRevitalizedTypeDescription(revitalizedType);
        if (RevitalizedDelegate.isResolutionChangedType(revitalizedType)) {
            ((RevitalizedPicturesViewHolder) listViewHolder).setRemasterImageText(mediaItem, revitalizedTypeDescription);
        } else {
            ((RevitalizedPicturesViewHolder) listViewHolder).setRemasterImageText(revitalizedTypeDescription);
        }
    }

    public RevitalizedPicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new RevitalizedPicturesViewHolderFactory(context);
    }

    public int getItemHeight(int i2) {
        return this.mItemHeight;
    }

    public int getItemViewType(int i2) {
        if (isDefaultDepth(getGridSize())) {
            return 6;
        }
        return 5;
    }

    public int getMaxScroll() {
        int i2;
        int gridSize = getGridSize();
        int itemCount = getItemCount();
        int i7 = itemCount / gridSize;
        if (itemCount % gridSize > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return getItemHeight(0) * (i7 + i2);
    }

    public int getNextRowIndex(int i2, int i7) {
        return Math.min(getGridSize() + i2, i7 - 1);
    }

    public int getPrevRowIndex(int i2) {
        return Math.max(0, i2 - getGridSize());
    }

    public ThumbKind getThumbnailKind() {
        if (isDefaultDepth(getGridSize())) {
            return ThumbKind.MEDIUM_KIND;
        }
        return ThumbKind.LARGE_KIND;
    }

    public boolean isDefaultDepth(int i2) {
        int depth = this.mGalleryListView.getDepth();
        if (getGridSize() == i2) {
            if (depth == 0) {
                return true;
            }
            return false;
        } else if (depth != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((IRevitalizedView) this.mView).postAnalyticsLog(((IRevitalizedView) this.mView).getScreenId(), AnalyticsEventId.EVENT_SUGGEST_REMASTER_PICTURES_VIEW_DETAIL);
        super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public void updateBorder(ListViewHolder listViewHolder) {
        ((RevitalizedPicturesViewHolder) listViewHolder).updateBorder();
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createListViewHolder(viewGroup, i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        updateBodyText(listViewHolder, listViewHolder.getMediaItem());
        listViewHolder.itemView.post(new a(4, this, listViewHolder));
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("updateLayout")) {
            ((RevitalizedPicturesViewHolder) listViewHolder).updateLayout(getItemViewType(i2));
        }
        super.onBindViewHolder(listViewHolder, i2, list);
    }
}
