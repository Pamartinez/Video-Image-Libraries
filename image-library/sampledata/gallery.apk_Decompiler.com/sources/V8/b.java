package V8;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;
    public final /* synthetic */ long g;

    public /* synthetic */ b(String str, String str2, long j2, int i2) {
        this.d = i2;
        this.e = str;
        this.f = str2;
        this.g = j2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                PeopleDataManager.lambda$loadHeaderItem$6(this.e, this.f, this.g, (QueryParams) obj);
                return;
            default:
                PetDataManager.lambda$loadHeaderItem$3(this.e, this.f, this.g, (QueryParams) obj);
                return;
        }
    }
}
