package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextUtils.TruncateAt e;

    public /* synthetic */ d(int i2, TextUtils.TruncateAt truncateAt) {
        this.d = i2;
        this.e = truncateAt;
    }

    public final void onAnimationEnd(View view) {
        DrawerSlideAnimationManager.lambda$addWidthHeightAnimator$3(this.d, this.e, view);
    }
}
