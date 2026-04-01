package com.samsung.android.motionphoto.utils.ex;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.functional.Operator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Operator {
    public final /* synthetic */ int d;
    public final /* synthetic */ WrapVSWEnginePlugin e;

    public /* synthetic */ d(WrapVSWEnginePlugin wrapVSWEnginePlugin, int i2) {
        this.d = i2;
        this.e = wrapVSWEnginePlugin;
    }

    public final MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        int i2 = this.d;
        WrapVSWEnginePlugin wrapVSWEnginePlugin = this.e;
        switch (i2) {
            case 0:
                return wrapVSWEnginePlugin.x2Upscaler(mediaBuffer, mutableMediaBuffer);
            case 1:
                return wrapVSWEnginePlugin.x3Upscaler(mediaBuffer, mutableMediaBuffer);
            default:
                return wrapVSWEnginePlugin.x4Upscaler(mediaBuffer, mutableMediaBuffer);
        }
    }
}
