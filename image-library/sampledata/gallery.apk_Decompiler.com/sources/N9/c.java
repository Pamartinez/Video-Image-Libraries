package N9;

import com.samsung.android.gallery.module.publisher.retrieval.SQLiteRetrieval;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2850a;
    public final /* synthetic */ SQLiteRetrieval b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f2851c;
    public final /* synthetic */ boolean d;

    public /* synthetic */ c(SQLiteRetrieval sQLiteRetrieval, boolean z, boolean z3, int i2) {
        this.f2850a = i2;
        this.b = sQLiteRetrieval;
        this.f2851c = z;
        this.d = z3;
    }

    public final Object apply(Object obj) {
        switch (this.f2850a) {
            case 0:
                return this.b.lambda$getTimelineFileIds$12(this.f2851c, this.d, (String) obj);
            default:
                return this.b.lambda$getTimelineFileIds$14(this.f2851c, this.d, (String) obj);
        }
    }
}
