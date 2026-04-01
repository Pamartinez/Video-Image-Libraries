package com.samsung.scsp.framework.core;

import com.google.gson.annotations.SerializedName;
import com.samsung.scsp.framework.core.util.StringUtil;
import i.C0212a;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScspException extends Exception {
    public String errorString;
    public Map<String, List<String>> headers;
    @SerializedName("rcode")
    public int rcode;
    @SerializedName("rmsg")
    public String rmsg;
    public int status;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Code {
        public static final int BAD_IMPLEMENTATION = 80000000;
        public static final int CANCELED = 70000004;
        public static final int FORBIDDEN = 80300000;
        public static final int NETWORK_USAGE_CONSENT = 80300002;
        public static final int NOT_INITIALIZED = 60000002;
        public static final int NOT_SUPPORT_OS_VERSION = 70000001;
        public static final int NO_PERMISSION = 70000002;
        public static final int REGISTRATION_REQUIRED = 40001001;
        public static final int REQUEST_TIMEOUT = 80800000;
        public static final int RUNTIME_ENVIRONMENT = 60000000;
        public static final int RUNTIME_NETWORK_ERROR = 60000004;
        public static final int RUNTIME_POLICY = 70000000;
        public static final int UNAUTHENTICATED_FOR_SA = 40100001;
        public static final int UNAUTHENTICATED_FOR_SC = 40100002;
        public static final int UNAUTHENTICATED_FOR_SC_V1 = 101000;
    }

    public ScspException(int i2, String str, Throwable th) {
        super(str, th);
        this.rcode = i2;
        this.rmsg = str;
    }

    public static void throwIfEmpty(String str, String str2) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(Code.BAD_IMPLEMENTATION, str2);
        }
    }

    public static void throwIfNull(Object obj, String str) {
        if (obj == null) {
            throw new ScspException(Code.BAD_IMPLEMENTATION, str);
        }
    }

    public static void throwWhen(boolean z, String str) {
        if (z) {
            throw new ScspException(Code.BAD_IMPLEMENTATION, str);
        }
    }

    public String toString() {
        Locale locale = Locale.US;
        return C0212a.k(this.rcode, "[rcode] ", ", [rmsg]: ", this.rmsg);
    }

    public ScspException(int i2, String str) {
        super(str);
        this.rcode = i2;
        this.rmsg = str;
    }
}
