package com.samsung.android.sdk.scs.ai.asr_6_0;

import com.samsung.android.scs.ai.sdkcommon.asr.ServerFeature;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ f(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return RemoteServiceExecutor.lambda$new$0();
            case 1:
                return RemoteServiceExecutor.lambda$new$1();
            case 2:
                return SpeechRecognizerControl.lambda$static$0();
            default:
                return Environment.getCurrentServerInfo(ServerFeature.DICTATION_ASR);
        }
    }
}
