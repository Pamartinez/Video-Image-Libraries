package androidx.media3.extractor.avi;

import A.a;
import F2.U;
import androidx.media3.common.Format;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.samsung.android.sdk.sgpl.pip.core.Encode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class StreamFormatChunk implements AviChunk {
    public final Format format;

    public StreamFormatChunk(Format format2) {
        this.format = format2;
    }

    private static String getMimeTypeFromCompression(int i2) {
        switch (i2) {
            case 808802372:
            case 877677894:
            case 1145656883:
            case 1145656920:
            case 1482049860:
            case 1684633208:
            case 2021026148:
                return Encode.ContentType.VIDEO_MP4V_ES;
            case 826496577:
            case 828601953:
            case 875967048:
                return Encode.CodecsMime.VIDEO_CODEC_H264;
            case 842289229:
                return "video/mp42";
            case 859066445:
                return "video/mp43";
            case 1196444237:
            case 1735420525:
                return "video/mjpeg";
            default:
                return null;
        }
    }

    private static String getMimeTypeFromTag(int i2) {
        if (i2 == 1) {
            return "audio/raw";
        }
        if (i2 == 85) {
            return "audio/mpeg";
        }
        if (i2 == 255) {
            return Encode.CodecsMime.AUDIO_CODEC_AAC;
        }
        if (i2 == 8192) {
            return "audio/ac3";
        }
        if (i2 != 8193) {
            return null;
        }
        return "audio/vnd.dts";
    }

    private static AviChunk parseBitmapInfoHeader(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        String mimeTypeFromCompression = getMimeTypeFromCompression(readLittleEndianInt3);
        if (mimeTypeFromCompression == null) {
            a.D(readLittleEndianInt3, "Ignoring track with unsupported compression ", "StreamFormatChunk");
            return null;
        }
        Format.Builder builder = new Format.Builder();
        builder.setWidth(readLittleEndianInt).setHeight(readLittleEndianInt2).setSampleMimeType(mimeTypeFromCompression);
        return new StreamFormatChunk(builder.build());
    }

    public static AviChunk parseFrom(int i2, ParsableByteArray parsableByteArray) {
        if (i2 == 2) {
            return parseBitmapInfoHeader(parsableByteArray);
        }
        if (i2 == 1) {
            return parseWaveFormatEx(parsableByteArray);
        }
        Log.w("StreamFormatChunk", "Ignoring strf box for unsupported track type: " + Util.getTrackTypeString(i2));
        return null;
    }

    private static AviChunk parseWaveFormatEx(ParsableByteArray parsableByteArray) {
        int i2;
        int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        String mimeTypeFromTag = getMimeTypeFromTag(readLittleEndianUnsignedShort);
        if (mimeTypeFromTag == null) {
            a.D(readLittleEndianUnsignedShort, "Ignoring track with unsupported format tag ", "StreamFormatChunk");
            return null;
        }
        int readLittleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(6);
        int pcmEncoding = Util.getPcmEncoding(parsableByteArray.readLittleEndianUnsignedShort());
        if (parsableByteArray.bytesLeft() > 0) {
            i2 = parsableByteArray.readLittleEndianUnsignedShort();
        } else {
            i2 = 0;
        }
        Format.Builder builder = new Format.Builder();
        builder.setSampleMimeType(mimeTypeFromTag).setChannelCount(readLittleEndianUnsignedShort2).setSampleRate(readLittleEndianInt);
        if (mimeTypeFromTag.equals("audio/raw") && pcmEncoding != 0) {
            builder.setPcmEncoding(pcmEncoding);
        }
        if (mimeTypeFromTag.equals(Encode.CodecsMime.AUDIO_CODEC_AAC) && i2 > 0) {
            byte[] bArr = new byte[i2];
            parsableByteArray.readBytes(bArr, 0, i2);
            builder.setInitializationData(U.B(bArr));
        }
        return new StreamFormatChunk(builder.build());
    }

    public int getType() {
        return 1718776947;
    }
}
