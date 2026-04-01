package com.samsung.android.imagetranslation;

import android.graphics.Bitmap;
import com.samsung.android.imagetranslation.data.MaskResponse;
import com.samsung.android.imagetranslation.inpainting.InpainterResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface LttEngineListener {
    void onFailure(int i2, int i7);

    void onInPaintingSuccess(int i2, InpainterResult inpainterResult);

    void onRenderSuccess(int i2, Bitmap bitmap);

    void onRenderSuccess(Bitmap bitmap) {
    }

    void onRenderFailure(String str) {
    }

    void onMaskAvailable(int i2, MaskResponse maskResponse) {
    }

    void onMessageReceived(int i2, Object obj) {
    }
}
