package com.samsung.scsp.framework.core.api;

import android.util.Pair;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Job {
    HttpRequest createRequest(ApiContext apiContext, Listeners listeners);

    void execute(ApiContext apiContext, Listeners listeners);

    String getName();

    void attachHeaderFunction(Function<ApiContext, Pair<String, String>[]> function) {
    }

    void attachJournalSupplier(Supplier<Pair<String, String>> supplier) {
    }

    void attachProperties(Property[] propertyArr) {
    }

    void attachUrlFunction(BiFunction<ApiContext, String, String> biFunction) {
    }
}
