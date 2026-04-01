package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ StoryHighlightViewPagerV2.EndProgressHandler d;

    public /* synthetic */ d(StoryHighlightViewPagerV2.EndProgressHandler endProgressHandler) {
        this.d = endProgressHandler;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$createFakeProgressAnimator$0(valueAnimator);
    }
}
