package W9;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.SearchHelper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchHelper e;
    public final /* synthetic */ SearchFilter f;
    public final /* synthetic */ String g;

    public /* synthetic */ h(SearchHelper searchHelper, SearchFilter searchFilter, String str, int i2) {
        this.d = i2;
        this.e = searchHelper;
        this.f = searchFilter;
        this.g = str;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$getQueryParamModifierForPeople$0(this.f, this.g, (QueryParams) obj);
                return;
            default:
                this.e.lambda$getQueryParamModifierForPet$1(this.f, this.g, (QueryParams) obj);
                return;
        }
    }
}
