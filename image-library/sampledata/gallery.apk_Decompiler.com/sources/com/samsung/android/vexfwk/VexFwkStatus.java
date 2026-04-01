package com.samsung.android.vexfwk;

import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\b\tB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/VexFwkStatus;", "", "code", "", "<init>", "(I)V", "getCode", "()I", "OK", "ERR", "Lcom/samsung/android/vexfwk/VexFwkStatus$ERR;", "Lcom/samsung/android/vexfwk/VexFwkStatus$OK;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkStatus {
    private final int code;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/vexfwk/VexFwkStatus$ERR;", "Lcom/samsung/android/vexfwk/VexFwkStatus;", "error", "Lcom/samsung/android/vexfwk/VexFwkError;", "<init>", "(Lcom/samsung/android/vexfwk/VexFwkError;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ERR extends VexFwkStatus {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ERR(VexFwkError vexFwkError) {
            super(vexFwkError.getCode(), (e) null);
            j.e(vexFwkError, "error");
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/samsung/android/vexfwk/VexFwkStatus$OK;", "Lcom/samsung/android/vexfwk/VexFwkStatus;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OK extends VexFwkStatus {
        public static final OK INSTANCE = new OK();

        private OK() {
            super(0, (e) null);
        }
    }

    public /* synthetic */ VexFwkStatus(int i2, e eVar) {
        this(i2);
    }

    public final int getCode() {
        return this.code;
    }

    private VexFwkStatus(int i2) {
        this.code = i2;
    }
}
