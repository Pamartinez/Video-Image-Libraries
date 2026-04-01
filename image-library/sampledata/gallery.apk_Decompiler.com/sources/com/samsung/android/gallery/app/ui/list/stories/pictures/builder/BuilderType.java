package com.samsung.android.gallery.app.ui.list.stories.pictures.builder;

import android.widget.RelativeLayout;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum BuilderType {
    SINGLE(new SingleGroup()),
    TWO(new DoubleGroup()),
    THREE(new TripleGroup());
    
    LayoutBuilder mBuilder;

    private BuilderType(LayoutBuilder layoutBuilder) {
        this.mBuilder = layoutBuilder;
    }

    public static BuilderType get(DimenValues dimenValues) {
        return get(1, dimenValues);
    }

    private BuilderType init(DimenValues dimenValues) {
        this.mBuilder.init(dimenValues);
        return this;
    }

    public void buildLayout(LayoutInfo layoutInfo, int i2, RelativeLayout.LayoutParams layoutParams) {
        this.mBuilder.buildLayout(layoutInfo, i2, layoutParams);
    }

    public int getTimeViewSpace(int i2) {
        return this.mBuilder.getTimeViewSpace(i2);
    }

    public float getVerticalGapRatio() {
        return this.mBuilder.getVerticalGapRatio();
    }

    public float getWidthRatio(LayoutInfo layoutInfo) {
        return this.mBuilder.getWidthRatio(layoutInfo);
    }

    public static BuilderType get(int i2, DimenValues dimenValues) {
        BuilderType builderType;
        if (i2 == 2) {
            builderType = TWO;
        } else if (i2 == 3) {
            builderType = THREE;
        } else if (i2 == 1) {
            builderType = SINGLE;
        } else {
            builderType = SINGLE;
        }
        return builderType.init(dimenValues);
    }
}
