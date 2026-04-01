package com.samsung.android.gallery.widget.story.transitory;

import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapPageTransformer extends PageTransformer {
    private static final int GRADIENT_BLUR_VIEW_RES_ID = R$id.thumbnail_container;

    private View getContentView(View view) {
        return view.findViewById(R$id.content_parent);
    }

    private float getScale(View view, float f) {
        return 1.0f - (Math.abs(f) * 0.15f);
    }

    private int getScaleDelta(View view, float f) {
        return (int) (Math.abs(f) * ((float) view.getWidth()) * 0.15f);
    }

    private void setAlpha(View view, float f) {
        if (f > 2.0f) {
            ViewUtils.setAlpha(view, 1.0f - (f - ((float) ((int) f))));
        } else {
            ViewUtils.setAlpha(view, 1.0f);
        }
    }

    private void setEndPadding(View view, float f) {
        ViewParent parent = view.getParent();
        if (parent instanceof RecyclerView) {
            RecyclerView.Adapter adapter = ((RecyclerView) parent).getAdapter();
            int absoluteAdapterPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getAbsoluteAdapterPosition();
            if (adapter != null && absoluteAdapterPosition == adapter.getItemCount() - 1) {
                ViewMarginUtils.setEndPadding(view, (int) (Math.min(Math.abs(f), 1.0f) * ((float) view.getResources().getDimensionPixelSize(R$dimen.stories_category_transitory_recap_item_padding_end))));
            }
        }
    }

    private void setTransform(View view, float f) {
        View contentView = getContentView(view);
        float scale = getScale(view, f);
        ViewUtils.setScale(contentView, scale, scale);
        setTranslateXY(view, f);
        setTranslateZ(view, f);
        setAlpha(view, f);
        setEndPadding(view, f);
    }

    private void setTranslateXY(View view, float f) {
        View contentView = getContentView(view);
        if (f >= 3.0f || f <= 0.0f) {
            contentView.setTranslationX(0.0f);
            contentView.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            return;
        }
        view.setTranslationX(adjustRtlPosition(((float) view.getWidth()) * (-f)));
        contentView.setTranslationX(adjustRtlPosition((((float) getScaleDelta(view, f)) / 2.0f) + (((float) view.getResources().getDimensionPixelSize(R$dimen.stories_category_transitory_recap_item_queue)) * f)));
        if (PreferenceFeatures.OneUi8x.STORY_COVER_BLUR) {
            CoverGradientBlur.applyRecap(view.findViewById(GRADIENT_BLUR_VIEW_RES_ID));
        }
    }

    private void setTranslateZ(View view, float f) {
        if (f < -1.0f) {
            view.setTranslationZ(-2.0f);
        } else if (f <= 0.0f) {
            view.setTranslationZ(1.0f - f);
        } else if (f <= 3.0f) {
            view.setTranslationZ(-f);
        } else {
            view.setTranslationZ(-2.0f);
        }
    }

    public final float adjustRtlPosition(float f) {
        if (Features.isEnabled(Features.IS_RTL)) {
            return -f;
        }
        return f;
    }

    public void transformPage(View view, float f) {
        setTransform(view, f);
        super.transformPage(view, f);
    }
}
