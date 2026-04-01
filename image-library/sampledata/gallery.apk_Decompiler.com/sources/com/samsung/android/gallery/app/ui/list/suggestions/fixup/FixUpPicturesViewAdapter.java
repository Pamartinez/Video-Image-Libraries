package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FixUpPicturesViewAdapter extends PicturesViewAdapter<IFixUpPictures> {
    public FixUpPicturesViewAdapter(IFixUpPictures iFixUpPictures, String str, boolean z) {
        super(iFixUpPictures, str, z);
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return ((IFixUpPictures) this.mView).createViewHolderFactory();
    }

    public int getDividerIndex(int i2) {
        return -1;
    }

    public int getItemHeight(int i2) {
        return ((IFixUpPictures) this.mView).getLayoutManager().getItemHeight(i2, getGridSize());
    }

    public int getMaxScroll() {
        int i2;
        int gridSize = getGridSize();
        int itemCount = getItemCount();
        int i7 = itemCount / gridSize;
        if (itemCount % gridSize > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return getItemHeight(0) * (i7 + i2);
    }

    public int getNextRowIndex(int i2, int i7) {
        return Math.min(getGridSize() + i2, i7 - 1);
    }

    public int getPrevRowIndex(int i2) {
        return Math.max(0, i2 - getGridSize());
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public boolean isPreviewAvailable() {
        return true;
    }

    public boolean supportHover() {
        return false;
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2) {
    }
}
