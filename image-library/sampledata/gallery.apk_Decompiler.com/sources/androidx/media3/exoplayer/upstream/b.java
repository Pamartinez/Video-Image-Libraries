package androidx.media3.exoplayer.upstream;

import androidx.media3.exoplayer.upstream.SlidingPercentile;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        SlidingPercentile.Sample sample = (SlidingPercentile.Sample) obj;
        SlidingPercentile.Sample sample2 = (SlidingPercentile.Sample) obj2;
        switch (this.d) {
            case 0:
                return SlidingPercentile.lambda$static$0(sample, sample2);
            default:
                return Float.compare(sample.value, sample2.value);
        }
    }
}
