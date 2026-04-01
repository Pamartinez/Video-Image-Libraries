package com.google.gson.internal;

import java.lang.reflect.Type;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ObjectConstructor {
    public final /* synthetic */ int d;
    public final /* synthetic */ Type e;

    public /* synthetic */ b(Type type, int i2) {
        this.d = i2;
        this.e = type;
    }

    public final Object construct() {
        int i2 = this.d;
        Type type = this.e;
        switch (i2) {
            case 0:
                return ConstructorConstructor.lambda$newSpecialCollectionConstructor$5(type);
            default:
                return ConstructorConstructor.lambda$newSpecialCollectionConstructor$6(type);
        }
    }
}
