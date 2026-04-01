package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Presenter;
import com.samsung.android.gallery.widget.listview.GalleryListView;

/* renamed from: A4.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0376k implements GalleryListView.onTouchUpListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2252a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0376k(int i2, Object obj) {
        this.f2252a = i2;
        this.b = obj;
    }

    public final void touchUpOnSelectionMode() {
        int i2 = this.f2252a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ((BaseListFragment) obj).updateSelectionToolBar();
                return;
            default:
                ((StoryHighlightListV2Presenter) obj).invalidateOptionsMenu();
                return;
        }
    }
}
