package com.samsung.android.gallery.module.search.engine;

import android.database.Cursor;
import com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ Cursor[] d;
    public final /* synthetic */ IntelligentSearchEngine.SearchResult e;

    public /* synthetic */ c(Cursor[] cursorArr, IntelligentSearchEngine.SearchResult searchResult) {
        this.d = cursorArr;
        this.e = searchResult;
    }

    public final void run() {
        IntelligentExpandedSearchEngine.lambda$searchExpandedResult$4(this.d, this.e);
    }
}
