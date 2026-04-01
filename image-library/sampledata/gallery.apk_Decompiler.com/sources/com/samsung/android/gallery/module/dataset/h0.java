package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import java.util.function.BiFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h0 implements BiFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2980a;
    public final /* synthetic */ MediaDataSearch b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Cursor[] f2981c;

    public /* synthetic */ h0(MediaDataSearch mediaDataSearch, Cursor[] cursorArr, int i2) {
        this.f2980a = i2;
        this.b = mediaDataSearch;
        this.f2981c = cursorArr;
    }

    public final Object apply(Object obj, Object obj2) {
        Integer num = (Integer) obj;
        String str = (String) obj2;
        switch (this.f2980a) {
            case 0:
                return this.b.lambda$swap$0(this.f2981c, num, str);
            default:
                return this.b.lambda$swapPartialCursors$2(this.f2981c, num, str);
        }
    }
}
