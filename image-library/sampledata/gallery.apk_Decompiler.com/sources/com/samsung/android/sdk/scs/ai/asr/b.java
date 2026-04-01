package com.samsung.android.sdk.scs.ai.asr;

import com.samsung.android.sivs.ai.sdkcommon.asr.BTCLocaleInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.LocaleInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerInfo;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1637a;

    public /* synthetic */ b(int i2) {
        this.f1637a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f1637a) {
            case 0:
                return Objects.nonNull((ArrayList) obj);
            case 1:
                return Objects.nonNull((LocaleInfo) obj);
            case 2:
                return Objects.nonNull((BTCLocaleInfo) obj);
            case 3:
                return Environment.lambda$getSupportedBTCLocaleInfosFromCP$22((BTCLocaleInfo) obj);
            default:
                return Objects.nonNull((ServerInfo) obj);
        }
    }
}
