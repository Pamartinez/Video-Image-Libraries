package androidx.media3.extractor.avi;

import androidx.media3.common.DataReader;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackOutput;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ChunkReader {
    private final int alternativeChunkId;
    private int bytesRemainingInCurrentChunk;
    private int chunkCount;
    private final int chunkId;
    private int currentChunkIndex;
    private int currentChunkSize;
    private final long durationUs;
    private long firstIndexChunkOffset;
    private int indexChunkCount;
    private int indexSize;
    private int[] keyFrameIndices;
    private long[] keyFrameOffsets;
    private final AviStreamHeaderChunk streamHeaderChunk;
    private final TrackOutput trackOutput;

    public ChunkReader(int i2, AviStreamHeaderChunk aviStreamHeaderChunk, TrackOutput trackOutput2) {
        int i7;
        int i8;
        this.streamHeaderChunk = aviStreamHeaderChunk;
        int trackType = aviStreamHeaderChunk.getTrackType();
        boolean z = true;
        if (!(trackType == 1 || trackType == 2)) {
            z = false;
        }
        Assertions.checkArgument(z);
        if (trackType == 2) {
            i7 = 1667497984;
        } else {
            i7 = 1651965952;
        }
        this.chunkId = getChunkIdFourCc(i2, i7);
        this.durationUs = aviStreamHeaderChunk.getDurationUs();
        this.trackOutput = trackOutput2;
        if (trackType == 2) {
            i8 = getChunkIdFourCc(i2, 1650720768);
        } else {
            i8 = -1;
        }
        this.alternativeChunkId = i8;
        this.firstIndexChunkOffset = -1;
        this.keyFrameOffsets = new long[512];
        this.keyFrameIndices = new int[512];
        this.chunkCount = aviStreamHeaderChunk.length;
    }

    private static int getChunkIdFourCc(int i2, int i7) {
        return (((i2 % 10) + 48) << 8) | ((i2 / 10) + 48) | i7;
    }

    private long getChunkTimestampUs(int i2) {
        return (this.durationUs * ((long) i2)) / ((long) this.chunkCount);
    }

    private SeekPoint getSeekPoint(int i2) {
        return new SeekPoint(((long) this.keyFrameIndices[i2]) * getFrameDurationUs(), this.keyFrameOffsets[i2]);
    }

    public void advanceCurrentChunk() {
        this.currentChunkIndex++;
    }

    public void appendIndexChunk(long j2, boolean z) {
        if (this.firstIndexChunkOffset == -1) {
            this.firstIndexChunkOffset = j2;
        }
        if (z) {
            if (this.indexSize == this.keyFrameIndices.length) {
                long[] jArr = this.keyFrameOffsets;
                this.keyFrameOffsets = Arrays.copyOf(jArr, (jArr.length * 3) / 2);
                int[] iArr = this.keyFrameIndices;
                this.keyFrameIndices = Arrays.copyOf(iArr, (iArr.length * 3) / 2);
            }
            long[] jArr2 = this.keyFrameOffsets;
            int i2 = this.indexSize;
            jArr2[i2] = j2;
            this.keyFrameIndices[i2] = this.indexChunkCount;
            this.indexSize = i2 + 1;
        }
        this.indexChunkCount++;
    }

    public void commitIndex() {
        int i2;
        this.keyFrameOffsets = Arrays.copyOf(this.keyFrameOffsets, this.indexSize);
        this.keyFrameIndices = Arrays.copyOf(this.keyFrameIndices, this.indexSize);
        if (isAudio() && this.streamHeaderChunk.sampleSize != 0 && (i2 = this.indexSize) > 0) {
            this.chunkCount = i2;
        }
    }

    public long getCurrentChunkTimestampUs() {
        return getChunkTimestampUs(this.currentChunkIndex);
    }

    public long getFrameDurationUs() {
        return getChunkTimestampUs(1);
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        if (this.indexSize == 0) {
            return new SeekMap.SeekPoints(new SeekPoint(0, this.firstIndexChunkOffset));
        }
        int frameDurationUs = (int) (j2 / getFrameDurationUs());
        int binarySearchFloor = Util.binarySearchFloor(this.keyFrameIndices, frameDurationUs, true, true);
        if (this.keyFrameIndices[binarySearchFloor] == frameDurationUs) {
            return new SeekMap.SeekPoints(getSeekPoint(binarySearchFloor));
        }
        SeekPoint seekPoint = getSeekPoint(binarySearchFloor);
        int i2 = binarySearchFloor + 1;
        if (i2 < this.keyFrameOffsets.length) {
            return new SeekMap.SeekPoints(seekPoint, getSeekPoint(i2));
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    public boolean handlesChunkId(int i2) {
        if (this.chunkId == i2 || this.alternativeChunkId == i2) {
            return true;
        }
        return false;
    }

    public boolean isAudio() {
        if ((this.chunkId & 1651965952) == 1651965952) {
            return true;
        }
        return false;
    }

    public boolean isCurrentFrameAKeyFrame() {
        if (Arrays.binarySearch(this.keyFrameIndices, this.currentChunkIndex) >= 0) {
            return true;
        }
        return false;
    }

    public boolean onChunkData(ExtractorInput extractorInput) {
        int i2 = this.bytesRemainingInCurrentChunk;
        boolean z = false;
        int sampleData = i2 - this.trackOutput.sampleData((DataReader) extractorInput, i2, false);
        this.bytesRemainingInCurrentChunk = sampleData;
        if (sampleData == 0) {
            z = true;
        }
        if (z) {
            if (this.currentChunkSize > 0) {
                TrackOutput trackOutput2 = this.trackOutput;
                long currentChunkTimestampUs = getCurrentChunkTimestampUs();
                boolean isCurrentFrameAKeyFrame = isCurrentFrameAKeyFrame();
                trackOutput2.sampleMetadata(currentChunkTimestampUs, isCurrentFrameAKeyFrame ? 1 : 0, this.currentChunkSize, 0, (TrackOutput.CryptoData) null);
            }
            advanceCurrentChunk();
        }
        return z;
    }

    public void onChunkStart(int i2) {
        this.currentChunkSize = i2;
        this.bytesRemainingInCurrentChunk = i2;
    }

    public void seekToPosition(long j2) {
        if (this.indexSize == 0) {
            this.currentChunkIndex = 0;
            return;
        }
        this.currentChunkIndex = this.keyFrameIndices[Util.binarySearchFloor(this.keyFrameOffsets, j2, true, true)];
    }
}
