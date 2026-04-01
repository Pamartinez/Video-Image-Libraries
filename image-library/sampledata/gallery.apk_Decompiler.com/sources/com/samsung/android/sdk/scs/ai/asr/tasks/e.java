package com.samsung.android.sdk.scs.ai.asr.tasks;

import android.os.Bundle;
import com.samsung.android.sdk.scs.ai.asr.tasks.SttRecognitionTask;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ SttRecognitionTask.ListenerWrapper d;
    public final /* synthetic */ Bundle e;

    public /* synthetic */ e(SttRecognitionTask.ListenerWrapper listenerWrapper, Bundle bundle) {
        this.d = listenerWrapper;
        this.e = bundle;
    }

    public final void accept(Object obj) {
        this.d.lambda$checkFileUriResult$0(this.e, (String) obj);
    }
}
