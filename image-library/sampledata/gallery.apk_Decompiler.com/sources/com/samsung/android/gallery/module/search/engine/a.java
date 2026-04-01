package com.samsung.android.gallery.module.search.engine;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ IntelligentSearchEngine e;
    public final /* synthetic */ SearchFilter f;
    public final /* synthetic */ Serializable g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ IntelligentSearchEngine.SearchResult f3079h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ QueryParams f3080i;

    /* JADX WARNING: type inference failed for: r6v0, types: [android.database.Cursor[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ a(com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2, com.samsung.android.gallery.database.dbtype.SearchFilter r3, com.samsung.android.gallery.module.search.engine.IntelligentExpandedSearchEngine r4, com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine.SearchResult r5, android.database.Cursor[] r6) {
        /*
            r1 = this;
            r0 = 1
            r1.d = r0
            r1.<init>()
            r1.e = r4
            r1.f = r3
            r1.g = r6
            r1.f3079h = r5
            r1.f3080i = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.search.engine.a.<init>(com.samsung.android.gallery.database.dal.abstraction.query.QueryParams, com.samsung.android.gallery.database.dbtype.SearchFilter, com.samsung.android.gallery.module.search.engine.IntelligentExpandedSearchEngine, com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine$SearchResult, android.database.Cursor[]):void");
    }

    public final void run() {
        switch (this.d) {
            case 0:
                IntelligentSearchEngine.SearchResult searchResult = this.f3079h;
                ((IntelligentExpandedSearchEngine) this.e).lambda$searchExpandedResult$0((Cursor[]) this.g, this.f, searchResult, this.f3080i);
                return;
            case 1:
                IntelligentSearchEngine.SearchResult searchResult2 = this.f3079h;
                ((IntelligentExpandedSearchEngine) this.e).lambda$searchExpandedResult$1(this.f, (Cursor[]) this.g, searchResult2, this.f3080i);
                return;
            case 2:
                IntelligentSearchEngine.SearchResult searchResult3 = this.f3079h;
                ((IntelligentExpandedSearchEngine) this.e).lambda$searchExpandedResult$2((Cursor[]) this.g, this.f, searchResult3, this.f3080i);
                return;
            default:
                IntelligentSearchEngine.SearchResult searchResult4 = this.f3079h;
                QueryParams queryParams = this.f3080i;
                this.e.lambda$searchResult$1((AtomicReference) this.g, this.f, searchResult4, queryParams);
                return;
        }
    }

    public /* synthetic */ a(IntelligentSearchEngine intelligentSearchEngine, Serializable serializable, SearchFilter searchFilter, IntelligentSearchEngine.SearchResult searchResult, QueryParams queryParams, int i2) {
        this.d = i2;
        this.e = intelligentSearchEngine;
        this.g = serializable;
        this.f = searchFilter;
        this.f3079h = searchResult;
        this.f3080i = queryParams;
    }
}
