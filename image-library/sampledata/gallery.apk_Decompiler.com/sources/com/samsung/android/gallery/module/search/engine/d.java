package com.samsung.android.gallery.module.search.engine;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ IntelligentSearchEngine d;
    public final /* synthetic */ AtomicReference e;
    public final /* synthetic */ SearchFilter f;
    public final /* synthetic */ IntelligentSearchEngine.SearchResult g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ QueryParams f3081h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ AtomicReference f3082i;

    public /* synthetic */ d(IntelligentSearchEngine intelligentSearchEngine, AtomicReference atomicReference, SearchFilter searchFilter, IntelligentSearchEngine.SearchResult searchResult, QueryParams queryParams, AtomicReference atomicReference2) {
        this.d = intelligentSearchEngine;
        this.e = atomicReference;
        this.f = searchFilter;
        this.g = searchResult;
        this.f3081h = queryParams;
        this.f3082i = atomicReference2;
    }

    public final void run() {
        this.d.lambda$searchResult$0(this.e, this.f, this.g, this.f3081h, this.f3082i);
    }
}
