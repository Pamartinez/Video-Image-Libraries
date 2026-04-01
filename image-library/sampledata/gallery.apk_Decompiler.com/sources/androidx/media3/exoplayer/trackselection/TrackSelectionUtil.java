package androidx.media3.exoplayer.trackselection;

import F2.G;
import F2.N;
import F2.U;
import F2.y0;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.Tracks;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrackSelectionUtil {
    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, TrackSelection[] trackSelectionArr) {
        y0 y0Var;
        List[] listArr = new List[trackSelectionArr.length];
        for (int i2 = 0; i2 < trackSelectionArr.length; i2++) {
            TrackSelection trackSelection = trackSelectionArr[i2];
            if (trackSelection != null) {
                y0Var = U.B(trackSelection);
            } else {
                G g = U.e;
                y0Var = y0.f278h;
            }
            listArr[i2] = y0Var;
        }
        return buildTracks(mappedTrackInfo, (List<? extends TrackSelection>[]) listArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r1 != r3) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L_0x000f
            r3 = 0
            r0 = 1
            if (r6 <= r7) goto L_0x0008
            r1 = r0
            goto L_0x0009
        L_0x0008:
            r1 = r3
        L_0x0009:
            if (r4 <= r5) goto L_0x000c
            r3 = r0
        L_0x000c:
            if (r1 == r3) goto L_0x000f
            goto L_0x0012
        L_0x000f:
            r2 = r5
            r5 = r4
            r4 = r2
        L_0x0012:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L_0x0022
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = androidx.media3.common.util.Util.ceilDivide(r0, r6)
            r3.<init>(r5, r4)
            return r3
        L_0x0022:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = androidx.media3.common.util.Util.ceilDivide(r3, r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.trackselection.TrackSelectionUtil.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [F2.N, F2.Q] */
    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, List<? extends TrackSelection>[] listArr) {
        boolean z;
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
        ? n = new N(4);
        for (int i2 = 0; i2 < mappedTrackInfo2.getRendererCount(); i2++) {
            TrackGroupArray trackGroups = mappedTrackInfo2.getTrackGroups(i2);
            List<? extends TrackSelection> list = listArr[i2];
            for (int i7 = 0; i7 < trackGroups.length; i7++) {
                TrackGroup trackGroup = trackGroups.get(i7);
                boolean z3 = mappedTrackInfo2.getAdaptiveSupport(i2, i7, false) != 0;
                int i8 = trackGroup.length;
                int[] iArr = new int[i8];
                boolean[] zArr = new boolean[i8];
                for (int i10 = 0; i10 < trackGroup.length; i10++) {
                    iArr[i10] = mappedTrackInfo2.getTrackSupport(i2, i7, i10);
                    int i11 = 0;
                    while (true) {
                        if (i11 >= list.size()) {
                            z = false;
                            break;
                        }
                        TrackSelection trackSelection = (TrackSelection) list.get(i11);
                        if (trackSelection.getTrackGroup().equals(trackGroup) && trackSelection.indexOf(i10) != -1) {
                            z = true;
                            break;
                        }
                        i11++;
                    }
                    zArr[i10] = z;
                }
                n.a(new Tracks.Group(trackGroup, z3, iArr, zArr));
            }
        }
        TrackGroupArray unmappedTrackGroups = mappedTrackInfo2.getUnmappedTrackGroups();
        for (int i12 = 0; i12 < unmappedTrackGroups.length; i12++) {
            TrackGroup trackGroup2 = unmappedTrackGroups.get(i12);
            int[] iArr2 = new int[trackGroup2.length];
            Arrays.fill(iArr2, 0);
            n.a(new Tracks.Group(trackGroup2, false, iArr2, new boolean[trackGroup2.length]));
        }
        return new Tracks(n.f());
    }
}
