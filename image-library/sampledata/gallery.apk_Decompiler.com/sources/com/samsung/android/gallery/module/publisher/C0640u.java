package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* renamed from: com.samsung.android.gallery.module.publisher.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0640u implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3073a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0640u(int i2, Object obj) {
        this.f3073a = i2;
        this.b = obj;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f3073a;
        Object obj3 = this.b;
        switch (i2) {
            case 0:
                ((DataChangeEventPublisher) obj3).lambda$processDeferredChangeEvent$2((Integer) obj, (ArrayList) obj2);
                return;
            case 1:
                ((HashMap) obj3).put(Integer.valueOf(((String) obj2).hashCode()), (Integer) obj);
                return;
            default:
                CacheProviderHelper.cacheCursor((String) obj2, ((Cursor[]) obj3)[((Integer) obj).intValue()]);
                return;
        }
    }
}
