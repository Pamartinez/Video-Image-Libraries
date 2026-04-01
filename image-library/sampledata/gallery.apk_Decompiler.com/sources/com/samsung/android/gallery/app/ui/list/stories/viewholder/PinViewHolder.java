package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PinViewHolder extends ImageTitleViewHolder {
    protected boolean mPinAnimPrepared;

    public PinViewHolder(View view, int i2) {
        super(view, i2);
    }

    public abstract void preparePinItemAnim();

    public void recycle() {
        super.recycle();
        resetPinItemAnim();
    }

    public abstract void resetPinItemAnim();

    public abstract void startPinItemAnim();
}
