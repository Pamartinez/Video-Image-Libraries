package com.samsung.android.sdk.scs.ai.gateway;

import android.os.IBinder;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiServiceFactory<T> implements IServiceFactory<T> {
    private final Function<IBinder, T> creator;

    public AiServiceFactory(Function<IBinder, T> function) {
        this.creator = function;
    }

    public T createService(IBinder iBinder) {
        return this.creator.apply(iBinder);
    }
}
