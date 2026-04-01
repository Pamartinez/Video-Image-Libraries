package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import android.content.res.Resources;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import com.sec.android.gallery3d.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GridMarginHelper {
    private static volatile int sConstMarginValue;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AlbumMarginHolder {
        static final AtomicInteger HORIZONTAL = new AtomicInteger();
        static final AtomicInteger SIDE = new AtomicInteger();
        static final AtomicInteger VERTICAL = new AtomicInteger();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WidgetMarginHolder {
        static final int[] MAP = {R.dimen.flip_widget_grid_3_depth_item_margin, R.dimen.flip_widget_grid_4_depth_item_margin, R.dimen.flip_widget_grid_7_depth_item_margin};

        public static int getResId(boolean z, int i2) {
            int[] iArr = MAP;
            if (!z) {
                i2 = (iArr.length - 1) - i2;
            }
            return iArr[i2];
        }
    }

    public static void clear() {
        sConstMarginValue = 0;
    }

    public static void clearAlbumPadding() {
        AlbumMarginHolder.HORIZONTAL.set(0);
        AlbumMarginHolder.SIDE.set(0);
        AlbumMarginHolder.VERTICAL.set(0);
    }

    public static int getAlbumHorizontalPadding(IPinchRecyclerView iPinchRecyclerView) {
        AtomicInteger atomicInteger = AlbumMarginHolder.HORIZONTAL;
        int i2 = atomicInteger.get();
        if (i2 != 0) {
            return i2;
        }
        int dimensionPixelOffset = iPinchRecyclerView.getContext().getResources().getDimensionPixelOffset(R.dimen.album_grid_item_side_offset_horizontal);
        atomicInteger.set(dimensionPixelOffset);
        return dimensionPixelOffset;
    }

    public static int getAlbumSideGap(IPinchRecyclerView iPinchRecyclerView) {
        AtomicInteger atomicInteger = AlbumMarginHolder.SIDE;
        int i2 = atomicInteger.get();
        if (i2 != 0) {
            return i2;
        }
        int dimensionPixelOffset = iPinchRecyclerView.getContext().getResources().getDimensionPixelOffset(R.dimen.album_grid_view_side_gap);
        atomicInteger.set(dimensionPixelOffset);
        return dimensionPixelOffset;
    }

    public static int getAlbumVerticalPadding(IPinchRecyclerView iPinchRecyclerView) {
        AtomicInteger atomicInteger = AlbumMarginHolder.VERTICAL;
        int i2 = atomicInteger.get();
        if (i2 != 0) {
            return i2;
        }
        int dimensionPixelOffset = iPinchRecyclerView.getContext().getResources().getDimensionPixelOffset(R.dimen.album_grid_item_side_offset_vertical);
        atomicInteger.set(dimensionPixelOffset);
        return dimensionPixelOffset;
    }

    public static int getDividerHeight(GalleryListView galleryListView, int i2) {
        if (galleryListView == null) {
            return 0;
        }
        return galleryListView.getGridSpans().computeHeight(galleryListView.getContext(), i2);
    }

    public static int getMargin(IPinchRecyclerView iPinchRecyclerView, int i2) {
        int i7;
        if (iPinchRecyclerView != null && sConstMarginValue == 0) {
            boolean isDexMode = DeviceInfo.isDexMode(iPinchRecyclerView.getContext());
            Resources resources = iPinchRecyclerView.getResources();
            if (isDexMode) {
                i7 = R.dimen.grid_item_margin_for_floating_dex;
            } else {
                i7 = R.dimen.grid_item_margin_for_floating;
            }
            sConstMarginValue = resources.getDimensionPixelOffset(i7);
        }
        return sConstMarginValue;
    }

    public static int getMarginInFlipCover(IPinchRecyclerView iPinchRecyclerView, int i2) {
        GridSpans gridSpans = iPinchRecyclerView.getGridSpans();
        return iPinchRecyclerView.getContext().getResources().getDimensionPixelOffset(WidgetMarginHolder.getResId(gridSpans.normalOrder, gridSpans.indexOf(i2)));
    }
}
