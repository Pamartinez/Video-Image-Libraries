package com.samsung.android.gallery.app.ui.list.stories.recap.lastpage;

import com.samsung.android.gallery.app.controller.story.SaveRecapVideoCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.ILastPageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPagePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.recap.IRecapView;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapLastPagePresenter extends LastPagePresenter {
    public RecapLastPagePresenter(ILastPageView iLastPageView) {
        super(iLastPageView);
    }

    public PageDataLoader createPageDataLoader() {
        return new RecapPageDataLoader((IRecapView) this.mPageView.getParent());
    }

    public void handleAction(int i2, Object... objArr) {
        IStoryHighlightView parent = this.mPageView.getParent();
        if (i2 == 0) {
            parent.getEventHandler().postEvent(Event.REPLAY_VIDEO, new Object[0]);
            parent.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_RELATED_WATCH_AGAIN, String.valueOf(MediaItemStory.getStorySaType(parent.getHeaderItem())));
            Log.d(this.TAG, "handleAction : close");
        } else if (i2 == 1) {
            BlackboardUtils.publishBackKeyEvent(parent.getBlackboard());
            parent.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_RELATED_CLOSE);
            Log.d(this.TAG, "handleAction : close");
        } else if (i2 == 3) {
            new SaveRecapVideoCmd().execute(parent.getPresenter(), parent.getHeaderItem(), Boolean.TRUE);
        }
    }
}
