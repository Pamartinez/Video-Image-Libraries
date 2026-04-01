package com.samsung.scsp.error;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Response<T> extends Result {
    public final T obj;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder<T> {
        public Response<T> response = new Response<>(null);
    }

    public Response(T t) {
        this.obj = t;
    }

    public Response(T t, Result result) {
        super(result.rcode, result.rmsg);
        this.obj = t;
    }
}
