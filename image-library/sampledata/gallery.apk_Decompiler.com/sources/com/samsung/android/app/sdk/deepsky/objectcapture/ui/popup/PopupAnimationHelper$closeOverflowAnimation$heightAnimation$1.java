package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.util.Size;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupAnimationHelper$closeOverflowAnimation$heightAnimation$1", "Landroid/view/animation/Animation;", "", "interpolatedTime", "Landroid/view/animation/Transformation;", "t", "Lme/x;", "applyTransformation", "(FLandroid/view/animation/Transformation;)V", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PopupAnimationHelper$closeOverflowAnimation$heightAnimation$1 extends Animation {
    final /* synthetic */ float $bottom;
    final /* synthetic */ View $mContentContainer;
    final /* synthetic */ View $mMainPanel;
    final /* synthetic */ Size $mMainPanelSize;
    final /* synthetic */ boolean $mOpenOverflowUpwards;
    final /* synthetic */ ImageButton $mOverflowButton;
    final /* synthetic */ OverflowPanel $mOverflowPanel;
    final /* synthetic */ Size $mOverflowPanelSize;
    final /* synthetic */ int $startHeight;
    final /* synthetic */ int $targetHeight;

    public PopupAnimationHelper$closeOverflowAnimation$heightAnimation$1(int i2, int i7, View view, boolean z, float f, View view2, Size size, ImageButton imageButton, OverflowPanel overflowPanel, Size size2) {
        this.$targetHeight = i2;
        this.$startHeight = i7;
        this.$mContentContainer = view;
        this.$mOpenOverflowUpwards = z;
        this.$bottom = f;
        this.$mMainPanel = view2;
        this.$mMainPanelSize = size;
        this.$mOverflowButton = imageButton;
        this.$mOverflowPanel = overflowPanel;
        this.$mOverflowPanelSize = size2;
    }

    public void applyTransformation(float f, Transformation transformation) {
        j.e(transformation, "t");
        int i2 = this.$targetHeight;
        int i7 = this.$startHeight;
        ViewUtils.setHeight(this.$mContentContainer, i7 + ((int) (f * ((float) (i2 - i7)))));
        if (this.$mOpenOverflowUpwards) {
            View view = this.$mContentContainer;
            view.setY(this.$bottom - ((float) view.getHeight()));
            this.$mMainPanel.setY((float) (this.$mContentContainer.getHeight() - this.$mMainPanelSize.getHeight()));
            this.$mOverflowButton.setY((float) (this.$mContentContainer.getHeight() - this.$mOverflowButton.getHeight()));
            this.$mOverflowPanel.setY((float) (this.$mContentContainer.getHeight() - this.$mOverflowPanelSize.getHeight()));
        }
    }
}
