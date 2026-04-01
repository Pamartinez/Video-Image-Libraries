package com.samsung.android.gallery.module.media;

import Da.f;
import F9.d;
import F9.e;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.XmpType;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.MediaCacheLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.module.xmp.XmpUtils;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.FileInputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MetadataManager {
    private static final MetadataManager sInstance = new MetadataManager();
    private final LruCache<Integer, ExifTag> mExifCache = new LruCache<>(10);
    protected final ConcurrentHashMap<Integer, MediaHelper.VideoInfo> mMap = new ConcurrentHashMap<>();
    protected final LruCache<Integer, AtomicReference<Bitmap>> mMotionPreviewCache = new LruCache<>(5);

    private ExifTag extractExif(int i2, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        ExifTag exifTag = new ExifTag(ExifUtils.getExif(str), PocFeatures.isEnabled(PocFeatures.MoreInfoExif));
        this.mExifCache.put(Integer.valueOf(i2), exifTag);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 10) {
            Log.w("MetadataManager", "extractExif {" + i2 + "} +" + currentTimeMillis2);
        }
        return exifTag;
    }

    private MediaHelper.VideoInfo extractVideoInfo(FileItemInterface fileItemInterface) {
        String str;
        if (fileItemInterface.isSharing()) {
            str = MediaItemMde.getDownloadVideoPath(fileItemInterface);
        } else {
            str = fileItemInterface.getPath();
        }
        MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(str);
        if (videoInfo.width == 0 || videoInfo.height == 0) {
            videoInfo.width = fileItemInterface.getWidth();
            videoInfo.height = fileItemInterface.getHeight();
            videoInfo.orientation = fileItemInterface.getOrientation();
            Log.e("MetadataManager", "getVideoInfo failed {" + fileItemInterface.getMediaId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileItemInterface.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileItemInterface.getMimeType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + videoInfo.width + GlobalPostProcInternalPPInterface.SPLIT_REGEX + videoInfo.height + GlobalPostProcInternalPPInterface.SPLIT_REGEX + videoInfo.orientation + "}");
        }
        if (videoInfo.length == 0) {
            videoInfo.length = fileItemInterface.getFileSize();
        }
        return videoInfo;
    }

    private MediaHelper.VideoInfo extractVideoInfoFromMotionPhoto(FileItemInterface fileItemInterface, VideoReqInfo videoReqInfo) {
        String str;
        long[] jArr;
        FileInputStream fileInputStream;
        Throwable th;
        long j2;
        FileItemInterface fileItemInterface2 = fileItemInterface;
        if (fileItemInterface2.isSharing()) {
            str = MediaItemMde.getDownloadVideoPath(fileItemInterface2);
        } else {
            str = VideoPropData.getLongExposurePath(fileItemInterface2);
        }
        if (str != null && (fileItemInterface2.isLocal() || ((fileItemInterface2.isCloudOnly() && MediaItemUtil.supportCloudPreviewPlay((MediaItem) fileItemInterface2)) || fileItemInterface2.isSharing() || fileItemInterface2.isPrivateItem() || VideoPropData.of(fileItemInterface2).longExposure || RemoteGalleryUtil.isRemoteFile(fileItemInterface2)))) {
            if (!PreferenceFeatures.OneUi30.SUPPORT_GOOGLE_MOTION_PHOTO || fileItemInterface2.getXmpType() != XmpType.XmpMotionPhoto) {
                jArr = MotionPhotoUtils.getVideoStreamInfoFromMotionPhoto(str, videoReqInfo);
            } else {
                if (fileItemInterface2.getFileSize() > 0) {
                    j2 = fileItemInterface2.getFileSize();
                } else {
                    j2 = FileUtils.length(str);
                }
                jArr = XmpUtils.getVideoStreamInfoFromGoogleMotionPhoto(str, j2);
            }
            if (jArr != null && jArr[1] > 0) {
                try {
                    fileInputStream = new FileInputStream(str);
                    MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(fileInputStream, jArr[0], jArr[1]);
                    if (!isValidVideo(fileItemInterface2, videoInfo)) {
                        Log.e("MetadataManager", "getVideoInfo(MotionPhoto) wrong ratio Item{" + fileItemInterface2.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileItemInterface2.getHeight() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileItemInterface2.getOrientation() + "} Video{" + videoInfo.width + GlobalPostProcInternalPPInterface.SPLIT_REGEX + videoInfo.height + GlobalPostProcInternalPPInterface.SPLIT_REGEX + videoInfo.orientation + "}");
                        videoInfo.length = 0;
                    }
                    fileInputStream.close();
                    return videoInfo;
                } catch (Exception unused) {
                    Log.e("MetadataManager", "getVideoInfo(MotionPhoto) failed {" + fileItemInterface2.getMediaId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileItemInterface2.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileItemInterface2.getMimeType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + jArr[0] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + jArr[1] + "}");
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
        }
        return new MediaHelper.VideoInfo(fileItemInterface2.getWidth(), fileItemInterface2.getHeight(), fileItemInterface2.getOrientation());
        throw th;
    }

    public static MetadataManager getInstance() {
        return sInstance;
    }

    private boolean isValidVideo(FileItemInterface fileItemInterface, MediaHelper.VideoInfo videoInfo) {
        boolean z;
        boolean z3;
        int orientation = fileItemInterface.getOrientation();
        if (orientation == 90 || orientation == 270 ? fileItemInterface.getWidth() <= fileItemInterface.getHeight() : fileItemInterface.getHeight() <= fileItemInterface.getWidth()) {
            z = false;
        } else {
            z = true;
        }
        int i2 = videoInfo.orientation;
        if (i2 == 90 || i2 == 270 ? videoInfo.width <= videoInfo.height : videoInfo.height <= videoInfo.width) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (z == z3) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaHelper.VideoInfo lambda$load$1(FileItemInterface fileItemInterface, VideoReqInfo videoReqInfo, Integer num) {
        if (fileItemInterface.isImage()) {
            return extractVideoInfoFromMotionPhoto(fileItemInterface, videoReqInfo);
        }
        return extractVideoInfo(fileItemInterface);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadMotionBitmap$2(FileItemInterface fileItemInterface, int i2, BiConsumer biConsumer) {
        long currentTimeMillis = System.currentTimeMillis();
        Bitmap retrieveMotionBitmap = retrieveMotionBitmap(fileItemInterface);
        boolean z = false;
        if (retrieveMotionBitmap != null) {
            retrieveMotionBitmap = retrieveMotionBitmap.copy(retrieveMotionBitmap.getConfig(), false);
            this.mMotionPreviewCache.put(Integer.valueOf(i2), new AtomicReference(retrieveMotionBitmap));
            Log.w("MetadataManager", "loadMotionBitmap cache=" + Logger.toSimpleString(retrieveMotionBitmap));
            ObjectDictionary.setTag(retrieveMotionBitmap, "Recyclable", Boolean.FALSE);
        } else {
            this.mMotionPreviewCache.put(Integer.valueOf(i2), new AtomicReference((Object) null));
        }
        if (biConsumer != null) {
            biConsumer.accept(Integer.valueOf(i2), retrieveMotionBitmap);
        }
        StringBuilder sb2 = new StringBuilder("loadMotionBitmap {");
        sb2.append(fileItemInterface.getFileId());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (retrieveMotionBitmap != null) {
            z = true;
        }
        sb2.append(z);
        sb2.append("} +");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d("MetadataManager", sb2.toString());
    }

    private Bitmap retrieveMotionBitmap(FileItemInterface fileItemInterface) {
        String message;
        FileInputStream fileInputStream;
        Throwable th;
        MediaMetadataRetriever mediaMetadataRetriever;
        Throwable th2;
        MediaHelper.VideoInfo load = load(fileItemInterface, new VideoReqInfo.Builder().isMotionSefPlay(true).build(), (Consumer<MediaHelper.VideoInfo>) null);
        if (load.length == 0) {
            return null;
        }
        int max = (int) Math.max(0, calcMotionPresentationTime(fileItemInterface, (long) load.duration) - 500);
        try {
            fileInputStream = new FileInputStream(fileItemInterface.getPath());
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(fileInputStream.getFD(), load.offset, load.length);
            float max2 = 960.0f / ((float) Math.max(load.width, load.height));
            SeApiCompat.setVideoSize(mediaMetadataRetriever, Math.round(((float) load.width) * max2), Math.round(((float) load.height) * max2));
            Bitmap videoFrameAtTime = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, ((long) max) * 1000, 2);
            mediaMetadataRetriever.close();
            fileInputStream.close();
            return videoFrameAtTime;
        } catch (Exception e) {
            Exception exc = e;
            Log.e((CharSequence) "MetadataManager", "retrieveMotionBitmap failed {" + fileItemInterface.getMediaId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileItemInterface.getFileId() + "}", (Throwable) exc);
            if (fileItemInterface.getXmpType() == XmpType.XmpMotionPhoto && (message = exc.getMessage()) != null && message.startsWith("setDataSource failed")) {
                fileItemInterface.setXmpType((XmpType) null);
                MediaCacheLoader.getInstance().clearTag(fileItemInterface, 1);
            }
            return null;
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
        throw th2;
        throw th;
    }

    public long calcMotionPresentationTime(FileItemInterface fileItemInterface, long j2) {
        if (fileItemInterface != null && !MotionPhotoUtils.supportMotionSefPlay(fileItemInterface)) {
            long loadMotionPhotoPresentationTime = XmpUtils.loadMotionPhotoPresentationTime(fileItemInterface);
            if (loadMotionPhotoPresentationTime >= 0) {
                return Math.min(TimeUnit.MICROSECONDS.toMillis(loadMotionPhotoPresentationTime), j2);
            }
        }
        return j2;
    }

    public void clear(FileItemInterface fileItemInterface) {
        this.mMap.remove(Integer.valueOf(getVideoInfoKey(fileItemInterface)));
    }

    public boolean contains(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !this.mMap.containsKey(Integer.valueOf(getVideoInfoKey(fileItemInterface)))) {
            return false;
        }
        return true;
    }

    public MediaHelper.VideoInfo get(FileItemInterface fileItemInterface) {
        return get(fileItemInterface, new VideoReqInfo.Builder().build());
    }

    public int getVideoInfoKey(FileItemInterface fileItemInterface) {
        return getVideoInfoKey(fileItemInterface, new VideoReqInfo.Builder().build());
    }

    /* renamed from: load */
    public MediaHelper.VideoInfo lambda$preload$0(FileItemInterface fileItemInterface) {
        return load(fileItemInterface, new VideoReqInfo.Builder().build(), (Consumer<MediaHelper.VideoInfo>) null);
    }

    public ExifTag loadExif(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || fileItemInterface.getPath() == null || !fileItemInterface.isImage()) {
            return null;
        }
        int complexHashCode = fileItemInterface.getComplexHashCode();
        ExifTag exifTag = this.mExifCache.get(Integer.valueOf(complexHashCode));
        if (exifTag != null) {
            return exifTag;
        }
        return extractExif(complexHashCode, fileItemInterface.getPath());
    }

    public void loadMotionBitmap(FileItemInterface fileItemInterface, BiConsumer<Integer, Bitmap> biConsumer) {
        Bitmap bitmap;
        if (fileItemInterface == null || !fileItemInterface.isLocal() || fileItemInterface.getPath() == null) {
            Log.w("MetadataManager", "loadMotionBitmap skip");
            return;
        }
        int complexHashCode = fileItemInterface.getComplexHashCode();
        AtomicReference atomicReference = this.mMotionPreviewCache.get(Integer.valueOf(complexHashCode));
        if (atomicReference == null || ((bitmap = (Bitmap) atomicReference.get()) != null && bitmap.isRecycled())) {
            SimpleThreadPool.getInstance().execute(new e((Object) this, (Object) fileItemInterface, complexHashCode, (Object) biConsumer, 0));
            return;
        }
        if (bitmap != null) {
            ObjectDictionary.setTag(bitmap, "Recyclable", Boolean.FALSE);
        }
        Log.w("MetadataManager", "loadMotionBitmap cache=" + Logger.toSimpleString(bitmap));
        if (biConsumer != null) {
            biConsumer.accept(Integer.valueOf(complexHashCode), bitmap);
        }
    }

    public void preload(FileItemInterface fileItemInterface) {
        if (fileItemInterface != null && !contains(fileItemInterface)) {
            SimpleThreadPool.getInstance().execute(new f(13, this, fileItemInterface));
        }
    }

    public void put(FileItemInterface fileItemInterface, MediaHelper.VideoInfo videoInfo) {
        if (videoInfo != null && videoInfo.width > 0 && videoInfo.height > 0) {
            this.mMap.putIfAbsent(Integer.valueOf(getVideoInfoKey(fileItemInterface)), videoInfo);
        }
    }

    public MediaHelper.VideoInfo get(FileItemInterface fileItemInterface, VideoReqInfo videoReqInfo) {
        if (fileItemInterface != null) {
            return this.mMap.get(Integer.valueOf(getVideoInfoKey(fileItemInterface, videoReqInfo)));
        }
        return null;
    }

    public int getVideoInfoKey(FileItemInterface fileItemInterface, VideoReqInfo videoReqInfo) {
        if (!videoReqInfo.isMotionSefPlay) {
            return fileItemInterface.getComplexHashCode();
        }
        return (fileItemInterface.getComplexHashCode() + "MotionSefPlay").hashCode();
    }

    public MediaHelper.VideoInfo load(FileItemInterface fileItemInterface, VideoReqInfo videoReqInfo, Consumer<MediaHelper.VideoInfo> consumer) {
        MediaHelper.VideoInfo videoInfo;
        if (fileItemInterface.isSharing()) {
            videoInfo = fileItemInterface.isImage() ? extractVideoInfoFromMotionPhoto(fileItemInterface, videoReqInfo) : extractVideoInfo(fileItemInterface);
        } else {
            videoInfo = this.mMap.computeIfAbsent(Integer.valueOf(getVideoInfoKey(fileItemInterface, videoReqInfo)), new d(this, fileItemInterface, videoReqInfo, 0));
        }
        if (consumer != null) {
            consumer.accept(videoInfo);
        }
        return videoInfo;
    }
}
