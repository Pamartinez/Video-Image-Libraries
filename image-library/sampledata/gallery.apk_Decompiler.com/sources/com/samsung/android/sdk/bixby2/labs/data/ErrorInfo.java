package com.samsung.android.sdk.bixby2.labs.data;

import c0.C0086a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/sdk/bixby2/labs/data/ErrorInfo;", "", "type", "", "stackTrace", "(Ljava/lang/String;Ljava/lang/String;)V", "getStackTrace", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "bixbyappsdk_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ErrorInfo {
    private final String stackTrace;
    private final String type;

    public ErrorInfo(String str, String str2) {
        this.type = str;
        this.stackTrace = str2;
    }

    public static /* synthetic */ ErrorInfo copy$default(ErrorInfo errorInfo, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = errorInfo.type;
        }
        if ((i2 & 2) != 0) {
            str2 = errorInfo.stackTrace;
        }
        return errorInfo.copy(str, str2);
    }

    public final String component1() {
        return this.type;
    }

    public final String component2() {
        return this.stackTrace;
    }

    public final ErrorInfo copy(String str, String str2) {
        return new ErrorInfo(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ErrorInfo)) {
            return false;
        }
        ErrorInfo errorInfo = (ErrorInfo) obj;
        if (j.a(this.type, errorInfo.type) && j.a(this.stackTrace, errorInfo.stackTrace)) {
            return true;
        }
        return false;
    }

    public final String getStackTrace() {
        return this.stackTrace;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int i2;
        String str = this.type;
        int i7 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i8 = i2 * 31;
        String str2 = this.stackTrace;
        if (str2 != null) {
            i7 = str2.hashCode();
        }
        return i8 + i7;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ErrorInfo(type=");
        sb2.append(this.type);
        sb2.append(", stackTrace=");
        return C0086a.m(sb2, this.stackTrace, ')');
    }
}
