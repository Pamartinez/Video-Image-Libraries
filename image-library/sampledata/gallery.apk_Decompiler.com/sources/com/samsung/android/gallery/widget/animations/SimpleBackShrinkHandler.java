package com.samsung.android.gallery.widget.animations;

import android.view.animation.PathInterpolator;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.scs.base.StatusCodes;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SimpleBackShrinkHandler extends SimpleShrinkHandler {
    public SimpleBackShrinkHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        super(simpleShrinkView, listViewHolder);
        this.mBackgroundColorThreshold = 1.0f;
        this.mDuration = StatusCodes.INPUT_MISSING;
        this.mTimeInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
    }

    public float getCornerRadius() {
        ListViewHolder listViewHolder = this.mViewHolder;
        if (listViewHolder != null) {
            return listViewHolder.getCornerRadius();
        }
        return 0.0f;
    }

    public void showInternal() {
        this.mState.set(2);
        if (this.mWithRadius) {
            setOutlineProvider();
        }
        if (this.mPostAnim) {
            ThreadUtil.postOnUiThread(new c(1, this));
        } else {
            startShrinkAnimation();
        }
    }
}
