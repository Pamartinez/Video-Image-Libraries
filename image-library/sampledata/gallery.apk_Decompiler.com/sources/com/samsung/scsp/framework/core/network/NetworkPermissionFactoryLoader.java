package com.samsung.scsp.framework.core.network;

import android.content.Context;
import android.os.Bundle;
import com.samsung.scsp.error.FaultBarrier;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NetworkPermissionFactoryLoader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NetworkPermissionFactoryHolder {
        /* access modifiers changed from: private */
        public Supplier<Predicate<String>> instance;

        /* JADX WARNING: type inference failed for: r0v0, types: [java.util.function.Supplier<java.util.function.Predicate<java.lang.String>>, java.lang.Object] */
        private NetworkPermissionFactoryHolder() {
            this.instance = new Object();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$new$0(String str) {
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Predicate lambda$new$1() {
            return new c(0);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$load$1(Bundle bundle, NetworkPermissionFactoryHolder networkPermissionFactoryHolder, String str) {
        Object obj = bundle.get(str);
        if ((obj instanceof String) && "NetworkPermissionFactory".equals(obj)) {
            FaultBarrier.run(new a(0, networkPermissionFactoryHolder, str));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$load$2(Context context, NetworkPermissionFactoryHolder networkPermissionFactoryHolder) {
        Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        bundle.keySet().forEach(new b(bundle, networkPermissionFactoryHolder));
    }

    public static Supplier<Predicate<String>> load(Context context) {
        NetworkPermissionFactoryHolder networkPermissionFactoryHolder = new NetworkPermissionFactoryHolder();
        FaultBarrier.run(new a(context, networkPermissionFactoryHolder), true);
        return networkPermissionFactoryHolder.instance;
    }
}
