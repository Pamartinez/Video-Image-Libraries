package I5;

import com.samsung.android.gallery.app.ui.list.search.suggestionview.FaceClusterMergeView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FaceClusterMergeView e;

    public /* synthetic */ b(FaceClusterMergeView faceClusterMergeView, int i2) {
        this.d = i2;
        this.e = faceClusterMergeView;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        FaceClusterMergeView faceClusterMergeView = this.e;
        switch (i2) {
            case 0:
                faceClusterMergeView.onCompleteMerge(obj);
                return;
            default:
                faceClusterMergeView.lambda$new$5((Long) obj);
                return;
        }
    }
}
