package androidx.work.impl;

import androidx.work.impl.model.WorkSpec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Scheduler {
    void cancel(String str);

    boolean hasLimitedSchedulingSlots();

    void schedule(WorkSpec... workSpecArr);
}
