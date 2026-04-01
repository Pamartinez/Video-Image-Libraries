package com.samsung.android.gallery.app.ui.list.stories.pictures.builder;

import android.widget.RelativeLayout;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface LayoutBuilder {
    void buildLayout(LayoutInfo layoutInfo, int i2, RelativeLayout.LayoutParams layoutParams);

    int getTimeViewSpace(int i2);

    float getVerticalGapRatio();

    float getWidthRatio(LayoutInfo layoutInfo);

    void init(DimenValues dimenValues);
}
