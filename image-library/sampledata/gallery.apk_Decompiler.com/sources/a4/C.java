package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListOnItemCheckChangeListener;
import com.samsung.android.gallery.widget.listview.GalleryListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C implements GalleryListView.onTouchUpListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BaseListOnItemCheckChangeListener f2236a;
    public final /* synthetic */ Integer b;

    public /* synthetic */ C(BaseListOnItemCheckChangeListener baseListOnItemCheckChangeListener, Integer num) {
        this.f2236a = baseListOnItemCheckChangeListener;
        this.b = num;
    }

    public final void touchUpOnSelectionMode() {
        this.f2236a.lambda$alignScrollToBottom$0(this.b);
    }
}
