package com.samsung.scsp.framework.core.network;

import android.os.Bundle;
import com.samsung.scsp.framework.core.network.NetworkPermissionFactoryLoader;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ Bundle d;
    public final /* synthetic */ NetworkPermissionFactoryLoader.NetworkPermissionFactoryHolder e;

    public /* synthetic */ b(Bundle bundle, NetworkPermissionFactoryLoader.NetworkPermissionFactoryHolder networkPermissionFactoryHolder) {
        this.d = bundle;
        this.e = networkPermissionFactoryHolder;
    }

    public final void accept(Object obj) {
        NetworkPermissionFactoryLoader.lambda$load$1(this.d, this.e, (String) obj);
    }
}
