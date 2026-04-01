package androidx.core.util;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0011\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000bR\u001c\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"androidx/core/util/Pools$SimplePool", "", "T", "Landroidx/core/util/Pools$Pool;", "", "maxPoolSize", "<init>", "(I)V", "instance", "", "isInPool", "(Ljava/lang/Object;)Z", "acquire", "()Ljava/lang/Object;", "release", "", "pool", "[Ljava/lang/Object;", "poolSize", "I", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Pools$SimplePool<T> implements Pools$Pool<T> {
    private final Object[] pool;
    private int poolSize;

    public Pools$SimplePool(int i2) {
        if (i2 > 0) {
            this.pool = new Object[i2];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    private final boolean isInPool(T t) {
        int i2 = this.poolSize;
        for (int i7 = 0; i7 < i2; i7++) {
            if (this.pool[i7] == t) {
                return true;
            }
        }
        return false;
    }

    public T acquire() {
        int i2 = this.poolSize;
        if (i2 <= 0) {
            return null;
        }
        int i7 = i2 - 1;
        T t = this.pool[i7];
        j.c(t, "null cannot be cast to non-null type T of androidx.core.util.Pools.SimplePool");
        this.pool[i7] = null;
        this.poolSize--;
        return t;
    }

    public boolean release(T t) {
        j.e(t, "instance");
        if (!isInPool(t)) {
            int i2 = this.poolSize;
            Object[] objArr = this.pool;
            if (i2 >= objArr.length) {
                return false;
            }
            objArr[i2] = t;
            this.poolSize = i2 + 1;
            return true;
        }
        throw new IllegalStateException("Already in the pool!");
    }
}
