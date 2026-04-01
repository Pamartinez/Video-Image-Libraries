package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sdk.scs.base.tasks.Task;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LlmTranslationRequest e;
    public final /* synthetic */ Task f;

    public /* synthetic */ h(LlmTranslationRequest llmTranslationRequest, Task task, int i2) {
        this.d = i2;
        this.e = llmTranslationRequest;
        this.f = task;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                NeuralTranslator.lambda$translate$0(this.e, this.f);
                return;
            default:
                NeuralTranslator.lambda$translate$1(this.e, this.f);
                return;
        }
    }
}
