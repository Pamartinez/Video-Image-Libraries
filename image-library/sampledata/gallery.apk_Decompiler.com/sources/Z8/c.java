package Z8;

import Ae.b;
import Tf.w;
import a.C0068a;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper;
import com.samsung.android.gallery.app.ui.list.search.category.document.SearchDocumentItemAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.location.SearchLocationItemAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterCategoryItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageItemPicker;
import com.samsung.android.gallery.module.dataset.tables.MediaDataFileIdSorter;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.dynamicview.Highlight;
import com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Comparator {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final int compare(Object obj, Object obj2) {
        int i2 = this.d;
        Object obj3 = this.e;
        switch (i2) {
            case 0:
                return ((MediaDataFileIdSorter) obj3).lambda$new$0((Long) obj, (Long) obj2);
            case 1:
                return ((FilterResultsEntry.Builder) obj3).lambda$sortFilterResult$3((Map.Entry) obj, (Map.Entry) obj2);
            case 2:
                return ((CollageItemPicker) obj3).lambda$findBestImage$3((CollageItemPicker.Content) obj, (CollageItemPicker.Content) obj2);
            case 3:
                return ((Highlight) obj3).highlightClipCompare((ClipInfo) obj, (ClipInfo) obj2);
            case 4:
                return ((SearchDocumentItemAdapter) obj3).lambda$updatePositionList$1((Integer) obj, (Integer) obj2);
            case 5:
                return ((SearchLocationItemAdapter) obj3).lambda$sortCountry$4((Map.Entry) obj, (Map.Entry) obj2);
            case 6:
                for (b bVar : (b[]) obj3) {
                    int l = C0068a.l((Comparable) bVar.invoke(obj), (Comparable) bVar.invoke(obj2));
                    if (l != 0) {
                        return l;
                    }
                }
                return 0;
            case 7:
                return TextSelectionHelper.findNearestLineInBlockLineByLine$lambda$31((w) obj3, obj, obj2);
            case 8:
                return ((List) obj3).indexOf(((RecapImageCandidate) obj).item.getFileId() + "");
            default:
                return ((ClusterCategoryItemAdapter) obj3).lambda$updatePositionList$1((Integer) obj, (Integer) obj2);
        }
    }
}
