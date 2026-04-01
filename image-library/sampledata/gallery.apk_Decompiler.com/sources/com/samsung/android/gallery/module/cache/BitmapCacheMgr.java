package com.samsung.android.gallery.module.cache;

import A4.I;
import K4.a;
import L5.b;
import android.graphics.Bitmap;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapCacheMgr<Object> extends MemoryCacheMgr<Object, Bitmap> {
    AbsCacheMgr$EvictListener<Object, Bitmap> mListener;
    private long mTotalBytes = 0;

    public BitmapCacheMgr(int i2, AbsCacheMgr$EvictListener<Object, Bitmap> absCacheMgr$EvictListener) {
        super(i2, (AbsCacheMgr$EvictListener) null);
        this.mListener = absCacheMgr$EvictListener;
        setListener(new a(6, this));
    }

    /* access modifiers changed from: private */
    public void OnBitmapEvicted(Object object, Bitmap bitmap) {
        this.mTotalBytes -= (long) bitmap.getAllocationByteCount();
        AbsCacheMgr$EvictListener<Object, Bitmap> absCacheMgr$EvictListener = this.mListener;
        if (absCacheMgr$EvictListener != null) {
            absCacheMgr$EvictListener.OnEvicted(object, bitmap);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$dumpIf$1(Map.Entry entry) {
        return " " + Logger.getEncodedString(entry.getKey()) + "=" + Logger.toString((Bitmap) entry.getValue());
    }

    public void clearCache() {
        super.clearCache();
        this.mTotalBytes = 0;
    }

    public String dumpIf(BiPredicate<Object, Bitmap> biPredicate) {
        return (String) snapshot().entrySet().stream().filter(new I(7, biPredicate)).map(new b(1)).collect(Collectors.joining("\n"));
    }

    public String toString() {
        return "{size=" + Logger.format(this.mTotalBytes) + ", count=" + getSize() + '}';
    }

    public void addCache(Object object, Bitmap bitmap) {
        if (bitmap != null) {
            this.mTotalBytes += (long) bitmap.getAllocationByteCount();
            super.addCache(object, bitmap);
        }
    }

    public Bitmap removeCache(Object object) {
        Bitmap bitmap = (Bitmap) super.removeCache(object);
        if (bitmap != null) {
            this.mTotalBytes -= (long) bitmap.getAllocationByteCount();
        }
        return bitmap;
    }

    public void clearCache(boolean z) {
        super.clearCache(z);
        this.mTotalBytes = 0;
    }
}
