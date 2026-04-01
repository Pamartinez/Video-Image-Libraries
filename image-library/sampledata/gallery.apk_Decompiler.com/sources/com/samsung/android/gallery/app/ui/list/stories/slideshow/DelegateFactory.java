package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BgmPlayerDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomDecoViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ControlDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.FilmStripViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.FilterApplyDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GifPlayDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.OsdUiDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.RecapPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ReplayDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.VideoPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ViewPagerDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.WindowDecoDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.DataUpdateChecker;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.module.story.FilterHolder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.livemotion.DurationMeasure;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DelegateFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocalDurationMeasure extends DurationMeasure {
        private int mCount;
        private int mCurrentDuration;
        private int mTotalDuration;

        public /* synthetic */ LocalDurationMeasure(int i2) {
            this();
        }

        private int getOffsetTime(int i2, float f) {
            float f5;
            if (f > 0.99999f) {
                f5 = (float) i2;
            } else {
                f5 = ((float) i2) * f;
            }
            return (((int) f5) / 1000) * 1000;
        }

        public int getCurrentDuration() {
            return this.mCurrentDuration;
        }

        public int getDuration(ThumbnailInterface thumbnailInterface) {
            return 3000;
        }

        public int getTotalDuration() {
            return this.mTotalDuration;
        }

        public void measure(MediaData mediaData) {
            int count = mediaData.getCount();
            this.mCount = count;
            this.mTotalDuration = count * 3000;
        }

        public void update(int i2, float f, float... fArr) {
            int i7 = this.mCount;
            if (i7 != 0) {
                this.mCurrentDuration = ((i2 % i7) * 3000) + getOffsetTime(3000, f);
            }
        }

        private LocalDurationMeasure() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocalViewPagerDelegate extends ViewPagerDelegate {
        public LocalViewPagerDelegate(ISlideshowV2View iSlideshowV2View) {
            super(iSlideshowV2View);
        }

        public DataUpdateChecker createDataUpdaterChecker() {
            return new DataUpdateChecker() {
                public boolean handleDataChanged(MediaData mediaData, StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter) {
                    return false;
                }
            };
        }

        public boolean supportEndEffect() {
            return true;
        }

        public boolean supportFaceCircle() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ThemeProvideDelegate extends Delegate {
        private BgmExtraInfo mBgmExtraInfo;
        private final EffectTheme mTheme = EffectTheme.getRandomTheme();
        private final ISlideshowV2View mView;

        public ThemeProvideDelegate(ISlideshowV2View iSlideshowV2View) {
            super(iSlideshowV2View);
            this.mView = iSlideshowV2View;
        }

        private String getCurrentBgm() {
            String bgmName = this.mView.getBgmName();
            if (TextUtils.isEmpty(bgmName)) {
                return this.mTheme.getRandomBgm();
            }
            return bgmName;
        }

        private Filter getCurrentFilter() {
            String filterName = this.mView.getFilterName();
            if (TextUtils.isEmpty(filterName)) {
                return Filter.NONE;
            }
            return FilterHolder.findCurrentSepFilter(filterName);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Object lambda$addRequestProvider$0(DataRequest dataRequest, Object[] objArr) {
            return this.mTheme;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Object lambda$addRequestProvider$1(DataRequest dataRequest, Object[] objArr) {
            return getCurrentFilter();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Object lambda$addRequestProvider$2(DataRequest dataRequest, Object[] objArr) {
            if (this.mBgmExtraInfo == null) {
                BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
                this.mBgmExtraInfo = bgmExtraInfo;
                bgmExtraInfo.bgmName = getCurrentBgm();
            }
            return this.mBgmExtraInfo;
        }

        public void addRequestProvider() {
            addRequestProvider(DataRequest.REQ_EFFECT_THEME, new a(this, 0));
            addRequestProvider(DataRequest.REQ_FILTER_CURRENT, new a(this, 1));
            addRequestProvider(DataRequest.REQ_BGM_EXTRA_INFO, new a(this, 2));
        }

        public void onAttach() {
            this.mView.getEventHandler().lambda$postEvent$0(Event.ON_THEME_INITIALIZED, new Object[0]);
        }
    }

    public static Delegate create(ISlideshowV2View iSlideshowV2View) {
        return new ControlDelegate<ISlideshowV2View>(iSlideshowV2View) {
            public List<Delegate> create(ISlideshowV2View iSlideshowV2View) {
                ArrayList arrayList = new ArrayList();
                if (ArgumentsUtil.getArgValue(iSlideshowV2View.getLocationKey(), "slide_show_recap", 0) == 1) {
                    arrayList.add(new RecapPreviewDelegate(iSlideshowV2View));
                    return arrayList;
                }
                arrayList.add(new ThemeProvideDelegate(iSlideshowV2View));
                arrayList.add(new LocalViewPagerDelegate(iSlideshowV2View));
                arrayList.add(new WindowDecoDelegate(iSlideshowV2View));
                arrayList.add(new OsdUiDelegate(iSlideshowV2View));
                if (iSlideshowV2View.getOptions().supportBgm()) {
                    arrayList.add(new BgmPlayerDelegate(iSlideshowV2View));
                }
                arrayList.add(new FilmStripViewDelegate(iSlideshowV2View));
                arrayList.add(new VideoPreviewDelegate(iSlideshowV2View));
                arrayList.add(new BottomDecoViewDelegate(iSlideshowV2View) {
                    public DurationMeasure createDurationMeasure() {
                        return new LocalDurationMeasure(0);
                    }
                });
                arrayList.add(new FilterApplyDelegate(iSlideshowV2View));
                arrayList.add(new ReplayDelegate(iSlideshowV2View));
                arrayList.add(new GifPlayDelegate(iSlideshowV2View));
                return arrayList;
            }
        };
    }
}
