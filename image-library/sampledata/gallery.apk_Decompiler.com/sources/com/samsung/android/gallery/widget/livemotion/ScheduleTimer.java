package com.samsung.android.gallery.widget.livemotion;

import com.samsung.android.gallery.support.utils.Timer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ScheduleTimer {
    private int mDuration = 500;
    private Function<Integer, Boolean> mScheduleListener;
    private final int mScheduleTimerId = Timer.getTimerId();

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$start$0(int i2) {
        Function<Integer, Boolean> function = this.mScheduleListener;
        if (function != null) {
            function.apply(Integer.valueOf(this.mDuration));
            resetDuration();
        }
    }

    private void resetDuration() {
        this.mDuration = 500;
    }

    public ScheduleTimer setDuration(int i2) {
        this.mDuration = i2;
        return this;
    }

    public void setScheduleListener(Function<Integer, Boolean> function) {
        this.mScheduleListener = function;
    }

    public void start() {
        Timer.startTimer(this.mScheduleTimerId, (long) this.mDuration, new a(this));
    }

    public void stop() {
        Timer.stopTimer(this.mScheduleTimerId);
    }
}
