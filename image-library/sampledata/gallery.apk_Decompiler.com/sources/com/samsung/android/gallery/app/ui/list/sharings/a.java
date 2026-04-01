package com.samsung.android.gallery.app.ui.list.sharings;

import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ SharingsItemViewHolder d;
    public final /* synthetic */ Bitmap e;

    public /* synthetic */ a(SharingsItemViewHolder sharingsItemViewHolder, Bitmap bitmap) {
        this.d = sharingsItemViewHolder;
        this.e = bitmap;
    }

    public final void run() {
        this.d.lambda$bindImage$0(this.e);
    }
}
