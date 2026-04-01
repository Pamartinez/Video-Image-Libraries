package androidx.media3.common.util;

import androidx.media3.common.util.NetworkTypeObserver;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ NetworkTypeObserver.ListenerHolder d;

    public /* synthetic */ a(NetworkTypeObserver.ListenerHolder listenerHolder) {
        this.d = listenerHolder;
    }

    public final void run() {
        this.d.lambda$callOnNetworkTypeChanged$0();
    }
}
