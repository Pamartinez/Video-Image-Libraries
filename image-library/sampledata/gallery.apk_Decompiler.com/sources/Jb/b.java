package Jb;

import android.view.View;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItemBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionPageTransformer;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ float e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ b(View view, float f5, ListViewHolder listViewHolder) {
        this.f = view;
        this.e = f5;
        this.g = listViewHolder;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((PinchItemBuilder) this.f).lambda$updateHeaderViewRect$5((PinchItem) this.g, this.e, (Integer) obj);
                return;
            default:
                LiveMotionPageTransformer.lambda$transformPageInternal$2((View) this.f, this.e, (ListViewHolder) this.g, (PageTransform) obj);
                return;
        }
    }

    public /* synthetic */ b(PinchItemBuilder pinchItemBuilder, PinchItem pinchItem, float f5) {
        this.f = pinchItemBuilder;
        this.g = pinchItem;
        this.e = f5;
    }
}
