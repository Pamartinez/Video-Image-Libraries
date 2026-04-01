package A4;

import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.TopResultsClusterResult;
import com.samsung.android.gallery.module.mde.executor.DeleteGroup;
import com.samsung.android.sum.core.filter.collection.SequentialPickerFilter;
import java.util.ArrayList;
import java.util.function.IntConsumer;

/* renamed from: A4.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0383s implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2257a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0383s(int i2, Object obj) {
        this.f2257a = i2;
        this.b = obj;
    }

    public final void accept(int i2) {
        int i7 = this.f2257a;
        Object obj = this.b;
        switch (i7) {
            case 0:
                ((BaseListFragment) obj).onAutoDragReady(i2);
                return;
            case 1:
                ((DeleteGroup) obj).lambda$execute$0(i2);
                return;
            case 2:
                ((SequentialPickerFilter) obj).lambda$prepare$0(i2);
                return;
            case 3:
                ((ArrayList) obj).add(Integer.valueOf(i2));
                return;
            case 4:
                ((RelationshipChoiceAdapter) obj).lambda$findSelectedPosition$2(i2);
                return;
            default:
                ((TopResultsClusterResult) obj).onAutoDragReady(i2);
                return;
        }
    }
}
