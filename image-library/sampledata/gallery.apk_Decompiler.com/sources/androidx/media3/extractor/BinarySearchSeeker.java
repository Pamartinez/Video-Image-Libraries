package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BinarySearchSeeker {
    private final int minimumSearchRange;
    protected final BinarySearchSeekMap seekMap;
    protected SeekOperationParams seekOperationParams;
    protected final TimestampSeeker timestampSeeker;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BinarySearchSeekMap implements SeekMap {
        /* access modifiers changed from: private */
        public final long approxBytesPerFrame;
        /* access modifiers changed from: private */
        public final long ceilingBytePosition;
        /* access modifiers changed from: private */
        public final long ceilingTimePosition;
        private final long durationUs;
        /* access modifiers changed from: private */
        public final long floorBytePosition;
        /* access modifiers changed from: private */
        public final long floorTimePosition;
        private final SeekTimestampConverter seekTimestampConverter;

        public BinarySearchSeekMap(SeekTimestampConverter seekTimestampConverter2, long j2, long j3, long j8, long j10, long j11, long j12) {
            this.seekTimestampConverter = seekTimestampConverter2;
            this.durationUs = j2;
            this.floorTimePosition = j3;
            this.ceilingTimePosition = j8;
            this.floorBytePosition = j10;
            this.ceilingBytePosition = j11;
            this.approxBytesPerFrame = j12;
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekMap.SeekPoints getSeekPoints(long j2) {
            return new SeekMap.SeekPoints(new SeekPoint(j2, SeekOperationParams.calculateNextSearchBytePosition(this.seekTimestampConverter.timeUsToTargetTime(j2), this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame)));
        }

        public boolean isSeekable() {
            return true;
        }

        public long timeUsToTargetTime(long j2) {
            return this.seekTimestampConverter.timeUsToTargetTime(j2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SeekOperationParams {
        private final long approxBytesPerFrame;
        private long ceilingBytePosition;
        private long ceilingTimePosition;
        private long floorBytePosition;
        private long floorTimePosition;
        private long nextSearchBytePosition;
        private final long seekTimeUs;
        private final long targetTimePosition;

        public SeekOperationParams(long j2, long j3, long j8, long j10, long j11, long j12, long j13) {
            this.seekTimeUs = j2;
            this.targetTimePosition = j3;
            this.floorTimePosition = j8;
            this.ceilingTimePosition = j10;
            this.floorBytePosition = j11;
            this.ceilingBytePosition = j12;
            this.approxBytesPerFrame = j13;
            this.nextSearchBytePosition = calculateNextSearchBytePosition(j3, j8, j10, j11, j12, j13);
        }

        public static long calculateNextSearchBytePosition(long j2, long j3, long j8, long j10, long j11, long j12) {
            if (j10 + 1 >= j11 || j3 + 1 >= j8) {
                return j10;
            }
            long j13 = (long) (((float) (j2 - j3)) * (((float) (j11 - j10)) / ((float) (j8 - j3))));
            return Util.constrainValue(((j13 + j10) - j12) - (j13 / 20), j10, j11 - 1);
        }

        /* access modifiers changed from: private */
        public long getCeilingBytePosition() {
            return this.ceilingBytePosition;
        }

        /* access modifiers changed from: private */
        public long getFloorBytePosition() {
            return this.floorBytePosition;
        }

        /* access modifiers changed from: private */
        public long getNextSearchBytePosition() {
            return this.nextSearchBytePosition;
        }

        /* access modifiers changed from: private */
        public long getSeekTimeUs() {
            return this.seekTimeUs;
        }

        /* access modifiers changed from: private */
        public long getTargetTimePosition() {
            return this.targetTimePosition;
        }

        private void updateNextSearchBytePosition() {
            this.nextSearchBytePosition = calculateNextSearchBytePosition(this.targetTimePosition, this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame);
        }

        /* access modifiers changed from: private */
        public void updateSeekCeiling(long j2, long j3) {
            this.ceilingTimePosition = j2;
            this.ceilingBytePosition = j3;
            updateNextSearchBytePosition();
        }

        /* access modifiers changed from: private */
        public void updateSeekFloor(long j2, long j3) {
            this.floorTimePosition = j2;
            this.floorBytePosition = j3;
            updateNextSearchBytePosition();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SeekTimestampConverter {
        long timeUsToTargetTime(long j2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TimestampSearchResult {
        public static final TimestampSearchResult NO_TIMESTAMP_IN_RANGE_RESULT = new TimestampSearchResult(-3, -9223372036854775807L, -1);
        /* access modifiers changed from: private */
        public final long bytePositionToUpdate;
        /* access modifiers changed from: private */
        public final long timestampToUpdate;
        /* access modifiers changed from: private */
        public final int type;

        private TimestampSearchResult(int i2, long j2, long j3) {
            this.type = i2;
            this.timestampToUpdate = j2;
            this.bytePositionToUpdate = j3;
        }

        public static TimestampSearchResult overestimatedResult(long j2, long j3) {
            return new TimestampSearchResult(-1, j2, j3);
        }

        public static TimestampSearchResult targetFoundResult(long j2) {
            return new TimestampSearchResult(0, -9223372036854775807L, j2);
        }

        public static TimestampSearchResult underestimatedResult(long j2, long j3) {
            return new TimestampSearchResult(-2, j2, j3);
        }
    }

    public BinarySearchSeeker(SeekTimestampConverter seekTimestampConverter, TimestampSeeker timestampSeeker2, long j2, long j3, long j8, long j10, long j11, long j12, int i2) {
        this.timestampSeeker = timestampSeeker2;
        this.minimumSearchRange = i2;
        this.seekMap = new BinarySearchSeekMap(seekTimestampConverter, j2, j3, j8, j10, j11, j12);
    }

    public SeekOperationParams createSeekParamsForTargetTimeUs(long j2) {
        long j3 = j2;
        return new SeekOperationParams(j3, this.seekMap.timeUsToTargetTime(j3), this.seekMap.floorTimePosition, this.seekMap.ceilingTimePosition, this.seekMap.floorBytePosition, this.seekMap.ceilingBytePosition, this.seekMap.approxBytesPerFrame);
    }

    public final SeekMap getSeekMap() {
        return this.seekMap;
    }

    public int handlePendingSeek(ExtractorInput extractorInput, PositionHolder positionHolder) {
        while (true) {
            SeekOperationParams seekOperationParams2 = (SeekOperationParams) Assertions.checkStateNotNull(this.seekOperationParams);
            long access$100 = seekOperationParams2.getFloorBytePosition();
            long access$200 = seekOperationParams2.getCeilingBytePosition();
            long access$300 = seekOperationParams2.getNextSearchBytePosition();
            if (access$200 - access$100 <= ((long) this.minimumSearchRange)) {
                markSeekOperationFinished(false, access$100);
                return seekToPosition(extractorInput, access$100, positionHolder);
            } else if (!skipInputUntilPosition(extractorInput, access$300)) {
                return seekToPosition(extractorInput, access$300, positionHolder);
            } else {
                extractorInput.resetPeekPosition();
                TimestampSearchResult searchForTimestamp = this.timestampSeeker.searchForTimestamp(extractorInput, seekOperationParams2.getTargetTimePosition());
                int access$500 = searchForTimestamp.type;
                if (access$500 == -3) {
                    markSeekOperationFinished(false, access$300);
                    return seekToPosition(extractorInput, access$300, positionHolder);
                } else if (access$500 == -2) {
                    seekOperationParams2.updateSeekFloor(searchForTimestamp.timestampToUpdate, searchForTimestamp.bytePositionToUpdate);
                } else if (access$500 == -1) {
                    seekOperationParams2.updateSeekCeiling(searchForTimestamp.timestampToUpdate, searchForTimestamp.bytePositionToUpdate);
                } else if (access$500 == 0) {
                    skipInputUntilPosition(extractorInput, searchForTimestamp.bytePositionToUpdate);
                    markSeekOperationFinished(true, searchForTimestamp.bytePositionToUpdate);
                    return seekToPosition(extractorInput, searchForTimestamp.bytePositionToUpdate, positionHolder);
                } else {
                    throw new IllegalStateException("Invalid case");
                }
            }
        }
    }

    public final boolean isSeeking() {
        if (this.seekOperationParams != null) {
            return true;
        }
        return false;
    }

    public final void markSeekOperationFinished(boolean z, long j2) {
        this.seekOperationParams = null;
        this.timestampSeeker.onSeekFinished();
        onSeekOperationFinished(z, j2);
    }

    public final int seekToPosition(ExtractorInput extractorInput, long j2, PositionHolder positionHolder) {
        if (j2 == extractorInput.getPosition()) {
            return 0;
        }
        positionHolder.position = j2;
        return 1;
    }

    public final void setSeekTargetUs(long j2) {
        SeekOperationParams seekOperationParams2 = this.seekOperationParams;
        if (seekOperationParams2 == null || seekOperationParams2.getSeekTimeUs() != j2) {
            this.seekOperationParams = createSeekParamsForTargetTimeUs(j2);
        }
    }

    public final boolean skipInputUntilPosition(ExtractorInput extractorInput, long j2) {
        long position = j2 - extractorInput.getPosition();
        if (position < 0 || position > 262144) {
            return false;
        }
        extractorInput.skipFully((int) position);
        return true;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TimestampSeeker {
        TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j2);

        void onSeekFinished() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultSeekTimestampConverter implements SeekTimestampConverter {
        public long timeUsToTargetTime(long j2) {
            return j2;
        }
    }

    public void onSeekOperationFinished(boolean z, long j2) {
    }
}
