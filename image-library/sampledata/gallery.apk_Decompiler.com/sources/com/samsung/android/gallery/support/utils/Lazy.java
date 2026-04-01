package com.samsung.android.gallery.support.utils;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Lazy<T> implements Supplier<T> {
    private final Supplier<T> mSupplier;
    private volatile T mValue;

    public Lazy(Supplier<T> supplier) {
        this.mSupplier = supplier;
    }

    public synchronized T get() {
        try {
            if (this.mValue == null) {
                this.mValue = this.mSupplier.get();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mValue;
    }

    public synchronized boolean isEmpty() {
        boolean z;
        if (this.mValue == null) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized void remove() {
        this.mValue = null;
    }
}
