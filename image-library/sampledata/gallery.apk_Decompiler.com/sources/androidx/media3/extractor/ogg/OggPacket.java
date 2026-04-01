package androidx.media3.extractor.ogg;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorUtil;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class OggPacket {
    private int currentSegmentIndex = -1;
    private final ParsableByteArray packetArray = new ParsableByteArray(new byte[65025], 0);
    private final OggPageHeader pageHeader = new OggPageHeader();
    private boolean populated;
    private int segmentCount;

    private int calculatePacketSize(int i2) {
        int i7;
        int i8 = 0;
        this.segmentCount = 0;
        do {
            int i10 = this.segmentCount;
            int i11 = i2 + i10;
            OggPageHeader oggPageHeader = this.pageHeader;
            if (i11 >= oggPageHeader.pageSegmentCount) {
                break;
            }
            int[] iArr = oggPageHeader.laces;
            this.segmentCount = i10 + 1;
            i7 = iArr[i10 + i2];
            i8 += i7;
        } while (i7 == 255);
        return i8;
    }

    public OggPageHeader getPageHeader() {
        return this.pageHeader;
    }

    public ParsableByteArray getPayload() {
        return this.packetArray;
    }

    public boolean populate(ExtractorInput extractorInput) {
        boolean z;
        boolean z3;
        int i2;
        if (extractorInput != null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        if (this.populated) {
            this.populated = false;
            this.packetArray.reset(0);
        }
        while (!this.populated) {
            if (this.currentSegmentIndex < 0) {
                if (!this.pageHeader.skipToNextPage(extractorInput) || !this.pageHeader.populate(extractorInput, true)) {
                    return false;
                }
                OggPageHeader oggPageHeader = this.pageHeader;
                int i7 = oggPageHeader.headerSize;
                if ((oggPageHeader.type & 1) == 1 && this.packetArray.limit() == 0) {
                    i7 += calculatePacketSize(0);
                    i2 = this.segmentCount;
                } else {
                    i2 = 0;
                }
                if (!ExtractorUtil.skipFullyQuietly(extractorInput, i7)) {
                    return false;
                }
                this.currentSegmentIndex = i2;
            }
            int calculatePacketSize = calculatePacketSize(this.currentSegmentIndex);
            int i8 = this.currentSegmentIndex + this.segmentCount;
            if (calculatePacketSize > 0) {
                ParsableByteArray parsableByteArray = this.packetArray;
                parsableByteArray.ensureCapacity(parsableByteArray.limit() + calculatePacketSize);
                if (!ExtractorUtil.readFullyQuietly(extractorInput, this.packetArray.getData(), this.packetArray.limit(), calculatePacketSize)) {
                    return false;
                }
                ParsableByteArray parsableByteArray2 = this.packetArray;
                parsableByteArray2.setLimit(parsableByteArray2.limit() + calculatePacketSize);
                if (this.pageHeader.laces[i8 - 1] != 255) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.populated = z3;
            }
            if (i8 == this.pageHeader.pageSegmentCount) {
                i8 = -1;
            }
            this.currentSegmentIndex = i8;
        }
        return true;
    }

    public void reset() {
        this.pageHeader.reset();
        this.packetArray.reset(0);
        this.currentSegmentIndex = -1;
        this.populated = false;
    }

    public void trimPayload() {
        if (this.packetArray.getData().length != 65025) {
            ParsableByteArray parsableByteArray = this.packetArray;
            parsableByteArray.reset(Arrays.copyOf(parsableByteArray.getData(), Math.max(65025, this.packetArray.limit())), this.packetArray.limit());
        }
    }
}
