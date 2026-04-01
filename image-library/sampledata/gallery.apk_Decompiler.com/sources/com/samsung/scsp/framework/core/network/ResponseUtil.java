package com.samsung.scsp.framework.core.network;

import com.samsung.android.sdk.mobileservice.common.result.CommonStatusCodes;
import com.samsung.android.sum.core.message.Message;
import com.samsung.scsp.common.Status;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ResponseUtil {
    private static final Predicate<Integer> REDIRECTED_STATUS = new c(2);
    private static final int[] REDIRECTED_STATUS_ARRAY = {CommonStatusCodes.DEVICE_OUT_OF_STORAGE, 301, 303, TEMPORARY_REDIRECT};
    private static final Predicate<Integer> RESPONSIBLE_STATUS = new c(1);
    private static final int[] RESPONSIBLE_STATUS_ARRAY = {200, 201, 202, 204, Message.END_OF_STREAM, RESUMABLE_INCOMPLETE_UPLOAD_V1, RESUMABLE_INCOMPLETE_UPLOAD_V2, Status.NOT_MODIFIED};
    private static final int RESUMABLE_INCOMPLETE_UPLOAD_V1 = 308;
    private static final int RESUMABLE_INCOMPLETE_UPLOAD_V2 = 251;
    private static final int TEMPORARY_REDIRECT = 307;

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$0(Integer num, int i2) {
        if (num.intValue() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$2(Integer num, int i2) {
        if (num.intValue() == i2) {
            return true;
        }
        return false;
    }

    public static Predicate<Integer> redirected() {
        return REDIRECTED_STATUS;
    }

    public static Predicate<Integer> responsible() {
        return RESPONSIBLE_STATUS;
    }
}
