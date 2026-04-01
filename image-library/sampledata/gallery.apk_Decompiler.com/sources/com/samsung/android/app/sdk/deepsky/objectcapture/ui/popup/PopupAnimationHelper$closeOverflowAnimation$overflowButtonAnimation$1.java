package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1", "Landroid/view/animation/Animation;", "", "interpolatedTime", "Landroid/view/animation/Transformation;", "t", "Lme/x;", "applyTransformation", "(FLandroid/view/animation/Transformation;)V", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PopupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1 extends Animation {
    final /* synthetic */ View $mContentContainer;
    final /* synthetic */ ImageButton $mOverflowButton;
    final /* synthetic */ float $overflowButtonStartX;
    final /* synthetic */ float $overflowButtonTargetX;
    final /* synthetic */ int $startWidth;
    final /* synthetic */ PopupAnimationHelper this$0;

    public PopupAnimationHelper$closeOverflowAnimation$overflowButtonAnimation$1(float f, float f5, PopupAnimationHelper popupAnimationHelper, View view, int i2, ImageButton imageButton) {
        this.$overflowButtonStartX = f;
        this.$overflowButtonTargetX = f5;
        this.this$0 = popupAnimationHelper;
        this.$mContentContainer = view;
        this.$startWidth = i2;
        this.$mOverflowButton = imageButton;
    }

    public void applyTransformation(float f, Transformation transformation) {
        int i2;
        j.e(transformation, "t");
        float f5 = this.$overflowButtonStartX;
        float a7 = C0212a.a(this.$overflowButtonTargetX, f5, f, f5);
        if (this.this$0.isInRTLMode()) {
            i2 = 0;
        } else {
            i2 = this.$mContentContainer.getWidth() - this.$startWidth;
        }
        this.$mOverflowButton.setX(a7 + ((float) i2));
    }
}
