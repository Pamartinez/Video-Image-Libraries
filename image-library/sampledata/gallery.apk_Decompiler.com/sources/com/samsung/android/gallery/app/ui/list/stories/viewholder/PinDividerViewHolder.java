package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinDividerViewHolder extends PinViewHolder {
    public PinDividerViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void preparePinItemAnim() {
        if (!this.mPinAnimPrepared) {
            this.mPinAnimPrepared = true;
            ViewUtils.setAlpha(getRootView(), 0.0f);
        }
    }

    public void resetPinItemAnim() {
        if (this.mPinAnimPrepared) {
            this.mPinAnimPrepared = false;
            ViewUtils.setAlpha(getRootView(), 1.0f);
        }
    }

    public void startPinItemAnim() {
        if (this.mPinAnimPrepared) {
            getRootView().animate().alpha(1.0f).setDuration(200).start();
            String str = this.TAG;
            Log.d(str, "startPinItemAnim=Divider#" + getAdapterPosition());
        }
    }

    public void bindImage(Bitmap bitmap) {
    }
}
