package androidx.media3.effect;

import E2.l;
import androidx.media3.effect.DefaultVideoCompositor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements l {
    public final /* synthetic */ long d;

    public /* synthetic */ i(long j2) {
        this.d = j2;
    }

    public final boolean apply(Object obj) {
        return DefaultVideoCompositor.lambda$releaseExcessFramesInSecondaryStream$1(this.d, (DefaultVideoCompositor.InputFrameInfo) obj);
    }
}
