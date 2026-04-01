package I;

import E2.r;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.TrackSelector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements r {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return ExoPlayer.Builder.lambda$setLoadControl$19((LoadControl) obj);
            case 1:
                return ExoPlayer.Builder.lambda$setTrackSelector$18((TrackSelector) obj);
            case 2:
                return ExoPlayer.Builder.lambda$setMediaSourceFactory$17((MediaSource.Factory) obj);
            default:
                return ExoPlayer.Builder.lambda$new$2((RenderersFactory) obj);
        }
    }
}
