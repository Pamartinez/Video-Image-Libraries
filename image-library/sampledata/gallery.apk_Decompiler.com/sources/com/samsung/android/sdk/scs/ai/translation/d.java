package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationByChunkRunnable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ NeuralTranslationByChunkRunnable.AnonymousClass1 e;
    public final /* synthetic */ NeuralTranslationResult f;

    public /* synthetic */ d(NeuralTranslationByChunkRunnable.AnonymousClass1 r1, NeuralTranslationResult neuralTranslationResult, int i2) {
        this.d = i2;
        this.e = r1;
        this.f = neuralTranslationResult;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onSuccess$0(this.f);
                return;
            default:
                this.e.lambda$onSuccess$1(this.f);
                return;
        }
    }
}
