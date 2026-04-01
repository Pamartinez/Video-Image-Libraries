package com.samsung.android.gallery.support.utils;

import java.util.function.ToIntFunction;

/* renamed from: com.samsung.android.gallery.support.utils.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0672j implements ToIntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3179a;

    public /* synthetic */ C0672j(int i2) {
        this.f3179a = i2;
    }

    public final int applyAsInt(Object obj) {
        String str = (String) obj;
        switch (this.f3179a) {
            case 0:
                return FileListHolder.lambda$truncate$0(str);
            default:
                return FileListHolder.lambda$truncate$1(str);
        }
    }
}
