package com.glidebitmappool.internal;

import com.glidebitmappool.internal.Poolable;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class BaseKeyPool<T extends Poolable> {
    private final Queue<T> keyPool = Util.createQueue(20);

    public abstract T create();

    public T get() {
        T t = (Poolable) this.keyPool.poll();
        if (t == null) {
            return create();
        }
        return t;
    }

    public void offer(T t) {
        if (this.keyPool.size() < 20) {
            this.keyPool.offer(t);
        }
    }
}
