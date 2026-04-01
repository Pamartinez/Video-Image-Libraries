package com.samsung.android.motionphoto.utils.v2;

import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.ServiceStatus;
import com.samsung.android.sum.core.types.Status;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    public /* synthetic */ i(int i2, int i7) {
        this.d = i7;
        this.e = i2;
    }

    public final Object get() {
        int i2 = this.d;
        int i7 = this.e;
        switch (i2) {
            case 0:
                return MotionPhotoFormat.lambda$of$3(i7);
            case 1:
                return DataType.lambda$from$1(i7);
            case 2:
                return MediaType.lambda$of$1(i7);
            case 3:
                return ServiceStatus.lambda$from$1(i7);
            case 4:
                return Status.lambda$from$1(i7);
            default:
                return Integer.valueOf(i7 * 2);
        }
    }
}
