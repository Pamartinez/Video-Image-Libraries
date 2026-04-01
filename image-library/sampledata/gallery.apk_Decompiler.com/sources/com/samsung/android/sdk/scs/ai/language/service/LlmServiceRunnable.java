package com.samsung.android.sdk.scs.ai.language.service;

import android.os.Bundle;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.Result;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LlmServiceRunnable<T> extends TaskRunnable<T> {
    private static final String TAG = "LlmServiceRunnable";
    private final String featureName;
    private final LlmServiceObserver2 observer;
    /* access modifiers changed from: private */
    public final Function<List<Bundle>, T> resultMapper;
    private final Consumer<LlmServiceObserver2> serviceRequest;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LlmServiceRunnable(java.lang.String r1, boolean r2, java.util.function.Consumer<com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2> r3, java.util.function.Function<java.util.List<android.os.Bundle>, T> r4) {
        /*
            r0 = this;
            if (r2 == 0) goto L_0x0008
            com.samsung.android.sdk.scs.base.tasks.TaskStreamingCompletionSource r2 = new com.samsung.android.sdk.scs.base.tasks.TaskStreamingCompletionSource
            r2.<init>()
            goto L_0x000d
        L_0x0008:
            com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource r2 = new com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource
            r2.<init>()
        L_0x000d:
            r0.<init>(r2)
            com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable$1 r2 = new com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable$1
            r2.<init>()
            r0.observer = r2
            r0.featureName = r1
            r0.serviceRequest = r3
            r0.resultMapper = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable.<init>(java.lang.String, boolean, java.util.function.Consumer, java.util.function.Function):void");
    }

    public static List<Result> listResultMapper(List<Bundle> list) {
        return (List) list.stream().map(new C0431a(24)).collect(Collectors.toList());
    }

    public static Result singleResultMapper(List<Bundle> list) {
        if (list.isEmpty()) {
            return new Result(new Bundle());
        }
        return new Result(list.get(0));
    }

    public void execute() {
        this.serviceRequest.accept(this.observer);
    }

    public String getFeatureName() {
        return this.featureName;
    }
}
