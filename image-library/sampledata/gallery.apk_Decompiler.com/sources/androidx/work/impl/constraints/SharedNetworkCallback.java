package androidx.work.impl.constraints;

import Ae.a;
import Ae.b;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintsState;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000b\u0010\fJ;\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u0011j\u0002`\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0006*\u00020\r¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR0\u0010\u001e\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u0011j\u0002`\u0013\u0012\u0004\u0012\u00020\u000f0\u001d8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010'\u001a\u00020&8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006-"}, d2 = {"Landroidx/work/impl/constraints/SharedNetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "<init>", "()V", "Landroid/net/Network;", "network", "Landroid/net/NetworkCapabilities;", "networkCapabilities", "Lme/x;", "onCapabilitiesChanged", "(Landroid/net/Network;Landroid/net/NetworkCapabilities;)V", "onLost", "(Landroid/net/Network;)V", "Landroid/net/ConnectivityManager;", "connManager", "Landroid/net/NetworkRequest;", "networkRequest", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "Landroidx/work/impl/constraints/OnConstraintState;", "onConstraintState", "Lkotlin/Function0;", "addCallback", "(Landroid/net/ConnectivityManager;Landroid/net/NetworkRequest;LAe/b;)LAe/a;", "getCurrentNetworkCapabilities", "(Landroid/net/ConnectivityManager;)Landroid/net/NetworkCapabilities;", "", "requestsLock", "Ljava/lang/Object;", "", "requests", "Ljava/util/Map;", "cachedCapabilities", "Landroid/net/NetworkCapabilities;", "getCachedCapabilities", "()Landroid/net/NetworkCapabilities;", "setCachedCapabilities", "(Landroid/net/NetworkCapabilities;)V", "", "capabilitiesInitialized", "Z", "getCapabilitiesInitialized", "()Z", "setCapabilitiesInitialized", "(Z)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SharedNetworkCallback extends ConnectivityManager.NetworkCallback {
    public static final SharedNetworkCallback INSTANCE = new SharedNetworkCallback();
    private static NetworkCapabilities cachedCapabilities;
    private static boolean capabilitiesInitialized;
    /* access modifiers changed from: private */
    public static final Map<b, NetworkRequest> requests = new LinkedHashMap();
    /* access modifiers changed from: private */
    public static final Object requestsLock = new Object();

    private SharedNetworkCallback() {
    }

    public final a addCallback(ConnectivityManager connectivityManager, NetworkRequest networkRequest, b bVar) {
        Object obj;
        j.e(connectivityManager, "connManager");
        j.e(networkRequest, "networkRequest");
        j.e(bVar, "onConstraintState");
        synchronized (requestsLock) {
            try {
                Map<b, NetworkRequest> map = requests;
                boolean isEmpty = map.isEmpty();
                map.put(bVar, networkRequest);
                if (isEmpty) {
                    Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController register shared callback");
                    connectivityManager.registerDefaultNetworkCallback(this);
                }
                Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController send initial capabilities");
                if (networkRequest.canBeSatisfiedBy(INSTANCE.getCurrentNetworkCapabilities(connectivityManager))) {
                    obj = ConstraintsState.ConstraintsMet.INSTANCE;
                } else {
                    obj = new ConstraintsState.ConstraintsNotMet(7);
                }
                bVar.invoke(obj);
            } catch (Throwable th) {
                throw th;
            }
        }
        return new SharedNetworkCallback$addCallback$2(bVar, connectivityManager, this);
    }

    public final NetworkCapabilities getCurrentNetworkCapabilities(ConnectivityManager connectivityManager) {
        j.e(connectivityManager, "<this>");
        if (capabilitiesInitialized) {
            return cachedCapabilities;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        cachedCapabilities = networkCapabilities;
        capabilitiesInitialized = true;
        return networkCapabilities;
    }

    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        Object obj;
        j.e(network, "network");
        j.e(networkCapabilities, "networkCapabilities");
        Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController onCapabilitiesChanged callback");
        synchronized (requestsLock) {
            try {
                cachedCapabilities = networkCapabilities;
                for (Map.Entry entry : requests.entrySet()) {
                    b bVar = (b) entry.getKey();
                    if (((NetworkRequest) entry.getValue()).canBeSatisfiedBy(networkCapabilities)) {
                        obj = ConstraintsState.ConstraintsMet.INSTANCE;
                    } else {
                        obj = new ConstraintsState.ConstraintsNotMet(7);
                    }
                    bVar.invoke(obj);
                }
            } finally {
            }
        }
    }

    public void onLost(Network network) {
        j.e(network, "network");
        Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController onLost callback");
        synchronized (requestsLock) {
            cachedCapabilities = null;
            for (b invoke : requests.keySet()) {
                invoke.invoke(new ConstraintsState.ConstraintsNotMet(7));
            }
        }
    }

    public final void setCachedCapabilities(NetworkCapabilities networkCapabilities) {
        cachedCapabilities = networkCapabilities;
    }

    public final void setCapabilitiesInitialized(boolean z) {
        capabilitiesInitialized = z;
    }
}
