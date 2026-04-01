package com.samsung.android.gallery.app.ui.list.albums.pictures;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPaneLayoutManager extends AlbumsBaseLayoutManager {
    public AlbumsPaneLayoutManager(Context context, int i2) {
        super(context, i2);
    }

    public void addView(View view, int i2) {
        updateViewSize(view, getItemViewType(view), getSpanCount());
        super.addView(view, i2);
    }

    public void updateViewSize(View view, int i2, int i7) {
        if (i2 == 2) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = getWidth() / getRealGridSize(i7);
            layoutParams.height = -2;
            view.setLayoutParams(layoutParams);
        }
    }
}
