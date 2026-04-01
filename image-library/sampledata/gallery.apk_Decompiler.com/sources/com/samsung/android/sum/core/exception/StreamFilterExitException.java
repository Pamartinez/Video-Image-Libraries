package com.samsung.android.sum.core.exception;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StreamFilterExitException extends IllegalStateException implements IntendedQuitException {
    public StreamFilterExitException() {
    }

    public StreamFilterExitException(String str) {
        super(str);
    }

    public StreamFilterExitException(String str, Throwable th) {
        super(str, th);
    }

    public StreamFilterExitException(Throwable th) {
        super(th);
    }
}
