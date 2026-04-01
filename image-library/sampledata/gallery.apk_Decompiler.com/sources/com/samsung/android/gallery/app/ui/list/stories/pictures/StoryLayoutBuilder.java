package com.samsung.android.gallery.app.ui.list.stories.pictures;

import android.content.Context;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.app.ui.list.stories.pictures.builder.BuilderType;
import com.samsung.android.gallery.app.ui.list.stories.pictures.builder.DimenValues;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.module.dataset.tables.ChapterIndexer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryLayoutBuilder implements ChapterIndexer.LayoutLookup {
    private final DimenValues mDimenValues;

    public StoryLayoutBuilder(Context context) {
        this.mDimenValues = new DimenValues(context);
    }

    public void buildImageLayout(LayoutInfo layoutInfo, int i2, RelativeLayout.LayoutParams layoutParams) {
        BuilderType.get(layoutInfo.groupType, this.mDimenValues).buildLayout(layoutInfo, i2, layoutParams);
    }

    public int getTimeViewSpace(int i2) {
        return BuilderType.get(this.mDimenValues).getTimeViewSpace(i2);
    }

    public float getVerticalGapRatio() {
        return BuilderType.get(this.mDimenValues).getVerticalGapRatio();
    }

    public float getWidthRatio(LayoutInfo layoutInfo) {
        return BuilderType.get(layoutInfo.groupType, this.mDimenValues).getWidthRatio(layoutInfo);
    }
}
