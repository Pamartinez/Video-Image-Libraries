package com.samsung.android.gallery.support.utils;

import android.os.Handler;
import android.text.TextUtils;
import c0.C0086a;
import i.C0212a;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ValueTrigger<V> {
    protected final StringCompat TAG;
    private final Handler mHandler;
    private final V mInitial;
    private final AtomicBoolean mSetting;
    private final ArrayList<Trigger> mTriggerList;
    private V mValue;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Condition<V> {
        private final Function<V, Boolean> mCondition;
        private final ValueTrigger<V> mParent;

        public Condition(ValueTrigger<V> valueTrigger, Function<V, Boolean> function) {
            this.mParent = valueTrigger;
            this.mCondition = function;
        }

        public void then(Runnable runnable, String str) {
            this.mParent.setTrigger(this.mCondition, runnable, str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Trigger<V> {
        /* access modifiers changed from: private */
        public final Runnable mAction;
        private final Function<V, Boolean> mCondition;
        private final String mTag;

        public Trigger(Function<V, Boolean> function, Runnable runnable, String str) {
            this.mCondition = function;
            this.mAction = runnable;
            this.mTag = str;
        }

        public boolean match(V v) {
            return this.mCondition.apply(v).booleanValue();
        }
    }

    public ValueTrigger(V v, String str) {
        this(v, str, (Handler) null);
    }

    public synchronized void clear() {
        clearTrigger();
        this.mValue = this.mInitial;
    }

    public synchronized void clearTrigger() {
        synchronized (this.mTriggerList) {
            this.mTriggerList.clear();
        }
    }

    public synchronized V get() {
        return this.mValue;
    }

    public void runTrigger(Runnable runnable) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(runnable);
        } else {
            runnable.run();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void set(V r6) {
        /*
            r5 = this;
            java.lang.String r0 = "should not change status while changing "
            monitor-enter(r5)
            java.util.concurrent.atomic.AtomicBoolean r1 = r5.mSetting     // Catch:{ all -> 0x0055 }
            r2 = 1
            boolean r1 = r1.getAndSet(r2)     // Catch:{ all -> 0x0055 }
            if (r1 != 0) goto L_0x0061
            r5.mValue = r6     // Catch:{ all -> 0x0055 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0055 }
            r0.<init>()     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.ValueTrigger$Trigger> r1 = r5.mTriggerList     // Catch:{ all -> 0x0055 }
            monitor-enter(r1)     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.ValueTrigger$Trigger> r2 = r5.mTriggerList     // Catch:{ all -> 0x0035 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0035 }
        L_0x001d:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0035 }
            if (r3 == 0) goto L_0x0037
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0035 }
            com.samsung.android.gallery.support.utils.ValueTrigger$Trigger r3 = (com.samsung.android.gallery.support.utils.ValueTrigger.Trigger) r3     // Catch:{ all -> 0x0035 }
            if (r3 == 0) goto L_0x001d
            boolean r4 = r3.match(r6)     // Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x001d
            r0.add(r3)     // Catch:{ all -> 0x0035 }
            goto L_0x001d
        L_0x0035:
            r6 = move-exception
            goto L_0x005f
        L_0x0037:
            java.util.ArrayList<com.samsung.android.gallery.support.utils.ValueTrigger$Trigger> r6 = r5.mTriggerList     // Catch:{ all -> 0x0035 }
            r6.removeAll(r0)     // Catch:{ all -> 0x0035 }
            monitor-exit(r1)     // Catch:{ all -> 0x0035 }
            java.util.Iterator r6 = r0.iterator()     // Catch:{ all -> 0x0055 }
        L_0x0041:
            boolean r0 = r6.hasNext()     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0057
            java.lang.Object r0 = r6.next()     // Catch:{ all -> 0x0055 }
            com.samsung.android.gallery.support.utils.ValueTrigger$Trigger r0 = (com.samsung.android.gallery.support.utils.ValueTrigger.Trigger) r0     // Catch:{ all -> 0x0055 }
            java.lang.Runnable r0 = r0.mAction     // Catch:{ all -> 0x0055 }
            r5.runTrigger(r0)     // Catch:{ all -> 0x0055 }
            goto L_0x0041
        L_0x0055:
            r6 = move-exception
            goto L_0x0087
        L_0x0057:
            java.util.concurrent.atomic.AtomicBoolean r6 = r5.mSetting     // Catch:{ all -> 0x0055 }
            r0 = 0
            r6.set(r0)     // Catch:{ all -> 0x0055 }
            monitor-exit(r5)
            return
        L_0x005f:
            monitor-exit(r1)     // Catch:{ all -> 0x0035 }
            throw r6     // Catch:{ all -> 0x0055 }
        L_0x0061:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x0055 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0055 }
            r2.<init>(r0)     // Catch:{ all -> 0x0055 }
            V r0 = r5.mValue     // Catch:{ all -> 0x0055 }
            r2.append(r0)     // Catch:{ all -> 0x0055 }
            java.lang.String r0 = " -> "
            r2.append(r0)     // Catch:{ all -> 0x0055 }
            r2.append(r6)     // Catch:{ all -> 0x0055 }
            java.lang.String r6 = " / "
            r2.append(r6)     // Catch:{ all -> 0x0055 }
            com.samsung.android.gallery.support.utils.StringCompat r6 = r5.TAG     // Catch:{ all -> 0x0055 }
            r2.append(r6)     // Catch:{ all -> 0x0055 }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x0055 }
            r1.<init>(r6)     // Catch:{ all -> 0x0055 }
            throw r1     // Catch:{ all -> 0x0055 }
        L_0x0087:
            monitor-exit(r5)     // Catch:{ all -> 0x0055 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.ValueTrigger.set(java.lang.Object):void");
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }

    public void setTrigger(Function<V, Boolean> function, Runnable runnable, String str) {
        if (function.apply(this.mValue).booleanValue()) {
            runTrigger(runnable);
            return;
        }
        synchronized (this.mTriggerList) {
            this.mTriggerList.add(new Trigger(function, runnable, str));
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("value = ");
        sb2.append(this.mValue);
        sb2.append(" / trigger count = ");
        C0086a.A(sb2, this.mTriggerList, " / ");
        sb2.append(super.toString());
        return sb2.toString();
    }

    public synchronized Condition<V> when(V v) {
        return new Condition<>(this, new r(5, v));
    }

    public ValueTrigger(V v, Handler handler) {
        this(v, (String) null, handler);
    }

    public synchronized Condition<V> when(Function<V, Boolean> function) {
        return new Condition<>(this, function);
    }

    public ValueTrigger(V v, String str, Handler handler) {
        this.mTriggerList = new ArrayList<>();
        this.mSetting = new AtomicBoolean(false);
        if (!TextUtils.isEmpty(str)) {
            this.TAG = new StringCompat(C0212a.l("Trigger.", str));
        } else {
            this.TAG = new StringCompat("Trigger.".concat(v.getClass().getSimpleName()));
        }
        this.mInitial = v;
        this.mValue = v;
        this.mHandler = handler;
    }
}
