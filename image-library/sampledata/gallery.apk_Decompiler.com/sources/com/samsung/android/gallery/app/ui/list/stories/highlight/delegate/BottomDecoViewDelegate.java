package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import B4.c;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.DurationHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.livemotion.DurationMeasure;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerScrolledValues;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Optional;
import java.util.stream.IntStream;
import k6.b;
import n5.e;
import o6.f;
import o6.g;
import o6.h;
import o6.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomDecoViewDelegate extends Delegate {
    private static final int[] DURATION_FORMAT = {R.string.details_ms, R.string.details_hms};
    private ImageView mAudioMuteIcon;
    private View mBgmIcon;
    private View mBottomControl;
    private View mBottomDecoViewLayout;
    private int mCurrentTime = -1;
    private final DurationMeasure mDurationMeasure = createDurationMeasure();
    private View mFilterIcon;
    private boolean mIsMute;
    private TextView mPlayDurationView;
    private ImageView mPlaySwitchIcon;
    private int mTotalTime = -1;

    public BottomDecoViewDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        if (iStoryHighlightView.getOptions().supportBgm() && iStoryHighlightView.getOptions().isAudioDefaultMuted()) {
            this.mIsMute = true;
        }
    }

    private void enableAudioMuteIcon(boolean z) {
        ViewUtils.setViewEnabled(this.mAudioMuteIcon, z);
    }

    private int getAudioMuteIconRes(boolean z) {
        if (z) {
            return R.drawable.btn_sound_off_t;
        }
        return R.drawable.btn_sound_on;
    }

    private float[] getDurationInterpolation(ViewPagerScrolledValues viewPagerScrolledValues, MediaItem mediaItem) {
        if (MediaItemStory.isLiveEffectItem(mediaItem)) {
            int itemDuration = DurationHelper.getItemDuration(mediaItem) / 1000;
            float f = 1.0f / ((float) itemDuration);
            int i2 = itemDuration - 1;
            float[] fArr = new float[i2];
            IntStream.range(0, i2).forEach(new h(fArr, f));
            return fArr;
        }
        return new float[]{viewPagerScrolledValues.getInterpolation(0.25f), viewPagerScrolledValues.getInterpolation(0.5f), viewPagerScrolledValues.getInterpolation(0.75f)};
    }

    private boolean isPlaying() {
        return ((Boolean) Optional.ofNullable(this.mPlaySwitchIcon.getTag()).map(new e(6)).orElse(Boolean.TRUE)).booleanValue();
    }

    private boolean isViewInitialized() {
        if (this.mBottomDecoViewLayout != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        setVisible(Boolean.FALSE, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$2(Object[] objArr) {
        Boolean bool = Boolean.TRUE;
        setVisible(bool, bool);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$3(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf("pause".equals(this.mPlaySwitchIcon.getTag()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$4(DataRequest dataRequest, Object[] objArr) {
        return Integer.valueOf(this.mDurationMeasure.getCurrentDuration());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$5(DataRequest dataRequest, Object[] objArr) {
        return Integer.valueOf(this.mDurationMeasure.getTotalDuration());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$6(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(ViewUtils.isVisible(this.mBottomDecoViewLayout));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$7(DataRequest dataRequest, Object[] objArr) {
        return this.mDurationMeasure;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getDurationInterpolation$10(float[] fArr, float f, int i2) {
        fArr[i2] = (((float) i2) * f) + f;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initView$0(View view, MotionEvent motionEvent) {
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVisible$8() {
        lambda$setVisible$9(true);
    }

    /* access modifiers changed from: private */
    public void onAudioMuteClicked(View view) {
        int i2;
        AnalyticsDetail analyticsDetail;
        ImageView imageView = this.mAudioMuteIcon;
        if (imageView != null) {
            if (this.mView.setInputBlock(this.TAG + "_onAudioMuteClicked", 500)) {
                boolean z = !this.mIsMute;
                this.mIsMute = z;
                imageView.setImageResource(getAudioMuteIconRes(z));
                Context context = imageView.getContext();
                if (this.mIsMute) {
                    i2 = R.string.unmute;
                } else {
                    i2 = R.string.mute;
                }
                imageView.setContentDescription(context.getString(i2));
                this.mEventHandler.lambda$postEvent$0(Event.USER_AUDIO_MUTE, Boolean.valueOf(this.mIsMute));
                AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_STORY_HIGHLIGHT_AUDIO_TOGGLE;
                if (this.mIsMute) {
                    analyticsDetail = AnalyticsDetail.EVENT_AUDIO_MUTE;
                } else {
                    analyticsDetail = AnalyticsDetail.EVENT_AUDIO_UNMUTE;
                }
                postAnalyticsLog(analyticsEventId, analyticsDetail);
                if (!this.mView.getOptions().isSlideshow()) {
                    GalleryPreference.getInstance().saveState(PreferenceName.STORY_HIGHLIGHT_AUDIO_MUTED, this.mIsMute);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onBgmButtonClicked(View view) {
        this.mEventHandler.postEvent(Event.SHOW_BGM_PICKER_ALONE, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onFilterButtonClicked(View view) {
        this.mEventHandler.postEvent(Event.SHOW_THEME_VIEW, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onKeepPauseStateChanged(Object... objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        updatePlayPauseButton(booleanValue);
        enableAudioMuteIcon(!booleanValue);
        sendAnalyticsLog(Event.PLAYER_KEEP_PAUSE, booleanValue);
    }

    /* access modifiers changed from: private */
    public void onPlayButtonClicked(View view) {
        int i2;
        boolean isPlaying = isPlaying();
        View view2 = (View) this.mPlaySwitchIcon.getParent();
        if (isPlaying) {
            i2 = R.string.play_video;
        } else {
            i2 = R.string.pause_video;
        }
        view2.setContentDescription(AppResources.getString(i2));
        Log.d(this.TAG, "onPlayButtonClicked", Boolean.valueOf(isPlaying), this.mEventHandler.requestData(DataRequest.IS_VIEW_PAGER_PLAYING, Boolean.FALSE));
        if (!PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE) {
            this.mEventHandler.postEvent(Event.PLAYER_KEEP_PAUSE, Boolean.valueOf(isPlaying));
        } else if (this.mView.setInputBlock("keep_pause", 200)) {
            this.mEventHandler.postEvent(Event.PLAYER_KEEP_PAUSE, Boolean.valueOf(isPlaying));
        }
    }

    /* access modifiers changed from: private */
    public void onViewPagerScrolled(Object... objArr) {
        ViewPagerScrolledValues viewPagerScrolledValues = objArr[0];
        this.mDurationMeasure.update(viewPagerScrolledValues.getPosition(), viewPagerScrolledValues.getPositionOffset(), getDurationInterpolation(viewPagerScrolledValues, this.mEventHandler.getCurrentMediaItem()));
        updateDurationText(new Object[0]);
    }

    private void sendAnalyticsLog(Event event, boolean z) {
        AnalyticsDetail analyticsDetail;
        if (event == Event.PLAYER_KEEP_PAUSE) {
            AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_STORY_HIGHLIGHT_PLAY_BUTTON;
            if (z) {
                analyticsDetail = AnalyticsDetail.STORY_HIGHLIGHT_PAUSE;
            } else {
                analyticsDetail = AnalyticsDetail.STORY_HIGHLIGHT_PLAY;
            }
            postAnalyticsLog(analyticsEventId, analyticsDetail);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setBottomDecoViewVisibility */
    public void lambda$setVisible$9(boolean z) {
        int i2;
        View view = this.mBottomDecoViewLayout;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        ViewUtils.setVisibility(view, i2);
    }

    private void updateBottomControlView() {
        int dimensionPixelOffset = this.mView.getResources().getDimensionPixelOffset(R.dimen.story_highlight_bottom_control_side_padding);
        this.mBottomControl.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        int dimensionPixelOffset2 = this.mView.getResources().getDimensionPixelOffset(R.dimen.story_highlight_deco_duration_min_height);
        ViewGroup.LayoutParams layoutParams = this.mBottomControl.getLayoutParams();
        layoutParams.height = dimensionPixelOffset2;
        this.mBottomControl.setLayoutParams(layoutParams);
        ImageView imageView = this.mAudioMuteIcon;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
            layoutParams2.width = dimensionPixelOffset2;
            this.mAudioMuteIcon.setLayoutParams(layoutParams2);
        }
        View view = this.mFilterIcon;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
            layoutParams3.width = dimensionPixelOffset2;
            this.mFilterIcon.setLayoutParams(layoutParams3);
        }
    }

    private boolean updateDuration(int i2, int i7) {
        boolean z;
        if (i2 == this.mCurrentTime && i7 == this.mTotalTime) {
            z = false;
        } else {
            z = true;
        }
        this.mCurrentTime = i2;
        this.mTotalTime = i7;
        return z;
    }

    /* access modifiers changed from: private */
    public void updateDurationText(Object... objArr) {
        updateDurationText(this.mDurationMeasure.getCurrentDuration(), this.mDurationMeasure.getTotalDuration());
    }

    private void updatePlayPauseButton(boolean z) {
        String str;
        int i2;
        ImageView imageView = this.mPlaySwitchIcon;
        if (z) {
            str = "pause";
        } else {
            str = "play";
        }
        imageView.setTag(str);
        ImageView imageView2 = this.mPlaySwitchIcon;
        if (z) {
            i2 = R.drawable.gallery_ic_detail_play;
        } else {
            i2 = R.drawable.gallery_ic_detail_pause;
        }
        imageView2.setImageResource(i2);
    }

    private void updateWindowInset() {
        int i2;
        View view = this.mBottomDecoViewLayout;
        if (view != null) {
            View view2 = (View) view.getParent();
            if (!this.mView.isInMultiWindowMode()) {
                i2 = WindowUtils.getSystemInsetsBottom(view2.getRootWindowInsets());
            } else {
                i2 = 0;
            }
            ViewMarginUtils.setBottomPadding(view2, i2);
        }
    }

    public void addListenEvent() {
        addEvent(Event.ENABLE_BOTTOM_DECO, new g(this, 1));
        addEvent(Event.VIEW_PAGER_SCROLLED, new g(this, 2));
        addEvent(Event.SLIDE_SHOW_DONE, new g(this, 3));
        addEvent(Event.PLAYER_KEEP_PAUSE, new g(this, 4));
        addEvent(Event.SHOW_THEME_OPTION, new g(this, 5));
        addEvent(Event.HIDE_THEME_OPTION, new g(this, 0));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.PLAYER_KEEP_PAUSED, new f(this, 0));
        addRequestProvider(DataRequest.CURRENT_PLAY_TIME, new f(this, 1));
        addRequestProvider(DataRequest.TOTAL_PLAY_TIME, new f(this, 2));
        addRequestProvider(DataRequest.BOTTOM_DECO_VISIBLE, new f(this, 3));
        addRequestProvider(DataRequest.DURATION_MEASURE, new f(this, 4));
    }

    public DurationMeasure createDurationMeasure() {
        return new DurationMeasure();
    }

    public void handleResolutionChange(int i2) {
        updateWindowInset();
        updateBottomControlView();
    }

    public void initView(View view) {
        ImageView imageView;
        View findViewById = view.findViewById(R.id.bottom_deco_container);
        this.mBottomDecoViewLayout = findViewById;
        findViewById.setOnTouchListener(new D5.f(3));
        this.mPlaySwitchIcon = (ImageView) view.findViewById(R.id.play_switch_icon);
        this.mPlayDurationView = (TextView) view.findViewById(R.id.play_duration);
        this.mBottomControl = view.findViewById(R.id.bottom_control);
        updateDurationText(0, 0);
        view.findViewById(R.id.play_switch_layout).setOnClickListener(new i(this, 0));
        if (this.mView.getOptions().supportEditFilter()) {
            View inflate = ((ViewStub) view.findViewById(R.id.filter_icon_stub)).inflate();
            this.mFilterIcon = inflate;
            inflate.setOnClickListener(new i(this, 1));
        } else if (this.mView.getOptions().supportBgmPicker()) {
            View inflate2 = ((ViewStub) view.findViewById(R.id.bgm_icon_stub)).inflate();
            this.mBgmIcon = inflate2;
            inflate2.setOnClickListener(new i(this, 2));
            ViewUtils.setVisibleOrGone(this.mBgmIcon, true);
        }
        if (this.mView.getOptions().supportBgm()) {
            ViewStub viewStub = (ViewStub) view.findViewById(R.id.play_audio_icon_stub);
            if (viewStub != null) {
                imageView = (ImageView) viewStub.inflate();
            } else {
                imageView = null;
            }
            this.mAudioMuteIcon = imageView;
            if (imageView != null) {
                imageView.setOnClickListener(new i(this, 3));
                if (this.mIsMute) {
                    imageView.setImageResource(getAudioMuteIconRes(true));
                    imageView.setContentDescription(imageView.getContext().getString(R.string.unmute));
                }
            }
        }
        updateWindowInset();
        updateBottomControlView();
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        updateWindowInset();
    }

    public void onDataChangedOnUi() {
        if (this.mView.getMediaData() != null) {
            this.mDurationMeasure.measure(this.mView.getMediaData());
            updateDurationText(new Object[0]);
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        updateWindowInset();
    }

    public void setVisible(Object... objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        float f = 0.0f;
        if (objArr[1].booleanValue()) {
            ViewPropertyAnimator animate = this.mBottomDecoViewLayout.animate();
            if (booleanValue) {
                f = 1.0f;
            }
            animate.alpha(f).setDuration(90).setInterpolator(new LinearInterpolator()).withStartAction(new b(27, this)).withEndAction(new c((Object) this, booleanValue, 24)).start();
            return;
        }
        View view = this.mBottomDecoViewLayout;
        if (booleanValue) {
            f = 1.0f;
        }
        view.setAlpha(f);
        lambda$setVisible$9(booleanValue);
    }

    private void updateDurationText(int i2, int i7) {
        if (isViewInitialized() && updateDuration(i2, i7)) {
            Context context = this.mPlayDurationView.getContext();
            int[] iArr = DURATION_FORMAT;
            String B = C0212a.B(TimeUtil.formatDuration(context, i2, iArr), " / ", TimeUtil.formatDuration(context, i7, iArr));
            this.mPlayDurationView.setText(B);
            this.mPlayDurationView.setContentDescription(B);
            ViewGroup.LayoutParams layoutParams = this.mPlayDurationView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = ViewUtils.getMeasureTextViewWidth(this.mPlayDurationView) + 3;
                this.mPlayDurationView.setLayoutParams(layoutParams);
            }
        }
    }
}
