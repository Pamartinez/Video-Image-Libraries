package E2;

import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements r {
    public final /* synthetic */ int d;

    public /* synthetic */ t(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                throw new IllegalStateException();
            case 1:
                return DataSourceBitmapLoader.lambda$static$0();
            case 2:
                return new DefaultLoadControl();
            default:
                return DefaultPlaybackSessionManager.generateDefaultSessionId();
        }
    }
}
