package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.upstream.Allocator;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultAllocator implements Allocator {
    private int allocatedCount;
    private Allocation[] availableAllocations;
    private int availableCount;
    private final int individualAllocationSize;
    private final byte[] initialAllocationBlock;
    private int targetBufferSize;
    private final boolean trimOnReset;

    public DefaultAllocator(boolean z, int i2) {
        this(z, i2, 0);
    }

    public synchronized Allocation allocate() {
        Allocation allocation;
        try {
            this.allocatedCount++;
            int i2 = this.availableCount;
            if (i2 > 0) {
                Allocation[] allocationArr = this.availableAllocations;
                int i7 = i2 - 1;
                this.availableCount = i7;
                allocation = (Allocation) Assertions.checkNotNull(allocationArr[i7]);
                this.availableAllocations[this.availableCount] = null;
            } else {
                allocation = new Allocation(new byte[this.individualAllocationSize], 0);
                int i8 = this.allocatedCount;
                Allocation[] allocationArr2 = this.availableAllocations;
                if (i8 > allocationArr2.length) {
                    this.availableAllocations = (Allocation[]) Arrays.copyOf(allocationArr2, allocationArr2.length * 2);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return allocation;
    }

    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }

    public synchronized int getTotalBytesAllocated() {
        return this.allocatedCount * this.individualAllocationSize;
    }

    public synchronized void release(Allocation allocation) {
        Allocation[] allocationArr = this.availableAllocations;
        int i2 = this.availableCount;
        this.availableCount = i2 + 1;
        allocationArr[i2] = allocation;
        this.allocatedCount--;
        notifyAll();
    }

    public synchronized void reset() {
        if (this.trimOnReset) {
            setTargetBufferSize(0);
        }
    }

    public synchronized void setTargetBufferSize(int i2) {
        boolean z;
        if (i2 < this.targetBufferSize) {
            z = true;
        } else {
            z = false;
        }
        this.targetBufferSize = i2;
        if (z) {
            trim();
        }
    }

    public synchronized void trim() {
        try {
            int i2 = 0;
            int max = Math.max(0, Util.ceilDivide(this.targetBufferSize, this.individualAllocationSize) - this.allocatedCount);
            int i7 = this.availableCount;
            if (max < i7) {
                if (this.initialAllocationBlock != null) {
                    int i8 = i7 - 1;
                    while (i2 <= i8) {
                        Allocation allocation = (Allocation) Assertions.checkNotNull(this.availableAllocations[i2]);
                        if (allocation.data == this.initialAllocationBlock) {
                            i2++;
                        } else {
                            Allocation allocation2 = (Allocation) Assertions.checkNotNull(this.availableAllocations[i8]);
                            if (allocation2.data != this.initialAllocationBlock) {
                                i8--;
                            } else {
                                Allocation[] allocationArr = this.availableAllocations;
                                allocationArr[i2] = allocation2;
                                allocationArr[i8] = allocation;
                                i8--;
                                i2++;
                            }
                        }
                    }
                    max = Math.max(max, i2);
                    if (max >= this.availableCount) {
                        return;
                    }
                }
                Arrays.fill(this.availableAllocations, max, this.availableCount, (Object) null);
                this.availableCount = max;
            }
        } finally {
            while (true) {
            }
        }
    }

    public DefaultAllocator(boolean z, int i2, int i7) {
        boolean z3 = true;
        Assertions.checkArgument(i2 > 0);
        Assertions.checkArgument(i7 < 0 ? false : z3);
        this.trimOnReset = z;
        this.individualAllocationSize = i2;
        this.availableCount = i7;
        this.availableAllocations = new Allocation[(i7 + 100)];
        if (i7 > 0) {
            this.initialAllocationBlock = new byte[(i7 * i2)];
            for (int i8 = 0; i8 < i7; i8++) {
                this.availableAllocations[i8] = new Allocation(this.initialAllocationBlock, i8 * i2);
            }
            return;
        }
        this.initialAllocationBlock = null;
    }

    public synchronized void release(Allocator.AllocationNode allocationNode) {
        while (allocationNode != null) {
            try {
                Allocation[] allocationArr = this.availableAllocations;
                int i2 = this.availableCount;
                this.availableCount = i2 + 1;
                allocationArr[i2] = allocationNode.getAllocation();
                this.allocatedCount--;
                allocationNode = allocationNode.next();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        notifyAll();
    }
}
