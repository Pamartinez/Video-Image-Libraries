package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import A9.a;
import B8.e;
import O3.r;
import T3.f;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import x6.C0538a;
import x6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BlurUtil {
    public static void applyBlur(Blackboard blackboard, BlurInterface blurInterface, MediaItem mediaItem, boolean z) {
        if (mediaItem == null) {
            Log.e("BitmapUtils", "applyBlur failed {null}");
            return;
        }
        if (blackboard != null) {
            String headerCacheKey = LocationKey.getHeaderCacheKey("data://user/storyBlurBitmap", String.valueOf(mediaItem.getFileId()));
            Bitmap bitmap = (Bitmap) (z ? blackboard.pop(headerCacheKey, null) : blackboard.read(headerCacheKey, null));
            if (bitmap != null) {
                blurInterface.applyFilter(bitmap, new a(27, blurInterface, mediaItem));
                return;
            }
        }
        MediaItem mediaItem2 = mediaItem;
        ThumbnailLoader.getInstance().getOrLoad(mediaItem2, ThumbKind.FREE_KIND, new e(mediaItem, 1), new r(blurInterface, mediaItem2, System.currentTimeMillis(), 4));
    }

    /* access modifiers changed from: private */
    public static void bindBitmap(BlurInterface blurInterface, MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            blurInterface.bindBlurBitmap(mediaItem, bitmap);
        }
    }

    /* access modifiers changed from: private */
    public static void blurAndBindBitmap(BlurInterface blurInterface, MediaItem mediaItem, Bitmap bitmap, long j2) {
        Bitmap bitmap2;
        if (bitmap == null) {
            bitmap2 = getBrokenImage(mediaItem);
            Log.d("BlurUtil", "no image for blur");
        } else {
            bitmap2 = BitmapUtils.resize(bitmap, 64);
        }
        BlurInterface blurInterface2 = blurInterface;
        blurInterface2.applyFilter(bitmap2, new f(blurInterface2, mediaItem, j2, 1));
    }

    /* access modifiers changed from: private */
    public static void blurAndBindFilteredBitmap(BlurInterface blurInterface, MediaItem mediaItem, Bitmap bitmap, long j2) {
        int i2;
        Bitmap bitmap2;
        Bitmap blurAfterResize = BitmapUtils.blurAfterResize(AppResources.getAppContext(), bitmap, 64);
        if (mediaItem.isBroken() || mediaItem.isVideo()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        if (blurAfterResize != null) {
            bitmap2 = BitmapUtils.rotateBitmap(blurAfterResize, i2);
        } else {
            bitmap2 = null;
        }
        ThreadUtil.runOnUiThread(new C0538a(blurInterface, mediaItem, bitmap2, 1));
    }

    private static Bitmap getBrokenImage(MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$applyBlur$3(BlurInterface blurInterface, MediaItem mediaItem, long j2, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        if (ThreadUtil.isMainThread()) {
            SimpleThreadPool.getInstance().execute(new b(blurInterface, mediaItem, bitmap, j2, 0));
            return;
        }
        blurAndBindBitmap(blurInterface, mediaItem, bitmap, j2);
    }

    public static void applyBlur(BlurInterface blurInterface, MediaItem mediaItem, Bitmap bitmap) {
        long currentTimeMillis = System.currentTimeMillis();
        if (ThreadUtil.isMainThread()) {
            SimpleThreadPool.getInstance().execute(new b(blurInterface, mediaItem, bitmap, currentTimeMillis, 1));
        } else {
            blurAndBindFilteredBitmap(blurInterface, mediaItem, bitmap, currentTimeMillis);
        }
    }
}
