package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class S implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2958a;
    public final /* synthetic */ HashMap b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaDataCursor f2959c;
    public final /* synthetic */ AbstractCollection d;
    public final /* synthetic */ Object e;

    public /* synthetic */ S(MediaDataCursor mediaDataCursor, HashMap hashMap, AbstractCollection abstractCollection, Object obj, int i2) {
        this.f2958a = i2;
        this.f2959c = mediaDataCursor;
        this.b = hashMap;
        this.d = abstractCollection;
        this.e = obj;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2958a) {
            case 0:
                ((MediaDataMxAlbum) this.f2959c).lambda$buildMergedNameList$14(this.b, (HashSet) this.d, (AtomicInteger) this.e, (Integer) obj, (ArrayList) obj2);
                return;
            default:
                ((MediaDataStoriesV7) this.f2959c).lambda$loadDataFull$3(this.b, (ArrayList) this.d, (Cursor) this.e, (String) obj, (ArrayList) obj2);
                return;
        }
    }
}
