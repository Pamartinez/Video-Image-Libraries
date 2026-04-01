package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemDebugExif;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((DetailsItemCaptureUrl) obj).lambda$onViewInflate$0(view);
                return;
            case 1:
                ((DetailsItemClippedImage) obj).onPathClicked(view);
                return;
            case 2:
                ((DetailsItemDebugExif) obj).lambda$updateFileData$3(view);
                return;
            case 3:
                ((DetailsItemDebugExif.DebugExifThumbnailViewHolder) obj).lambda$new$0(view);
                return;
            case 4:
                ((DetailsItemLocationText) obj).lambda$onViewInflate$0(view);
                return;
            case 5:
                ((ViewHolderTagAdd) obj).lambda$bindView$0(view);
                return;
            default:
                ((ViewHolderTagEdit) obj).lambda$bindView$0(view);
                return;
        }
    }
}
