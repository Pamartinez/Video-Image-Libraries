package com.samsung.scsp.error;

import com.samsung.scsp.error.ErrorSupplier;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ ErrorSupplier.ResultHolder d;
    public final /* synthetic */ Throwable e;

    public /* synthetic */ a(ErrorSupplier.ResultHolder resultHolder, Throwable th) {
        this.d = resultHolder;
        this.e = th;
    }

    public final void accept(Object obj) {
        Result unused = this.d.result = (Result) ((Function) ((Map.Entry) obj).getValue()).apply(this.e);
    }
}
