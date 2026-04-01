package com.samsung.android.gallery.app.ui.viewer2.selection;

import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewZoomDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((SelectionPageChangeDelegate) obj2).lambda$onPageIdle$3((SelectionViewAdapter) obj);
                return;
            default:
                ((SelectionViewZoomDelegate.AnonymousClass1) obj2).lambda$initImageView$0((ViewGroup) obj);
                return;
        }
    }
}
