package com.samsung.android.gallery.widget.story.transitory;

import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.widget.R$id;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapViewPagerDelegate extends ViewPagerDelegate {
    private int mParentDataCount;

    public StoriesViewPagerAdapter createAdapter(ViewPagerItemFactory viewPagerItemFactory) {
        RecapViewPagerAdapter recapViewPagerAdapter = new RecapViewPagerAdapter(viewPagerItemFactory);
        recapViewPagerAdapter.setParentDataCount(this.mParentDataCount);
        return recapViewPagerAdapter;
    }

    public StoriesViewPager createViewPager(View view) {
        return new RecapViewPager((ViewGroup) view.findViewById(R$id.viewpager_parent));
    }

    public void setParentDataCount(int i2) {
        this.mParentDataCount = i2;
        StoriesViewPagerAdapter storiesViewPagerAdapter = this.mAdapter;
        if (storiesViewPagerAdapter instanceof RecapViewPagerAdapter) {
            ((RecapViewPagerAdapter) storiesViewPagerAdapter).setParentDataCount(i2);
        }
    }
}
