package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import Ob.a;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.app.ui.list.hover.HoverParams;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesBaseView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoriesBaseViewAdapter<V extends IStoriesBaseView> extends BaseListViewAdapter<V> {
    protected int mItemHeight = -1;

    public StoriesBaseViewAdapter(V v, String str) {
        super(v, str);
    }

    private AnalyticsEventId getEventId(int i2) {
        return AnalyticsEventId.EVENT_STORY_SELECT;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$calculateItemHeight$0(ListViewHolder listViewHolder) {
        this.mItemHeight = listViewHolder.itemView.getHeight();
    }

    public void calculateItemHeight(ListViewHolder listViewHolder) {
        listViewHolder.itemView.post(new a(17, this, listViewHolder));
    }

    public long getDataId(MediaItem mediaItem) {
        return (long) MediaItemStory.getStoryId(mediaItem);
    }

    public List<Long> getDataIds() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mMediaData.getCount(); i2++) {
            MediaItem read = this.mMediaData.read(i2);
            if (read != null) {
                arrayList.add(Long.valueOf((long) MediaItemStory.getStoryId(read)));
            }
        }
        return arrayList;
    }

    public int getItemHeight(int i2) {
        return this.mItemHeight;
    }

    public int getItemViewType(int i2) {
        return 0;
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

    public boolean isPreviewAvailable() {
        return true;
    }

    public void onHoverInternal(ListViewHolder listViewHolder, MotionEvent motionEvent) {
        ((HoverHandler) this.mHoverHandler.get()).onHover(listViewHolder, motionEvent, new HoverParams.HoverParamsBuilder(getLocationKey()).setContainer((ViewGroup) ((IStoriesBaseView) this.mView).getView()).setItem(listViewHolder.getMediaItem()).setDataPosition(getMediaItemPosition(listViewHolder.getViewPosition())).setAlbumType(true).setHideOption(false).build());
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (i7 == 1001) {
            ((IStoriesBaseView) this.mView).onHighlightVideoButtonClick(mediaItem);
            return;
        }
        ((IStoriesBaseView) this.mView).postAnalyticsLog(getEventId(MediaItemStory.getStoryType(mediaItem)), (long) mediaItem.getCount());
        super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((IStoriesBaseView) this.mView).postAnalyticsLog(AnalyticsEventId.EVENT_LONG_PRESS_ALBUM);
        return super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public boolean supportHover() {
        return true;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        calculateItemHeight(listViewHolder);
        super.onBindViewHolder(listViewHolder, i2, list);
    }
}
