package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ NativeUniImgpPlugin e;
    public final /* synthetic */ MutableMediaBuffer f;
    public final /* synthetic */ HashMap g;

    public /* synthetic */ d(NativeUniImgpPlugin nativeUniImgpPlugin, MutableMediaBuffer mutableMediaBuffer, HashMap hashMap, int i2) {
        this.d = i2;
        this.e = nativeUniImgpPlugin;
        this.f = mutableMediaBuffer;
        this.g = hashMap;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$encode$1(this.f, this.g, (MediaBuffer) obj);
                return;
            default:
                this.e.lambda$encodeHDR$2(this.f, this.g, (MediaBuffer) obj);
                return;
        }
    }
}
