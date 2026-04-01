package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemDebugExif;
import com.samsung.android.gallery.module.data.MediaItem;

/* renamed from: com.samsung.android.gallery.app.ui.viewer2.details.items.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0447a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0447a(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((DetailsItem3rdParty) this.e).lambda$onClicked$3((BaseCommand) this.f);
                return;
            case 1:
                DetailsItemDebugExif.lambda$loadSefData$8((MediaItem) this.e, (View) this.f);
                return;
            case 2:
                ((TextView) ((View) this.e)).setText((String) this.f);
                return;
            case 3:
                ((DetailsItemDebugExif.DebugExifThumbnailViewHolder) this.e).lambda$saveBitmap$3((String) this.f);
                return;
            case 4:
                ((DetailsItemDynamicVideo) this.e).lambda$onRecyclerViewInited$0((RecyclerView) this.f);
                return;
            case 5:
                ((DetailsItemStory) this.e).lambda$onItemClick$1((MediaItem) this.f);
                return;
            default:
                ((DetailsItemSuperSlow) this.e).lambda$onRecyclerViewInited$0((RecyclerView) this.f);
                return;
        }
    }
}
