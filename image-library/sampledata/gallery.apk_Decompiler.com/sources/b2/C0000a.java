package B2;

import android.view.View;
import androidx.appcompat.widget.SearchView;
import com.samsung.android.gallery.app.ui.list.suggestions.cleanout.DuplicateDividerViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.details.EditDetailsHandler;
import com.samsung.android.gallery.app.ui.viewholders.DateLocationViewHolder;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.toolbar.SelectInfoView;

/* renamed from: B2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0000a implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f42a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0000a(int i2, Object obj) {
        this.f42a = i2;
        this.b = obj;
    }

    public final void onFocusChange(View view, boolean z) {
        int i2 = this.f42a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                C0003d dVar = (C0003d) obj;
                dVar.s(dVar.t());
                return;
            case 1:
                l lVar = (l) obj;
                lVar.l = z;
                lVar.p();
                if (!z) {
                    lVar.s(false);
                    lVar.m = false;
                    return;
                }
                return;
            case 2:
                ((EditDetailsHandler) obj).onEditTitleFocusChanged(view, z);
                return;
            case 3:
                ((DuplicateDividerViewHolder) obj).lambda$bind$0(view, z);
                return;
            case 4:
                ((SearchToolbar) obj).lambda$new$9(view, z);
                return;
            case 5:
                ((DateLocationViewHolder) obj).lambda$setDateText$0(view, z);
                return;
            case 6:
                ((SelectInfoView) obj).lambda$initView$0(view, z);
                return;
            default:
                ((SearchView) obj).lambda$new$0(view, z);
                return;
        }
    }
}
