package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseCleanOutPicturesViewAdapter extends PicturesHeaderViewAdapter<ICleanOutPicturesView> {
    public BaseCleanOutPicturesViewAdapter(ICleanOutPicturesView iCleanOutPicturesView, String str, View view) {
        super(iCleanOutPicturesView, str, view, false);
    }

    private CleanOutPicturesLayoutManager getCleanOutPicturesLayoutManager() {
        return (CleanOutPicturesLayoutManager) ((ICleanOutPicturesView) this.mView).getLayoutManager();
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return CleanOutDelegate.createViewHolderFactory(context, getLocationKey());
    }

    public int getMeasuredItemHeight() {
        return getCleanOutPicturesLayoutManager().getItemHeight(getGridSize());
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public void selectDuplicatedItems() {
        int i2;
        ArrayList<Integer> dividers = getDividers();
        int size = dividers.size();
        for (int i7 = 0; i7 < size; i7++) {
            int intValue = dividers.get(i7).intValue();
            if (i7 == size - 1) {
                i2 = getItemCount();
            } else {
                i2 = dividers.get(i7 + 1).intValue() + 1;
            }
            for (int i8 = intValue + 3; i8 < i2; i8++) {
                selectItem(i8, true, false);
            }
        }
        notifySelectedItemChanged();
    }

    public void onDividerSelected() {
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2) {
    }

    public void enableLocationText(ListViewHolder listViewHolder, boolean z, boolean z3) {
    }
}
