package androidx.media3.extractor.mp3;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class XingFrame {
    public final long dataSize;
    public final int encoderDelay;
    public final int encoderPadding;
    public final long frameCount;
    public final MpegAudioUtil.Header header;
    public final long[] tableOfContents;

    private XingFrame(MpegAudioUtil.Header header2, long j2, long j3, long[] jArr, int i2, int i7) {
        this.header = new MpegAudioUtil.Header(header2);
        this.frameCount = j2;
        this.dataSize = j3;
        this.tableOfContents = jArr;
        this.encoderDelay = i2;
        this.encoderPadding = i7;
    }

    public static XingFrame parse(MpegAudioUtil.Header header2, ParsableByteArray parsableByteArray) {
        int i2;
        long j2;
        long[] jArr;
        int i7;
        int i8;
        int readInt = parsableByteArray.readInt();
        if ((readInt & 1) != 0) {
            i2 = parsableByteArray.readUnsignedIntToInt();
        } else {
            i2 = -1;
        }
        if ((readInt & 2) != 0) {
            j2 = parsableByteArray.readUnsignedInt();
        } else {
            j2 = -1;
        }
        long j3 = j2;
        if ((readInt & 4) == 4) {
            jArr = new long[100];
            for (int i10 = 0; i10 < 100; i10++) {
                jArr[i10] = (long) parsableByteArray.readUnsignedByte();
            }
        } else {
            jArr = null;
        }
        long[] jArr2 = jArr;
        if ((readInt & 8) != 0) {
            parsableByteArray.skipBytes(4);
        }
        if (parsableByteArray.bytesLeft() >= 24) {
            parsableByteArray.skipBytes(21);
            int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
            i7 = readUnsignedInt24 & 4095;
            i8 = (16773120 & readUnsignedInt24) >> 12;
        } else {
            i8 = -1;
            i7 = -1;
        }
        return new XingFrame(header2, (long) i2, j3, jArr2, i8, i7);
    }

    public long computeDurationUs() {
        long j2 = this.frameCount;
        if (j2 == -1 || j2 == 0) {
            return -9223372036854775807L;
        }
        MpegAudioUtil.Header header2 = this.header;
        return Util.sampleCountToDurationUs((j2 * ((long) header2.samplesPerFrame)) - 1, header2.sampleRate);
    }
}
