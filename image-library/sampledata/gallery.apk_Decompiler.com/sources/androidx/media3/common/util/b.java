package androidx.media3.common.util;

import android.content.Context;
import androidx.media3.common.util.NetworkTypeObserver;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ NetworkTypeObserver.Receiver d;
    public final /* synthetic */ Context e;

    public /* synthetic */ b(NetworkTypeObserver.Receiver receiver, Context context) {
        this.d = receiver;
        this.e = context;
    }

    public final void run() {
        this.d.lambda$onReceive$0(this.e);
    }
}
