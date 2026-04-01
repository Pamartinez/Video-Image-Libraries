package z5;

import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchCreatureHeaderView e;

    public /* synthetic */ i(SearchCreatureHeaderView searchCreatureHeaderView, int i2) {
        this.d = i2;
        this.e = searchCreatureHeaderView;
    }

    public final void run() {
        int i2 = this.d;
        SearchCreatureHeaderView searchCreatureHeaderView = this.e;
        switch (i2) {
            case 0:
                searchCreatureHeaderView.lambda$bindImage$16();
                return;
            case 1:
                searchCreatureHeaderView.lambda$updateFaceClusterHeader$7();
                return;
            case 2:
                searchCreatureHeaderView.lambda$updateFaceClusterHeader$6();
                return;
            default:
                searchCreatureHeaderView.lambda$updateFaceClusterHeader$9();
                return;
        }
    }
}
