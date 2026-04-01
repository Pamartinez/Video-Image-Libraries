package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ float d;
    public final /* synthetic */ float e;

    public /* synthetic */ e(float f, float f5) {
        this.d = f;
        this.e = f5;
    }

    public final void accept(Object obj) {
        ImgpDecorateFilter.lambda$run$4(this.d, this.e, (MediaBuffer) obj);
    }
}
