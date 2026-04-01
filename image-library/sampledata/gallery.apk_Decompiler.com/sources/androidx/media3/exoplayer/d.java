package androidx.media3.exoplayer;

import E2.r;
import android.content.Context;
import androidx.media3.common.audio.AudioManagerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements r {
    public final /* synthetic */ Context d;

    public /* synthetic */ d(Context context) {
        this.d = context;
    }

    public final Object get() {
        return AudioManagerCompat.getAudioManager(this.d);
    }
}
