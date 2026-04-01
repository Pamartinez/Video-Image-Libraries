package com.samsung.android.gallery.app.ui.list.stories.recap.delegate;

import H3.l;
import H6.i;
import H6.j;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPagePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPageView;
import com.samsung.android.gallery.app.ui.list.stories.recap.IRecapView;
import com.samsung.android.gallery.app.ui.list.stories.recap.lastpage.RecapLastPagePresenter;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapLastPageDelegate extends Delegate {
    private final LastPageView mLastPageView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RecapLastPageView extends LastPageView {
        public RecapLastPageView(IStoryHighlightView iStoryHighlightView) {
            super(iStoryHighlightView);
        }

        public LastPagePresenter createPresenter() {
            return new RecapLastPagePresenter(this);
        }
    }

    public RecapLastPageDelegate(IRecapView iRecapView) {
        super(iRecapView);
        this.mLastPageView = new RecapLastPageView(iRecapView);
    }

    private void hide(boolean z) {
        Log.v(this.TAG, "hide", Boolean.valueOf(z));
        this.mLastPageView.hide(z);
        this.mEventHandler.postEvent(Event.ON_OFF_OSD, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$0(Object[] objArr) {
        this.mLastPageView.saveStoryFromOnDemand();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        this.mLastPageView.replay();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$2(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(this.mLastPageView.isShowing());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$3(DataRequest dataRequest, Object[] objArr) {
        return this.mLastPageView.getPageDataHolder();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$4(DataRequest dataRequest, Object[] objArr) {
        return this.mLastPageView.getEntryEffectHelper();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$5() {
        show(true);
    }

    private void loadData(Runnable runnable) {
        this.mLastPageView.loadData(runnable);
    }

    private void show(boolean z) {
        Log.v(this.TAG, "show", Boolean.valueOf(z));
        this.mLastPageView.show(z);
        this.mEventHandler.postEvent(Event.UPDATE_OSD, new Object[0]);
    }

    public void addListenEvent() {
        addEvent(Event.SLIDE_SHOW_DONE);
        addEvent(Event.REPLAY_VIDEO);
        addEvent(Event.SAVE_STORY_FROM_ON_DEMAND, new i(this, 0));
        addEvent(Event.LAST_PAGE_REPLAY, new i(this, 1));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.RELATED_VIEW_VISIBLE, new j(this, 0));
        addRequestProvider(DataRequest.REQ_LAST_PAGE_DATA_HOLDER, new j(this, 1));
        addRequestProvider(DataRequest.REQ_LAST_PAGE_ENTRY_EFFECT_HELPER, new j(this, 2));
    }

    public void handleEvent(Event event, Object... objArr) {
        Log.d(this.TAG, "handleEvent", event);
        if (event == Event.SLIDE_SHOW_DONE) {
            loadData(new l(2, this));
        } else if (event == Event.REPLAY_VIDEO) {
            hide(true);
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        this.mLastPageView.onConfigurationChanged();
    }

    public void initView(View view) {
        this.mLastPageView.initView((ViewGroup) view.findViewById(R.id.last_page_container));
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.mLastPageView.onApplyWindowInsets(windowInsets);
    }

    public void onDataChangedOnUi() {
        this.mLastPageView.onDataChangedOnUi();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mLastPageView.destroy();
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
