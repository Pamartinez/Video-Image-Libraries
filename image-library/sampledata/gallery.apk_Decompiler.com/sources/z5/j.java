package z5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchCreatureHeaderView e;

    public /* synthetic */ j(SearchCreatureHeaderView searchCreatureHeaderView, int i2) {
        this.d = i2;
        this.e = searchCreatureHeaderView;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        SearchCreatureHeaderView searchCreatureHeaderView = this.e;
        switch (i2) {
            case 0:
                searchCreatureHeaderView.lambda$updateFaceClusterHeader$4(view);
                return;
            default:
                searchCreatureHeaderView.lambda$updateFaceClusterHeader$5(view);
                return;
        }
    }
}
