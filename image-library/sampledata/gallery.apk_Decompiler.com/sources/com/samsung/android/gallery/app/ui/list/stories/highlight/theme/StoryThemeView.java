package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import B4.c;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import h3.b;
import java.util.Arrays;
import o4.a;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryThemeView<V extends IStoryHighlightView> {
    private StoriesThemeViewAdapter mAdapter;
    private final EventHandler mEventHandler;
    private boolean mIsVisible;
    private RecyclerView mRecyclerView;
    private View mThemeViewLayout;
    private final V mView;

    public StoryThemeView(V v) {
        this.mView = v;
        this.mEventHandler = v.getEventHandler();
    }

    private MediaItem getCurrentItem() {
        PreviewViewHolder currentViewHolder = this.mEventHandler.getCurrentViewHolder();
        if (currentViewHolder == null || currentViewHolder.getMediaItem() == null) {
            return this.mView.getHeaderItem();
        }
        return currentViewHolder.getMediaItem();
    }

    private AnalyticsEventId getEventId(int i2) {
        Filter filter = this.mAdapter.getFilter(i2);
        if (this.mAdapter.isCustom(i2)) {
            return AnalyticsEventId.EVENT_THEME_CUSTOM;
        }
        if (filter.noneFilter()) {
            return AnalyticsEventId.EVENT_THEME_ORIGINAL;
        }
        return EffectTheme.getMatchedTheme(filter).getEventId();
    }

    private EffectTheme getMatchedTheme(Filter filter) {
        return (EffectTheme) Arrays.stream(EffectTheme.values()).filter(new b(8, filter)).findFirst().orElse(EffectTheme.values()[0]);
    }

    private void initViewInternal() {
        RecyclerView recyclerView = (RecyclerView) this.mThemeViewLayout.findViewById(R.id.filter_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new StoriesThemeLayoutManager(recyclerView.getContext(), this.mRecyclerView));
        if (this.mAdapter == null) {
            StoriesThemeViewAdapter storiesThemeViewAdapter = new StoriesThemeViewAdapter(this.mView);
            this.mAdapter = storiesThemeViewAdapter;
            storiesThemeViewAdapter.setOnItemClickListener(new a(19, this));
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme) && this.mEventHandler.isCustomTheme()) {
            this.mAdapter.moveToCustomTheme();
        }
    }

    private boolean isCustomTheme(BgmExtraInfo bgmExtraInfo) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme)) {
            return this.mEventHandler.isCustomTheme();
        }
        return CustomThemeHelper.saveCustomTheme(this.mEventHandler.getEffectTheme(), this.mEventHandler.getFilter(), bgmExtraInfo);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMatchedTheme$2(Filter filter, EffectTheme effectTheme) {
        if (effectTheme.getFilter() == filter) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVisible$0() {
        lambda$setVisible$1(true);
    }

    /* access modifiers changed from: private */
    public void onItemSelected(int i2) {
        if (this.mAdapter.isCustom(i2)) {
            Filter filter = this.mAdapter.getFilter(i2);
            EffectTheme effectTheme = this.mAdapter.getEffectTheme(i2);
            if (effectTheme == null) {
                effectTheme = getMatchedTheme(this.mAdapter.getFilter(i2));
            }
            this.mEventHandler.lambda$postEvent$0(Event.CHANGE_THEME, effectTheme, filter, this.mAdapter.getBgmExtraInfo(i2));
        } else {
            Filter filter2 = this.mAdapter.getFilter(i2);
            if (filter2.noneFilter()) {
                this.mEventHandler.lambda$postEvent$0(Event.CHANGE_FILTER, filter2);
            } else {
                this.mEventHandler.lambda$postEvent$0(Event.CHANGE_THEME, getMatchedTheme(this.mAdapter.getFilter(i2)), null, null);
            }
        }
        this.mEventHandler.postEvent(Event.NOTIFY_GLOBAL_THEME_CHANGED, new Object[0]);
        this.mView.postAnalyticsLog(getEventId(i2));
    }

    /* access modifiers changed from: private */
    /* renamed from: setThemeViewVisibility */
    public void lambda$setVisible$1(boolean z) {
        int i2;
        View view = this.mThemeViewLayout;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        ViewUtils.setVisibility(view, i2);
    }

    private void updateThemeView(BgmExtraInfo bgmExtraInfo) {
        StoriesThemeViewAdapter storiesThemeViewAdapter = this.mAdapter;
        if (storiesThemeViewAdapter != null) {
            storiesThemeViewAdapter.reset();
            this.mAdapter.updateThemeItem(getCurrentItem(), true);
            if (isCustomTheme(bgmExtraInfo)) {
                this.mAdapter.notifyUpdateCustomThemeItem(true);
                this.mAdapter.moveToCustomTheme();
                return;
            }
            this.mAdapter.notifyUpdateCustomThemeItem(false);
            StoriesThemeViewAdapter storiesThemeViewAdapter2 = this.mAdapter;
            storiesThemeViewAdapter2.updateFocusedViewPosition(storiesThemeViewAdapter2.getFocusedPosition());
        }
    }

    private void updateWindowInset() {
        int i2;
        View view = this.mThemeViewLayout;
        if (view != null) {
            if (!this.mView.isInMultiWindowMode()) {
                i2 = WindowUtils.getSystemInsetsBottom(this.mThemeViewLayout.getRootWindowInsets());
            } else {
                i2 = 0;
            }
            ViewMarginUtils.setBottomPadding(view, i2);
        }
    }

    public void handleDensityChange(int i2) {
        updateWindowInset();
        View view = this.mThemeViewLayout;
        if (view != null) {
            View inflate = LayoutInflater.from(this.mThemeViewLayout.getContext()).inflate(R.layout.stories_filter_layout, (ViewGroup) this.mThemeViewLayout.getParent(), false);
            ViewUtils.replaceView((ViewGroup) view.getParent(), this.mThemeViewLayout, inflate);
            this.mThemeViewLayout = inflate;
            if (this.mIsVisible) {
                this.mIsVisible = false;
                this.mRecyclerView = null;
                setVisible(true);
            }
        }
    }

    public void handleResolutionChange(int i2) {
        updateWindowInset();
    }

    public void initView(ViewGroup viewGroup) {
        this.mThemeViewLayout = ((ViewStub) viewGroup.findViewById(R.id.stories_filter_stub)).inflate();
        updateWindowInset();
    }

    public boolean isVisible() {
        return this.mIsVisible;
    }

    public void onBgmChanged(BgmExtraInfo bgmExtraInfo) {
        Log.d("StoryFilterView", "onBgmChanged");
        updateThemeView(bgmExtraInfo);
        this.mEventHandler.postEvent(Event.NOTIFY_GLOBAL_THEME_CHANGED, new Object[0]);
    }

    public void onMultiWindowModeChanged(boolean z) {
        updateWindowInset();
    }

    public void onThemeChanged() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme)) {
            updateThemeView((BgmExtraInfo) null);
            Log.d("StoryFilterView", "onThemeChanged");
        }
    }

    public void reset() {
        StoriesThemeViewAdapter storiesThemeViewAdapter = this.mAdapter;
        if (storiesThemeViewAdapter != null) {
            storiesThemeViewAdapter.reset();
        }
    }

    public void setVisible(boolean z) {
        if (this.mIsVisible != z) {
            this.mIsVisible = z;
            if (this.mRecyclerView == null) {
                initViewInternal();
            }
            float f = 0.0f;
            if (z) {
                this.mAdapter.updateThemeItem(getCurrentItem(), false);
                this.mRecyclerView.setAdapter(this.mAdapter);
                this.mRecyclerView.scrollToPosition(this.mAdapter.getFocusedPosition());
                ViewUtils.setAlpha(this.mThemeViewLayout, 0.0f);
            }
            ViewPropertyAnimator animate = this.mThemeViewLayout.animate();
            if (z) {
                f = 1.0f;
            }
            animate.alpha(f).setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_90)).withStartAction(new e(9, this)).withEndAction(new c((Object) this, z, 27)).start();
        }
    }
}
