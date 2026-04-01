package androidx.media3.transformer;

import F2.U;
import N2.j;
import W.a;
import android.content.Context;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.ExportException;
import androidx.media3.transformer.TransformerUtil;
import androidx.media3.transformer.VideoEncoderSettings;
import c0.C0086a;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultEncoderFactory implements Codec.EncoderFactory {
    private final int codecPriority;
    private final Context context;
    private final boolean enableCodecDbLite;
    private final boolean enableFallback;
    private final AudioEncoderSettings requestedAudioEncoderSettings;
    private final VideoEncoderSettings requestedVideoEncoderSettings;
    private final EncoderSelector videoEncoderSelector;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public int codecPriority = -2000;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public boolean enableCodecDbLite = false;
        /* access modifiers changed from: private */
        public boolean enableFallback = true;
        /* access modifiers changed from: private */
        public AudioEncoderSettings requestedAudioEncoderSettings = AudioEncoderSettings.DEFAULT;
        /* access modifiers changed from: private */
        public VideoEncoderSettings requestedVideoEncoderSettings = VideoEncoderSettings.DEFAULT;
        /* access modifiers changed from: private */
        public EncoderSelector videoEncoderSelector = EncoderSelector.DEFAULT;

        public Builder(Context context2) {
            this.context = context2.getApplicationContext();
        }

        public DefaultEncoderFactory build() {
            return new DefaultEncoderFactory(this);
        }

        public Builder setRequestedAudioEncoderSettings(AudioEncoderSettings audioEncoderSettings) {
            this.requestedAudioEncoderSettings = audioEncoderSettings;
            return this;
        }

        public Builder setRequestedVideoEncoderSettings(VideoEncoderSettings videoEncoderSettings) {
            this.requestedVideoEncoderSettings = videoEncoderSettings;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EncoderFallbackCost {
        int getParameterSupportGap(MediaCodecInfo mediaCodecInfo);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EncoderQueryResult {
        public final MediaCodecInfo encoder;
        public final Format supportedFormat;

        public EncoderQueryResult(MediaCodecInfo mediaCodecInfo, Format format) {
            this.encoder = mediaCodecInfo;
            this.supportedFormat = format;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VideoEncoderQueryResult extends EncoderQueryResult {
        public final VideoEncoderSettings supportedEncoderSettings;

        public VideoEncoderQueryResult(MediaCodecInfo mediaCodecInfo, Format format, VideoEncoderSettings videoEncoderSettings) {
            super(mediaCodecInfo, format);
            this.supportedEncoderSettings = videoEncoderSettings;
        }
    }

    private static void adjustMediaFormatForEncoderPerformanceSettings(MediaFormat mediaFormat) {
        mediaFormat.setInteger("priority", 1);
        if (deviceNeedsLowerOperatingRateAvoidingOverflowWorkaround()) {
            mediaFormat.setInteger("operating-rate", 1000);
        } else {
            mediaFormat.setInteger("operating-rate", Integer.MAX_VALUE);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void adjustMediaFormatForH264EncoderSettings(androidx.media3.common.ColorInfo r2, android.media.MediaCodecInfo r3, android.media.MediaFormat r4) {
        /*
            java.lang.String r0 = "video/avc"
            if (r2 == 0) goto L_0x001d
            int r2 = r2.colorTransfer
            F2.U r2 = androidx.media3.transformer.EncoderUtil.getCodecProfilesForHdrFormat(r0, r2)
            boolean r1 = r2.isEmpty()
            if (r1 != 0) goto L_0x001d
            r1 = 0
            java.lang.Object r2 = r2.get(r1)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            goto L_0x001f
        L_0x001d:
            r2 = 8
        L_0x001f:
            int r3 = androidx.media3.transformer.EncoderUtil.findHighestSupportedEncodingLevel(r3, r0, r2)
            r0 = -1
            if (r3 == r0) goto L_0x0036
            java.lang.String r0 = "profile"
            r4.setInteger(r0, r2)
            java.lang.String r2 = "level"
            boolean r0 = r4.containsKey(r2)
            if (r0 != 0) goto L_0x0036
            r4.setInteger(r2, r3)
        L_0x0036:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.DefaultEncoderFactory.adjustMediaFormatForH264EncoderSettings(androidx.media3.common.ColorInfo, android.media.MediaCodecInfo, android.media.MediaFormat):void");
    }

    private static ExportException createExportException(Format format, String str) {
        return ExportException.createForCodec(new IllegalArgumentException(str), 4003, new ExportException.CodecInfo(format.toString(), MimeTypes.isVideo(format.sampleMimeType), false, (String) null));
    }

    private static ExportException createNoSupportedMimeTypeException(Format format, boolean z) {
        String str;
        if (!z || !ColorInfo.isTransferHdr(format.colorInfo)) {
            str = "No MIME type is supported by both encoder and muxer.";
        } else {
            str = "No MIME type is supported by both encoder and muxer. Requested HDR colorInfo: " + format.colorInfo;
        }
        return ExportException.createForCodec(new IllegalArgumentException(str), 4003, new ExportException.CodecInfo(format.toString(), z, false, (String) null));
    }

    private static boolean deviceNeedsDefaultFrameRateWorkaround() {
        return false;
    }

    private static boolean deviceNeedsLowerOperatingRateAvoidingOverflowWorkaround() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 31 || i2 > 34) {
            return false;
        }
        if (Build.SOC_MODEL.equals("SM8550") || Build.SOC_MODEL.equals("SM7450") || Build.SOC_MODEL.equals("SM6450") || Build.SOC_MODEL.equals("SC9863A") || Build.SOC_MODEL.equals("T612") || Build.SOC_MODEL.equals("T606") || Build.SOC_MODEL.equals("T603")) {
            return true;
        }
        return false;
    }

    private static U filterEncoders(List<MediaCodecInfo> list, EncoderFallbackCost encoderFallbackCost) {
        ArrayList arrayList = new ArrayList(list.size());
        int i2 = Integer.MAX_VALUE;
        for (int i7 = 0; i7 < list.size(); i7++) {
            MediaCodecInfo mediaCodecInfo = list.get(i7);
            int parameterSupportGap = encoderFallbackCost.getParameterSupportGap(mediaCodecInfo);
            if (parameterSupportGap != Integer.MAX_VALUE) {
                if (parameterSupportGap < i2) {
                    arrayList.clear();
                    arrayList.add(mediaCodecInfo);
                    i2 = parameterSupportGap;
                } else if (parameterSupportGap == i2) {
                    arrayList.add(mediaCodecInfo);
                }
            }
        }
        return U.y(arrayList);
    }

    private static U filterEncodersByBitrate(List<MediaCodecInfo> list, String str, int i2) {
        return filterEncoders(list, new a(str, i2, 1));
    }

    private static U filterEncodersByBitrateMode(List<MediaCodecInfo> list, String str, int i2) {
        return filterEncoders(list, new a(str, i2, 2));
    }

    private static U filterEncodersByHdrEditingSupport(List<MediaCodecInfo> list, String str, ColorInfo colorInfo) {
        if (Build.VERSION.SDK_INT < 33 || !ColorInfo.isTransferHdr(colorInfo)) {
            return U.y(list);
        }
        return filterEncoders(list, new b(str, colorInfo));
    }

    private static U filterEncodersByResolution(List<MediaCodecInfo> list, String str, int i2, int i7) {
        return filterEncoders(list, new c(str, i2, i7));
    }

    private static U filterEncodersBySampleRate(List<MediaCodecInfo> list, String str, int i2) {
        return filterEncoders(list, new a(str, i2, 0));
    }

    private static EncoderQueryResult findAudioEncoderWithClosestSupportedFormat(Format format, U u) {
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        if (u.isEmpty()) {
            return null;
        }
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) filterEncodersBySampleRate(u, str, format.sampleRate).get(0);
        return new EncoderQueryResult(mediaCodecInfo, format.buildUpon().setSampleRate(EncoderUtil.getClosestSupportedSampleRate(mediaCodecInfo, str, format.sampleRate)).build());
    }

    private static VideoEncoderQueryResult findVideoEncoderWithClosestSupportedFormat(Format format, VideoEncoderSettings videoEncoderSettings, EncoderSelector encoderSelector, boolean z) {
        int i2;
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        ((a) encoderSelector).getClass();
        U b = EncoderSelector.lambda$static$1(str);
        if (b.isEmpty()) {
            return null;
        }
        if (!z) {
            return new VideoEncoderQueryResult((MediaCodecInfo) b.get(0), format, videoEncoderSettings);
        }
        U filterEncodersByHdrEditingSupport = filterEncodersByHdrEditingSupport(b, str, format.colorInfo);
        if (filterEncodersByHdrEditingSupport.isEmpty()) {
            return null;
        }
        U filterEncodersByResolution = filterEncodersByResolution(filterEncodersByHdrEditingSupport, str, format.width, format.height);
        if (filterEncodersByResolution.isEmpty()) {
            return null;
        }
        Size size = (Size) Assertions.checkNotNull(EncoderUtil.getSupportedResolution((MediaCodecInfo) filterEncodersByResolution.get(0), str, format.width, format.height));
        int i7 = videoEncoderSettings.bitrate;
        if (i7 == -1 && (i7 = format.averageBitrate) == -1) {
            i7 = getSuggestedBitrate(size.getWidth(), size.getHeight(), format.frameRate);
        }
        U filterEncodersByBitrate = filterEncodersByBitrate(filterEncodersByResolution, str, i7);
        if (filterEncodersByBitrate.isEmpty()) {
            return null;
        }
        U filterEncodersByBitrateMode = filterEncodersByBitrateMode(filterEncodersByBitrate, str, videoEncoderSettings.bitrateMode);
        if (filterEncodersByBitrateMode.isEmpty()) {
            return null;
        }
        VideoEncoderSettings.Builder buildUpon = videoEncoderSettings.buildUpon();
        Format.Builder height = format.buildUpon().setSampleMimeType(str).setWidth(size.getWidth()).setHeight(size.getHeight());
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) filterEncodersByBitrateMode.get(0);
        int intValue = EncoderUtil.getSupportedBitrateRange(mediaCodecInfo, str).clamp(Integer.valueOf(i7)).intValue();
        buildUpon.setBitrate(intValue);
        height.setAverageBitrate(intValue);
        int i8 = videoEncoderSettings.profile;
        if (i8 == -1 || (i2 = videoEncoderSettings.level) == -1 || i2 > EncoderUtil.findHighestSupportedEncodingLevel(mediaCodecInfo, str, i8)) {
            buildUpon.setEncodingProfileLevel(-1, -1);
        }
        return new VideoEncoderQueryResult(mediaCodecInfo, height.build(), buildUpon.build());
    }

    private static int getSuggestedBitrate(int i2, int i7, float f) {
        return (int) (((double) (((float) (i2 * i7)) * f)) * 0.07d * 2.0d);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$filterEncodersByBitrateMode$2(String str, int i2, MediaCodecInfo mediaCodecInfo) {
        if (EncoderUtil.isBitrateModeSupported(mediaCodecInfo, str, i2)) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$filterEncodersByHdrEditingSupport$3(String str, ColorInfo colorInfo, MediaCodecInfo mediaCodecInfo) {
        if (EncoderUtil.isHdrEditingSupported(mediaCodecInfo, str, (ColorInfo) Assertions.checkNotNull(colorInfo))) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$filterEncodersByResolution$0(String str, int i2, int i7, MediaCodecInfo mediaCodecInfo) {
        Size supportedResolution = EncoderUtil.getSupportedResolution(mediaCodecInfo, str, i2, i7);
        if (supportedResolution == null) {
            return Integer.MAX_VALUE;
        }
        return Math.abs((i2 * i7) - (supportedResolution.getHeight() * supportedResolution.getWidth()));
    }

    public boolean audioNeedsEncoding() {
        return !this.requestedAudioEncoderSettings.equals(AudioEncoderSettings.DEFAULT);
    }

    public boolean videoNeedsEncoding() {
        return !this.requestedVideoEncoderSettings.equals(VideoEncoderSettings.DEFAULT);
    }

    private DefaultEncoderFactory(Builder builder) {
        this.context = builder.context;
        this.videoEncoderSelector = builder.videoEncoderSelector;
        this.requestedVideoEncoderSettings = builder.requestedVideoEncoderSettings;
        this.requestedAudioEncoderSettings = builder.requestedAudioEncoderSettings;
        this.enableFallback = builder.enableFallback;
        this.enableCodecDbLite = builder.enableCodecDbLite;
        this.codecPriority = builder.codecPriority;
    }

    public DefaultCodec createForAudioEncoding(Format format, LogSessionId logSessionId) {
        EncoderQueryResult findAudioEncoderWithClosestSupportedFormat;
        if (format.bitrate == -1) {
            format = format.buildUpon().setAverageBitrate(131072).build();
        }
        boolean z = false;
        if (format.sampleMimeType != null) {
            MediaFormat createMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(format);
            U supportedEncoders = EncoderUtil.getSupportedEncoders(format.sampleMimeType);
            if (!supportedEncoders.isEmpty()) {
                MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) supportedEncoders.get(0);
                if (this.requestedAudioEncoderSettings.profile != -1) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= supportedEncoders.size()) {
                            break;
                        }
                        MediaCodecInfo mediaCodecInfo2 = (MediaCodecInfo) supportedEncoders.get(i2);
                        if (EncoderUtil.findSupportedEncodingProfiles(mediaCodecInfo2, format.sampleMimeType).contains(Integer.valueOf(this.requestedAudioEncoderSettings.profile))) {
                            if (format.sampleMimeType.equals(Encode.CodecsMime.AUDIO_CODEC_AAC)) {
                                createMediaFormatFromFormat.setInteger("aac-profile", this.requestedAudioEncoderSettings.profile);
                            }
                            createMediaFormatFromFormat.setInteger("profile", this.requestedAudioEncoderSettings.profile);
                            z = true;
                            mediaCodecInfo = mediaCodecInfo2;
                        } else {
                            i2++;
                        }
                    }
                }
                if (!z && this.enableFallback && (findAudioEncoderWithClosestSupportedFormat = findAudioEncoderWithClosestSupportedFormat(format, supportedEncoders)) != null) {
                    mediaCodecInfo = findAudioEncoderWithClosestSupportedFormat.encoder;
                    format = findAudioEncoderWithClosestSupportedFormat.supportedFormat;
                    createMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(format);
                }
                Format format2 = format;
                MediaFormat mediaFormat = createMediaFormatFromFormat;
                int i7 = this.requestedAudioEncoderSettings.bitrate;
                if (i7 != -1) {
                    mediaFormat.setInteger("bitrate", i7);
                }
                if (Build.VERSION.SDK_INT >= 35 && logSessionId != null) {
                    TransformerUtil.Api35.setLogSessionIdToMediaCodecFormat(mediaFormat, logSessionId);
                }
                return new DefaultCodec(this.context, format2, mediaFormat, mediaCodecInfo.getName(), false, (Surface) null);
            }
            throw createExportException(format, "No audio media codec found");
        }
        throw createNoSupportedMimeTypeException(format, false);
    }

    public DefaultCodec createForVideoEncoding(Format format, LogSessionId logSessionId) {
        int i2;
        String str;
        if (format.frameRate == -1.0f || deviceNeedsDefaultFrameRateWorkaround()) {
            format = format.buildUpon().setFrameRate(30.0f).build();
        }
        boolean z = true;
        if (format.sampleMimeType != null) {
            Assertions.checkArgument(format.width != -1);
            Assertions.checkArgument(format.height != -1);
            if (format.rotationDegrees != 0) {
                z = false;
            }
            Assertions.checkArgument(z);
            Assertions.checkStateNotNull(this.videoEncoderSelector);
            VideoEncoderQueryResult findVideoEncoderWithClosestSupportedFormat = findVideoEncoderWithClosestSupportedFormat(format, this.requestedVideoEncoderSettings, this.videoEncoderSelector, this.enableFallback);
            if (findVideoEncoderWithClosestSupportedFormat != null) {
                MediaCodecInfo mediaCodecInfo = findVideoEncoderWithClosestSupportedFormat.encoder;
                Format format2 = findVideoEncoderWithClosestSupportedFormat.supportedFormat;
                VideoEncoderSettings videoEncoderSettings = findVideoEncoderWithClosestSupportedFormat.supportedEncoderSettings;
                String str2 = (String) Assertions.checkNotNull(format2.sampleMimeType);
                if (this.enableCodecDbLite) {
                    VideoEncoderSettings recommendedVideoEncoderSettings = CodecDbLite.getRecommendedVideoEncoderSettings(format);
                    VideoEncoderSettings.Builder buildUpon = videoEncoderSettings.buildUpon();
                    if (videoEncoderSettings.maxBFrames == -1) {
                        buildUpon.setMaxBFrames(recommendedVideoEncoderSettings.maxBFrames);
                    }
                    if (videoEncoderSettings.numNonBidirectionalTemporalLayers == -1 && videoEncoderSettings.numBidirectionalTemporalLayers == -1) {
                        buildUpon.setTemporalLayers(recommendedVideoEncoderSettings.numNonBidirectionalTemporalLayers, recommendedVideoEncoderSettings.numBidirectionalTemporalLayers);
                    }
                    videoEncoderSettings = buildUpon.build();
                }
                if (this.enableFallback) {
                    i2 = videoEncoderSettings.bitrate;
                } else {
                    i2 = videoEncoderSettings.bitrate;
                    if (i2 == -1 && (i2 = format2.averageBitrate) == -1) {
                        i2 = getSuggestedBitrate(format2.width, format2.height, format2.frameRate);
                    }
                }
                Format build = format2.buildUpon().setAverageBitrate(i2).build();
                MediaFormat createMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(build);
                createMediaFormatFromFormat.setInteger("bitrate-mode", videoEncoderSettings.bitrateMode);
                createMediaFormatFromFormat.setInteger("frame-rate", Math.round(build.frameRate));
                int i7 = videoEncoderSettings.profile;
                if (i7 != -1 && videoEncoderSettings.level != -1) {
                    createMediaFormatFromFormat.setInteger("profile", i7);
                    createMediaFormatFromFormat.setInteger("level", videoEncoderSettings.level);
                } else if (ColorInfo.isTransferHdr(format.colorInfo)) {
                    createMediaFormatFromFormat.setInteger("profile", ((Integer) EncoderUtil.getCodecProfilesForHdrFormat(str2, ((ColorInfo) Assertions.checkNotNull(format.colorInfo)).colorTransfer).get(0)).intValue());
                }
                if (str2.equals(Encode.CodecsMime.VIDEO_CODEC_H264)) {
                    adjustMediaFormatForH264EncoderSettings(format.colorInfo, mediaCodecInfo, createMediaFormatFromFormat);
                }
                int i8 = Build.VERSION.SDK_INT;
                if (i8 < 31 || !ColorInfo.isTransferHdr(format.colorInfo)) {
                    createMediaFormatFromFormat.setInteger("color-format", 2130708361);
                } else if (EncoderUtil.getSupportedColorFormats(mediaCodecInfo, str2).contains(2130750114)) {
                    createMediaFormatFromFormat.setInteger("color-format", 2130750114);
                } else {
                    throw createExportException(format, "Encoding HDR is not supported on this device.");
                }
                createMediaFormatFromFormat.setFloat("i-frame-interval", videoEncoderSettings.iFrameIntervalSeconds);
                int i10 = videoEncoderSettings.operatingRate;
                int i11 = videoEncoderSettings.priority;
                if (i10 == -1 && i11 == -1) {
                    adjustMediaFormatForEncoderPerformanceSettings(createMediaFormatFromFormat);
                } else {
                    if (i10 != -2) {
                        createMediaFormatFromFormat.setInteger("operating-rate", i10);
                    }
                    if (i11 != -2) {
                        createMediaFormatFromFormat.setInteger("priority", i11);
                    }
                }
                long j2 = videoEncoderSettings.repeatPreviousFrameIntervalUs;
                if (j2 != -1) {
                    createMediaFormatFromFormat.setLong("repeat-previous-frame-after", j2);
                }
                if (i8 >= 35) {
                    createMediaFormatFromFormat.setInteger("importance", Math.max(0, -this.codecPriority));
                    if (logSessionId != null) {
                        TransformerUtil.Api35.setLogSessionIdToMediaCodecFormat(createMediaFormatFromFormat, logSessionId);
                    }
                }
                int i12 = videoEncoderSettings.maxBFrames;
                if (i12 != -1) {
                    createMediaFormatFromFormat.setInteger("max-bframes", i12);
                }
                int i13 = videoEncoderSettings.numNonBidirectionalTemporalLayers;
                int i14 = videoEncoderSettings.numBidirectionalTemporalLayers;
                if (i13 >= 0) {
                    if (i13 == 0) {
                        str = "none";
                    } else if (i14 > 0) {
                        Locale locale = Locale.ROOT;
                        str = j.b(i13, i14, "android.generic.", "+");
                    } else {
                        Locale locale2 = Locale.ROOT;
                        str = C0086a.i(i13, "android.generic.");
                    }
                    createMediaFormatFromFormat.setString("ts-schema", str);
                }
                return new DefaultCodec(this.context, build, createMediaFormatFromFormat, mediaCodecInfo.getName(), false, (Surface) null);
            }
            throw createExportException(format, "The requested video encoding format is not supported.");
        }
        throw createNoSupportedMimeTypeException(format, true);
    }
}
