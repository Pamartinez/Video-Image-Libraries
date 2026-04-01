package W9;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.SearchApi;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2891a;
    public final /* synthetic */ QueryParams b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ SearchFilter f2892c;

    public /* synthetic */ a(QueryParams queryParams, SearchFilter searchFilter, int i2) {
        this.f2891a = i2;
        this.b = queryParams;
        this.f2892c = searchFilter;
    }

    public final Object apply(Object obj) {
        switch (this.f2891a) {
            case 0:
                return new SearchApi(this.b).searchDateItems((String) obj, this.f2892c);
            case 1:
                return new SearchApi(this.b).searchRealRatioItems((String) obj, this.f2892c);
            default:
                return new SearchApi(this.b).searchRealRatioItems((String) obj, this.f2892c);
        }
    }
}
