package z5;

import android.content.res.Resources;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements View.OnLayoutChangeListener {
    public final /* synthetic */ SearchCreatureHeader2View d;
    public final /* synthetic */ View e;
    public final /* synthetic */ Resources f;
    public final /* synthetic */ int g;

    public /* synthetic */ g(SearchCreatureHeader2View searchCreatureHeader2View, View view, Resources resources, int i2) {
        this.d = searchCreatureHeader2View;
        this.e = view;
        this.f = resources;
        this.g = i2;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        this.d.lambda$getNameCardLayoutChangeListener$1(this.e, this.f, this.g, view, i2, i7, i8, i10, i11, i12, i13, i14);
    }
}
