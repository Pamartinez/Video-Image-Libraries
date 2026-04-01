package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupAnimationHelper$closeOverflowAnimation$widthAnimation$1", "Landroid/view/animation/Animation;", "", "interpolatedTime", "Landroid/view/animation/Transformation;", "t", "Lme/x;", "applyTransformation", "(FLandroid/view/animation/Transformation;)V", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PopupAnimationHelper$closeOverflowAnimation$widthAnimation$1 extends Animation {
    final /* synthetic */ float $left;
    final /* synthetic */ View $mContentContainer;
    final /* synthetic */ boolean $mIsClosedOpposites;
    final /* synthetic */ View $mMainPanel;
    final /* synthetic */ OverflowPanel $mOverflowPanel;
    final /* synthetic */ float $right;
    final /* synthetic */ int $startWidth;
    final /* synthetic */ int $targetWidth;
    final /* synthetic */ PopupAnimationHelper this$0;

    public PopupAnimationHelper$closeOverflowAnimation$widthAnimation$1(int i2, int i7, View view, float f, PopupAnimationHelper popupAnimationHelper, boolean z, float f5, View view2, OverflowPanel overflowPanel) {
        this.$targetWidth = i2;
        this.$startWidth = i7;
        this.$mContentContainer = view;
        this.$right = f;
        this.this$0 = popupAnimationHelper;
        this.$mIsClosedOpposites = z;
        this.$left = f5;
        this.$mMainPanel = view2;
        this.$mOverflowPanel = overflowPanel;
    }

    public void applyTransformation(float f, Transformation transformation) {
        j.e(transformation, "t");
        int i2 = this.$targetWidth;
        int i7 = this.$startWidth;
        ViewUtils.setWidth(this.$mContentContainer, i7 + ((int) (f * ((float) (i2 - i7)))));
        float width = this.$right - ((float) this.$mContentContainer.getWidth());
        if (this.this$0.isInRTLMode() != this.$mIsClosedOpposites) {
            width = this.$left;
        }
        this.$mContentContainer.setX(width);
        if (this.this$0.isInRTLMode()) {
            this.$mMainPanel.setX(0.0f);
            this.$mOverflowPanel.setX(0.0f);
            return;
        }
        this.$mMainPanel.setX((float) (this.$mContentContainer.getWidth() - this.$targetWidth));
        this.$mOverflowPanel.setX((float) (this.$mContentContainer.getWidth() - this.$startWidth));
    }
}
