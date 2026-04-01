package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.ProcessingViewManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((PdcRecommendAdapter) this.e).lambda$bindThumbnail$4((ListViewHolder) this.f, (Bitmap) this.g);
                return;
            default:
                ((ProcessingViewManager.AnonymousClass2) this.e).lambda$onAnimationEnd$0((String) this.f, (ArrayList) this.g);
                return;
        }
    }
}
