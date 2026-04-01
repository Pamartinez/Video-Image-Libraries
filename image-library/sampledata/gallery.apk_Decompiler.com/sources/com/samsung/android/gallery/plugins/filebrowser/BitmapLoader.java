package com.samsung.android.gallery.plugins.filebrowser;

import A.a;
import I5.e;
import N2.j;
import android.content.Context;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.cache.MemoryCacheMgr;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import za.C0719a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapLoader {
    private static final ConcurrentHashMap<String, BitmapLoader> sBitmapLoaderMap = new ConcurrentHashMap<>();
    private final Object LOCK = new Object();
    private final String NAME;
    private int THUMBNAIL_SIZE = 512;
    private volatile ThreadPool mThreadPool;
    private final MemoryCacheMgr<String, BitmapHolder> mThumbnailCache = new MemoryCacheMgr<>(64, (AbsCacheMgr$EvictListener) null);

    private BitmapLoader(String str) {
        this.NAME = str;
    }

    private void decodeExif(BitmapHolder bitmapHolder, ExifInterface exifInterface) {
        bitmapHolder.exif = exifInterface;
        int orientationTag = ExifUtils.getOrientationTag(exifInterface);
        bitmapHolder.orientationTag = orientationTag;
        bitmapHolder.orientation = ExifTag.toOrientation(orientationTag);
        bitmapHolder.width = ExifUtils.getWidth(exifInterface);
        bitmapHolder.height = ExifUtils.getHeight(exifInterface);
        byte[] thumbnail = exifInterface.getThumbnail();
        if (thumbnail != null) {
            bitmapHolder.bitmap = ImageDecoder.decodeByteArray(thumbnail, 0, thumbnail.length, new BitmapOptions().withQuramCodec(bitmapHolder.isJpeg()).withHeifCodec(bitmapHolder.isHeif()));
        }
    }

    private void decodeFullImage(BitmapHolder bitmapHolder, byte[] bArr, int i2) {
        try {
            BitmapOptions adjustInSampleSizeSmallerThan = BitmapOptionsFactory.parse(bArr, 0, bArr.length).withQuramCodec(bitmapHolder.isJpeg()).withHeifCodec(bitmapHolder.isHeif()).adjustInSampleSizeSmallerThan(i2);
            bitmapHolder.width = adjustInSampleSizeSmallerThan.outWidth;
            bitmapHolder.height = adjustInSampleSizeSmallerThan.outHeight;
            bitmapHolder.bitmap = BitmapUtils.decodeByteArray(bArr, adjustInSampleSizeSmallerThan, 0);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("decodeFullImage#stream failed. e="), "BitmapLoader");
        }
    }

    private void decodeImage(BitmapHolder bitmapHolder, byte[] bArr, int i2) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            decodeExif(bitmapHolder, new ExifInterface((InputStream) byteArrayInputStream));
            byteArrayInputStream.close();
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (bitmapHolder.bitmap == null) {
            decodeFullImage(bitmapHolder, bArr, i2);
            return;
        }
        return;
        throw th;
    }

    private void decodeVideo(BitmapHolder bitmapHolder, byte[] bArr, int i2) {
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(new MediaDataSourceImpl(bArr));
            decodeVideo(bitmapHolder, mediaMetadataRetriever, i2);
            mediaMetadataRetriever.close();
            return;
        } catch (Exception e) {
            Log.e("BitmapLoader", "decodeVideo#stream failed {" + e.getMessage() + "}");
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static /* synthetic */ BitmapLoader g(String str) {
        return new BitmapLoader(str);
    }

    private ThreadPool getThreadPool() {
        if (this.mThreadPool == null) {
            synchronized (this.LOCK) {
                try {
                    if (this.mThreadPool == null) {
                        this.mThreadPool = ThreadPool.createPrivateInstance("md-" + hashCode());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.mThreadPool;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BitmapHolder lambda$decodeThumbnail$0(String str, ThreadPool.JobContext jobContext) {
        return decodeThumbnail(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$decodeThumbnail$1(String str, BitmapHolder bitmapHolder) {
        this.mThumbnailCache.addCache(str, bitmapHolder);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$decodeThumbnail$2(String str, Consumer consumer, Future future) {
        Optional.ofNullable((BitmapHolder) future.get()).ifPresent(new b(this, str, 1));
        consumer.accept((BitmapHolder) future.get());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BitmapHolder lambda$decodeThumbnail$3(ZipEntryHolder zipEntryHolder, ThreadPool.JobContext jobContext) {
        return decodeThumbnail(zipEntryHolder.read());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$decodeThumbnail$4(String str, BitmapHolder bitmapHolder) {
        this.mThumbnailCache.addCache(str, bitmapHolder);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$decodeThumbnail$5(String str, Consumer consumer, Future future) {
        Optional.ofNullable((BitmapHolder) future.get()).ifPresent(new b(this, str, 0));
        consumer.accept((BitmapHolder) future.get());
    }

    public static BitmapLoader of(Context context) {
        return of(context.toString());
    }

    public Future<BitmapHolder> decodeThumbnail(String str, Consumer<BitmapHolder> consumer) {
        StringBuilder k = j.k("file:", str, NumericEnum.SEP);
        k.append(FileUtils.length(str));
        String sb2 = k.toString();
        BitmapHolder cache = this.mThumbnailCache.getCache(sb2);
        if (cache == null) {
            return getThreadPool().submit(new e(12, this, str), new C0719a(this, sb2, consumer, 0));
        }
        consumer.accept(cache);
        return null;
    }

    public BitmapHolder getCache(ZipEntryHolder zipEntryHolder) {
        MemoryCacheMgr<String, BitmapHolder> memoryCacheMgr = this.mThumbnailCache;
        return memoryCacheMgr.getCache("zip:" + zipEntryHolder.getName() + NumericEnum.SEP + zipEntryHolder.getSize());
    }

    public void release() {
        sBitmapLoaderMap.remove(this.NAME);
        synchronized (this.LOCK) {
            if (this.mThreadPool != null) {
                try {
                    this.mThreadPool.shutDown();
                } catch (Exception unused) {
                }
                this.mThreadPool = null;
            }
        }
        this.mThumbnailCache.clearCache();
    }

    public String toString() {
        return "BitmapLoader{" + Logger.getSimpleName(this.NAME) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mThumbnailCache.getSize() + "}";
    }

    public static BitmapLoader of(String str) {
        return sBitmapLoaderMap.computeIfAbsent(str, new y7.j(1));
    }

    private void decodeImage(BitmapHolder bitmapHolder, String str, int i2) {
        try {
            decodeExif(bitmapHolder, new ExifInterface(str));
        } catch (Exception unused) {
        }
        if (bitmapHolder.bitmap == null) {
            decodeFullImage(bitmapHolder, str, i2);
        }
    }

    private void decodeVideo(BitmapHolder bitmapHolder, String str, int i2) {
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            decodeVideo(bitmapHolder, mediaMetadataRetriever, i2);
            mediaMetadataRetriever.close();
            return;
        } catch (Exception e) {
            Log.e("BitmapLoader", "decodeVideo#file failed {" + e.getMessage() + "}");
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Future<BitmapHolder> decodeThumbnail(ZipEntryHolder zipEntryHolder, Consumer<BitmapHolder> consumer) {
        String str = "zip:" + zipEntryHolder.getName() + NumericEnum.SEP + zipEntryHolder.getSize();
        BitmapHolder cache = this.mThumbnailCache.getCache(str);
        if (cache == null) {
            return getThreadPool().submit(new a(this, zipEntryHolder), new C0719a(this, str, consumer, 1));
        }
        consumer.accept(cache);
        return null;
    }

    private void decodeFullImage(BitmapHolder bitmapHolder, String str, int i2) {
        try {
            BitmapOptions adjustInSampleSize = BitmapOptionsFactory.parse(str).withQuramCodec(bitmapHolder.isJpeg()).withHeifCodec(bitmapHolder.isHeif()).adjustInSampleSize(i2);
            bitmapHolder.width = adjustInSampleSize.outWidth;
            bitmapHolder.height = adjustInSampleSize.outHeight;
            bitmapHolder.bitmap = BitmapUtils.getDecodedBitmap(str, adjustInSampleSize);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("decodeFullImage#file failed. e="), "BitmapLoader");
        }
    }

    private void decodeVideo(BitmapHolder bitmapHolder, MediaMetadataRetriever mediaMetadataRetriever, int i2) {
        bitmapHolder.width = UnsafeCast.toInt(mediaMetadataRetriever.extractMetadata(18), 0);
        bitmapHolder.height = UnsafeCast.toInt(mediaMetadataRetriever.extractMetadata(19), 0);
        bitmapHolder.orientation = UnsafeCast.toInt(mediaMetadataRetriever.extractMetadata(24), 0);
        float max = ((float) i2) / ((float) Math.max(bitmapHolder.width, bitmapHolder.height));
        if (max < 1.0f) {
            SeApiCompat.setVideoSize(mediaMetadataRetriever, Math.round(((float) bitmapHolder.width) * max), Math.round(((float) bitmapHolder.height) * max));
        }
        bitmapHolder.bitmap = mediaMetadataRetriever.getFrameAtTime(0);
    }

    private BitmapHolder decodeThumbnail(byte[] bArr) {
        BitmapHolder bitmapHolder = new BitmapHolder();
        long currentTimeMillis = System.currentTimeMillis();
        String mimeType = FileType.getMimeType(bArr);
        bitmapHolder.mimeType = mimeType;
        if (mimeType.startsWith("video")) {
            decodeVideo(bitmapHolder, bArr, this.THUMBNAIL_SIZE);
        } else {
            decodeImage(bitmapHolder, bArr, this.THUMBNAIL_SIZE);
        }
        if (bitmapHolder.bitmap == null) {
            Log.e("BitmapLoader", "decodeThumbnail#stream failed" + Logger.vt(bitmapHolder, Integer.valueOf(bArr.length), Long.valueOf(currentTimeMillis)));
        }
        return bitmapHolder;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaDataSourceImpl extends MediaDataSource {
        final byte[] data;

        public MediaDataSourceImpl(byte[] bArr) {
            this.data = bArr;
        }

        public long getSize() {
            return (long) this.data.length;
        }

        public int readAt(long j2, byte[] bArr, int i2, int i7) {
            byte[] bArr2 = this.data;
            if (((long) i7) + j2 > ((long) bArr2.length)) {
                i7 = bArr2.length - ((int) j2);
            }
            System.arraycopy(bArr2, (int) j2, bArr, i2, i7);
            return i7;
        }

        public void close() {
        }
    }

    private BitmapHolder decodeThumbnail(String str) {
        BitmapHolder bitmapHolder = new BitmapHolder();
        long currentTimeMillis = System.currentTimeMillis();
        String mimeType = FileType.getMimeType(str);
        bitmapHolder.mimeType = mimeType;
        if (mimeType.startsWith("video")) {
            decodeVideo(bitmapHolder, str, this.THUMBNAIL_SIZE);
        } else {
            decodeImage(bitmapHolder, str, this.THUMBNAIL_SIZE);
        }
        if (bitmapHolder.bitmap == null) {
            Log.e("BitmapLoader", "decodeThumbnail#file failed" + Logger.vt(bitmapHolder, str, Long.valueOf(currentTimeMillis)));
        }
        return bitmapHolder;
    }
}
