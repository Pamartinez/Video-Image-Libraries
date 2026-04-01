package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import B5.c;
import E9.b;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ILargeImage;
import com.samsung.android.gallery.module.cache.BitmapCacheMgr;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import o4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LargeImageLoader<T extends ILargeImage> {
    private final Blackboard mBlackboard;
    private BitmapCacheMgr<Integer> mFilterLargeCache;
    private final HashMap<String, SubscriberListener> mSubscribers = new HashMap<>();
    private final IStoryHighlightView mView;

    public LargeImageLoader(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        this.mBlackboard = iStoryHighlightView.getBlackboard();
        createFilterLargeCache();
    }

    private void bindOriginImage(MediaItem mediaItem, T t, Bitmap bitmap) {
        if (bitmap == null) {
            cancelRequest(mediaItem);
            return;
        }
        this.mView.getEventHandler().postEvent(Event.APPLY_FILTER_TO_BITMAP, bitmap, Boolean.TRUE, new b(this, mediaItem, t, 4));
    }

    private boolean equal(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId()) {
            return false;
        }
        return true;
    }

    private int getFilterCacheKey(MediaItem mediaItem, Filter filter) {
        return (mediaItem.getThumbCacheKey() + "_" + filter.getRawName()).hashCode();
    }

    private String getImageDecodeKey(MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        return "data://bitmap/viewer/" + mediaItem.getSimpleHashCode();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindOriginImage$1(Bitmap bitmap, MediaItem mediaItem, ILargeImage iLargeImage, Filter filter) {
        if (bitmap != null && equal(mediaItem, iLargeImage.getMediaItem())) {
            if (this.mFilterLargeCache != null && !filter.noneFilter()) {
                this.mFilterLargeCache.addCache(Integer.valueOf(getFilterCacheKey(mediaItem, filter)), bitmap);
            }
            iLargeImage.bindOriginImage(bitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindOriginImage$2(MediaItem mediaItem, ILargeImage iLargeImage, Bitmap bitmap, Filter filter) {
        ThreadUtil.runOnUiThread(new c(this, bitmap, mediaItem, iLargeImage, filter, 29));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDecodeOriginalImage$0(MediaItem mediaItem, ILargeImage iLargeImage, Object obj, Bundle bundle) {
        bindOriginImage(mediaItem, iLargeImage, (Bitmap) obj);
    }

    private void requestDecodeOriginalImage(T t, String str) {
        if (t != null && t.getMediaItem() != null) {
            MediaItem mediaItem = t.getMediaItem();
            String imageDecodeKey = getImageDecodeKey(mediaItem);
            Bitmap bitmap = (Bitmap) this.mBlackboard.read(imageDecodeKey, null);
            if (bitmap != null || !t.getRootView().isAttachedToWindow()) {
                bindOriginImage(mediaItem, t, bitmap);
                return;
            }
            unsubscribeRequest(imageDecodeKey);
            x6.c cVar = new x6.c(this, mediaItem, t);
            this.mSubscribers.put(imageDecodeKey, cVar);
            this.mBlackboard.subscribeOnUi(imageDecodeKey, cVar);
            BlackboardUtils.requestViewerBitmap(this.mBlackboard, mediaItem, false);
        }
    }

    private void unsubscribeRequest(String str) {
        if (this.mSubscribers.containsKey(str)) {
            this.mBlackboard.unsubscribe(str, this.mSubscribers.get(str));
        }
    }

    public void cancelRequest(T t) {
        cancelRequest(t.getMediaItem());
        if (MediaItemStory.isAiEditEffect(t.getMediaItem())) {
            Optional.ofNullable((MediaItem) MediaItemStory.getEffectItem(t.getMediaItem())).ifPresent(new a(28, this));
        }
    }

    public void clearFilterCache() {
        BitmapCacheMgr<Integer> bitmapCacheMgr = this.mFilterLargeCache;
        if (bitmapCacheMgr != null) {
            bitmapCacheMgr.clearCache();
        }
    }

    public void destroy() {
        Iterator it = new ArrayList(this.mSubscribers.keySet()).iterator();
        while (it.hasNext()) {
            cancelRequest((String) it.next());
        }
        clearFilterCache();
    }

    public void requestOriginalImage(T t, String str) {
        requestOriginalImageInternal(t, str);
        if (MediaItemStory.isAiEditEffect(t.getMediaItem()) && ((MediaItem) MediaItemStory.getEffectItem(t.getMediaItem())) != null) {
            requestOriginalImageInternal(new AIEditImageBinder(t), str);
        }
    }

    public void requestOriginalImageInternal(T t, String str) {
        MediaItem mediaItem = t.getMediaItem();
        if (mediaItem != null && !mediaItem.isBroken() && !mediaItem.isVideo()) {
            if (this.mFilterLargeCache != null) {
                Bitmap bitmap = (Bitmap) this.mFilterLargeCache.getCache(Integer.valueOf(getFilterCacheKey(mediaItem, this.mView.getEventHandler().getFilter())));
                if (bitmap != null) {
                    t.bindOriginImage(bitmap);
                    return;
                }
            }
            requestDecodeOriginalImage(t, str);
        }
    }

    public void validateOriginalImage(T t) {
        if (!t.isOriginImageLoaded() && !this.mSubscribers.containsKey(getImageDecodeKey(t.getMediaItem()))) {
            requestOriginalImage(t, "validateOriginalImage");
        }
    }

    /* access modifiers changed from: private */
    public void cancelRequest(MediaItem mediaItem) {
        cancelRequest(getImageDecodeKey(mediaItem));
    }

    private void cancelRequest(String str) {
        unsubscribeRequest(str);
        BlackboardUtils.cancelAndEraseViewerBitmap(this.mBlackboard, str);
        this.mSubscribers.remove(str);
    }

    private void createFilterLargeCache() {
    }
}
