package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.error.FaultBarrier;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements FaultBarrier.ThrowableSupplier {
    public final Object get() {
        return Locale.getDefault().toLanguageTag();
    }
}
