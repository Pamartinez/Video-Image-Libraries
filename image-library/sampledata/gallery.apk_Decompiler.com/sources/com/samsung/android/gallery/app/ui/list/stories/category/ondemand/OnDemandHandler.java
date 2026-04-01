package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import C3.i;
import J5.c;
import a6.C0420c;
import com.samsung.android.gallery.app.ui.list.search.toolbar.BaseSearchToolbarBehavior;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.StoryLauncher;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesCategory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandHandler {
    private boolean mIsExecutedStoryHighlight = false;
    private final StoriesOnDemandDelegate mOnDemandDelegate;
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();
    private final IOnDemandFloatingView mView;

    public OnDemandHandler(IOnDemandFloatingView iOnDemandFloatingView) {
        this.mView = iOnDemandFloatingView;
        this.mOnDemandDelegate = new StoriesOnDemandDelegate(iOnDemandFloatingView);
    }

    private int getDataPosition(MediaItem mediaItem, boolean z) {
        MediaData open = MediaDataFactory.getInstance(this.mView.getBlackboard()).open("location://story/albums");
        int i2 = 0;
        if (!z && open != null) {
            try {
                MediaDataStoriesCategory mediaDataStoriesCategory = (MediaDataStoriesCategory) open.getChildMediaData("location://stories/category/creation");
                if (mediaDataStoriesCategory != null) {
                    i2 = mediaDataStoriesCategory.findPosition((long) MediaItemStory.getStoryId(mediaItem));
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (open != null) {
            open.close();
        }
        return i2;
        throw th;
    }

    private void handleRelationConfirmed(boolean z) {
        if (z) {
            String str = (String) this.mView.getBlackboard().pop("data://user/storyGenQuery", "");
            this.mView.getBlackboard().erase("data://user/storyGenQuery");
            handleToolbarEvent(SearchToolbarEvent.obtain(106, Boolean.TRUE));
            if (this.mView.isViewActive()) {
                this.mOnDemandDelegate.continueOnDemand(str);
            } else {
                ThreadUtil.postOnUiThreadDelayed(new C0420c(this, str, 1), 100);
            }
        }
    }

    private void initToolbar() {
        if (Features.isEnabled(Features.SUPPORT_ON_DEMAND_STORY) && !this.mView.isDestroyed() && this.mSearchToolbarDelegate.isEmpty()) {
            SearchToolbarDelegate buildForStory = SearchToolbarDelegateFactory.buildForStory(this.mView, new c(9, this));
            this.mSearchToolbarDelegate = buildForStory;
            this.mOnDemandDelegate.setSearchToolbarDelegate(buildForStory);
            Log.d("OnDemandHandler", "initOnDemandToolbar");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleRelationConfirmed$3(String str) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(110, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BaseSearchToolbarBehavior lambda$initToolbar$0() {
        return new BottomStoryBehavior(this.mView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startOnDemandStory$2(String str) {
        this.mOnDemandDelegate.startOnDemand(str);
    }

    private void moveToOnDemandStoryHighlight(Object obj) {
        Log.d("OnDemandHandler", "moveToOnDemandStory");
        Object[] objArr = (Object[]) obj;
        ArrayList arrayList = (ArrayList) objArr[0];
        boolean booleanValue = ((Boolean) objArr[1]).booleanValue();
        if (arrayList != null && !arrayList.isEmpty() && arrayList.get(0) != null) {
            handleToolbarEvent(SearchToolbarEvent.obtain(104));
            MediaItem mediaItem = (MediaItem) arrayList.get(0);
            new StoryLauncher(this.mView).setData(mediaItem, getDataPosition(mediaItem, booleanValue)).setFromOnDemand(true).execute();
            BlackboardUtils.removeOtherTabs(this.mView.getBlackboard(), this.mView.getLocationKey());
            this.mIsExecutedStoryHighlight = true;
        }
    }

    private void onRecommendItemClick(String str, int i2) {
        updateRecommendData();
        handleToolbarEvent(SearchToolbarEvent.obtain(105, str));
    }

    private void startOnDemandStory(String str) {
        handleToolbarEvent(SearchToolbarEvent.obtain(106, Boolean.TRUE));
        handleToolbarEvent(SearchToolbarEvent.obtain(103, Boolean.FALSE));
        ThreadUtil.postOnUiThreadDelayed(new C0420c(this, str, 0), 200);
    }

    private void updateRecommendData() {
        ThreadUtil.postOnUiThreadDelayed(new i(17), 300);
    }

    public void destroy() {
        this.mOnDemandDelegate.onDestroy();
    }

    public void destroyView() {
        this.mOnDemandDelegate.onDestroyView();
    }

    public SearchToolbarDelegate getSearchToolbarDelegate() {
        return this.mSearchToolbarDelegate;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1137) {
            startOnDemandStory((String) eventMessage.obj);
            return false;
        } else if (i2 == 1139) {
            Boolean bool = (Boolean) eventMessage.obj;
            handleRelationConfirmed(bool.booleanValue());
            Log.i("OnDemandHandler", "relation confirmed", bool);
            return true;
        } else if (i2 == 1140) {
            Object[] objArr = (Object[]) eventMessage.obj;
            onRecommendItemClick((String) objArr[0], ((Integer) objArr[1]).intValue());
            return true;
        } else if (i2 == 1138) {
            moveToOnDemandStoryHighlight(eventMessage.obj);
            return true;
        } else if (i2 != 1142) {
            return this.mSearchToolbarDelegate.handleEvent(eventMessage);
        } else {
            handleToolbarEvent(SearchToolbarEvent.obtain(106, Boolean.TRUE));
            this.mOnDemandDelegate.continueOnDemand((String) eventMessage.obj);
            return true;
        }
    }

    public void handleToolbarEvent(SearchToolbarEvent searchToolbarEvent) {
        this.mSearchToolbarDelegate.handleInternalEvent(searchToolbarEvent);
    }

    public boolean isOnDemandRunning() {
        return this.mOnDemandDelegate.isRunning();
    }

    public boolean onBackPressed() {
        Boolean bool = (Boolean) this.mSearchToolbarDelegate.getData(SearchToolbarDataKey.IS_BACK_PRESS_CONSUMED);
        if (this.mOnDemandDelegate.onBackPressed()) {
            return true;
        }
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    public void onConfigurationChanged() {
        this.mOnDemandDelegate.onConfigurationChanged();
    }

    public void onHiddenChanged(boolean z) {
        handleToolbarEvent(SearchToolbarEvent.obtain(9, Boolean.valueOf(z)));
    }

    public void pause() {
        this.mOnDemandDelegate.onPause();
    }

    public void resume() {
        this.mOnDemandDelegate.onResume();
        if (this.mIsExecutedStoryHighlight) {
            this.mIsExecutedStoryHighlight = false;
            this.mView.finish();
        }
    }

    public void viewCreated() {
        initToolbar();
    }
}
