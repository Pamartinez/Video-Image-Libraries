package com.samsung.android.gallery.plugins.compare;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.compare.CompareActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ CompareActivity.ViewHolder d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ d(CompareActivity.ViewHolder viewHolder, Bitmap bitmap, MediaItem mediaItem) {
        this.d = viewHolder;
        this.e = bitmap;
        this.f = mediaItem;
    }

    public final void run() {
        CompareActivity.ListAdapter.AnonymousClass1.lambda$onLoaded$0(this.d, this.e, this.f);
    }
}
