package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationRunnable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((NeuralTranslationByChunkRunnable) this.f).lambda$execute$0((NeuralTranslationResult) this.e);
                return;
            case 1:
                ((NeuralTranslationRunnable.AnonymousClass1) this.f).lambda$onSuccess$0((NeuralTranslationResult) this.e);
                return;
            default:
                ((NeuralTranslationRunnable.AnonymousClass1) this.f).lambda$onFailure$1((NeuralTranslationErrorCode) this.e);
                return;
        }
    }
}
