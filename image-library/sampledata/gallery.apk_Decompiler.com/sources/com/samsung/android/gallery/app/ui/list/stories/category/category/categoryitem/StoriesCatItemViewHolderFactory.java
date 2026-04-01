package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoriesCatItemViewHolderFactory {
    private static final int CATEGORY_TRANSITORY_ITEM_LAYOUT_ID;
    private static final boolean STORY_ONE_UI_80;

    static {
        int i2;
        boolean z = PreferenceFeatures.OneUi8x.STORY_ONE_UI_80;
        STORY_ONE_UI_80 = z;
        if (z) {
            i2 = R.layout.recycler_item_stories_category_transitory_image_layout_v80;
        } else {
            i2 = R.layout.recycler_item_stories_category_transitory_image_layout;
        }
        CATEGORY_TRANSITORY_ITEM_LAYOUT_ID = i2;
    }

    public static ListViewHolder createItemViewHolder(ViewGroup viewGroup, int i2, String str) {
        return new StoriesCatItemViewHolder(inflateView(viewGroup, R.layout.recycler_item_stories_category_image_layout), i2, str);
    }

    public static ListViewHolder createRecapItemViewHolder(ViewGroup viewGroup, int i2) {
        return new RecapContentItemViewHolder(inflateView(viewGroup, R.layout.recycler_item_stories_category_transitory_image_layout_v80), i2, "location://stories/category/transitory");
    }

    public static ListViewHolder createTransitoryItemViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 102) {
            return new StoriesCatTransitoryItemOnDemandViewHolder(inflateView(viewGroup, R.layout.recycler_item_stories_category_on_demand_image_layout), i2);
        }
        if (PocFeatures.SUPPORT_RECAP_STACK_UI && i2 == 103) {
            return new StoriesCatTransitoryRecapContentViewHolder(inflateView(viewGroup, R.layout.recycler_item_stories_category_transitory_recap_content), i2);
        }
        View inflateView = inflateView(viewGroup, CATEGORY_TRANSITORY_ITEM_LAYOUT_ID);
        if (!STORY_ONE_UI_80) {
            return new StoriesCatTransitoryItemViewHolder(inflateView, i2, "location://stories/category/transitory");
        }
        if (i2 == 101) {
            return new StoriesCatTransitoryItemRecapViewHolder(inflateView, i2, "location://stories/category/transitory");
        }
        return new StoriesCatTransitoryItemViewHolderV80(inflateView, i2, "location://stories/category/transitory");
    }

    public static int getRecapContentItemViewType(MediaItem mediaItem) {
        return 200;
    }

    public static int getTransitoryItemViewType(MediaItem mediaItem, boolean z) {
        if (z) {
            return 102;
        }
        if (PocFeatures.SUPPORT_RECAP_STACK_UI && MediaItemStory.isRecap(mediaItem)) {
            return 103;
        }
        if (MediaItemStory.isRecap(mediaItem)) {
            return 101;
        }
        return 100;
    }

    private static View inflateView(ViewGroup viewGroup, int i2) {
        return C0086a.d(viewGroup, i2, viewGroup, false);
    }
}
