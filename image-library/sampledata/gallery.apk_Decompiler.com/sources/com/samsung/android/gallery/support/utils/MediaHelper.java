package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Size;
import c0.C0086a;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MetadataKeyHolder {
        static final int[] KEYS = {18, 19, 9, 11, 24, 5, 35, 36, 37, 17, 16, 26, 1027, ErrorCodeConvertor.TEMP_AGENT_WIFI_ONLY_MODEL, 1032, 1033, 1034, 1035, 1028, 23};
    }

    public static VideoInfo extractMetadata(MediaMetadataRetriever mediaMetadataRetriever) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            VideoInfo videoInfo = new VideoInfo(loadMetadata(mediaMetadataRetriever, MetadataKeyHolder.KEYS));
            Log.d("MediaHelper", "extractMetadata +" + (System.currentTimeMillis() - currentTimeMillis) + " " + videoInfo);
            return videoInfo;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("extractMetadata failed e="), "MediaHelper");
            return null;
        }
    }

    public static String[] getMetadata(String str, int... iArr) {
        int length = iArr.length;
        String[] strArr = new String[length];
        if (TextUtils.isEmpty(str)) {
            return strArr;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(str);
            for (int i2 = 0; i2 < length; i2++) {
                strArr[i2] = mediaMetadataRetriever.extractMetadata(iArr[i2]);
            }
            return strArr;
        } catch (Exception | NoSuchMethodError e) {
            Log.e("MediaHelper", "getMetadata#" + iArr[0] + " failed e=" + e);
            return strArr;
        } finally {
            releaseRetrieverAsync(mediaMetadataRetriever);
        }
    }

    private static String getSuperSlowData(String str) {
        byte[] data;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            File file = new File(str);
            SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
            if (!sefFileCompat.isValidFile(file) || (data = sefFileCompat.getData(file, SefInfo.SUPER_SLOW_MOTION_DATA.keyName)) == null || data.length <= 0) {
                return null;
            }
            return new String(data, 0, data.length);
        } catch (Exception | NoSuchMethodError e) {
            a.z(e, new StringBuilder("getSuperSlowData failed e="), "MediaHelper");
            return null;
        }
    }

    public static int getSuperSlowStartTime(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String superSlowData = getSuperSlowData(str);
        if (superSlowData != null) {
            try {
                String[] split = superSlowData.split("!");
                int parseInt = Integer.parseInt(split[0].split("\\*")[Integer.parseInt(split[1]) - 1].split(NumericEnum.SEP)[0]);
                Log.d("MediaHelper", "getSuperSlowStartTime {" + parseInt + "} +" + (System.currentTimeMillis() - currentTimeMillis));
                return Math.max(parseInt, 0);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                j.u(e, new StringBuilder("getSuperSlowStartTime failed. e="), "MediaHelper");
            }
        }
        return 0;
    }

    public static int getVideoDuration(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String[] metadata = getMetadata(str, 9);
        Log.d("MediaHelper", C0086a.j(currentTimeMillis, metadata[0], "} +", new StringBuilder("getVideoDuration {")));
        return UnsafeCast.toInt(metadata[0], 0);
    }

    public static VideoInfo getVideoInfo(String str) {
        return new VideoInfo(loadMetadata(str, MetadataKeyHolder.KEYS));
    }

    public static int getVideoOrientation(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String[] metadata = getMetadata(str, 24);
        Log.d("MediaHelper", C0086a.j(currentTimeMillis, metadata[0], "} +", new StringBuilder("getVideoOrientation {")));
        return UnsafeCast.toInt(metadata[0], 0);
    }

    public static boolean isPortraitVideo(String str) {
        return isVertical(getVideoInfo(str));
    }

    public static boolean isVertical(int i2) {
        return i2 == 90 || i2 == 270;
    }

    public static HashMap<Integer, String> loadMetadata(MediaMetadataRetriever mediaMetadataRetriever, int[] iArr) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i2 : iArr) {
            String extractMetadata = mediaMetadataRetriever.extractMetadata(i2);
            if (extractMetadata != null) {
                hashMap.put(Integer.valueOf(i2), extractMetadata);
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public static void releaseRetriever(MediaMetadataRetriever mediaMetadataRetriever) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            mediaMetadataRetriever.release();
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (currentTimeMillis2 > 10) {
                Log.w("MediaHelper", "releaseRetriever +" + currentTimeMillis2);
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("releaseRetriever failed e="), "MediaHelper");
        }
    }

    private static void releaseRetrieverAsync(MediaMetadataRetriever mediaMetadataRetriever) {
        SimpleThreadPool.getInstance().execute(new C(3, mediaMetadataRetriever));
    }

    public static VideoInfo getVideoInfo(Context context, Uri uri) {
        long currentTimeMillis = System.currentTimeMillis();
        VideoInfo videoInfo = new VideoInfo(loadMetadata(context, uri, MetadataKeyHolder.KEYS));
        Log.d("MediaHelper", "getVideoInfo(u) +" + (System.currentTimeMillis() - currentTimeMillis) + " " + videoInfo);
        return videoInfo;
    }

    public static boolean isVertical(VideoInfo videoInfo) {
        return isVertical(videoInfo.orientation) ? videoInfo.width > videoInfo.height : videoInfo.height > videoInfo.width;
    }

    public static VideoInfo getVideoInfo(FileInputStream fileInputStream, long j2, long j3) {
        long currentTimeMillis = System.currentTimeMillis();
        long j8 = j2;
        long j10 = j3;
        VideoInfo videoInfo = new VideoInfo(loadMetadata(fileInputStream, j8, j10, MetadataKeyHolder.KEYS));
        videoInfo.offset = j8;
        videoInfo.length = j10;
        Log.d("MediaHelper", "getVideoInfo(s) +" + (System.currentTimeMillis() - currentTimeMillis) + " o=" + j8 + ",l=" + j10 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + videoInfo);
        return videoInfo;
    }

    public static HashMap<Integer, String> loadMetadata(String str, int... iArr) {
        if (!TextUtils.isEmpty(str)) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(str);
                return loadMetadata(mediaMetadataRetriever, iArr);
            } catch (Exception | NoSuchMethodError e) {
                Log.e("MediaHelper", "loadMetadata#" + iArr[0] + " failed e=" + e);
            } finally {
                releaseRetrieverAsync(mediaMetadataRetriever);
            }
        }
        return new HashMap<>();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VideoInfo {
        public boolean corrected;
        public int duration;
        final HashMap<Integer, String> extras;
        public int height;
        public long length;
        public long offset;
        public int orientation;
        public Size size;
        public int width;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class KeymapHolder {
            static final HashMap<Integer, String> map = new HashMap<Integer, String>() {
                {
                    put(18, "w");
                    put(19, "h");
                    put(9, "dur");
                    put(11, "writer");
                    put(24, "rotation");
                    put(5, BuddyContract.Event.DATE);
                    put(35, "colorStandard");
                    put(36, "colorTransfer");
                    put(37, "colorRange");
                    put(17, "hasVideo");
                    put(16, "hasAudio");
                    put(26, "hasImage");
                    put(1027, "hdr10+");
                    put(Integer.valueOf(ErrorCodeConvertor.TEMP_AGENT_WIFI_ONLY_MODEL), "syncFrame");
                    put(1033, "lensType");
                    put(1034, "focalLen");
                    put(1035, "zoomedFocalLen");
                    put(1028, "bitDepth");
                    put(1024, "videoCodecInfo");
                    put(1021, "video360");
                    put(Integer.valueOf(ErrorCodeConvertor.TEMP_AGENT_SERVICE_DISABLED), "numAudio");
                    put(23, "location");
                }
            };
        }

        public VideoInfo(HashMap<Integer, String> hashMap) {
            this.extras = hashMap;
            this.width = UnsafeCast.toInt(hashMap.get(18), 0);
            this.height = UnsafeCast.toInt(hashMap.get(19), 0);
            this.orientation = UnsafeCast.toInt(hashMap.get(24), 0);
            this.duration = UnsafeCast.toInt(hashMap.get(9), 0);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ String lambda$toDebugString$0(int i2) {
            return KeymapHolder.map.getOrDefault(Integer.valueOf(i2), String.valueOf(i2)) + "=" + this.extras.get(Integer.valueOf(i2));
        }

        public boolean compareAndSet(int i2, int i7) {
            if (this.corrected || i2 <= 0 || i7 <= 0) {
                return false;
            }
            this.corrected = true;
            this.size = null;
            if (isVertical()) {
                if (this.height == i2 && this.width == i7) {
                    return false;
                }
                this.height = i2;
                this.width = i7;
                return true;
            } else if (this.width == i2 && this.height == i7) {
                return false;
            } else {
                this.width = i2;
                this.height = i7;
                return true;
            }
        }

        public String getExtraValue(int i2) {
            return this.extras.get(Integer.valueOf(i2));
        }

        public HashMap<Integer, String> getExtras() {
            return this.extras;
        }

        public String getModelName() {
            return this.extras.get(11);
        }

        public Size getSize() {
            Size size2;
            if (this.size == null) {
                if (isVertical()) {
                    size2 = new Size(this.height, this.width);
                } else {
                    size2 = new Size(this.width, this.height);
                }
                this.size = size2;
            }
            return this.size;
        }

        public int getVideoBitDepth() {
            String str = this.extras.get(1028);
            if (str != null) {
                return UnsafeCast.toInt(str, 8);
            }
            return 8;
        }

        public boolean isVertical() {
            int i2 = this.orientation;
            if (i2 == 90 || i2 == 270) {
                return true;
            }
            return false;
        }

        public String toDebugString() {
            IntStream stream = Arrays.stream(MetadataKeyHolder.KEYS);
            HashMap<Integer, String> hashMap = this.extras;
            Objects.requireNonNull(hashMap);
            return "VideoInfo{" + this.width + "x" + this.height + Log.TAG_SEPARATOR + this.orientation + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.duration + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.offset + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.length + ",Extra=" + ((String) stream.filter(new y(hashMap)).mapToObj(new z(this)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"))) + "}";
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("VideoInfo{");
            sb2.append(this.width);
            sb2.append("x");
            sb2.append(this.height);
            sb2.append(Log.TAG_SEPARATOR);
            sb2.append(this.orientation);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.duration);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.offset);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0212a.o(sb2, this.length, "");
        }

        public VideoInfo(int i2, int i7, int i8) {
            this.width = i2;
            this.height = i7;
            this.orientation = i8;
            this.extras = new HashMap<>();
        }
    }

    public static HashMap<Integer, String> loadMetadata(FileInputStream fileInputStream, long j2, long j3, int... iArr) {
        if (fileInputStream != null) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(fileInputStream.getFD(), j2, j3);
                HashMap<Integer, String> loadMetadata = loadMetadata(mediaMetadataRetriever, iArr);
                releaseRetrieverAsync(mediaMetadataRetriever);
                return loadMetadata;
            } catch (Exception | NoSuchMethodError e) {
                Object obj = e;
                Log.e("MediaHelper", "loadMetadata#" + iArr[0] + " failed e=" + obj);
                releaseRetrieverAsync(mediaMetadataRetriever);
            } catch (Throwable th) {
                Throwable th2 = th;
                releaseRetrieverAsync(mediaMetadataRetriever);
                throw th2;
            }
        }
        return new HashMap<>();
    }

    /* JADX INFO: finally extract failed */
    public static HashMap<Integer, String> loadMetadata(Context context, Uri uri, int... iArr) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(context, uri);
            HashMap<Integer, String> loadMetadata = loadMetadata(mediaMetadataRetriever, iArr);
            releaseRetrieverAsync(mediaMetadataRetriever);
            return loadMetadata;
        } catch (Exception | NoSuchMethodError e) {
            Log.e("MediaHelper", "loadMetadata#" + iArr[0] + " failed e=" + e);
            releaseRetrieverAsync(mediaMetadataRetriever);
            return new HashMap<>();
        } catch (Throwable th) {
            releaseRetrieverAsync(mediaMetadataRetriever);
            throw th;
        }
    }
}
