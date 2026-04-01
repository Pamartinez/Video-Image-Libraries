package com.samsung.android.gallery.app.ui.list.pictures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.WidgetPicturesDateLocationViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WidgetPicturesViewHolderFactory extends PicturesViewHolderFactory {
    public WidgetPicturesViewHolderFactory(Context context) {
        super(context);
    }

    public ListViewHolder getDateLocationViewHolder(View view, int i2) {
        return new WidgetPicturesDateLocationViewHolder(view, i2);
    }

    public WidgetPicturesViewHolderFactory(LayoutInflater layoutInflater) {
        super(layoutInflater);
    }
}
