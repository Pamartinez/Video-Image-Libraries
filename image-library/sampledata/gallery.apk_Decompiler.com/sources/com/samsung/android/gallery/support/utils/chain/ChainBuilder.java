package com.samsung.android.gallery.support.utils.chain;

import com.samsung.android.gallery.support.utils.chain.Chain;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChainBuilder<T extends Chain<T>> {
    private T mNext = null;
    private T mRet = null;

    public ChainBuilder<T> append(T t) {
        if (t != null) {
            if (this.mRet == null) {
                this.mRet = t;
                this.mNext = t;
                return this;
            }
            this.mNext.setNext(t);
            this.mNext = t;
        }
        return this;
    }

    public T build() {
        return this.mRet;
    }
}
