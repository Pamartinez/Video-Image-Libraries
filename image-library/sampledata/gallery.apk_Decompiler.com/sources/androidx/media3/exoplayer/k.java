package androidx.media3.exoplayer;

import androidx.media3.common.FlagSet;
import androidx.media3.common.Player;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.ExoPlayerImplInternal;
import androidx.media3.exoplayer.SuitableOutputChecker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ListenerSet.IterationFinishedEvent, ExoPlayerImplInternal.PlaybackInfoUpdateListener, BackgroundThreadStateHandler.StateChangeListener, SuitableOutputChecker.Callback, ListenerSet.Event {
    public final /* synthetic */ ExoPlayerImpl d;

    public /* synthetic */ k(ExoPlayerImpl exoPlayerImpl) {
        this.d = exoPlayerImpl;
    }

    public void a(boolean z) {
        this.d.onSelectedOutputSuitabilityChanged(z);
    }

    public void invoke(Object obj) {
        this.d.lambda$updateAvailableCommands$28((Player.Listener) obj);
    }

    public void onStateChanged(Object obj, Object obj2) {
        this.d.onAudioSessionIdChanged(((Integer) obj).intValue(), ((Integer) obj2).intValue());
    }

    public void invoke(Object obj, FlagSet flagSet) {
        this.d.lambda$new$0((Player.Listener) obj, flagSet);
    }
}
