package androidx.media3.extractor.mp3;

import N2.j;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class VbriSeeker implements Seeker {
    private final int bitrate;
    private final long dataEndPosition;
    private final long dataStartPosition;
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    private VbriSeeker(long[] jArr, long[] jArr2, long j2, long j3, long j8, int i2) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j2;
        this.dataStartPosition = j3;
        this.dataEndPosition = j8;
        this.bitrate = i2;
    }

    public static VbriSeeker create(long j2, long j3, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int i2;
        long j8 = j2;
        MpegAudioUtil.Header header2 = header;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.skipBytes(6);
        int readInt = parsableByteArray2.readInt();
        long j10 = j3 + ((long) header2.frameSize);
        long j11 = ((long) readInt) + j10;
        int readInt2 = parsableByteArray2.readInt();
        if (readInt2 <= 0) {
            return null;
        }
        long sampleCountToDurationUs = Util.sampleCountToDurationUs((((long) readInt2) * ((long) header2.samplesPerFrame)) - 1, header2.sampleRate);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray2.readUnsignedShort();
        int readUnsignedShort3 = parsableByteArray2.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        int i7 = readUnsignedShort2;
        long[] jArr = new long[readUnsignedShort];
        long[] jArr2 = new long[readUnsignedShort];
        int i8 = 0;
        long j12 = j3 + ((long) header2.frameSize);
        while (i8 < readUnsignedShort) {
            long[] jArr3 = jArr2;
            long[] jArr4 = jArr;
            jArr4[i8] = (((long) i8) * sampleCountToDurationUs) / ((long) readUnsignedShort);
            jArr3[i8] = j12;
            if (readUnsignedShort3 == 1) {
                i2 = parsableByteArray.readUnsignedByte();
            } else if (readUnsignedShort3 == 2) {
                i2 = parsableByteArray.readUnsignedShort();
            } else if (readUnsignedShort3 == 3) {
                i2 = parsableByteArray.readUnsignedInt24();
            } else if (readUnsignedShort3 != 4) {
                return null;
            } else {
                i2 = parsableByteArray.readUnsignedIntToInt();
            }
            int i10 = i8;
            int i11 = i7;
            j12 += ((long) i2) * ((long) i11);
            i7 = i11;
            i8 = i10 + 1;
            readUnsignedShort = readUnsignedShort;
            jArr = jArr4;
            jArr2 = jArr3;
        }
        long[] jArr5 = jArr2;
        long[] jArr6 = jArr;
        if (!(j8 == -1 || j8 == j11)) {
            StringBuilder j13 = j.j(j8, "VBRI data size mismatch: ", ArcCommonLog.TAG_COMMA);
            j13.append(j11);
            Log.w("VbriSeeker", j13.toString());
        }
        if (j11 != j12) {
            StringBuilder j14 = j.j(j11, "VBRI bytes and ToC mismatch (using max): ", ArcCommonLog.TAG_COMMA);
            j14.append(j12);
            j14.append("\nSeeking will be inaccurate.");
            Log.w("VbriSeeker", j14.toString());
            j11 = Math.max(j11, j12);
        }
        return new VbriSeeker(jArr6, jArr5, sampleCountToDurationUs, j10, j11, header2.bitrate);
    }

    public int getAverageBitrate() {
        return this.bitrate;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public long getDataStartPosition() {
        return this.dataStartPosition;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j2, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[binarySearchFloor], this.positions[binarySearchFloor]);
        if (seekPoint.timeUs >= j2 || binarySearchFloor == this.timesUs.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i2 = binarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs[i2], this.positions[i2]));
    }

    public long getTimeUs(long j2) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j2, true, true)];
    }

    public boolean isSeekable() {
        return true;
    }
}
