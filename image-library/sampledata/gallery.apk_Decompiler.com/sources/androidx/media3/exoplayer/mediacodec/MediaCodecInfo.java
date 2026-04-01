package androidx.media3.exoplayer.mediacodec;

import A.a;
import N2.j;
import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import i.C0212a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaCodecInfo {
    public final boolean adaptive;
    public final MediaCodecInfo.CodecCapabilities capabilities;
    public final String codecMimeType;
    public final boolean detachedSurfaceSupported;
    public final boolean hardwareAccelerated;
    private final boolean isVideo;
    private float maxFrameRate = -3.4028235E38f;
    private int maxFrameRateHeight = -1;
    private int maxFrameRateWidth = -1;
    public final String mimeType;
    public final String name;
    public final boolean secure;
    public final boolean softwareOnly;
    public final boolean tunneling;
    public final boolean vendor;

    public MediaCodecInfo(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z3, boolean z7, boolean z9, boolean z10, boolean z11, boolean z12) {
        this.name = (String) Assertions.checkNotNull(str);
        this.mimeType = str2;
        this.codecMimeType = str3;
        this.capabilities = codecCapabilities;
        this.hardwareAccelerated = z;
        this.softwareOnly = z3;
        this.vendor = z7;
        this.adaptive = z9;
        this.tunneling = z10;
        this.secure = z11;
        this.detachedSurfaceSupported = z12;
        this.isVideo = MimeTypes.isVideo(str2);
    }

    private static int adjustMaxInputChannelCount(String str, String str2, int i2) {
        int i7;
        if (i2 > 1 || i2 > 0 || "audio/mpeg".equals(str2) || Encode.CodecsMime.AUDIO_CODEC_AMR.equals(str2) || "audio/amr-wb".equals(str2) || Encode.CodecsMime.AUDIO_CODEC_AAC.equals(str2) || "audio/vorbis".equals(str2) || "audio/opus".equals(str2) || "audio/raw".equals(str2) || "audio/flac".equals(str2) || "audio/g711-alaw".equals(str2) || "audio/g711-mlaw".equals(str2) || "audio/gsm".equals(str2)) {
            return i2;
        }
        if ("audio/ac3".equals(str2)) {
            i7 = 6;
        } else if ("audio/eac3".equals(str2)) {
            i7 = 16;
        } else {
            i7 = 30;
        }
        StringBuilder u = C0212a.u("AssumedMaxChannelAdjustment: ", str, ", [", i2, " to ");
        u.append(i7);
        u.append("]");
        Log.w("MediaCodecInfo", u.toString());
        return i7;
    }

    private static Point alignVideoSize(MediaCodecInfo.VideoCapabilities videoCapabilities, int i2, int i7) {
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(Util.ceilDivide(i2, widthAlignment) * widthAlignment, Util.ceilDivide(i7, heightAlignment) * heightAlignment);
    }

    private static boolean areSizeAndRateSupported(MediaCodecInfo.VideoCapabilities videoCapabilities, int i2, int i7, double d) {
        Point alignVideoSize = alignVideoSize(videoCapabilities, i2, i7);
        int i8 = alignVideoSize.x;
        int i10 = alignVideoSize.y;
        if (d == -1.0d || d < 1.0d) {
            return videoCapabilities.isSizeSupported(i8, i10);
        }
        double floor = Math.floor(d);
        if (!videoCapabilities.areSizeAndRateSupported(i8, i10, floor)) {
            return false;
        }
        Range<Double> achievableFrameRatesFor = videoCapabilities.getAchievableFrameRatesFor(i8, i10);
        if (achievableFrameRatesFor != null && floor > achievableFrameRatesFor.getUpper().doubleValue()) {
            return false;
        }
        return true;
    }

    private static boolean isAdaptive(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    private boolean isCodecProfileAndLevelSupported(Format format, boolean z) {
        Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format);
        String str = format.sampleMimeType;
        if (str != null && str.equals("video/mv-hevc")) {
            String normalizeMimeType = MimeTypes.normalizeMimeType(this.codecMimeType);
            if (normalizeMimeType.equals("video/mv-hevc")) {
                return true;
            }
            if (normalizeMimeType.equals("video/hevc")) {
                codecProfileAndLevel = MediaCodecUtil.getHevcBaseLayerCodecProfileAndLevel(format);
            }
        }
        if (codecProfileAndLevel == null) {
            return true;
        }
        int intValue = ((Integer) codecProfileAndLevel.first).intValue();
        int intValue2 = ((Integer) codecProfileAndLevel.second).intValue();
        if ("video/dolby-vision".equals(format.sampleMimeType)) {
            String str2 = this.mimeType;
            str2.getClass();
            char c5 = 65535;
            switch (str2.hashCode()) {
                case -1662735862:
                    if (str2.equals("video/av01")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -1662541442:
                    if (str2.equals("video/hevc")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 1331836730:
                    if (str2.equals(Encode.CodecsMime.VIDEO_CODEC_H264)) {
                        c5 = 2;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                case 1:
                    intValue2 = 0;
                    intValue = 2;
                    break;
                case 2:
                    intValue = 8;
                    intValue2 = 0;
                    break;
            }
        }
        if (!this.isVideo && intValue != 42) {
            return true;
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : getProfileLevels()) {
            if (codecProfileLevel.profile == intValue && ((codecProfileLevel.level >= intValue2 || !z) && !needsProfileExcludedWorkaround(this.mimeType, intValue))) {
                return true;
            }
        }
        logNoSupport("codec.profileLevel, " + format.codecs + ArcCommonLog.TAG_COMMA + this.codecMimeType);
        return false;
    }

    private boolean isCompressedAudioBitDepthSupported(Format format) {
        if (!Objects.equals(format.sampleMimeType, "audio/flac") || format.pcmEncoding != 22 || Build.VERSION.SDK_INT >= 34 || !this.name.equals("c2.android.flac.decoder")) {
            return true;
        }
        return false;
    }

    private static boolean isDetachedSurfaceSupported(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        if (Build.VERSION.SDK_INT < 35 || codecCapabilities == null || !codecCapabilities.isFeatureSupported("detached-surface") || needsDetachedSurfaceUnsupportedWorkaround()) {
            return false;
        }
        return true;
    }

    private boolean isSampleMimeTypeSupported(Format format) {
        if (this.mimeType.equals(format.sampleMimeType) || this.mimeType.equals(MediaCodecUtil.getAlternativeCodecMimeType(format))) {
            return true;
        }
        return false;
    }

    private static boolean isSecure(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    private static boolean isTunneling(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    private void logAssumedSupport(String str) {
        StringBuilder k = j.k("AssumedSupport [", str, "] [");
        k.append(this.name);
        k.append(ArcCommonLog.TAG_COMMA);
        k.append(this.mimeType);
        k.append("] [");
        k.append(Util.DEVICE_DEBUG_INFO);
        k.append("]");
        Log.d("MediaCodecInfo", k.toString());
    }

    private void logNoSupport(String str) {
        StringBuilder k = j.k("NoSupport [", str, "] [");
        k.append(this.name);
        k.append(ArcCommonLog.TAG_COMMA);
        k.append(this.mimeType);
        k.append("] [");
        k.append(Util.DEVICE_DEBUG_INFO);
        k.append("]");
        Log.d("MediaCodecInfo", k.toString());
    }

    private static boolean needsDetachedSurfaceUnsupportedWorkaround() {
        String str = Build.MANUFACTURER;
        if (str.equals("Xiaomi") || str.equals("OPPO") || str.equals("realme") || str.equals("motorola") || str.equals("LENOVO")) {
            return true;
        }
        return false;
    }

    private static boolean needsDisableAdaptationWorkaround(String str) {
        return false;
    }

    private static boolean needsProfileExcludedWorkaround(String str, int i2) {
        if (!"video/hevc".equals(str) || 2 != i2) {
            return false;
        }
        String str2 = Build.DEVICE;
        if ("sailfish".equals(str2) || "marlin".equals(str2)) {
            return true;
        }
        return false;
    }

    private static boolean needsRotatedVerticalResolutionWorkaround(String str) {
        if (!"OMX.MTK.VIDEO.DECODER.HEVC".equals(str) || !"mcv5a".equals(Build.DEVICE)) {
            return true;
        }
        return false;
    }

    public static MediaCodecInfo newInstance(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z3, boolean z7, boolean z9, boolean z10) {
        boolean z11;
        boolean z12;
        boolean z13;
        if (z9 || codecCapabilities == null || !isAdaptive(codecCapabilities) || needsDisableAdaptationWorkaround(str)) {
            z11 = false;
        } else {
            z11 = true;
        }
        if (codecCapabilities == null || !isTunneling(codecCapabilities)) {
            z12 = false;
        } else {
            z12 = true;
        }
        if (z10 || (codecCapabilities != null && isSecure(codecCapabilities))) {
            z13 = true;
        } else {
            z13 = false;
        }
        return new MediaCodecInfo(str, str2, str3, codecCapabilities, z, z3, z7, z11, z12, z13, isDetachedSurfaceSupported(codecCapabilities));
    }

    public MediaCodecInfo.CodecProfileLevel[] getProfileLevels() {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null || (codecProfileLevelArr = codecCapabilities.profileLevels) == null) {
            return new MediaCodecInfo.CodecProfileLevel[0];
        }
        return codecProfileLevelArr;
    }

    public boolean isAudioChannelCountSupportedV21(int i2) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("channelCount.aCaps");
            return false;
        } else if (adjustMaxInputChannelCount(this.name, this.mimeType, audioCapabilities.getMaxInputChannelCount()) >= i2) {
            return true;
        } else {
            logNoSupport(C0086a.i(i2, "channelCount.support, "));
            return false;
        }
    }

    public boolean isAudioSampleRateSupportedV21(int i2) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("sampleRate.aCaps");
            return false;
        } else if (audioCapabilities.isSampleRateSupported(i2)) {
            return true;
        } else {
            logNoSupport(C0086a.i(i2, "sampleRate.support, "));
            return false;
        }
    }

    public boolean isFormatSupported(Format format) {
        int i2;
        int i7;
        if (!isSampleMimeTypeSupported(format) || !isCodecProfileAndLevelSupported(format, true) || !isCompressedAudioBitDepthSupported(format)) {
            return false;
        }
        if (this.isVideo) {
            int i8 = format.width;
            if (i8 <= 0 || (i7 = format.height) <= 0) {
                return true;
            }
            return isVideoSizeAndRateSupportedV21(i8, i7, (double) format.frameRate);
        }
        int i10 = format.sampleRate;
        if ((i10 == -1 || isAudioSampleRateSupportedV21(i10)) && ((i2 = format.channelCount) == -1 || isAudioChannelCountSupportedV21(i2))) {
            return true;
        }
        return false;
    }

    public boolean isVideoSizeAndRateSupportedV21(int i2, int i7, double d) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("sizeAndRate.vCaps");
            return false;
        }
        int areResolutionAndFrameRateCovered = MediaCodecPerformancePointCoverageProvider.areResolutionAndFrameRateCovered(videoCapabilities, i2, i7, d);
        if (areResolutionAndFrameRateCovered == 2) {
            return true;
        }
        if (areResolutionAndFrameRateCovered == 1) {
            StringBuilder h5 = a.h(i2, i7, "sizeAndRate.cover, ", "x", com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR);
            h5.append(d);
            logNoSupport(h5.toString());
            return false;
        }
        if (!areSizeAndRateSupported(videoCapabilities, i2, i7, d)) {
            if (i2 >= i7 || !needsRotatedVerticalResolutionWorkaround(this.name) || !areSizeAndRateSupported(videoCapabilities, i7, i2, d)) {
                StringBuilder h6 = a.h(i2, i7, "sizeAndRate.support, ", "x", com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR);
                h6.append(d);
                logNoSupport(h6.toString());
                return false;
            }
            StringBuilder h8 = a.h(i2, i7, "sizeAndRate.rotated, ", "x", com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR);
            h8.append(d);
            logAssumedSupport(h8.toString());
        }
        return true;
    }

    public String toString() {
        return this.name;
    }
}
