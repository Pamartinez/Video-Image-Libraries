package androidx.media3.exoplayer.source;

import F2.A;
import F2.C0009c;
import F2.C0040v;
import F2.r0;
import F2.s0;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MergingMediaSource extends CompositeMediaSource<Integer> {
    private static final MediaItem PLACEHOLDER_MEDIA_ITEM = new MediaItem.Builder().setMediaId("MergingMediaSource").build();
    private final boolean adjustPeriodTimeOffsets;
    private final boolean clipDurations;
    private final Map<Object, Long> clippedDurationsUs;
    private final r0 clippedMediaPeriods;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final List<List<MediaPeriodAndId>> mediaPeriods;
    private final MediaSource[] mediaSources;
    private IllegalMergeException mergeError;
    private final ArrayList<MediaSource> pendingTimelineSources;
    private int periodCount;
    private long[][] periodTimeOffsetsUs;
    private final Timeline[] timelines;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ClippedTimeline extends ForwardingTimeline {
        private final long[] periodDurationsUs;
        private final long[] windowDurationsUs;

        public ClippedTimeline(Timeline timeline, Map<Object, Long> map) {
            super(timeline);
            int windowCount = timeline.getWindowCount();
            this.windowDurationsUs = new long[timeline.getWindowCount()];
            Timeline.Window window = new Timeline.Window();
            for (int i2 = 0; i2 < windowCount; i2++) {
                this.windowDurationsUs[i2] = timeline.getWindow(i2, window).durationUs;
            }
            int periodCount = timeline.getPeriodCount();
            this.periodDurationsUs = new long[periodCount];
            Timeline.Period period = new Timeline.Period();
            for (int i7 = 0; i7 < periodCount; i7++) {
                timeline.getPeriod(i7, period, true);
                long longValue = ((Long) Assertions.checkNotNull(map.get(period.uid))).longValue();
                long[] jArr = this.periodDurationsUs;
                longValue = longValue == Long.MIN_VALUE ? period.durationUs : longValue;
                jArr[i7] = longValue;
                long j2 = period.durationUs;
                if (j2 != -9223372036854775807L) {
                    long[] jArr2 = this.windowDurationsUs;
                    int i8 = period.windowIndex;
                    jArr2[i8] = jArr2[i8] - (j2 - longValue);
                }
            }
        }

        public Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
            super.getPeriod(i2, period, z);
            period.durationUs = this.periodDurationsUs[i2];
            return period;
        }

        public Timeline.Window getWindow(int i2, Timeline.Window window, long j2) {
            long j3;
            super.getWindow(i2, window, j2);
            long j8 = this.windowDurationsUs[i2];
            window.durationUs = j8;
            if (j8 != -9223372036854775807L) {
                long j10 = window.defaultPositionUs;
                if (j10 != -9223372036854775807L) {
                    j3 = Math.min(j10, j8);
                    window.defaultPositionUs = j3;
                    return window;
                }
            }
            j3 = window.defaultPositionUs;
            window.defaultPositionUs = j3;
            return window;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IllegalMergeException extends IOException {
        public final int reason;

        public IllegalMergeException(int i2) {
            this.reason = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaPeriodAndId {
        /* access modifiers changed from: private */
        public final MediaPeriod mediaPeriod;
        /* access modifiers changed from: private */
        public final MediaSource.MediaPeriodId mediaPeriodId;

        private MediaPeriodAndId(MediaSource.MediaPeriodId mediaPeriodId2, MediaPeriod mediaPeriod2) {
            this.mediaPeriodId = mediaPeriodId2;
            this.mediaPeriod = mediaPeriod2;
        }
    }

    public MergingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    private void computePeriodTimeOffsets() {
        Timeline.Period period = new Timeline.Period();
        for (int i2 = 0; i2 < this.periodCount; i2++) {
            long j2 = -this.timelines[0].getPeriod(i2, period).getPositionInWindowUs();
            int i7 = 1;
            while (true) {
                Timeline[] timelineArr = this.timelines;
                if (i7 >= timelineArr.length) {
                    break;
                }
                this.periodTimeOffsetsUs[i2][i7] = j2 - (-timelineArr[i7].getPeriod(i2, period).getPositionInWindowUs());
                i7++;
            }
        }
    }

    private void updateClippedDuration() {
        Timeline[] timelineArr;
        Timeline.Period period = new Timeline.Period();
        for (int i2 = 0; i2 < this.periodCount; i2++) {
            int i7 = 0;
            long j2 = Long.MIN_VALUE;
            while (true) {
                timelineArr = this.timelines;
                if (i7 >= timelineArr.length) {
                    break;
                }
                long durationUs = timelineArr[i7].getPeriod(i2, period).getDurationUs();
                if (durationUs != -9223372036854775807L) {
                    long j3 = durationUs + this.periodTimeOffsetsUs[i2][i7];
                    if (j2 == Long.MIN_VALUE || j3 < j2) {
                        j2 = j3;
                    }
                }
                i7++;
            }
            Object uidOfPeriod = timelineArr[0].getUidOfPeriod(i2);
            this.clippedDurationsUs.put(uidOfPeriod, Long.valueOf(j2));
            for (ClippingMediaPeriod updateClipping : this.clippedMediaPeriods.get(uidOfPeriod)) {
                updateClipping.updateClipping(0, j2);
            }
        }
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        int length = this.mediaSources.length;
        MediaPeriod[] mediaPeriodArr = new MediaPeriod[length];
        int indexOfPeriod = this.timelines[0].getIndexOfPeriod(mediaPeriodId.periodUid);
        for (int i2 = 0; i2 < length; i2++) {
            MediaSource.MediaPeriodId copyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(this.timelines[i2].getUidOfPeriod(indexOfPeriod));
            mediaPeriodArr[i2] = this.mediaSources[i2].createPeriod(copyWithPeriodUid, allocator, j2 - this.periodTimeOffsetsUs[indexOfPeriod][i2]);
            this.mediaPeriods.get(i2).add(new MediaPeriodAndId(copyWithPeriodUid, mediaPeriodArr[i2]));
        }
        MergingMediaPeriod mergingMediaPeriod = new MergingMediaPeriod(this.compositeSequenceableLoaderFactory, this.periodTimeOffsetsUs[indexOfPeriod], mediaPeriodArr);
        if (!this.clipDurations) {
            return mergingMediaPeriod;
        }
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(mergingMediaPeriod, false, 0, ((Long) Assertions.checkNotNull(this.clippedDurationsUs.get(mediaPeriodId.periodUid))).longValue());
        this.clippedMediaPeriods.put(mediaPeriodId.periodUid, clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    public MediaItem getMediaItem() {
        MediaSource[] mediaSourceArr = this.mediaSources;
        if (mediaSourceArr.length > 0) {
            return mediaSourceArr[0].getMediaItem();
        }
        return PLACEHOLDER_MEDIA_ITEM;
    }

    public void maybeThrowSourceInfoRefreshError() {
        IllegalMergeException illegalMergeException = this.mergeError;
        if (illegalMergeException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalMergeException;
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        for (int i2 = 0; i2 < this.mediaSources.length; i2++) {
            prepareChildSource(Integer.valueOf(i2), this.mediaSources[i2]);
        }
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        if (this.clipDurations) {
            ClippingMediaPeriod clippingMediaPeriod = (ClippingMediaPeriod) mediaPeriod;
            Iterator it = this.clippedMediaPeriods.a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                if (((ClippingMediaPeriod) entry.getValue()).equals(clippingMediaPeriod)) {
                    this.clippedMediaPeriods.remove(entry.getKey(), entry.getValue());
                    break;
                }
            }
            mediaPeriod = clippingMediaPeriod.mediaPeriod;
        }
        MergingMediaPeriod mergingMediaPeriod = (MergingMediaPeriod) mediaPeriod;
        for (int i2 = 0; i2 < this.mediaSources.length; i2++) {
            List list = this.mediaPeriods.get(i2);
            MediaPeriod childPeriod = mergingMediaPeriod.getChildPeriod(i2);
            int i7 = 0;
            while (true) {
                if (i7 >= list.size()) {
                    break;
                } else if (((MediaPeriodAndId) list.get(i7)).mediaPeriod.equals(childPeriod)) {
                    list.remove(i7);
                    break;
                } else {
                    i7++;
                }
            }
            this.mediaSources[i2].releasePeriod(mergingMediaPeriod.getChildPeriod(i2));
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Arrays.fill(this.timelines, (Object) null);
        this.periodCount = -1;
        this.mergeError = null;
        this.pendingTimelineSources.clear();
        Collections.addAll(this.pendingTimelineSources, this.mediaSources);
    }

    public void updateMediaItem(MediaItem mediaItem) {
        this.mediaSources[0].updateMediaItem(mediaItem);
    }

    public MergingMediaSource(boolean z, MediaSource... mediaSourceArr) {
        this(z, false, mediaSourceArr);
    }

    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        List list = this.mediaPeriods.get(num.intValue());
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (((MediaPeriodAndId) list.get(i2)).mediaPeriodId.equals(mediaPeriodId)) {
                return ((MediaPeriodAndId) this.mediaPeriods.get(0).get(i2)).mediaPeriodId;
            }
        }
        return null;
    }

    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline) {
        if (this.mergeError == null) {
            if (this.periodCount == -1) {
                this.periodCount = timeline.getPeriodCount();
            } else if (timeline.getPeriodCount() != this.periodCount) {
                this.mergeError = new IllegalMergeException(0);
                return;
            }
            if (this.periodTimeOffsetsUs.length == 0) {
                int i2 = this.periodCount;
                int[] iArr = new int[2];
                iArr[1] = this.timelines.length;
                iArr[0] = i2;
                this.periodTimeOffsetsUs = (long[][]) Array.newInstance(Long.TYPE, iArr);
            }
            this.pendingTimelineSources.remove(mediaSource);
            this.timelines[num.intValue()] = timeline;
            if (this.pendingTimelineSources.isEmpty()) {
                if (this.adjustPeriodTimeOffsets) {
                    computePeriodTimeOffsets();
                }
                ClippedTimeline clippedTimeline = this.timelines[0];
                if (this.clipDurations) {
                    updateClippedDuration();
                    clippedTimeline = new ClippedTimeline(clippedTimeline, this.clippedDurationsUs);
                }
                refreshSourceInfo(clippedTimeline);
            }
        }
    }

    public MergingMediaSource(boolean z, boolean z3, MediaSource... mediaSourceArr) {
        this(z, z3, new DefaultCompositeSequenceableLoaderFactory(), mediaSourceArr);
    }

    /* JADX WARNING: type inference failed for: r4v3, types: [F2.c, F2.r0, F2.t0] */
    public MergingMediaSource(boolean z, boolean z3, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, MediaSource... mediaSourceArr) {
        this.adjustPeriodTimeOffsets = z;
        this.clipDurations = z3;
        this.mediaSources = mediaSourceArr;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.pendingTimelineSources = new ArrayList<>(Arrays.asList(mediaSourceArr));
        this.periodCount = -1;
        this.mediaPeriods = new ArrayList(mediaSourceArr.length);
        for (int i2 = 0; i2 < mediaSourceArr.length; i2++) {
            this.mediaPeriods.add(new ArrayList());
        }
        this.timelines = new Timeline[mediaSourceArr.length];
        this.periodTimeOffsetsUs = new long[0][];
        this.clippedDurationsUs = new HashMap();
        C0040v.c(8, "expectedKeys");
        C0040v.c(2, "expectedValuesPerKey");
        A b = A.b(8);
        s0 s0Var = new s0();
        ? cVar = new C0009c(b);
        cVar.f272j = s0Var;
        this.clippedMediaPeriods = cVar;
    }
}
