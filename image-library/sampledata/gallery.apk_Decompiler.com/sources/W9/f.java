package W9;

import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.SearchEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ String f;
    public final /* synthetic */ SearchFilter g;

    public /* synthetic */ f(Cursor[] cursorArr, String str, SearchFilter searchFilter, int i2) {
        this.d = i2;
        this.e = cursorArr;
        this.f = str;
        this.g = searchFilter;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                SearchEngine.lambda$searchForTimeline$0(this.e, this.f, this.g);
                return;
            case 1:
                SearchEngine.lambda$searchForTimeline$4(this.e, this.f, this.g);
                return;
            default:
                SearchEngine.lambda$searchForTimeline$5(this.e, this.f, this.g);
                return;
        }
    }
}
