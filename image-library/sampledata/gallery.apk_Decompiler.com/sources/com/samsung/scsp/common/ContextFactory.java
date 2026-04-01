package com.samsung.scsp.common;

import T4.c;
import android.content.Context;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContextFactory {
    private static final ContextHolder contextHolder = new ContextHolder();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ContextHolder {
        /* access modifiers changed from: private */
        public Supplier<Context> applicationContext;
        /* access modifiers changed from: private */
        public Supplier<Context> baseContext;

        private ContextHolder() {
            this.baseContext = new a(0);
            this.applicationContext = new a(1);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Context lambda$new$0() {
            return null;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Context lambda$new$1() {
            return null;
        }
    }

    public static void attachApplicationContext(Context context) {
        ContextHolder contextHolder2 = contextHolder;
        Supplier unused = contextHolder2.applicationContext = new c(context, 2);
        Supplier unused2 = contextHolder2.baseContext = new c(context, 3);
    }

    public static void attachBaseContext(Context context) {
        Supplier unused = contextHolder.baseContext = new c(context, 1);
    }

    public static Context getApplicationContext() {
        return (Context) contextHolder.applicationContext.get();
    }

    public static Context getBaseContext() {
        return (Context) contextHolder.baseContext.get();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Context lambda$attachApplicationContext$1(Context context) {
        return context;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Context lambda$attachApplicationContext$2(Context context) {
        return context;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Context lambda$attachBaseContext$0(Context context) {
        return context;
    }
}
