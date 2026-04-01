package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements OnCompleteListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ NeuralTranslator e;

    public /* synthetic */ f(NeuralTranslator neuralTranslator, int i2) {
        this.d = i2;
        this.e = neuralTranslator;
    }

    public final void onComplete(Task task) {
        int i2 = this.d;
        NeuralTranslator neuralTranslator = this.e;
        switch (i2) {
            case 0:
                neuralTranslator.lambda$clear$3(task);
                return;
            case 1:
                neuralTranslator.lambda$refresh$5(task);
                return;
            default:
                neuralTranslator.lambda$clear$4(task);
                return;
        }
    }
}
