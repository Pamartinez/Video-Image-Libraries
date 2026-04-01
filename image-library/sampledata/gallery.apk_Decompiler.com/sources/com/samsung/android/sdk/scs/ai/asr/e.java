package com.samsung.android.sdk.scs.ai.asr;

import com.samsung.android.sivs.ai.sdkcommon.asr.ServerFeature;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerType;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1641a;
    public final /* synthetic */ Object b;

    public /* synthetic */ e(int i2, Object obj) {
        this.f1641a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f1641a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((ExpiringData) obj2).lambda$getOrCompute$2(obj);
            case 1:
                return ((ServerInfo) obj).getName().equals(((ServerType) obj2).getName());
            default:
                return Environment.lambda$getSupportedServerInfos$12((ServerFeature) obj2, (ServerInfo) obj);
        }
    }
}
