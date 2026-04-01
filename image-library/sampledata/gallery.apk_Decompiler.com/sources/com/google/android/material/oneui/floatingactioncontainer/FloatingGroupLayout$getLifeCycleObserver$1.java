package com.google.android.material.oneui.floatingactioncontainer;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import q2.u;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/google/android/material/oneui/floatingactioncontainer/FloatingGroupLayout$getLifeCycleObserver$1", "Landroidx/lifecycle/DefaultLifecycleObserver;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FloatingGroupLayout$getLifeCycleObserver$1 implements DefaultLifecycleObserver {
    public final /* synthetic */ u d;

    public FloatingGroupLayout$getLifeCycleObserver$1(u uVar) {
        this.d = uVar;
    }

    public final void onDestroy(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
        int i2 = u.f1890K;
        this.d.e();
        lifecycleOwner.getLifecycle().removeObserver(this);
    }
}
