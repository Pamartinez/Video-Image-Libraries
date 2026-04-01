package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExecutionListener {
    void onExecuted(WorkGenerationalId workGenerationalId, boolean z);
}
