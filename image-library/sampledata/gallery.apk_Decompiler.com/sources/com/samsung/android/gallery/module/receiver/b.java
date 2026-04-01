package com.samsung.android.gallery.module.receiver;

import android.os.Bundle;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Bundle f3076a;

    public /* synthetic */ b(Bundle bundle) {
        this.f3076a = bundle;
    }

    public final Object apply(Object obj) {
        return CloudSyncStatusReceiver.lambda$onReceive$0(this.f3076a, (String) obj);
    }
}
