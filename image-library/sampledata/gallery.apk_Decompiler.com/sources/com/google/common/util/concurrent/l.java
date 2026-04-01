package com.google.common.util.concurrent;

import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class l {
    public static /* synthetic */ boolean a(Unsafe unsafe, q qVar, long j2, C0126e eVar, C0126e eVar2) {
        while (!unsafe.compareAndSwapObject(qVar, j2, eVar, eVar2)) {
            if (unsafe.getObject(qVar, j2) != eVar) {
                return false;
            }
        }
        return true;
    }
}
