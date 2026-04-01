package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import android.content.Context;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import k6.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GridDimenHelper {
    private static final int[] MARGIN_BY_DEPTH = {R.dimen.grid_1_depth_item_margin, R.dimen.grid_2_depth_item_margin};
    private static final int[] RADIUS_BY_DEPTH = {R.dimen.story_highlight_list_1_depth_item_radius, R.dimen.story_highlight_list_2_depth_item_radius};
    private static final HashMap<Integer, Integer> sMarginMap = new HashMap<>();
    private static final HashMap<Integer, Integer> sRadiusMap = new HashMap<>();

    private static int getDepthKey(int i2, int i7) {
        return (i2 * 10) + i7;
    }

    private static int getMargin(Context context, int i2, int i7) {
        int i8 = i2 - i7;
        int[] iArr = MARGIN_BY_DEPTH;
        return context.getResources().getDimensionPixelOffset(iArr[Math.min(i8, iArr.length - 1)]);
    }

    public static int getMarginFromDepth(GalleryListView galleryListView, int i2) {
        if (galleryListView == null) {
            return 0;
        }
        return getMarginFromDepth(galleryListView.getContext(), galleryListView.getMaxDepth(), galleryListView.getDepthFromColumnCount(i2));
    }

    private static int getRadius(Context context, int i2, int i7) {
        int i8 = i2 - i7;
        int[] iArr = RADIUS_BY_DEPTH;
        return context.getResources().getDimensionPixelOffset(iArr[Math.min(i8, iArr.length - 1)]);
    }

    public static int getRadiusFromDepth(IPinchRecyclerView iPinchRecyclerView, int i2) {
        if (iPinchRecyclerView == null) {
            return 0;
        }
        return getRadiusFromDepth(iPinchRecyclerView.getContext(), iPinchRecyclerView.getMaxDepth(), iPinchRecyclerView.getDepthFromColumnCount(i2));
    }

    public static int getMarginFromDepth(Context context, int i2, int i7) {
        return sMarginMap.computeIfAbsent(Integer.valueOf(getDepthKey(i2, i7)), new a(context, i2, i7, 1)).intValue();
    }

    public static int getRadiusFromDepth(Context context, int i2, int i7) {
        return sRadiusMap.computeIfAbsent(Integer.valueOf(getDepthKey(i2, i7)), new a(context, i2, i7, 0)).intValue();
    }
}
