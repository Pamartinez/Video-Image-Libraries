package Z8;

import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.module.dataset.tables.SearchCreatureSorter;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchCreatureSorter e;
    public final /* synthetic */ ArrayList f;

    public /* synthetic */ d(SearchCreatureSorter searchCreatureSorter, ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = searchCreatureSorter;
        this.f = arrayList;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$sort$3(this.f, (String) obj);
                return;
            default:
                this.e.lambda$sort$2(this.f, (AbstractSorter.SortData) obj);
                return;
        }
    }
}
