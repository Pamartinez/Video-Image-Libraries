package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import O3.l;
import com.samsung.android.gallery.app.controller.story.SaveOnDemandStoryCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.LastPageDataHolder;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import n4.C0491c;
import o4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LastPagePresenter {
    protected final String TAG = getClass().getSimpleName();
    private final AtomicBoolean mDirty = new AtomicBoolean(true);
    private final LastPageDataHolder mLastPageDataHolder = new LastPageDataHolder();
    private final PageDataLoader mPageDataLoader;
    protected final ILastPageView mPageView;

    public LastPagePresenter(ILastPageView iLastPageView) {
        this.mPageView = iLastPageView;
        this.mPageDataLoader = createPageDataLoader();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$0(Runnable runnable, Boolean bool) {
        onDataLoaded(bool.booleanValue());
        Optional.ofNullable(runnable).ifPresent(new l(0));
    }

    private void moveToHighlight(int i2) {
        String str;
        EventHandler eventHandler = this.mPageView.getParent().getEventHandler();
        if (eventHandler.isBottomSheetHidden()) {
            this.mPageView.hide(true);
            if (i2 > -1) {
                str = "replay";
            } else {
                str = "touchOutside";
            }
            saveFocusPosition(0, false, str);
            eventHandler.postEvent(Event.MOVE_TO_HIGHLIGHT, Integer.valueOf(i2), Boolean.TRUE);
        }
    }

    private void onDataLoaded(boolean z) {
        this.mDirty.set(false);
        this.mPageView.updateDataAndUi();
    }

    public PageDataLoader createPageDataLoader() {
        return new PageDataLoader(this.mPageView.getParent());
    }

    public void destroy() {
        this.mLastPageDataHolder.destroy();
    }

    public int getFocusPosition() {
        return this.mLastPageDataHolder.getFocusPosition();
    }

    public LastPageDataHolder getPageDataHolder() {
        return this.mLastPageDataHolder;
    }

    public ArrayList<PageItem> getPageItems() {
        return this.mPageDataLoader.getPageItems();
    }

    public void handleAction(int i2, Object... objArr) {
        int i7;
        IStoryHighlightView parent = this.mPageView.getParent();
        if (i2 == 0) {
            moveToHighlight(0);
            parent.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_RELATED_WATCH_AGAIN, String.valueOf(MediaItemStory.getStorySaType(parent.getHeaderItem())));
            Log.d(this.TAG, "handleAction : close");
        } else if (i2 == 1) {
            BlackboardUtils.publishBackKeyEvent(parent.getBlackboard());
            parent.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_RELATED_CLOSE);
            Log.d(this.TAG, "handleAction : close");
        } else if (i2 == 2) {
            if (objArr == null || objArr.length <= 0) {
                i7 = -1;
            } else {
                i7 = objArr[0].intValue();
            }
            moveToHighlight(i7);
            Log.d(this.TAG, "handleAction : moveTo", Integer.valueOf(i7));
        } else if (i2 == 3) {
            new SaveOnDemandStoryCmd().addExecuteListener(new a(7, parent)).execute(parent.getPresenter(), Boolean.FALSE, this.mPageView.getEditedTitle());
        }
    }

    public boolean hasRelatedPage() {
        if (this.mPageDataLoader.getPageItems().size() > 1) {
            return true;
        }
        return false;
    }

    public void loadData(Runnable runnable) {
        boolean z;
        String str = this.TAG;
        Boolean valueOf = Boolean.valueOf(this.mDirty.get());
        if (runnable != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(str, "loadData", valueOf, Boolean.valueOf(z));
        if (this.mDirty.get()) {
            this.mPageDataLoader.loadData(new C0491c(12, this, runnable));
        } else {
            Optional.ofNullable(runnable).ifPresent(new l(0));
        }
    }

    public void onDataChangedOnUi() {
        this.mDirty.set(true);
    }

    public void reset() {
        this.mPageDataLoader.reset();
        this.mLastPageDataHolder.reset();
    }

    public void resetCountDown() {
        this.mLastPageDataHolder.resetCountDown();
    }

    public void saveFocusPosition(int i2, String str) {
        saveFocusPosition(i2, true, str);
    }

    public void saveFocusPosition(int i2, boolean z, String str) {
        this.mLastPageDataHolder.saveFocusPosition(i2, z, str);
    }
}
