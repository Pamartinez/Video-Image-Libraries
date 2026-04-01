package com.samsung.android.sdk.scs.ai.asr_6_0;

import android.content.SharedPreferences;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerFeature;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerType;
import com.samsung.android.sdk.scs.ai.asr_6_0.Repository;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1655a = 0;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f1656c;

    public /* synthetic */ g(ServerType serverType, String str) {
        this.f1656c = serverType;
        this.b = str;
    }

    public final Object apply(Object obj) {
        switch (this.f1655a) {
            case 0:
                return Repository.SharedPrefRepository.lambda$setServerType$0((ServerType) this.f1656c, this.b, (SharedPreferences.Editor) obj);
            default:
                return Repository.SharedPrefRepository.lambda$getServerType$2(this.b, (ServerFeature) this.f1656c, (SharedPreferences) obj);
        }
    }

    public /* synthetic */ g(String str, ServerFeature serverFeature) {
        this.b = str;
        this.f1656c = serverFeature;
    }
}
