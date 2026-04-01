package com.samsung.android.gallery.module.graphics;

import A.a;
import F6.f;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.samsung.gallery.decoder.QuramCodec;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CodecCompat {
    public static final boolean PATCH_HEIF_ALL_FILES;
    public static final boolean PATCH_HEIF_FILE_TRANSCODING;
    public static final boolean PATCH_HEIF_STREAM_FATAL;
    public static final boolean PATCH_JPEG_PROGRESSIVE = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CmhIpServiceHolder {
        static final HashSet<Long> set = new HashSet<>();
        static final Uri uri = Uri.parse("content://com.samsung.cmh/files");

        public static synchronized boolean contains(long j2) {
            synchronized (CmhIpServiceHolder.class) {
                HashSet<Long> hashSet = set;
                if (hashSet.contains(Long.valueOf(j2))) {
                    return true;
                }
                hashSet.add(Long.valueOf(j2));
                return false;
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$mark$0(long j2) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("serviceflag", "2147483647");
                ContentResolver contentResolver = AppResources.getAppContext().getContentResolver();
                Uri uri2 = uri;
                contentResolver.update(uri2, contentValues, "sec_media_id=" + j2, (String[]) null);
            } catch (Error | Exception e) {
                a.z(e, new StringBuilder("avoidIpService failed. e="), "CodecCompat");
            }
        }

        public static void mark(long j2) {
            if (!contains(j2)) {
                SimpleThreadPool.getInstance().execute(new b(j2));
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemCacheHolder {
        static final LruCache<Integer, Boolean> cache = new LruCache<>(StatusCodes.INPUT_MISSING);
        static final LruCache<Integer, Boolean> heifCache = new LruCache<>(1000);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class QuramSyntaxPatchCacheHolder {
        private static final LruCache<Integer, Boolean> cache = new LruCache<>(StatusCodes.INPUT_MISSING);

        public static boolean get(int i2) {
            Boolean bool = cache.get(Integer.valueOf(i2));
            if (bool == null || bool.booleanValue()) {
                return true;
            }
            return false;
        }

        public static void put(int i2, boolean z) {
            cache.put(Integer.valueOf(i2), Boolean.valueOf(z));
        }
    }

    static {
        boolean z;
        boolean support = support();
        PATCH_HEIF_FILE_TRANSCODING = support;
        PATCH_HEIF_STREAM_FATAL = support;
        if (!support || !SdkConfig.atLeast(SdkConfig.SEM.U)) {
            z = false;
        } else {
            z = true;
        }
        PATCH_HEIF_ALL_FILES = z;
    }

    public static boolean contains(FileItemInterface fileItemInterface) {
        Boolean bool;
        if (fileItemInterface == null || !fileItemInterface.isHeif() || (bool = ItemCacheHolder.cache.get(Integer.valueOf(fileItemInterface.getComplexHashCode()))) == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    public static boolean ensureIntegrity(byte[] bArr, int i2) {
        if (HeifSyntaxImpl.isEditRequired(bArr, i2) == 1) {
            return true;
        }
        return false;
    }

    public static boolean ensureJpegSyntaxCompatible(FileItemInterface fileItemInterface) {
        if (!PATCH_JPEG_PROGRESSIVE || !fileItemInterface.isJpeg()) {
            return true;
        }
        if (((Integer) fileItemInterface.getTag("BitmapOptions.outCodecReason", 0)).intValue() == 101) {
            return false;
        }
        return QuramSyntaxPatchCacheHolder.get(fileItemInterface.getComplexHashCode());
    }

    public static boolean fixHeifSyntax(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || fileItemInterface.getPath() == null) {
            return true;
        }
        int complexHashCode = fileItemInterface.getComplexHashCode();
        Integer cache = HeifSyntaxImpl.getCache(complexHashCode);
        if (cache == null) {
            long currentTimeMillis = System.currentTimeMillis();
            String path = fileItemInterface.getPath();
            SecureFile secureFile = new SecureFile(path);
            int isEditRequired = HeifSyntaxImpl.isEditRequired(secureFile);
            if (isEditRequired != 2) {
                HeifSyntaxImpl.putCache(complexHashCode, isEditRequired);
                if (isEditRequired == 1) {
                    return true;
                }
                return false;
            }
            long lastModified = secureFile.lastModified();
            int patch = HeifSyntaxImpl.patch(secureFile);
            HeifSyntaxImpl.putCache(complexHashCode, patch);
            if (lastModified > 0) {
                try {
                    secureFile.setLastModified(lastModified);
                } catch (Error | Exception unused) {
                }
            }
            StringBuilder h5 = a.h(complexHashCode, patch, "fixHeifSyntax#patch {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "} +");
            h5.append(System.currentTimeMillis() - currentTimeMillis);
            h5.append(" ");
            h5.append(Logger.getEncodedString(path));
            Log.d("CodecCompat", h5.toString());
            if (patch == -2) {
                put(fileItemInterface, true);
                CmhIpServiceHolder.mark(fileItemInterface.getFileId());
            } else if (patch == 0) {
                DebugLogger decodeInstance = DebugLogger.getDecodeInstance();
                decodeInstance.apply("[FixHeifSyntax]", fileItemInterface.getFileId() + NumericEnum.SEP + fileItemInterface.getMediaId() + NumericEnum.SEP + fileItemInterface.getPath());
            }
            if (patch != -2) {
                return true;
            }
            return false;
        } else if (cache.intValue() != -2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHeif(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || fileItemInterface.getPath() == null) {
            return false;
        }
        if (fileItemInterface.isHeif()) {
            return true;
        }
        int complexHashCode = fileItemInterface.getComplexHashCode();
        LruCache<Integer, Boolean> lruCache = ItemCacheHolder.heifCache;
        Boolean bool = lruCache.get(Integer.valueOf(complexHashCode));
        if (bool == null) {
            bool = Boolean.valueOf(MimeType.isHeif(MimeType.from(fileItemInterface.getPath()).mimeType));
            lruCache.put(Integer.valueOf(complexHashCode), bool);
        }
        return bool.booleanValue();
    }

    public static boolean loadVideoApvCapability() {
        boolean z;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            MediaCodecInfo[] codecInfos = new MediaCodecList(0).getCodecInfos();
            int length = codecInfos.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                }
                MediaCodecInfo mediaCodecInfo = codecInfos[i2];
                if (!mediaCodecInfo.isEncoder() && !mediaCodecInfo.isSoftwareOnly()) {
                    try {
                        if (mediaCodecInfo.getCapabilitiesForType("video/apv") != null) {
                            z = true;
                            break;
                        }
                    } catch (Exception unused) {
                        continue;
                    }
                }
                i2++;
            }
            Log.d("CodecCompat", "loadVideoApvCapability" + Logger.vt(Boolean.valueOf(z), Long.valueOf(currentTimeMillis)));
            return z;
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadVideoApvCapability failed. e="), "CodecCompat");
            return false;
        }
    }

    public static void put(FileItemInterface fileItemInterface, boolean z) {
        ItemCacheHolder.cache.put(Integer.valueOf(fileItemInterface.getComplexHashCode()), Boolean.valueOf(z));
    }

    private static boolean support() {
        try {
            if (!SdkConfig.lessThan(SdkConfig.SEM.V) || SeApiCompat.getSecurityPatchLevel() >= 20241101) {
                return false;
            }
            return true;
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public static boolean supportAPVHWDecoder() {
        return GalleryPreference.getInstanceCache().computeBooleanIfAbsent("support_apv_hw_decoder", new f(15));
    }

    public static boolean ensureJpegSyntaxCompatible(byte[] bArr, int i2, int i7, BitmapOptions bitmapOptions) {
        if (PATCH_JPEG_PROGRESSIVE) {
            if (bitmapOptions.outMimeType == null && bArr[0] == -1 && bArr[1] == -40) {
                bitmapOptions.outMimeType = "image/jpeg";
            }
            if (MimeType.isJpeg(bitmapOptions.outMimeType) && !QuramCodec.isJpegSyntaxCompatible(bArr, i2, i7)) {
                bitmapOptions.outCodecReason = 101;
                int i8 = bitmapOptions.inComplexHash;
                if (i8 != 0) {
                    QuramSyntaxPatchCacheHolder.put(i8, false);
                }
                return false;
            }
        }
        return true;
    }
}
