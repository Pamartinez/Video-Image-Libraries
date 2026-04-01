package io.grpc.binder;

import He.F;
import android.content.Intent;
import java.net.SocketAddress;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends SocketAddress {
    public static final /* synthetic */ int e = 0;
    public final Intent d;

    public a(Intent intent) {
        boolean z;
        if (intent.getComponent() == null && intent.getPackage() == null) {
            z = false;
        } else {
            z = true;
        }
        F.i("'bindIntent' must be explicit. Specify either a package or ComponentName.", z);
        this.d = intent;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            return this.d.filterEquals(((a) obj).d);
        }
        return false;
    }

    public final int hashCode() {
        Intent intent = this.d;
        if (intent.getPackage() != null) {
            intent = intent.cloneFilter().setPackage((String) null);
        }
        return intent.filterHashCode();
    }

    public final String toString() {
        return "AndroidComponentAddress[" + this.d + "]";
    }
}
