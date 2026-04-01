package com.samsung.scsp.framework.core.identity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class InfoManager<T> {
    public abstract boolean accept(T t);

    public abstract T make(T t);

    public abstract void updateCache(T t);
}
