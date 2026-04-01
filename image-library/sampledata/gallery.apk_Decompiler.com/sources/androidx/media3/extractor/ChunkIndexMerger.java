package androidx.media3.extractor;

import He.F;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ChunkIndexMerger {
    private final Map<Long, ChunkIndex> chunkMap = new LinkedHashMap();

    public void add(ChunkIndex chunkIndex) {
        long[] jArr = chunkIndex.timesUs;
        if (jArr.length > 0 && !this.chunkMap.containsKey(Long.valueOf(jArr[0]))) {
            this.chunkMap.put(Long.valueOf(chunkIndex.timesUs[0]), chunkIndex);
        }
    }

    public ChunkIndex merge() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (ChunkIndex next : this.chunkMap.values()) {
            arrayList.add(next.sizes);
            arrayList2.add(next.offsets);
            arrayList3.add(next.durationsUs);
            arrayList4.add(next.timesUs);
        }
        int[][] iArr = (int[][]) arrayList.toArray(new int[arrayList.size()][]);
        long j2 = 0;
        for (int[] length : iArr) {
            j2 += (long) length.length;
        }
        int i2 = (int) j2;
        if (j2 == ((long) i2)) {
            z = true;
        } else {
            z = false;
        }
        F.k(z, "the total number of elements (%s) in the arrays must fit in an int", j2);
        int[] iArr2 = new int[i2];
        int i7 = 0;
        for (int[] iArr3 : iArr) {
            System.arraycopy(iArr3, 0, iArr2, i7, iArr3.length);
            i7 += iArr3.length;
        }
        return new ChunkIndex(iArr2, k.h((long[][]) arrayList2.toArray(new long[arrayList2.size()][])), k.h((long[][]) arrayList3.toArray(new long[arrayList3.size()][])), k.h((long[][]) arrayList4.toArray(new long[arrayList4.size()][])));
    }

    public int size() {
        return this.chunkMap.size();
    }
}
