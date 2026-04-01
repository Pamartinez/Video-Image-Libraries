package androidx.appsearch.app;

import android.util.Log;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.core.util.ObjectsCompat;
import i.C0212a;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppSearchResult<ValueType> {
    private final String mErrorMessage;
    private final int mResultCode;
    private final ValueType mResultValue;

    private AppSearchResult(int i2, ValueType valuetype, String str) {
        this.mResultCode = i2;
        this.mResultValue = valuetype;
        this.mErrorMessage = str;
    }

    public static <ValueType> AppSearchResult<ValueType> newFailedResult(int i2, String str) {
        return new AppSearchResult<>(i2, (Object) null, str);
    }

    public static <ValueType> AppSearchResult<ValueType> newSuccessfulResult(ValueType valuetype) {
        return new AppSearchResult<>(0, valuetype, (String) null);
    }

    public static <ValueType> AppSearchResult<ValueType> throwableToFailedResult(Throwable th) {
        int i2;
        boolean z = th instanceof AppSearchException;
        if (!z || ((AppSearchException) th).getResultCode() != 6) {
            Log.w("AppSearchResult", "Converting throwable to failed result.", th);
        }
        if (z) {
            return ((AppSearchException) th).toAppSearchResult();
        }
        String simpleName = th.getClass().getSimpleName();
        if ((th instanceof IllegalStateException) || (th instanceof NullPointerException)) {
            i2 = 2;
        } else if (th instanceof IllegalArgumentException) {
            i2 = 3;
        } else if (th instanceof IOException) {
            i2 = 4;
        } else if (th instanceof SecurityException) {
            i2 = 8;
        } else {
            i2 = 1;
        }
        StringBuilder t = C0212a.t(simpleName, ": ");
        t.append(th.getMessage());
        return newFailedResult(i2, t.toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppSearchResult)) {
            return false;
        }
        AppSearchResult appSearchResult = (AppSearchResult) obj;
        if (this.mResultCode != appSearchResult.mResultCode || !ObjectsCompat.equals(this.mResultValue, appSearchResult.mResultValue) || !ObjectsCompat.equals(this.mErrorMessage, appSearchResult.mErrorMessage)) {
            return false;
        }
        return true;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public ValueType getResultValue() {
        if (isSuccess()) {
            return this.mResultValue;
        }
        throw new IllegalStateException("AppSearchResult is a failure: " + this);
    }

    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(this.mResultCode), this.mResultValue, this.mErrorMessage);
    }

    public boolean isSuccess() {
        if (getResultCode() == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        if (isSuccess()) {
            return "[SUCCESS]: " + this.mResultValue;
        }
        return "[FAILURE(" + this.mResultCode + ")]: " + this.mErrorMessage;
    }
}
