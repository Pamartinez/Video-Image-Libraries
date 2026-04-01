package com.samsung.android.gallery.widget.livemotion.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ViewPagerCallback {
    boolean isPlayable();

    void onClick();

    void onPageScrolled(ViewPagerScrolledValues viewPagerScrolledValues);

    void onPageSelected(int i2, int i7);

    boolean onPreClick();

    void onPreSlideShowDone();

    void onSlideShowDone();

    void onViewPagerIdleState();

    void onZoomState(boolean z);
}
