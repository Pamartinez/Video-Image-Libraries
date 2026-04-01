package com.samsung.android.gallery.widget.story.transitory;

import android.content.res.Resources;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class HeightTransform {
    private static final int GRADIENT_BLUR_VIEW_RES_ID = R$id.thumbnail_container;

    private static int getContentHeight(View view) {
        Resources resources = view.getResources();
        return (int) ((((float) resources.getDimensionPixelOffset(R$dimen.stories_category_transitory_content_height_v85)) / ((float) resources.getDimensionPixelOffset(R$dimen.stories_category_transitory_content_width_v85))) * ((float) getContentView(view).getWidth()));
    }

    private static View getContentView(View view) {
        return view.findViewById(R$id.content_parent);
    }

    private static int getHeight(View view, float f) {
        if (f < -1.0f || !isRecap(view) || f > 0.0f) {
            return -1;
        }
        return getContentHeight(view) + ((int) ((1.0f - Math.abs(f)) * ((float) (getRecapContentHeigh(view) - getContentHeight(view)))));
    }

    private static int getRecapContentHeigh(View view) {
        Resources resources = view.getResources();
        int width = getContentView(view).getWidth();
        int paddingEnd = view.getPaddingEnd();
        return (int) ((((float) resources.getDimensionPixelOffset(R$dimen.stories_category_transitory_recap_height)) / ((float) resources.getDimensionPixelOffset(R$dimen.stories_category_transitory_recap_width))) * ((float) (paddingEnd + view.getPaddingStart() + width)));
    }

    private static boolean isRecap(View view) {
        return "recap".equals(view.getTag());
    }

    public static void transform(ViewPager2 viewPager2, View view, float f) {
        int height = getHeight(view, f);
        if (height != -1) {
            ViewUtils.setHeight(viewPager2, height);
            if (PreferenceFeatures.OneUi8x.STORY_COVER_BLUR) {
                CoverGradientBlur.applyRecap(view.findViewById(GRADIENT_BLUR_VIEW_RES_ID));
            }
        }
    }
}
