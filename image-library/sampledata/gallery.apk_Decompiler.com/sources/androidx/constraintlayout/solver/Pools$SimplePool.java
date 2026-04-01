package androidx.constraintlayout.solver;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Pools$SimplePool<T> implements Pools$Pool<T> {
    private final Object[] mPool;
    private int mPoolSize;

    public Pools$SimplePool(int i2) {
        if (i2 > 0) {
            this.mPool = new Object[i2];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    public T acquire() {
        int i2 = this.mPoolSize;
        if (i2 <= 0) {
            return null;
        }
        int i7 = i2 - 1;
        T[] tArr = this.mPool;
        T t = tArr[i7];
        tArr[i7] = null;
        this.mPoolSize = i2 - 1;
        return t;
    }

    public boolean release(T t) {
        int i2 = this.mPoolSize;
        Object[] objArr = this.mPool;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = t;
        this.mPoolSize = i2 + 1;
        return true;
    }

    public void releaseAll(T[] tArr, int i2) {
        if (i2 > tArr.length) {
            i2 = tArr.length;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            T t = tArr[i7];
            int i8 = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i8 < objArr.length) {
                objArr[i8] = t;
                this.mPoolSize = i8 + 1;
            }
        }
    }
}
