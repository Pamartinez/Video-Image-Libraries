package com.samsung.android.sdk.mobileservice.social.social.result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OpenSessionResult<T> {
    private final T data;
    private final int result;

    public OpenSessionResult(int i2, T t) {
        this.result = i2;
        this.data = t;
    }

    public T getData() {
        return this.data;
    }

    public int getResult() {
        return this.result;
    }
}
