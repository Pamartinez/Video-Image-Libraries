package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.StoryThemeView;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import o6.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThemeViewDelegate extends Delegate {
    private final StoryThemeView<IStoryHighlightView> mThemeView;

    public ThemeViewDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        this.mThemeView = new StoryThemeView<>(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$0(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(this.mThemeView.isVisible());
    }

    public void addListenEvent() {
        addEvent(Event.SHOW_THEME_VIEW);
        addEvent(Event.HIDE_THEME_VIEW);
        addEvent(Event.SLIDE_SHOW_DONE);
        addEvent(Event.ON_BGM_CHANGED);
        addEvent(Event.ON_THEME_INITIALIZED);
        addEvent(Event.ON_THEME_CHANGED);
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.THEME_VIEW_VISIBLE, new m(3, this));
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        StoryThemeView<IStoryHighlightView> storyThemeView = this.mThemeView;
        if (storyThemeView != null) {
            storyThemeView.handleDensityChange(i2);
        }
    }

    public void handleEvent(Event event, Object... objArr) {
        if (event == Event.SHOW_THEME_VIEW) {
            if (!this.mEventHandler.isShowingRelatedView()) {
                this.mThemeView.setVisible(true);
                postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_THEME);
                this.mEventHandler.postEvent(Event.SHOW_THEME_OPTION, new Object[0]);
            }
        } else if (event == Event.HIDE_THEME_VIEW) {
            this.mThemeView.setVisible(false);
            this.mEventHandler.postEvent(Event.HIDE_THEME_OPTION, new Object[0]);
        } else if (event == Event.SLIDE_SHOW_DONE) {
            this.mThemeView.setVisible(false);
        } else if (event == Event.ON_BGM_CHANGED) {
            if (objArr != null) {
                BgmExtraInfo bgmExtraInfo = objArr[0];
                if (bgmExtraInfo instanceof BgmExtraInfo) {
                    this.mThemeView.onBgmChanged(bgmExtraInfo);
                }
            }
        } else if (event == Event.ON_THEME_INITIALIZED) {
            this.mThemeView.reset();
        } else if (event == Event.ON_THEME_CHANGED) {
            Log.d(this.TAG, "handleEvent#CHANGE_STORY_THEME");
            this.mThemeView.onThemeChanged();
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        StoryThemeView<IStoryHighlightView> storyThemeView = this.mThemeView;
        if (storyThemeView != null) {
            storyThemeView.handleResolutionChange(i2);
        }
    }

    public void initView(View view) {
        this.mThemeView.initView((ViewGroup) view.findViewById(R.id.main_layout));
    }

    public boolean onBackPressed() {
        if (this.mEventHandler.isBgmPickerVisible() || !this.mThemeView.isVisible()) {
            return false;
        }
        this.mEventHandler.postEvent(Event.HIDE_THEME_VIEW, new Object[0]);
        return true;
    }

    public void onDestroy() {
        this.mThemeView.reset();
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        StoryThemeView<IStoryHighlightView> storyThemeView = this.mThemeView;
        if (storyThemeView != null) {
            storyThemeView.onMultiWindowModeChanged(z);
        }
    }
}
