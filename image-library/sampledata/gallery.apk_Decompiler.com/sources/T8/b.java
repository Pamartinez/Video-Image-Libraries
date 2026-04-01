package t8;

import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterCategoryItemAdapter;
import java.util.Comparator;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        Map.Entry entry = (Map.Entry) obj;
        Map.Entry entry2 = (Map.Entry) obj2;
        switch (this.d) {
            case 0:
                return ((Long) entry.getValue()).compareTo((Long) entry2.getValue());
            default:
                return ClusterCategoryItemAdapter.lambda$sortSubCategory$0(entry, entry2);
        }
    }
}
