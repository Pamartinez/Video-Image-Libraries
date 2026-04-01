package com.samsung.android.gallery.module.cloud;

import android.os.Bundle;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                SamsungCloudCompat.SamsungCloud2StatusHolder.lambda$static$0((Boolean) obj);
                return;
            case 1:
                SamsungCloudCompat.SamsungCloud2StatusHolder.lambda$static$1((Integer) obj);
                return;
            case 2:
                SamsungCloudCompat.SamsungCloud2StatusHolder.lambda$static$2((Integer) obj);
                return;
            default:
                SamsungCloudCompat.SamsungCloud2StatusHolder.lambda$static$3((Bundle) obj);
                return;
        }
    }
}
