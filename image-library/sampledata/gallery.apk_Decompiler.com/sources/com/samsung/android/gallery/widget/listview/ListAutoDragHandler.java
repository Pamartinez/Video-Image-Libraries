package com.samsung.android.gallery.widget.listview;

import Fb.k;
import com.samsung.android.gallery.support.utils.Timer;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListAutoDragHandler {
    private static final int AUTO_DRAG_TIMER_ID = Timer.getTimerId();
    private int mAutoDragIndex = -1;
    private boolean mAutoDragIndexSelected;
    private final IntConsumer mOnAutoDragReady;
    private int mStartIndex = -1;

    public ListAutoDragHandler(IntConsumer intConsumer) {
        this.mOnAutoDragReady = intConsumer;
    }

    /* access modifiers changed from: private */
    public void onTimer(int i2) {
        IntConsumer intConsumer;
        if (i2 == AUTO_DRAG_TIMER_ID && (intConsumer = this.mOnAutoDragReady) != null) {
            intConsumer.accept(this.mAutoDragIndex);
        }
    }

    public static void stopTimer() {
        Timer.stopTimer(AUTO_DRAG_TIMER_ID);
    }

    public void finish() {
        this.mStartIndex = -1;
        stopTimer();
    }

    public void startTimer(int i2, boolean z) {
        if (!(this.mAutoDragIndex == i2 && this.mAutoDragIndexSelected == z)) {
            stopTimer();
        }
        int i7 = AUTO_DRAG_TIMER_ID;
        if (!Timer.isTimerRunning(i7)) {
            if (this.mStartIndex < 0) {
                this.mStartIndex = i2;
            }
            this.mAutoDragIndex = i2;
            this.mAutoDragIndexSelected = z;
            Timer.startTimer(i7, 1000, new k(0, this));
        }
    }
}
