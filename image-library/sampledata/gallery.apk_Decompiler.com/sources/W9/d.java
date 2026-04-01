package W9;

import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;
import com.samsung.android.gallery.app.ui.list.search.category.people.PeopleSelectPresenter;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.sdk.moneta.basicdomain.entity.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ List e;
    public final /* synthetic */ ArrayList f;

    public /* synthetic */ d(List list, ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = list;
        this.f = arrayList;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                PersonalDataCore.lambda$getSelectedPersonList$8(this.e, this.f, (Person) obj);
                return;
            case 1:
                CreatureSelectPresenterV2.lambda$getDifferenceUnifiedId$1(this.e, this.f, (String) obj);
                return;
            default:
                PeopleSelectPresenter.lambda$getDifferenceUnifiedId$4(this.e, this.f, (String) obj);
                return;
        }
    }
}
