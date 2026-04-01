package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ PlaybackInfo e;

    public /* synthetic */ i(PlaybackInfo playbackInfo, int i2) {
        this.d = i2;
        this.e = playbackInfo;
    }

    public final void invoke(Object obj) {
        int i2 = this.d;
        PlaybackInfo playbackInfo = this.e;
        Player.Listener listener = (Player.Listener) obj;
        switch (i2) {
            case 0:
                ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, listener);
                return;
            case 1:
                listener.onPlayerStateChanged(playbackInfo.playWhenReady, playbackInfo.playbackState);
                return;
            case 2:
                listener.onPlaybackStateChanged(playbackInfo.playbackState);
                return;
            case 3:
                listener.onPlayWhenReadyChanged(playbackInfo.playWhenReady, playbackInfo.playWhenReadyChangeReason);
                return;
            case 4:
                listener.onPlaybackSuppressionReasonChanged(playbackInfo.playbackSuppressionReason);
                return;
            case 5:
                listener.onIsPlayingChanged(playbackInfo.isPlaying());
                return;
            case 6:
                listener.onPlaybackParametersChanged(playbackInfo.playbackParameters);
                return;
            case 7:
                listener.onPlayerErrorChanged(playbackInfo.playbackError);
                return;
            case 8:
                listener.onPlayerError(playbackInfo.playbackError);
                return;
            default:
                listener.onTracksChanged(playbackInfo.trackSelectorResult.tracks);
                return;
        }
    }
}
