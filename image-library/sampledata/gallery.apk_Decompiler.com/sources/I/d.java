package I;

import E2.r;
import android.content.Context;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements r {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;

    public /* synthetic */ d(Context context, int i2) {
        this.d = i2;
        this.e = context;
    }

    public final Object get() {
        int i2 = this.d;
        Context context = this.e;
        switch (i2) {
            case 0:
                return ExoPlayer.Builder.lambda$new$3(context);
            case 1:
                return ExoPlayer.Builder.lambda$new$14(context);
            default:
                return DefaultBandwidthMeter.getSingletonInstance(context);
        }
    }
}
