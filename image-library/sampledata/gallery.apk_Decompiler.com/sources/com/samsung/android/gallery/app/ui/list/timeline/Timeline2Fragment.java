package com.samsung.android.gallery.app.ui.list.timeline;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.timeline.ITimelineView2;
import com.samsung.android.gallery.app.ui.list.timeline.Timeline2Presenter;
import com.samsung.android.gallery.app.ui.list.timeline.quicksearch.QuickSearchDelegate;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Timeline2Fragment<V extends ITimelineView2, P extends Timeline2Presenter> extends TimelineFragment<V, P> implements ITimelineView2 {
    private final QuickSearchDelegate mQuickSearchDelegate = new QuickSearchDelegate(this);

    public void bindView(View view) {
        super.bindView(view);
        this.mQuickSearchDelegate.bindView(view);
    }

    public void closeMediaData() {
        super.closeMediaData();
        this.mQuickSearchDelegate.closeQuickSearchData();
    }

    public int getLayoutId() {
        return R.layout.fragment_timeline_layout_v2;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mQuickSearchDelegate.unbindView();
    }

    public void openMediaData() {
        super.openMediaData();
        this.mQuickSearchDelegate.openQuickSearchData();
    }

    public void updateQuickSearchDateFilterCard() {
        this.mQuickSearchDelegate.updateDateFilterCard();
    }

    public void updateQuickSearchViewVisibility() {
        this.mQuickSearchDelegate.updateContainerVisibility();
    }

    public Timeline2Presenter createPresenter(ITimelineView2 iTimelineView2) {
        return new Timeline2Presenter(this.mBlackboard, iTimelineView2);
    }
}
