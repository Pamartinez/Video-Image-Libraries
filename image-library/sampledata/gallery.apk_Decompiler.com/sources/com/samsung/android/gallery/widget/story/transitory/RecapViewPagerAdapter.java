package com.samsung.android.gallery.widget.story.transitory;

import android.view.View;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapViewPagerAdapter extends StoriesViewPagerAdapter {
    private int mParentDataCount;

    public RecapViewPagerAdapter(ViewPagerItemFactory viewPagerItemFactory) {
        super(viewPagerItemFactory);
    }

    public int getChildDataCount() {
        return 0;
    }

    public int getTotalCountForDisplay() {
        return (this.mParentDataCount + this.mDataCount) - 1;
    }

    public void setParentDataCount(int i2) {
        this.mParentDataCount = i2;
    }

    public void updateContentView(View view) {
        ViewMarginUtils.setHorizontalMargin(view.findViewById(R$id.content_parent), this.mSideMargin);
    }
}
