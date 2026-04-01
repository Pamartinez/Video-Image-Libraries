package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SectionReader implements TsPayloadReader {
    private int bytesRead;
    private final SectionPayloadReader reader;
    private final ParsableByteArray sectionData = new ParsableByteArray(32);
    private boolean sectionSyntaxIndicator;
    private int totalSectionLength;
    private boolean waitingForPayloadStart;

    public SectionReader(SectionPayloadReader sectionPayloadReader) {
        this.reader = sectionPayloadReader;
    }

    public void consume(ParsableByteArray parsableByteArray, int i2) {
        boolean z;
        int i7;
        boolean z3;
        if ((i2 & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i7 = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
        } else {
            i7 = -1;
        }
        if (this.waitingForPayloadStart) {
            if (z) {
                this.waitingForPayloadStart = false;
                parsableByteArray.setPosition(i7);
                this.bytesRead = 0;
            } else {
                return;
            }
        }
        while (parsableByteArray.bytesLeft() > 0) {
            int i8 = this.bytesRead;
            if (i8 < 3) {
                if (i8 == 0) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    parsableByteArray.setPosition(parsableByteArray.getPosition() - 1);
                    if (readUnsignedByte == 255) {
                        this.waitingForPayloadStart = true;
                        return;
                    }
                }
                int min = Math.min(parsableByteArray.bytesLeft(), 3 - this.bytesRead);
                parsableByteArray.readBytes(this.sectionData.getData(), this.bytesRead, min);
                int i10 = this.bytesRead + min;
                this.bytesRead = i10;
                if (i10 == 3) {
                    this.sectionData.setPosition(0);
                    this.sectionData.setLimit(3);
                    this.sectionData.skipBytes(1);
                    int readUnsignedByte2 = this.sectionData.readUnsignedByte();
                    int readUnsignedByte3 = this.sectionData.readUnsignedByte();
                    if ((readUnsignedByte2 & 128) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    this.sectionSyntaxIndicator = z3;
                    this.totalSectionLength = (((readUnsignedByte2 & 15) << 8) | readUnsignedByte3) + 3;
                    int capacity = this.sectionData.capacity();
                    int i11 = this.totalSectionLength;
                    if (capacity < i11) {
                        this.sectionData.ensureCapacity(Math.min(4098, Math.max(i11, this.sectionData.capacity() * 2)));
                    }
                }
            } else {
                int min2 = Math.min(parsableByteArray.bytesLeft(), this.totalSectionLength - this.bytesRead);
                parsableByteArray.readBytes(this.sectionData.getData(), this.bytesRead, min2);
                int i12 = this.bytesRead + min2;
                this.bytesRead = i12;
                int i13 = this.totalSectionLength;
                if (i12 != i13) {
                    continue;
                } else {
                    if (!this.sectionSyntaxIndicator) {
                        this.sectionData.setLimit(i13);
                    } else if (Util.crc32(this.sectionData.getData(), 0, this.totalSectionLength, -1) != 0) {
                        this.waitingForPayloadStart = true;
                        return;
                    } else {
                        this.sectionData.setLimit(this.totalSectionLength - 4);
                    }
                    this.sectionData.setPosition(0);
                    this.reader.consume(this.sectionData);
                    this.bytesRead = 0;
                }
            }
        }
    }

    public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.reader.init(timestampAdjuster, extractorOutput, trackIdGenerator);
        this.waitingForPayloadStart = true;
    }

    public void seek() {
        this.waitingForPayloadStart = true;
    }
}
