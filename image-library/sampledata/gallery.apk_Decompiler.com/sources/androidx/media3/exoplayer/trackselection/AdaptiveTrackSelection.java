package androidx.media3.exoplayer.trackselection;

import F2.C0009c;
import F2.C0036q;
import F2.C0040v;
import F2.Q;
import F2.U;
import F2.s0;
import F2.v0;
import F2.y0;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AdaptiveTrackSelection extends BaseTrackSelection {
    private final U adaptationCheckpoints;
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    private long lastBufferEvaluationMs;
    private long latestBitrateEstimate;
    private final long maxDurationForQualityDecreaseUs;
    private final int maxHeightToDiscard;
    private final int maxWidthToDiscard;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private float playbackSpeed;
    private int reason;
    private int selectedIndex;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AdaptationCheckpoint {
        public final long allocatedBandwidth;
        public final long totalBandwidth;

        public AdaptationCheckpoint(long j2, long j3) {
            this.totalBandwidth = j2;
            this.allocatedBandwidth = j3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdaptationCheckpoint)) {
                return false;
            }
            AdaptationCheckpoint adaptationCheckpoint = (AdaptationCheckpoint) obj;
            if (this.totalBandwidth == adaptationCheckpoint.totalBandwidth && this.allocatedBandwidth == adaptationCheckpoint.allocatedBandwidth) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((int) this.totalBandwidth) * 31) + ((int) this.allocatedBandwidth);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Factory implements ExoTrackSelection.Factory {
        private final float bandwidthFraction;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int maxHeightToDiscard;
        private final int maxWidthToDiscard;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;

        public Factory() {
            this(10000, 25000, 25000, 0.7f);
        }

        public AdaptiveTrackSelection createAdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, int i2, BandwidthMeter bandwidthMeter, U u) {
            return new AdaptiveTrackSelection(trackGroup, iArr, i2, bandwidthMeter, (long) this.minDurationForQualityIncreaseMs, (long) this.maxDurationForQualityDecreaseMs, (long) this.minDurationToRetainAfterDiscardMs, this.maxWidthToDiscard, this.maxHeightToDiscard, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, u, this.clock);
        }

        public final ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
            BandwidthMeter bandwidthMeter2;
            Factory factory;
            ExoTrackSelection exoTrackSelection;
            U access$000 = AdaptiveTrackSelection.getAdaptationCheckpoints(definitionArr);
            ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
            int i2 = 0;
            while (i2 < definitionArr.length) {
                ExoTrackSelection.Definition definition = definitionArr[i2];
                if (definition != null) {
                    int[] iArr = definition.tracks;
                    if (iArr.length != 0) {
                        if (iArr.length == 1) {
                            factory = this;
                            exoTrackSelection = new FixedTrackSelection(definition.group, iArr[0], definition.type);
                            bandwidthMeter2 = bandwidthMeter;
                        } else {
                            factory = this;
                            bandwidthMeter2 = bandwidthMeter;
                            exoTrackSelection = factory.createAdaptiveTrackSelection(definition.group, iArr, definition.type, bandwidthMeter2, (U) access$000.get(i2));
                        }
                        exoTrackSelectionArr[i2] = exoTrackSelection;
                        i2++;
                        this = factory;
                        bandwidthMeter = bandwidthMeter2;
                    }
                }
                factory = this;
                bandwidthMeter2 = bandwidthMeter;
                i2++;
                this = factory;
                bandwidthMeter = bandwidthMeter2;
            }
            return exoTrackSelectionArr;
        }

        public Factory(int i2, int i7, int i8, float f) {
            this(i2, i7, i8, 1279, 719, f, 0.75f, Clock.DEFAULT);
        }

        public Factory(int i2, int i7, int i8, int i10, int i11, float f, float f5, Clock clock2) {
            this.minDurationForQualityIncreaseMs = i2;
            this.maxDurationForQualityDecreaseMs = i7;
            this.minDurationToRetainAfterDiscardMs = i8;
            this.maxWidthToDiscard = i10;
            this.maxHeightToDiscard = i11;
            this.bandwidthFraction = f;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f5;
            this.clock = clock2;
        }
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, int i2, BandwidthMeter bandwidthMeter2, long j2, long j3, long j8, int i7, int i8, float f, float f5, List<AdaptationCheckpoint> list, Clock clock2) {
        super(trackGroup, iArr, i2);
        long j10;
        if (j8 < j2) {
            Log.w("AdaptiveTrackSelection", "Adjusting minDurationToRetainAfterDiscardMs to be at least minDurationForQualityIncreaseMs");
            j10 = j2;
        } else {
            j10 = j8;
        }
        this.bandwidthMeter = bandwidthMeter2;
        this.minDurationForQualityIncreaseUs = j2 * 1000;
        this.maxDurationForQualityDecreaseUs = j3 * 1000;
        this.minDurationToRetainAfterDiscardUs = j10 * 1000;
        this.maxWidthToDiscard = i7;
        this.maxHeightToDiscard = i8;
        this.bandwidthFraction = f;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f5;
        this.adaptationCheckpoints = U.y(list);
        this.clock = clock2;
        this.playbackSpeed = 1.0f;
        this.reason = 0;
        this.lastBufferEvaluationMs = -9223372036854775807L;
        this.latestBitrateEstimate = -2147483647L;
    }

    private static void addCheckpoint(List<Q> list, long[] jArr) {
        long j2 = 0;
        for (long j3 : jArr) {
            j2 += j3;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Q q = list.get(i2);
            if (q != null) {
                q.a(new AdaptationCheckpoint(j2, jArr[i2]));
            }
        }
    }

    /* access modifiers changed from: private */
    public static U getAdaptationCheckpoints(ExoTrackSelection.Definition[] definitionArr) {
        y0 y0Var;
        long j2;
        ArrayList arrayList = new ArrayList();
        for (ExoTrackSelection.Definition definition : definitionArr) {
            if (definition == null || definition.tracks.length <= 1) {
                arrayList.add((Object) null);
            } else {
                Q x9 = U.x();
                x9.a(new AdaptationCheckpoint(0, 0));
                arrayList.add(x9);
            }
        }
        long[][] sortedTrackBitrates = getSortedTrackBitrates(definitionArr);
        int[] iArr = new int[sortedTrackBitrates.length];
        long[] jArr = new long[sortedTrackBitrates.length];
        for (int i2 = 0; i2 < sortedTrackBitrates.length; i2++) {
            long[] jArr2 = sortedTrackBitrates[i2];
            if (jArr2.length == 0) {
                j2 = 0;
            } else {
                j2 = jArr2[0];
            }
            jArr[i2] = j2;
        }
        addCheckpoint(arrayList, jArr);
        U switchOrder = getSwitchOrder(sortedTrackBitrates);
        for (int i7 = 0; i7 < switchOrder.size(); i7++) {
            int intValue = ((Integer) switchOrder.get(i7)).intValue();
            int i8 = iArr[intValue] + 1;
            iArr[intValue] = i8;
            jArr[intValue] = sortedTrackBitrates[intValue][i8];
            addCheckpoint(arrayList, jArr);
        }
        for (int i10 = 0; i10 < definitionArr.length; i10++) {
            if (arrayList.get(i10) != null) {
                jArr[i10] = jArr[i10] * 2;
            }
        }
        addCheckpoint(arrayList, jArr);
        Q x10 = U.x();
        for (int i11 = 0; i11 < arrayList.size(); i11++) {
            Q q = (Q) arrayList.get(i11);
            if (q == null) {
                y0Var = y0.f278h;
            } else {
                y0Var = q.f();
            }
            x10.a(y0Var);
        }
        return x10.f();
    }

    private static long[][] getSortedTrackBitrates(ExoTrackSelection.Definition[] definitionArr) {
        long[][] jArr = new long[definitionArr.length][];
        for (int i2 = 0; i2 < definitionArr.length; i2++) {
            ExoTrackSelection.Definition definition = definitionArr[i2];
            if (definition == null) {
                jArr[i2] = new long[0];
            } else {
                jArr[i2] = new long[definition.tracks.length];
                int i7 = 0;
                while (true) {
                    int[] iArr = definition.tracks;
                    if (i7 >= iArr.length) {
                        break;
                    }
                    long j2 = (long) definition.group.getFormat(iArr[i7]).bitrate;
                    long[] jArr2 = jArr[i2];
                    if (j2 == -1) {
                        j2 = 0;
                    }
                    jArr2[i7] = j2;
                    i7++;
                }
                Arrays.sort(jArr[i2]);
            }
        }
        return jArr;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [F2.r, F2.c, F2.t0] */
    private static U getSwitchOrder(long[][] jArr) {
        double d;
        C0040v.c(2, "expectedValuesPerKey");
        TreeMap treeMap = new TreeMap(v0.e);
        s0 s0Var = new s0();
        ? cVar = new C0009c(treeMap);
        cVar.f272j = s0Var;
        for (int i2 = 0; i2 < jArr.length; i2++) {
            long[] jArr2 = jArr[i2];
            if (jArr2.length > 1) {
                int length = jArr2.length;
                double[] dArr = new double[length];
                int i7 = 0;
                while (true) {
                    long[] jArr3 = jArr[i2];
                    int length2 = jArr3.length;
                    double d2 = MapUtil.INVALID_LOCATION;
                    if (i7 >= length2) {
                        break;
                    }
                    long j2 = jArr3[i7];
                    if (j2 != -1) {
                        d2 = Math.log((double) j2);
                    }
                    dArr[i7] = d2;
                    i7++;
                }
                int i8 = length - 1;
                double d3 = dArr[i8] - dArr[0];
                int i10 = 0;
                while (i10 < i8) {
                    double d5 = dArr[i10];
                    i10++;
                    double d6 = (d5 + dArr[i10]) * 0.5d;
                    if (d3 == MapUtil.INVALID_LOCATION) {
                        d = 1.0d;
                    } else {
                        d = (d6 - dArr[0]) / d3;
                    }
                    cVar.put(Double.valueOf(d), Integer.valueOf(i2));
                }
            }
        }
        Collection collection = cVar.f;
        if (collection == null) {
            collection = new C0036q(cVar, 1);
            cVar.f = collection;
        }
        return U.y(collection);
    }

    public void enable() {
        this.lastBufferEvaluationMs = -9223372036854775807L;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public void onPlaybackSpeed(float f) {
        this.playbackSpeed = f;
    }

    public void disable() {
    }
}
