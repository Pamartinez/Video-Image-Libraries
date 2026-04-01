package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.adobe.internal.xmp.options.PropertyOptions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TrackSampleTable {
    public final long durationUs;
    public final int[] flags;
    public final int maximumSize;
    public final long[] offsets;
    public final int sampleCount;
    public final int[] sizes;
    public final long[] timestampsUs;
    public final Track track;

    public TrackSampleTable(Track track2, long[] jArr, int[] iArr, int i2, long[] jArr2, int[] iArr2, long j2) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        if (iArr.length == jArr2.length) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (jArr.length == jArr2.length) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3);
        Assertions.checkArgument(iArr2.length == jArr2.length ? true : z7);
        this.track = track2;
        this.offsets = jArr;
        this.sizes = iArr;
        this.maximumSize = i2;
        this.timestampsUs = jArr2;
        this.flags = iArr2;
        this.durationUs = j2;
        this.sampleCount = jArr.length;
        if (iArr2.length > 0) {
            int length = iArr2.length - 1;
            iArr2[length] = iArr2[length] | PropertyOptions.DELETE_EXISTING;
        }
    }

    public int getIndexOfEarlierOrEqualSynchronizationSample(long j2) {
        for (int binarySearchFloor = Util.binarySearchFloor(this.timestampsUs, j2, true, false); binarySearchFloor >= 0; binarySearchFloor--) {
            if ((this.flags[binarySearchFloor] & 1) != 0) {
                return binarySearchFloor;
            }
        }
        return -1;
    }

    public int getIndexOfLaterOrEqualSynchronizationSample(long j2) {
        for (int binarySearchCeil = Util.binarySearchCeil(this.timestampsUs, j2, true, false); binarySearchCeil < this.timestampsUs.length; binarySearchCeil++) {
            if ((this.flags[binarySearchCeil] & 1) != 0) {
                return binarySearchCeil;
            }
        }
        return -1;
    }
}
