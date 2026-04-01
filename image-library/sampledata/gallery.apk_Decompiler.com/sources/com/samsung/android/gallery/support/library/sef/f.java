package com.samsung.android.gallery.support.library.sef;

import java.io.RandomAccessFile;
import java.util.function.BiFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements BiFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3147a;
    public final /* synthetic */ String b;

    public /* synthetic */ f(String str, int i2) {
        this.f3147a = i2;
        this.b = str;
    }

    public final Object apply(Object obj, Object obj2) {
        int i2 = this.f3147a;
        RandomAccessFile randomAccessFile = (RandomAccessFile) obj;
        SefData sefData = (SefData) obj2;
        String str = this.b;
        switch (i2) {
            case 0:
                return Boolean.valueOf(str.equals(sefData.name));
            default:
                return Boolean.valueOf(str.equals(sefData.name));
        }
    }
}
