package androidx.work.impl.constraints.trackers;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.work.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"androidx/work/impl/constraints/trackers/NetworkStateTracker24$networkCallback$1", "Landroid/net/ConnectivityManager$NetworkCallback;", "Landroid/net/Network;", "network", "Landroid/net/NetworkCapabilities;", "capabilities", "Lme/x;", "onCapabilitiesChanged", "(Landroid/net/Network;Landroid/net/NetworkCapabilities;)V", "onLost", "(Landroid/net/Network;)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NetworkStateTracker24$networkCallback$1 extends ConnectivityManager.NetworkCallback {
    final /* synthetic */ NetworkStateTracker24 this$0;

    public NetworkStateTracker24$networkCallback$1(NetworkStateTracker24 networkStateTracker24) {
        this.this$0 = networkStateTracker24;
    }

    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        j.e(network, "network");
        j.e(networkCapabilities, "capabilities");
        Logger logger = Logger.get();
        String access$getTAG$p = NetworkStateTrackerKt.TAG;
        logger.debug(access$getTAG$p, "Network capabilities changed: " + networkCapabilities);
        this.this$0.setState(NetworkStateTrackerKt.getActiveNetworkState(networkCapabilities));
    }

    public void onLost(Network network) {
        j.e(network, "network");
        Logger.get().debug(NetworkStateTrackerKt.TAG, "Network connection lost");
        NetworkStateTracker24 networkStateTracker24 = this.this$0;
        networkStateTracker24.setState(NetworkStateTrackerKt.getActiveNetworkState(networkStateTracker24.connectivityManager));
    }
}
