package com.google.common.util.concurrent;

import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class m {
    public static /* synthetic */ boolean a(Unsafe unsafe, q qVar, long j2, Object obj, Object obj2) {
        while (!unsafe.compareAndSwapObject(qVar, j2, obj, obj2)) {
            if (unsafe.getObject(qVar, j2) != obj) {
                return false;
            }
        }
        return true;
    }
}
