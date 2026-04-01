package androidx.media3.transformer;

import F2.N;
import F2.U;
import F2.y0;
import android.content.Context;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.util.Pair;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.effect.GlEffect;
import androidx.media3.effect.ScaleAndRotateTransformation;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import androidx.media3.transformer.Codec;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import java.util.Objects;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TransformerUtil {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api35 {
        public static void setLogSessionIdToMediaCodecFormat(MediaFormat mediaFormat, LogSessionId logSessionId) {
            LogSessionId unused = LogSessionId.LOG_SESSION_ID_NONE;
            if (!logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                mediaFormat.setString("log-session-id", logSessionId.getStringId());
            }
        }
    }

    private static boolean containsSlowMotionData(Format format) {
        Metadata metadata = format.metadata;
        if (metadata == null) {
            return false;
        }
        for (int i2 = 0; i2 < metadata.length(); i2++) {
            if (metadata.get(i2) instanceof SlowMotionData) {
                return true;
            }
        }
        return false;
    }

    private static String getCommonImageMimeTypeFromExtension(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case 96870:
                if (str.equals("arw")) {
                    c5 = 0;
                    break;
                }
                break;
            case 97669:
                if (str.equals("bmp")) {
                    c5 = 1;
                    break;
                }
                break;
            case 98723:
                if (str.equals("cr2")) {
                    c5 = 2;
                    break;
                }
                break;
            case 99453:
                if (str.equals("dib")) {
                    c5 = 3;
                    break;
                }
                break;
            case 102340:
                if (str.equals("gif")) {
                    c5 = 4;
                    break;
                }
                break;
            case 104085:
                if (str.equals("ico")) {
                    c5 = 5;
                    break;
                }
                break;
            case 104430:
                if (str.equals("k25")) {
                    c5 = 6;
                    break;
                }
                break;
            case 105133:
                if (str.equals("jfi")) {
                    c5 = 7;
                    break;
                }
                break;
            case 105223:
                if (str.equals("jif")) {
                    c5 = 8;
                    break;
                }
                break;
            case 105439:
                if (str.equals("jpe")) {
                    c5 = 9;
                    break;
                }
                break;
            case 105441:
                if (str.equals("jpg")) {
                    c5 = 10;
                    break;
                }
                break;
            case 111145:
                if (str.equals("png")) {
                    c5 = 11;
                    break;
                }
                break;
            case 112680:
                if (str.equals("raw")) {
                    c5 = 12;
                    break;
                }
                break;
            case 114276:
                if (str.equals("svg")) {
                    c5 = 13;
                    break;
                }
                break;
            case 114833:
                if (str.equals("tif")) {
                    c5 = 14;
                    break;
                }
                break;
            case 3006482:
                if (str.equals("avif")) {
                    c5 = 15;
                    break;
                }
                break;
            case 3198679:
                if (str.equals(IFormat.FORMAT_HEIC)) {
                    c5 = 16;
                    break;
                }
                break;
            case 3198682:
                if (str.equals("heif")) {
                    c5 = 17;
                    break;
                }
                break;
            case 3259225:
                if (str.equals("jfif")) {
                    c5 = 18;
                    break;
                }
                break;
            case 3268712:
                if (str.equals(IFormat.FORMAT_JPEG)) {
                    c5 = 19;
                    break;
                }
                break;
            case 3542678:
                if (str.equals("svgz")) {
                    c5 = 20;
                    break;
                }
                break;
            case 3559925:
                if (str.equals("tiff")) {
                    c5 = 21;
                    break;
                }
                break;
            case 3645340:
                if (str.equals("webp")) {
                    c5 = 22;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 2:
            case 6:
            case 12:
                return "image/raw";
            case 1:
            case 3:
                return "image/bmp";
            case 4:
                return "image/gif";
            case 5:
                return "image/x-icon";
            case 7:
            case 8:
            case 9:
            case 10:
            case 18:
            case 19:
                return "image/jpeg";
            case 11:
                return "image/png";
            case 13:
            case 20:
                return "image/svg+xml";
            case 14:
            case 21:
                return "image/tiff";
            case 15:
                return "image/avif";
            case 16:
                return "image/heic";
            case 17:
                return "image/heif";
            case 22:
                return "image/webp";
            default:
                return null;
        }
    }

    public static ColorInfo getDecoderOutputColor(ColorInfo colorInfo, boolean z) {
        if (!z || !ColorInfo.isTransferHdr(colorInfo)) {
            return colorInfo;
        }
        return ColorInfo.SDR_BT709_LIMITED;
    }

    public static String getImageMimeType(Context context, MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        if (localConfiguration == null) {
            return null;
        }
        String str = localConfiguration.mimeType;
        if (str == null) {
            if (Objects.equals(localConfiguration.uri.getScheme(), "content")) {
                return context.getContentResolver().getType(localConfiguration.uri);
            }
            String path = localConfiguration.uri.getPath();
            if (path == null) {
                return null;
            }
            int lastIndexOf = path.lastIndexOf(".");
            if (lastIndexOf >= 0 && lastIndexOf < path.length() - 1) {
                return getCommonImageMimeTypeFromExtension(k.S(path.substring(lastIndexOf + 1)));
            }
        }
        return str;
    }

    public static int getMediaCodecFlags(int i2) {
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        if ((i2 & 4) == 4) {
            return i7 | 4;
        }
        return i7;
    }

    public static Pair<String, Integer> getOutputMimeTypeAndHdrModeAfterFallback(int i2, String str, ColorInfo colorInfo) {
        if (i2 == 0 && ColorInfo.isTransferHdr(colorInfo) && EncoderUtil.getSupportedEncodersForHdrEditing(str, colorInfo).isEmpty()) {
            if (!EncoderUtil.getSupportedEncodersForHdrEditing("video/hevc", colorInfo).isEmpty()) {
                str = "video/hevc";
            } else {
                i2 = 2;
            }
        }
        return Pair.create(str, Integer.valueOf(i2));
    }

    public static int getProcessedTrackType(String str) {
        int trackType = MimeTypes.getTrackType(str);
        if (trackType == 4) {
            return 2;
        }
        return trackType;
    }

    public static ColorInfo getValidColor(ColorInfo colorInfo) {
        if (colorInfo == null || !colorInfo.isDataSpaceValid()) {
            return ColorInfo.SDR_BT709_LIMITED;
        }
        return colorInfo;
    }

    public static boolean isImage(Context context, MediaItem mediaItem) {
        String imageMimeType = getImageMimeType(context, mediaItem);
        if (imageMimeType == null || !MimeTypes.isImage(imageMimeType)) {
            return false;
        }
        return true;
    }

    private static float maybeCalculateTotalRotationDegreesAppliedInEffects(U u, Format format) {
        int i2;
        int i7;
        int i8;
        int i10 = format.rotationDegrees;
        if (i10 % MOCRLang.KHMER == 0) {
            i2 = format.width;
        } else {
            i2 = format.height;
        }
        if (i10 % MOCRLang.KHMER == 0) {
            i7 = format.height;
        } else {
            i7 = format.width;
        }
        float f = 0.0f;
        for (int i11 = 0; i11 < u.size(); i11++) {
            Effect effect = (Effect) u.get(i11);
            if (!(effect instanceof GlEffect)) {
                return -1.0f;
            }
            GlEffect glEffect = (GlEffect) effect;
            if (effect instanceof ScaleAndRotateTransformation) {
                ScaleAndRotateTransformation scaleAndRotateTransformation = (ScaleAndRotateTransformation) effect;
                if (scaleAndRotateTransformation.scaleX != 1.0f || scaleAndRotateTransformation.scaleY != 1.0f) {
                    return -1.0f;
                }
                float f5 = scaleAndRotateTransformation.rotationDegrees;
                if (f5 % 90.0f != 0.0f) {
                    return -1.0f;
                }
                f += f5;
                int i12 = ((f % 180.0f) > 0.0f ? 1 : ((f % 180.0f) == 0.0f ? 0 : -1));
                if (i12 == 0) {
                    i8 = format.width;
                } else {
                    i8 = format.height;
                }
                if (i12 == 0) {
                    i7 = format.height;
                } else {
                    i7 = format.width;
                }
            } else if (!glEffect.isNoOp(i2, i7)) {
                return -1.0f;
            }
        }
        float f8 = f % 360.0f;
        if (f8 % 90.0f == 0.0f) {
            return f8;
        }
        return -1.0f;
    }

    public static void maybeSetMuxerWrapperAdditionalRotationDegrees(MuxerWrapper muxerWrapper, U u, Format format) {
        float maybeCalculateTotalRotationDegreesAppliedInEffects = maybeCalculateTotalRotationDegreesAppliedInEffects(u, format);
        if (maybeCalculateTotalRotationDegreesAppliedInEffects == 90.0f || maybeCalculateTotalRotationDegreesAppliedInEffects == 180.0f || maybeCalculateTotalRotationDegreesAppliedInEffects == 270.0f) {
            muxerWrapper.setAdditionalRotationDegrees(360 - Math.round(maybeCalculateTotalRotationDegreesAppliedInEffects));
        }
    }

    public static boolean shouldTranscodeAudio(Format format, Composition composition, int i2, TransformationRequest transformationRequest, Codec.EncoderFactory encoderFactory, MuxerWrapper muxerWrapper) {
        if (composition.sequences.size() > 1 || ((EditedMediaItemSequence) composition.sequences.get(i2)).editedMediaItems.size() > 1) {
            return !composition.transmuxAudio;
        }
        if (composition.hasGaps() || encoderFactory.audioNeedsEncoding()) {
            return true;
        }
        String str = transformationRequest.audioMimeType;
        if (str != null && !str.equals(format.sampleMimeType)) {
            return true;
        }
        if (transformationRequest.audioMimeType == null && !muxerWrapper.supportsSampleMimeType(format.sampleMimeType)) {
            return true;
        }
        EditedMediaItem editedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(i2)).editedMediaItems.get(0);
        if ((!editedMediaItem.flattenForSlowMotion || !containsSlowMotionData(format)) && editedMediaItem.effects.audioProcessors.isEmpty() && composition.effects.audioProcessors.isEmpty()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r6v4, types: [F2.N, F2.Q] */
    public static boolean shouldTranscodeVideo(Format format, Composition composition, int i2, TransformationRequest transformationRequest, Codec.EncoderFactory encoderFactory, MuxerWrapper muxerWrapper) {
        String str;
        if (composition.sequences.size() > 1 || ((EditedMediaItemSequence) composition.sequences.get(i2)).editedMediaItems.size() > 1) {
            return !composition.transmuxVideo;
        }
        if (!encoderFactory.videoNeedsEncoding() && transformationRequest.hdrMode == 0 && (((str = transformationRequest.videoMimeType) == null || str.equals(format.sampleMimeType) || str.equals(MediaCodecUtil.getAlternativeCodecMimeType(format))) && ((str != null || muxerWrapper.supportsSampleMimeType(format.sampleMimeType) || muxerWrapper.supportsSampleMimeType(MediaCodecUtil.getAlternativeCodecMimeType(format))) && format.pixelWidthHeightRatio == 1.0f))) {
            ? n = new N(4);
            n.c(((EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(i2)).editedMediaItems.get(0)).effects.videoEffects);
            n.c(composition.effects.videoEffects);
            y0 f = n.f();
            if (f.isEmpty() || maybeCalculateTotalRotationDegreesAppliedInEffects(f, format) != -1.0f) {
                return false;
            }
        }
        return true;
    }
}
