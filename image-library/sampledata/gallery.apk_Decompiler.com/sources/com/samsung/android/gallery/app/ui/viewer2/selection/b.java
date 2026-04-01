package com.samsung.android.gallery.app.ui.viewer2.selection;

import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewZoomDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((SelectionPageChangeDelegate) this.e).lambda$onPageIdle$0((SelectionViewAdapter) this.f, (SelectionViewHolder) obj);
                return;
            case 1:
                ((SelectionPageChangeDelegate) this.e).lambda$onPageIdle$1((SelectionViewAdapter) this.f, (SelectionViewHolder) obj);
                return;
            case 2:
                ((SelectionPageChangeDelegate) this.e).lambda$onPageIdle$2((SelectionViewAdapter) this.f, (SelectionViewHolder) obj);
                return;
            default:
                ((SelectionViewZoomDelegate.AnonymousClass1) this.e).lambda$initBackgroundView$1((ViewGroup) this.f, (ViewGroup) obj);
                return;
        }
    }
}
