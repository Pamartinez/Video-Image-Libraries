package com.samsung.android.sdk.scs.ai.asr_6_0;

import com.samsung.android.scs.ai.sdkcommon.asr.BTCLocaleInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.LocaleInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerInfo;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1650a;

    public /* synthetic */ a(int i2) {
        this.f1650a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f1650a) {
            case 0:
                return Objects.nonNull((LocaleInfo) obj);
            case 1:
                return Objects.nonNull((BTCLocaleInfo) obj);
            case 2:
                return Environment.lambda$getSupportedBTCLocaleInfosFromCP$19((BTCLocaleInfo) obj);
            default:
                return Objects.nonNull((ServerInfo) obj);
        }
    }
}
