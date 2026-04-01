package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TaskRunnable e;

    public /* synthetic */ c(TaskRunnable taskRunnable, int i2) {
        this.d = i2;
        this.e = taskRunnable;
    }

    public final void run() {
        int i2 = this.d;
        TaskRunnable taskRunnable = this.e;
        switch (i2) {
            case 0:
                ((NeuralTranslationByChunkRunnable) taskRunnable).lambda$execute$1();
                return;
            default:
                ((NeuralTranslationRunnable) taskRunnable).lambda$execute$0();
                return;
        }
    }
}
