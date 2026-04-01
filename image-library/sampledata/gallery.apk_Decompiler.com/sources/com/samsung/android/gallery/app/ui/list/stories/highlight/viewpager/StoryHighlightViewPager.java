package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import com.samsung.android.gallery.widget.livemotion.abstraction.IDuration;
import java.util.Optional;
import q8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryHighlightViewPager extends LiveMotionViewPager {
    protected ViewPropertyAnimator mEndAnimator;
    protected boolean mSupportEndEffect;
    protected boolean mSupportFaceCircle;

    public StoryHighlightViewPager(ViewGroup viewGroup, IDuration iDuration) {
        super(viewGroup, iDuration);
        setVisibility(4, 0);
    }

    private void handleZoomOrigin(boolean z, int i2) {
        ViewPagerHolder viewPagerHolder = (ViewPagerHolder) getViewHolder(i2);
        if (viewPagerHolder == null) {
            return;
        }
        if (z) {
            ZoomOriginHelper.applyOriginScale(viewPagerHolder, true);
        } else {
            ZoomOriginHelper.applySmartCropScale(viewPagerHolder, true);
        }
    }

    public int getInitialPosition() {
        return getAdapter().getFocusDataPosition();
    }

    public boolean isActiveZoom() {
        return this.mZoomDelegate.isActive();
    }

    public boolean isDoneEndAnimation() {
        if (!((Boolean) Optional.ofNullable((ViewPagerHolder) getViewHolder(this.mViewPager.getCurrentItem())).map(new a(25)).orElse(Boolean.FALSE)).booleanValue() || !this.mAdapter.isLast(this.mViewPager.getCurrentItem())) {
            return false;
        }
        return true;
    }

    public boolean isVideo() {
        return getAdapter().isVideo();
    }

    public abstract void onDataUpdated();

    public abstract void onEscapeEndPosition(int i2, boolean z);

    public void onPageScrolled(int i2, float f, int i7) {
        ViewPropertyAnimator viewPropertyAnimator = this.mEndAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        super.onPageScrolled(i2, f, i7);
    }

    public abstract void onStoryChanged();

    public void restoreZoom() {
        this.mZoomDelegate.restore();
    }

    public void setKeepPause(boolean z) {
        super.setKeepPause(z);
        if (PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE) {
            handleZoomOrigin(this.mKeepPause, getCurrentItem());
        }
    }

    public void setSupportEndEffect(boolean z) {
        this.mSupportEndEffect = z;
    }

    public void setSupportFaceCircle(boolean z) {
        this.mSupportFaceCircle = z;
    }

    public boolean supportFaceCircle() {
        return this.mSupportFaceCircle;
    }

    public IStoryHighlightViewPagerAdapter getAdapter() {
        return (IStoryHighlightViewPagerAdapter) this.mAdapter;
    }
}
