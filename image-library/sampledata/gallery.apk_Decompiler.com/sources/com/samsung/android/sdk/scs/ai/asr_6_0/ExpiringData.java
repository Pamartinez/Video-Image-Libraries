package com.samsung.android.sdk.scs.ai.asr_6_0;

import android.os.Build;
import android.util.Log;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.scs.ai.asr.f;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ExpiringData<V> {
    protected static final Duration DEFAULT_DURATION;
    protected static final boolean isDevelop;
    private final String TAG;
    private Function<V, Boolean> checkValidate;
    private final AtomicReference<V> data;
    private final Supplier<V> defaultSupplier;
    private long lastTimeUpdate;
    private final String name;
    private final Duration timeout;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder<V> {
        private Function<V, Boolean> checker;
        private final Supplier<V> defaultHandler;
        private final String name;
        private Duration timeout = ExpiringData.DEFAULT_DURATION;

        public Builder(String str, Supplier<V> supplier) {
            this.name = str;
            this.defaultHandler = supplier;
        }

        public ExpiringData<V> build() {
            ExpiringData<V> expiringData = new ExpiringData<>(this.name, this.defaultHandler, this.timeout, 0);
            expiringData.setChecker(this.checker);
            return expiringData;
        }

        public Builder setChecker(Function<V, Boolean> function) {
            this.checker = function;
            return this;
        }

        public Builder setTimeout(Duration duration) {
            this.timeout = duration;
            return this;
        }
    }

    static {
        Duration duration;
        boolean equals = "user".equals(Build.TYPE);
        isDevelop = !equals;
        if (!equals) {
            duration = Duration.ofSeconds(30);
        } else {
            duration = Duration.ofMinutes(15);
        }
        DEFAULT_DURATION = duration;
    }

    public /* synthetic */ ExpiringData(String str, Supplier supplier, Duration duration, int i2) {
        this(str, supplier, duration);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$getOrCompute$1(Supplier supplier, Object obj) {
        Object orElse;
        String str;
        long currentTimeMillis = System.currentTimeMillis() - this.lastTimeUpdate;
        if ((obj != null && currentTimeMillis <= this.timeout.toMillis()) || (orElse = Optional.ofNullable(supplier).map(new f(26)).orElse((Object) null)) == null || !this.checkValidate.apply(orElse).booleanValue()) {
            return obj;
        }
        this.lastTimeUpdate = System.currentTimeMillis();
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.name);
        sb2.append(" has updated with ");
        sb2.append(Duration.ofMillis(currentTimeMillis));
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (obj == null) {
            str = "NEW";
        } else {
            str = "EXPIRED";
        }
        sb2.append(str);
        Log.i(str2, sb2.toString());
        return orElse;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getOrCompute$2(Object obj) {
        String str = this.TAG;
        Log.w(str, this.name + " return current value : " + Duration.ofMillis(System.currentTimeMillis() - this.lastTimeUpdate));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$getOrCompute$3() {
        String str = this.TAG;
        Log.w(str, this.name + " return default value : " + Duration.ofMillis(System.currentTimeMillis() - this.lastTimeUpdate));
        return this.defaultSupplier.get();
    }

    /* access modifiers changed from: private */
    public ExpiringData setChecker(Function<V, Boolean> function) {
        if (function != null) {
            this.checkValidate = function;
        }
        return this;
    }

    public V getOrCompute(Supplier<V> supplier) {
        return Optional.ofNullable(this.data.updateAndGet(new d(this, supplier))).filter(new e(0, this)).orElseGet(new h(5, this));
    }

    private ExpiringData(String str, Supplier<V> supplier, Duration duration) {
        this.TAG = "ExpiringData@" + hashCode();
        this.data = new AtomicReference<>((Object) null);
        this.checkValidate = new c(0);
        this.lastTimeUpdate = System.currentTimeMillis();
        this.name = str;
        this.defaultSupplier = supplier;
        this.timeout = duration;
    }
}
