package com.samsung.android.gallery.module.publisher;

import A.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverCollector;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.R$bool;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.debugger.BugReporter;
import com.samsung.android.gallery.module.graphics.BitmapCache;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.CodecCompat;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SafeMode;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.net.ssl.HttpsURLConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BitmapDataPublisher extends Subscriber {
    private Context mAppContext;
    private final DebugLogger mDebugLogger;
    private final ImageDecodeHandler mDecodeHandler;
    private final RecoverCollector mRecoverCollector;
    private final ConcurrentLinkedQueue<DecodeRequest> mReqQueue;
    private boolean mRetryPPP = true;
    final ConcurrentHashMap<String, Status> mStatusMap = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DecodeRequest {
        long initTime = System.currentTimeMillis();
        boolean isViewerEnter;
        MediaItem item;
        String key;
        boolean verify;

        public DecodeRequest(MediaItem mediaItem, String str, boolean z, boolean z3) {
            this.item = mediaItem;
            this.key = str;
            this.verify = z;
            this.isViewerEnter = z3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ImageDecodeHandler extends Handler {
        final BitmapDataPublisher mPublisher;
        final ConcurrentLinkedQueue<DecodeRequest> mReqQueue;

        public ImageDecodeHandler(BitmapDataPublisher bitmapDataPublisher, ConcurrentLinkedQueue<DecodeRequest> concurrentLinkedQueue) {
            super(ThreadUtil.createBackgroundThreadLooper("BitmapPublisher"));
            this.mPublisher = bitmapDataPublisher;
            this.mReqQueue = concurrentLinkedQueue;
        }

        public void handleMessage(Message message) {
            while (!this.mReqQueue.isEmpty()) {
                DecodeRequest poll = this.mReqQueue.poll();
                if (poll != null) {
                    this.mPublisher.processRequest(poll);
                    synchronized (this.mPublisher.mStatusMap) {
                        this.mPublisher.mStatusMap.remove(CommandKey.SEGMENT(poll.key));
                        this.mPublisher.mBlackboard.erase(poll.key);
                    }
                }
            }
        }

        public void recycle() {
            try {
                getLooper().quit();
            } catch (Exception unused) {
            }
        }

        public void request(Runnable runnable) {
            try {
                post(runnable);
            } catch (Exception e) {
                a.r(e, new StringBuilder("requestAsync failed. ignore exception e="), this.mPublisher.TAG);
            }
        }

        public void requestDecode() {
            try {
                sendEmptyMessage(0);
            } catch (Exception e) {
                a.r(e, new StringBuilder("requestDecode failed. ignore exception e="), this.mPublisher.TAG);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Status {
        REQUESTED,
        PROCESS,
        CANCELED
    }

    public BitmapDataPublisher(Blackboard blackboard) {
        super(blackboard);
        ConcurrentLinkedQueue<DecodeRequest> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        this.mReqQueue = concurrentLinkedQueue;
        this.mDecodeHandler = new ImageDecodeHandler(this, concurrentLinkedQueue);
        this.mDebugLogger = DebugLogger.getDecodeInstance();
        this.mRecoverCollector = RecoverCollector.getInstance();
    }

    private void addDecodeFailLog(String str, MediaItem mediaItem) {
        String str2;
        int i2;
        String str3;
        String path = mediaItem.getPath();
        boolean exists = FileUtils.exists(path);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Logger.getEncodedString(mediaItem.getPath()));
        sb2.append("/");
        sb2.append(mediaItem.getFileId());
        sb2.append("/");
        sb2.append(exists);
        sb2.append("/");
        sb2.append(mediaItem.getStorageType());
        if (!mediaItem.isCloudOnly() || !TrashData.isTrash(mediaItem)) {
            str2 = "";
        } else {
            str2 = "/" + mediaItem.getCloudServerId() + "/" + Logger.getEncodedString(mediaItem.getCloudServerPath());
        }
        sb2.append(str2);
        String sb3 = sb2.toString();
        BugReporter.addDecodeFailPath(mediaItem.getBucketID(), path);
        if (path == null) {
            i2 = sb3.hashCode();
        } else {
            i2 = path.hashCode();
        }
        String valueOf = String.valueOf(i2);
        boolean isInRemovableStorage = FileUtils.isInRemovableStorage(path);
        if (isInRemovableStorage) {
            str3 = StorageInfo.getRemovable().getCardId();
        } else {
            str3 = null;
        }
        this.mDebugLogger.insertLog(str, sb3, str3, valueOf);
        this.mRecoverCollector.collectNCall(mediaItem, exists);
        if (isInRemovableStorage) {
            this.mDebugLogger.insertLog("Detail", BugReporter.validateJpgFile(path, Logger.getEncodedString(mediaItem.getPath())), str3, C0212a.A(valueOf, "/d"));
        }
    }

    private Bitmap getDecodedBitmap(DecodeRequest decodeRequest) {
        MediaItem mediaItem = decodeRequest.item;
        if (mediaItem.isVideo() && mediaItem.isLocal()) {
            return getVideoBitmap(mediaItem, decodeRequest.isViewerEnter);
        }
        StorageType storageType = mediaItem.getStorageType();
        Bitmap bitmap = null;
        if (storageType == StorageType.Mtp) {
            long currentTimeMillis = System.currentTimeMillis();
            MtpClient instance = MtpClient.getInstance(this.mAppContext);
            byte[] object = instance.getObject(instance.getDeviceNameFromPath(mediaItem.getPath()), (int) mediaItem.getFileId(), (int) mediaItem.getFileSize());
            if (object == null) {
                Log.w(this.TAG, "getDecodedBitmap failed. null mtp data");
                return null;
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (currentTimeMillis2 > 1000) {
                StringCompat stringCompat = this.TAG;
                Log.w(stringCompat, "getDecodedBitmap load mtp data {" + object.length + "} +" + currentTimeMillis2);
            }
            bitmap = getMtpItemBitmap(mediaItem, object);
        } else if (storageType == StorageType.UriItem) {
            bitmap = getUriItemBitmap(mediaItem);
        } else if (storageType == StorageType.WebItem) {
            bitmap = getWebItemBitmap(mediaItem);
        } else if (!PocFeatures.isEnabled(PocFeatures.WifiGalleryClient) || storageType != StorageType.RemoteItem) {
            if (mediaItem.isVideo()) {
                try {
                    bitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), new BitmapOptions(mediaItem.getWidth(), mediaItem.getHeight(), mediaItem.getMimeType()).withQuramCodec(true).withHeap(true).adjustInSampleSize(BitmapSizeHolder.size()));
                } catch (Error | Exception e) {
                    Log.e((CharSequence) this.TAG, "getDecodedBitmap failed", e);
                }
            } else if (mediaItem.isStream()) {
                bitmap = BitmapUtils.getDecodedBitmap(mediaItem.getByteArray(), DeviceInfo.getDefaultDisplay().getLongSideLength());
            } else {
                if (SafeMode.ENABLED) {
                    Log.e("SafeMode", "decode file(m) #" + mediaItem.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getPath());
                    GalleryPreference.getInstanceDebug().saveState(PreferenceName.LAST_DECODE_FILE, mediaItem.getPath());
                    BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem).adjustInSampleSizeLargerThan(ThumbKind.MEDIUM_KIND.size()));
                    Log.d("SafeMode", "decode file(m) #" + mediaItem.getFileId() + " done");
                }
                if (CodecCompat.PATCH_HEIF_ALL_FILES && !mediaItem.isHeif() && !mediaItem.isCamModelSamsung() && CodecCompat.isHeif(mediaItem)) {
                    mediaItem.setMimeType("image/heic");
                }
                BitmapOptions adjustInSampleSize = BitmapOptionsFactory.parse((FileItemInterface) mediaItem, true).adjustInSampleSize(getTargetSize(mediaItem));
                if ((mediaItem.isLocal() || mediaItem.isPrivateItem()) && CodecCompat.PATCH_HEIF_FILE_TRANSCODING && mediaItem.isHeif() && !mediaItem.isCamModelSamsung() && !CodecCompat.fixHeifSyntax(mediaItem)) {
                    adjustInSampleSize.withAndroidCodec(true);
                }
                Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), adjustInSampleSize);
                if (CodecCompat.PATCH_JPEG_PROGRESSIVE && adjustInSampleSize.outCodecReason == 101) {
                    mediaItem.setTag("BitmapOptions.outCodecReason", 101);
                }
                if (SafeMode.ENABLED) {
                    Log.d("SafeMode", "decode file(o) #" + mediaItem.getFileId() + " done");
                    GalleryPreference.getInstanceDebug().removeState(PreferenceName.LAST_DECODE_FILE);
                }
                bitmap = decodedBitmap;
            }
        } else if (!mediaItem.isImage()) {
            return null;
        } else {
            bitmap = new RemoteClient(RemoteGalleryUtil.getIp(mediaItem)).getPreview(mediaItem.getFileId());
        }
        return BitmapUtils.resizeForMaxBitmapSize(bitmap);
    }

    private Bitmap getDecodedBitmapWithDiskCache(DecodeRequest decodeRequest) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        byte[] bArr;
        boolean z10;
        Bitmap.Config config;
        String str;
        MediaItem mediaItem = decodeRequest.item;
        long width = ((long) mediaItem.getWidth()) * ((long) mediaItem.getHeight());
        boolean z11 = false;
        if (!mediaItem.isHeif() || width <= 12582912) {
            z = false;
        } else {
            z = true;
        }
        if (!mediaItem.isPng() || width <= 12582912) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (!mediaItem.isVideo() || !mediaItem.is4K()) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (!mediaItem.isLocal() || (!z && !z7 && !z3)) {
            z9 = false;
        } else {
            z9 = true;
        }
        Bitmap bitmap = null;
        if (z9) {
            bArr = getPreviewCacheKey(mediaItem, z);
        } else {
            bArr = null;
        }
        if (z9) {
            if (mediaItem.hasPhotoHdrInfo() || ((str = mediaItem.camModel) != null && str.startsWith("Apple"))) {
                bitmap = BitmapCache.getDiskCache(8, bArr, BitmapOptionsFactory.applyHdr(new BitmapOptions(), mediaItem));
            } else {
                bitmap = BitmapCache.getDiskCache(8, bArr);
            }
        }
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap decodedBitmap = getDecodedBitmap(decodeRequest);
        if (decodedBitmap == null || !z9 || !supportDiskCache(mediaItem)) {
            z10 = false;
        } else {
            z10 = true;
        }
        if (!(decodedBitmap == null || !z10 || (config = decodedBitmap.getConfig()) == null)) {
            Bitmap copy = decodedBitmap.copy(config, true);
            if (mediaItem.isPng() && BitmapUtils.hasTransparency(decodedBitmap)) {
                z11 = true;
            }
            this.mDecodeHandler.request(new A(bArr, copy, z11, 2));
        }
        return decodedBitmap;
    }

    private int getTargetSize(MediaItem mediaItem) {
        int min;
        if (mediaItem.getOrientation() == 0) {
            DeviceInfo.DeviceDisplay defaultDisplay = DeviceInfo.getDefaultDisplay();
            int longSideLength = defaultDisplay.getLongSideLength();
            int shortSideLength = defaultDisplay.getShortSideLength();
            if (Math.max(mediaItem.getWidth(), mediaItem.getHeight()) <= longSideLength && ((min = Math.min(mediaItem.getWidth(), mediaItem.getHeight())) == shortSideLength || (AppResources.getBoolean(R$bool.isTabletDpi) && min < shortSideLength))) {
                return longSideLength;
            }
        }
        return BitmapSizeHolder.size();
    }

    private Bitmap getUriItemBitmap(MediaItem mediaItem) {
        BufferedInputStream bufferedInputStream;
        Uri parse = Uri.parse(mediaItem.getPath());
        if (mediaItem.isImage()) {
            try {
                bufferedInputStream = new BufferedInputStream(this.mAppContext.getContentResolver().openInputStream(parse));
                if (mediaItem.getWidth() != 0) {
                    if (mediaItem.getHeight() == 0) {
                    }
                    Bitmap decodeStream = BitmapUtils.decodeStream(bufferedInputStream, new BitmapOptions(mediaItem.getWidth(), mediaItem.getHeight(), mediaItem.getMimeType()).adjustInSampleSize(BitmapSizeHolder.size()));
                    bufferedInputStream.close();
                    return decodeStream;
                }
                ThumbnailUtil.updateImageInfo((ThumbnailInterface) mediaItem, (InputStream) bufferedInputStream);
                Bitmap decodeStream2 = BitmapUtils.decodeStream(bufferedInputStream, new BitmapOptions(mediaItem.getWidth(), mediaItem.getHeight(), mediaItem.getMimeType()).adjustInSampleSize(BitmapSizeHolder.size()));
                bufferedInputStream.close();
                return decodeStream2;
            } catch (Exception | OutOfMemoryError | StackOverflowError e) {
                Log.e((CharSequence) this.TAG, "getUriItemBitmap failed", e);
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else if (!mediaItem.isVideo()) {
            return null;
        } else {
            if (mediaItem.getWidth() != 0 && mediaItem.getHeight() != 0) {
                return null;
            }
            ThumbnailUtil.updateVideoInfo(mediaItem, this.mAppContext);
            return null;
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBitmapRequested$0(String str) {
        this.mBlackboard.erase(str);
    }

    /* access modifiers changed from: private */
    public void onBitmapRequestCanceled(Object obj, Bundle bundle) {
        String substring = ArgumentsUtil.getSubscribeKey(bundle).substring(28);
        synchronized (this.mStatusMap) {
            try {
                if (this.mStatusMap.containsKey(substring)) {
                    StringCompat stringCompat = this.TAG;
                    Log.d(stringCompat, "cancel  : " + substring);
                    this.mStatusMap.put(substring, Status.CANCELED);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0149, code lost:
        r0 = r8.TAG;
        com.samsung.android.gallery.support.utils.Log.p(r0, "onBitmapRequested {" + com.samsung.android.gallery.support.blackboard.key.CommandKey.SEGMENT(r1) + ", p=" + com.samsung.android.gallery.support.utils.BundleWrapper.getInt(r10, com.samsung.android.sum.core.message.Message.KEY_POSITION, -1) + com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface.SPLIT_REGEX + r9.getMediaId() + com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface.SPLIT_REGEX + r9.getFileId() + "}");
        requestBitmapDecoding(new com.samsung.android.gallery.module.publisher.BitmapDataPublisher.DecodeRequest(r9, r1, r2, r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0195, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBitmapRequested(java.lang.Object r9, android.os.Bundle r10) {
        /*
            r8 = this;
            java.lang.String r0 = "status cancel -> request : "
            com.samsung.android.gallery.module.data.MediaItem r9 = (com.samsung.android.gallery.module.data.MediaItem) r9
            java.lang.String r1 = com.samsung.android.gallery.support.utils.ArgumentsUtil.getSubscribeKey(r10)
            boolean r2 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi6x.SUPPORT_EMBEDDED_THUMBNAIL_IN_VIDEO
            if (r2 != 0) goto L_0x007f
            boolean r2 = r9.isVideo()
            if (r2 == 0) goto L_0x007f
            boolean r2 = r9.isLocal()
            if (r2 == 0) goto L_0x007f
            boolean r2 = r9.is8K()
            if (r2 == 0) goto L_0x007f
            com.samsung.android.gallery.support.utils.StringCompat r10 = r8.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "onBitmapRequested skip 8K {"
            r0.<init>(r2)
            java.lang.String r2 = com.samsung.android.gallery.support.blackboard.key.CommandKey.SEGMENT(r1)
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            long r2 = r9.getMediaId()
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            long r2 = r9.getFileId()
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            int r2 = r9.getWidth()
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            int r2 = r9.getHeight()
            r0.append(r2)
            java.lang.String r2 = "}"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.w(r10, r0)
            com.samsung.android.gallery.module.media.MetadataManager r10 = com.samsung.android.gallery.module.media.MetadataManager.getInstance()
            r10.preload(r9)
            com.samsung.android.gallery.module.publisher.r r9 = new com.samsung.android.gallery.module.publisher.r
            r10 = 0
            r9.<init>(r10, r8, r1)
            r0 = 1000(0x3e8, double:4.94E-321)
            com.samsung.android.gallery.support.utils.ThreadUtil.postOnBgThreadDelayed(r9, r0)
            return
        L_0x007f:
            java.lang.String r2 = "verify_subscriber"
            boolean r2 = com.samsung.android.gallery.support.utils.BundleWrapper.getBoolean(r10, r2)
            java.lang.String r3 = "isViewerEnter"
            boolean r3 = com.samsung.android.gallery.support.utils.BundleWrapper.getBoolean(r10, r3)
            if (r2 == 0) goto L_0x0113
            com.samsung.android.gallery.support.blackboard.Blackboard r4 = r8.mBlackboard
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "data://bitmap/viewer/"
            r5.<init>(r6)
            int r6 = r9.getSimpleHashCode()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            boolean r4 = r4.hasSubscriber(r5)
            if (r4 != 0) goto L_0x0113
            com.samsung.android.gallery.support.utils.StringCompat r10 = r8.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "onBitmapRequested ignored. {"
            r0.<init>(r2)
            java.lang.String r2 = com.samsung.android.gallery.support.blackboard.key.CommandKey.SEGMENT(r1)
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            long r2 = r9.getMediaId()
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            long r2 = r9.getFileId()
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            com.samsung.android.gallery.database.dbtype.MediaType r2 = r9.getMediaType()
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            int r2 = r9.getWidth()
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            int r2 = r9.getHeight()
            r0.append(r2)
            java.lang.String r2 = ","
            r0.append(r2)
            com.samsung.android.gallery.database.dbtype.StorageType r9 = r9.getStorageType()
            r0.append(r9)
            java.lang.String r9 = "}"
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.w(r10, r9)
            com.samsung.android.gallery.support.blackboard.Blackboard r8 = r8.mBlackboard
            r8.erase(r1)
            return
        L_0x0113:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status> r4 = r8.mStatusMap
            monitor-enter(r4)
            java.lang.String r5 = com.samsung.android.gallery.support.blackboard.key.CommandKey.SEGMENT(r1)     // Catch:{ all -> 0x0146 }
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status> r6 = r8.mStatusMap     // Catch:{ all -> 0x0146 }
            boolean r6 = r6.containsKey(r5)     // Catch:{ all -> 0x0146 }
            if (r6 == 0) goto L_0x0148
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status> r6 = r8.mStatusMap     // Catch:{ all -> 0x0146 }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ all -> 0x0146 }
            com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status r7 = com.samsung.android.gallery.module.publisher.BitmapDataPublisher.Status.CANCELED     // Catch:{ all -> 0x0146 }
            if (r6 != r7) goto L_0x0148
            com.samsung.android.gallery.support.utils.StringCompat r9 = r8.TAG     // Catch:{ all -> 0x0146 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0146 }
            r10.<init>(r0)     // Catch:{ all -> 0x0146 }
            r10.append(r5)     // Catch:{ all -> 0x0146 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0146 }
            com.samsung.android.gallery.support.utils.Log.d(r9, r10)     // Catch:{ all -> 0x0146 }
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status> r8 = r8.mStatusMap     // Catch:{ all -> 0x0146 }
            com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status r9 = com.samsung.android.gallery.module.publisher.BitmapDataPublisher.Status.REQUESTED     // Catch:{ all -> 0x0146 }
            r8.put(r5, r9)     // Catch:{ all -> 0x0146 }
            monitor-exit(r4)     // Catch:{ all -> 0x0146 }
            return
        L_0x0146:
            r8 = move-exception
            goto L_0x0196
        L_0x0148:
            monitor-exit(r4)     // Catch:{ all -> 0x0146 }
            com.samsung.android.gallery.support.utils.StringCompat r0 = r8.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onBitmapRequested {"
            r4.<init>(r5)
            java.lang.String r5 = com.samsung.android.gallery.support.blackboard.key.CommandKey.SEGMENT(r1)
            r4.append(r5)
            java.lang.String r5 = ", p="
            r4.append(r5)
            java.lang.String r5 = "position"
            r6 = -1
            int r10 = com.samsung.android.gallery.support.utils.BundleWrapper.getInt(r10, r5, r6)
            r4.append(r10)
            java.lang.String r10 = ","
            r4.append(r10)
            long r5 = r9.getMediaId()
            r4.append(r5)
            java.lang.String r10 = ","
            r4.append(r10)
            long r5 = r9.getFileId()
            r4.append(r5)
            java.lang.String r10 = "}"
            r4.append(r10)
            java.lang.String r10 = r4.toString()
            com.samsung.android.gallery.support.utils.Log.p(r0, r10)
            com.samsung.android.gallery.module.publisher.BitmapDataPublisher$DecodeRequest r10 = new com.samsung.android.gallery.module.publisher.BitmapDataPublisher$DecodeRequest
            r10.<init>(r9, r1, r2, r3)
            r8.requestBitmapDecoding(r10)
            return
        L_0x0196:
            monitor-exit(r4)     // Catch:{ all -> 0x0146 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.publisher.BitmapDataPublisher.onBitmapRequested(java.lang.Object, android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public void onContext(Object obj, Bundle bundle) {
        this.mAppContext = BlackboardUtils.readAppContext(this.mBlackboard);
    }

    private void requestBitmapDecoding(DecodeRequest decodeRequest) {
        DecodeRequest poll;
        if (this.mReqQueue.size() == 3 && (poll = this.mReqQueue.poll()) != null) {
            synchronized (this.mStatusMap) {
                this.mBlackboard.erase(poll.key);
                this.mStatusMap.remove(CommandKey.SEGMENT(poll.key));
            }
        }
        synchronized (this.mStatusMap) {
            this.mReqQueue.add(decodeRequest);
            this.mStatusMap.put(CommandKey.SEGMENT(decodeRequest.key), Status.REQUESTED);
        }
        this.mDecodeHandler.requestDecode();
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int i2) {
        if (bitmap != null) {
            float max = ((float) i2) / ((float) Math.max(bitmap.getWidth(), bitmap.getHeight()));
            if (max < 0.9f) {
                return BitmapUtils.resize(bitmap, max);
            }
        }
        return bitmap;
    }

    private void saveLargeThumb(MediaItem mediaItem, Bitmap bitmap, int i2, int i7) {
        int i8;
        Bitmap resizeBitmap;
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        Bitmap memCache = instance.getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            i8 = Math.max(memCache.getWidth(), memCache.getHeight());
        } else {
            i8 = 0;
        }
        int max = Math.max(bitmap.getWidth(), bitmap.getHeight());
        if (i8 < i2 && i8 < max && (resizeBitmap = resizeBitmap(bitmap, i2)) != null) {
            ThumbnailLoader.getInstance().replaceCache(mediaItem, thumbKind, resizeBitmap, i7);
        }
    }

    private void saveLargeThumbForHeif(MediaItem mediaItem, Bitmap bitmap) {
        saveLargeThumb(mediaItem, bitmap, 720, 90);
    }

    private void saveWrongAspectRatio(MediaItem mediaItem, Bitmap bitmap) {
        float f;
        float f5;
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        Bitmap memCache = instance.getMemCache(mediaItem, thumbKind);
        if (memCache == null) {
            f = -1.0f;
        } else {
            f = MathUtils.getRatio(memCache.getWidth(), memCache.getHeight());
        }
        float ratio = MathUtils.getRatio(bitmap.getWidth(), bitmap.getHeight());
        if (ratio > 0.0f && !MathUtils.equals(f, ratio)) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "fixWrongAspectRatio{" + mediaItem.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f + GlobalPostProcInternalPPInterface.SPLIT_REGEX + ratio + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getWidth() + "x" + mediaItem.getHeight() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + bitmap.getWidth() + "x" + bitmap.getHeight() + "} " + Logger.getEncodedString(mediaItem.getPath()));
            if (mediaItem.is4K()) {
                f5 = 720.0f;
            } else {
                f5 = 640.0f;
            }
            float max = f5 / ((float) Math.max(bitmap.getWidth(), bitmap.getHeight()));
            if (max < 1.0f) {
                bitmap = BitmapUtils.resize(bitmap, max);
            }
            if (bitmap != null) {
                ThumbnailLoader.getInstance().replaceCache(mediaItem, thumbKind, bitmap, 95);
            }
        }
    }

    private boolean supportDiskCache(MediaItem mediaItem) {
        if (!CodecCompat.PATCH_HEIF_FILE_TRANSCODING || !mediaItem.isHeif() || !CodecCompat.contains(mediaItem)) {
            return true;
        }
        return false;
    }

    private boolean useFirstFrameAsThumbnail(MediaItem mediaItem) {
        if (PreferenceFeatures.VIDEO_THUMBNAIL_WITH_FIRST_FRAME || mediaItem.isStories()) {
            return true;
        }
        return false;
    }

    private boolean useVideoThumbStartTimeFrame(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (MediaItemUtil.isNonMovieClip(mediaItem) || DynamicViewData.of(mediaItem).dynamicViewPlayInfo != null) {
            return true;
        }
        return false;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("data://app_context", new C0637q(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://bitmap/viewer/#"), new C0637q(this, 1)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST_CANCEL("data://bitmap/viewer/#"), new C0637q(this, 2)));
    }

    public Bitmap getMtpItemBitmap(MediaItem mediaItem, byte[] bArr) {
        try {
            Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(bArr, BitmapSizeHolder.size());
            if (decodedBitmap != null) {
                Integer num = (Integer) mediaItem.getTag("mtp/orientation");
                if (num == null) {
                    num = Integer.valueOf(ExifUtils.getOrientation(bArr));
                    mediaItem.setTag("mtp/orientation", num);
                }
                int intValue = num.intValue();
                if (intValue != 0) {
                    Bitmap rotateBitmap = BitmapUtils.rotateBitmap(decodedBitmap, intValue);
                    if (rotateBitmap != decodedBitmap) {
                        BitmapUtils.putBitmap(decodedBitmap);
                    }
                    decodedBitmap = rotateBitmap;
                }
                mediaItem.setSize(decodedBitmap.getWidth(), decodedBitmap.getHeight());
            }
            return decodedBitmap;
        } catch (Error | Exception e) {
            Log.e((CharSequence) this.TAG, "getMtpItemBitmap failed", e);
            return null;
        }
    }

    public byte[] getPreviewCacheKey(MediaItem mediaItem, boolean z) {
        String thumbCacheKey = mediaItem.getThumbCacheKey();
        if (z && SuperHdrConfig.isEnabled()) {
            thumbCacheKey = C0212a.l("HDR:", thumbCacheKey);
        }
        return CacheManager.generateKey(thumbCacheKey, mediaItem.getDateModified());
    }

    public Bitmap getVideoBitmap(MediaItem mediaItem, boolean z) {
        long j2;
        MediaMetadataRetriever mediaMetadataRetriever;
        Bitmap bitmap;
        int i2;
        int i7;
        String path = mediaItem.getPath();
        if (path == null) {
            return null;
        }
        if (useVideoThumbStartTimeFrame(mediaItem)) {
            j2 = mediaItem.getVideoThumbStartTime();
        } else if (useFirstFrameAsThumbnail(mediaItem) || mediaItem.getFileDuration() < 15000) {
            j2 = 0;
        } else {
            j2 = 15000000;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            try {
                mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
                MediaHelper.VideoInfo videoInfo = MetadataManager.getInstance().get(mediaItem);
                if (videoInfo == null) {
                    videoInfo = MediaHelper.extractMetadata(mediaMetadataRetriever);
                    MetadataManager.getInstance().put(mediaItem, videoInfo);
                }
                if (z) {
                    j2 = BitmapUtils.getVideoThumbnailFrameTime(mediaMetadataRetriever);
                    if (j2 != 0) {
                        VideoPropData.of(mediaItem).videoThumbnailFrameTime = j2;
                    }
                    Log.d(this.TAG, "bitmap for first " + mediaItem.getDisplayName() + " / " + j2);
                }
                if (PreferenceFeatures.OneUi6x.SUPPORT_EMBEDDED_THUMBNAIL_IN_VIDEO) {
                    bitmap = BitmapUtils.decodeEmbeddedInVideo(mediaMetadataRetriever);
                } else {
                    bitmap = null;
                }
                if (bitmap == null && !mediaItem.is8K()) {
                    if (videoInfo != null && ((mediaItem.is4K() || videoInfo.corrected) && (i2 = videoInfo.width) > 0 && (i7 = videoInfo.height) > 0)) {
                        float max = 1280.0f / ((float) Math.max(i2, i7));
                        if (max < 1.0f) {
                            SeApiCompat.setVideoSize(mediaMetadataRetriever, Math.round(((float) videoInfo.width) * max), Math.round(((float) videoInfo.height) * max));
                        }
                    }
                    bitmap = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, j2, 2);
                }
                Bitmap resizeBitmap = resizeBitmap(bitmap, MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE);
                mediaMetadataRetriever.close();
                fileInputStream.close();
                return resizeBitmap;
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
        } catch (Error | Exception e) {
            Log.e(this.TAG, "getVideoBitmap failed e=" + e.getMessage());
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public Bitmap getWebItemBitmap(MediaItem mediaItem) {
        InputStream inputStream;
        BitmapOptions adjustInSampleSize = new BitmapOptions(mediaItem.getWidth(), mediaItem.getHeight(), mediaItem.getMimeType()).adjustInSampleSize(BitmapSizeHolder.size());
        Bitmap bitmap = null;
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(mediaItem.getPath()).openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() != 200) {
                StringCompat stringCompat = this.TAG;
                Log.e(stringCompat, "Server returned HTTP " + httpsURLConnection.getResponseCode() + " " + httpsURLConnection.getResponseMessage());
                return null;
            }
            inputStream = httpsURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream, (Rect) null, adjustInSampleSize);
            if (inputStream != null) {
                inputStream.close();
            }
            return bitmap;
        } catch (Error | Exception e) {
            Log.e((CharSequence) this.TAG, "getWebItemBitmap failed", e);
            return bitmap;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mReqQueue.clear();
        this.mDecodeHandler.recycle();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x022d, code lost:
        if (r10 != null) goto L_0x0235;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x022f, code lost:
        addDecodeFailLog("publishBitmap", r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0239, code lost:
        if (r6.isBroken() != false) goto L_0x0264;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x023f, code lost:
        if (r6.isLocal() == false) goto L_0x0264;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0245, code lost:
        if (r6.isVideo() == false) goto L_0x024b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0247, code lost:
        saveWrongAspectRatio(r6, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x024f, code lost:
        if (r6.isHeif() == false) goto L_0x0255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0251, code lost:
        saveLargeThumbForHeif(r6, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x025b, code lost:
        if (com.samsung.android.gallery.support.utils.PocFeatures.isEnabled(com.samsung.android.gallery.support.utils.PocFeatures.MediumCacheEnhance) == false) goto L_0x0264;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x025d, code lost:
        saveLargeThumb(r6, r10, 640, 95);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processRequest(com.samsung.android.gallery.module.publisher.BitmapDataPublisher.DecodeRequest r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            java.lang.String r2 = "data://bitmap/viewer/"
            java.lang.String r3 = ",time="
            java.lang.String r4 = "publishBitmap {"
            java.lang.String r5 = "publishBitmap canceled : "
            com.samsung.android.gallery.module.data.MediaItem r6 = r1.item
            com.samsung.android.gallery.support.utils.MemLog r7 = new com.samsung.android.gallery.support.utils.MemLog
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "BDP"
            r8.<init>(r9)
            long r9 = r6.getFileId()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            long r8 = java.lang.System.currentTimeMillis()
            boolean r10 = r1.isViewerEnter
            if (r10 != 0) goto L_0x0032
            android.graphics.Bitmap r10 = r18.getDecodedBitmapWithDiskCache(r19)
            goto L_0x0036
        L_0x0032:
            android.graphics.Bitmap r10 = r18.getDecodedBitmap(r19)
        L_0x0036:
            java.lang.String r11 = r1.key
            java.lang.String r11 = com.samsung.android.gallery.support.blackboard.key.CommandKey.SEGMENT(r11)
            if (r10 != 0) goto L_0x008f
            boolean r12 = r6.isVideo()
            if (r12 == 0) goto L_0x008f
            com.samsung.android.gallery.support.utils.StringCompat r0 = r0.TAG
            java.lang.String r1 = "publishBitmap skip null bitmap {"
            java.lang.String r2 = ","
            java.lang.StringBuilder r1 = N2.j.k(r1, r11, r2)
            long r2 = r6.getMediaId()
            r1.append(r2)
            java.lang.String r2 = ","
            r1.append(r2)
            long r2 = r6.getFileId()
            r1.append(r2)
            java.lang.String r2 = ","
            r1.append(r2)
            int r2 = r6.getSefFileType()
            r1.append(r2)
            java.lang.String r2 = ","
            r1.append(r2)
            com.samsung.android.gallery.database.dbtype.StorageType r2 = r6.getStorageType()
            r1.append(r2)
            java.lang.String r2 = "} +"
            r1.append(r2)
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r8
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.p(r0, r1)
            return
        L_0x008f:
            r12 = 0
            r13 = 0
            r14 = 1
            if (r10 != 0) goto L_0x00af
            boolean r15 = r6.isBroken()
            if (r15 != 0) goto L_0x00af
            r6.setBroken(r14)
            com.samsung.android.gallery.support.blackboard.Blackboard r15 = r0.mBlackboard
            r14 = 3031(0xbd7, float:4.247E-42)
            r17 = r7
            int r7 = r6.getSimpleHashCode()
            com.samsung.android.gallery.support.blackboard.key.EventMessage r7 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r14, r7, r13, r12)
            r15.postEvent(r7)
            goto L_0x00b1
        L_0x00af:
            r17 = r7
        L_0x00b1:
            if (r10 != 0) goto L_0x00f1
            boolean r7 = r6.isPostProcessing()
            if (r7 == 0) goto L_0x00f1
            boolean r1 = r0.mRetryPPP
            if (r1 == 0) goto L_0x00cd
            r0.mRetryPPP = r13
            com.samsung.android.gallery.support.utils.StringCompat r1 = r0.TAG
            java.lang.String r2 = "PPP FILE DECODE FAIL. try refresh data"
            com.samsung.android.gallery.support.utils.Log.e(r1, r2)
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r0.mBlackboard
            r1 = 1
            com.samsung.android.gallery.module.utils.BlackboardUtils.forceRefreshPicturesData(r0, r1)
            return
        L_0x00cd:
            com.samsung.android.gallery.support.utils.StringCompat r1 = r0.TAG
            java.lang.String r2 = "PPP FILE DECODE FAIL. already retried"
            com.samsung.android.gallery.support.utils.Log.e(r1, r2)
            java.lang.String r1 = "PPP FAIL"
            r0.addDecodeFailLog(r1, r6)
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r0.mBlackboard
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "data://bitmap/viewer/"
            r1.<init>(r2)
            int r2 = r6.getSimpleHashCode()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.publish(r1, r12)
            return
        L_0x00f1:
            long r12 = java.lang.System.currentTimeMillis()
            boolean r7 = r1.verify
            if (r7 == 0) goto L_0x0174
            com.samsung.android.gallery.support.blackboard.Blackboard r7 = r0.mBlackboard
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "data://bitmap/viewer/"
            r14.<init>(r15)
            int r15 = r6.getSimpleHashCode()
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            boolean r7 = r7.hasSubscriber(r14)
            if (r7 != 0) goto L_0x0174
            com.samsung.android.gallery.support.utils.StringCompat r0 = r0.TAG
            java.lang.String r2 = "publishBitmap ignored. no-sub {"
            java.lang.String r3 = ",v,"
            java.lang.StringBuilder r2 = N2.j.k(r2, r11, r3)
            long r3 = r6.getMediaId()
            r2.append(r3)
            java.lang.String r3 = ","
            r2.append(r3)
            long r3 = r6.getFileId()
            r2.append(r3)
            java.lang.String r3 = ","
            r2.append(r3)
            int r3 = r6.getSefFileType()
            r2.append(r3)
            java.lang.String r3 = ","
            r2.append(r3)
            com.samsung.android.gallery.database.dbtype.StorageType r3 = r6.getStorageType()
            r2.append(r3)
            java.lang.String r3 = ","
            r2.append(r3)
            java.lang.String r3 = r6.getMimeType()
            r2.append(r3)
            java.lang.String r3 = ",time="
            r2.append(r3)
            long r3 = r1.initTime
            long r12 = r12 - r3
            r2.append(r12)
            java.lang.String r1 = "}, "
            r2.append(r1)
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.toString((android.graphics.Bitmap) r10)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.samsung.android.gallery.support.utils.Log.w(r0, r1)
            return
        L_0x0174:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status> r7 = r0.mStatusMap
            monitor-enter(r7)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status> r14 = r0.mStatusMap     // Catch:{ all -> 0x0194 }
            java.lang.Object r14 = r14.get(r11)     // Catch:{ all -> 0x0194 }
            com.samsung.android.gallery.module.publisher.BitmapDataPublisher$Status r15 = com.samsung.android.gallery.module.publisher.BitmapDataPublisher.Status.CANCELED     // Catch:{ all -> 0x0194 }
            if (r14 != r15) goto L_0x0197
            com.samsung.android.gallery.support.utils.StringCompat r0 = r0.TAG     // Catch:{ all -> 0x0194 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0194 }
            r1.<init>(r5)     // Catch:{ all -> 0x0194 }
            r1.append(r11)     // Catch:{ all -> 0x0194 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0194 }
            com.samsung.android.gallery.support.utils.Log.w(r0, r1)     // Catch:{ all -> 0x0194 }
            monitor-exit(r7)     // Catch:{ all -> 0x0194 }
            return
        L_0x0194:
            r0 = move-exception
            goto L_0x0268
        L_0x0197:
            com.samsung.android.gallery.support.utils.StringCompat r5 = r0.TAG     // Catch:{ all -> 0x0194 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0194 }
            r14.<init>(r4)     // Catch:{ all -> 0x0194 }
            r14.append(r11)     // Catch:{ all -> 0x0194 }
            java.lang.String r4 = ","
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            boolean r4 = r1.verify     // Catch:{ all -> 0x0194 }
            if (r4 == 0) goto L_0x01ae
            java.lang.String r4 = "v"
            goto L_0x01b0
        L_0x01ae:
            java.lang.String r4 = "nv"
        L_0x01b0:
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            java.lang.String r4 = ","
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            r15 = r8
            long r8 = r6.getMediaId()     // Catch:{ all -> 0x0194 }
            r14.append(r8)     // Catch:{ all -> 0x0194 }
            java.lang.String r4 = ","
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            long r8 = r6.getFileId()     // Catch:{ all -> 0x0194 }
            r14.append(r8)     // Catch:{ all -> 0x0194 }
            java.lang.String r4 = ","
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            int r4 = r6.getSefFileType()     // Catch:{ all -> 0x0194 }
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            java.lang.String r4 = ","
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            com.samsung.android.gallery.database.dbtype.StorageType r4 = r6.getStorageType()     // Catch:{ all -> 0x0194 }
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            java.lang.String r4 = ","
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            java.lang.String r4 = r6.getMimeType()     // Catch:{ all -> 0x0194 }
            r14.append(r4)     // Catch:{ all -> 0x0194 }
            r14.append(r3)     // Catch:{ all -> 0x0194 }
            long r3 = r1.initTime     // Catch:{ all -> 0x0194 }
            long r3 = r12 - r3
            r14.append(r3)     // Catch:{ all -> 0x0194 }
            java.lang.String r1 = "}, "
            r14.append(r1)     // Catch:{ all -> 0x0194 }
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.toString((android.graphics.Bitmap) r10)     // Catch:{ all -> 0x0194 }
            r14.append(r1)     // Catch:{ all -> 0x0194 }
            java.lang.String r1 = " +"
            r14.append(r1)     // Catch:{ all -> 0x0194 }
            long r12 = r12 - r15
            r14.append(r12)     // Catch:{ all -> 0x0194 }
            java.lang.String r1 = r14.toString()     // Catch:{ all -> 0x0194 }
            com.samsung.android.gallery.support.utils.Log.p(r5, r1)     // Catch:{ all -> 0x0194 }
            com.samsung.android.gallery.support.blackboard.Blackboard r1 = r0.mBlackboard     // Catch:{ all -> 0x0194 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0194 }
            r3.<init>(r2)     // Catch:{ all -> 0x0194 }
            int r2 = r6.getSimpleHashCode()     // Catch:{ all -> 0x0194 }
            r3.append(r2)     // Catch:{ all -> 0x0194 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0194 }
            r1.publish(r2, r10)     // Catch:{ all -> 0x0194 }
            monitor-exit(r7)     // Catch:{ all -> 0x0194 }
            if (r10 != 0) goto L_0x0235
            java.lang.String r1 = "publishBitmap"
            r0.addDecodeFailLog(r1, r6)
            goto L_0x0264
        L_0x0235:
            boolean r1 = r6.isBroken()
            if (r1 != 0) goto L_0x0264
            boolean r1 = r6.isLocal()
            if (r1 == 0) goto L_0x0264
            boolean r1 = r6.isVideo()     // Catch:{ Error | Exception -> 0x0264 }
            if (r1 == 0) goto L_0x024b
            r0.saveWrongAspectRatio(r6, r10)     // Catch:{ Error | Exception -> 0x0264 }
            goto L_0x0264
        L_0x024b:
            boolean r1 = r6.isHeif()     // Catch:{ Error | Exception -> 0x0264 }
            if (r1 == 0) goto L_0x0255
            r0.saveLargeThumbForHeif(r6, r10)     // Catch:{ Error | Exception -> 0x0264 }
            goto L_0x0264
        L_0x0255:
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.MediumCacheEnhance     // Catch:{ Error | Exception -> 0x0264 }
            boolean r1 = com.samsung.android.gallery.support.utils.PocFeatures.isEnabled(r1)     // Catch:{ Error | Exception -> 0x0264 }
            if (r1 == 0) goto L_0x0264
            r1 = 640(0x280, float:8.97E-43)
            r2 = 95
            r0.saveLargeThumb(r6, r10, r1, r2)     // Catch:{ Error | Exception -> 0x0264 }
        L_0x0264:
            r17.check()
            return
        L_0x0268:
            monitor-exit(r7)     // Catch:{ all -> 0x0194 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.publisher.BitmapDataPublisher.processRequest(com.samsung.android.gallery.module.publisher.BitmapDataPublisher$DecodeRequest):void");
    }
}
