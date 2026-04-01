package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t0 implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaDataStoriesV7 f2995a;
    public final /* synthetic */ Cursor b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ List f2996c;
    public final /* synthetic */ HashMap d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ t0(MediaDataStoriesV7 mediaDataStoriesV7, Cursor cursor, List list, HashMap hashMap, ArrayList arrayList) {
        this.f2995a = mediaDataStoriesV7;
        this.b = cursor;
        this.f2996c = list;
        this.d = hashMap;
        this.e = arrayList;
    }

    public final void accept(Object obj, Object obj2) {
        this.f2995a.lambda$loadDataPartial$7(this.b, this.f2996c, this.d, this.e, (String) obj, (ArrayList) obj2);
    }
}
