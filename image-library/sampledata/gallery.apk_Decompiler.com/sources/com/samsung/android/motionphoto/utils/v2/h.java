package com.samsung.android.motionphoto.utils.v2;

import com.samsung.android.sum.core.types.nn.NNFW;
import com.samsung.scsp.error.LoggerConfig;
import i.C0212a;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;

    public /* synthetic */ h(String str, int i2) {
        this.d = i2;
        this.e = str;
    }

    public final Object get() {
        int i2 = this.d;
        String str = this.e;
        switch (i2) {
            case 0:
                return MotionPhotoFormat.lambda$of$1(str);
            case 1:
                return NNFW.lambda$fromExtension$1(str);
            case 2:
                return C0212a.l("callbackUrl :", str);
            default:
                return LoggerConfig.lambda$new$0(str);
        }
    }
}
