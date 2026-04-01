package com.samsung.scsp.framework.core.network;

import android.content.Context;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.HttpRequestImpl;
import com.samsung.scsp.framework.core.network.NetworkPermissionFactoryLoader;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(int i2, Object obj, String str) {
        this.d = i2;
        this.e = obj;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                Supplier unused = ((NetworkPermissionFactoryLoader.NetworkPermissionFactoryHolder) this.e).instance = (Supplier) Class.forName((String) this.f).newInstance();
                return;
            case 1:
                NetworkPermissionFactoryLoader.lambda$load$2((Context) this.f, (NetworkPermissionFactoryLoader.NetworkPermissionFactoryHolder) this.e);
                return;
            default:
                ((HttpRequestImpl.HttpRequestBuilder) this.e).lambda$new$0((String) this.f);
                return;
        }
    }

    public /* synthetic */ a(Context context, NetworkPermissionFactoryLoader.NetworkPermissionFactoryHolder networkPermissionFactoryHolder) {
        this.d = 1;
        this.f = context;
        this.e = networkPermissionFactoryHolder;
    }
}
