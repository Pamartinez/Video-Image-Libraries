package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.functional.Operator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Operator {
    public final /* synthetic */ int d;
    public final /* synthetic */ NativeUniImgpPlugin e;

    public /* synthetic */ e(NativeUniImgpPlugin nativeUniImgpPlugin, int i2) {
        this.d = i2;
        this.e = nativeUniImgpPlugin;
    }

    public final MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        int i2 = this.d;
        NativeUniImgpPlugin nativeUniImgpPlugin = this.e;
        switch (i2) {
            case 0:
                return nativeUniImgpPlugin.cvtData(mediaBuffer, mutableMediaBuffer);
            case 1:
                return nativeUniImgpPlugin.cvtGamut(mediaBuffer, mutableMediaBuffer);
            case 2:
                return nativeUniImgpPlugin.cvtHdr2Sdr(mediaBuffer, mutableMediaBuffer);
            case 3:
                return nativeUniImgpPlugin.rotate(mediaBuffer, mutableMediaBuffer);
            case 4:
                return nativeUniImgpPlugin.flip(mediaBuffer, mutableMediaBuffer);
            case 5:
                return nativeUniImgpPlugin.crop(mediaBuffer, mutableMediaBuffer);
            case 6:
                return nativeUniImgpPlugin.split(mediaBuffer, mutableMediaBuffer);
            case 7:
                return nativeUniImgpPlugin.merge(mediaBuffer, mutableMediaBuffer);
            case 8:
                return nativeUniImgpPlugin.measureQuality(mediaBuffer, mutableMediaBuffer);
            case 9:
                return nativeUniImgpPlugin.decode(mediaBuffer, mutableMediaBuffer);
            case 10:
                return nativeUniImgpPlugin.encode(mediaBuffer, mutableMediaBuffer);
            case 11:
                return nativeUniImgpPlugin.encodeHDR(mediaBuffer, mutableMediaBuffer);
            case 12:
                return nativeUniImgpPlugin.resize(mediaBuffer, mutableMediaBuffer);
            default:
                return nativeUniImgpPlugin.cvtColor(mediaBuffer, mutableMediaBuffer);
        }
    }
}
