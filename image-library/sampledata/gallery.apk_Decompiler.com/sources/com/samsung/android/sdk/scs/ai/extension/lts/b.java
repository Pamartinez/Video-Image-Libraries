package com.samsung.android.sdk.scs.ai.extension.lts;

import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import java.util.concurrent.CompletableFuture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements OnCompleteListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LongTextSummarizer e;
    public final /* synthetic */ CompletableFuture f;

    public /* synthetic */ b(LongTextSummarizer longTextSummarizer, CompletableFuture completableFuture, int i2) {
        this.d = i2;
        this.e = longTextSummarizer;
        this.f = completableFuture;
    }

    public final void onComplete(Task task) {
        switch (this.d) {
            case 0:
                this.e.lambda$process$0(this.f, task);
                return;
            case 1:
                this.e.lambda$process$1(this.f, task);
                return;
            default:
                this.e.lambda$summarize$4(this.f, task);
                return;
        }
    }
}
