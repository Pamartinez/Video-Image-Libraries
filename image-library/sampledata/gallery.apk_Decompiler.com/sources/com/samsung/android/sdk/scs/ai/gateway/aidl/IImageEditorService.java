package com.samsung.android.sdk.scs.ai.gateway.aidl;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IImageEditorService {
    Bundle cancel(int i2, Bundle bundle);

    Bundle generate(int i2, Bundle bundle);

    void prepare(int i2);

    void release(int i2);
}
