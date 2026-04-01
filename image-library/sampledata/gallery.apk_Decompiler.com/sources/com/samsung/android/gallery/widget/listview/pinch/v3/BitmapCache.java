package com.samsung.android.gallery.widget.listview.pinch.v3;

import A4.Q;
import Gb.a;
import J5.e;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapCache {
    private final HashMap<String, Bitmap> mBitmapCache = new HashMap<>();
    private final HashMap<String, List<Consumer<Bitmap>>> mRequester = new HashMap<>();

    private void addConsumer(String str, Consumer<Bitmap> consumer) {
        this.mRequester.computeIfAbsent(str, new a(21)).add(consumer);
    }

    private Bitmap getBrokenBitmap(MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    private float getRatio(int i2, int i7) {
        float f;
        float f5;
        if (i2 > i7) {
            f = (float) i2;
            f5 = (float) i7;
        } else {
            f = (float) i7;
            f5 = (float) i2;
        }
        return f / f5;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$addConsumer$2(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbLoaded$1(String str, Bitmap bitmap, ThumbnailInterface thumbnailInterface) {
        if (bitmap == null) {
            bitmap = getBrokenBitmap((MediaItem) thumbnailInterface);
        }
        notify(str, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestBitmap$0(ThumbnailInterface thumbnailInterface, String str, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbLoaded(thumbnailInterface, str, bitmap);
    }

    private void notify(String str, Bitmap bitmap) {
        List list = this.mRequester.get(str);
        if (list == null || list.isEmpty()) {
            recycle(bitmap);
            return;
        }
        put(str, bitmap);
        list.forEach(new e(bitmap, 1));
        list.clear();
    }

    private void onThumbLoaded(ThumbnailInterface thumbnailInterface, String str, Bitmap bitmap) {
        ThreadUtil.runOnUiThread(new A6.a(this, str, bitmap, thumbnailInterface));
    }

    private void recycle(Bitmap bitmap) {
        ThumbnailLoader.getInstance().recycle((String) null, bitmap);
    }

    public void clear() {
        this.mRequester.clear();
        this.mBitmapCache.clear();
    }

    public Bitmap get(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind) {
        return get(thumbnailInterface.getThumbCacheKey() + "_" + thumbKind);
    }

    public void put(String str, Bitmap bitmap) {
        this.mBitmapCache.put(str, bitmap.copy(Bitmap.Config.ARGB_8888, true));
    }

    public void requestBitmap(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, Consumer<Bitmap> consumer, boolean z) {
        String str = thumbnailInterface.getThumbCacheKey() + "_" + thumbKind;
        if (get(str) != null) {
            consumer.accept(get(str));
        } else if (thumbnailInterface.isBroken()) {
            consumer.accept(getBrokenBitmap((MediaItem) thumbnailInterface));
        } else {
            Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(thumbnailInterface, thumbKind);
            if (memCache != null) {
                consumer.accept(memCache);
                return;
            }
            Bitmap memCache2 = ThumbnailLoader.getInstance().getMemCache(thumbnailInterface, ThumbKind.FREE_KIND);
            if (memCache2 != null) {
                float ratio = getRatio(memCache2.getWidth(), memCache2.getHeight());
                float ratio2 = getRatio(thumbnailInterface.getWidth(), thumbnailInterface.getHeight());
                if (!z || ratio != 1.0f || ratio == ratio2) {
                    consumer.accept(memCache2);
                }
            }
            addConsumer(str, consumer);
            ThumbnailLoader.getInstance().loadThumbnail(thumbnailInterface, thumbKind, new Q((Object) this, (Object) thumbnailInterface, (Object) str, 5));
        }
    }

    public Bitmap get(String str) {
        return this.mBitmapCache.get(str);
    }
}
