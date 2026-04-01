package com.samsung.android.sdk.sgpl.content.story;

import android.content.Context;
import android.database.Cursor;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1673a;
    public final /* synthetic */ Object b;

    public /* synthetic */ g(int i2, Object obj) {
        this.f1673a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f1673a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return Long.valueOf(Utils.getPackageVersion((Context) obj2, "com.samsung.android.providers.media"));
            default:
                return Integer.valueOf(((Cursor) obj2).getColumnIndex((String) obj));
        }
    }
}
