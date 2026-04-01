package W9;

import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.SearchEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ SearchEngine e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ SearchFilter g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2894h;

    public /* synthetic */ g(SearchEngine searchEngine, Cursor[] cursorArr, SearchFilter searchFilter, String str) {
        this.e = searchEngine;
        this.f = cursorArr;
        this.g = searchFilter;
        this.f2894h = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                String str = this.f2894h;
                this.e.lambda$searchForTimeline$1(this.f, str, this.g);
                return;
            default:
                this.e.lambda$searchForTimeline$2(this.f, this.g, this.f2894h);
                return;
        }
    }

    public /* synthetic */ g(SearchEngine searchEngine, Cursor[] cursorArr, String str, SearchFilter searchFilter) {
        this.e = searchEngine;
        this.f = cursorArr;
        this.f2894h = str;
        this.g = searchFilter;
    }
}
