package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import java.util.HashMap;
import java.util.function.UnaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements UnaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StoriesPinData f2932a;
    public final /* synthetic */ Cursor b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ HashMap f2933c;

    public /* synthetic */ l(StoriesPinData storiesPinData, Cursor cursor, HashMap hashMap) {
        this.f2932a = storiesPinData;
        this.b = cursor;
        this.f2933c = hashMap;
    }

    public final Object apply(Object obj) {
        return StoriesPinCache.lambda$loadComplete$0(this.f2932a, this.b, this.f2933c, (MediaItem) obj);
    }
}
