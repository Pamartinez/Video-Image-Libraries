package androidx.work.impl.constraints;

import Ae.a;
import Ae.b;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import androidx.work.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lme/x;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SharedNetworkCallback$addCallback$2 extends k implements a {
    final /* synthetic */ ConnectivityManager $connManager;
    final /* synthetic */ b $onConstraintState;
    final /* synthetic */ SharedNetworkCallback this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SharedNetworkCallback$addCallback$2(b bVar, ConnectivityManager connectivityManager, SharedNetworkCallback sharedNetworkCallback) {
        super(0);
        this.$onConstraintState = bVar;
        this.$connManager = connectivityManager;
        this.this$0 = sharedNetworkCallback;
    }

    public final void invoke() {
        Object access$getRequestsLock$p = SharedNetworkCallback.requestsLock;
        b bVar = this.$onConstraintState;
        ConnectivityManager connectivityManager = this.$connManager;
        SharedNetworkCallback sharedNetworkCallback = this.this$0;
        synchronized (access$getRequestsLock$p) {
            SharedNetworkCallback.requests.remove(bVar);
            if (SharedNetworkCallback.requests.isEmpty()) {
                Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController unregister shared callback");
                connectivityManager.unregisterNetworkCallback(sharedNetworkCallback);
                SharedNetworkCallback sharedNetworkCallback2 = SharedNetworkCallback.INSTANCE;
                sharedNetworkCallback2.setCachedCapabilities((NetworkCapabilities) null);
                sharedNetworkCallback2.setCapabilitiesInitialized(false);
            }
        }
    }
}
