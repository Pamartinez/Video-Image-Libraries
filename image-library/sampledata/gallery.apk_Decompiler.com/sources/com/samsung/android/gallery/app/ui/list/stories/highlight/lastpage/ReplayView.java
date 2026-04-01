package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import android.animation.Animator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.timer.TimerView;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;
import o4.a;
import q6.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReplayView<V extends IStoryHighlightView> {
    protected final String TAG = getClass().getSimpleName();
    private boolean mIsShowing;
    private Consumer<Integer> mOnReplayClickListener;
    private View mReplayButton;
    /* access modifiers changed from: private */
    public View mRootView;
    /* access modifiers changed from: private */
    public final TimerView mTimerView;
    private final IStoryHighlightView mView;

    public ReplayView(V v) {
        this.mView = v;
        this.mTimerView = new TimerView().setTime(4);
    }

    private void initProgressView() {
        this.mTimerView.initView(this.mReplayButton);
        ViewUtils.setVisibleOrGone(this.mReplayButton.findViewById(R.id.replay_icon), false);
        ViewUtils.setVisibleOrGone(this.mReplayButton.findViewById(R.id.progress_container), true);
        this.mTimerView.setOnFinishListener(new a(8, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initProgressView$3(Void voidR) {
        this.mView.getEventHandler().postEvent(Event.MOVE_TO_HIGHLIGHT, 0, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        onReplayClicked(-1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$1(View view) {
        onRelatedCloseClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$2(View view) {
        onReplayClicked(0);
    }

    private void onRelatedCloseClicked() {
        BlackboardUtils.publishBackKeyEvent(this.mView.getBlackboard());
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_RELATED_CLOSE);
    }

    private void startAnimation(final boolean z, int i2) {
        float f;
        if (!this.mView.isDestroyed()) {
            ViewPropertyAnimator animate = this.mRootView.animate();
            if (z) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            animate.alpha(f).setDuration((long) i2).setListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    float f;
                    ViewUtils.setVisibleOrGone(ReplayView.this.mRootView, z);
                    View e = ReplayView.this.mRootView;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    ViewUtils.setAlpha(e, f);
                    if (!z) {
                        ReplayView.this.mTimerView.reset();
                    }
                }

                public void onAnimationStart(Animator animator) {
                    ViewUtils.setVisibleOrGone(ReplayView.this.mRootView, true);
                }
            }).start();
        }
    }

    public void destroy() {
        ViewUtils.removeSelf(this.mRootView);
        this.mTimerView.destroy();
        this.mOnReplayClickListener = null;
        hide();
    }

    public int getLayoutId() {
        return R.layout.story_highlight_replay_layout;
    }

    public void handleResolutionChange() {
        updateReplayButtonSize();
    }

    public void hide() {
        if (this.mIsShowing) {
            this.mIsShowing = false;
            startAnimation(false, 100);
        }
    }

    public void initView(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.mView.getContext()).inflate(getLayoutId(), (ViewGroup) null, false);
        this.mRootView = inflate;
        inflate.findViewById(R.id.back_button).setOnClickListener(new f(this, 0));
        ViewUtils.addView(viewGroup, this.mRootView);
        this.mRootView.findViewById(R.id.close_button).setOnClickListener(new f(this, 1));
        View findViewById = this.mRootView.findViewById(R.id.replay_button_layout);
        this.mReplayButton = findViewById;
        findViewById.setOnClickListener(new f(this, 2));
        initProgressView();
    }

    public boolean isShowing() {
        return this.mIsShowing;
    }

    public final void onReplayClicked(int i2) {
        Consumer<Integer> consumer = this.mOnReplayClickListener;
        if (consumer != null) {
            consumer.accept(Integer.valueOf(i2));
        }
    }

    public void pause() {
        this.mTimerView.pause();
    }

    public void resume() {
        if (this.mIsShowing) {
            this.mTimerView.resume();
        }
    }

    public void setOnReplayClickListener(Consumer<Integer> consumer) {
        this.mOnReplayClickListener = consumer;
    }

    public void show(boolean z) {
        this.mIsShowing = true;
        startAnimation(true, 350);
        if (z) {
            this.mTimerView.resume();
        }
    }

    public void updateReplayButtonSize() {
        ViewGroup.LayoutParams layoutParams = this.mReplayButton.getLayoutParams();
        layoutParams.width = this.mView.getResources().getDimensionPixelOffset(R.dimen.story_highlight_related_sub_button_width);
        layoutParams.height = this.mView.getResources().getDimensionPixelOffset(R.dimen.story_highlight_related_sub_button_height);
        this.mReplayButton.setLayoutParams(layoutParams);
    }
}
