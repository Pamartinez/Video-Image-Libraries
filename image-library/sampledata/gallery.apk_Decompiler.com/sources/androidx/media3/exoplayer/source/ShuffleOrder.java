package androidx.media3.exoplayer.source;

import java.util.Arrays;
import java.util.Random;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ShuffleOrder {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultShuffleOrder implements ShuffleOrder {
        private final int[] indexInShuffled;
        private final Random random;
        private final int[] shuffled;

        public DefaultShuffleOrder(int i2) {
            this(i2, new Random());
        }

        private static int[] createShuffledList(int i2, Random random2) {
            int[] iArr = new int[i2];
            int i7 = 0;
            while (i7 < i2) {
                int i8 = i7 + 1;
                int nextInt = random2.nextInt(i8);
                iArr[i7] = iArr[nextInt];
                iArr[nextInt] = i7;
                i7 = i8;
            }
            return iArr;
        }

        public ShuffleOrder cloneAndClear() {
            return new DefaultShuffleOrder(0, new Random(this.random.nextLong()));
        }

        public ShuffleOrder cloneAndInsert(int i2, int i7) {
            int[] iArr = new int[i7];
            int[] iArr2 = new int[i7];
            int i8 = 0;
            int i10 = 0;
            while (i10 < i7) {
                iArr[i10] = this.random.nextInt(this.shuffled.length + 1);
                int i11 = i10 + 1;
                int nextInt = this.random.nextInt(i11);
                iArr2[i10] = iArr2[nextInt];
                iArr2[nextInt] = i10 + i2;
                i10 = i11;
            }
            Arrays.sort(iArr);
            int[] iArr3 = new int[(this.shuffled.length + i7)];
            int i12 = 0;
            int i13 = 0;
            while (true) {
                int[] iArr4 = this.shuffled;
                if (i8 >= iArr4.length + i7) {
                    return new DefaultShuffleOrder(iArr3, new Random(this.random.nextLong()));
                }
                if (i12 >= i7 || i13 != iArr[i12]) {
                    int i14 = i13 + 1;
                    int i15 = iArr4[i13];
                    iArr3[i8] = i15;
                    if (i15 >= i2) {
                        iArr3[i8] = i15 + i7;
                    }
                    i13 = i14;
                } else {
                    iArr3[i8] = iArr2[i12];
                    i12++;
                }
                i8++;
            }
        }

        public ShuffleOrder cloneAndRemove(int i2, int i7) {
            int i8 = i7 - i2;
            int[] iArr = new int[(this.shuffled.length - i8)];
            int i10 = 0;
            int i11 = 0;
            while (true) {
                int[] iArr2 = this.shuffled;
                if (i10 >= iArr2.length) {
                    return new DefaultShuffleOrder(iArr, new Random(this.random.nextLong()));
                }
                int i12 = iArr2[i10];
                if (i12 < i2 || i12 >= i7) {
                    int i13 = i10 - i11;
                    if (i12 >= i2) {
                        i12 -= i8;
                    }
                    iArr[i13] = i12;
                } else {
                    i11++;
                }
                i10++;
            }
        }

        public int getFirstIndex() {
            int[] iArr = this.shuffled;
            if (iArr.length > 0) {
                return iArr[0];
            }
            return -1;
        }

        public int getLastIndex() {
            int[] iArr = this.shuffled;
            if (iArr.length > 0) {
                return iArr[iArr.length - 1];
            }
            return -1;
        }

        public int getLength() {
            return this.shuffled.length;
        }

        public int getNextIndex(int i2) {
            int i7 = this.indexInShuffled[i2] + 1;
            int[] iArr = this.shuffled;
            if (i7 < iArr.length) {
                return iArr[i7];
            }
            return -1;
        }

        public int getPreviousIndex(int i2) {
            int i7 = this.indexInShuffled[i2] - 1;
            if (i7 >= 0) {
                return this.shuffled[i7];
            }
            return -1;
        }

        private DefaultShuffleOrder(int i2, Random random2) {
            this(createShuffledList(i2, random2), random2);
        }

        private DefaultShuffleOrder(int[] iArr, Random random2) {
            this.shuffled = iArr;
            this.random = random2;
            this.indexInShuffled = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                this.indexInShuffled[iArr[i2]] = i2;
            }
        }
    }

    ShuffleOrder cloneAndClear();

    ShuffleOrder cloneAndInsert(int i2, int i7);

    ShuffleOrder cloneAndRemove(int i2, int i7);

    int getFirstIndex();

    int getLastIndex();

    int getLength();

    int getNextIndex(int i2);

    int getPreviousIndex(int i2);
}
