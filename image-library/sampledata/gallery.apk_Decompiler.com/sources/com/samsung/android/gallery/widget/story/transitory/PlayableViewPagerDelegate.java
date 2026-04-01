package com.samsung.android.gallery.widget.story.transitory;

import a6.g;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.DurationHelper;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.livemotion.abstraction.IDuration;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayableViewPagerDelegate extends ViewPagerDelegate implements IDuration {
    private StoriesPlayableViewPager mViewPager;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAdapter$0(int i2, int i7, MediaItem mediaItem) {
        ViewPagerListener viewPagerListener = this.mViewPagerListener;
        if (viewPagerListener != null) {
            viewPagerListener.onChildPageSelected(i2, i7, mediaItem);
        }
    }

    public StoriesViewPager createViewPager(View view) {
        StoriesPlayableViewPager storiesPlayableViewPager = new StoriesPlayableViewPager((ViewGroup) view.findViewById(R$id.viewpager_parent), this);
        this.mViewPager = storiesPlayableViewPager;
        return storiesPlayableViewPager;
    }

    public int getDuration(ThumbnailInterface thumbnailInterface) {
        int i2;
        if (MediaItemStory.isLiveEffectItem(thumbnailInterface)) {
            i2 = 7000;
        } else {
            i2 = 5000;
        }
        return DurationHelper.getItemDuration(thumbnailInterface, new int[]{3000, i2});
    }

    public void initAdapter(ViewPagerItemFactory viewPagerItemFactory) {
        super.initAdapter(viewPagerItemFactory);
        this.mAdapter.setRecapEventListener(new g(11, this));
    }

    public boolean isPlayable() {
        ViewPagerListener viewPagerListener = this.mViewPagerListener;
        if (viewPagerListener == null || !viewPagerListener.isPlayable()) {
            return false;
        }
        return true;
    }

    public void pause() {
        this.mViewPager.pause();
    }

    public void resume() {
        this.mViewPager.resume();
    }

    public void start() {
        this.mViewPager.start();
    }

    public void stop() {
        this.mViewPager.stop();
    }

    public boolean supportFooter() {
        if (!PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY || !OnDemandStory.getInstance().isEnabled()) {
            return false;
        }
        return true;
    }

    public void transformPage(View view, float f) {
        super.transformPage(view, f);
        if (PocFeatures.SUPPORT_RECAP_STACK_UI) {
            HeightTransform.transform(this.mViewPager.getView(), view, f);
        }
    }

    public void updateData(MediaData mediaData, int i2) {
        if (this.mAdapter != null) {
            this.mViewPager.stop();
            updateDataInternal(mediaData, i2);
            this.mViewPager.start();
        }
    }
}
