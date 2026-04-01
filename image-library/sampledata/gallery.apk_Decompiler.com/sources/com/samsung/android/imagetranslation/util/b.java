package com.samsung.android.imagetranslation.util;

import java.util.function.BinaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BinaryOperator {
    public final Object apply(Object obj, Object obj2) {
        return Integer.valueOf(Integer.sum(((Integer) obj).intValue(), ((Integer) obj2).intValue()));
    }
}
