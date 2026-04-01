package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.filter.StoryFilterApplier;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.FilterHolder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.BiConsumer;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EntryBlurHolder implements BlurInterface {
    private final ViewGroup mBlurLayout;
    private final ImageView mBlurView;
    private final MediaItem mMediaItem;
    private final IStoryHighlightView mView;

    public EntryBlurHolder(IStoryHighlightView iStoryHighlightView, ViewGroup viewGroup, MediaItem mediaItem) {
        this.mView = iStoryHighlightView;
        this.mBlurLayout = viewGroup;
        this.mBlurView = (ImageView) viewGroup.findViewById(R.id.entry_blur_view);
        this.mMediaItem = mediaItem;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$disable$0() {
        ViewUtils.setVisibleOrGone(this.mBlurLayout, false);
    }

    public void applyFilter(Bitmap bitmap, BiConsumer<Bitmap, Filter> biConsumer) {
        StoryFilterApplier storyFilterApplier = new StoryFilterApplier(this.mView);
        storyFilterApplier.setImageFilter(FilterHolder.findCurrentSepFilter(MediaItemStory.getStoryFilter(this.mMediaItem)));
        storyFilterApplier.apply(bitmap, false, biConsumer);
    }

    public void bindBlurBitmap(MediaItem mediaItem, Bitmap bitmap) {
        if (this.mMediaItem != null) {
            this.mBlurView.setImageBitmap(bitmap);
            ViewUtils.setVisibleOrGone(this.mBlurLayout, true);
            Log.d("EntryBlurHolder", "apply entry blur");
        }
    }

    public void disable(int i2) {
        if (ViewUtils.isVisible(this.mBlurLayout)) {
            ThreadUtil.postOnUiThreadDelayed(new e(26, this), (long) i2);
        }
    }
}
