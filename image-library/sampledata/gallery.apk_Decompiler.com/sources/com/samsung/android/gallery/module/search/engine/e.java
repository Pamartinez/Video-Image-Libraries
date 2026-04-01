package com.samsung.android.gallery.module.search.engine;

import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ IntelligentSearchEngine d;
    public final /* synthetic */ AtomicReference e;
    public final /* synthetic */ AtomicReference f;
    public final /* synthetic */ Cursor[][] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ SearchFilter f3083h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ AtomicReference f3084i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ IntelligentSearchEngine.SearchResult f3085j;
    public final /* synthetic */ long k;

    public /* synthetic */ e(IntelligentSearchEngine intelligentSearchEngine, AtomicReference atomicReference, AtomicReference atomicReference2, Cursor[][] cursorArr, SearchFilter searchFilter, AtomicReference atomicReference3, IntelligentSearchEngine.SearchResult searchResult, long j2) {
        this.d = intelligentSearchEngine;
        this.e = atomicReference;
        this.f = atomicReference2;
        this.g = cursorArr;
        this.f3083h = searchFilter;
        this.f3084i = atomicReference3;
        this.f3085j = searchResult;
        this.k = j2;
    }

    public final void run() {
        this.d.lambda$searchResult$2(this.e, this.f, this.g, this.f3083h, this.f3084i, this.f3085j, this.k);
    }
}
