package com.samsung.android.gallery.support.utils;

import java.util.Comparator;

/* renamed from: com.samsung.android.gallery.support.utils.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0669g implements Comparator {
    public final /* synthetic */ DataCounter d;

    public /* synthetic */ C0669g(DataCounter dataCounter) {
        this.d = dataCounter;
    }

    public final int compare(Object obj, Object obj2) {
        return this.d.lambda$getSortedList$0(obj, obj2);
    }
}
