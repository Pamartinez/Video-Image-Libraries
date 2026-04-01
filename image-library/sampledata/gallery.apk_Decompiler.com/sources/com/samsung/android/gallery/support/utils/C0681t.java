package com.samsung.android.gallery.support.utils;

import java.util.StringJoiner;
import java.util.function.LongConsumer;

/* renamed from: com.samsung.android.gallery.support.utils.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0681t implements LongConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StringJoiner f3187a;

    public /* synthetic */ C0681t(StringJoiner stringJoiner) {
        this.f3187a = stringJoiner;
    }

    public final void accept(long j2) {
        this.f3187a.add(String.valueOf(j2));
    }
}
