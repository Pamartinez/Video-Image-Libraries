package androidx.media3.exoplayer.upstream;

import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlidingPercentile {
    private static final Comparator<Sample> INDEX_COMPARATOR = new b(0);
    private static final Comparator<Sample> VALUE_COMPARATOR = new b(1);
    private int currentSortOrder = -1;
    private final int maxWeight;
    private int nextSampleIndex;
    private int recycledSampleCount;
    private final Sample[] recycledSamples = new Sample[5];
    private final ArrayList<Sample> samples = new ArrayList<>();
    private int totalWeight;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Sample {
        public int index;
        public float value;
        public int weight;

        private Sample() {
        }
    }

    public SlidingPercentile(int i2) {
        this.maxWeight = i2;
    }

    private void ensureSortedByIndex() {
        if (this.currentSortOrder != 1) {
            Collections.sort(this.samples, INDEX_COMPARATOR);
            this.currentSortOrder = 1;
        }
    }

    private void ensureSortedByValue() {
        if (this.currentSortOrder != 0) {
            Collections.sort(this.samples, VALUE_COMPARATOR);
            this.currentSortOrder = 0;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(Sample sample, Sample sample2) {
        return sample.index - sample2.index;
    }

    public void addSample(int i2, float f) {
        Sample sample;
        ensureSortedByIndex();
        int i7 = this.recycledSampleCount;
        if (i7 > 0) {
            Sample[] sampleArr = this.recycledSamples;
            int i8 = i7 - 1;
            this.recycledSampleCount = i8;
            sample = sampleArr[i8];
        } else {
            sample = new Sample();
        }
        int i10 = this.nextSampleIndex;
        this.nextSampleIndex = i10 + 1;
        sample.index = i10;
        sample.weight = i2;
        sample.value = f;
        this.samples.add(sample);
        this.totalWeight += i2;
        while (true) {
            int i11 = this.totalWeight;
            int i12 = this.maxWeight;
            if (i11 > i12) {
                int i13 = i11 - i12;
                Sample sample2 = this.samples.get(0);
                int i14 = sample2.weight;
                if (i14 <= i13) {
                    this.totalWeight -= i14;
                    this.samples.remove(0);
                    int i15 = this.recycledSampleCount;
                    if (i15 < 5) {
                        Sample[] sampleArr2 = this.recycledSamples;
                        this.recycledSampleCount = i15 + 1;
                        sampleArr2[i15] = sample2;
                    }
                } else {
                    sample2.weight = i14 - i13;
                    this.totalWeight -= i13;
                }
            } else {
                return;
            }
        }
    }

    public float getPercentile(float f) {
        ensureSortedByValue();
        float f5 = f * ((float) this.totalWeight);
        int i2 = 0;
        for (int i7 = 0; i7 < this.samples.size(); i7++) {
            Sample sample = this.samples.get(i7);
            i2 += sample.weight;
            if (((float) i2) >= f5) {
                return sample.value;
            }
        }
        if (this.samples.isEmpty()) {
            return Float.NaN;
        }
        return ((Sample) C0212a.i(this.samples, 1)).value;
    }

    public void reset() {
        this.samples.clear();
        this.currentSortOrder = -1;
        this.nextSampleIndex = 0;
        this.totalWeight = 0;
    }
}
