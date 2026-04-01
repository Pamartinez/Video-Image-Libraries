package com.samsung.scsp.framework.core.identity;

import i.C0212a;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;

    public /* synthetic */ l(String str, int i2) {
        this.d = i2;
        this.e = str;
    }

    public final Object get() {
        int i2 = this.d;
        String str = this.e;
        switch (i2) {
            case 0:
                return RegistrationApiImpl.lambda$register$0(str);
            default:
                return C0212a.l("e2eeType = ", str);
        }
    }
}
