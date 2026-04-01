package com.samsung.android.sum.core.functional;

import android.content.Context;

@FunctionalInterface
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ModelLoader<T> {
    T load(Context context, String str);
}
