package I5;

import com.samsung.android.gallery.app.ui.list.search.suggestionview.FaceClusterMergeView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FaceClusterMergeView e;

    public /* synthetic */ a(FaceClusterMergeView faceClusterMergeView, int i2) {
        this.d = i2;
        this.e = faceClusterMergeView;
    }

    public final void run() {
        int i2 = this.d;
        FaceClusterMergeView faceClusterMergeView = this.e;
        switch (i2) {
            case 0:
                faceClusterMergeView.lambda$new$6();
                return;
            case 1:
                faceClusterMergeView.updateEmptyViewPadding();
                return;
            case 2:
                faceClusterMergeView.lambda$handleNextItem$9();
                return;
            case 3:
                faceClusterMergeView.lambda$prepareItems$2();
                return;
            case 4:
                faceClusterMergeView.lambda$prepareItems$0();
                return;
            default:
                faceClusterMergeView.lambda$prepareItems$1();
                return;
        }
    }
}
