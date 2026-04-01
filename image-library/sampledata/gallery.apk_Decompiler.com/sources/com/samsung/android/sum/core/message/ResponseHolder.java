package com.samsung.android.sum.core.message;

import android.os.ConditionVariable;
import com.samsung.android.sum.core.functional.PlaceHolder;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ResponseHolder implements PlaceHolder<Response> {
    private final ConditionVariable cv = new ConditionVariable();
    private final int requestCode;
    private Response response;
    private WeakReference<Request> weakRequest;

    public ResponseHolder(int i2) {
        this.requestCode = i2;
    }

    public Response await() {
        this.cv.block();
        return this.response;
    }

    public boolean contains() {
        if (this.response != null) {
            return true;
        }
        return false;
    }

    public Response get() {
        return this.response;
    }

    public int getCode() {
        return this.requestCode;
    }

    public boolean isEmpty() {
        if (this.response == null) {
            return true;
        }
        return false;
    }

    public boolean isNotEmpty() {
        if (this.response != null) {
            return true;
        }
        return false;
    }

    public void signal() {
        this.cv.open();
    }

    public void put(Response response2) {
        this.response = response2;
        WeakReference<Request> weakReference = this.weakRequest;
        if (weakReference != null && weakReference.get() != null && response2.replyTo == null) {
            response2.replyTo = this.weakRequest.get().replyTo;
        }
    }

    public Response reset() {
        Response response2 = this.response;
        this.response = null;
        return response2;
    }

    public Response await(int i2, TimeUnit timeUnit) {
        if (this.cv.block(timeUnit.toMillis((long) i2))) {
            return this.response;
        }
        return null;
    }

    public ResponseHolder(Request request) {
        this.requestCode = request.getCode();
        this.weakRequest = new WeakReference<>(request);
    }
}
