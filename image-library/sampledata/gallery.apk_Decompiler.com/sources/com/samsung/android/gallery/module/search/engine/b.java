package com.samsung.android.gallery.module.search.engine;

import android.database.Cursor;
import com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ Cursor[] d;
    public final /* synthetic */ IntelligentSearchEngine.SearchResult e;
    public final /* synthetic */ AtomicReference f;

    public /* synthetic */ b(Cursor[] cursorArr, IntelligentSearchEngine.SearchResult searchResult, AtomicReference atomicReference) {
        this.d = cursorArr;
        this.e = searchResult;
        this.f = atomicReference;
    }

    public final void run() {
        IntelligentExpandedSearchEngine.lambda$searchExpandedResult$3(this.d, this.e, this.f);
    }
}
