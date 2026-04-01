package com.samsung.android.gallery.app.ui.list.timeline;

import android.view.MenuItem;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.timeline.TimelinePresenter;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ c(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((ImageView) obj).setImageDrawable(((ImageView) obj).getContext().getDrawable(R.drawable.gallery_ic_cloud_syncing));
                return;
            case 1:
                ((ImageView) obj).setImageDrawable(((ImageView) obj).getContext().getDrawable(R.drawable.gallery_ic_cloud_synced));
                return;
            default:
                TimelinePresenter.TimelineMenuUpdater.lambda$updatePopupMenuAction$0((MenuItem) obj);
                return;
        }
    }
}
