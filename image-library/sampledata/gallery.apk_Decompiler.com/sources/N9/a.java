package N9;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.publisher.retrieval.SQLiteRetrieval;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SQLiteRetrieval e;
    public final /* synthetic */ String f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ a(SQLiteRetrieval sQLiteRetrieval, String str, boolean z, int i2) {
        this.d = i2;
        this.e = sQLiteRetrieval;
        this.f = str;
        this.g = z;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$getQueryParamModifier$0(this.f, this.g, (QueryParams) obj);
                return;
            case 1:
                this.e.lambda$getQueryParamModifierForGenerated$2(this.f, this.g, (QueryParams) obj);
                return;
            case 2:
                this.e.lambda$getQueryParamModifierForMultiplePeople$5(this.f, this.g, (QueryParams) obj);
                return;
            case 3:
                this.e.lambda$getQueryParamModifierForPet$4(this.f, this.g, (QueryParams) obj);
                return;
            default:
                this.e.lambda$getQueryParamModifierForShotMode$1(this.f, this.g, (QueryParams) obj);
                return;
        }
    }
}
