package androidx.media3.extractor.ts;

import A.a;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PesReader implements TsPayloadReader {
    private int bytesRead;
    private boolean dataAlignmentIndicator;
    private boolean dtsFlag;
    private int extendedHeaderLength;
    private int payloadSize;
    private final ParsableBitArray pesScratch = new ParsableBitArray(new byte[10]);
    private boolean ptsFlag;
    private final ElementaryStreamReader reader;
    private boolean seenFirstDts;
    private int state = 0;
    private long timeUs;
    private TimestampAdjuster timestampAdjuster;

    public PesReader(ElementaryStreamReader elementaryStreamReader) {
        this.reader = elementaryStreamReader;
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i2) {
        int min = Math.min(parsableByteArray.bytesLeft(), i2 - this.bytesRead);
        if (min <= 0) {
            return true;
        }
        if (bArr == null) {
            parsableByteArray.skipBytes(min);
        } else {
            parsableByteArray.readBytes(bArr, this.bytesRead, min);
        }
        int i7 = this.bytesRead + min;
        this.bytesRead = i7;
        if (i7 == i2) {
            return true;
        }
        return false;
    }

    private boolean parseHeader() {
        this.pesScratch.setPosition(0);
        int readBits = this.pesScratch.readBits(24);
        if (readBits != 1) {
            a.D(readBits, "Unexpected start code prefix: ", "PesReader");
            this.payloadSize = -1;
            return false;
        }
        this.pesScratch.skipBits(8);
        int readBits2 = this.pesScratch.readBits(16);
        this.pesScratch.skipBits(5);
        this.dataAlignmentIndicator = this.pesScratch.readBit();
        this.pesScratch.skipBits(2);
        this.ptsFlag = this.pesScratch.readBit();
        this.dtsFlag = this.pesScratch.readBit();
        this.pesScratch.skipBits(6);
        int readBits3 = this.pesScratch.readBits(8);
        this.extendedHeaderLength = readBits3;
        if (readBits2 == 0) {
            this.payloadSize = -1;
        } else {
            int i2 = (readBits2 - 3) - readBits3;
            this.payloadSize = i2;
            if (i2 < 0) {
                Log.w("PesReader", "Found negative packet payload size: " + this.payloadSize);
                this.payloadSize = -1;
            }
        }
        return true;
    }

    private void parseHeaderExtension() {
        this.pesScratch.setPosition(0);
        this.timeUs = -9223372036854775807L;
        if (this.ptsFlag) {
            this.pesScratch.skipBits(4);
            this.pesScratch.skipBits(1);
            this.pesScratch.skipBits(1);
            long readBits = (((long) this.pesScratch.readBits(3)) << 30) | ((long) (this.pesScratch.readBits(15) << 15)) | ((long) this.pesScratch.readBits(15));
            this.pesScratch.skipBits(1);
            if (!this.seenFirstDts && this.dtsFlag) {
                this.pesScratch.skipBits(4);
                this.pesScratch.skipBits(1);
                this.pesScratch.skipBits(1);
                this.pesScratch.skipBits(1);
                this.timestampAdjuster.adjustTsTimestamp((((long) this.pesScratch.readBits(3)) << 30) | ((long) (this.pesScratch.readBits(15) << 15)) | ((long) this.pesScratch.readBits(15)));
                this.seenFirstDts = true;
            }
            this.timeUs = this.timestampAdjuster.adjustTsTimestamp(readBits);
        }
    }

    private void setState(int i2) {
        this.state = i2;
        this.bytesRead = 0;
    }

    public boolean canConsumeSynthesizedEmptyPusi(boolean z) {
        boolean z3;
        if (!z || parseHeader()) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (this.state == 3 && this.payloadSize == -1 && ((!z || !(this.reader instanceof H262Reader)) && z3)) {
            return true;
        }
        return false;
    }

    public void consume(ParsableByteArray parsableByteArray, int i2) {
        int i7;
        int i8;
        int i10;
        boolean z;
        Assertions.checkStateNotNull(this.timestampAdjuster);
        if ((i2 & 1) != 0) {
            int i11 = this.state;
            if (!(i11 == 0 || i11 == 1)) {
                if (i11 == 2) {
                    Log.w("PesReader", "Unexpected start indicator reading extended header");
                } else if (i11 == 3) {
                    if (this.payloadSize != -1) {
                        Log.w("PesReader", "Unexpected start indicator: expected " + this.payloadSize + " more bytes");
                    }
                    if (parsableByteArray.limit() == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.reader.packetFinished(z);
                } else {
                    throw new IllegalStateException();
                }
            }
            setState(1);
        }
        while (parsableByteArray.bytesLeft() > 0) {
            int i12 = this.state;
            if (i12 == 0) {
                parsableByteArray.skipBytes(parsableByteArray.bytesLeft());
            } else if (i12 != 1) {
                if (i12 == 2) {
                    if (continueRead(parsableByteArray, this.pesScratch.data, Math.min(10, this.extendedHeaderLength)) && continueRead(parsableByteArray, (byte[]) null, this.extendedHeaderLength)) {
                        parseHeaderExtension();
                        if (this.dataAlignmentIndicator) {
                            i8 = 4;
                        } else {
                            i8 = 0;
                        }
                        i2 |= i8;
                        this.reader.packetStarted(this.timeUs, i2);
                        setState(3);
                    }
                } else if (i12 == 3) {
                    int bytesLeft = parsableByteArray.bytesLeft();
                    int i13 = this.payloadSize;
                    if (i13 == -1) {
                        i10 = 0;
                    } else {
                        i10 = bytesLeft - i13;
                    }
                    if (i10 > 0) {
                        bytesLeft -= i10;
                        parsableByteArray.setLimit(parsableByteArray.getPosition() + bytesLeft);
                    }
                    this.reader.consume(parsableByteArray);
                    int i14 = this.payloadSize;
                    if (i14 != -1) {
                        int i15 = i14 - bytesLeft;
                        this.payloadSize = i15;
                        if (i15 == 0) {
                            this.reader.packetFinished(false);
                            setState(1);
                        }
                    }
                } else {
                    throw new IllegalStateException();
                }
            } else if (continueRead(parsableByteArray, this.pesScratch.data, 9)) {
                if (parseHeader()) {
                    i7 = 2;
                } else {
                    i7 = 0;
                }
                setState(i7);
            }
        }
    }

    public void init(TimestampAdjuster timestampAdjuster2, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.timestampAdjuster = timestampAdjuster2;
        this.reader.createTracks(extractorOutput, trackIdGenerator);
    }

    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.seenFirstDts = false;
        this.reader.seek();
    }
}
