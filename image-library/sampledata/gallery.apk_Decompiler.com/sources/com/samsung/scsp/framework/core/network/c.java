package com.samsung.scsp.framework.core.network;

import com.samsung.scsp.framework.core.network.NetworkPermissionFactoryLoader;
import java.util.Arrays;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4220a;

    public /* synthetic */ c(int i2) {
        this.f4220a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4220a) {
            case 0:
                return NetworkPermissionFactoryLoader.NetworkPermissionFactoryHolder.lambda$new$0((String) obj);
            case 1:
                return Arrays.stream(ResponseUtil.RESPONSIBLE_STATUS_ARRAY).anyMatch(new e(0, (Integer) obj));
            default:
                return Arrays.stream(ResponseUtil.REDIRECTED_STATUS_ARRAY).anyMatch(new e(1, (Integer) obj));
        }
    }
}
