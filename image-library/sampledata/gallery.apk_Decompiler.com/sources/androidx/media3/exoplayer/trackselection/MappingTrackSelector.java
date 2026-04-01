package androidx.media3.exoplayer.trackselection;

import android.util.Pair;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup, int[] iArr, boolean z) {
        boolean z3;
        int length = rendererCapabilitiesArr.length;
        int i2 = 0;
        boolean z7 = true;
        for (int i7 = 0; i7 < rendererCapabilitiesArr.length; i7++) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i7];
            int i8 = 0;
            for (int i10 = 0; i10 < trackGroup.length; i10++) {
                i8 = Math.max(i8, RendererCapabilities.getFormatSupport(rendererCapabilities.supportsFormat(trackGroup.getFormat(i10))));
            }
            if (iArr[i7] == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (i8 > i2 || (i8 == i2 && z && !z7 && z3)) {
                length = i7;
                z7 = z3;
                i2 = i8;
            }
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) {
        int[] iArr = new int[trackGroup.length];
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            iArr[i2] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i2));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] rendererCapabilitiesArr) {
        int length = rendererCapabilitiesArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = rendererCapabilitiesArr[i2].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }

    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    public abstract Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline);

    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
        int[] iArr;
        int[] iArr2 = new int[(rendererCapabilitiesArr.length + 1)];
        int length = rendererCapabilitiesArr.length + 1;
        TrackGroup[][] trackGroupArr = new TrackGroup[length][];
        int[][][] iArr3 = new int[(rendererCapabilitiesArr.length + 1)][][];
        for (int i2 = 0; i2 < length; i2++) {
            int i7 = trackGroupArray.length;
            trackGroupArr[i2] = new TrackGroup[i7];
            iArr3[i2] = new int[i7][];
        }
        int[] mixedMimeTypeAdaptationSupports = getMixedMimeTypeAdaptationSupports(rendererCapabilitiesArr);
        for (int i8 = 0; i8 < trackGroupArray.length; i8++) {
            TrackGroup trackGroup = trackGroupArray.get(i8);
            int findRenderer = findRenderer(rendererCapabilitiesArr, trackGroup, iArr2, trackGroup.type == 5);
            if (findRenderer == rendererCapabilitiesArr.length) {
                iArr = new int[trackGroup.length];
            } else {
                iArr = getFormatSupport(rendererCapabilitiesArr[findRenderer], trackGroup);
            }
            int i10 = iArr2[findRenderer];
            trackGroupArr[findRenderer][i10] = trackGroup;
            iArr3[findRenderer][i10] = iArr;
            iArr2[findRenderer] = i10 + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr.length];
        String[] strArr = new String[rendererCapabilitiesArr.length];
        int[] iArr4 = new int[rendererCapabilitiesArr.length];
        for (int i11 = 0; i11 < rendererCapabilitiesArr.length; i11++) {
            int i12 = iArr2[i11];
            trackGroupArrayArr[i11] = new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[i11], i12));
            iArr3[i11] = (int[][]) Util.nullSafeArrayCopy(iArr3[i11], i12);
            strArr[i11] = rendererCapabilitiesArr[i11].getName();
            iArr4[i11] = rendererCapabilitiesArr[i11].getTrackType();
        }
        int[] iArr5 = mixedMimeTypeAdaptationSupports;
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(strArr, iArr4, trackGroupArrayArr, iArr5, iArr3, new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[rendererCapabilitiesArr.length], iArr2[rendererCapabilitiesArr.length])));
        Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks = selectTracks(mappedTrackInfo, iArr3, iArr5, mediaPeriodId, timeline);
        MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
        return new TrackSelectorResult((RendererConfiguration[]) selectTracks.first, (ExoTrackSelection[]) selectTracks.second, TrackSelectionUtil.buildTracks(mappedTrackInfo2, (TrackSelection[]) selectTracks.second), mappedTrackInfo2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MappedTrackInfo {
        private final int rendererCount;
        private final int[][][] rendererFormatSupports;
        private final int[] rendererMixedMimeTypeAdaptiveSupports;
        private final String[] rendererNames;
        private final TrackGroupArray[] rendererTrackGroups;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray unmappedTrackGroups;

        public MappedTrackInfo(String[] strArr, int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererNames = strArr;
            this.rendererTrackTypes = iArr;
            this.rendererTrackGroups = trackGroupArrayArr;
            this.rendererFormatSupports = iArr3;
            this.rendererMixedMimeTypeAdaptiveSupports = iArr2;
            this.unmappedTrackGroups = trackGroupArray;
            this.rendererCount = iArr.length;
        }

        public int getAdaptiveSupport(int i2, int i7, boolean z) {
            int i8 = this.rendererTrackGroups[i2].get(i7).length;
            int[] iArr = new int[i8];
            int i10 = 0;
            for (int i11 = 0; i11 < i8; i11++) {
                int trackSupport = getTrackSupport(i2, i7, i11);
                if (trackSupport == 4 || (z && trackSupport == 3)) {
                    iArr[i10] = i11;
                    i10++;
                }
            }
            return getAdaptiveSupport(i2, i7, Arrays.copyOf(iArr, i10));
        }

        public int getCapabilities(int i2, int i7, int i8) {
            return this.rendererFormatSupports[i2][i7][i8];
        }

        public int getRendererCount() {
            return this.rendererCount;
        }

        public int getRendererType(int i2) {
            return this.rendererTrackTypes[i2];
        }

        public TrackGroupArray getTrackGroups(int i2) {
            return this.rendererTrackGroups[i2];
        }

        public int getTrackSupport(int i2, int i7, int i8) {
            return RendererCapabilities.getFormatSupport(getCapabilities(i2, i7, i8));
        }

        public TrackGroupArray getUnmappedTrackGroups() {
            return this.unmappedTrackGroups;
        }

        public int getAdaptiveSupport(int i2, int i7, int[] iArr) {
            int i8 = 0;
            int i10 = 16;
            String str = null;
            boolean z = false;
            int i11 = 0;
            while (i8 < iArr.length) {
                String str2 = this.rendererTrackGroups[i2].get(i7).getFormat(iArr[i8]).sampleMimeType;
                int i12 = i11 + 1;
                if (i11 == 0) {
                    str = str2;
                } else {
                    z |= !Objects.equals(str, str2);
                }
                i10 = Math.min(i10, RendererCapabilities.getAdaptiveSupport(this.rendererFormatSupports[i2][i7][i8]));
                i8++;
                i11 = i12;
            }
            return z ? Math.min(i10, this.rendererMixedMimeTypeAdaptiveSupports[i2]) : i10;
        }
    }
}
