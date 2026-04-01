package U5;

import com.samsung.android.gallery.app.ui.list.stories.category.StoriesCategoryPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.ReorderHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.searchbar.InstantSearchHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Timer.OnTimer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2450a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2451c;

    public /* synthetic */ d(int i2, Object obj, Object obj2) {
        this.f2450a = i2;
        this.b = obj;
        this.f2451c = obj2;
    }

    public final void onTimer(int i2) {
        switch (this.f2450a) {
            case 0:
                ((StoriesCategoryPreviewDelegate) this.b).lambda$onStartInternal$0((PreviewViewHolder) this.f2451c, i2);
                return;
            case 1:
                ((InstantSearchHandler) this.b).lambda$postSearch$0((CharSequence) this.f2451c, i2);
                return;
            default:
                ((ReorderHandler) this.b).lambda$tryReorder$0((ListViewHolder) this.f2451c, i2);
                return;
        }
    }
}
