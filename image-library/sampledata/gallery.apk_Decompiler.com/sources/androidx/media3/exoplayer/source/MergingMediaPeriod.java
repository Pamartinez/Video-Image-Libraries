package androidx.media3.exoplayer.source;

import F2.C0040v;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.ForwardingTrackSelection;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private final HashMap<TrackGroup, TrackGroup> childTrackGroupByMergedTrackGroup = new HashMap<>();
    private final ArrayList<MediaPeriod> childrenPendingPreparation = new ArrayList<>();
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private MediaPeriod[] enabledPeriods;
    private final MediaPeriod[] periods;
    private final boolean[] periodsWithTimeOffsets;
    private final IdentityHashMap<SampleStream, Integer> streamPeriodIndices;
    private TrackGroupArray trackGroups;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MergingMediaPeriodTrackSelection extends ForwardingTrackSelection {
        private final TrackGroup trackGroup;

        public MergingMediaPeriodTrackSelection(ExoTrackSelection exoTrackSelection, TrackGroup trackGroup2) {
            super(exoTrackSelection);
            this.trackGroup = trackGroup2;
        }

        public boolean equals(Object obj) {
            if (!super.equals(obj) || !(obj instanceof MergingMediaPeriodTrackSelection)) {
                return false;
            }
            return this.trackGroup.equals(((MergingMediaPeriodTrackSelection) obj).trackGroup);
        }

        public Format getFormat(int i2) {
            return this.trackGroup.getFormat(getWrappedInstance().getIndexInTrackGroup(i2));
        }

        public Format getSelectedFormat() {
            return this.trackGroup.getFormat(getWrappedInstance().getSelectedIndexInTrackGroup());
        }

        public TrackGroup getTrackGroup() {
            return this.trackGroup;
        }

        public int hashCode() {
            return this.trackGroup.hashCode() + (super.hashCode() * 31);
        }
    }

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, long[] jArr, MediaPeriod... mediaPeriodArr) {
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.periods = mediaPeriodArr;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory2.empty();
        this.streamPeriodIndices = new IdentityHashMap<>();
        this.enabledPeriods = new MediaPeriod[0];
        this.periodsWithTimeOffsets = new boolean[mediaPeriodArr.length];
        for (int i2 = 0; i2 < mediaPeriodArr.length; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                this.periodsWithTimeOffsets[i2] = true;
                this.periods[i2] = new TimeOffsetMediaPeriod(mediaPeriodArr[i2], j2);
            }
        }
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.childrenPendingPreparation.isEmpty()) {
            return this.compositeSequenceableLoader.continueLoading(loadingInfo);
        }
        int size = this.childrenPendingPreparation.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.childrenPendingPreparation.get(i2).continueLoading(loadingInfo);
        }
        return false;
    }

    public void discardBuffer(long j2, boolean z) {
        for (MediaPeriod discardBuffer : this.enabledPeriods) {
            discardBuffer.discardBuffer(j2, z);
        }
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        MediaPeriod mediaPeriod;
        MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
        if (mediaPeriodArr.length > 0) {
            mediaPeriod = mediaPeriodArr[0];
        } else {
            mediaPeriod = this.periods[0];
        }
        return mediaPeriod.getAdjustedSeekPositionUs(j2, seekParameters);
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public MediaPeriod getChildPeriod(int i2) {
        if (this.periodsWithTimeOffsets[i2]) {
            return ((TimeOffsetMediaPeriod) this.periods[i2]).getWrappedMediaPeriod();
        }
        return this.periods[i2];
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    public void maybeThrowPrepareError() {
        for (MediaPeriod maybeThrowPrepareError : this.periods) {
            maybeThrowPrepareError.maybeThrowPrepareError();
        }
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.childrenPendingPreparation.remove(mediaPeriod);
        if (this.childrenPendingPreparation.isEmpty()) {
            int i2 = 0;
            for (MediaPeriod trackGroups2 : this.periods) {
                i2 += trackGroups2.getTrackGroups().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i2];
            int i7 = 0;
            int i8 = 0;
            while (true) {
                MediaPeriod[] mediaPeriodArr = this.periods;
                if (i7 < mediaPeriodArr.length) {
                    TrackGroupArray trackGroups3 = mediaPeriodArr[i7].getTrackGroups();
                    int i10 = trackGroups3.length;
                    int i11 = 0;
                    while (i11 < i10) {
                        TrackGroup trackGroup = trackGroups3.get(i11);
                        Format[] formatArr = new Format[trackGroup.length];
                        for (int i12 = 0; i12 < trackGroup.length; i12++) {
                            Format format = trackGroup.getFormat(i12);
                            Format.Builder buildUpon = format.buildUpon();
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(i7);
                            sb2.append(NumericEnum.SEP);
                            String str = format.id;
                            if (str == null) {
                                str = "";
                            }
                            sb2.append(str);
                            formatArr[i12] = buildUpon.setId(sb2.toString()).build();
                        }
                        TrackGroup trackGroup2 = new TrackGroup(i7 + NumericEnum.SEP + trackGroup.id, formatArr);
                        this.childTrackGroupByMergedTrackGroup.put(trackGroup2, trackGroup);
                        trackGroupArr[i8] = trackGroup2;
                        i11++;
                        i8++;
                    }
                    i7++;
                } else {
                    this.trackGroups = new TrackGroupArray(trackGroupArr);
                    ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
                    return;
                }
            }
        }
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        Collections.addAll(this.childrenPendingPreparation, this.periods);
        for (MediaPeriod prepare : this.periods) {
            prepare.prepare(this, j2);
        }
    }

    public long readDiscontinuity() {
        long j2 = -9223372036854775807L;
        for (MediaPeriod mediaPeriod : this.enabledPeriods) {
            long readDiscontinuity = mediaPeriod.readDiscontinuity();
            if (readDiscontinuity != -9223372036854775807L) {
                if (j2 == -9223372036854775807L) {
                    MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
                    int length = mediaPeriodArr.length;
                    int i2 = 0;
                    while (i2 < length) {
                        MediaPeriod mediaPeriod2 = mediaPeriodArr[i2];
                        if (mediaPeriod2 == mediaPeriod) {
                            break;
                        } else if (mediaPeriod2.seekToUs(readDiscontinuity) == readDiscontinuity) {
                            i2++;
                        } else {
                            throw new IllegalStateException("Unexpected child seekToUs result.");
                        }
                    }
                    j2 = readDiscontinuity;
                } else if (readDiscontinuity != j2) {
                    throw new IllegalStateException("Conflicting discontinuities.");
                }
            } else if (!(j2 == -9223372036854775807L || mediaPeriod.seekToUs(j2) == j2)) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
        return j2;
    }

    public void reevaluateBuffer(long j2) {
        this.compositeSequenceableLoader.reevaluateBuffer(j2);
    }

    public long seekToUs(long j2) {
        long seekToUs = this.enabledPeriods[0].seekToUs(j2);
        int i2 = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
            if (i2 >= mediaPeriodArr.length) {
                return seekToUs;
            }
            if (mediaPeriodArr[i2].seekToUs(seekToUs) == seekToUs) {
                i2++;
            } else {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [E2.h, java.lang.Object] */
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        boolean z;
        SampleStream sampleStream;
        Integer num;
        int i2;
        ExoTrackSelection[] exoTrackSelectionArr2 = exoTrackSelectionArr;
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        int[] iArr = new int[exoTrackSelectionArr2.length];
        int[] iArr2 = new int[exoTrackSelectionArr2.length];
        int i7 = 0;
        for (int i8 = 0; i8 < exoTrackSelectionArr2.length; i8++) {
            SampleStream sampleStream2 = sampleStreamArr2[i8];
            if (sampleStream2 == null) {
                num = null;
            } else {
                num = this.streamPeriodIndices.get(sampleStream2);
            }
            if (num == null) {
                i2 = -1;
            } else {
                i2 = num.intValue();
            }
            iArr[i8] = i2;
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr2[i8];
            if (exoTrackSelection != null) {
                String str = exoTrackSelection.getTrackGroup().id;
                iArr2[i8] = Integer.parseInt(str.substring(0, str.indexOf(NumericEnum.SEP)));
            } else {
                iArr2[i8] = -1;
            }
        }
        this.streamPeriodIndices.clear();
        int length = exoTrackSelectionArr2.length;
        SampleStream[] sampleStreamArr3 = new SampleStream[length];
        SampleStream[] sampleStreamArr4 = new SampleStream[exoTrackSelectionArr2.length];
        ExoTrackSelection[] exoTrackSelectionArr3 = new ExoTrackSelection[exoTrackSelectionArr2.length];
        ArrayList arrayList = new ArrayList(this.periods.length);
        long j3 = j2;
        int i10 = 0;
        while (i10 < this.periods.length) {
            for (int i11 = i7; i11 < exoTrackSelectionArr2.length; i11++) {
                if (iArr[i11] == i10) {
                    sampleStream = sampleStreamArr2[i11];
                } else {
                    sampleStream = null;
                }
                sampleStreamArr4[i11] = sampleStream;
                if (iArr2[i11] == i10) {
                    ExoTrackSelection exoTrackSelection2 = (ExoTrackSelection) Assertions.checkNotNull(exoTrackSelectionArr2[i11]);
                    exoTrackSelectionArr3[i11] = new MergingMediaPeriodTrackSelection(exoTrackSelection2, (TrackGroup) Assertions.checkNotNull(this.childTrackGroupByMergedTrackGroup.get(exoTrackSelection2.getTrackGroup())));
                } else {
                    exoTrackSelectionArr3[i11] = null;
                }
            }
            int i12 = i10;
            long selectTracks = this.periods[i10].selectTracks(exoTrackSelectionArr3, zArr, sampleStreamArr4, zArr2, j3);
            if (i12 == 0) {
                j3 = selectTracks;
            } else if (selectTracks != j3) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean z3 = false;
            for (int i13 = 0; i13 < exoTrackSelectionArr2.length; i13++) {
                if (iArr2[i13] == i12) {
                    sampleStreamArr3[i13] = sampleStreamArr4[i13];
                    this.streamPeriodIndices.put((SampleStream) Assertions.checkNotNull(sampleStreamArr4[i13]), Integer.valueOf(i12));
                    z3 = true;
                } else if (iArr[i13] == i12) {
                    if (sampleStreamArr4[i13] == null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Assertions.checkState(z);
                }
            }
            if (z3) {
                arrayList.add(this.periods[i12]);
            }
            i10 = i12 + 1;
            i7 = 0;
        }
        int i14 = i7;
        System.arraycopy(sampleStreamArr3, i14, sampleStreamArr2, i14, length);
        this.enabledPeriods = (MediaPeriod[]) arrayList.toArray(new MediaPeriod[i14]);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(arrayList, C0040v.t(arrayList, new Object()));
        return j3;
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }
}
