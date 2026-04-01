package N;

import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.util.UrlUtil;
import java.io.Serializable;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DefaultTrackSelector.TrackInfo.Factory, FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ boolean d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Serializable g;

    /* JADX WARNING: type inference failed for: r4v0, types: [int[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ b(androidx.media3.exoplayer.trackselection.DefaultTrackSelector r1, androidx.media3.exoplayer.trackselection.DefaultTrackSelector.Parameters r2, boolean r3, int[] r4) {
        /*
            r0 = this;
            r0.<init>()
            r0.e = r1
            r0.f = r2
            r0.d = r3
            r0.g = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: N.b.<init>(androidx.media3.exoplayer.trackselection.DefaultTrackSelector, androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters, boolean, int[]):void");
    }

    public List create(int i2, TrackGroup trackGroup, int[] iArr) {
        return ((DefaultTrackSelector) this.e).lambda$selectAudioTrack$3((DefaultTrackSelector.Parameters) this.f, this.d, (int[]) this.g, i2, trackGroup, iArr);
    }

    public Object get() {
        return UrlUtil.lambda$addUrlParameter$0(this.d, (StringBuilder) this.e, (String) this.f, (String) this.g);
    }

    public /* synthetic */ b(boolean z, StringBuilder sb2, String str, String str2) {
        this.d = z;
        this.e = sb2;
        this.f = str;
        this.g = str2;
    }
}
