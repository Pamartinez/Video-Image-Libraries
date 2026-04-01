package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.SContextHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ApiContext {
    public Api api;
    public Object content;
    public String etag;
    public String invoker;
    public String name;
    public SCHashMap parameters = new SCHashMap();
    public String payload;
    public SContext scontext;
    public SContextHolder scontextHolder;

    private ApiContext(SContextHolder sContextHolder, String str) {
        this.scontextHolder = sContextHolder;
        this.scontext = sContextHolder.scontext;
        this.name = str;
    }

    public static ApiContext create(SContextHolder sContextHolder, String str) {
        return new ApiContext(sContextHolder, str);
    }
}
