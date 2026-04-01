package com.samsung.android.gallery.widget.details;

import com.samsung.android.gallery.widget.details.DetailsLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ DetailsLayoutManager.AnonymousClass2 d;

    public /* synthetic */ a(DetailsLayoutManager.AnonymousClass2 r1) {
        this.d = r1;
    }

    public final void run() {
        this.d.requestLayout();
    }
}
