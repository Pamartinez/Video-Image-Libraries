package androidx.work.impl.utils;

import android.net.NetworkRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Landroidx/work/impl/utils/NetworkRequest31;", "", "()V", "capabilities", "", "request", "Landroid/net/NetworkRequest;", "transportTypes", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class NetworkRequest31 {
    public static final NetworkRequest31 INSTANCE = new NetworkRequest31();

    private NetworkRequest31() {
    }

    public final int[] capabilities(NetworkRequest networkRequest) {
        j.e(networkRequest, "request");
        int[] z = networkRequest.getCapabilities();
        j.d(z, "request.capabilities");
        return z;
    }

    public final int[] transportTypes(NetworkRequest networkRequest) {
        j.e(networkRequest, "request");
        int[] v = networkRequest.getTransportTypes();
        j.d(v, "request.transportTypes");
        return v;
    }
}
