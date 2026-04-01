package com.samsung.android.gallery.app.ui.list.albums.choice.abstraction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsChoiceLayoutManager extends AlbumsBaseLayoutManager {
    public AlbumsChoiceLayoutManager(Context context, int i2) {
        super(context, i2);
    }

    public void updateViewSize(View view, int i2, int i7) {
        if (i2 == 2 || (ViewHolderValue.isHeader(i2) && getRealGridSize(i7) > 1)) {
            int[] itemPadding = getItemPadding(i7);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = getHintWidthSpace(i7) / getRealGridSize(i7);
            layoutParams.height = -2;
            view.setLayoutParams(layoutParams);
            view.setPadding(itemPadding[0], itemPadding[1], itemPadding[2], itemPadding[3]);
        }
    }
}
