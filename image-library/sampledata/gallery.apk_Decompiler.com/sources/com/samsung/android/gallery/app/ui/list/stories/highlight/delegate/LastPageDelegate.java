package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.controller.story.SaveOnDemandStoryCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPageView;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import o6.r;
import o6.s;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LastPageDelegate extends Delegate {
    private final LastPageView mLastPageView;

    public LastPageDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        this.mLastPageView = new LastPageView(iStoryHighlightView);
    }

    private boolean handleSaveOnDemandStory() {
        if (!this.mView.getOptions().isOnDemandStory()) {
            return false;
        }
        new SaveOnDemandStoryCmd().addExecuteListener(new s(this, 0)).execute(this.mView.getPresenter(), Boolean.TRUE, this.mLastPageView.getEditedTitle());
        return true;
    }

    private void hide(boolean z, boolean z3) {
        Log.d(this.TAG, "hide");
        this.mLastPageView.hide(z3);
        this.mEventHandler.postEvent(Event.RELATED_VIEW_STATE_CHANGED, Boolean.FALSE);
        if (z) {
            this.mEventHandler.postEvent(Event.ON_OFF_OSD, Boolean.TRUE);
        } else {
            this.mEventHandler.postEvent(Event.UPDATE_OSD, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$0(Object[] objArr) {
        this.mLastPageView.stopNextPageTimer();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        this.mLastPageView.saveStoryFromOnDemand();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$2(Object[] objArr) {
        this.mLastPageView.resetCountDown();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$3(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(this.mLastPageView.isShowing());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$4(DataRequest dataRequest, Object[] objArr) {
        return this.mLastPageView.getPageDataHolder();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$5(DataRequest dataRequest, Object[] objArr) {
        return this.mLastPageView.getEntryEffectHelper();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$6() {
        if (!this.mLastPageView.isLastPosition() || !this.mEventHandler.isBottomSheetHidden()) {
            Log.d(this.TAG, "ignore show", Boolean.valueOf(this.mLastPageView.isLastPosition()), Boolean.valueOf(this.mEventHandler.isBottomSheetHidden()));
        } else {
            show(true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSaveOnDemandStory$7(Object obj) {
        this.mView.finish();
    }

    private void loadData(Runnable runnable) {
        this.mLastPageView.loadData(runnable);
    }

    /* access modifiers changed from: private */
    public void onStoryChanged(Object... objArr) {
        this.mLastPageView.reset();
    }

    /* access modifiers changed from: private */
    public void prepareStoryChange(Object... objArr) {
        hide(false, false);
    }

    private void show(boolean z) {
        Log.d(this.TAG, "show", Boolean.valueOf(z));
        this.mLastPageView.show(z);
        this.mEventHandler.postEvent(Event.RELATED_VIEW_STATE_CHANGED, Boolean.TRUE);
        this.mEventHandler.postEvent(Event.UPDATE_OSD, new Object[0]);
    }

    public void addListenEvent() {
        addEvent(Event.SLIDE_SHOW_DONE);
        addEvent(Event.BOTTOM_SHEET_STATE_CHANGED);
        addEvent(Event.MOVE_TO_HIGHLIGHT);
        addEvent(Event.VIEW_PAGER_SELECTED);
        addEvent(Event.CHANGE_STORY, new s(this, 1));
        addEvent(Event.PREPARE_CHANGE_STORY, new s(this, 2));
        addEvent(Event.LAST_PAGE_HIDE_SELF);
        addEvent(Event.LAST_PAGE_STOP_AUTO_SCROLL, new s(this, 3));
        addEvent(Event.SAVE_STORY_FROM_ON_DEMAND, new s(this, 4));
        addEvent(Event.LAST_PAGE_RESET_COUNTDOWN, new s(this, 5));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.RELATED_VIEW_VISIBLE, new r(this, 0));
        addRequestProvider(DataRequest.REQ_LAST_PAGE_DATA_HOLDER, new r(this, 1));
        addRequestProvider(DataRequest.REQ_LAST_PAGE_ENTRY_EFFECT_HELPER, new r(this, 2));
    }

    public void handleEvent(Event event, Object... objArr) {
        int i2;
        if (event == Event.SLIDE_SHOW_DONE) {
            loadData(new t(0, this));
        } else if (event != Event.BOTTOM_SHEET_STATE_CHANGED) {
            boolean z = false;
            if (event == Event.MOVE_TO_HIGHLIGHT || event == Event.LAST_PAGE_HIDE_SELF) {
                if (objArr.length > 0) {
                    i2 = objArr[0].intValue();
                } else {
                    i2 = -1;
                }
                if (i2 < 0) {
                    z = true;
                }
                hide(z, true);
            } else if (event != Event.VIEW_PAGER_SELECTED) {
            } else {
                if (this.mLastPageView.isLastPosition()) {
                    loadData((Runnable) null);
                } else if (this.mLastPageView.isShowing()) {
                    hide(false, true);
                    Log.d(this.TAG, "hide relatedView due to touching out side of related view");
                }
            }
        }
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        LastPageView lastPageView = this.mLastPageView;
        if (lastPageView != null && lastPageView.isShowing()) {
            this.mEventHandler.postEvent(Event.RELATED_VIEW_STATE_CHANGED, Boolean.TRUE);
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        this.mLastPageView.onConfigurationChanged();
    }

    public void initView(View view) {
        this.mLastPageView.initView((ViewGroup) view.findViewById(R.id.cover_viewpager_container));
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.mLastPageView.onApplyWindowInsets(windowInsets);
    }

    public boolean onBackPressed() {
        return handleSaveOnDemandStory();
    }

    public void onDataChangedOnUi() {
        this.mLastPageView.onDataChangedOnUi();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mLastPageView.destroy();
    }

    public void onHeaderUpdated() {
        this.mLastPageView.onHeaderUpdate();
    }

    public void onMultiWindowModeChanged(boolean z) {
        this.mLastPageView.onMultiWindowModeChanged(z);
    }

    public void onPause() {
        super.onPause();
        this.mLastPageView.pause();
    }

    public void onResume() {
        super.onResume();
        this.mLastPageView.resume();
    }
}
