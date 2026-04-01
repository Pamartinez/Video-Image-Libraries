package androidx.media3.exoplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Looper;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class StreamVolumeManager {
    private final Context applicationContext;
    private AudioManager audioManager;
    private final Listener listener;
    /* access modifiers changed from: private */
    public VolumeChangeReceiver receiver;
    /* access modifiers changed from: private */
    public final BackgroundThreadStateHandler<StreamVolumeState> stateHandler;
    private int volumeBeforeMute;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onStreamTypeChanged(int i2);

        void onStreamVolumeChanged(int i2, boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class StreamVolumeState {
        public final int maxVolume;
        public final int minVolume;
        public final boolean muted;
        public final int streamType;
        public final int volume;

        public StreamVolumeState(int i2, int i7, boolean z, int i8, int i10) {
            this.streamType = i2;
            this.volume = i7;
            this.muted = z;
            this.minVolume = i8;
            this.maxVolume = i10;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class VolumeChangeReceiver extends BroadcastReceiver {
        private VolumeChangeReceiver() {
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$0() {
            if (StreamVolumeManager.this.receiver != null) {
                StreamVolumeManager.this.stateHandler.setStateInBackground(StreamVolumeManager.this.generateState(((StreamVolumeState) StreamVolumeManager.this.stateHandler.get()).streamType));
            }
        }

        public void onReceive(Context context, Intent intent) {
            StreamVolumeManager.this.stateHandler.runInBackground(new C0079b(4, this));
        }
    }

    public StreamVolumeManager(Context context, Listener listener2, int i2, Looper looper, Looper looper2, Clock clock) {
        this.applicationContext = context.getApplicationContext();
        this.listener = listener2;
        int i7 = i2;
        int i8 = i7;
        StreamVolumeState streamVolumeState = new StreamVolumeState(i7, 0, false, 0, 0);
        BackgroundThreadStateHandler<StreamVolumeState> backgroundThreadStateHandler = new BackgroundThreadStateHandler<>(streamVolumeState, looper, looper2, clock, new y(this));
        this.stateHandler = backgroundThreadStateHandler;
        backgroundThreadStateHandler.runInBackground(new z(this, i8));
    }

    /* access modifiers changed from: private */
    public StreamVolumeState generateState(int i2) {
        Assertions.checkNotNull(this.audioManager);
        return new StreamVolumeState(i2, AudioManagerCompat.getStreamVolume(this.audioManager, i2), AudioManagerCompat.isStreamMute(this.audioManager, i2), AudioManagerCompat.getStreamMinVolume(this.audioManager, i2), AudioManagerCompat.getStreamMaxVolume(this.audioManager, i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(int i2) {
        this.audioManager = (AudioManager) Assertions.checkStateNotNull((AudioManager) this.applicationContext.getSystemService("audio"));
        VolumeChangeReceiver volumeChangeReceiver = new VolumeChangeReceiver();
        try {
            this.applicationContext.registerReceiver(volumeChangeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            this.receiver = volumeChangeReceiver;
        } catch (RuntimeException e) {
            Log.w("StreamVolumeManager", "Error registering stream volume receiver", e);
        }
        this.stateHandler.setStateInBackground(generateState(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ StreamVolumeState lambda$release$12(StreamVolumeState streamVolumeState) {
        VolumeChangeReceiver volumeChangeReceiver = this.receiver;
        if (volumeChangeReceiver != null) {
            try {
                this.applicationContext.unregisterReceiver(volumeChangeReceiver);
            } catch (RuntimeException e) {
                Log.w("StreamVolumeManager", "Error unregistering stream volume receiver", e);
            }
            this.receiver = null;
        }
        return streamVolumeState;
    }

    /* access modifiers changed from: private */
    public void onStreamVolumeStateChanged(StreamVolumeState streamVolumeState, StreamVolumeState streamVolumeState2) {
        boolean z = streamVolumeState.muted;
        if (!z && streamVolumeState2.muted) {
            this.volumeBeforeMute = streamVolumeState.volume;
        }
        int i2 = streamVolumeState.volume;
        int i7 = streamVolumeState2.volume;
        if (!(i2 == i7 && z == streamVolumeState2.muted)) {
            this.listener.onStreamVolumeChanged(i7, streamVolumeState2.muted);
        }
        int i8 = streamVolumeState.streamType;
        int i10 = streamVolumeState2.streamType;
        if (i8 != i10 || streamVolumeState.minVolume != streamVolumeState2.minVolume || streamVolumeState.maxVolume != streamVolumeState2.maxVolume) {
            this.listener.onStreamTypeChanged(i10);
        }
    }

    public int getMaxVolume() {
        return this.stateHandler.get().maxVolume;
    }

    public int getMinVolume() {
        return this.stateHandler.get().minVolume;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [E2.h, java.lang.Object] */
    public void release() {
        this.stateHandler.updateStateAsync(new Object(), new y(this));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ StreamVolumeState lambda$release$11(StreamVolumeState streamVolumeState) {
        return streamVolumeState;
    }
}
