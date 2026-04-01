package androidx.media3.exoplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AudioBecomingNoisyManager {
    private final HandlerWrapper backgroundHandler;
    private final Context context;
    /* access modifiers changed from: private */
    public boolean isEnabled;
    private final AudioBecomingNoisyReceiver receiver;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AudioBecomingNoisyReceiver extends BroadcastReceiver {
        private final HandlerWrapper eventHandler;
        private final EventListener listener;

        public AudioBecomingNoisyReceiver(HandlerWrapper handlerWrapper, EventListener eventListener) {
            this.eventHandler = handlerWrapper;
            this.listener = eventListener;
        }

        /* access modifiers changed from: private */
        public void callListenerIfEnabled() {
            if (AudioBecomingNoisyManager.this.isEnabled) {
                this.listener.onAudioBecomingNoisy();
            }
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                this.eventHandler.post(new C0079b(0, this));
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EventListener {
        void onAudioBecomingNoisy();
    }

    public AudioBecomingNoisyManager(Context context2, Looper looper, Looper looper2, EventListener eventListener, Clock clock) {
        this.context = context2.getApplicationContext();
        this.backgroundHandler = clock.createHandler(looper, (Handler.Callback) null);
        this.receiver = new AudioBecomingNoisyReceiver(clock.createHandler(looper2, (Handler.Callback) null), eventListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnabled$0() {
        this.context.registerReceiver(this.receiver, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnabled$1() {
        this.context.unregisterReceiver(this.receiver);
    }

    public void setEnabled(boolean z) {
        if (z != this.isEnabled) {
            if (z) {
                this.backgroundHandler.post(new C0078a(this, 0));
                this.isEnabled = true;
                return;
            }
            this.backgroundHandler.post(new C0078a(this, 1));
            this.isEnabled = false;
        }
    }
}
