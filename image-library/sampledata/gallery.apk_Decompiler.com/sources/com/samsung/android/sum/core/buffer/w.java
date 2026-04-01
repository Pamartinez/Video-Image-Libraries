package com.samsung.android.sum.core.buffer;

import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaBufferBase f4059a;

    public /* synthetic */ w(MediaBufferBase mediaBufferBase) {
        this.f4059a = mediaBufferBase;
    }

    public final boolean test(int i2) {
        return this.f4059a.lambda$containFlags$0(i2);
    }
}
