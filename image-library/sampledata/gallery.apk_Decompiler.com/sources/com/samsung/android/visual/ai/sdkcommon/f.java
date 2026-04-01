package com.samsung.android.visual.ai.sdkcommon;

import android.os.IInterface;
import android.os.ParcelFileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface f extends IInterface {
    void onError(String str);

    void onPfdCreation(ParcelFileDescriptor parcelFileDescriptor, boolean z);

    void onResult(String str, boolean z, boolean z3);
}
