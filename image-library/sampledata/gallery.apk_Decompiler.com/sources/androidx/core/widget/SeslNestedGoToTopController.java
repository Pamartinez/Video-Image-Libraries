package androidx.core.widget;

import android.os.Build;
import android.util.Log;
import android.util.StateSet;
import android.view.MotionEvent;
import androidx.core.widget.SeslGoToTopController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslNestedGoToTopController extends SeslGoToTopController {
    private boolean mIsSupportGoToTop = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends SeslGoToTopControllerBuilder<SeslNestedGoToTopController, Builder> {
        public SeslNestedGoToTopController build() {
            validate();
            return new SeslNestedGoToTopController(this.host, this.config);
        }
    }

    public SeslNestedGoToTopController(SeslGoToTopController.Host host, SeslGoToTopConfig seslGoToTopConfig) {
        super(host, seslGoToTopConfig);
        this.mHost = host;
    }

    public int getAutoHideDelayMs() {
        return 2500;
    }

    public int getScrollToTopDurationMs() {
        return this.mConfig.getScrollToTopDurationMs();
    }

    public boolean isAvailable() {
        if (Build.VERSION.SDK_INT >= 33 && isSupportGoToTop() && super.isAvailable()) {
            return true;
        }
        return false;
    }

    public boolean isSupportGoToTop() {
        return this.mIsSupportGoToTop;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isAvailable()) {
            int actionMasked = motionEvent.getActionMasked();
            int x9 = (int) (motionEvent.getX() + 0.5f);
            int y = (int) (motionEvent.getY() + 0.5f);
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        if (actionMasked == 3 && this.mGoToTopState != 0) {
                            this.mGoToTopImage.setState(StateSet.NOTHING);
                            return false;
                        }
                    } else if (getState() == 2) {
                        if (!this.mGoToTopRect.contains(x9, y)) {
                            setState(1);
                            this.mGoToTopImage.setState(StateSet.NOTHING);
                            autoHide(1);
                            return true;
                        }
                    }
                } else if (getState() == 2) {
                    if (this.mHost.canScrollUp()) {
                        SeslGoToTopController.OnGoToTopClickListener onGoToTopClickListener = this.mOnClickListener;
                        if (onGoToTopClickListener == null || !onGoToTopClickListener.onGoToTopClick()) {
                            this.mHost.smoothScrollToTop();
                        }
                    }
                    setState(1);
                    autoHide(1);
                    this.mGoToTopImage.setState(StateSet.NOTHING);
                    this.mHost.playSoundEffect(0);
                    return true;
                }
                return true;
            }
            setScrollRunning(false);
            if (!(this.mGoToTopView == null || getState() == 2 || !this.mGoToTopRect.contains(x9, y))) {
                applyState(2);
                this.mGoToTopImage.setHotspot((float) x9, (float) y);
                this.mGoToTopImage.setState(new int[]{16842919, 16842910, 16842913});
                return true;
            }
        }
        return false;
    }

    public boolean onTouchPenEvent(MotionEvent motionEvent) {
        if (!isAvailable()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        int x9 = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        switch (actionMasked) {
            case 211:
                if (this.mGoToTopState != 2 && this.mGoToTopRect.contains(x9, y)) {
                    applyState(2);
                    this.mGoToTopImage.setHotspot((float) x9, (float) y);
                    this.mGoToTopImage.setState(new int[]{16842919, 16842910, 16842913});
                    return true;
                }
            case 212:
                if (this.mGoToTopState == 2) {
                    Log.d("SeslNestedGoToTopController", "pen up false GOTOTOP");
                    if (this.mHost.canScrollUp()) {
                        this.mHost.smoothScrollToTop();
                        this.mHost.showTopEdgeEffect();
                    }
                    applyState(0);
                    this.mGoToTopImage.setState(StateSet.NOTHING);
                    return true;
                }
                break;
            case 213:
                if (this.mGoToTopState == 2 && !this.mGoToTopRect.contains(x9, y)) {
                    this.mGoToTopState = 1;
                    this.mGoToTopImage.setState(StateSet.NOTHING);
                    return true;
                }
            default:
                return false;
        }
        return false;
    }

    public void setSupportGoToTop(boolean z) {
        this.mIsSupportGoToTop = z;
    }
}
