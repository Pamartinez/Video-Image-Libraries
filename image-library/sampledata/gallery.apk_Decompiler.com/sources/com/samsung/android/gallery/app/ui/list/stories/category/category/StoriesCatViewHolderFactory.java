package com.samsung.android.gallery.app.ui.list.stories.category.category;

import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.sec.android.gallery3d.R;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoriesCatViewHolderFactory {
    private static final int CATEGORY_TRANSITORY_CONTENT_LAYOUT_ID;
    private static final boolean STORY_ONE_UI_80;
    static HashMap<Integer, String> locationMap = new HashMap<Integer, String>() {
        {
            put(Integer.valueOf(LttEngineErrors.ERROR_INPAINTING_FAILED), "location://stories/category/transitory");
            put(Integer.valueOf(LttEngineErrors.ERROR_INPAINTING_PROCESSING_FAILED), "location://stories/category/creation");
            put(Integer.valueOf(LttEngineErrors.ERROR_INPAINTING_INITIALIZATION_FAILED), "location://stories/category/trip");
            put(Integer.valueOf(LttEngineErrors.ERROR_INPAINTING_NOT_SUPPORTED), "location://stories/category/RecapBookMarked");
            put(-100, "location://stories/category/more");
        }
    };
    static HashMap<String, Integer> viewTypeMap = new HashMap<String, Integer>() {
        {
            put("location://stories/category/transitory", Integer.valueOf(LttEngineErrors.ERROR_INPAINTING_FAILED));
            put("location://stories/category/creation", Integer.valueOf(LttEngineErrors.ERROR_INPAINTING_PROCESSING_FAILED));
            put("location://stories/category/trip", Integer.valueOf(LttEngineErrors.ERROR_INPAINTING_INITIALIZATION_FAILED));
            put("location://stories/category/RecapBookMarked", Integer.valueOf(LttEngineErrors.ERROR_INPAINTING_NOT_SUPPORTED));
            put("location://stories/category/more", -100);
        }
    };

    static {
        int i2;
        boolean z = PreferenceFeatures.OneUi8x.STORY_ONE_UI_80;
        STORY_ONE_UI_80 = z;
        if (z) {
            i2 = R.layout.recycler_item_stories_category_layout_transitory_v80;
        } else {
            i2 = R.layout.recycler_item_stories_category_layout_transitory;
        }
        CATEGORY_TRANSITORY_CONTENT_LAYOUT_ID = i2;
    }

    public static StoriesCatBaseViewHolder create(IStoriesCategoryView iStoriesCategoryView, ViewGroup viewGroup, int i2) {
        View inflateView = inflateView(viewGroup, getCategoryViewResId(i2));
        if (i2 == -101) {
            return new StoriesCatTransitoryViewHolder(iStoriesCategoryView, inflateView, i2);
        }
        if (i2 != -103 || !OnDemandStory.getInstance().isEnabled() || PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY) {
            return new StoriesCatViewHolder(iStoriesCategoryView, inflateView, i2);
        }
        return new StoriesCatOnDemandViewHolder(iStoriesCategoryView, inflateView, i2);
    }

    private static int getCategoryViewResId(int i2) {
        if (i2 == -101) {
            return CATEGORY_TRANSITORY_CONTENT_LAYOUT_ID;
        }
        return R.layout.recycler_item_stories_category_layout;
    }

    public static int getCategoryViewType(String str) {
        return viewTypeMap.getOrDefault(str, -100).intValue();
    }

    public static String getLocationKey(int i2) {
        return locationMap.getOrDefault(Integer.valueOf(i2), "location://stories/category/more");
    }

    private static View inflateView(ViewGroup viewGroup, int i2) {
        return C0086a.d(viewGroup, i2, viewGroup, false);
    }
}
