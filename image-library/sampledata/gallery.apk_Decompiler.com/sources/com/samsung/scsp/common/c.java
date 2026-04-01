package com.samsung.scsp.common;

import android.content.Context;
import android.os.Bundle;
import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;
    public final /* synthetic */ String f;

    public /* synthetic */ c(Context context, String str, int i2) {
        this.d = i2;
        this.e = context;
        this.f = str;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return this.e.getContentResolver().call(FeatureConfigurator.URI, "get", this.f, new Bundle());
            default:
                return FeatureConfigurator.lambda$getJson$2(this.e, this.f);
        }
    }
}
