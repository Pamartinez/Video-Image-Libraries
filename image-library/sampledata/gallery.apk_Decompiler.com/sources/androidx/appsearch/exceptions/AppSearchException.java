package androidx.appsearch.exceptions;

import androidx.appsearch.app.AppSearchResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppSearchException extends Exception {
    private final int mResultCode;

    public AppSearchException(int i2, String str) {
        this(i2, str, (Throwable) null);
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public <T> AppSearchResult<T> toAppSearchResult() {
        return AppSearchResult.newFailedResult(this.mResultCode, getMessage());
    }

    public AppSearchException(int i2, String str, Throwable th) {
        super(str, th);
        this.mResultCode = i2;
    }
}
