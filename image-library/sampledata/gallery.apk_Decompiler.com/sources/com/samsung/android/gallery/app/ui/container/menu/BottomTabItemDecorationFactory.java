package com.samsung.android.gallery.app.ui.container.menu;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BottomTabItemDecorationFactory {
    static final int BOTTOM_MENU_CIRCLE_VIEW_SLOT;
    private static final boolean BOTTOM_TAB_BLUR_GUI_80 = PreferenceFeatures.OneUi8x.IS_ONE_UI_80;
    /* access modifiers changed from: private */
    public static final boolean BOTTOM_TAB_ONE_UI_85;

    static {
        int i2;
        boolean z = PreferenceFeatures.OneUi8x.IS_ONE_UI_85;
        BOTTOM_TAB_ONE_UI_85 = z;
        if (z) {
            i2 = 8;
        } else {
            i2 = 4;
        }
        BOTTOM_MENU_CIRCLE_VIEW_SLOT = i2;
    }

    private static RecyclerView.ItemDecoration createBlurredDecoration() {
        return new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                int i2;
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                Resources resources = view.getResources();
                if (childAdapterPosition >= BottomTabItemDecorationFactory.BOTTOM_MENU_CIRCLE_VIEW_SLOT) {
                    boolean isEnabled = Features.isEnabled(Features.IS_RTL);
                    int i7 = childAdapterPosition % 2;
                    int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.bottom_menu_list_transparent_dialog_grid_item_gap);
                    rect.top = resources.getDimensionPixelSize(R.dimen.bottom_menu_list_transparent_dialog_item_vertical_gap);
                    if (i7 == isEnabled) {
                        i2 = 0;
                    } else {
                        i2 = dimensionPixelSize;
                    }
                    rect.left = i2;
                    if (i7 == (!isEnabled)) {
                        dimensionPixelSize = 0;
                    }
                    rect.right = dimensionPixelSize;
                    return;
                }
                BottomTabItemDecorationFactory.updateColumnSideGap(rect, childAdapterPosition % 4, resources.getDimensionPixelSize(R.dimen.bottom_menu_list_circle_item_horizontal_gap));
                if (BottomTabItemDecorationFactory.BOTTOM_TAB_ONE_UI_85 && childAdapterPosition >= 4) {
                    rect.top = resources.getDimensionPixelSize(R.dimen.bottom_menu_list_transparent_dialog_item_vertical_gap);
                }
            }
        };
    }

    public static RecyclerView.ItemDecoration createDecoration() {
        if (BOTTOM_TAB_BLUR_GUI_80) {
            return createBlurredDecoration();
        }
        return createLegacyDecoration();
    }

    private static RecyclerView.ItemDecoration createLegacyDecoration() {
        return new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.bottom_menu_list_dialog_item_gap);
                BottomTabItemDecorationFactory.updateColumnSideGap(rect, childAdapterPosition % 4, dimensionPixelSize);
                if (childAdapterPosition >= 4) {
                    rect.top = dimensionPixelSize;
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public static void updateColumnSideGap(Rect rect, int i2, int i7) {
        rect.left = (i2 * i7) / 4;
        rect.right = i7 - (((i2 + 1) * i7) / 4);
    }
}
