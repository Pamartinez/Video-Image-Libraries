package com.samsung.android.sdk.scs.ai.asr_6_0;

import android.content.Context;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ h(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return SpeechRecognizerControl.lambda$new$1((Context) obj);
            case 1:
                return Environment.getLangpackConfigInfoFromCP((Context) obj);
            case 2:
                return Environment.sFuncGetLocales.apply((Context) obj);
            case 3:
                return Environment.getSupportedBTCLocaleInfosFromCP((Context) obj);
            case 4:
                return Environment.getSupportedServerLists((Context) obj);
            default:
                return ((ExpiringData) obj).lambda$getOrCompute$3();
        }
    }
}
