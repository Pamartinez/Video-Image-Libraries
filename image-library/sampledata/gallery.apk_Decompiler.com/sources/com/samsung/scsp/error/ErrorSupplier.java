package com.samsung.scsp.error;

import A4.I;
import com.samsung.android.sum.core.filter.n;
import com.samsung.scsp.framework.core.ScspException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ErrorSupplier {
    private static final Map<Class<?>, Function<Throwable, Result>> ERROR_SUPPLIER_MAP = new HashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ResultHolder {
        /* access modifiers changed from: private */
        public Result result;

        public ResultHolder(Throwable th) {
            this.result = new Result(0, "Not defined error. There is exception {" + th + "}");
        }
    }

    static {
        add(ScspException.class, new n(28));
    }

    public static void add(Class<?> cls, Function<Throwable, Result> function) {
        ERROR_SUPPLIER_MAP.put(cls, function);
    }

    public static Result get(Throwable th) {
        ResultHolder resultHolder = new ResultHolder(th);
        ERROR_SUPPLIER_MAP.entrySet().stream().filter(new I(26, th)).findFirst().ifPresent(new a(resultHolder, th));
        return resultHolder.result;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Result lambda$static$0(Throwable th) {
        ScspException scspException = (ScspException) th;
        return new Result(scspException.rcode, scspException.rmsg);
    }
}
