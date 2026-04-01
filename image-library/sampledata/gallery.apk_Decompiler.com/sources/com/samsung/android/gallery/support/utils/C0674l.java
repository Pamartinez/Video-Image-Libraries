package com.samsung.android.gallery.support.utils;

import java.io.File;
import java.util.function.ToLongFunction;

/* renamed from: com.samsung.android.gallery.support.utils.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0674l implements ToLongFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3180a;

    public /* synthetic */ C0674l(int i2) {
        this.f3180a = i2;
    }

    public final long applyAsLong(Object obj) {
        switch (this.f3180a) {
            case 0:
                return FileUtils.lambda$size$8((File) obj);
            default:
                return (long) ((String) obj).length();
        }
    }
}
