package com.samsung.android.sum.core.functional;

import android.os.ConditionVariable;
import com.samsung.android.sum.core.Def;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CountLatch {
    private final String TAG = Def.tagOf((Class<?>) CountLatch.class);
    protected AtomicInteger counter;
    protected ConditionVariable cv;
    private int initValue;
    protected int openValue = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CountDownLatch extends CountLatch {
        public CountDownLatch(int i2) {
            super(i2);
        }

        public int countDown() {
            int decrementAndGet = this.counter.decrementAndGet();
            if (decrementAndGet == this.openValue) {
                this.cv.open();
            }
            return decrementAndGet;
        }

        public int countUp() {
            int incrementAndGet = this.counter.incrementAndGet();
            if (incrementAndGet == this.openValue + 1) {
                this.cv.close();
            }
            return incrementAndGet;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CountUpLatch extends CountLatch {
        public CountUpLatch(int i2) {
            super(i2);
        }

        public int countDown() {
            int decrementAndGet = this.counter.decrementAndGet();
            if (decrementAndGet == this.openValue - 1) {
                this.cv.close();
            }
            return decrementAndGet;
        }

        public int countUp() {
            int incrementAndGet = this.counter.incrementAndGet();
            if (incrementAndGet == this.openValue) {
                this.cv.open();
            }
            return incrementAndGet;
        }
    }

    public CountLatch(int i2) {
        this.initValue = i2;
        init();
    }

    public static CountLatch countDownOf() {
        return new CountDownLatch(0);
    }

    public static CountLatch countUpOf() {
        return new CountUpLatch(0);
    }

    private void init() {
        this.counter = new AtomicInteger(this.initValue);
        this.cv = new ConditionVariable(false);
    }

    public void block() {
        if (this.counter.get() != this.openValue) {
            this.cv.block();
        } else {
            this.cv.open();
        }
    }

    public abstract int countDown();

    public abstract int countUp();

    public void release() {
        this.counter = null;
        this.cv = null;
    }

    public void reset() {
        this.initValue = 0;
        this.counter = null;
        this.cv.close();
    }

    public CountLatch setOpenValue(int i2) {
        this.openValue = i2;
        return this;
    }

    public static CountLatch countDownOf(int i2) {
        return new CountDownLatch(i2);
    }

    public static CountLatch countUpOf(int i2) {
        return new CountUpLatch(i2);
    }

    public void block(long j2) {
        if (this.counter.get() != this.openValue) {
            this.cv.block(j2);
        } else {
            this.cv.open();
        }
    }
}
