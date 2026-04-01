package N9;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.publisher.retrieval.SQLiteRetrieval;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2849h;

    public /* synthetic */ b(SQLiteRetrieval sQLiteRetrieval, String str, String str2, boolean z) {
        this.f = sQLiteRetrieval;
        this.g = str;
        this.f2849h = str2;
        this.e = z;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((SQLiteRetrieval) this.f).lambda$getQueryParamModifierForPeople$3((String) this.g, (String) this.f2849h, this.e, (QueryParams) obj);
                return;
            default:
                FileUtils.lambda$computeStorageList$2((List) this.f, (List) this.g, this.e, (List) this.f2849h, (StorageVolumeCompat) obj);
                return;
        }
    }

    public /* synthetic */ b(List list, List list2, boolean z, List list3) {
        this.f = list;
        this.g = list2;
        this.e = z;
        this.f2849h = list3;
    }
}
