package com.samsung.android.sum.core.utils;

import android.os.Build;
import com.samsung.android.sum.core.SLog;
import gd.C0999a;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FPSTracer {
    private static final long CALCULATE_PERIOD_US;
    private static final String TAG = SLog.tagOf(FPSTracer.class);
    private static final long WINDOW_DURATION_US;
    private final Consumer<Integer> action;
    private final long calcPeriodUs;
    private long lastTimestampUs;
    private final List<Long> times;
    private final long windowPeriodUs;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        CALCULATE_PERIOD_US = timeUnit.toMicros(5);
        WINDOW_DURATION_US = timeUnit.toMicros(1);
    }

    public FPSTracer(Consumer<Integer> consumer) {
        this(CALCULATE_PERIOD_US, WINDOW_DURATION_US, consumer);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ long lambda$update$0(int i2) {
        return this.times.get(i2).longValue() - this.times.get(i2 - 1).longValue();
    }

    /* JADX WARNING: type inference failed for: r8v2, types: [java.util.function.LongBinaryOperator, java.lang.Object] */
    public void update(long j2) {
        long j3 = this.lastTimestampUs;
        if (j3 == 0 || j2 - j3 >= this.calcPeriodUs) {
            this.times.add(Long.valueOf(j2));
            if (Build.VERSION.SDK_INT >= 35 && j2 - ((Long) this.times.getFirst()).longValue() >= this.windowPeriodUs) {
                this.lastTimestampUs = ((Long) this.times.getLast()).longValue();
                String str = TAG;
                SLog.i(str, "calculate avg preview time diff over " + this.times.size());
                this.action.accept(Integer.valueOf((int) ((IntStream.range(1, this.times.size()).mapToLong(new C0999a(this)).reduce(new Object()).orElse(0) / ((long) this.times.size())) / 1000)));
                this.times.clear();
            }
        }
    }

    public FPSTracer(long j2, long j3, Consumer<Integer> consumer) {
        this.times = new ArrayList();
        this.lastTimestampUs = 0;
        this.calcPeriodUs = j2;
        this.windowPeriodUs = j3;
        this.action = consumer;
    }
}
