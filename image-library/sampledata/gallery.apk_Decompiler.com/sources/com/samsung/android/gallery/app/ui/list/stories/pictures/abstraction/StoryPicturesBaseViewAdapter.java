package com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction;

import V8.a;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesBaseView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryPicturesBaseViewAdapter<V extends IStoryPicturesBaseView> extends PicturesHeaderViewAdapter<V> {
    private final boolean mFromDetails;

    public StoryPicturesBaseViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
        this.mFromDetails = ArgumentsUtil.getArgValue(str, "details", false);
    }

    public MediaItem getMediaItemFromCache(int i2) {
        if (i2 == 0) {
            return ((IStoryPicturesBaseView) this.mView).getHeaderItem();
        }
        return super.getMediaItemFromCache(i2);
    }

    public int getScrollStart() {
        if (((IStoryPicturesBaseView) this.mView).isPortrait() && !((IStoryPicturesBaseView) this.mView).getActivity().isInMultiWindowMode()) {
            return super.getScrollStart();
        }
        return ((Integer) Optional.ofNullable(((IStoryPicturesBaseView) this.mView).getToolbar()).map(new a(4)).orElse(0)).intValue() + ((IStoryPicturesBaseView) this.mView).getStatusBarHeight();
    }

    public boolean isPreviewAvailable() {
        return true;
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((IStoryPicturesBaseView) this.mView).postAnalyticsLog(AnalyticsScreenId.SCREEN_EVENT_DETAIL_VIEW_NORMAL.toString(), AnalyticsEventId.EVENT_LONG_PRESS_ALBUM);
        return super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public boolean supportEmptyDescription() {
        return !this.mFromDetails;
    }
}
