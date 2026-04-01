package androidx.media3.extractor.mp4;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TrackFragment {
    public long atomPosition;
    public long auxiliaryDataPosition;
    public long dataPosition;
    public boolean definesEncryptionData;
    public DefaultSampleValues header;
    public long nextFragmentDecodeTime;
    public boolean nextFragmentDecodeTimeIncludesMoov;
    public int sampleCount;
    public final ParsableByteArray sampleEncryptionData = new ParsableByteArray();
    public boolean sampleEncryptionDataNeedsFill;
    public boolean[] sampleHasSubsampleEncryptionTable = new boolean[0];
    public boolean[] sampleIsSyncFrameTable = new boolean[0];
    public long[] samplePresentationTimesUs = new long[0];
    public int[] sampleSizeTable = new int[0];
    public TrackEncryptionBox trackEncryptionBox;
    public int trunCount;
    public long[] trunDataPosition = new long[0];
    public int[] trunLength = new int[0];

    public void fillEncryptionData(ExtractorInput extractorInput) {
        extractorInput.readFully(this.sampleEncryptionData.getData(), 0, this.sampleEncryptionData.limit());
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public long getSamplePresentationTimeUs(int i2) {
        return this.samplePresentationTimesUs[i2];
    }

    public void initEncryptionData(int i2) {
        this.sampleEncryptionData.reset(i2);
        this.definesEncryptionData = true;
        this.sampleEncryptionDataNeedsFill = true;
    }

    public void initTables(int i2, int i7) {
        this.trunCount = i2;
        this.sampleCount = i7;
        if (this.trunLength.length < i2) {
            this.trunDataPosition = new long[i2];
            this.trunLength = new int[i2];
        }
        if (this.sampleSizeTable.length < i7) {
            int i8 = (i7 * 125) / 100;
            this.sampleSizeTable = new int[i8];
            this.samplePresentationTimesUs = new long[i8];
            this.sampleIsSyncFrameTable = new boolean[i8];
            this.sampleHasSubsampleEncryptionTable = new boolean[i8];
        }
    }

    public void reset() {
        this.trunCount = 0;
        this.nextFragmentDecodeTime = 0;
        this.nextFragmentDecodeTimeIncludesMoov = false;
        this.definesEncryptionData = false;
        this.sampleEncryptionDataNeedsFill = false;
        this.trackEncryptionBox = null;
    }

    public boolean sampleHasSubsampleEncryptionTable(int i2) {
        if (!this.definesEncryptionData || !this.sampleHasSubsampleEncryptionTable[i2]) {
            return false;
        }
        return true;
    }

    public void fillEncryptionData(ParsableByteArray parsableByteArray) {
        parsableByteArray.readBytes(this.sampleEncryptionData.getData(), 0, this.sampleEncryptionData.limit());
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }
}
