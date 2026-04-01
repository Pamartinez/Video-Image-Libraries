package androidx.media3.extractor.ogg;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorUtil;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class OggPageHeader {
    public int bodySize;
    public long granulePosition;
    public int headerSize;
    public final int[] laces = new int[ScoverState.TYPE_NFC_SMART_COVER];
    public long pageChecksum;
    public int pageSegmentCount;
    public long pageSequenceNumber;
    public int revision;
    private final ParsableByteArray scratch = new ParsableByteArray((int) ScoverState.TYPE_NFC_SMART_COVER);
    public long streamSerialNumber;
    public int type;

    public boolean populate(ExtractorInput extractorInput, boolean z) {
        reset();
        this.scratch.reset(27);
        if (!ExtractorUtil.peekFullyQuietly(extractorInput, this.scratch.getData(), 0, 27, z) || this.scratch.readUnsignedInt() != 1332176723) {
            return false;
        }
        int readUnsignedByte = this.scratch.readUnsignedByte();
        this.revision = readUnsignedByte;
        if (readUnsignedByte == 0) {
            this.type = this.scratch.readUnsignedByte();
            this.granulePosition = this.scratch.readLittleEndianLong();
            this.streamSerialNumber = this.scratch.readLittleEndianUnsignedInt();
            this.pageSequenceNumber = this.scratch.readLittleEndianUnsignedInt();
            this.pageChecksum = this.scratch.readLittleEndianUnsignedInt();
            int readUnsignedByte2 = this.scratch.readUnsignedByte();
            this.pageSegmentCount = readUnsignedByte2;
            this.headerSize = readUnsignedByte2 + 27;
            this.scratch.reset(readUnsignedByte2);
            if (!ExtractorUtil.peekFullyQuietly(extractorInput, this.scratch.getData(), 0, this.pageSegmentCount, z)) {
                return false;
            }
            for (int i2 = 0; i2 < this.pageSegmentCount; i2++) {
                this.laces[i2] = this.scratch.readUnsignedByte();
                this.bodySize += this.laces[i2];
            }
            return true;
        } else if (z) {
            return false;
        } else {
            throw ParserException.createForUnsupportedContainerFeature("unsupported bit stream revision");
        }
    }

    public void reset() {
        this.revision = 0;
        this.type = 0;
        this.granulePosition = 0;
        this.streamSerialNumber = 0;
        this.pageSequenceNumber = 0;
        this.pageChecksum = 0;
        this.pageSegmentCount = 0;
        this.headerSize = 0;
        this.bodySize = 0;
    }

    public boolean skipToNextPage(ExtractorInput extractorInput) {
        return skipToNextPage(extractorInput, -1);
    }

    public boolean skipToNextPage(ExtractorInput extractorInput, long j2) {
        int i2;
        Assertions.checkArgument(extractorInput.getPosition() == extractorInput.getPeekPosition());
        this.scratch.reset(4);
        while (true) {
            i2 = (j2 > -1 ? 1 : (j2 == -1 ? 0 : -1));
            if ((i2 == 0 || extractorInput.getPosition() + 4 < j2) && ExtractorUtil.peekFullyQuietly(extractorInput, this.scratch.getData(), 0, 4, true)) {
                this.scratch.setPosition(0);
                if (this.scratch.readUnsignedInt() == 1332176723) {
                    extractorInput.resetPeekPosition();
                    return true;
                }
                extractorInput.skipFully(1);
            }
        }
        do {
            if ((i2 != 0 && extractorInput.getPosition() >= j2) || extractorInput.skip(1) == -1) {
                return false;
            }
            break;
        } while (extractorInput.skip(1) == -1);
        return false;
    }
}
