package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageLayoutManager extends LinearLayoutManager {
    private boolean mScrollDisabled;

    public PageLayoutManager(Context context, int i2, boolean z) {
        super(context, i2, z);
    }

    public void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
        super.calculateExtraLayoutSpace(state, iArr);
        iArr[0] = iArr[0] + 100;
        iArr[1] = iArr[1] + 100;
    }

    public boolean canScrollHorizontally() {
        if (this.mScrollDisabled || !super.canScrollHorizontally()) {
            return false;
        }
        return true;
    }

    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            return super.scrollHorizontallyBy(i2, recycler, state);
        } catch (NullPointerException e) {
            Log.e("PageLayoutManager", "scrollHorizontallyBy e=" + e.getMessage());
            return 0;
        }
    }
}
