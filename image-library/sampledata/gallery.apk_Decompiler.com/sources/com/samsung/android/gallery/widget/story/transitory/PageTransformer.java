package com.samsung.android.gallery.widget.story.transitory;

import Yb.c;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$id;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PageTransformer implements ViewPager2.PageTransformer {
    protected static final float SCALE_AMOUNT;
    protected ViewPager2.PageTransformer mTransformListener;
    private boolean mTransformRequested;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class InnerMarginTransformer implements ViewPager2.PageTransformer {
        int mMargin;
        int mPageQueueMargin;
        int mRemainSpace;

        public InnerMarginTransformer(int i2, int i7, int i8) {
            this.mMargin = i2;
            this.mPageQueueMargin = i7;
            this.mRemainSpace = i8;
        }

        private int getContentWidth(View view) {
            return view.findViewById(R$id.content_parent).getWidth();
        }

        private float getPageQueueDelta(float f) {
            return ((float) this.mPageQueueMargin) * f;
        }

        private float getRemainSpace(float f) {
            int i2 = this.mRemainSpace;
            if (i2 > 0) {
                return ((float) i2) * f;
            }
            return 0.0f;
        }

        private float getScaleDelta(View view, float f) {
            return ((((float) getContentWidth(view)) * PageTransformer.SCALE_AMOUNT) / 2.0f) * f;
        }

        public void setPageMargin(int i2, int i7, int i8) {
            this.mMargin = i2;
            this.mPageQueueMargin = i7;
            this.mRemainSpace = i8;
        }

        public void transformPage(View view, float f) {
            float pageQueueDelta = (((float) this.mMargin) * f) + getPageQueueDelta(f) + getScaleDelta(view, f) + getRemainSpace(f);
            if (!Features.isEnabled(Features.IS_RTL)) {
                pageQueueDelta = -pageQueueDelta;
            }
            view.setTranslationX(pageQueueDelta);
        }
    }

    static {
        float f;
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_80) {
            f = 0.0f;
        } else {
            f = 0.15f;
        }
        SCALE_AMOUNT = f;
    }

    public void setTransformListener(ViewPager2.PageTransformer pageTransformer) {
        this.mTransformListener = pageTransformer;
    }

    public void setTransformRequested() {
        this.mTransformRequested = true;
    }

    public void transformPage(View view, float f) {
        if (this.mTransformRequested) {
            Log.d("PageTransformer", "perform requested transform", Integer.valueOf(view.getWidth()));
            this.mTransformRequested = false;
        }
        Optional.ofNullable(this.mTransformListener).ifPresent(new c(view, f, 1));
    }

    public void setPageMargin(int i2, int i7, int i8) {
    }
}
