package androidx.media3.exoplayer.mediacodec;

import F2.G;
import F2.Q;
import F2.U;
import F2.y0;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import androidx.media3.common.Format;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaCodecUtil {
    private static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = new HashMap<>();
    private static int maxH264DecodableFrameSize = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CodecKey {
        public final String mimeType;
        public final boolean secure;
        public final boolean tunneling;

        public CodecKey(String str, boolean z, boolean z3) {
            this.mimeType = str;
            this.secure = z;
            this.tunneling = z3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == CodecKey.class) {
                CodecKey codecKey = (CodecKey) obj;
                if (TextUtils.equals(this.mimeType, codecKey.mimeType) && this.secure == codecKey.secure && this.tunneling == codecKey.tunneling) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            int i2;
            int d = C0212a.d(31, 31, this.mimeType);
            int i7 = 1237;
            if (this.secure) {
                i2 = 1231;
            } else {
                i2 = 1237;
            }
            int i8 = (d + i2) * 31;
            if (this.tunneling) {
                i7 = 1231;
            }
            return i8 + i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i2);

        boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private MediaCodecInfo[] mediaCodecInfos;

        public MediaCodecListCompatV21(boolean z, boolean z3, boolean z7) {
            int i2;
            if (z || z3 || z7) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            this.codecKind = i2;
        }

        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }

        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        public MediaCodecInfo getCodecInfoAt(int i2) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i2];
        }

        public boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureRequired(str);
        }

        public boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported(str);
        }

        public boolean secureDecodersExplicit() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ScoreProvider<T> {
        int getScore(T t);
    }

    private static void applyWorkarounds(String str, List<MediaCodecInfo> list) {
        if ("audio/raw".equals(str)) {
            sortByScore(list, new a(1));
        }
        if (Build.VERSION.SDK_INT < 32 && list.size() > 1 && "OMX.qti.audio.decoder.flac".equals(list.get(0).name)) {
            list.add(list.remove(0));
        }
    }

    public static String getAlternativeCodecMimeType(Format format) {
        Pair<Integer, Integer> codecProfileAndLevel;
        if ("audio/eac3-joc".equals(format.sampleMimeType)) {
            return "audio/eac3";
        }
        if ("video/dolby-vision".equals(format.sampleMimeType) && (codecProfileAndLevel = getCodecProfileAndLevel(format)) != null) {
            int intValue = ((Integer) codecProfileAndLevel.first).intValue();
            if (intValue == 16 || intValue == 256) {
                return "video/hevc";
            }
            if (intValue == 512) {
                return Encode.CodecsMime.VIDEO_CODEC_H264;
            }
            if (intValue == 1024) {
                return "video/av01";
            }
        }
        if ("video/mv-hevc".equals(format.sampleMimeType)) {
            return "video/hevc";
        }
        return null;
    }

    public static List<MediaCodecInfo> getAlternativeDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z3) {
        String alternativeCodecMimeType = getAlternativeCodecMimeType(format);
        if (alternativeCodecMimeType != null) {
            return mediaCodecSelector.getDecoderInfos(alternativeCodecMimeType, z, z3);
        }
        G g = U.e;
        return y0.f278h;
    }

    private static String getCodecMimeType(MediaCodecInfo mediaCodecInfo, String str, String str2) {
        for (String str3 : mediaCodecInfo.getSupportedTypes()) {
            if (str3.equalsIgnoreCase(str2)) {
                return str3;
            }
        }
        if (str2.equals("video/dolby-vision")) {
            if ("OMX.MS.HEVCDV.Decoder".equals(str)) {
                return "video/hevcdv";
            }
            if ("OMX.RTK.video.decoder".equals(str) || "OMX.realtek.video.decoder.tunneled".equals(str)) {
                return "video/dv_hevc";
            }
            return null;
        } else if (str2.equals("video/mv-hevc")) {
            if ("c2.qti.mvhevc.decoder".equals(str) || "c2.qti.mvhevc.decoder.secure".equals(str)) {
                return "video/x-mvhevc";
            }
            return null;
        } else if (str2.equals("audio/alac") && "OMX.lge.alac.decoder".equals(str)) {
            return "audio/x-lg-alac";
        } else {
            if (str2.equals("audio/flac") && "OMX.lge.flac.decoder".equals(str)) {
                return "audio/x-lg-flac";
            }
            if (!str2.equals("audio/ac3") || !"OMX.lge.ac3.decoder".equals(str)) {
                return null;
            }
            return "audio/lg-ac3";
        }
    }

    @Deprecated
    public static Pair<Integer, Integer> getCodecProfileAndLevel(Format format) {
        return CodecSpecificDataUtil.getCodecProfileAndLevel(format);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String str, boolean z, boolean z3) {
        synchronized (MediaCodecUtil.class) {
            try {
                CodecKey codecKey = new CodecKey(str, z, z3);
                HashMap<CodecKey, List<MediaCodecInfo>> hashMap = decoderInfosCache;
                List<MediaCodecInfo> list = hashMap.get(codecKey);
                if (list != null) {
                    return list;
                }
                ArrayList<MediaCodecInfo> decoderInfosInternal = getDecoderInfosInternal(codecKey, new MediaCodecListCompatV21(z, z3, str.equals("video/mv-hevc")));
                if (z) {
                    decoderInfosInternal.isEmpty();
                }
                applyWorkarounds(str, decoderInfosInternal);
                U y = U.y(decoderInfosInternal);
                hashMap.put(codecKey, y);
                return y;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private static ArrayList<MediaCodecInfo> getDecoderInfosInternal(CodecKey codecKey, MediaCodecListCompat mediaCodecListCompat) {
        int i2;
        String codecMimeType;
        String str;
        CodecKey codecKey2 = codecKey;
        MediaCodecListCompat mediaCodecListCompat2 = mediaCodecListCompat;
        try {
            ArrayList<MediaCodecInfo> arrayList = new ArrayList<>();
            String str2 = codecKey2.mimeType;
            int codecCount = mediaCodecListCompat2.getCodecCount();
            boolean secureDecodersExplicit = mediaCodecListCompat2.secureDecodersExplicit();
            int i7 = 0;
            while (i7 < codecCount) {
                MediaCodecInfo codecInfoAt = mediaCodecListCompat2.getCodecInfoAt(i7);
                if (isAlias(codecInfoAt)) {
                    i2 = i7;
                } else {
                    int i8 = i7;
                    String name = codecInfoAt.getName();
                    if (isCodecUsableDecoder(codecInfoAt, name, secureDecodersExplicit, str2) && (codecMimeType = getCodecMimeType(codecInfoAt, name, str2)) != null) {
                        int i10 = i8;
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(codecMimeType);
                            boolean isFeatureSupported = mediaCodecListCompat2.isFeatureSupported("tunneled-playback", codecMimeType, capabilitiesForType);
                            boolean isFeatureRequired = mediaCodecListCompat2.isFeatureRequired("tunneled-playback", codecMimeType, capabilitiesForType);
                            boolean z = codecKey2.tunneling;
                            if ((z || !isFeatureRequired) && (!z || isFeatureSupported)) {
                                boolean isFeatureSupported2 = mediaCodecListCompat2.isFeatureSupported("secure-playback", codecMimeType, capabilitiesForType);
                                boolean isFeatureRequired2 = mediaCodecListCompat2.isFeatureRequired("secure-playback", codecMimeType, capabilitiesForType);
                                boolean z3 = codecKey2.secure;
                                if ((z3 || !isFeatureRequired2) && (!z3 || isFeatureSupported2)) {
                                    String str3 = codecMimeType;
                                    try {
                                        boolean isHardwareAccelerated = isHardwareAccelerated(codecInfoAt, str2);
                                        int i11 = i10;
                                        boolean isSoftwareOnly = isSoftwareOnly(codecInfoAt, str2);
                                        boolean isVendor = isVendor(codecInfoAt);
                                        if (secureDecodersExplicit) {
                                            if (codecKey2.secure != isFeatureSupported2) {
                                            }
                                            boolean z7 = isVendor;
                                            str = str3;
                                            i2 = i11;
                                            arrayList.add(MediaCodecInfo.newInstance(name, str2, str, capabilitiesForType, isHardwareAccelerated, isSoftwareOnly, z7, false, false));
                                        }
                                        if (secureDecodersExplicit || codecKey2.secure) {
                                            boolean z9 = isFeatureSupported2;
                                            boolean z10 = isVendor;
                                            str = str3;
                                            boolean z11 = z9;
                                            i2 = i11;
                                            if (!secureDecodersExplicit && z11) {
                                                try {
                                                    String str4 = name;
                                                    try {
                                                        arrayList.add(MediaCodecInfo.newInstance(name + ".secure", str2, str, capabilitiesForType, isHardwareAccelerated, isSoftwareOnly, z10, false, true));
                                                        return arrayList;
                                                    } catch (Exception e) {
                                                        e = e;
                                                        name = str4;
                                                        Log.e("MediaCodecUtil", "Failed to query codec " + name + " (" + str + ")");
                                                        throw e;
                                                    }
                                                } catch (Exception e7) {
                                                    e = e7;
                                                    String str5 = name;
                                                    Log.e("MediaCodecUtil", "Failed to query codec " + name + " (" + str + ")");
                                                    throw e;
                                                }
                                            }
                                        }
                                        boolean z72 = isVendor;
                                        str = str3;
                                        i2 = i11;
                                        try {
                                            arrayList.add(MediaCodecInfo.newInstance(name, str2, str, capabilitiesForType, isHardwareAccelerated, isSoftwareOnly, z72, false, false));
                                        } catch (Exception e8) {
                                            e = e8;
                                        }
                                    } catch (Exception e9) {
                                        e = e9;
                                        str = str3;
                                        Log.e("MediaCodecUtil", "Failed to query codec " + name + " (" + str + ")");
                                        throw e;
                                    }
                                }
                            }
                            i2 = i10;
                        } catch (Exception e10) {
                            e = e10;
                            str = codecMimeType;
                            Log.e("MediaCodecUtil", "Failed to query codec " + name + " (" + str + ")");
                            throw e;
                        }
                    } else {
                        i2 = i8;
                    }
                }
                i7 = i2 + 1;
                mediaCodecListCompat2 = mediaCodecListCompat;
            }
            return arrayList;
        } catch (Exception e11) {
            throw new DecoderQueryException(e11);
        }
    }

    public static List<MediaCodecInfo> getDecoderInfosSoftMatch(MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z3) {
        List<MediaCodecInfo> decoderInfos = mediaCodecSelector.getDecoderInfos(format.sampleMimeType, z, z3);
        List<MediaCodecInfo> alternativeDecoderInfos = getAlternativeDecoderInfos(mediaCodecSelector, format, z, z3);
        Q x9 = U.x();
        x9.c(decoderInfos);
        x9.c(alternativeDecoderInfos);
        return x9.f();
    }

    public static List<MediaCodecInfo> getDecoderInfosSortedByFullFormatSupport(List<MediaCodecInfo> list, Format format) {
        ArrayList arrayList = new ArrayList(list);
        sortByScore(arrayList, new b(format));
        return arrayList;
    }

    public static List<MediaCodecInfo> getDecoderInfosSortedBySoftwareOnly(List<MediaCodecInfo> list) {
        ArrayList arrayList = new ArrayList(list);
        sortByScore(arrayList, new a(0));
        return U.y(arrayList);
    }

    public static Pair<Integer, Integer> getHevcBaseLayerCodecProfileAndLevel(Format format) {
        String h265BaseLayerCodecsString = NalUnitUtil.getH265BaseLayerCodecsString(format.initializationData);
        if (h265BaseLayerCodecsString == null) {
            return null;
        }
        return CodecSpecificDataUtil.getHevcProfileAndLevel(h265BaseLayerCodecsString, Util.split(h265BaseLayerCodecsString.trim(), "\\."), format.colorInfo);
    }

    private static boolean isAlias(MediaCodecInfo mediaCodecInfo) {
        if (isAliasV29(mediaCodecInfo)) {
            return true;
        }
        return false;
    }

    private static boolean isAliasV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isAlias();
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z, String str2) {
        if (mediaCodecInfo.isEncoder()) {
            return false;
        }
        if (z || !str.endsWith(".secure")) {
            return true;
        }
        return false;
    }

    private static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo, String str) {
        return isHardwareAcceleratedV29(mediaCodecInfo);
    }

    private static boolean isHardwareAcceleratedV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isHardwareAccelerated();
    }

    private static boolean isSoftwareOnly(MediaCodecInfo mediaCodecInfo, String str) {
        return isSoftwareOnlyV29(mediaCodecInfo);
    }

    private static boolean isSoftwareOnlyV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isSoftwareOnly();
    }

    private static boolean isVendor(MediaCodecInfo mediaCodecInfo) {
        return isVendorV29(mediaCodecInfo);
    }

    private static boolean isVendorV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isVendor();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$applyWorkarounds$3(MediaCodecInfo mediaCodecInfo) {
        String str = mediaCodecInfo.name;
        if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$getDecoderInfosSortedByFullFormatSupport$1(Format format, MediaCodecInfo mediaCodecInfo) {
        try {
            return mediaCodecInfo.isFormatSupported(format) ? 1 : 0;
        } catch (DecoderQueryException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$getDecoderInfosSortedBySoftwareOnly$2(MediaCodecInfo mediaCodecInfo) {
        int i2;
        if (mediaCodecInfo.softwareOnly) {
            i2 = 2;
        } else {
            i2 = 0;
        }
        return i2 + (mediaCodecInfo.vendor ^ true ? 1 : 0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortByScore$4(ScoreProvider scoreProvider, Object obj, Object obj2) {
        return scoreProvider.getScore(obj2) - scoreProvider.getScore(obj);
    }

    private static <T> void sortByScore(List<T> list, ScoreProvider<T> scoreProvider) {
        Collections.sort(list, new c(scoreProvider));
    }
}
