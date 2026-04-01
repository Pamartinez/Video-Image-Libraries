package com.samsung.android.sdk.scs.ai.asr_6_0;

import android.content.Context;
import android.os.Bundle;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f1651a;

    public /* synthetic */ b(Context context) {
        this.f1651a = context;
    }

    public final Object apply(Object obj) {
        return Environment.callContentProvider(this.f1651a, "get_locale_list", (String) null, (Bundle) obj);
    }
}
