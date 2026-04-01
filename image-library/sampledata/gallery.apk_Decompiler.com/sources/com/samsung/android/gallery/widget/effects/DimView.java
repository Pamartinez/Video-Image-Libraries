package com.samsung.android.gallery.widget.effects;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.widget.R$styleable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DimView extends View {
    private boolean mIsConsumeTouch;
    private boolean mIsTouchBlocked;
    private Runnable mTouchListener;

    public DimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean isTerminated;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DimView);
            this.mIsConsumeTouch = obtainStyledAttributes.getBoolean(R$styleable.DimView_consumeTouch, true);
            if (obtainStyledAttributes instanceof AutoCloseable) {
                ((AutoCloseable) obtainStyledAttributes).close();
            } else if (obtainStyledAttributes instanceof ExecutorService) {
                ExecutorService executorService = (ExecutorService) obtainStyledAttributes;
                if (executorService != ForkJoinPool.commonPool() && !(isTerminated = executorService.isTerminated())) {
                    executorService.shutdown();
                    boolean z = false;
                    while (!isTerminated) {
                        try {
                            isTerminated = executorService.awaitTermination(1, TimeUnit.DAYS);
                        } catch (InterruptedException unused) {
                            if (!z) {
                                executorService.shutdownNow();
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
            } else {
                obtainStyledAttributes.recycle();
            }
        }
    }

    private boolean isValidArea(float f) {
        float width = (float) ((int) (((float) getWidth()) * 0.1f * 0.5f));
        if (f <= width || f >= ((float) getWidth()) - width) {
            return false;
        }
        return true;
    }

    public void blockTouch(boolean z) {
        blockTouch(z, (Runnable) null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Runnable runnable;
        if (!this.mIsTouchBlocked) {
            return super.onTouchEvent(motionEvent);
        }
        if (isValidArea(motionEvent.getX()) && motionEvent.getAction() == 0 && (runnable = this.mTouchListener) != null) {
            runnable.run();
            this.mTouchListener = null;
        }
        if (this.mIsConsumeTouch || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public void setGone() {
        setVisibility(8);
        blockTouch(false);
    }

    public void setVisible(Runnable runnable) {
        setVisibility(0);
        blockTouch(true, runnable);
    }

    private void blockTouch(boolean z, Runnable runnable) {
        this.mIsTouchBlocked = z;
        this.mTouchListener = runnable;
    }
}
