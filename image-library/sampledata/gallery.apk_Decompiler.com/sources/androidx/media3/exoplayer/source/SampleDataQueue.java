package androidx.media3.exoplayer.source;

import androidx.media3.common.DataReader;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.upstream.Allocation;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.extractor.TrackOutput;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SampleDataQueue {
    private final int allocationLength;
    private final Allocator allocator;
    private AllocationNode firstAllocationNode;
    private AllocationNode readAllocationNode;
    private final ParsableByteArray scratch = new ParsableByteArray(32);
    private long totalBytesWritten;
    private AllocationNode writeAllocationNode;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AllocationNode implements Allocator.AllocationNode {
        public Allocation allocation;
        public long endPosition;
        public AllocationNode next;
        public long startPosition;

        public AllocationNode(long j2, int i2) {
            reset(j2, i2);
        }

        public AllocationNode clear() {
            this.allocation = null;
            AllocationNode allocationNode = this.next;
            this.next = null;
            return allocationNode;
        }

        public Allocation getAllocation() {
            return (Allocation) Assertions.checkNotNull(this.allocation);
        }

        public void initialize(Allocation allocation2, AllocationNode allocationNode) {
            this.allocation = allocation2;
            this.next = allocationNode;
        }

        public Allocator.AllocationNode next() {
            AllocationNode allocationNode = this.next;
            if (allocationNode == null || allocationNode.allocation == null) {
                return null;
            }
            return allocationNode;
        }

        public void reset(long j2, int i2) {
            boolean z;
            if (this.allocation == null) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            this.startPosition = j2;
            this.endPosition = j2 + ((long) i2);
        }

        public int translateOffset(long j2) {
            return ((int) (j2 - this.startPosition)) + this.allocation.offset;
        }
    }

    public SampleDataQueue(Allocator allocator2) {
        this.allocator = allocator2;
        int individualAllocationLength = allocator2.getIndividualAllocationLength();
        this.allocationLength = individualAllocationLength;
        AllocationNode allocationNode = new AllocationNode(0, individualAllocationLength);
        this.firstAllocationNode = allocationNode;
        this.readAllocationNode = allocationNode;
        this.writeAllocationNode = allocationNode;
    }

    private void clearAllocationNodes(AllocationNode allocationNode) {
        if (allocationNode.allocation != null) {
            this.allocator.release((Allocator.AllocationNode) allocationNode);
            allocationNode.clear();
        }
    }

    private static AllocationNode getNodeContainingPosition(AllocationNode allocationNode, long j2) {
        while (j2 >= allocationNode.endPosition) {
            allocationNode = allocationNode.next;
        }
        return allocationNode;
    }

    private void postAppend(int i2) {
        long j2 = this.totalBytesWritten + ((long) i2);
        this.totalBytesWritten = j2;
        AllocationNode allocationNode = this.writeAllocationNode;
        if (j2 == allocationNode.endPosition) {
            this.writeAllocationNode = allocationNode.next;
        }
    }

    private int preAppend(int i2) {
        AllocationNode allocationNode = this.writeAllocationNode;
        if (allocationNode.allocation == null) {
            allocationNode.initialize(this.allocator.allocate(), new AllocationNode(this.writeAllocationNode.endPosition, this.allocationLength));
        }
        return Math.min(i2, (int) (this.writeAllocationNode.endPosition - this.totalBytesWritten));
    }

    private static AllocationNode readData(AllocationNode allocationNode, long j2, ByteBuffer byteBuffer, int i2) {
        AllocationNode nodeContainingPosition = getNodeContainingPosition(allocationNode, j2);
        while (i2 > 0) {
            int min = Math.min(i2, (int) (nodeContainingPosition.endPosition - j2));
            byteBuffer.put(nodeContainingPosition.allocation.data, nodeContainingPosition.translateOffset(j2), min);
            i2 -= min;
            j2 += (long) min;
            if (j2 == nodeContainingPosition.endPosition) {
                nodeContainingPosition = nodeContainingPosition.next;
            }
        }
        return nodeContainingPosition;
    }

    private static AllocationNode readEncryptionData(AllocationNode allocationNode, DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder, ParsableByteArray parsableByteArray) {
        boolean z;
        SampleQueue.SampleExtrasHolder sampleExtrasHolder2 = sampleExtrasHolder;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        long j2 = sampleExtrasHolder2.offset;
        int i2 = 1;
        parsableByteArray2.reset(1);
        AllocationNode readData = readData(allocationNode, j2, parsableByteArray2.getData(), 1);
        long j3 = j2 + 1;
        byte b = parsableByteArray2.getData()[0];
        if ((b & 128) != 0) {
            z = true;
        } else {
            z = false;
        }
        byte b5 = b & Byte.MAX_VALUE;
        CryptoInfo cryptoInfo = decoderInputBuffer.cryptoInfo;
        byte[] bArr = cryptoInfo.iv;
        if (bArr == null) {
            cryptoInfo.iv = new byte[16];
        } else {
            Arrays.fill(bArr, (byte) 0);
        }
        AllocationNode readData2 = readData(readData, j3, cryptoInfo.iv, (int) b5);
        long j8 = j3 + ((long) b5);
        if (z) {
            parsableByteArray2.reset(2);
            readData2 = readData(readData2, j8, parsableByteArray2.getData(), 2);
            j8 += 2;
            i2 = parsableByteArray2.readUnsignedShort();
        }
        int i7 = i2;
        int[] iArr = cryptoInfo.numBytesOfClearData;
        if (iArr == null || iArr.length < i7) {
            iArr = new int[i7];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = cryptoInfo.numBytesOfEncryptedData;
        if (iArr3 == null || iArr3.length < i7) {
            iArr3 = new int[i7];
        }
        int[] iArr4 = iArr3;
        if (z) {
            int i8 = i7 * 6;
            parsableByteArray2.reset(i8);
            readData2 = readData(readData2, j8, parsableByteArray2.getData(), i8);
            j8 += (long) i8;
            parsableByteArray2.setPosition(0);
            for (int i10 = 0; i10 < i7; i10++) {
                iArr2[i10] = parsableByteArray2.readUnsignedShort();
                iArr4[i10] = parsableByteArray2.readUnsignedIntToInt();
            }
        } else {
            iArr2[0] = 0;
            iArr4[0] = sampleExtrasHolder2.size - ((int) (j8 - sampleExtrasHolder2.offset));
        }
        TrackOutput.CryptoData cryptoData = (TrackOutput.CryptoData) Util.castNonNull(sampleExtrasHolder2.cryptoData);
        cryptoInfo.set(i7, iArr2, iArr4, cryptoData.encryptionKey, cryptoInfo.iv, cryptoData.cryptoMode, cryptoData.encryptedBlocks, cryptoData.clearBlocks);
        long j10 = sampleExtrasHolder2.offset;
        int i11 = (int) (j8 - j10);
        sampleExtrasHolder2.offset = j10 + ((long) i11);
        sampleExtrasHolder2.size -= i11;
        return readData2;
    }

    private static AllocationNode readSampleData(AllocationNode allocationNode, DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder, ParsableByteArray parsableByteArray) {
        if (decoderInputBuffer.isEncrypted()) {
            allocationNode = readEncryptionData(allocationNode, decoderInputBuffer, sampleExtrasHolder, parsableByteArray);
        }
        if (decoderInputBuffer.hasSupplementalData()) {
            parsableByteArray.reset(4);
            AllocationNode readData = readData(allocationNode, sampleExtrasHolder.offset, parsableByteArray.getData(), 4);
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            sampleExtrasHolder.offset += 4;
            sampleExtrasHolder.size -= 4;
            decoderInputBuffer.ensureSpaceForWrite(readUnsignedIntToInt);
            AllocationNode readData2 = readData(readData, sampleExtrasHolder.offset, decoderInputBuffer.data, readUnsignedIntToInt);
            sampleExtrasHolder.offset += (long) readUnsignedIntToInt;
            int i2 = sampleExtrasHolder.size - readUnsignedIntToInt;
            sampleExtrasHolder.size = i2;
            decoderInputBuffer.resetSupplementalData(i2);
            return readData(readData2, sampleExtrasHolder.offset, decoderInputBuffer.supplementalData, sampleExtrasHolder.size);
        }
        decoderInputBuffer.ensureSpaceForWrite(sampleExtrasHolder.size);
        return readData(allocationNode, sampleExtrasHolder.offset, decoderInputBuffer.data, sampleExtrasHolder.size);
    }

    public void discardDownstreamTo(long j2) {
        AllocationNode allocationNode;
        if (j2 != -1) {
            while (true) {
                allocationNode = this.firstAllocationNode;
                if (j2 < allocationNode.endPosition) {
                    break;
                }
                this.allocator.release(allocationNode.allocation);
                this.firstAllocationNode = this.firstAllocationNode.clear();
            }
            if (this.readAllocationNode.startPosition < allocationNode.startPosition) {
                this.readAllocationNode = allocationNode;
            }
        }
    }

    public long getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void peekToBuffer(DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder) {
        readSampleData(this.readAllocationNode, decoderInputBuffer, sampleExtrasHolder, this.scratch);
    }

    public void readToBuffer(DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder) {
        this.readAllocationNode = readSampleData(this.readAllocationNode, decoderInputBuffer, sampleExtrasHolder, this.scratch);
    }

    public void reset() {
        clearAllocationNodes(this.firstAllocationNode);
        this.firstAllocationNode.reset(0, this.allocationLength);
        AllocationNode allocationNode = this.firstAllocationNode;
        this.readAllocationNode = allocationNode;
        this.writeAllocationNode = allocationNode;
        this.totalBytesWritten = 0;
        this.allocator.trim();
    }

    public void rewind() {
        this.readAllocationNode = this.firstAllocationNode;
    }

    public int sampleData(DataReader dataReader, int i2, boolean z) {
        int preAppend = preAppend(i2);
        AllocationNode allocationNode = this.writeAllocationNode;
        int read = dataReader.read(allocationNode.allocation.data, allocationNode.translateOffset(this.totalBytesWritten), preAppend);
        if (read != -1) {
            postAppend(read);
            return read;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    private static AllocationNode readData(AllocationNode allocationNode, long j2, byte[] bArr, int i2) {
        AllocationNode nodeContainingPosition = getNodeContainingPosition(allocationNode, j2);
        int i7 = i2;
        while (i7 > 0) {
            int min = Math.min(i7, (int) (nodeContainingPosition.endPosition - j2));
            System.arraycopy(nodeContainingPosition.allocation.data, nodeContainingPosition.translateOffset(j2), bArr, i2 - i7, min);
            i7 -= min;
            j2 += (long) min;
            if (j2 == nodeContainingPosition.endPosition) {
                nodeContainingPosition = nodeContainingPosition.next;
            }
        }
        return nodeContainingPosition;
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i2) {
        while (i2 > 0) {
            int preAppend = preAppend(i2);
            AllocationNode allocationNode = this.writeAllocationNode;
            parsableByteArray.readBytes(allocationNode.allocation.data, allocationNode.translateOffset(this.totalBytesWritten), preAppend);
            i2 -= preAppend;
            postAppend(preAppend);
        }
    }
}
