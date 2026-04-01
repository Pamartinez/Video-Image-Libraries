package com.google.common.util.concurrent;

import He.F;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements ListenableFuture {
    public static final x e = new x(u.class);
    public final Object d;

    public u(VexFwkImageTranslatorV2.TranslationResult.Error.UnsupportedImageSize unsupportedImageSize) {
        this.d = unsupportedImageSize;
    }

    public final void addListener(Runnable runnable, Executor executor) {
        F.n(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (Exception e7) {
            Logger a7 = e.a();
            Level level = Level.SEVERE;
            a7.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e7);
        }
    }

    public final boolean cancel(boolean z) {
        return false;
    }

    public final Object get() {
        return this.d;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.d + "]]";
    }

    public final Object get(long j2, TimeUnit timeUnit) {
        timeUnit.getClass();
        return this.d;
    }
}
