package com.samsung.android.gallery.widget.story.transitory;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ViewPagerListener extends ViewPager2.PageTransformer {
    boolean isPlayable() {
        return false;
    }

    void onPageSelected(int i2, int i7);

    void transformPage(View view, float f);

    void onChildPageSelected(int i2, int i7, MediaItem mediaItem) {
    }
}
