package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationByChunkRunnable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ NeuralTranslationByChunkRunnable.AnonymousClass1 e;
    public final /* synthetic */ NeuralTranslationErrorCode f;

    public /* synthetic */ e(NeuralTranslationByChunkRunnable.AnonymousClass1 r1, NeuralTranslationErrorCode neuralTranslationErrorCode, int i2) {
        this.d = i2;
        this.e = r1;
        this.f = neuralTranslationErrorCode;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onFailure$2(this.f);
                return;
            default:
                this.e.lambda$onFailure$3(this.f);
                return;
        }
    }
}
