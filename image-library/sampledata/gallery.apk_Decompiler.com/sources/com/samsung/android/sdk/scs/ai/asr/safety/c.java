package com.samsung.android.sdk.scs.ai.asr.safety;

import com.samsung.android.sdk.scs.ai.asr.safety.WatchDogImpl;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WatchDogCallback f1647a;

    public /* synthetic */ c(WatchDogCallback watchDogCallback) {
        this.f1647a = watchDogCallback;
    }

    public final boolean test(Object obj) {
        return ((WatchDogImpl.TimeOutCheck) obj).isMatched(this.f1647a);
    }
}
