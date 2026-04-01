package androidx.media3.common.util;

import F2.C0040v;
import F2.N;
import F2.U;
import android.media.MediaFormat;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.message.Message;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaFormatUtil {
    public static Format createFormatFromMediaFormat(MediaFormat mediaFormat) {
        int i2 = 0;
        Format.Builder pcmEncoding = new Format.Builder().setSampleMimeType(mediaFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX)).setLanguage(mediaFormat.getString("language")).setPeakBitrate(getInteger(mediaFormat, "max-bitrate", -1)).setAverageBitrate(getInteger(mediaFormat, "bitrate", -1)).setCodecs(getCodecString(mediaFormat)).setFrameRate(getFrameRate(mediaFormat, -1.0f)).setWidth(getInteger(mediaFormat, "width", -1)).setHeight(getInteger(mediaFormat, "height", -1)).setPixelWidthHeightRatio(getPixelWidthHeightRatio(mediaFormat, 1.0f)).setMaxInputSize(getInteger(mediaFormat, "max-input-size", -1)).setRotationDegrees(getInteger(mediaFormat, Message.KEY_ROTATION, 0)).setColorInfo(getColorInfo(mediaFormat)).setSampleRate(getInteger(mediaFormat, "sample-rate", -1)).setChannelCount(getInteger(mediaFormat, "channel-count", -1)).setPcmEncoding(getInteger(mediaFormat, "pcm-encoding", -1));
        C0040v.c(4, "initialCapacity");
        int i7 = 0;
        Object[] objArr = new Object[4];
        while (true) {
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-" + i2);
            if (byteBuffer == null) {
                break;
            }
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            byteBuffer.rewind();
            int i8 = i7 + 1;
            int e = N.e(objArr.length, i8);
            Object[] copyOf = e <= objArr.length ? objArr : Arrays.copyOf(objArr, e);
            copyOf[i7] = bArr;
            i2++;
            i7 = i8;
            objArr = copyOf;
        }
        pcmEncoding.setInitializationData(U.w(i7, objArr));
        if (mediaFormat.containsKey("track-id")) {
            pcmEncoding.setId(mediaFormat.getInteger("track-id"));
        }
        return pcmEncoding.build();
    }

    public static MediaFormat createMediaFormatFromFormat(Format format) {
        MediaFormat mediaFormat = new MediaFormat();
        maybeSetInteger(mediaFormat, "bitrate", format.bitrate);
        maybeSetInteger(mediaFormat, "max-bitrate", format.peakBitrate);
        maybeSetInteger(mediaFormat, "channel-count", format.channelCount);
        maybeSetColorInfo(mediaFormat, format.colorInfo);
        maybeSetString(mediaFormat, MediaDefs.Image.HEIF.HEIF_MIME_BOX, format.sampleMimeType);
        maybeSetString(mediaFormat, "codecs-string", format.codecs);
        maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        maybeSetInteger(mediaFormat, "width", format.width);
        maybeSetInteger(mediaFormat, "height", format.height);
        setCsdBuffers(mediaFormat, format.initializationData);
        maybeSetPcmEncoding(mediaFormat, format.pcmEncoding);
        maybeSetString(mediaFormat, "language", format.language);
        maybeSetInteger(mediaFormat, "max-input-size", format.maxInputSize);
        maybeSetInteger(mediaFormat, "sample-rate", format.sampleRate);
        maybeSetInteger(mediaFormat, "caption-service-number", format.accessibilityChannel);
        mediaFormat.setInteger(Message.KEY_ROTATION, format.rotationDegrees);
        int i2 = format.selectionFlags;
        setBooleanAsInt(mediaFormat, "is-autoselect", i2 & 4);
        setBooleanAsInt(mediaFormat, "is-default", i2 & 1);
        setBooleanAsInt(mediaFormat, "is-forced-subtitle", i2 & 2);
        mediaFormat.setInteger("encoder-delay", format.encoderDelay);
        mediaFormat.setInteger("encoder-padding", format.encoderPadding);
        maybeSetPixelAspectRatio(mediaFormat, format.pixelWidthHeightRatio);
        String str = format.id;
        if (str != null) {
            try {
                mediaFormat.setInteger("track-id", Integer.parseInt(str));
            } catch (NumberFormatException unused) {
            }
        }
        return mediaFormat;
    }

    public static byte[] getArray(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    private static String getCodecString(MediaFormat mediaFormat) {
        if (Objects.equals(mediaFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX), "video/3gpp") && mediaFormat.containsKey("profile") && mediaFormat.containsKey("level")) {
            return CodecSpecificDataUtil.buildH263CodecString(mediaFormat.getInteger("profile"), mediaFormat.getInteger("level"));
        }
        if (!Objects.equals(mediaFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX), "video/dolby-vision") || !mediaFormat.containsKey("profile") || !mediaFormat.containsKey("level")) {
            return getString(mediaFormat, "codecs-string", (String) null);
        }
        return CodecSpecificDataUtil.buildDolbyVisionCodecString(CodecSpecificDataUtil.dolbyVisionConstantToProfileNumber(mediaFormat.getInteger("profile")), CodecSpecificDataUtil.dolbyVisionConstantToLevelNumber(mediaFormat.getInteger("level")));
    }

    public static ColorInfo getColorInfo(MediaFormat mediaFormat) {
        byte[] bArr;
        int integer = getInteger(mediaFormat, "color-standard", -1);
        int integer2 = getInteger(mediaFormat, "color-range", -1);
        int integer3 = getInteger(mediaFormat, "color-transfer", -1);
        ByteBuffer byteBuffer = mediaFormat.getByteBuffer("hdr-static-info");
        if (byteBuffer != null) {
            bArr = getArray(byteBuffer);
        } else {
            bArr = null;
        }
        if (!isValidColorSpace(integer)) {
            integer = -1;
        }
        if (!isValidColorRange(integer2)) {
            integer2 = -1;
        }
        if (!isValidColorTransfer(integer3)) {
            integer3 = -1;
        }
        if (integer == -1 && integer2 == -1 && integer3 == -1 && bArr == null) {
            return null;
        }
        return new ColorInfo.Builder().setColorSpace(integer).setColorRange(integer2).setColorTransfer(integer3).setHdrStaticInfo(bArr).build();
    }

    private static float getFrameRate(MediaFormat mediaFormat, float f) {
        if (!mediaFormat.containsKey("frame-rate")) {
            return f;
        }
        try {
            return mediaFormat.getFloat("frame-rate");
        } catch (ClassCastException unused) {
            return (float) mediaFormat.getInteger("frame-rate");
        }
    }

    public static int getInteger(MediaFormat mediaFormat, String str, int i2) {
        if (mediaFormat.containsKey(str)) {
            return mediaFormat.getInteger(str);
        }
        return i2;
    }

    private static float getPixelWidthHeightRatio(MediaFormat mediaFormat, float f) {
        if (!mediaFormat.containsKey("sar-width") || !mediaFormat.containsKey("sar-height")) {
            return f;
        }
        return ((float) mediaFormat.getInteger("sar-width")) / ((float) mediaFormat.getInteger("sar-height"));
    }

    public static String getString(MediaFormat mediaFormat, String str, String str2) {
        if (mediaFormat.containsKey(str)) {
            return mediaFormat.getString(str);
        }
        return str2;
    }

    private static boolean isValidColorRange(int i2) {
        if (i2 == 2 || i2 == 1 || i2 == -1) {
            return true;
        }
        return false;
    }

    private static boolean isValidColorSpace(int i2) {
        if (i2 == 2 || i2 == 1 || i2 == 6 || i2 == -1) {
            return true;
        }
        return false;
    }

    private static boolean isValidColorTransfer(int i2) {
        if (i2 == 1 || i2 == 3 || i2 == 6 || i2 == 7 || i2 == -1) {
            return true;
        }
        return false;
    }

    public static void maybeSetByteBuffer(MediaFormat mediaFormat, String str, byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    public static void maybeSetColorInfo(MediaFormat mediaFormat, ColorInfo colorInfo) {
        if (colorInfo != null) {
            maybeSetInteger(mediaFormat, "color-transfer", colorInfo.colorTransfer);
            maybeSetInteger(mediaFormat, "color-standard", colorInfo.colorSpace);
            maybeSetInteger(mediaFormat, "color-range", colorInfo.colorRange);
            maybeSetByteBuffer(mediaFormat, "hdr-static-info", colorInfo.hdrStaticInfo);
        }
    }

    public static void maybeSetFloat(MediaFormat mediaFormat, String str, float f) {
        if (f != -1.0f) {
            mediaFormat.setFloat(str, f);
        }
    }

    public static void maybeSetInteger(MediaFormat mediaFormat, String str, int i2) {
        if (i2 != -1) {
            mediaFormat.setInteger(str, i2);
        }
    }

    private static void maybeSetPcmEncoding(MediaFormat mediaFormat, int i2) {
        int i7;
        if (i2 != -1) {
            maybeSetInteger(mediaFormat, "exo-pcm-encoding-int", i2);
            if (i2 != 0) {
                i7 = 2;
                if (i2 != 2) {
                    i7 = 3;
                    if (i2 != 3) {
                        i7 = 4;
                        if (i2 != 4) {
                            i7 = 21;
                            if (i2 != 21) {
                                i7 = 22;
                                if (i2 != 22) {
                                    return;
                                }
                            }
                        }
                    }
                }
            } else {
                i7 = 0;
            }
            mediaFormat.setInteger("pcm-encoding", i7);
        }
    }

    private static void maybeSetPixelAspectRatio(MediaFormat mediaFormat, float f) {
        int i2;
        mediaFormat.setFloat("exo-pixel-width-height-ratio-float", f);
        int i7 = 1073741824;
        if (f < 1.0f) {
            i7 = (int) (f * ((float) 1073741824));
            i2 = 1073741824;
        } else if (f > 1.0f) {
            i2 = (int) (((float) 1073741824) / f);
        } else {
            i7 = 1;
            i2 = 1;
        }
        mediaFormat.setInteger("sar-width", i7);
        mediaFormat.setInteger("sar-height", i2);
    }

    public static void maybeSetString(MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    private static void setBooleanAsInt(MediaFormat mediaFormat, String str, int i2) {
        int i7;
        if (i2 != 0) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        mediaFormat.setInteger(str, i7);
    }

    public static void setCsdBuffers(MediaFormat mediaFormat, List<byte[]> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            mediaFormat.setByteBuffer(C0086a.i(i2, "csd-"), ByteBuffer.wrap(list.get(i2)));
        }
    }
}
