package androidx.media3.exoplayer;

import android.content.Context;
import androidx.media3.exoplayer.ExoPlayerImpl;
import androidx.media3.exoplayer.analytics.PlayerId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ Context d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ ExoPlayerImpl f;
    public final /* synthetic */ PlayerId g;

    public /* synthetic */ o(Context context, boolean z, ExoPlayerImpl exoPlayerImpl, PlayerId playerId) {
        this.d = context;
        this.e = z;
        this.f = exoPlayerImpl;
        this.g = playerId;
    }

    public final void run() {
        ExoPlayerImpl.Api31.lambda$registerMediaMetricsListener$0(this.d, this.e, this.f, this.g);
    }
}
