package com.samsung.android.gallery.widget.pinch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPinchAdapter {
    int getViewHolderMargin(int i2);

    void notifyItemChanged(int i2);

    void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2);

    void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2, int i7);

    void stopPreview(boolean z);

    void updateExtraViewHolderMargin();
}
