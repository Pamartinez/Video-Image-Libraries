package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OpPriorityComputable {
    private ComputeBridge bridge;
    private OpPriorityCompute priorityCompute;
    private final Enum<?> type;

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ComputeBridge {
        float compute(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute);
    }

    public OpPriorityComputable(Enum<?> enumR) {
        this.type = enumR;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ float lambda$compute$0(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
        return -1.0f;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ComputeBridge lambda$compute$1() {
        return new e(0);
    }

    public float compute(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat) {
        return ((ComputeBridge) Optional.ofNullable(this.bridge).orElseGet(new d(0))).compute(mutableMediaFormat, mediaFormat, this.priorityCompute);
    }

    public <T extends Enum<?>> T getType() {
        return this.type;
    }

    public void setComputeBridge(ComputeBridge computeBridge, OpPriorityCompute opPriorityCompute) {
        this.bridge = computeBridge;
        this.priorityCompute = opPriorityCompute;
    }
}
