package androidx.media3.extractor.mkv;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.ExtractorInput;
import java.util.ArrayDeque;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class DefaultEbmlReader implements EbmlReader {
    private long elementContentSize;
    private int elementId;
    private int elementState;
    private final ArrayDeque<MasterElement> masterElementsStack = new ArrayDeque<>();
    private EbmlProcessor processor;
    private final byte[] scratch = new byte[8];
    private final VarintReader varintReader = new VarintReader();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MasterElement {
        /* access modifiers changed from: private */
        public final long elementEndPosition;
        /* access modifiers changed from: private */
        public final int elementId;

        private MasterElement(int i2, long j2) {
            this.elementId = i2;
            this.elementEndPosition = j2;
        }
    }

    private long maybeResyncToNextLevel1Element(ExtractorInput extractorInput) {
        extractorInput.resetPeekPosition();
        while (true) {
            extractorInput.peekFully(this.scratch, 0, 4);
            int parseUnsignedVarintLength = VarintReader.parseUnsignedVarintLength(this.scratch[0]);
            if (parseUnsignedVarintLength != -1 && parseUnsignedVarintLength <= 4) {
                int assembleVarint = (int) VarintReader.assembleVarint(this.scratch, parseUnsignedVarintLength, false);
                if (this.processor.isLevel1Element(assembleVarint)) {
                    extractorInput.skipFully(parseUnsignedVarintLength);
                    return (long) assembleVarint;
                }
            }
            extractorInput.skipFully(1);
        }
    }

    private double readFloat(ExtractorInput extractorInput, int i2) {
        long readInteger = readInteger(extractorInput, i2);
        if (i2 == 4) {
            return (double) Float.intBitsToFloat((int) readInteger);
        }
        return Double.longBitsToDouble(readInteger);
    }

    private long readInteger(ExtractorInput extractorInput, int i2) {
        extractorInput.readFully(this.scratch, 0, i2);
        long j2 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            j2 = (j2 << 8) | ((long) (this.scratch[i7] & 255));
        }
        return j2;
    }

    private static String readString(ExtractorInput extractorInput, int i2) {
        if (i2 == 0) {
            return "";
        }
        byte[] bArr = new byte[i2];
        extractorInput.readFully(bArr, 0, i2);
        while (i2 > 0 && bArr[i2 - 1] == 0) {
            i2--;
        }
        return new String(bArr, 0, i2);
    }

    public void init(EbmlProcessor ebmlProcessor) {
        this.processor = ebmlProcessor;
    }

    public boolean read(ExtractorInput extractorInput) {
        Assertions.checkStateNotNull(this.processor);
        while (true) {
            MasterElement peek = this.masterElementsStack.peek();
            if (peek == null || extractorInput.getPosition() < peek.elementEndPosition) {
                if (this.elementState == 0) {
                    long readUnsignedVarint = this.varintReader.readUnsignedVarint(extractorInput, true, false, 4);
                    if (readUnsignedVarint == -2) {
                        readUnsignedVarint = maybeResyncToNextLevel1Element(extractorInput);
                    }
                    if (readUnsignedVarint == -1) {
                        return false;
                    }
                    this.elementId = (int) readUnsignedVarint;
                    this.elementState = 1;
                }
                if (this.elementState == 1) {
                    this.elementContentSize = this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
                    this.elementState = 2;
                }
                int elementType = this.processor.getElementType(this.elementId);
                if (elementType == 0) {
                    extractorInput.skipFully((int) this.elementContentSize);
                    this.elementState = 0;
                } else if (elementType == 1) {
                    long position = extractorInput.getPosition();
                    this.masterElementsStack.push(new MasterElement(this.elementId, this.elementContentSize + position));
                    this.processor.startMasterElement(this.elementId, position, this.elementContentSize);
                    this.elementState = 0;
                    return true;
                } else if (elementType == 2) {
                    long j2 = this.elementContentSize;
                    if (j2 <= 8) {
                        this.processor.integerElement(this.elementId, readInteger(extractorInput, (int) j2));
                        this.elementState = 0;
                        return true;
                    }
                    throw ParserException.createForMalformedContainer("Invalid integer size: " + this.elementContentSize, (Throwable) null);
                } else if (elementType == 3) {
                    long j3 = this.elementContentSize;
                    if (j3 <= 2147483647L) {
                        this.processor.stringElement(this.elementId, readString(extractorInput, (int) j3));
                        this.elementState = 0;
                        return true;
                    }
                    throw ParserException.createForMalformedContainer("String element size: " + this.elementContentSize, (Throwable) null);
                } else if (elementType == 4) {
                    this.processor.binaryElement(this.elementId, (int) this.elementContentSize, extractorInput);
                    this.elementState = 0;
                    return true;
                } else if (elementType == 5) {
                    long j8 = this.elementContentSize;
                    if (j8 == 4 || j8 == 8) {
                        this.processor.floatElement(this.elementId, readFloat(extractorInput, (int) j8));
                        this.elementState = 0;
                        return true;
                    }
                    throw ParserException.createForMalformedContainer("Invalid float size: " + this.elementContentSize, (Throwable) null);
                } else {
                    throw ParserException.createForMalformedContainer("Invalid element type " + elementType, (Throwable) null);
                }
            } else {
                this.processor.endMasterElement(this.masterElementsStack.pop().elementId);
                return true;
            }
        }
    }

    public void reset() {
        this.elementState = 0;
        this.masterElementsStack.clear();
        this.varintReader.reset();
    }
}
