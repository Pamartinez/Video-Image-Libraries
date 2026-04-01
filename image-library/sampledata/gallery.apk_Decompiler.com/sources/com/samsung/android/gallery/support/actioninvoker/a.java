package com.samsung.android.gallery.support.actioninvoker;

import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActionInvokeListener f3126a;

    public /* synthetic */ a(ActionInvokeListener actionInvokeListener) {
        this.f3126a = actionInvokeListener;
    }

    public final boolean test(Object obj) {
        return ActionInvoker.lambda$hasExclusive$2(this.f3126a, (ActionInvoker.ExclusiveListener) obj);
    }
}
