package com.samsung.android.gallery.app.ui.list.albums.pictures;

import N2.j;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.module.abstraction.GridValue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPicturesLayoutManager extends PicturesLayoutManager {
    private final SplitLayoutListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SplitLayoutListener {
        int getAlbumListWidth();

        boolean isSplitMode();
    }

    public AlbumPicturesLayoutManager(Context context, int i2, SplitLayoutListener splitLayoutListener) {
        super(context, i2);
        this.mListener = splitLayoutListener;
    }

    private int getAlbumListWidth() {
        return this.mListener.getAlbumListWidth();
    }

    private int getListViewWidth() {
        if (isSplitMode()) {
            return getAlbumListWidth();
        }
        return 0;
    }

    private boolean isSplitMode() {
        return this.mListener.isSplitMode();
    }

    public int getHeaderWidth(int i2) {
        int headerWidth = super.getHeaderWidth(i2);
        int i7 = 0;
        if (GridValue.isSplit(i2, isSplitMode(), false)) {
            i7 = getAlbumListWidth();
        }
        return headerWidth - i7;
    }

    public int getHintHorizontalPadding(int i2) {
        int i7 = 0;
        if (GridValue.isSplit(i2, isSplitMode(), false)) {
            i7 = getAlbumListWidth();
        }
        return getExtraStartPadding(i2) + (i7 - (getSpacing(i2) * 2));
    }

    public int getHintPaddingLeft(int i2) {
        int i7;
        int i8 = 0;
        boolean isSplit = GridValue.isSplit(i2, isSplitMode(), false);
        boolean isLayoutRTL = isLayoutRTL();
        if (isLayoutRTL) {
            i7 = 0;
        } else {
            i7 = getExtraStartPadding(i2);
        }
        if (!isLayoutRTL && isSplit) {
            i8 = getAlbumListWidth();
        }
        return (i7 + i8) - getSpacing(i2);
    }

    public int getHintPaddingStart(int i2) {
        return getExtraStartPadding(i2) + (getListViewWidth() - getSpacing(i2));
    }

    public int getHintSpanCount(int i2) {
        int realGridSize = getRealGridSize(i2);
        if (realGridSize > 1) {
            return realGridSize;
        }
        return getHintWidthSpace(i2);
    }

    public int getHintWidthSpace(int i2) {
        return getWidth() - getHintHorizontalPadding(i2);
    }

    public int getPaddingRight() {
        int i2;
        if (isLayoutRTL()) {
            i2 = getExtraStartPadding(getCurrentSpanCount()) + getListViewWidth();
        } else {
            i2 = 0;
        }
        return i2 - getSpacing(getCurrentSpanCount());
    }

    public int getPaddingStart() {
        return getExtraStartPadding(getCurrentSpanCount()) + (getListViewWidth() - getSpacing(getCurrentSpanCount()));
    }

    public boolean isHintHideDecoItem(int i2) {
        AlbumPicturesViewAdapter albumPicturesViewAdapter = (AlbumPicturesViewAdapter) this.mListAdapter;
        if (albumPicturesViewAdapter == null) {
            return false;
        }
        return albumPicturesViewAdapter.isHideDecoItem(getRealGridSize(i2), GridValue.isSplit(i2, isSplitMode(), false));
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            j.u(e, new StringBuilder("onLayoutChildren failed e="), "GalleryGridLayoutManager");
        }
    }

    public boolean updateTimelineWidth() {
        return false;
    }

    public void updateViewSize(View view, int i2, int i7) {
        if ((i2 != 0 || this.mListAdapter.isRealRatio(getRealGridSize(i7))) && i2 != 4) {
            super.updateViewSize(view, i2, i7);
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int hintWidthSpace = getHintWidthSpace(i7) / getRealGridSize(i7);
        layoutParams.width = hintWidthSpace;
        layoutParams.height = hintWidthSpace;
        view.setLayoutParams(layoutParams);
    }
}
