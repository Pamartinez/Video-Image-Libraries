package com.samsung.scsp.common;

import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.identity.ScspCorePreferences;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PushConsumer implements Consumer<PushVo> {
    private static final String CID_LIST = "cidList";
    private static final String SYNC = "sync";
    private final Consumer<PushVo> consumer = new b(12, this);
    private final Logger logger = Logger.get("PushConsumer");
    private final PushConsumerManager pushClientManager = PushConsumerManager.getInstance();
    private final Supplier<String> userIdSupplier;

    public PushConsumer(Supplier<String> supplier) {
        this.userIdSupplier = supplier == null ? new a(2) : supplier;
    }

    private void executeSyncPushExecutor(PushVo pushVo) {
        FaultBarrier.run(new e(1, this, pushVo));
    }

    private boolean isValidDeviceSignature(String str) {
        if (!StringUtil.isEmpty(str)) {
            boolean equals = str.equals((String) FaultBarrier.get(new i(ScspCorePreferences.get().cdid.get(), (String) FaultBarrier.get(new h(1), null).obj, 1), null).obj);
            Logger logger2 = this.logger;
            logger2.i("isValidDeviceSignature: " + equals);
            return equals;
        }
        this.logger.i("isValidDeviceSignature: empty, true");
        return true;
    }

    private boolean isValidSignature(String str) {
        if (!StringUtil.isEmpty(str)) {
            boolean equals = str.equals((String) FaultBarrier.get(new i(this.userIdSupplier.get(), (String) FaultBarrier.get(new h(0), null).obj, 0), null).obj);
            Logger logger2 = this.logger;
            logger2.i("isValidSignature: " + equals);
            return equals;
        }
        this.logger.i("isValidSignature: empty, true");
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeSyncPushExecutor$6(PushVo pushVo) {
        for (String str : pushVo.data.get(CID_LIST).getAsString().split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
            Consumer<PushVo> consumer2 = this.pushClientManager.get(str);
            if (consumer2 != null) {
                consumer2.accept(pushVo);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$new$0() {
        return "";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(PushVo pushVo) {
        this.logger.i(pushVo.category);
        if (isValidSignature(pushVo.signature) && isValidDeviceSignature(pushVo.deviceSignature)) {
            if (SYNC.equals(pushVo.category)) {
                executeSyncPushExecutor(pushVo);
                return;
            }
            Consumer<PushVo> consumer2 = this.pushClientManager.get(pushVo.category);
            if (consumer2 != null) {
                consumer2.accept(pushVo);
            }
        }
    }

    public void accept(PushVo pushVo) {
        this.consumer.accept(pushVo);
    }
}
