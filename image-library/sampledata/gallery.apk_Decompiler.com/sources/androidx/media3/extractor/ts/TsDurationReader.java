package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.PositionHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TsDurationReader {
    private long durationUs = -9223372036854775807L;
    private long firstPcrValue = -9223372036854775807L;
    private boolean isDurationRead;
    private boolean isFirstPcrValueRead;
    private boolean isLastPcrValueRead;
    private long lastPcrValue = -9223372036854775807L;
    private final ParsableByteArray packetBuffer = new ParsableByteArray();
    private final TimestampAdjuster pcrTimestampAdjuster = new TimestampAdjuster(0);
    private final int timestampSearchBytes;

    public TsDurationReader(int i2) {
        this.timestampSearchBytes = i2;
    }

    private int finishReadDuration(ExtractorInput extractorInput) {
        this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        this.isDurationRead = true;
        extractorInput.resetPeekPosition();
        return 0;
    }

    private int readFirstPcrValue(ExtractorInput extractorInput, PositionHolder positionHolder, int i2) {
        int min = (int) Math.min((long) this.timestampSearchBytes, extractorInput.getLength());
        long j2 = (long) 0;
        if (extractorInput.getPosition() != j2) {
            positionHolder.position = j2;
            return 1;
        }
        this.packetBuffer.reset(min);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
        this.firstPcrValue = readFirstPcrValueFromBuffer(this.packetBuffer, i2);
        this.isFirstPcrValueRead = true;
        return 0;
    }

    private long readFirstPcrValueFromBuffer(ParsableByteArray parsableByteArray, int i2) {
        int limit = parsableByteArray.limit();
        for (int position = parsableByteArray.getPosition(); position < limit; position++) {
            if (parsableByteArray.getData()[position] == 71) {
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, position, i2);
                if (readPcrFromPacket != -9223372036854775807L) {
                    return readPcrFromPacket;
                }
            }
        }
        return -9223372036854775807L;
    }

    private int readLastPcrValue(ExtractorInput extractorInput, PositionHolder positionHolder, int i2) {
        long length = extractorInput.getLength();
        int min = (int) Math.min((long) this.timestampSearchBytes, length);
        long j2 = length - ((long) min);
        if (extractorInput.getPosition() != j2) {
            positionHolder.position = j2;
            return 1;
        }
        this.packetBuffer.reset(min);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
        this.lastPcrValue = readLastPcrValueFromBuffer(this.packetBuffer, i2);
        this.isLastPcrValueRead = true;
        return 0;
    }

    private long readLastPcrValueFromBuffer(ParsableByteArray parsableByteArray, int i2) {
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        for (int i7 = limit - 188; i7 >= position; i7--) {
            if (TsUtil.isStartOfTsPacket(parsableByteArray.getData(), position, limit, i7)) {
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, i7, i2);
                if (readPcrFromPacket != -9223372036854775807L) {
                    return readPcrFromPacket;
                }
            }
        }
        return -9223372036854775807L;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public TimestampAdjuster getPcrTimestampAdjuster() {
        return this.pcrTimestampAdjuster;
    }

    public boolean isDurationReadFinished() {
        return this.isDurationRead;
    }

    public int readDuration(ExtractorInput extractorInput, PositionHolder positionHolder, int i2) {
        if (i2 <= 0) {
            return finishReadDuration(extractorInput);
        }
        if (!this.isLastPcrValueRead) {
            return readLastPcrValue(extractorInput, positionHolder, i2);
        }
        if (this.lastPcrValue == -9223372036854775807L) {
            return finishReadDuration(extractorInput);
        }
        if (!this.isFirstPcrValueRead) {
            return readFirstPcrValue(extractorInput, positionHolder, i2);
        }
        long j2 = this.firstPcrValue;
        if (j2 == -9223372036854775807L) {
            return finishReadDuration(extractorInput);
        }
        this.durationUs = this.pcrTimestampAdjuster.adjustTsTimestampGreaterThanPreviousTimestamp(this.lastPcrValue) - this.pcrTimestampAdjuster.adjustTsTimestamp(j2);
        return finishReadDuration(extractorInput);
    }
}
