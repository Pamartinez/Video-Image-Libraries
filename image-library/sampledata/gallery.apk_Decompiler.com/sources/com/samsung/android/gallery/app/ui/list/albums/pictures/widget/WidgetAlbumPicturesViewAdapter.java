package com.samsung.android.gallery.app.ui.list.albums.pictures.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.pictures.WidgetPicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WidgetAlbumPicturesViewAdapter<V extends IAlbumPicturesView> extends AlbumPicturesViewAdapter<V> {
    private final boolean mFlipCover;
    private final int mThumbnailBgColor = getContext().getColor(R.color.thumbnail_icon_background_dark);
    private final Drawable mThumbnailBorder = getContext().getDrawable(R.drawable.cover_widget_thumbnail_border);

    public WidgetAlbumPicturesViewAdapter(V v, String str, View view, boolean z, boolean z3) {
        super(v, str, view, z);
        this.mFlipCover = z3;
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new WidgetPicturesViewHolderFactory(context);
    }

    public int getGridMargin(int i2) {
        if (this.mFlipCover) {
            return GridMarginHelper.getMarginInFlipCover(this.mGalleryListView, i2);
        }
        return super.getGridMargin(i2);
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2, int i7) {
        if (this.mFlipCover) {
            super.setViewHolderMargin(iPinchViewHolder, i2, getGridMargin(i2));
        } else {
            super.setViewHolderMargin(iPinchViewHolder, i2, i7);
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        listViewHolder.setThumbnailBackgroundColor(this.mThumbnailBgColor);
        super.onBindViewHolder(listViewHolder, i2);
        listViewHolder.addThumbnailBorder(this.mThumbnailBorder);
    }
}
