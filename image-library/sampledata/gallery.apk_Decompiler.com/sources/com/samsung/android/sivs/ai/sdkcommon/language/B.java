package com.samsung.android.sivs.ai.sdkcommon.language;

import android.os.Bundle;
import android.os.IInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface B extends IInterface {
    void onComplete();

    void onError(Bundle bundle);

    void onNext(String str);
}
