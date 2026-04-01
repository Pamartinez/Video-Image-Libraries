package com.samsung.scsp.framework.core.identity;

import com.google.gson.JsonObject;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ JsonObject e;

    public /* synthetic */ k(JsonObject jsonObject, int i2) {
        this.d = i2;
        this.e = jsonObject;
    }

    public final Object get() {
        int i2 = this.d;
        JsonObject jsonObject = this.e;
        switch (i2) {
            case 0:
                return RegistrationApiImpl.lambda$register$1(jsonObject);
            case 1:
                return TokenApiImpl.lambda$issue$0(jsonObject);
            default:
                return UpdateApiImpl.lambda$update$0(jsonObject);
        }
    }
}
