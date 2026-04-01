package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import A.a;
import E9.b;
import a8.d;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.cache.BitmapCacheMgr;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.livemotion.LiveMotionAdapter;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.List;
import java.util.function.BiConsumer;
import t8.e;
import w4.C0533c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightViewPagerAdapter extends LiveMotionAdapter<ViewPagerHolder> implements IStoryHighlightViewPagerAdapter {
    /* access modifiers changed from: private */
    public final LruCache<String, Bitmap> mBlurCache = new LruCache<>(7);
    private int mDataCount;
    private final BitmapCacheMgr<Integer> mFilterThumbCache;
    private final FocusPositionHolder mFocusPositionHolder = new FocusPositionHolder();
    private final LargeImageLoader<ILargeImage> mImageLoader;
    private final LastBoundInfo mLastBoundInfo = new LastBoundInfo(0);
    /* access modifiers changed from: private */
    public final IStoryHighlightView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class BlurBinder implements BlurInterface {
        final ViewPagerHolder vh;

        public /* synthetic */ BlurBinder(StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter, ViewPagerHolder viewPagerHolder, int i2) {
            this(viewPagerHolder);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$bindBlurBitmap$0(MediaItem mediaItem, Bitmap bitmap) {
            if (StoryHighlightViewPagerAdapter.this.equal(mediaItem, this.vh.getMediaItem())) {
                StoryHighlightViewPagerAdapter.this.mBlurCache.put(mediaItem.getThumbCacheKey(), bitmap);
                StoryHighlightViewPagerAdapter.this.bindBlurBitmap(this.vh, mediaItem, bitmap);
            }
        }

        public void applyFilter(Bitmap bitmap, BiConsumer<Bitmap, Filter> biConsumer) {
            StoryHighlightViewPagerAdapter.this.mView.getEventHandler().lambda$postEvent$0(Event.APPLY_FILTER_TO_BITMAP, bitmap, Boolean.FALSE, biConsumer);
        }

        public void bindBlurBitmap(MediaItem mediaItem, Bitmap bitmap) {
            ThreadUtil.runOnUiThread(new b(this, mediaItem, bitmap));
        }

        private BlurBinder(ViewPagerHolder viewPagerHolder) {
            this.vh = viewPagerHolder;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LastBoundInfo {
        Bitmap bitmap;
        Bitmap blurBitmap;
        int orientation;
        int position;

        public /* synthetic */ LastBoundInfo(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public void clear() {
            this.bitmap = null;
            this.blurBitmap = null;
            this.orientation = 0;
            this.position = -1;
        }

        private LastBoundInfo() {
        }
    }

    public StoryHighlightViewPagerAdapter(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        this.mImageLoader = new LargeImageLoader<>(iStoryHighlightView);
        this.mFilterThumbCache = createFilterThumbCache();
    }

    /* access modifiers changed from: private */
    public void bindBlurBitmap(ViewPagerHolder viewPagerHolder, MediaItem mediaItem, Bitmap bitmap) {
        if (viewPagerHolder != null && bitmap != null) {
            viewPagerHolder.bindBlurBitmap(mediaItem, bitmap);
            this.mLastBoundInfo.blurBitmap = bitmap;
        }
    }

    private void clearFilterCache() {
        BitmapCacheMgr<Integer> bitmapCacheMgr = this.mFilterThumbCache;
        if (bitmapCacheMgr != null) {
            bitmapCacheMgr.clearCache();
        }
    }

    private BitmapCacheMgr<Integer> createFilterThumbCache() {
        if (this.mView.getOptions().supportFilter()) {
            return new BitmapCacheMgr<>(5, (AbsCacheMgr$EvictListener) null);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean equal(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId()) {
            return false;
        }
        return true;
    }

    private int findHeaderDataPosition() {
        MediaItem mediaItem;
        int i2;
        IStoryHighlightView iStoryHighlightView = this.mView;
        if (iStoryHighlightView != null) {
            mediaItem = iStoryHighlightView.getHeaderItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem == null) {
            return -1;
        }
        MediaData mediaData = this.mData;
        if (mediaData != null) {
            i2 = mediaData.getCount();
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            MediaItem read = mediaData.read(i7);
            if (read != null && read.getFileId() == mediaItem.getFileId()) {
                return i7;
            }
        }
        return -1;
    }

    private Bitmap getBlurFromBlackBoard(MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        String headerCacheKey = LocationKey.getHeaderCacheKey("data://user/storyBlurBitmap", String.valueOf(mediaItem.getFileId()));
        if (this.mView.getBlackboard() != null) {
            return (Bitmap) this.mView.getBlackboard().pop(headerCacheKey, null);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public int getCurrentItem() {
        return this.mFocusPositionHolder.getPosition();
    }

    private int getFilterCacheKey(MediaItem mediaItem, Filter filter) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(mediaItem.getThumbCacheKey());
        sb2.append("_");
        if (filter != null) {
            str = filter.getRawName();
        } else {
            str = "";
        }
        sb2.append(str);
        return sb2.toString().hashCode();
    }

    private boolean isFirstItem(ViewPagerHolder viewPagerHolder) {
        try {
            if (viewPagerHolder.getAbsoluteAdapterPosition() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to get position e"), "StoryHighlightViewPagerAdapter");
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$0(ViewPagerHolder viewPagerHolder, Bitmap bitmap) {
        LastBoundInfo lastBoundInfo = this.mLastBoundInfo;
        viewPagerHolder.bindBackupThumbnail(bitmap, lastBoundInfo.bitmap, lastBoundInfo.blurBitmap, lastBoundInfo.orientation);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$1(MediaItem mediaItem, ViewPagerHolder viewPagerHolder, Bitmap bitmap) {
        if (equal(mediaItem, viewPagerHolder.getMediaItem())) {
            super.onThumbnailLoadCompleted(viewPagerHolder, mediaItem, bitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$2(MediaItem mediaItem, ViewPagerHolder viewPagerHolder, Bitmap bitmap, Filter filter) {
        Filter filter2 = this.mView.getEventHandler().getFilter();
        if (this.mFilterThumbCache != null && !filter.noneFilter() && filter == filter2) {
            this.mFilterThumbCache.addCache(Integer.valueOf(getFilterCacheKey(mediaItem, filter)), bitmap);
        }
        ThreadUtil.runOnUiThread(new d((Object) this, (Object) mediaItem, (Object) viewPagerHolder, (Object) bitmap, 25));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestLargeImage$6(MediaItem mediaItem, ViewPagerHolder viewPagerHolder, String str) {
        if (equal(mediaItem, viewPagerHolder.getMediaItem())) {
            this.mImageLoader.requestOriginalImage(viewPagerHolder, str);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLastBoundBitmap$3() {
        PreviewViewHolder currentViewHolder = this.mView.getEventHandler().getCurrentViewHolder();
        if (currentViewHolder != null && currentViewHolder.hasBitmap() && currentViewHolder.getMediaItem() != null) {
            this.mLastBoundInfo.position = currentViewHolder.getAbsoluteAdapterPosition();
            this.mLastBoundInfo.bitmap = currentViewHolder.getBitmap();
            this.mLastBoundInfo.orientation = currentViewHolder.getMediaItem().getOrientation();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$validateOriginalImage$7(MediaItem mediaItem, RecyclerView.ViewHolder viewHolder) {
        ViewPagerHolder viewPagerHolder = (ViewPagerHolder) viewHolder;
        if (equal(mediaItem, viewPagerHolder.getMediaItem())) {
            this.mImageLoader.validateOriginalImage(viewPagerHolder);
        }
    }

    private void requestLargeImage(MediaItem mediaItem, ViewPagerHolder viewPagerHolder, String str) {
        ThreadUtil.postOnUiThreadDelayed(new d((Object) this, (Object) mediaItem, (Object) viewPagerHolder, (Object) str, 24), 75);
    }

    private void restorePosition(MediaData mediaData) {
        this.mFocusPositionHolder.restore(mediaData);
    }

    /* renamed from: bindBlurThumbnail */
    public void lambda$onBindViewHolder$5(MediaItem mediaItem, ViewPagerHolder viewPagerHolder) {
        if (mediaItem != null) {
            BlurBinder blurBinder = new BlurBinder(this, viewPagerHolder, 0);
            Bitmap blurFromBlackBoard = getBlurFromBlackBoard(mediaItem);
            if (blurFromBlackBoard != null) {
                blurBinder.bindBlurBitmap(mediaItem, blurFromBlackBoard);
                blurBinder.applyFilter(blurFromBlackBoard, new a(blurBinder, mediaItem));
                return;
            }
            Bitmap bitmap = this.mBlurCache.get(mediaItem.getThumbCacheKey());
            if (bitmap != null) {
                bindBlurBitmap(viewPagerHolder, mediaItem, bitmap);
            } else if (this.mFilterThumbCache != null) {
                Bitmap bitmap2 = (Bitmap) this.mFilterThumbCache.getCache(Integer.valueOf(getFilterCacheKey(mediaItem, this.mView.getEventHandler().getFilter())));
                if (bitmap2 != null) {
                    BlurUtil.applyBlur(blurBinder, mediaItem, bitmap2);
                } else {
                    BlurUtil.applyBlur(this.mView.getBlackboard(), blurBinder, mediaItem, true);
                }
            } else {
                BlurUtil.applyBlur(this.mView.getBlackboard(), blurBinder, mediaItem, true);
            }
        }
    }

    public void clearFocusedPositionHolder() {
        this.mFocusPositionHolder.clear();
        clearFilterCache();
    }

    public void destroy() {
        if (this.mBlurCache.size() > 0) {
            this.mBlurCache.evictAll();
        }
        clearFilterCache();
        this.mImageLoader.destroy();
        this.mLastBoundInfo.clear();
    }

    public int getDataPosition(int i2) {
        MediaData mediaData = this.mData;
        if (mediaData == null || mediaData.getCount() == 0 || i2 < 0) {
            return -1;
        }
        return i2;
    }

    public int getFocusDataPosition() {
        int position = this.mFocusPositionHolder.getPosition();
        if (position != -1) {
            return position;
        }
        return super.getFocusDataPosition();
    }

    public int getItemCount() {
        return this.mDataCount;
    }

    public int getItemViewType(int i2) {
        return ViewPagerHolderFactory.getType(getMediaItem(i2), this.mView.getOptions().isSlideshow());
    }

    public ThumbKind getThumbKind(MediaItem mediaItem) {
        return ThumbKind.MEDIUM_KIND;
    }

    public void invalidateData() {
        MediaData mediaData = this.mData;
        if (mediaData != null) {
            this.mDataCount = mediaData.getRealCount();
        }
    }

    public boolean isLast(int i2) {
        if (i2 == getItemCount() - 1) {
            return true;
        }
        return false;
    }

    public boolean isVideo() {
        return this.mFocusPositionHolder.isVideo();
    }

    public void keepPause(boolean z) {
        if (PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE) {
            notifyItemRangeChanged(0, getItemCount(), "PAYLOAD_CHANGE_KEEP_PAUSE_STATE");
        }
    }

    public void onFilterChanged() {
        if (this.mBlurCache.size() > 0) {
            this.mBlurCache.evictAll();
        }
        clearFilterCache();
        this.mLastBoundInfo.clear();
    }

    public void onTrimMemory(int i2) {
        clearFilterCache();
        this.mImageLoader.clearFilterCache();
    }

    public boolean prepareNext(int i2) {
        if (i2 >= getItemCount() || !super.prepareNext(i2)) {
            return false;
        }
        return true;
    }

    public void setData(MediaData mediaData) {
        if (mediaData != null) {
            updateLastBoundBitmap();
            restorePosition(mediaData);
            super.setData(mediaData);
            this.mDataCount = mediaData.getRealCount();
        }
    }

    public void updateCurrentPosition(int i2) {
        int dataPosition = getDataPosition(i2);
        this.mFocusPositionHolder.update(getMediaItem(dataPosition), dataPosition);
        if (PreferenceFeatures.OneUi7x.SUPPORT_STORY_LIVE_EFFECT_TYPE) {
            this.mView.getEventHandler().lambda$postEvent$0(Event.ENABLE_LIVE_EFFECT_DOWNLOAD, Boolean.valueOf(MediaItemStory.isLiveEffectItem(getMediaItem(i2))));
        }
    }

    public void updateHeaderData() {
        setHeaderDataPosition(findHeaderDataPosition());
    }

    public void updateLastBoundBitmap() {
        ThreadUtil.runOnUiThread(new e(27, this));
    }

    public void validateOriginalImage(int i2, RecyclerView.ViewHolder viewHolder) {
        if ((viewHolder instanceof ViewPagerHolder) && i2 == viewHolder.getAbsoluteAdapterPosition()) {
            ViewPagerHolder viewPagerHolder = (ViewPagerHolder) viewHolder;
            ThreadUtil.postOnUiThreadDelayed(new x6.d(this, viewPagerHolder.getMediaItem(), viewPagerHolder, 1), 75);
        }
    }

    public void bindThumbnail(MediaItem mediaItem, ViewPagerHolder viewPagerHolder) {
        Bitmap bitmap;
        Filter filter = this.mView.getEventHandler().getFilter();
        viewPagerHolder.setFilterMode(!filter.noneFilter());
        BitmapCacheMgr<Integer> bitmapCacheMgr = this.mFilterThumbCache;
        if (bitmapCacheMgr == null || (bitmap = (Bitmap) bitmapCacheMgr.getCache(Integer.valueOf(getFilterCacheKey(mediaItem, filter)))) == null) {
            super.bindThumbnail(mediaItem, viewPagerHolder);
            lambda$onBindViewHolder$5(mediaItem, viewPagerHolder);
            return;
        }
        super.onThumbnailLoadCompleted(viewPagerHolder, mediaItem, bitmap);
        lambda$onBindViewHolder$5(mediaItem, viewPagerHolder);
    }

    public ViewPagerHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return ViewPagerHolderFactory.createViewPagerHolder(viewGroup, i2);
    }

    public void onThumbnailLoadCompleted(ViewPagerHolder viewPagerHolder, MediaItem mediaItem, Bitmap bitmap) {
        if (this.mLastBoundInfo.bitmap != null) {
            ThreadUtil.runOnUiThread(new C0533c(this, viewPagerHolder, bitmap, 3));
        }
        this.mView.getEventHandler().lambda$postEvent$0(Event.APPLY_FILTER_TO_BITMAP, bitmap, Boolean.valueOf(mediaItem.isVideo() || isFirstItem(viewPagerHolder)), new b(this, mediaItem, viewPagerHolder, 5));
    }

    public void onViewAttachedToWindow(ViewPagerHolder viewPagerHolder) {
        super.onViewAttachedToWindow(viewPagerHolder);
        if (this.mView.getEventHandler().isShowingRelatedView() && isLast(getDataPosition(viewPagerHolder.getViewPosition()))) {
            ViewUtils.setAlpha(viewPagerHolder.getTransformParentLayout(), 0.0f);
        }
        requestLargeImage(viewPagerHolder.getMediaItem(), viewPagerHolder, "onViewAttachedToWindow");
    }

    public void onViewDetachedFromWindow(ViewPagerHolder viewPagerHolder) {
        super.onViewDetachedFromWindow(viewPagerHolder);
        viewPagerHolder.resetViewProperty();
        this.mImageLoader.cancelRequest(viewPagerHolder);
    }

    public void onViewRecycled(ViewPagerHolder viewPagerHolder) {
        viewPagerHolder.recycle();
    }

    public void onBindViewHolder(ViewPagerHolder viewPagerHolder, int i2) {
        if (PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE) {
            viewPagerHolder.setOriginScale(this.mView.getEventHandler().isKeepPaused(), false);
        }
        super.onBindViewHolder(viewPagerHolder, i2);
        if (viewPagerHolder.itemView.isAttachedToWindow()) {
            requestLargeImage(viewPagerHolder.getMediaItem(), viewPagerHolder, "onBindViewHolder");
        }
    }

    public void onBindViewHolder(ViewPagerHolder viewPagerHolder, int i2, List<Object> list) {
        if (list.contains("PAYLOAD_FILTER")) {
            MediaItem mediaItem = viewPagerHolder.getMediaItem();
            if (mediaItem != null) {
                viewPagerHolder.onFilterChanged();
                if (mediaItem.isVideo()) {
                    bindThumbnail(mediaItem, viewPagerHolder);
                    return;
                }
                this.mImageLoader.requestOriginalImage(viewPagerHolder, "payloads");
                ThreadUtil.postOnUiThreadDelayed(new x6.d(this, mediaItem, viewPagerHolder, 0), 100);
            }
        } else if (!PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE || !list.contains("PAYLOAD_CHANGE_KEEP_PAUSE_STATE")) {
            viewPagerHolder.setPositionSupplier(new v5.a(4, this));
            super.onBindViewHolder(viewPagerHolder, i2, list);
        } else {
            viewPagerHolder.setOriginScale(this.mView.getEventHandler().isKeepPaused(), i2 != getFocusDataPosition());
        }
    }
}
