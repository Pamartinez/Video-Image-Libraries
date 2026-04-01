package androidx.media3.transformer;

import F2.G;
import F2.U;
import F2.y0;
import W.a;
import android.content.Context;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Build;
import android.util.Pair;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.ExportException;
import androidx.media3.transformer.TransformerUtil;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import java.util.ArrayList;
import java.util.List;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultDecoderFactory implements Codec.DecoderFactory {
    private final int codecPriority;
    private final Context context;
    private final boolean dynamicSchedulingEnabled;
    private final boolean enableDecoderFallback;
    private final Listener listener;
    private final MediaCodecSelector mediaCodecSelector;
    private final boolean shouldConfigureOperatingRate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
    }

    private static void configureOperatingRate(MediaFormat mediaFormat) {
        if (deviceNeedsPriorityWorkaround()) {
            mediaFormat.setInteger("priority", 1);
        }
        mediaFormat.setInteger("operating-rate", 10000);
    }

    private DefaultCodec createCodecForMediaFormat(MediaFormat mediaFormat, Format format, Surface surface, boolean z, LogSessionId logSessionId) {
        G g = U.e;
        y0 y0Var = y0.f278h;
        Assertions.checkNotNull(format.sampleMimeType);
        try {
            List<MediaCodecInfo> decoderInfosSortedByFullFormatSupport = MediaCodecUtil.getDecoderInfosSortedByFullFormatSupport(MediaCodecUtil.getDecoderInfosSoftMatch(this.mediaCodecSelector, format, false, false), format);
            if (!decoderInfosSortedByFullFormatSupport.isEmpty()) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < decoderInfosSortedByFullFormatSupport.size(); i2++) {
                        MediaCodecInfo mediaCodecInfo = decoderInfosSortedByFullFormatSupport.get(i2);
                        if (!mediaCodecInfo.hardwareAccelerated) {
                            arrayList.add(mediaCodecInfo);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        decoderInfosSortedByFullFormatSupport = arrayList;
                    }
                }
                int i7 = Build.VERSION.SDK_INT;
                if (i7 >= 31 && decoderInfosSortedByFullFormatSupport.get(0).codecMimeType.equals("video/dolby-vision")) {
                    mediaFormat.setInteger("color-transfer-request", 7);
                }
                if (i7 >= 35 && logSessionId != null) {
                    TransformerUtil.Api35.setLogSessionIdToMediaCodecFormat(mediaFormat, logSessionId);
                }
                ArrayList arrayList2 = new ArrayList();
                Context context2 = this.context;
                if (!this.enableDecoderFallback) {
                    decoderInfosSortedByFullFormatSupport = decoderInfosSortedByFullFormatSupport.subList(0, 1);
                }
                DefaultCodec createCodecFromDecoderInfos = createCodecFromDecoderInfos(context2, decoderInfosSortedByFullFormatSupport, format, mediaFormat, surface, arrayList2);
                Listener listener2 = this.listener;
                String name = createCodecFromDecoderInfos.getName();
                ((a) listener2).getClass();
                Builder.lambda$new$0(name, arrayList2);
                return createCodecFromDecoderInfos;
            }
            throw createExportException(format, "No decoders for format");
        } catch (MediaCodecUtil.DecoderQueryException e) {
            Log.e("DefaultDecoderFactory", "Error querying decoders", e);
            throw createExportException(format, "Querying codecs failed");
        }
    }

    private static DefaultCodec createCodecFromDecoderInfos(Context context2, List<MediaCodecInfo> list, Format format, MediaFormat mediaFormat, Surface surface, List<ExportException> list2) {
        Context context3;
        Format format2;
        MediaFormat mediaFormat2;
        Surface surface2;
        for (MediaCodecInfo next : list) {
            mediaFormat.setString(MediaDefs.Image.HEIF.HEIF_MIME_BOX, next.codecMimeType);
            try {
                context3 = context2;
                format2 = format;
                mediaFormat2 = mediaFormat;
                surface2 = surface;
                try {
                    return new DefaultCodec(context3, format2, mediaFormat2, next.name, true, surface2);
                } catch (ExportException e) {
                    e = e;
                }
            } catch (ExportException e7) {
                e = e7;
                context3 = context2;
                format2 = format;
                mediaFormat2 = mediaFormat;
                surface2 = surface;
                list2.add(e);
                context2 = context3;
                format = format2;
                mediaFormat = mediaFormat2;
                surface = surface2;
            }
        }
        throw list2.get(0);
    }

    private static ExportException createExportException(Format format, String str) {
        return ExportException.createForCodec(new IllegalArgumentException(str), LiveTranslationTask.ERRORTYPE.ERR_INVALID_PARSING_DATA, new ExportException.CodecInfo(format.toString(), MimeTypes.isVideo((String) Assertions.checkNotNull(format.sampleMimeType)), true, (String) null));
    }

    private static boolean decoderSupportsKeyAllowFrameDrop(Context context2) {
        if (context2.getApplicationInfo().targetSdkVersion >= 29) {
            return true;
        }
        return false;
    }

    private static boolean deviceNeedsDisable8kWorkaround(Format format) {
        String str;
        if (Build.VERSION.SDK_INT >= 31 || format.width < 7680 || format.height < 4320 || (str = format.sampleMimeType) == null || !str.equals("video/hevc")) {
            return false;
        }
        String str2 = Build.MODEL;
        if (str2.equals("SM-F711U1") || str2.equals("SM-F926U1")) {
            return true;
        }
        return false;
    }

    private static boolean deviceNeedsDisableToneMappingWorkaround(int i2) {
        if (Build.MANUFACTURER.equals("Google") && Build.ID.startsWith("TP1A")) {
            return true;
        }
        if (i2 == 7) {
            String str = Build.MODEL;
            if (str.startsWith("SM-F936") || str.startsWith("SM-F916") || str.startsWith("SM-F721") || str.equals("SM-X900")) {
                return true;
            }
        }
        if (Build.VERSION.SDK_INT >= 34 || i2 != 6 || !Build.MODEL.startsWith("SM-F936")) {
            return false;
        }
        return true;
    }

    private static boolean deviceNeedsNoFrameRateWorkaround() {
        return false;
    }

    private static boolean deviceNeedsPriorityWorkaround() {
        if (Build.VERSION.SDK_INT < 31) {
            return false;
        }
        if (Build.SOC_MODEL.equals("s5e8835") || Build.SOC_MODEL.equals("SA8155P")) {
            return true;
        }
        return false;
    }

    private static boolean devicePrefersSoftwareDecoder(Format format) {
        if (format.width * format.height < 2073600) {
            return false;
        }
        String str = Build.MODEL;
        if (k.v(str, "vivo 1906") || k.v(str, "redmi 7a") || k.v(str, "redmi 8")) {
            return true;
        }
        return false;
    }

    public boolean isDynamicSchedulingEnabled() {
        return this.dynamicSchedulingEnabled;
    }

    private DefaultDecoderFactory(Builder builder) {
        this.context = builder.context;
        this.enableDecoderFallback = builder.enableDecoderFallback;
        this.listener = builder.listener;
        this.codecPriority = builder.codecPriority;
        this.shouldConfigureOperatingRate = builder.shouldConfigureOperatingRate;
        this.mediaCodecSelector = builder.mediaCodecSelector;
        this.dynamicSchedulingEnabled = builder.dynamicSchedulingEnabled;
    }

    public DefaultCodec createForAudioDecoding(Format format, LogSessionId logSessionId) {
        return createCodecForMediaFormat(MediaFormatUtil.createMediaFormatFromFormat(format), format, (Surface) null, false, logSessionId);
    }

    public DefaultCodec createForVideoDecoding(Format format, Surface surface, boolean z, LogSessionId logSessionId) {
        if (ColorInfo.isTransferHdr(format.colorInfo) && z && (Build.VERSION.SDK_INT < 31 || deviceNeedsDisableToneMappingWorkaround(((ColorInfo) Assertions.checkNotNull(format.colorInfo)).colorTransfer))) {
            throw createExportException(format, "Tone-mapping HDR is not supported on this device.");
        } else if (!deviceNeedsDisable8kWorkaround(format)) {
            if (deviceNeedsNoFrameRateWorkaround()) {
                format = format.buildUpon().setFrameRate(-1.0f).build();
            }
            Format format2 = format;
            MediaFormat createMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(format2);
            if (decoderSupportsKeyAllowFrameDrop(this.context)) {
                createMediaFormatFromFormat.setInteger("allow-frame-drop", 0);
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 31 && z) {
                createMediaFormatFromFormat.setInteger("color-transfer-request", 3);
            }
            Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format2);
            if (codecProfileAndLevel != null) {
                MediaFormatUtil.maybeSetInteger(createMediaFormatFromFormat, "profile", ((Integer) codecProfileAndLevel.first).intValue());
                MediaFormatUtil.maybeSetInteger(createMediaFormatFromFormat, "level", ((Integer) codecProfileAndLevel.second).intValue());
            }
            if (i2 >= 35) {
                createMediaFormatFromFormat.setInteger("importance", Math.max(0, -this.codecPriority));
            }
            if (this.shouldConfigureOperatingRate) {
                configureOperatingRate(createMediaFormatFromFormat);
            }
            return createCodecForMediaFormat(createMediaFormatFromFormat, format2, surface, devicePrefersSoftwareDecoder(format2), logSessionId);
        } else {
            throw createExportException(format, "Decoding 8k is not supported on this device.");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public int codecPriority = -2000;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public boolean dynamicSchedulingEnabled;
        /* access modifiers changed from: private */
        public boolean enableDecoderFallback;
        /* access modifiers changed from: private */
        public Listener listener = new a(5);
        /* access modifiers changed from: private */
        public MediaCodecSelector mediaCodecSelector = MediaCodecSelector.DEFAULT;
        /* access modifiers changed from: private */
        public boolean shouldConfigureOperatingRate = false;

        public Builder(Context context2) {
            this.context = context2.getApplicationContext();
        }

        public DefaultDecoderFactory build() {
            return new DefaultDecoderFactory(this);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$new$0(String str, List list) {
        }
    }
}
