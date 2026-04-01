package com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate;

import B4.c;
import Fb.k;
import Y7.a;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.ISlideshowView;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.transformer.SShowMarginPageTransformer;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.transformer.SShowPageTransformerFaceCircleUp;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.transformer.SShowPageTransformerFadeInOut;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Timer;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideDelegate extends AbsVuDelegate<ISlideshowView> {
    static final int SLIDE_SHOW_TIMER_ID = Timer.getTimerId();
    private boolean mIsScaleUp = true;
    private boolean mSlidePaused = true;
    private SlideTransitionAnimation mTransitionAnim;
    ViewPager2 mViewPager;

    public SlideDelegate(ISlideshowView iSlideshowView) {
        super(iSlideshowView);
    }

    private void enableOsd(boolean z) {
        if (z != ((ContainerModel) this.mModel).isOsdVisible()) {
            Log.d(this.TAG, "enableOsd", Boolean.valueOf(z));
            ((ContainerModel) this.mModel).setOsdVisible(z);
            ((ISlideshowView) this.mView).enableOsd(z);
        }
    }

    private SlideTransitionAnimation getTransitionAnimation() {
        if (this.mTransitionAnim == null) {
            this.mTransitionAnim = new SlideTransitionAnimation(this.mViewPager);
        }
        return this.mTransitionAnim;
    }

    private int getViewPagerMargin() {
        Resources resources;
        Context context = ((ISlideshowView) this.mView).getContext();
        if (context != null) {
            resources = context.getResources();
        } else {
            resources = null;
        }
        if (resources != null) {
            return resources.getDimensionPixelOffset(R.dimen.viewer_item_margin);
        }
        String str = this.TAG;
        Log.e(str, "getViewPagerMargin fail " + context);
        return 60;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ RectF lambda$onSlideshowTimer$0(PeopleDataDelegate peopleDataDelegate) {
        return peopleDataDelegate.getFaceRectF(((ContainerModel) this.mModel).getPosition() + 1);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setAnnounceVoiceAssistant$3(boolean z, Context context) {
        if (z) {
            SeApiCompat.announceVoiceAssistant(context, context.getString(R.string.slideshow_tts_slideshow_started_double_tap_to_pause));
        } else {
            SeApiCompat.announceVoiceAssistant(context, context.getString(R.string.slideshow_tts_tap_resume_button_to_resume));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stopMove$1() {
        this.mTransitionAnim.cancel();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stopMove$2() {
        this.mViewPager.setPageTransformer(new SShowMarginPageTransformer(getViewPagerMargin()));
    }

    private void pauseSlideshow() {
        if (!this.mSlidePaused) {
            Log.d(this.TAG, "pauseSlideshow");
            this.mSlidePaused = true;
            Timer.stopTimer(SLIDE_SHOW_TIMER_ID);
            ((ISlideshowView) this.mView).keepScreenOn(false);
            stopMove();
            enableOsd(true);
            ViewPager2 viewPager2 = this.mViewPager;
            if (viewPager2 != null) {
                viewPager2.setUserInputEnabled(true);
            }
            this.mIsScaleUp = true;
            setAnnounceVoiceAssistant(((ISlideshowView) this.mView).getContext(), false);
        }
    }

    private void setAnnounceVoiceAssistant(Context context, boolean z) {
        if (context != null) {
            ThreadUtil.postOnBgThread(new c(z, (Object) context, 16));
        }
    }

    private void stopMove() {
        boolean z;
        String str = this.TAG;
        if (this.mTransitionAnim != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(str, "stopMove", Boolean.valueOf(z));
        if (this.mTransitionAnim != null) {
            this.mViewPager.setPageTransformer(new SShowPageTransformerFadeInOut(true, getViewPagerMargin()));
            this.mViewPager.startAnimation(new SlideResetAnimation(this.mViewPager, this.mTransitionAnim.getCurrentOffset(), new a(this, 0)));
        }
        ThreadUtil.postOnUiThreadDelayed(new a(this, 1), 500);
    }

    public void moveNext(ViewPager2.PageTransformer pageTransformer) {
        if (this.mViewPager != null) {
            Log.d(this.TAG, "moveNext");
            this.mViewPager.setPageTransformer(pageTransformer);
            this.mViewPager.startAnimation(getTransitionAnimation());
        }
    }

    public void onBindView(View view) {
        this.mViewPager = (ViewPager2) view.findViewById(R.id.view_pager);
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3200) {
            return false;
        }
        pauseSlideshow();
        return true;
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        if (!((ContainerModel) this.mModel).isOsdVisible()) {
            resumeSlideshow();
        }
    }

    public void onPause() {
        pauseSlideshow();
    }

    public void onSlideshowTimer(int i2) {
        SShowPageTransformerFadeInOut sShowPageTransformerFadeInOut;
        if (SLIDE_SHOW_TIMER_ID == i2) {
            enableOsd(false);
            RectF rectF = (RectF) Optional.ofNullable((PeopleDataDelegate) getDelegate(PeopleDataDelegate.class)).map(new A5.a(25, this)).orElse((Object) null);
            if (rectF == null) {
                sShowPageTransformerFadeInOut = new SShowPageTransformerFadeInOut(this.mIsScaleUp, getViewPagerMargin());
            } else {
                sShowPageTransformerFadeInOut = new SShowPageTransformerFaceCircleUp(rectF, this.mIsScaleUp, getViewPagerMargin());
            }
            moveNext(sShowPageTransformerFadeInOut);
            this.mIsScaleUp = !this.mIsScaleUp;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        resumeSlideshow();
    }

    public void resumeSlideshow() {
        Log.d(this.TAG, "resumeSlideshow");
        if (this.mSlidePaused) {
            setAnnounceVoiceAssistant(((ISlideshowView) this.mView).getContext(), true);
            this.mSlidePaused = false;
        }
        int i2 = SLIDE_SHOW_TIMER_ID;
        Timer.stopTimer(i2);
        Timer.startTimer(i2, 800, new k(3, this));
        ((ISlideshowView) this.mView).keepScreenOn(true);
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            viewPager2.setUserInputEnabled(false);
        }
    }
}
