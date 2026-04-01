package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.functional.Operator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Operator {
    public final /* synthetic */ int d;
    public final /* synthetic */ SimgpPlugin e;

    public /* synthetic */ l(SimgpPlugin simgpPlugin, int i2) {
        this.d = i2;
        this.e = simgpPlugin;
    }

    public final MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        int i2 = this.d;
        SimgpPlugin simgpPlugin = this.e;
        switch (i2) {
            case 0:
                return simgpPlugin.resize(mediaBuffer, mutableMediaBuffer);
            case 1:
                return simgpPlugin.rotate(mediaBuffer, mutableMediaBuffer);
            default:
                return simgpPlugin.cvtColor(mediaBuffer, mutableMediaBuffer);
        }
    }
}
