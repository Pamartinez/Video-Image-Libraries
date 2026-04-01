package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EmptyJobImpl implements Job {
    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners) {
        return null;
    }

    public String getName() {
        return null;
    }

    public void execute(ApiContext apiContext, Listeners listeners) {
    }
}
