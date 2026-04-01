package com.google.gson.internal;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.decorator.AbstractDecorator;
import java.lang.reflect.Constructor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ObjectConstructor, FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ Constructor d;

    public /* synthetic */ c(Constructor constructor) {
        this.d = constructor;
    }

    public Object construct() {
        return ConstructorConstructor.lambda$newDefaultConstructor$9(this.d);
    }

    public Object get() {
        return AbstractDecorator.lambda$new$1(this.d);
    }
}
