package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.TriConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements TriConsumer {
    public final /* synthetic */ ScreenshotFilterCustomViewAdapter d;

    public /* synthetic */ b(ScreenshotFilterCustomViewAdapter screenshotFilterCustomViewAdapter) {
        this.d = screenshotFilterCustomViewAdapter;
    }

    public final void accept(Object obj, Object obj2, Object obj3) {
        this.d.onReorderItemTouch((RecyclerView.ViewHolder) obj, (View) obj2, (MotionEvent) obj3);
    }
}
