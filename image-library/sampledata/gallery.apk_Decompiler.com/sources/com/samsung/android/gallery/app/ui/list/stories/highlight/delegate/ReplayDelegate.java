package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.ReplayView;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import m7.c;
import o6.m;
import o6.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReplayDelegate extends Delegate {
    private ReplayView<IStoryHighlightView> mReplayView;

    public ReplayDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        ReplayView<IStoryHighlightView> replayView = new ReplayView<>(iStoryHighlightView);
        this.mReplayView = replayView;
        replayView.setOnReplayClickListener(new w(this, 4));
    }

    /* access modifiers changed from: private */
    public void hideRelatedView(Object... objArr) {
        this.mReplayView.hide();
        this.mEventHandler.postEvent(Event.RELATED_VIEW_STATE_CHANGED, Boolean.FALSE);
        this.mEventHandler.postEvent(Event.UPDATE_OSD, new Object[0]);
    }

    private boolean isDestroyed() {
        if (this.mReplayView == null) {
            return true;
        }
        return false;
    }

    private boolean isLastPosition() {
        int intValue = ((Integer) this.mEventHandler.requestData(DataRequest.VIEW_PAGER_CURRENT, -1)).intValue();
        if (this.mView.getMediaData() == null || intValue != this.mView.getMediaData().getCount() - 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$0(Object[] objArr) {
        show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$1(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(this.mReplayView.isShowing());
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (isBottomSheetHidden()) {
            this.mReplayView.resume();
        } else {
            this.mReplayView.pause();
        }
    }

    /* access modifiers changed from: private */
    public void onPageSelected(Object... objArr) {
        if (this.mReplayView.isShowing() && !isLastPosition()) {
            hideRelatedView(new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onReplayClicked(int i2) {
        if (isBottomSheetHidden()) {
            this.mEventHandler.postEvent(Event.MOVE_TO_HIGHLIGHT, Integer.valueOf(i2), Boolean.TRUE);
        }
    }

    private void show() {
        if (!this.mReplayView.isShowing()) {
            showInternal();
        }
    }

    private void showInternal() {
        if (!isDestroyed()) {
            if (this.mReplayView.isShowing() || !isLastPosition() || !isBottomSheetHidden()) {
                String str = this.TAG;
                Log.d(str, "ignore relatedView = " + this.mReplayView.isShowing(), Boolean.valueOf(isLastPosition()), Boolean.valueOf(isBottomSheetHidden()));
                return;
            }
            this.mReplayView.show(true);
            this.mEventHandler.postEvent(Event.RELATED_VIEW_STATE_CHANGED, Boolean.TRUE);
            this.mEventHandler.postEvent(Event.UPDATE_OSD, new Object[0]);
        }
    }

    public void addListenEvent() {
        addEvent(Event.SLIDE_SHOW_DONE, new w(this, 0));
        addEvent(Event.BOTTOM_SHEET_STATE_CHANGED, new w(this, 1));
        addEvent(Event.MOVE_TO_HIGHLIGHT, new w(this, 2));
        addEvent(Event.VIEW_PAGER_SELECTED, new w(this, 3));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.RELATED_VIEW_VISIBLE, new m(2, this));
    }

    public void handleResolutionChange(int i2) {
        Optional.ofNullable(this.mReplayView).ifPresent(new c(29));
    }

    public void initView(View view) {
        this.mReplayView.initView((ViewGroup) view.findViewById(R.id.cover_viewpager_container));
    }

    public boolean isBottomSheetHidden() {
        return this.mEventHandler.isBottomSheetHidden();
    }

    public void onDestroy() {
        this.mReplayView.destroy();
        this.mReplayView = null;
    }

    public void onPause() {
        super.onPause();
        this.mReplayView.pause();
    }

    public void onResume() {
        super.onResume();
        this.mReplayView.resume();
    }
}
